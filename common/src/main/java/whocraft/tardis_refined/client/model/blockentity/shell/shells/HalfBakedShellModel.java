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

    @Override
    public void setDoorPosition(boolean open) {
        this.leftDoor.yRot = open ? 250f : 0;
        this.rightDoor.yRot = open ? -250f : 0;
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