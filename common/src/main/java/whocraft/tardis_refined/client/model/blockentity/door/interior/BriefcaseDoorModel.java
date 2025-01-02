package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class BriefcaseDoorModel extends ShellDoorModel {

    private final ModelPart root;
    private final ModelPart door_open;
    private final ModelPart door_closed;
    private final ModelPart bb_main;

    private boolean isDoorOpen;

    public BriefcaseDoorModel(ModelPart root) {
        this.root = root;
        this.door_open = root.getChild("door_open");
        this.door_closed = root.getChild("door_closed");
        this.bb_main = root.getChild("bb_main");
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.isDoorOpen = open;
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (isDoorOpen) {
            door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        } else {
            door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }

        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

}