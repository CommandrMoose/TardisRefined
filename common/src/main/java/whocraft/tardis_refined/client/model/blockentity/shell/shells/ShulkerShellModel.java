package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class ShulkerShellModel extends ShellModel {

    private final ModelPart base;
    private final ModelPart lid;
    private final ModelPart head;
    private final ModelPart root;

    public ShulkerShellModel(ModelPart modelPart) {
        super(modelPart);
        this.root = modelPart;
        this.lid = modelPart.getChild("lid");
        this.base = modelPart.getChild("base");
        this.head = modelPart.getChild("head");
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

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.visible = false;
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
