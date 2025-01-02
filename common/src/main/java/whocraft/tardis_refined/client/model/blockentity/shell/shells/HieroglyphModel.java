package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class HieroglyphModel extends ShellModel {


    private final ModelPart door_closed;
    private final ModelPart door_open;
    private final ModelPart sides;
    private final ModelPart bone6;
    private final ModelPart pillars;
    private final ModelPart bone18;
    private final ModelPart bb_main;
    private final ModelPart root;

    public HieroglyphModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door_closed = root.getChild("door_closed");
        this.door_open = root.getChild("door_open");
        this.sides = root.getChild("sides");
        this.bone6 = root.getChild("bone6");
        this.pillars = root.getChild("pillars");
        this.bone18 = root.getChild("bone18");
        this.bb_main = root.getChild("bb_main");
    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        door_open.visible = open;
        door_closed.visible = !open;
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}
