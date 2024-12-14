package whocraft.tardis_refined.common.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRItemRegistry;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), TardisRefined.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        /*Simple Block Items*/
        blockItem(TRBlockRegistry.ARS_EGG.getId());
        blockItem(TRBlockRegistry.ARS_LEAVES.getId());
        blockItem(TRBlockRegistry.ARS_LEAVES_SLAB.getId());
        blockItem(TRBlockRegistry.LANDING_PAD.getId());
        blockItem(TRBlockRegistry.FOOLS_STONE.getId());
        blockItem(TRBlockRegistry.FLIGHT_DETECTOR.getId());

        blockItem(TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.getId());
        blockItem(TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.getId());
        blockItem(TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.getId());
        blockItem(TRBlockRegistry.ZEITON_ORE.getId());
        blockItem(TRBlockRegistry.ZEITON_ORE_DEEPSLATE.getId());
        blockItem(TRBlockRegistry.ZEITON_BLOCK.getId());
        //blockItem(TRBlockRegistry.ASTRAL_MAP.getId());

        ResourceLocation leavesTexture = new ResourceLocation("tardis_refined:block/ars_leaves");


        myFenceInventory(new ResourceLocation(TardisRefined.MODID, "ars_leaves_fence_inventory"), leavesTexture);

        blockItem(TRBlockRegistry.ARS_LEAVES_FENCE.getId(), new ResourceLocation(TardisRefined.MODID, "item/ars_leaves_fence_inventory"));
        blockItem(TRBlockRegistry.TERRAFORMER_BLOCK.getId());
        blockItem(TRBlockRegistry.AIR_LOCK_GENERATION_BLOCK.getId());
        blockItem(TRBlockRegistry.GRAVITY_WELL.getId());
        blockItem(TRBlockRegistry.CORRIDOR_TELEPORTER.getId());

        basicItem(TRBlockRegistry.BULK_HEAD_DOOR.getId());
        basicItem(TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.getId());
        basicItem(TRBlockRegistry.ARS_EGG.getId());
        basicItem(TRBlockRegistry.ROOT_SHELL_BLOCK.getId());
        basicItem(TRBlockRegistry.ROOT_SHELL_DOOR.getId());
        basicItem(TRBlockRegistry.GLOBAL_DOOR_BLOCK.getId());
        basicItem(TRBlockRegistry.GLOBAL_SHELL_BLOCK.getId());
        basicItem(TRItemRegistry.ZEITON_INGOT.getId());
        basicItem(TRItemRegistry.RAW_ZEITON.getId());
        basicItem(TRItemRegistry.ZEITON_NUGGET.getId());

        // basicItem(TRItemRegistry.MALLET.getId());

        basicItem(TRBlockRegistry.ZEITON_LANTERN.getId());

    }


    public ItemModelBuilder myFenceInventory(ResourceLocation item, ResourceLocation texture) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(BLOCK_FOLDER + "/fence_inventory")).texture("texture", texture);
    }

    public ItemModelBuilder basicItem(ResourceLocation item, ResourceLocation texture) {
        return this.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", texture);
    }

    public ItemModelBuilder blockItem(ResourceLocation item) {
        return getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(new ResourceLocation(item.getNamespace(), "block/" + item.getPath())));
    }

    public ItemModelBuilder blockItem(ResourceLocation item, ResourceLocation modelLocation) {
        return getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(modelLocation));
    }
}
