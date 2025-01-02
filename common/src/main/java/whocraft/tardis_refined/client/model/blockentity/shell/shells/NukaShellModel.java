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
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void handleSpecialAnimation(GlobalShellBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {
        TardisClientData reactions = TardisClientData.getInstance(entity.getTardisId());
        this.animate(entity.liveliness, NUKAANIM, Minecraft.getInstance().player.tickCount, reactions.isFlying() ? 5 : 1);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        sign.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        black.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wheel_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wheel_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wheel_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wheel_4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}