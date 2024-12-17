package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRItemRegistry;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(DataGenerator arg, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(arg.getPackOutput(), completableFuture);
    }


    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TRBlockRegistry.BULK_HEAD_DOOR.get()).pattern(" L ").pattern("PDP").pattern("RDR").define('L', Blocks.REDSTONE_LAMP).define('P', Blocks.PISTON).define('D', Blocks.IRON_DOOR).define('R', Blocks.REDSTONE_WIRE).unlockedBy("has_crafting_table", has(Blocks.TARGET)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TRItemRegistry.SCREWDRIVER.get()).pattern(" A ").pattern("BR ").pattern(" L ").define('L', Items.LAPIS_LAZULI).define('B', Items.STONE_BUTTON).define('R', Items.BLAZE_ROD).define('A', Items.AMETHYST_SHARD).unlockedBy("has_crafting_table", has(Items.LAPIS_LAZULI)).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get()).pattern("IAA").pattern("ILA").pattern("RII").define('L', Items.REDSTONE_LAMP).define('I', Items.IRON_INGOT).define('R', Items.REDSTONE_TORCH).define('A', Blocks.AMETHYST_BLOCK).unlockedBy("has_crafting_table", has(Items.REDSTONE_LAMP)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TRItemRegistry.PATTERN_MANIPULATOR.get()).pattern("RCL").pattern("EAE").pattern(" S ").define('S', Items.STICK).define('E', Items.REDSTONE).define('A', Items.IRON_INGOT).define('R', Items.RED_DYE).define('C', Items.GREEN_DYE).define('L', Items.LAPIS_LAZULI).unlockedBy("has_crafting_table", has(TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TRItemRegistry.DRILL.get()).pattern(" P ").pattern("PCP").pattern("IRI").define('P', Items.IRON_PICKAXE).define('R', Items.REDSTONE).define('I', Items.IRON_INGOT).define('C', Items.COBBLESTONE).unlockedBy("has_crafting_table", has(Items.REDSTONE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, TRItemRegistry.GLASSES.get()).pattern("S S").pattern("S S").pattern("GZG").define('S', Items.STICK).define('G', Items.GLASS_PANE).define('Z', TRItemRegistry.ZEITON_INGOT.get()).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_INGOT.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TRItemRegistry.KEY.get()).requires(Items.IRON_INGOT).requires(Items.TRIPWIRE_HOOK).unlockedBy("has_crafting_table", has(Blocks.TRIPWIRE_HOOK)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TRBlockRegistry.GLOBAL_DOOR_BLOCK.get()).requires(Items.IRON_INGOT).requires(Items.TRIPWIRE_HOOK).requires(Items.IRON_DOOR).unlockedBy("has_crafting_table", has(Blocks.IRON_DOOR)).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()).requires(TRItemRegistry.ZEITON_INGOT.get()).requires(Items.COPPER_BLOCK).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()).requires(TRItemRegistry.ZEITON_INGOT.get()).requires(Items.IRON_BLOCK).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_INGOT.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TRBlockRegistry.ZEITON_BLOCK.get()).pattern("ZZZ").pattern("ZZZ").pattern("ZZZ").define('Z', TRItemRegistry.ZEITON_INGOT.get()).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TRItemRegistry.ZEITON_INGOT.get(), 9).requires(TRBlockRegistry.ZEITON_BLOCK.get()).unlockedBy("has_crafting_table", has(TRBlockRegistry.ZEITON_BLOCK.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TRItemRegistry.ZEITON_NUGGET.get(), 9).requires(TRItemRegistry.ZEITON_INGOT.get()).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_INGOT.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TRItemRegistry.ZEITON_INGOT.get()).pattern("ZZZ").pattern("ZZZ").pattern("ZZZ").define('Z', TRItemRegistry.ZEITON_NUGGET.get()).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_NUGGET.get())).save(consumer, new ResourceLocation(TardisRefined.MODID, "zeiton_ingot_from_nugget"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TRBlockRegistry.ZEITON_LANTERN.get()).pattern("ZZZ").pattern("ZTZ").pattern("ZZZ").define('Z', TRItemRegistry.ZEITON_NUGGET.get()).define('T', Items.TORCH).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_NUGGET.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, TRItemRegistry.MALLET.get()).pattern("ZZZ").pattern("ZZZ").pattern(" S ").define('Z', Blocks.STONE).define('S', Items.STICK).unlockedBy("has_crafting_table", has(Blocks.STONE)).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TRItemRegistry.TEST_TUBE.get(), 1).requires(Blocks.GLASS_PANE).requires(Items.SLIME_BALL).unlockedBy("has_crafting_table", has(TRItemRegistry.ZEITON_INGOT.get())).save(consumer);


        // Smelting
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(TRItemRegistry.RAW_ZEITON.get()), RecipeCategory.MISC, TRItemRegistry.ZEITON_INGOT.get(), 0.7F, 300).unlockedBy("has_any_zeiton", has(TRItemRegistry.RAW_ZEITON.get())).save(consumer, new ResourceLocation(TardisRefined.MODID, "smelt_zeiton"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(TRItemRegistry.RAW_ZEITON.get()), RecipeCategory.MISC, TRItemRegistry.ZEITON_INGOT.get(), 0.8F, 150).unlockedBy("has_any_zeiton", has(TRItemRegistry.RAW_ZEITON.get())).save(consumer, new ResourceLocation(TardisRefined.MODID, "blast_zeiton"));
    }
}
