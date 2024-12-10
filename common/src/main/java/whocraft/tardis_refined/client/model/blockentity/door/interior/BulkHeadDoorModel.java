package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.common.block.door.BulkHeadDoorBlock;

public class BulkHeadDoorModel extends HierarchicalModel {

    private final ModelPart root;
    private final ModelPart right;
    private final ModelPart left;


    public BulkHeadDoorModel(ModelPart root) {
        this.root = root.getChild("root");
        this.right = this.root.getChild("right_door");
        this.left = this.root.getChild("left_door");
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    public void setDoorPosition(BlockState state) {
        if (state.getValue(BulkHeadDoorBlock.OPEN)) {
            right.x = 20f;
            left.x = -20f;
        } else {
            right.x = 0f;
            left.x = 0f;
        }
    }
}