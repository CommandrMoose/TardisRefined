package whocraft.tardis_refined.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorBlockResult;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingResult;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorItemResult;

/**
 * 50ap5ud5 11/05/2024: Register our own recipe types to allow for different data to be defined depending on the recipe type
 */
public class TRManipulatorRecipeResultTypes {

    /**
     * Registry Key for the Astral Manipulator Recipe Result type registry. For addon mods, use this as the registry key
     */
    public static final ResourceKey<Registry<Codec<? extends ManipulatorCraftingResult>>> MANIPULATOR_RECIPE_TYPE_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "manipulator_recipe_result"));

    /**
     * Tardis Refined instance of the Astral Manipulator Recipe Result type registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only
     */
    public static final DeferredRegistry<Codec<? extends ManipulatorCraftingResult>> MANIPULATOR_RECIPE_RESULT_DEFERRED_REGISTRY = DeferredRegistry.createCustom(TardisRefined.MODID, MANIPULATOR_RECIPE_TYPE_KEY, true);

    /**
     * Instance of registry containing all Astral Manipulator Recipe Result type entries. Addon mod entries will be included in this registry as long as they are use the same ResourceKey<Registry<ObjectType>>.
     */
    public static final Registry<Codec<? extends ManipulatorCraftingResult>> RESULT_TYPE_REGISTRY = MANIPULATOR_RECIPE_RESULT_DEFERRED_REGISTRY.getRegistry().get();

    /* Register the codec derived from the MapCodec for each entry since the MapCodec isn't actually inheriting from Codec.
    This is needed because for registration, NBT and JSON read/writing, we need the Codec. However, when we need to find the sub-codec based off the "type" key, we need to use the MapCodec
    */
    public static final RegistrySupplier<Codec<ManipulatorItemResult>> ITEM_RESULT = MANIPULATOR_RECIPE_RESULT_DEFERRED_REGISTRY.register("item_result", () -> ManipulatorItemResult.MAP_CODEC.codec());
    public static final RegistrySupplier<Codec<ManipulatorBlockResult>> BLOCK_RESULT = MANIPULATOR_RECIPE_RESULT_DEFERRED_REGISTRY.register("block_result", () -> ManipulatorBlockResult.MAP_CODEC.codec());
}
