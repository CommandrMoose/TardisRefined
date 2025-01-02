package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class CastleShellModel extends ShellModel {

    private final ModelPart r_door;
    private final ModelPart l_door;
    private final ModelPart bone38;
    private final ModelPart bone8;
    private final ModelPart bone3;
    private final ModelPart bone10;
    private final ModelPart bone16;
    private final ModelPart bone32;
    private final ModelPart blackbox;
    private final ModelPart bb_main;
    private final ModelPart root;

    public CastleShellModel(ModelPart root) {
        super(root);
        this.r_door = root.getChild("r_door");
        this.l_door = root.getChild("l_door");
        this.bone38 = root.getChild("bone38");
        this.bone8 = root.getChild("bone8");
        this.bone3 = root.getChild("bone3");
        this.bone10 = root.getChild("bone10");
        this.bone16 = root.getChild("bone16");
        this.bone32 = root.getChild("bone32");
        this.blackbox = root.getChild("blackbox");
        this.bb_main = root.getChild("bb_main");
        this.root = root;
    }

    @Override
    public void setDoorPosition(boolean open) {
        r_door.yRot = open ? (float) Math.toRadians(90) : (float) Math.toRadians(0);
        l_door.yRot = open ? (float) Math.toRadians(-90) : (float) Math.toRadians(0);
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}
