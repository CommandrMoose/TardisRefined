package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingIngredient;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorRecipes;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRTagKeys;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ProviderBlockTags extends BlockTagsProvider {

    public ProviderBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TardisRefined.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (Block blocksEntry : TRBlockRegistry.BLOCKS.getRegistry().get().stream().toList()) {
            Block block = blocksEntry;

            if (TRBlockRegistry.BLOCKS.getKey(block).getNamespace().equals(TardisRefined.MODID)) {
                /*Fences*/
                if (block instanceof FenceBlock fenceBlock) {
                    tag(BlockTags.FENCES).add(fenceBlock);
                }

                /*Leaves*/
                if (block instanceof LeavesBlock leavesBlock) {
                    tag(BlockTags.LEAVES).add(leavesBlock);
                }

                /*Slabs*/
                if (block instanceof SlabBlock slabBlock) {
                    tag(BlockTags.SLABS).add(slabBlock);
                }
            }
        }

        tag(BlockTags.DRAGON_IMMUNE).add(TRBlockRegistry.ROOT_SHELL_BLOCK.get()).add(TRBlockRegistry.GLOBAL_SHELL_BLOCK.get()).add(TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get());
        tag(BlockTags.WITHER_IMMUNE).add(TRBlockRegistry.ROOT_SHELL_BLOCK.get()).add(TRBlockRegistry.GLOBAL_SHELL_BLOCK.get()).add(TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get())
                .add(TRBlockRegistry.LANDING_PAD.get())
                .add(TRBlockRegistry.FLIGHT_DETECTOR.get())
                .add(TRBlockRegistry.TERRAFORMER_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get())
                .add(TRBlockRegistry.ZEITON_ORE.get())
                .add(TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_BLOCK.get())
                .add(TRBlockRegistry.GRAVITY_WELL.get())
                .add(TRBlockRegistry.ROOT_PLANT_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get())
                .add(TRBlockRegistry.LANDING_PAD.get())
                .add(TRBlockRegistry.GRAVITY_WELL.get())
                .add(TRBlockRegistry.FLIGHT_DETECTOR.get())
                .add(TRBlockRegistry.TERRAFORMER_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get())
                .add(TRBlockRegistry.ZEITON_ORE.get())
                .add(TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get())
                .add(TRBlockRegistry.ZEITON_BLOCK.get())
                .add(TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get());


        // ===== DIAGONAL WALLS =====
        // This is cursed, but we gotta do what we gotta do

        // Blocks
        Set<Block> normalBlocks = new HashSet<>();
        ManipulatorRecipes.MANIPULATOR_CRAFTING_RECIPES.forEach((resourceLocation, manipulatorCraftingRecipe) -> {
            for (ManipulatorCraftingIngredient ingredient : manipulatorCraftingRecipe.ingredients()) {
                normalBlocks.add(ingredient.inputBlockState().getBlock());
            }
        });
        tag(TRTagKeys.DIAGONAL_COMPAT_WALLS).add(normalBlocks.toArray(new Block[0]));

        Set<Block> glassBlocks = new HashSet<>();
        ManipulatorRecipes.MANIPULATOR_CRAFTING_RECIPES.forEach((resourceLocation, manipulatorCraftingRecipe) -> {
            for (ManipulatorCraftingIngredient ingredient : manipulatorCraftingRecipe.ingredients()) {
                if(ingredient.inputBlockState().getBlock() instanceof GlassBlock) {
                    glassBlocks.add(ingredient.inputBlockState().getBlock());
                }
            }
        });
        tag(TRTagKeys.DIAGONAL_COMPAT_GLASS).add(glassBlocks.toArray(new Block[0]));

        Set<Block> fenceBlocks = new HashSet<>();
        ManipulatorRecipes.MANIPULATOR_CRAFTING_RECIPES.forEach((resourceLocation, manipulatorCraftingRecipe) -> {
            for (ManipulatorCraftingIngredient ingredient : manipulatorCraftingRecipe.ingredients()) {
                if(ingredient.inputBlockState().getBlock() instanceof FenceBlock) {
                    fenceBlocks.add(ingredient.inputBlockState().getBlock());
                }
            }
        });
        tag(TRTagKeys.DIAGONAL_COMPAT_FENCES).add(fenceBlocks.toArray(new Block[0]));

    }
}
