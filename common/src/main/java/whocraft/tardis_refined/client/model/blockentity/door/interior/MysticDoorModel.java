package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class MysticDoorModel extends ShellDoorModel {

    private final ModelPart right_door;
    private final ModelPart left_door;
    private final ModelPart root_door;
    private final ModelPart root;

    public MysticDoorModel(ModelPart root) {
        this.root = root;
        this.right_door = root.getChild("right_door");
        this.left_door = root.getChild("left_door");
        this.root_door = root.getChild("root_door");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition right_door = partdefinition.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -16.0F, 0.0F, 7.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(39, 27).addBox(-6.975F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 7.0F, 6.0F));

        PartDefinition left_door = partdefinition.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(0, 38).mirror().addBox(0.0F, -16.0F, 0.0F, 7.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(39, 27).mirror().addBox(3.975F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, 7.0F, 6.0F));

        PartDefinition root_door = partdefinition.addOrReplaceChild("root_door", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 15.0F));

        PartDefinition bone = root_door.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(39, 19).addBox(6.0F, -23.0F, -10.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(37, 36).addBox(7.0F, -33.0F, -9.0F, 2.0F, 28.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 36).mirror().addBox(-9.0F, -33.0F, -9.0F, 2.0F, 28.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(39, 19).mirror().addBox(-10.0F, -23.0F, -10.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone11 = bone.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offset(-15.0F, -14.0F, 0.0F));

        PartDefinition bone10 = bone.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(39, 10).addBox(20.0F, -24.0F, -10.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 38).addBox(20.0F, 9.0F, -10.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 38).mirror().addBox(5.0F, 9.0F, -10.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(39, 10).mirror().addBox(5.0F, -24.0F, -10.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-15.0F, -14.0F, 0.0F));

        PartDefinition bone6 = bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(39, 6).addBox(-5.0F, -1.0F, -9.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.0F, -36.0F, -7.9F, 18.0F, 36.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(39, 0).addBox(-5.0F, -36.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone3 = bone.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        root_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        root_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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