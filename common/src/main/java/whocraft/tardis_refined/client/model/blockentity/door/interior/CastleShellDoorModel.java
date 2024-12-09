package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class CastleShellDoorModel extends ShellDoorModel {

    private final ModelPart r_door;
    private final ModelPart l_door;
    private final ModelPart bone8;

    public CastleShellDoorModel(ModelPart root) {
        this.r_door = root.getChild("r_door");
        this.l_door = root.getChild("l_door");
        this.bone8 = root.getChild("bone8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition r_door = partdefinition.addOrReplaceChild("r_door", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-0.25F, -16.0F, -0.5F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 8.0F, 4.5F));

        PartDefinition l_door = partdefinition.addOrReplaceChild("l_door", CubeListBuilder.create().texOffs(0, 40).addBox(-7.75F, -16.0F, -0.5F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 8.0F, 4.5F));

        PartDefinition bone8 = partdefinition.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(37, 0).addBox(-10.5F, -36.0F, 7.0F, 3.0F, 36.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(37, 0).mirror().addBox(7.5F, -36.0F, 7.0F, 3.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(-7.5F, -36.0F, 7.0F, 15.0F, 36.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(19, 40).addBox(-2.5F, -36.0F, 5.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(45, 45).addBox(2.5F, -35.0F, 5.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 40).addBox(6.5F, -36.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 40).mirror().addBox(-10.5F, -36.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(45, 45).mirror().addBox(-6.5F, -35.0F, 5.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -2.0F));

        PartDefinition bone = bone8.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(34, 40).addBox(7.5F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 47).addBox(7.5F, -8.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 40).addBox(7.5F, -12.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 47).addBox(7.5F, -16.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 47).addBox(7.5F, -20.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 47).addBox(7.5F, -24.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 47).addBox(7.5F, -28.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 47).addBox(7.5F, -32.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone8.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(34, 40).mirror().addBox(-11.5F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(19, 47).mirror().addBox(-10.5F, -8.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(34, 40).mirror().addBox(-11.5F, -12.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(19, 47).mirror().addBox(-10.5F, -16.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(30, 47).mirror().addBox(-9.5F, -20.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(19, 47).mirror().addBox(-10.5F, -24.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(30, 47).mirror().addBox(-9.5F, -28.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(19, 47).mirror().addBox(-10.5F, -32.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        r_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        l_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        l_door.yRot = open ? (float) Math.toRadians(90) : (float) Math.toRadians(0);
        r_door.yRot = open ? (float) Math.toRadians(-90) : (float) Math.toRadians(0);
    }
}
