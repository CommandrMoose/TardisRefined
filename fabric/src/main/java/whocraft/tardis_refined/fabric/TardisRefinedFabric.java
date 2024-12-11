package whocraft.tardis_refined.fabric;

import fuzs.forgeconfigapiport.api.config.v3.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.fml.config.ModConfig;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.items.DimensionSamplerItem;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.common.util.fabric.PlatformImpl;
import whocraft.tardis_refined.common.world.fabric.TRFabricBiomeModifiers;
import whocraft.tardis_refined.compat.CuriosTrinketsUtil;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortals;
import whocraft.tardis_refined.compat.portals.fabric.PortalsCompatFabric;
import whocraft.tardis_refined.compat.trinkets.TrinketsUtil;
import whocraft.tardis_refined.fabric.events.ModEvents;
import whocraft.tardis_refined.patterns.ConsolePatterns;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.TRItemRegistry;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static net.minecraft.server.packs.PackType.SERVER_DATA;

public class TardisRefinedFabric implements ModInitializer {
    /**
     * For use with Fabric BiomeModification API
     */
    public static void setupBiomeModifications() {
        TRFabricBiomeModifiers.addFeatures();
    }

    public static void register(PackType packType, ResourceLocation id, PreparableReloadListener listener) {
        ResourceManagerHelper.get(packType).registerReloadListener(new IdentifiableResourceReloadListener() {
            @Override
            public ResourceLocation getFabricId() {
                return id;
            }

            @Override
            public String getName() {
                return listener.getName();
            }

            @Override
            public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
                return listener.reload(preparationBarrier, resourceManager, profilerFiller, profilerFiller2, executor, executor2);
            }
        });
    }

    @Override
    public void onInitialize() {
        PlatformImpl.init();
        ModEvents.addCommonEvents();
        TardisRefined.init();
        setupBiomeModifications();
        ForgeConfigRegistry.INSTANCE.register(TardisRefined.MODID, ModConfig.Type.COMMON, TRConfig.COMMON_SPEC);
        ForgeConfigRegistry.INSTANCE.register(TardisRefined.MODID, ModConfig.Type.CLIENT, TRConfig.CLIENT_SPEC);
        ForgeConfigRegistry.INSTANCE.register(TardisRefined.MODID, ModConfig.Type.SERVER, TRConfig.SERVER_SPEC);


        register(SERVER_DATA, new ResourceLocation(TardisRefined.MODID, TardisRefined.MODID + "/" + "console_patterns"), ConsolePatterns.getReloadListener());

        register(SERVER_DATA, new ResourceLocation(TardisRefined.MODID, TardisRefined.MODID + "/" + "desktops"), TardisDesktops.getReloadListener());
        register(SERVER_DATA, new ResourceLocation(TardisRefined.MODID, TardisRefined.MODID + "/" + "shell_patterns"), ShellPatterns.getReloadListener());
        register(SERVER_DATA, new ResourceLocation(TardisRefined.MODID, TardisRefined.MODID + "/" + "hums"), TardisHums.getReloadListener());

        if (ModCompatChecker.immersivePortals()) {
            if (TRConfig.COMMON.COMPATIBILITY_IP.get()) {
                ImmersivePortals.init();
                PortalsCompatFabric.init();
            }
        } else {
            TardisRefined.LOGGER.info("ImmersivePortals was not detected.");
        }

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TardisRefined.MODID, "ore_zeiton")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TardisRefined.MODID, "ore_zeiton_small")));

        if (Platform.isModLoaded("trinkets")) {
            CuriosTrinketsUtil.setInstance(new TrinketsUtil());
        }



    }
}