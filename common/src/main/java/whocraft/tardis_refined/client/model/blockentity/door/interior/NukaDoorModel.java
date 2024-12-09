package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class NukaDoorModel extends ShellDoorModel {
    private final ModelPart root;
    private final ModelPart left_door;
    private final ModelPart right_door;
    private final ModelPart bb_main;

    public NukaDoorModel(ModelPart root) {
        this.root = root;
        this.left_door = root.getChild("left_door");
        this.right_door = root.getChild("right_door");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition left_door = partdefinition.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(19, 47).addBox(0.0F, -16.0F, -1.0F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 8.0F, 7.0F));

        PartDefinition right_door = partdefinition.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(0, 47).addBox(-8.0F, -16.0F, -1.0F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, 7.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 39).addBox(-11.5F, -35.0625F, 4.5F, 23.0F, 3.0F, 4.0F, new CubeDeformation(0.125F))
                .texOffs(0, 0).addBox(-11.5F, -35.0F, 6.5F, 23.0F, 35.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 47).addBox(-1.5F, -36.5F, -1.5F, 3.0F, 35.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, 1.5F, 6.5F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(51, 47).addBox(-1.5F, -36.5F, -1.5F, 3.0F, 35.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, 1.5F, 6.5F, 0.0F, -0.7854F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        if (open) {
            this.left_door.yRot = -250f;
            this.right_door.yRot = 250f;
        } else {
            this.left_door.yRot = 0;
            this.right_door.yRot = 0;
        }
    }

}