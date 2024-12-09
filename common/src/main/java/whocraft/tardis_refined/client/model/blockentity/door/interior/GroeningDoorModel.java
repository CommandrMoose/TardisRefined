package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class GroeningDoorModel extends ShellDoorModel {

    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bb_main;

    public GroeningDoorModel(ModelPart root) {
        this.root = root;
        this.door = root.getChild("door");
        this.bone = root.getChild("bone");
        this.bone2 = root.getChild("bone2");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(47, 0).addBox(0.0F, 9.0F, -2.0F, 15.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 42).addBox(0.0F, -17.0F, -1.0F, 15.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 7.0F, 5.025F));

        PartDefinition cube_r1 = door.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(47, 25).addBox(-8.0F, -2.0F, 0.0F, 15.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 9.0F, -2.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(35, 52).addBox(-1.0F, -28.0171F, -0.9687F, 2.0F, 39.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(46, 52).addBox(-1.025F, 2.9829F, -3.6187F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, 13.0171F, 5.9937F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(47, 10).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.975F, 2.9829F, -3.7187F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(35, 52).mirror().addBox(-1.0F, -28.0171F, -0.9687F, 2.0F, 39.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(46, 52).mirror().addBox(-1.975F, 2.9829F, -3.6187F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-9.5F, 13.0171F, 5.9937F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r3 = bone2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(47, 29).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(-0.975F, 2.9829F, -3.7187F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-10.5F, -39.0F, 6.0F, 21.0F, 39.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(35, 42).addBox(-9.0F, -39.0F, 3.025F, 18.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? -275f : 0;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}