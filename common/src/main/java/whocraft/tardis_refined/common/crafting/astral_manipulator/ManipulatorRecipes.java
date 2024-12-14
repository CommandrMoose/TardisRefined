package whocraft.tardis_refined.common.crafting.astral_manipulator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Default recipes added by Tardis Refined. Used for data generators
 */
public class ManipulatorRecipes {

    public static Map<ResourceLocation, ManipulatorCraftingRecipe> MANIPULATOR_CRAFTING_RECIPES = new HashMap<>();

    public static void registerRecipes() {

        MANIPULATOR_CRAFTING_RECIPES.clear();

        register("console_block", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), Blocks.SMOOTH_STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), Blocks.SMOOTH_STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), Blocks.REDSTONE_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), Blocks.SMOOTH_STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), Blocks.SMOOTH_STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.TOP)),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 0), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 1), Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACE, AttachFace.FLOOR)),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 2), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 0), Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACE, AttachFace.FLOOR)),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 1), Blocks.GLASS),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 2), Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACE, AttachFace.FLOOR)),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 0), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 1), Blocks.STONE_BUTTON.defaultBlockState().setValue(ButtonBlock.FACE, AttachFace.FLOOR)),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 2), Blocks.IRON_TRAPDOOR)

        ), TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get().defaultBlockState()));

        register("terraformer", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), TRBlockRegistry.ZEITON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), Blocks.STONE),

                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 0), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 1), Blocks.GLASS_PANE.defaultBlockState().setValue(StainedGlassPaneBlock.NORTH, true).setValue(StainedGlassPaneBlock.SOUTH, true).setValue(StainedGlassPaneBlock.EAST, true)),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 2), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 0), Blocks.GLASS_PANE.defaultBlockState().setValue(StainedGlassPaneBlock.SOUTH, true).setValue(StainedGlassPaneBlock.EAST, true).setValue(StainedGlassPaneBlock.WEST, true)),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 1), Blocks.EMERALD_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 2), Blocks.GLASS_PANE.defaultBlockState().setValue(StainedGlassPaneBlock.NORTH, true).setValue(StainedGlassPaneBlock.EAST, true).setValue(StainedGlassPaneBlock.WEST, true)),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 0), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 1), Blocks.GLASS_PANE.defaultBlockState().setValue(StainedGlassPaneBlock.NORTH, true).setValue(StainedGlassPaneBlock.SOUTH, true).setValue(StainedGlassPaneBlock.WEST, true)),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 2), Blocks.STONE),

                new ManipulatorCraftingIngredient(new BlockPos(0, 2, 0), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(0, 2, 1), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 2, 2), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(1, 2, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 2, 1), Blocks.DAYLIGHT_DETECTOR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 2, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 2, 0), Blocks.STONE),
                new ManipulatorCraftingIngredient(new BlockPos(2, 2, 1), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 2, 2), Blocks.STONE)

        ), new ItemStack(TRBlockRegistry.TERRAFORMER_BLOCK.get().asItem())));


        register("gravity_well", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), Blocks.PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, Direction.UP)),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get())

        ), new ItemStack(TRBlockRegistry.GRAVITY_WELL.get().asItem())));


        register("flight_detector", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), Blocks.DAYLIGHT_DETECTOR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get())

        ), new ItemStack(TRBlockRegistry.FLIGHT_DETECTOR.get().asItem())));


        register("console_configuration_block", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), Blocks.OBSERVER.defaultBlockState().setValue(ObserverBlock.FACING, Direction.UP)),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get())

        ), new ItemStack(TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get().asItem())));

        register("landing_pad", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), Blocks.TARGET),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), Blocks.AMETHYST_BLOCK),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),

                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 0), Blocks.CUT_COPPER_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 1), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 2), Blocks.CUT_COPPER_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 0), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 1), Blocks.AIR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 2), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 0), Blocks.CUT_COPPER_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 1), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 2), Blocks.CUT_COPPER_SLAB)

        ), new ItemStack(TRBlockRegistry.LANDING_PAD.get().asItem())));


        register("corridor_teleporter", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 1), Blocks.SMOOTH_STONE_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 0), Blocks.SMOOTH_STONE_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 1), TRBlockRegistry.LANDING_PAD.get()),
                new ManipulatorCraftingIngredient(new BlockPos(1, 0, 2), Blocks.SMOOTH_STONE_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 0), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 1), Blocks.SMOOTH_STONE_SLAB),
                new ManipulatorCraftingIngredient(new BlockPos(2, 0, 2), TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get()),

                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 0), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 1), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 2), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 0), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 1), Blocks.AIR),
                new ManipulatorCraftingIngredient(new BlockPos(1, 1, 2), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 0), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 1), Blocks.IRON_TRAPDOOR),
                new ManipulatorCraftingIngredient(new BlockPos(2, 1, 2), Blocks.IRON_TRAPDOOR)

        ), new ItemStack(TRBlockRegistry.CORRIDOR_TELEPORTER.get().asItem())));

        register("artron_pillar", new ManipulatorCraftingRecipe(Arrays.asList(
                new ManipulatorCraftingIngredient(new BlockPos(0, 0, 0), TRBlockRegistry.ZEITON_BLOCK.get()),
                new ManipulatorCraftingIngredient(new BlockPos(0, 1, 0), Blocks.POLISHED_DEEPSLATE_WALL),
                new ManipulatorCraftingIngredient(new BlockPos(0, 2, 0), Blocks.POLISHED_DEEPSLATE_WALL),
                new ManipulatorCraftingIngredient(new BlockPos(0, 3, 0), TRBlockRegistry.ZEITON_LANTERN.get())

        ), TRBlockRegistry.ARTRON_PILLAR.get().defaultBlockState()));
    }

    public static ManipulatorCraftingRecipe register(String id, ManipulatorCraftingRecipe manipulatorCraftingRecipe) {
        return register(new ResourceLocation(TardisRefined.MODID, id), manipulatorCraftingRecipe);
    }

    public static ManipulatorCraftingRecipe register(ResourceLocation id, ManipulatorCraftingRecipe manipulatorCraftingRecipe) {
        MANIPULATOR_CRAFTING_RECIPES.put(id, manipulatorCraftingRecipe.setRegistryId(id));

        return manipulatorCraftingRecipe;
    }


}
