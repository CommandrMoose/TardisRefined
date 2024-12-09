package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class FactoryShellModel extends ShellModel {

    private final ModelPart realRoot;
    private final ModelPart root;
    private final ModelPart leftDoor;
    private final ModelPart rightDoor;

    public FactoryShellModel(ModelPart root) {
        super(root);
        this.realRoot = root;
        this.root = root.getChild("model_outline");
        this.leftDoor = this.root.getChild("left_door");
        this.rightDoor = this.root.getChild("right_door");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition model_outline = partdefinition.addOrReplaceChild("model_outline", CubeListBuilder.create().texOffs(0, 28).addBox(-10.0F, -3.0F, -8.0F, 20.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -44.0F, -8.0F, 20.0F, 7.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(81, 46).addBox(-5.0F, -37.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(61, 41).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(79, 84).addBox(-8.0F, -37.0F, -6.0F, 1.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-9.0F, -37.0F, 7.0F, 18.0F, 34.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(70, 84).addBox(7.0F, -37.0F, -6.0F, 1.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(61, 32).addBox(-7.0F, -37.0F, -6.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 37).addBox(-7.0F, -5.0F, -6.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(45, 52).mirror().addBox(8.0F, -37.0F, -3.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(45, 52).addBox(-10.0F, -37.0F, -3.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -2.0F));

        PartDefinition left_door = model_outline.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(81, 0).addBox(0.0F, -15.0F, -0.5F, 7.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -20.0F, -5.6F));

        PartDefinition right_door = model_outline.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(70, 52).addBox(-7.0F, -15.0F, -0.5F, 7.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -20.0F, -5.6F));
        addMaterializationPart(partdefinition);
        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        realRoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.realRoot;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {
    }

    @Override
    public void setDoorPosition(boolean open) {
        if (open) {
            this.leftDoor.yRot = 250f;
            this.rightDoor.yRot = -250f;
        } else {
            this.leftDoor.yRot = 0;
            this.rightDoor.yRot = 0;
        }
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}