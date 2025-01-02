package whocraft.tardis_refined.client.model.blockentity.shell.shells;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PathfinderShellModel extends ShellModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart root;
    private final ModelPart bone3;
    private final ModelPart r_door;
    private final ModelPart l_door;
    private final ModelPart bb_main;

    public PathfinderShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.bone3 = root.getChild("bone3");
        this.r_door = root.getChild("r_door");
        this.l_door = root.getChild("l_door");
        this.bb_main = root.getChild("bb_main");
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
        this.l_door.yRot = (open) ? -275f : 0;
        this.r_door.yRot = (open) ? 275f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (isBaseModel) {
            poseStack.scale(1.05f, 1.05f, 1.05f);
            poseStack.translate(0, -0.07, 0);
        }
        handleAllAnimations(entity, bone3, isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

        bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, this.getCurrentAlpha());
        l_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, this.getCurrentAlpha());
        r_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, this.getCurrentAlpha());
    }
}