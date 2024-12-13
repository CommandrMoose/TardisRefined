package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRFeatureKeys;
import whocraft.tardis_refined.registry.TRTagKeys;

import static whocraft.tardis_refined.common.data.ProviderPlacedFeatures.TARDIS_ROOT_CLUSTER_PLACED_FEATUE;

public class BiomeModifierProvider {


    private static final ResourceKey<BiomeModifier> ADD_TARDIS_ROOT_CLUSTER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TRFeatureKeys.TARDIS_ROOT_CLUSTER_RL);

    private static final ResourceKey<BiomeModifier> ZEITON = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "zeiton"));
    private static final ResourceKey<BiomeModifier> ZEITON_SMALL = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "zeiton_small"));


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        var overworldTags = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);

        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        BiomeModifiers.AddFeaturesBiomeModifier oreModifer = new BiomeModifiers.AddFeaturesBiomeModifier(overworldTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.ORE_ZEITON)), GenerationStep.Decoration.UNDERGROUND_ORES);
        BiomeModifiers.AddFeaturesBiomeModifier oreModiferSmall = new BiomeModifiers.AddFeaturesBiomeModifier(overworldTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.ORE_ZEITON_SMALL)), GenerationStep.Decoration.UNDERGROUND_ORES);

        context.register(ZEITON, oreModifer);
        context.register(ZEITON_SMALL, oreModiferSmall);

        var tardisRoot = context.lookup(Registries.BIOME).getOrThrow(TRTagKeys.TARDIS_ROOT_CLUSTER);

        context.register(ADD_TARDIS_ROOT_CLUSTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                tardisRoot,
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(TARDIS_ROOT_CLUSTER_PLACED_FEATUE)),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS
        ));
    }
}