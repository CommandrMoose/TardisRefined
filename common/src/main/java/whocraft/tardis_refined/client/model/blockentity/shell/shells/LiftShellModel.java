package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class LiftShellModel extends ShellModel {


    private final ModelPart root;
    private final ModelPart doorOpen, doorClosed;

    public LiftShellModel(ModelPart root) {
        super(root);
        this.root = root;
        ModelPart bone28 = root.getChild("bone28");
        this.doorOpen = bone28.getChild("door_open");
        this.doorClosed = bone28.getChild("door_closed");
    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        doorOpen.visible = open;
        doorClosed.visible = !open;
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}
