package whocraft.tardis_refined.common.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import whocraft.tardis_refined.TardisRefined;

/** Helper for creating common objects.
 * Allows for smoother transition to future versions where registries change*/
public class RegistryHelper {

    public static ResourceKey<ConfiguredFeature<?,?>> makeConfiguredFeatureKey(ResourceLocation rl){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, rl);
    }

    public static ResourceKey<PlacedFeature> makePlacedFeatureKey(ResourceLocation rl){
        return ResourceKey.create(Registries.PLACED_FEATURE, rl);
    }

    public static TagKey<Biome> makeGenericBiomeTagCollection(String name){
        return TagKey.create(Registries.BIOME, new ResourceLocation(TardisRefined.MODID, "collections/" + name));
    }

    public static TagKey<Biome> makeBiomeTagForFeature(String name){
        return TagKey.create(Registries.BIOME, new ResourceLocation(TardisRefined.MODID, "has_structure/" + name));
    }
}
