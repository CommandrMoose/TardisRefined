package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipe;

public class TRCraftingRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE_DEFERRED_REGISTRY = DeferredRegister.create(TardisRefined.MODID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeType<ManipulatorCraftingRecipe>> ASTRAL_MANIPULATOR_RECIPE = RECIPE_TYPE_DEFERRED_REGISTRY.register("astral_manipulator_recipe", () -> registerRecipeType(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "astral_manipulator_recipe")));

    /**
     * Need a custom registry method as vanilla will register our entries under the minecraft namspace which creates a duplicate entry
     * We don't want that to happen so we have a custom method
     */
    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final ResourceLocation name) {
        final String toString = name.toString();
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return toString;
            }
        };
    }

}
