package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class NukaShellModel extends ShellModel {
    public static final AnimationDefinition NUKAANIM = AnimationDefinition.Builder.withLength(4.541677f).looping()
            .addAnimation("sign",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.541677f, KeyframeAnimations.degreeVec(0f, 360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("wheel_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.degreeVec(90f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.541677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("wheel_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.5f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.541677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("wheel_3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(90f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.0834335f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.541677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("wheel_4",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.3433333f, KeyframeAnimations.degreeVec(-90f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.0834335f, KeyframeAnimations.degreeVec(90f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.541677f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    private final ModelPart root;
    private final ModelPart sign;
    private final ModelPart right_door;
    private final ModelPart left_door;
    private final ModelPart bone2;
    private final ModelPart bone6;
    private final ModelPart black;
    private final ModelPart wheel_1;
    private final ModelPart wheel_2;
    private final ModelPart wheel_3;
    private final ModelPart wheel_4;
    private final ModelPart bb_main;

    public NukaShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.sign = root.getChild("sign");
        this.right_door = root.getChild("right_door");
        this.left_door = root.getChild("left_door");
        this.bone2 = root.getChild("bone2");
        this.bone6 = root.getChild("bone6");
        this.black = root.getChild("black");
        this.wheel_1 = root.getChild("wheel_1");
        this.wheel_2 = root.getChild("wheel_2");
        this.wheel_3 = root.getChild("wheel_3");
        this.wheel_4 = root.getChild("wheel_4");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition sign = partdefinition.addOrReplaceChild("sign", CubeListBuilder.create().texOffs(133, 110).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(110, 47).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(54, 55).addBox(-5.0F, -5.0F, -1.0F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -25.5F, 0.0F));

        PartDefinition right_door = partdefinition.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(133, 76).addBox(-8.0F, -16.0F, 0.0F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 2.0F, -10.0F));

        PartDefinition left_door = partdefinition.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(133, 42).addBox(0.0F, -16.0F, 0.0F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 2.0F, -10.0F));

        PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(127, 5).addBox(-9.0F, -6.0F, -11.0F, 18.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(127, 0).addBox(-9.0F, -6.0F, -11.0F, 18.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(73, 15).addBox(-9.0F, -6.0F, -11.0F, 18.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(73, 10).addBox(-9.0F, -6.0F, -11.0F, 18.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 55).addBox(-10.0F, -38.0F, -8.0F, 1.0F, 32.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.0F, -29.0F, -4.0F, 3.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.5F, -25.5F, 4.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(89, 47).addBox(-13.0F, -16.0F, -7.0F, 3.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition black = partdefinition.addOrReplaceChild("black", CubeListBuilder.create().texOffs(73, 0).addBox(-8.5F, -6.0F, -10.0F, 17.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 104).addBox(-8.5F, -38.0F, -2.0F, 17.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(117, 3).addBox(8.5F, -38.0F, -9.0F, 1.0F, 32.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(64, 104).addBox(-9.5F, -38.0F, -9.0F, 1.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition wheel_1 = partdefinition.addOrReplaceChild("wheel_1", CubeListBuilder.create().texOffs(13, 28).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(11.5F, -6.5F, -2.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition wheel_2 = partdefinition.addOrReplaceChild("wheel_2", CubeListBuilder.create().texOffs(13, 28).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(11.5F, -6.5F, 2.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition wheel_3 = partdefinition.addOrReplaceChild("wheel_3", CubeListBuilder.create().texOffs(36, 55).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, 0.5F, 2.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition wheel_4 = partdefinition.addOrReplaceChild("wheel_4", CubeListBuilder.create().texOffs(15, 0).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-11.5F, 1.5F, -3.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -3.0F, -12.0F, 24.0F, 3.0F, 24.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-11.5F, -41.0625F, -11.5F, 23.0F, 3.0F, 23.0F, new CubeDeformation(0.125F))
                .texOffs(70, 28).addBox(-7.5F, -44.0F, -7.5F, 15.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(0, 55).addBox(7.0F, -26.0F, -11.75F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(70, 55).addBox(9.0F, -38.0F, -8.0F, 1.0F, 32.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(35, 55).addBox(-10.0F, -38.0F, -8.0F, 1.0F, 32.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(37, 104).addBox(10.0F, -34.0F, -6.0F, 1.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(93, 92).addBox(-11.0F, -34.0F, -6.0F, 1.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-12.0F, -18.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(19, 55).addBox(-0.5F, 0.0F, -6.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -17.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(120, 70).addBox(-1.5F, -36.5F, -1.5F, 3.0F, 38.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -4.5F, 9.5F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(120, 112).addBox(-1.5F, -36.5F, -1.5F, 3.0F, 38.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -4.5F, 9.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(81, 122).addBox(-1.5F, -36.5F, -1.5F, 3.0F, 38.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -4.5F, -9.5F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(94, 125).addBox(-1.5F, -36.5F, -1.5F, 3.0F, 38.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -4.5F, -9.5F, 0.0F, -0.7854F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setDoorPosition(boolean open) {
        if (open) {
            this.left_door.yRot = 250f;
            this.right_door.yRot = -250f;
        } else {
            this.left_door.yRot = 0;
            this.right_door.yRot = 0;
        }
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());
    }

    @Override
    public void handleSpecialAnimation(GlobalShellBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {
        TardisClientData reactions = TardisClientData.getInstance(entity.getTardisId());
        this.animate(entity.liveliness, NUKAANIM, Minecraft.getInstance().player.tickCount, reactions.isFlying() ? 5 : 1);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        sign.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        black.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        wheel_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        wheel_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        wheel_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        wheel_4.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}