package whocraft.tardis_refined.common.dimension;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelStorageSource;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.util.RegistryHelper;
import whocraft.tardis_refined.common.world.ChunkGenerators;
import whocraft.tardis_refined.common.world.chunk.TardisChunkGenerator;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortals;
import whocraft.tardis_refined.common.mixin.MinecraftServerStorageAccessor;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import static whocraft.tardis_refined.common.util.Platform.getServer;

/*
 * Majority of this code is sourced from Commoble's Hyberbox with permission.
 * You can view their project here: https://github.com/Commoble/hyperbox
 * */

public class DimensionHandler {

    public static ArrayList<ResourceKey<Level>> LEVELS = new ArrayList<>();

    public static void addDimension(ResourceKey<Level> resourceKey) {
        LEVELS.add(resourceKey);
        writeLevels();
    }

    public static Path getWorldSavingDirectory() {
        return getStorage().getDimensionPath(Level.OVERWORLD);
    }

    public static LevelStorageSource.LevelStorageAccess getStorage() {
        return ((MinecraftServerStorageAccessor) getServer()).getStorageSource();
    }

    private static void writeLevels() {
        File file = new File(getWorldSavingDirectory().toFile(), TardisRefined.MODID + "_tardis_info.json");
        JsonObject jsonObject = new JsonObject();

        JsonArray dimensions = new JsonArray();
        for (ResourceKey<Level> level : LEVELS) {
            dimensions.add(level.location().toString());
        }

        jsonObject.add("tardis_dimensions", dimensions);

        TardisRefined.LOGGER.info("Writing {} to: {}", dimensions, file.getAbsolutePath());

        try (FileWriter writer = new FileWriter(file)) {
            TardisRefined.GSON.toJson(jsonObject, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ServerLevel getOrCreateInterior(Level interactionLevel, ResourceLocation resourceLocation) {

        ResourceKey<Level> levelResourceKey = ResourceKey.create(Registries.DIMENSION, resourceLocation);


        if (ModCompatChecker.immersivePortals()) {
            return ImmersivePortals.createDimension(interactionLevel, levelResourceKey);
        }

        if (interactionLevel instanceof ServerLevel serverLevel) {
            ServerLevel existingLevel = getExistingLevel(serverLevel, levelResourceKey);

            if (existingLevel != null) {
                return existingLevel;
            }

            return createDimension(interactionLevel, levelResourceKey);
        }

        return null;

    }

    public static void loadLevels(ServerLevel serverLevel) {
        File file = new File(getWorldSavingDirectory().toFile(), TardisRefined.MODID + "_tardis_info.json");
        if (!file.exists()) return;

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(file.toPath());

            JsonObject jsonObject = TardisRefined.GSON.fromJson(reader, JsonObject.class);
            for (JsonElement dimension : jsonObject.get("tardis_dimensions").getAsJsonArray()) {
                TardisRefined.LOGGER.info("Attempting to load {}", dimension.getAsString());
                ResourceLocation id = new ResourceLocation(dimension.getAsString());
                ResourceKey<Level> levelKey = ResourceKey.create(Registries.DIMENSION, id);
                if (getExistingLevel(serverLevel, levelKey) == null) {
                    TardisRefined.LOGGER.warn("Level {} not found! Creating new level instance", dimension.getAsString());
                    if (DimensionHandler.getOrCreateInterior(serverLevel, id) != null)
                        TardisRefined.LOGGER.warn("Successfully created and loaded new level {}", dimension.getAsString());
                } else {
                    TardisRefined.LOGGER.info("Successfully loaded existing level {}", dimension.getAsString());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @ExpectPlatform
    public static ServerLevel createDimension(Level level, ResourceKey<Level> id) {
        throw new AssertionError(TardisRefined.PLATFORM_ERROR);
    }

    public static LevelStem formLevelStem(MinecraftServer server, ResourceKey<LevelStem> stem) {
        RegistryAccess access = server.registryAccess();

        return new LevelStem(access.registryOrThrow(Registries.DIMENSION_TYPE).getHolderOrThrow(TRDimensionTypes.TARDIS), new TardisChunkGenerator(access.registryOrThrow(Registries.BIOME).getHolderOrThrow(ChunkGenerators.TARDIS_BIOME)));
    }


    public static ServerLevel getExistingLevel(ServerLevel serverLevel, String id) {
        return getExistingLevel(serverLevel, ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TardisRefined.MODID, id)));
    }

    public static ServerLevel getExistingLevel(ServerLevel serverLevel, ResourceKey<Level> levelResourceKey) {
        Map<ResourceKey<Level>, ServerLevel> levelMap = serverLevel.getServer().levels;
        @Nullable ServerLevel existingLevel = levelMap.get(levelResourceKey);
        return existingLevel;
    }

}
