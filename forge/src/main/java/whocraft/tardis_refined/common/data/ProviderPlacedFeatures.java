package whocraft.tardis_refined.common.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRFeatureKeys;

import java.util.List;

import static whocraft.tardis_refined.common.data.ProviderConfiguredFeatures.TARDIS_ROOT_CLUSTER_CONF_FEATURE;

public class ProviderPlacedFeatures {

    public static final ResourceKey<PlacedFeature> TARDIS_ROOT_CLUSTER_PLACED_FEATUE = ResourceKey.create(Registries.PLACED_FEATURE, TRFeatureKeys.TARDIS_ROOT_CLUSTER_RL);
    public static final ResourceKey<PlacedFeature> ORE_ZEITON = createKey("ore_zeiton");
    public static final ResourceKey<PlacedFeature> ORE_ZEITON_SMALL = createKey("ore_zeiton_small");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(TARDIS_ROOT_CLUSTER_PLACED_FEATUE, new PlacedFeature
                (context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(TARDIS_ROOT_CLUSTER_CONF_FEATURE),
                        List.of(
                                RarityFilter.onAverageOnceEvery(25),
                                InSquarePlacement.spread(),
                                HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(20)),
                                BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.matchesFluids(new Vec3i(0, 5, 0), Fluids.WATER)))))
        );


        register(context, ORE_ZEITON, configuredFeatures.getOrThrow(ProviderConfiguredFeatures.ORE_ZEITON), List.copyOf(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
        register(context, ORE_ZEITON_SMALL, configuredFeatures.getOrThrow(ProviderConfiguredFeatures.ORE_ZEITON_SMALL), List.copyOf(commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)))));

    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, name));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier plMod, PlacementModifier plMod2) {
        return List.of(plMod, InSquarePlacement.spread(), plMod2, BiomeFilter.biome(), RarityFilter.onAverageOnceEvery(3));
    }

    public static List<PlacementModifier> commonOrePlacement(int amt, PlacementModifier plMod) {
        return orePlacement(CountPlacement.of(amt), plMod);
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
