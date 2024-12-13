package whocraft.tardis_refined.client.model.blockentity.shell.internal.door;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class RootShellDoorModel extends HierarchicalModel {

    private final ModelPart root;
    private final ModelPart stage7;

    public RootShellDoorModel(ModelPart root) {

        this.root = root;
        this.stage7 = root.getChild("stage7");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stage7 = partdefinition.addOrReplaceChild("stage7", CubeListBuilder.create().texOffs(39, 48).addBox(105.0F, -38.0F, 6.55F, 18.0F, 38.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(96.0F, -46.0F, 6.825F, 36.0F, 46.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(105.0F, -38.0F, 6.8F, 18.0F, 38.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-114.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        stage7.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);

    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}