package whocraft.tardis_refined.common.data;

import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.block.device.AntiGravityBlock;
import whocraft.tardis_refined.common.block.device.TerraformerBlock;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import static whocraft.tardis_refined.registry.TRBlockRegistry.BLOCKS;

public class TRBlockModelProvider extends BlockStateProvider {

    public TRBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), TardisRefined.MODID, existingFileHelper);
    }

    public JsonObject emptyBlockState(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(TardisRefined.MODID, "block/terraformer"))).build());
        return builder.toJson();
    }

    public JsonObject terraformer(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block).forAllStates(blockState -> blockState.getValue(TerraformerBlock.ACTIVE) ?
                ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(TardisRefined.MODID, "block/terraformer_on"))).build() :
                ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(TardisRefined.MODID, "block/terraformer"))).build());

        return builder.toJson();
    }

    public JsonObject lantern(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block).forAllStates(blockState -> blockState.getValue(LanternBlock.HANGING) ?
                ConfiguredModel.builder().modelFile(models().withExistingParent("block/zeiton_lantern_hanging", new ResourceLocation("block/template_hanging_lantern")).texture("lantern", new ResourceLocation(TardisRefined.MODID, "block/zeiton_lantern"))).build() :
                ConfiguredModel.builder().modelFile(models().withExistingParent("block/zeiton_lantern", new ResourceLocation("block/template_lantern")).texture("lantern", new ResourceLocation(TardisRefined.MODID, "block/zeiton_lantern"))).build());

        return builder.toJson();
    }

    // Paul McGann is...
    public JsonObject threeDeeRotating(Block block, ResourceLocation location) {
        VariantBlockStateBuilder builder = getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder().modelFile(new ModelFile.UncheckedModelFile(location)).rotationY((int) blockState.getValue(HorizontalDirectionalBlock.FACING).toYRot()).build());
        return builder.toJson();
    }

    public ResourceLocation getBlockResourceLocation(Block block) {
        return BLOCKS.getRegistry().get().getKey(block);
    }

    public ResourceLocation getBlockTextureResourceLocation(Block block) {
        ResourceLocation blockTex = BLOCKS.getRegistry().get().getKey(block);
        return new ResourceLocation(TardisRefined.MODID, "block/" + blockTex.getPath());
    }

    public JsonObject customLocation(Block block, ResourceLocation resourceLocation) {
        return getVariantBuilder(block).partialState().modelForState().modelFile(models().getExistingFile(resourceLocation)).addModel().toJson();
    }

    public JsonObject customLocation(Block block) {
        return getVariantBuilder(block).partialState().modelForState().modelFile(cubeAll(block)).addModel().toJson();
    }

    public JsonObject antiGravityBlock(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        ResourceLocation modelLocation0 = new ResourceLocation(TardisRefined.MODID, "block/gravity_well");

        for (int space = 0; space <= 5; space++) {
            ResourceLocation modelLocation = space == 0 ? modelLocation0 : new ResourceLocation(TardisRefined.MODID, "block/gravity_well_" + space);
            builder.partialState().with(AntiGravityBlock.SPACE, space).modelForState()
                    .modelFile(models().getExistingFile(modelLocation))
                    .addModel();
        }
        return builder.toJson();
    }


    @Override
    protected void registerStatesAndModels() {
        ResourceLocation leavesTexture = new ResourceLocation("tardis_refined:block/ars_leaves");
        ResourceLocation growthStoneTexture = new ResourceLocation("tardis_refined:block/growth_stone");

/*
        Blocks that are rendered in code
*/
        emptyBlockState(TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get());
        emptyBlockState(TRBlockRegistry.BULK_HEAD_DOOR.get());
        emptyBlockState(TRBlockRegistry.GLOBAL_DOOR_BLOCK.get());
        emptyBlockState(TRBlockRegistry.GLOBAL_SHELL_BLOCK.get());
        emptyBlockState(TRBlockRegistry.ROOT_SHELL_DOOR.get());
        emptyBlockState(TRBlockRegistry.ROOT_PLANT_BLOCK.get());
        emptyBlockState(TRBlockRegistry.ROOT_SHELL_BLOCK.get());
        emptyBlockState(TRBlockRegistry.ARS_EGG.get());
        emptyBlockState(TRBlockRegistry.ARTRON_PILLAR.get());
        emptyBlockState(TRBlockRegistry.THE_EYE.get());


        threeDeeRotating(TRBlockRegistry.LANDING_PAD.get(), new ResourceLocation(TardisRefined.MODID, "block/landing_pad"));
        threeDeeRotating(TRBlockRegistry.FLIGHT_DETECTOR.get(), new ResourceLocation(TardisRefined.MODID, "block/flight_detector"));

        terraformer(TRBlockRegistry.TERRAFORMER_BLOCK.get());

        /*
         **Basic Blocks**
         */
        customLocation(TRBlockRegistry.ARS_LEAVES.get());
        customLocation(TRBlockRegistry.AIR_LOCK_GENERATION_BLOCK.get());
        customLocation(TRBlockRegistry.FOOLS_STONE.get());
        simpleBlock(TRBlockRegistry.ARTRON_PILLAR_PORT.get());
       // simpleBlock(TRBlockRegistry.ASTRAL_MAP.get());

        customLocation(TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get(), new ResourceLocation(TardisRefined.MODID, "block/astral_manipulator"));
        customLocation(TRBlockRegistry.CORRIDOR_TELEPORTER.get(), new ResourceLocation(TardisRefined.MODID, "block/corridor_teleporter"));
        customLocation(TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get());
        customLocation(TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get());
        customLocation(TRBlockRegistry.ZEITON_ORE.get());
        customLocation(TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get());
        customLocation(TRBlockRegistry.ZEITON_BLOCK.get());

        antiGravityBlock(TRBlockRegistry.GRAVITY_WELL.get());

        /*
         **Fences**
         */
        fenceBlock(TRBlockRegistry.ARS_LEAVES_FENCE.get(), leavesTexture);

        /*
         **Slabs**
         */
        slabBlock(TRBlockRegistry.ARS_LEAVES_SLAB.get(), leavesTexture, leavesTexture, leavesTexture, leavesTexture);


        threeDeeRotating(TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get(), new ResourceLocation(TardisRefined.MODID, "block/console_configuration"));

        lantern(TRBlockRegistry.ZEITON_LANTERN.get());
    }


}