package whocraft.tardis_refined.client.model.blockentity.shell.rootplant;// Made with Blockbench 4.5.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class RootPlantStateFourModel extends HierarchicalModel {

    private final ModelPart stage4;

    public RootPlantStateFourModel(ModelPart root) {
        this.stage4 = root.getChild("stage4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stage4 = partdefinition.addOrReplaceChild("stage4", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -22.5F, -4.0F, 8.0F, 22.0F, 8.0F, new CubeDeformation(0.5F))
                .texOffs(0, 0).addBox(-11.0F, -0.25F, -11.0F, 22.0F, 1.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone50 = stage4.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(33, 34).addBox(-3.5F, -26.0F, -6.1F, 7.0F, 26.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(33, 34).addBox(-3.5F, -26.0F, -6.1F, 7.0F, 26.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(33, 34).addBox(-3.5F, -26.0F, -6.1F, 7.0F, 26.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(33, 34).addBox(-3.5F, -26.0F, -6.1F, 7.0F, 26.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(33, 34).addBox(-3.5F, -26.0F, -6.1F, 7.0F, 26.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone55 = bone54.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(33, 34).addBox(-3.5F, -26.0F, -6.1F, 7.0F, 26.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone72 = stage4.addOrReplaceChild("bone72", CubeListBuilder.create(), PartPose.offset(36.0F, 0.0F, 0.0F));

        PartDefinition bone73 = bone72.addOrReplaceChild("bone73", CubeListBuilder.create().texOffs(33, 24).addBox(-42.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(33, 24).addBox(-42.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone74 = bone72.addOrReplaceChild("bone74", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(33, 24).addBox(-6.0F, -7.7918F, 27.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(33, 24).addBox(-6.0F, -7.7918F, 27.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone76 = bone74.addOrReplaceChild("bone76", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create().texOffs(33, 24).addBox(30.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(33, 24).addBox(30.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone78 = bone76.addOrReplaceChild("bone78", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone79 = bone78.addOrReplaceChild("bone79", CubeListBuilder.create().texOffs(33, 24).addBox(-6.0F, 7.7918F, -43.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(33, 24).addBox(-6.0F, 7.7918F, -43.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        stage4.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return stage4;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}