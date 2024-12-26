package whocraft.tardis_refined.client.model.blockentity.shell.internal.door;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import whocraft.tardis_refined.client.model.blockentity.door.interior.ShellDoorModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class ShulkerDoorModel extends ShellDoorModel {

    private final ModelPart base;
    private final ModelPart lid;
    private final ModelPart head;
    private final ModelPart root;

    public ShulkerDoorModel(ModelPart modelPart) {
        this.root = modelPart;
        this.lid = modelPart.getChild("lid");
        this.base = modelPart.getChild("base");
        this.head = modelPart.getChild("head");
    }


    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.visible = false;
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {
        if (!open) {
            this.lid.setPos(0.0F, 16.0F + 8.0F, 0.0F);
            this.lid.yRot = 0.0F;
        } else {
            this.lid.setPos(0.0F, 16.0F, 0.0F);
            this.lid.yRot = 0.0F;
        }
    }


}
