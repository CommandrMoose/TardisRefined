package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

import java.util.Calendar;

public class BigBenShellModel extends ShellModel {
    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart sides;
    private final ModelPart bone9;
    private final ModelPart bone24;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone28;
    private final ModelPart bone15;
    private final ModelPart bone19;
    private final ModelPart bone27;
    private final ModelPart N_big_hand;
    private final ModelPart N_small_hand;
    private final ModelPart S_big_hand;
    private final ModelPart S_small_hand;
    private final ModelPart W_small_hand;
    private final ModelPart W_big_hand;
    private final ModelPart E_big_hand;
    private final ModelPart E_small_hand;
    private final ModelPart bb_main;

    public BigBenShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door = root.getChild("door");
        this.sides = root.getChild("sides");
        this.bone9 = root.getChild("bone9");
        this.bone24 = root.getChild("bone24");
        this.bone4 = root.getChild("bone4");
        this.bone5 = root.getChild("bone5");
        this.bone28 = root.getChild("bone28");
        this.bone15 = root.getChild("bone15");
        this.bone19 = root.getChild("bone19");
        this.bone27 = root.getChild("bone27");
        this.N_big_hand = root.getChild("N_big_hand");
        this.N_small_hand = root.getChild("N_small_hand");
        this.S_big_hand = root.getChild("S_big_hand");
        this.S_small_hand = root.getChild("S_small_hand");
        this.W_small_hand = root.getChild("W_small_hand");
        this.W_big_hand = root.getChild("W_big_hand");
        this.E_big_hand = root.getChild("E_big_hand");
        this.E_small_hand = root.getChild("E_small_hand");
        this.bb_main = root.getChild("bb_main");
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? -275f : 0;

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void handleSpecialAnimation(GlobalShellBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        double hourHandDegree = (hour + minute / 60.0) / 12 * 360;
        double minuteHandDegree = minute / 60.0 * 360;

        //North
        N_big_hand.zRot = (float) Math.toRadians(minuteHandDegree);
        N_small_hand.zRot = (float) Math.toRadians(hourHandDegree);

        //East
        E_big_hand.xRot = (float) Math.toRadians(minuteHandDegree);
        E_small_hand.xRot = (float) Math.toRadians(hourHandDegree);

        //West
        W_big_hand.xRot = (float) Math.toRadians(-minuteHandDegree);
        W_small_hand.xRot = (float) Math.toRadians(-hourHandDegree);

        //South
        S_big_hand.zRot = (float) Math.toRadians(-minuteHandDegree);
        S_small_hand.zRot = (float) Math.toRadians(-hourHandDegree);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        double hourHandDegree = (hour + minute / 60.0) / 12 * 360;
        double minuteHandDegree = minute / 60.0 * 360;

        //North
        N_big_hand.zRot = (float) Math.toRadians(minuteHandDegree);
        N_small_hand.zRot = (float) Math.toRadians(hourHandDegree);

        //East
        E_big_hand.xRot = (float) Math.toRadians(minuteHandDegree);
        E_small_hand.xRot = (float) Math.toRadians(hourHandDegree);

        //West
        W_big_hand.xRot = (float) Math.toRadians(-minuteHandDegree);
        W_small_hand.xRot = (float) Math.toRadians(-hourHandDegree);

        //South
        S_big_hand.zRot = (float) Math.toRadians(-minuteHandDegree);
        S_small_hand.zRot = (float) Math.toRadians(-hourHandDegree);

        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        sides.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone24.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone28.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone15.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone19.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone27.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        N_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        N_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        S_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        S_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        W_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        W_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        E_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        E_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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