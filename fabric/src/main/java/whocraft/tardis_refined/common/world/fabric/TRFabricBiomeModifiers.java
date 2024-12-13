package whocraft.tardis_refined.common.world.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import whocraft.tardis_refined.TardisRefined;

/**
 * Central class that uses Fabric's BiomeModification API
 */
public class TRFabricBiomeModifiers {

    public static void addFeatures() {
        addToBiome("tardis_root_cluster", "has_structure/tardis_root_cluster", GenerationStep.Decoration.LOCAL_MODIFICATIONS);
    }

    /**
     * Fabric's Biome Modification API isn't data driven and must be called for each feature/structure we add to a biome
     */
    private static void addToBiome(String featureName, String biomeKeyName, GenerationStep.Decoration step) {
        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, featureName))
                .add(ModificationPhase.ADDITIONS,
                        (context) -> context.hasTag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, biomeKeyName))),
                        (context) -> context.getGenerationSettings().addFeature(step, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, featureName))));
    }
}