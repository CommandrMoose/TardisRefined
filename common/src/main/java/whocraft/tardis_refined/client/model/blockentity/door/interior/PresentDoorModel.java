package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;

public class PresentDoorModel extends ShellDoorModel {

    private final ModelPart root;
    private final ModelPart bone2;
    private final ModelPart door;
    private final ModelPart bb_main;

    public PresentDoorModel(ModelPart root) {
        this.root = root;
        this.bone2 = root.getChild("bone2");
        this.door = root.getChild("door");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 12.0F));

        PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(39, 0).mirror().addBox(-8.0F, 0.0F, -4.025F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(39, 0).addBox(-2.0F, 0.0F, -4.025F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(0, 37).addBox(-8.0F, -35.0F, 0.0F, 16.0F, 35.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 8.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(35, 45).addBox(-9.0F, -35.0F, 7.0F, 1.0F, 35.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 35).addBox(-10.0F, -39.0F, 6.0F, 20.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.0F, -35.0F, 8.025F, 18.0F, 35.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(35, 45).mirror().addBox(8.0F, -35.0F, 7.0F, 1.0F, 35.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(42, 45).addBox(-9.0F, -36.0F, 7.0F, 18.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
    public void setDoorPosition(boolean open) {
        this.door.xRot = (open) ? (ModCompatChecker.immersivePortals() ? -1.5f : 1.5F) : 0;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}