package whocraft.tardis_refined.common.data;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.world.Features;
import whocraft.tardis_refined.common.world.feature.NbtTemplateFeature;
import whocraft.tardis_refined.common.world.feature.config.NbtTemplateFeatureConfig;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRFeatureKeys;

public class ProviderConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ZEITON = createKey("ore_zeiton");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ZEITON_SMALL = createKey("ore_zeiton_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TARDIS_ROOT_CLUSTER_CONF_FEATURE = ResourceKey.create(Registries.CONFIGURED_FEATURE, TRFeatureKeys.TARDIS_ROOT_CLUSTER_RL);

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        ResourceLocation templateLocation = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "cave/tardis_root_cluster_deepslate");
        ConfiguredFeature<NbtTemplateFeatureConfig, NbtTemplateFeature> tardisRootCluster = new ConfiguredFeature<>(Features.NBT_FEATURE.get(), new NbtTemplateFeatureConfig(templateLocation, 0));

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, ORE_ZEITON, Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(stoneReplaceable, TRBlockRegistry.ZEITON_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceable, TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get().defaultBlockState())), 9));
        register(context, ORE_ZEITON_SMALL, Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(stoneReplaceable, TRBlockRegistry.ZEITON_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceable, TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get().defaultBlockState())), 4));
        context.register(TARDIS_ROOT_CLUSTER_CONF_FEATURE, tardisRootCluster);
    }

    public static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Feature<NoneFeatureConfiguration> feature) {
        register(context, key, feature, FeatureConfiguration.NONE);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
