package whocraft.tardis_refined.common.dimension.fabric;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.util.Unit;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.mixin.MappedRegistryAccessor;
import whocraft.tardis_refined.common.network.messages.sync.SyncLevelListMessage;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortals;

import java.util.concurrent.Executor;
import java.util.function.BiFunction;

import static whocraft.tardis_refined.common.dimension.DimensionHandler.LEVELS;
import static whocraft.tardis_refined.common.dimension.DimensionHandler.addDimension;
import static whocraft.tardis_refined.common.util.Platform.getServer;

public class DimensionHandlerImpl {

    public static ServerLevel createDimension(Level level, ResourceKey<Level> id) {

        if (ModCompatChecker.immersivePortals()) {
            return ImmersivePortals.createDimension(level, id);
        }

        BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory = DimensionHandler::formLevelStem;

        MinecraftServer server = getServer();
        ServerLevel overworld = server.getLevel(Level.OVERWORLD);

        final ResourceKey<LevelStem> dimensionKey = ResourceKey.create(Registries.LEVEL_STEM, id.location());

        LevelStem dimension = dimensionFactory.apply(server, dimensionKey);

        ChunkProgressListener chunkListener = server.progressListenerFactory.create(11);
        Executor executor = server.executor;
        LevelStorageSource.LevelStorageAccess levelSave = DimensionHandler.getStorage();

        WorldData serverConfig = server.getWorldData();
        DerivedLevelData derivedWorldInfo = new DerivedLevelData(serverConfig, serverConfig.overworldData());

        //Actually register our dimension
        Registry<LevelStem> dimensionRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        if (dimensionRegistry instanceof MappedRegistry<LevelStem> writableRegistry) {
            MappedRegistryAccessor accessor = (MappedRegistryAccessor) writableRegistry;
            accessor.setFrozen(false); //Must unfreeze registry to allow our dimension to persist
            writableRegistry.register(dimensionKey, dimension, Lifecycle.stable());
        } else {
            throw new IllegalStateException(String.format("Unable to register dimension %s -- dimension registry not writable", dimensionKey.location()));
        }

        // now we have everything we need to create the world instance
        ServerLevel newLevel = new ServerLevel(
                server,
                executor,
                levelSave,
                derivedWorldInfo,
                id,
                dimension,
                chunkListener,
                false, // boolean: is-debug-world
                BiomeManager.obfuscateSeed(serverConfig.worldGenOptions().seed()),
                ImmutableList.of(), // "special spawn list"
                // phantoms, raiders, travelling traders, cats are overworld special spawns
                // the dimension loader is hardcoded to initialize preexisting non-overworld worlds with no special spawn lists
                // so this can probably be left empty for best results and spawns should be handled via other means
                false, // "tick time", true for overworld, always false for everything else
                new RandomSequences(BiomeManager.obfuscateSeed(serverConfig.worldGenOptions().seed())));

        addDimension(newLevel.dimension());

        overworld.getWorldBorder().addListener(new BorderChangeListener.DelegateBorderChangeListener(newLevel.getWorldBorder()));

        server.levels.put(id, newLevel);

        ServerWorldEvents.LOAD.invoker().onWorldLoad(server, newLevel);

        new SyncLevelListMessage(newLevel.dimension(), true).sendToAll();


        BlockPos blockPos = new BlockPos(0, 0, 0);
        ChunkPos chunkPos = new ChunkPos(blockPos);
        chunkListener.updateSpawnPos(chunkPos);
        ServerChunkCache serverchunkcache = newLevel.getChunkSource();
        serverchunkcache.getLightEngine().checkBlock(blockPos); //Runs lighting update
        serverchunkcache.addRegionTicket(TicketType.START, chunkPos, 11, Unit.INSTANCE);

        return newLevel;
    }

    public static void clear() {
        LEVELS.clear();
    }
}
