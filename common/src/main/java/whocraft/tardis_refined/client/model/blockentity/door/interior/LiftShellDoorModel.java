package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class LiftShellDoorModel extends ShellDoorModel {

    private final ModelPart bone28;
    private final ModelPart doorOpen, doorClosed;
    public boolean isDoorOpen = false;

    public LiftShellDoorModel(ModelPart modelPart) {
        this.bone28 = modelPart.getChild("bone28");
        this.doorOpen = bone28.getChild("door_open");
        this.doorClosed = bone28.getChild("door_closed");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone28 = partdefinition.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -39.0F, 0.25F, 20.0F, 39.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 73).addBox(-9.0F, -35.0F, -1.0F, 18.0F, 3.0F, 1.0F, new CubeDeformation(0.025F))
                .texOffs(66, 72).addBox(-9.5F, -36.0F, -1.5F, 19.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 33).addBox(-5.5F, -40.5F, -0.775F, 11.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 7.0F));

        PartDefinition door_open = bone28.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(38, 73).addBox(5.0F, -32.0F, -1.0F, 3.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, 0.0F, 0.0F));

        PartDefinition door_closed = bone28.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(42, 0).addBox(-8.0F, -32.0F, -1.0F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 40).addBox(-8.0F, -32.0F, -0.25F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone7 = door_closed.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone6 = bone7.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(4.0F, -11.0F, -0.5F));

        PartDefinition cube_r1 = bone6.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(70, 75).mirror().addBox(0.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -5.5F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition cube_r2 = bone6.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 75).mirror().addBox(0.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -5.5F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition bone5 = bone7.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-4.0F, -11.0F, -0.5F));

        PartDefinition cube_r3 = bone5.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(70, 75).addBox(-1.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.5F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition cube_r4 = bone5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(66, 75).addBox(-1.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.5F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition bone4 = bone7.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-4.0F, -11.0F, -0.5F));

        PartDefinition cube_r5 = bone4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(66, 75).mirror().addBox(0.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition cube_r6 = bone4.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(66, 75).addBox(-1.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition bone3 = bone7.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(5.0F, -11.0F, -0.5F));

        PartDefinition cube_r7 = bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(66, 75).mirror().addBox(0.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition cube_r8 = bone3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 75).addBox(-1.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition bone2 = bone7.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, -0.5F));

        PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(66, 75).mirror().addBox(0.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition cube_r10 = bone2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(66, 75).addBox(-1.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition bone = bone7.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-4.0F, -22.0F, -0.5F));

        PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(66, 75).mirror().addBox(0.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(66, 75).addBox(-1.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition bone10 = bone28.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(68, 33).addBox(8.0F, -37.0F, -1.75F, 2.0F, 37.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(46, 73).addBox(-8.5F, -33.25F, -3.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(74, 31).addBox(-7.5F, -33.225F, -2.5F, 16.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(68, 33).mirror().addBox(-10.0F, -37.0F, -1.75F, 2.0F, 37.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(58, 73).addBox(7.5F, -22.0F, -2.25F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition blackface = bone28.addOrReplaceChild("blackface", CubeListBuilder.create().texOffs(0, 40).addBox(-8.0F, -32.0F, -0.75F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition clock_hand = bone28.addOrReplaceChild("clock_hand", CubeListBuilder.create().texOffs(74, 75).addBox(-0.5F, -2.5F, 0.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -37.0F, -1.275F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone28.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        doorOpen.visible = isDoorOpen;
        doorClosed.visible = !isDoorOpen;
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        isDoorOpen = open;
    }
}
