package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.frame.tardis.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;


public class MysticShellModel extends ShellModel {

    public static final AnimationDefinition IDLE = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/shell/myst/idle.json"));

    private final ModelPart right_door;
    private final ModelPart left_door;
    private final ModelPart bone3;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone10;
    private final ModelPart bone11;
    private final ModelPart bone;
    private final ModelPart side_animations;
    private final ModelPart bone56;
    private final ModelPart bone58;
    private final ModelPart gold_animations;
    private final ModelPart bb_main;
    private final ModelPart root;

    public MysticShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.right_door = root.getChild("right_door");
        this.left_door = root.getChild("left_door");
        this.bone3 = root.getChild("bone3");
        this.bone6 = root.getChild("bone6");
        this.bone7 = root.getChild("bone7");
        this.bone10 = root.getChild("bone10");
        this.bone11 = root.getChild("bone11");
        this.bone = root.getChild("bone");
        this.side_animations = root.getChild("side_animations");
        this.bone56 = root.getChild("bone56");
        this.bone58 = root.getChild("bone58");
        this.gold_animations = root.getChild("gold_animations");
        this.bb_main = root.getChild("bb_main");
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone11.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        side_animations.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone56.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone58.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        gold_animations.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        this.animate(entity.liveliness, IDLE, Minecraft.getInstance().player.tickCount, reactions.isFlying() ? 5 : 1);
    }
}