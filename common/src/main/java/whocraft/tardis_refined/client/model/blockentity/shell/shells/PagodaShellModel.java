package whocraft.tardis_refined.client.model.blockentity.shell.shells;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PagodaShellModel extends ShellModel {
    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone4;
    private final ModelPart bone;
    private final ModelPart bone17;
    private final ModelPart bone21;
    private final ModelPart bone13;
    private final ModelPart bone9;
    private final ModelPart bb_main;

    public PagodaShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door = root.getChild("door");
        this.bone4 = root.getChild("bone4");
        this.bone = root.getChild("bone");
        this.bone17 = root.getChild("bone17");
        this.bone21 = root.getChild("bone21");
        this.bone13 = root.getChild("bone13");
        this.bone9 = root.getChild("bone9");
        this.bb_main = root.getChild("bb_main");
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? -275f : 0;

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root, isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone17.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone21.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone13.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}