package whocraft.tardis_refined.client.model.blockentity.shell.rootplant;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class RootPlantStateFiveModel extends HierarchicalModel {

    private final ModelPart stage5;

    public RootPlantStateFiveModel(ModelPart root) {
        this.stage5 = root.getChild("stage5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stage5 = partdefinition.addOrReplaceChild("stage5", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, 0.75F, -11.0F, 22.0F, 1.0F, 22.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone80 = stage5.addOrReplaceChild("bone80", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone86 = bone80.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(29, 24).addBox(-5.5F, -34.0F, 0.0F, 11.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.6F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone87 = bone81.addOrReplaceChild("bone87", CubeListBuilder.create().texOffs(29, 24).addBox(-5.5F, -34.0F, 0.0F, 11.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.6F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone83 = bone82.addOrReplaceChild("bone83", CubeListBuilder.create().texOffs(29, 24).addBox(-5.5F, -34.0F, 0.0F, 11.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.6F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone84 = bone82.addOrReplaceChild("bone84", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone85 = bone84.addOrReplaceChild("bone85", CubeListBuilder.create().texOffs(29, 24).addBox(-5.5F, -34.0F, 0.0F, 11.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.6F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone88 = bone84.addOrReplaceChild("bone88", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(29, 24).addBox(-5.5F, -34.0F, 0.0F, 11.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.6F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone90 = bone88.addOrReplaceChild("bone90", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91 = bone90.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(29, 24).addBox(-5.5F, -34.0F, 0.0F, 11.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.6F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone92 = stage5.addOrReplaceChild("bone92", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -32.0F, -6.1F, 7.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -32.0F, -6.1F, 7.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -32.0F, -6.1F, 7.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -32.0F, -6.1F, 7.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -32.0F, -6.1F, 7.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = bone96.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(0, 24).addBox(-3.5F, -32.0F, -6.1F, 7.0F, 32.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = stage5.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(54, 24).addBox(-3.5F, -4.0F, -6.1F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

        PartDefinition bone109 = bone108.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(54, 24).addBox(-3.5F, -4.0F, -6.1F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone110 = bone109.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(54, 24).addBox(-3.5F, -4.0F, -6.1F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(54, 24).addBox(-3.5F, -4.0F, -6.1F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(54, 24).addBox(-3.5F, -4.0F, -6.1F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone113 = bone112.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(54, 24).addBox(-3.5F, -4.0F, -6.1F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone98 = stage5.addOrReplaceChild("bone98", CubeListBuilder.create(), PartPose.offset(60.0F, 0.0F, 0.0F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(46, 52).addBox(-66.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 52).addBox(-66.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone100 = bone98.addOrReplaceChild("bone100", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(46, 52).addBox(-6.0F, -12.9864F, 50.5778F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 52).addBox(-6.0F, -12.9864F, 50.5778F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone102 = bone100.addOrReplaceChild("bone102", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone103 = bone102.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(46, 52).addBox(54.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 52).addBox(54.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone104 = bone102.addOrReplaceChild("bone104", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(46, 52).addBox(-6.0F, 12.9864F, -66.5778F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(46, 52).addBox(-6.0F, 12.9864F, -66.5778F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        stage5.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return stage5;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}