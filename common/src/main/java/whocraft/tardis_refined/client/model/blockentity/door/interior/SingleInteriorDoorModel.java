package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortalsClient;

public class SingleInteriorDoorModel extends ShellDoorModel {

    private final ModelPart root;
    public final ModelPart door;
    private final ModelPart portal;
    private final ModelPart frame;
    private final float openAmount;

    public SingleInteriorDoorModel(ModelPart root, float openAmount) {
        this.root = root;
        this.frame = JsonToAnimationDefinition.findPart(this, "frame");
        this.door = JsonToAnimationDefinition.findPart(this, "door");
        this.portal = JsonToAnimationDefinition.findPart(this, "portal");
        this.openAmount = openAmount;
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.portal.visible = false;
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        setDoorPosition(open);
        this.root().getAllParts().forEach(modelPart -> {
            modelPart.visible = true;
        });
        this.portal.visible = false;
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {

        if (ModCompatChecker.immersivePortals()) {
            if (ImmersivePortalsClient.shouldStopRenderingInPortal()) {
                return;
            }
        }

        this.root().getAllParts().forEach(ModelPart::resetPose);
        setDoorPosition(open);
        this.root().getAllParts().forEach(modelPart -> modelPart.visible = false);
        this.portal.visible = true;
        portal.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        if (open) {
            this.door.yRot = -openAmount;
        } else {
            this.door.yRot = 0;
        }
    }

}