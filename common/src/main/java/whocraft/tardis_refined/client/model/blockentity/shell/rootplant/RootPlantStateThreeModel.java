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

public class RootPlantStateThreeModel extends HierarchicalModel {

    private final ModelPart stage3;

    public RootPlantStateThreeModel(ModelPart root) {
        this.stage3 = root.getChild("stage3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stage3 = partdefinition.addOrReplaceChild("stage3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -17.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(1.0F))
                .texOffs(36, 34).addBox(-2.5F, -15.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(1.0F))
                .texOffs(23, 15).addBox(-5.0F, -21.0F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone64 = stage3.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offset(36.0F, 0.0F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create().texOffs(0, 29).addBox(-42.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-42.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone66 = bone64.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone67 = bone66.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(0, 29).addBox(-6.0F, -7.7918F, 27.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-6.0F, -7.7918F, 27.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone68 = bone66.addOrReplaceChild("bone68", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(0, 29).addBox(30.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(30.0F, 0.0F, -8.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone70 = bone68.addOrReplaceChild("bone70", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(0, 29).addBox(-6.0F, 7.7918F, -43.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-6.0F, 7.7918F, -43.1467F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone8 = stage3.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, -12.0F, 0.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone7 = bone8.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone10 = bone7.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, -12.0F, 0.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone11 = bone7.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, -12.0F, 0.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone13 = bone11.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone49 = bone13.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(33, 0).addBox(-6.0F, -12.0F, 0.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        stage3.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return stage3;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}