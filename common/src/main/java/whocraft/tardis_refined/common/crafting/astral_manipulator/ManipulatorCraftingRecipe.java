package whocraft.tardis_refined.common.crafting.astral_manipulator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.registry.TRCraftingRecipeSerializers;
import whocraft.tardis_refined.registry.TRCraftingRecipeTypes;

import java.util.*;


/**
 * Main recipe object for the Astral Manipulator.
 **/
public class ManipulatorCraftingRecipe implements CraftingRecipe {
    public static final Codec<ManipulatorCraftingRecipe> CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                    ManipulatorCraftingIngredient.CODEC.listOf().fieldOf("ingredients").forGetter(ManipulatorCraftingRecipe::ingredients),
                    ManipulatorCraftingResult.RESULT_CODEC.fieldOf("result").forGetter(ManipulatorCraftingRecipe::result)
            ).apply(builder, ManipulatorCraftingRecipe::new)
    );
    // List of ingredient blocks for the recipe to work.
    private List<ManipulatorCraftingIngredient> ingredients;
    private ManipulatorCraftingResult result;
    private ResourceLocation id;

    public ManipulatorCraftingRecipe(List<ManipulatorCraftingIngredient> itemList, ManipulatorCraftingResult recipeOutput) {
        this.ingredients = itemList;
        this.result = recipeOutput;
    }

    public ManipulatorCraftingRecipe(List<ManipulatorCraftingIngredient> ingredients, ItemStack recipeOutput) {
        this(ingredients, new ManipulatorItemResult(recipeOutput));
    }

    public ManipulatorCraftingRecipe(List<ManipulatorCraftingIngredient> ingredients, BlockState recipeOutput) {
        this(ingredients, new ManipulatorBlockResult(recipeOutput));
    }

    /**
     * Helper to get all recipes without needing to use a Container. In 1.21+ recipes will be decoupled from Containers but until them, we will need this workaround
     */
    public static List<ManipulatorCraftingRecipe> getAllRecipes(Level level) {
        List<RecipeHolder<ManipulatorCraftingRecipe>> recipeHolders = level.getRecipeManager().getAllRecipesFor(TRCraftingRecipeTypes.ASTRAL_MANIPULATOR_RECIPE.get());
        List<ManipulatorCraftingRecipe> recipeList = new ArrayList<>();
        recipeHolders.forEach(recipeHolder -> recipeList.add(recipeHolder.value().setRegistryId(recipeHolder.id()))); //Make sure to set registry id
        return recipeList;
    }

    /**
     * Get the unique id for this recipe. Needed for debugging purposes, and also to make sure we keep track of the recipes in some manner
     */
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * Sets the id for this recipe. ALWAYS call this when making a new instance of this, as the registry name is null by default
     */
    public ManipulatorCraftingRecipe setRegistryId(ResourceLocation id) {
        this.id = id;
        return this;
    }

    /**
     * Compares a ManipulatorCraftingRecipe to another by sorting by size, then registered block entries
     *
     * @param comparedItemList The items of the recipe to compare to.
     **/
    public boolean hasSameItems(List<ManipulatorCraftingIngredient> comparedItemList) {

        if (ingredients.size() != comparedItemList.size()) {
            return false;
        }

        // Sort the items so the list comparison is better.
        List<ManipulatorCraftingIngredient> sortedIngredients = new ArrayList<ManipulatorCraftingIngredient>(this.ingredients);
        sortedIngredients.sort(Comparator.comparing(a -> a.relativeBlockPos()));

        List<ManipulatorCraftingIngredient> sortedCompareList = new ArrayList<ManipulatorCraftingIngredient>(comparedItemList);
        sortedCompareList.sort(Comparator.comparing(a -> a.relativeBlockPos()));

        for (int i = 0; i < sortedIngredients.size(); i++) {
            if (!sortedIngredients.get(i).IsSameAs(sortedCompareList.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Provides the result of the recipe. This can be either a blockstate or itemstack depending on the result type
     */
    public ManipulatorCraftingResult result() {
        return this.result;
    }

    /**
     * Provides the list of ingredients for the recipe, in the form of a list of blockstate to block position offsets
     */
    public List<ManipulatorCraftingIngredient> ingredients() {
        return this.ingredients;
    }

    /**
     * Default vanilla data, we will add dummy values as we won't be using them
     */
    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public boolean matches(CraftingInput recipeInput, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput recipeInput, HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }


    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        if (result.type() instanceof ManipulatorItemResult manipulatorItemResult) {
            return manipulatorItemResult.recipeOutput();
        }

        if (result.type() instanceof ManipulatorBlockResult manipulatorBlockResult) {
            return new ItemStack(manipulatorBlockResult.recipeOutput().getBlock().asItem());
        }

        return ItemStack.EMPTY;
    }

    /**
     * Required to allow vanilla to parse the recipe during datapack parsing
     */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return TRCraftingRecipeSerializers.ASTRAL_MANIPULATOR.get();
    }

    /**
     * Required to allow vanilla to parse the recipe during datapack parsing
     */
    @Override
    public RecipeType<?> getType() {
        return TRCraftingRecipeTypes.ASTRAL_MANIPULATOR_RECIPE.get();
    }


}
