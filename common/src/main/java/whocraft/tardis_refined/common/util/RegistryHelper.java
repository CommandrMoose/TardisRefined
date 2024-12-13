package whocraft.tardis_refined.common.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;

/**
 * Helper for creating common objects.
 * Allows for smoother transition to future versions where registries change
 */
public class RegistryHelper {

    public static ResourceLocation makeKey(String id) {
        return ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> makeConfiguredFeatureKey(ResourceLocation rl) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, rl);
    }

    public static ResourceKey<PlacedFeature> makePlacedFeatureKey(ResourceLocation rl) {
        return ResourceKey.create(Registries.PLACED_FEATURE, rl);
    }

    public static TagKey<Biome> makeGenericBiomeTagCollection(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "collections/" + name));
    }

    public static TagKey<Biome> makeBiomeTagForFeature(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "has_structure/" + name));
    }

    public static TagKey<Block> makeBlockTag(String modid, String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(modid, name));
    }

    public static TagKey<Item> makeItemTag(String modid, String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name));
    }


    public static ResourceLocation getKey(ConsoleTheme theme) {
        return ConsoleTheme.CONSOLE_THEME_DEFERRED_REGISTRY.getKey(theme);
    }

    public static ResourceLocation getKey(ShellTheme theme) {
        return ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.getKey(theme);
    }
}
