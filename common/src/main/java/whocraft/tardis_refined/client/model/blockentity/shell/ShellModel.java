package whocraft.tardis_refined.client.model.blockentity.shell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.patterns.ShellPattern;

public abstract class ShellModel extends HierarchicalModel {

    public static final AnimationDefinition MODEL_TAKEOFF = AnimationDefinition.Builder.withLength(12f)
            .addAnimation("fade_value",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 9.5f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 9.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.125f, KeyframeAnimations.posVec(0f, 5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 10f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 3f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 6f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5.791677f, KeyframeAnimations.posVec(0f, 3f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.916767f, KeyframeAnimations.posVec(0f, 5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 2f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.167666f, KeyframeAnimations.posVec(0f, 4f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
    ModelPart fade_value;
    float initAlpha = 0;
    float ANIMATION_SPEED = 1.1f;
    boolean ignoreAnmationAlpha = false;
    AnimationDefinition MODEL_LAND = AnimationDefinition.Builder.withLength(11f)
            .addAnimation("fade_value",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 1f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 2f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 4f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7f, KeyframeAnimations.posVec(0f, 6f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 2f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.5F, KeyframeAnimations.posVec(0f, 10f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    private float currentAlpha = 0;
    public ShellModel(ModelPart root) {
        this.fade_value = root.getChild("fade_value");
        this.initAlpha = this.fade_value.y;
    }

    public static void addMaterializationPart(PartDefinition partDefinition) {
        partDefinition.addOrReplaceChild("fade_value", CubeListBuilder.create().texOffs(128, 128), PartPose.offset(-24.0F, 24.0F, 0.0F));
    }

    public abstract void setDoorPosition(boolean open);

    public abstract void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha);

    public ResourceLocation getShellTexture(ShellPattern pattern, boolean isEmmissive) {
        return texture(pattern, isEmmissive);
    }

    public ResourceLocation getShellTexture(GlobalShellBlockEntity shellBlockEntitys, boolean isEmmissive) {
        ShellPattern pattern = shellBlockEntitys.pattern();
        return texture(pattern, isEmmissive);
    }

    private ResourceLocation texture(ShellPattern pattern, boolean isEmmissive) {
        return isEmmissive ? pattern.shellTexture().emissiveTexture() : pattern.shellTexture().texture();
    }

    public boolean isIgnoreAnmationAlpha() {
        return ignoreAnmationAlpha;
    }

    public void setIgnoreAnmationAlpha(boolean ignoreAnmationAlpha) {
        this.ignoreAnmationAlpha = ignoreAnmationAlpha;
    }

    public float initAlpha() {
        return initAlpha;
    }

    public ModelPart fadeValue() {
        return fade_value;
    }

    public float getCurrentAlpha() {
        return currentAlpha;
    }

    public void handleAllAnimations(GlobalShellBlockEntity entity, ModelPart root, boolean isBaseModel, boolean isDoorOpen, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {
        if (entity.getTardisId() == null) return;
        entity.liveliness.start(12);

        this.root().getAllParts().forEach(ModelPart::resetPose);
        TardisClientData reactions = TardisClientData.getInstance(entity.getTardisId());

        setDoorPosition(isDoorOpen);

        if (reactions.isLanding()) {
            this.animate(reactions.LANDING_ANIMATION, MODEL_LAND, reactions.landingTime * ANIMATION_SPEED);
        }

        if (reactions.isTakingOff()) {
            this.animate(reactions.ROTOR_ANIMATION, MODEL_TAKEOFF, reactions.takeOffTime * ANIMATION_SPEED);
        }

        currentAlpha = (reactions.isFlying()) ? (this.initAlpha() - this.fadeValue().y) * 0.1f : baseAlpha;

        handleSpecialAnimation(entity, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, baseAlpha);

        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, reactions.isFlying() && !ignoreAnmationAlpha ? this.getCurrentAlpha() : baseAlpha);
    }

    public void handleSpecialAnimation(GlobalShellBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {

    }
}
