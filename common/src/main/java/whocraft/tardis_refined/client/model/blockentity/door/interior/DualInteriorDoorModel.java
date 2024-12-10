package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortalsClient;

public class DualInteriorDoorModel extends ShellDoorModel {

    private final ModelPart root;
    public final ModelPart leftDoor;
    public final ModelPart rightDoor;
    private final ModelPart portal;
    private final ModelPart frame;
    private final float openAmount;
    private final boolean openLeft, openRight;

    public DualInteriorDoorModel(ModelPart root, float openAmount) {
       this(root, openAmount, true, true);
    }

    public DualInteriorDoorModel(ModelPart root, float openAmount, boolean openLeft, boolean openRight) {
        this.root = root;
        this.leftDoor = JsonToAnimationDefinition.findPart(this, "left_door");
        this.frame = JsonToAnimationDefinition.findPart(this, "frame");
        this.rightDoor = JsonToAnimationDefinition.findPart(this, "right_door");
        this.portal = JsonToAnimationDefinition.findPart(this, "portal");
        this.openAmount = openAmount;
        this.openLeft = openLeft;
        this.openRight = openRight;
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.portal.visible = false;
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        setDoorPosition(open);
        this.root().getAllParts().forEach(modelPart -> {
            modelPart.visible = true;
        });
        this.portal.visible = false;
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (ModCompatChecker.immersivePortals()) {
            if (ImmersivePortalsClient.shouldStopRenderingInPortal()) {
                return;
            }
        }

        this.root().getAllParts().forEach(ModelPart::resetPose);
        setDoorPosition(open);
        this.root().getAllParts().forEach(modelPart -> modelPart.visible = false);
        this.portal.visible = true;
        portal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
            this.leftDoor.yRot = openLeft ? -openAmount : 0;
            this.rightDoor.yRot = openRight ? openAmount : 0;
        } else {
            this.leftDoor.yRot = 0;
            this.rightDoor.yRot = 0;
        }
    }

}