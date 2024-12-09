package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortalsClient;

public class PoliceBoxDoorModel extends ShellDoorModel {

    private final ModelPart left_door;
    private final ModelPart root;
    private final ModelPart portal;

    public PoliceBoxDoorModel(ModelPart root) {
        this.root = root;
        this.left_door = JsonToAnimationDefinition.findPart(this, "left_door");
        this.portal = JsonToAnimationDefinition.findPart(this, "portal");
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
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        this.left_door.yRot = (open) ? -300f : 0;
    }

}