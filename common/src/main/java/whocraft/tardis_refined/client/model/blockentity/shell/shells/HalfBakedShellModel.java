package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class HalfBakedShellModel extends ShellModel {

    private final ModelPart root;
    private final ModelPart leftDoor;
    private final ModelPart rightDoor;

    public HalfBakedShellModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.leftDoor = this.root.getChild("left_door");
        this.rightDoor = this.root.getChild("right_door");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 28).addBox(-10.0F, -3.0F, -8.0F, 20.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 103).addBox(-9.5F, -2.5F, -7.5F, 19.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -44.0F, -8.0F, 20.0F, 7.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 104).addBox(-9.0F, -43.275F, -7.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.25F))
                .texOffs(81, 46).addBox(-5.0F, -37.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(61, 41).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(79, 84).addBox(-8.0F, -37.0F, -6.0F, 1.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-9.0F, -37.0F, 7.0F, 18.0F, 34.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 103).addBox(-8.5F, -36.5F, 9.25F, 17.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 84).addBox(7.0F, -37.0F, -6.0F, 1.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(61, 32).addBox(-7.0F, -37.0F, -6.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 37).addBox(-7.0F, -5.0F, -6.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(45, 52).mirror().addBox(8.0F, -37.0F, -3.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(45, 52).addBox(-10.0F, -37.0F, -3.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(12, 103).addBox(-9.5F, -31.0F, -2.5F, 1.0F, 22.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(12, 103).addBox(8.5F, -31.0F, -2.5F, 1.0F, 22.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -2.0F));

        PartDefinition root_r1 = root.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(123, 134).addBox(-10.0F, 0.0F, -7.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -40.75F, -7.25F, 1.2252F, 0.0603F, 0.1163F));

        PartDefinition root_r2 = root.addOrReplaceChild("root_r2", CubeListBuilder.create().texOffs(0, 141).mirror().addBox(-1.0F, -20.0F, -12.5F, 1.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.5F, 0.0F, 4.5F, 0.0F, 0.0873F, 0.0873F));

        PartDefinition root_r3 = root.addOrReplaceChild("root_r3", CubeListBuilder.create().texOffs(0, 141).addBox(0.0F, -20.0F, -12.5F, 1.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 0.0F, 4.5F, 0.0F, -0.0873F, -0.0873F));

        PartDefinition root_r4 = root.addOrReplaceChild("root_r4", CubeListBuilder.create().texOffs(47, 148).addBox(-10.0F, -25.0F, -1.0F, 20.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 12.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition root_r5 = root.addOrReplaceChild("root_r5", CubeListBuilder.create().texOffs(67, 161).addBox(-10.0F, -11.0F, 0.0F, 20.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -8.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition bone14 = root.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(10.5F, 0.5F, -4.5F, 0.0F, 1.8762F, 0.0F));

        PartDefinition bone14_r1 = bone14.addOrReplaceChild("bone14_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, 0.9848F, -0.1737F, 13.0F, 1.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(9.25F, -37.5F, 8.5F, 0.829F, -0.1309F, 1.5708F));

        PartDefinition bone2_r1 = bone2.addOrReplaceChild("bone2_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, -0.5373F, 0.8434F, 13.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone13 = root.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, 0.5F, -7.5F, 0.0F, -2.9234F, 0.0F));

        PartDefinition bone13_r1 = bone13.addOrReplaceChild("bone13_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, 0.9848F, -0.1737F, 13.0F, 1.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone12 = root.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(6.5F, 0.5F, 13.5F, 0.0F, 0.3927F, 0.0F));

        PartDefinition bone12_r1 = bone12.addOrReplaceChild("bone12_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, 0.9848F, -0.1737F, 13.0F, 1.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone11 = root.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.5F, 0.5F, 13.5F, 0.0F, -0.3054F, 0.0F));

        PartDefinition bone11_r1 = bone11.addOrReplaceChild("bone11_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, 0.9848F, -0.1737F, 13.0F, 1.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone10 = root.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.25F, 0.5F, -6.75F, 0.0F, -2.1817F, 0.0F));

        PartDefinition bone10_r1 = bone10.addOrReplaceChild("bone10_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, 0.9848F, -0.1737F, 13.0F, 1.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone9 = root.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.75F, -41.5F, 4.25F, 1.9255F, -0.8487F, -2.3362F));

        PartDefinition bone9_r1 = bone9.addOrReplaceChild("bone9_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-6.5F, 0.0F, 0.0F, 13.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone8 = root.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -45.5F, 9.25F, 0.6545F, 0.0F, 0.0F));

        PartDefinition bone8_r1 = bone8.addOrReplaceChild("bone8_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-7.0F, 0.0F, 0.0F, 13.0F, 1.0F, 7.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(6.5F, -45.0F, 9.25F, 0.6545F, 0.0F, 0.2618F));

        PartDefinition bone3_r1 = bone3.addOrReplaceChild("bone3_r1", CubeListBuilder.create().texOffs(66, 180).addBox(-7.0F, 0.0F, 0.0F, 13.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone4 = root.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.5F, -45.0F, 9.25F, 0.6545F, 0.0F, -0.2618F));

        PartDefinition bone4_r1 = bone4.addOrReplaceChild("bone4_r1", CubeListBuilder.create().texOffs(66, 180).mirror().addBox(-6.0F, 0.0F, 0.0F, 13.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, -3.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition left_door = root.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(81, 0).addBox(-0.1F, -15.0F, -0.5F, 7.0F, 30.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 51).mirror().addBox(0.4F, -15.0F, 0.0F, 6.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.9F, -20.0F, -5.6F));

        PartDefinition right_door = root.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(70, 52).addBox(-6.9F, -15.0F, -0.5F, 7.0F, 30.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 51).addBox(-6.4F, -15.0F, 0.0F, 6.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.9F, -20.0F, -5.6F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.leftDoor.yRot = open ? 250f : 0;
        this.rightDoor.yRot = open ? -250f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}