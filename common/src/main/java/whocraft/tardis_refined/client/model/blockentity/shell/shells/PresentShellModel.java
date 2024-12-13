package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PresentShellModel extends ShellModel {

    private final ModelPart root;
    private final ModelPart bone;
    private final ModelPart door;

    public PresentShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.bone = root.getChild("bone");
        this.door = bone.getChild("door_rotate_neg_92dot5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(74, 27).addBox(23.0F, -36.0F, -5.025F, 16.0F, 35.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 27).addBox(22.0F, -1.0F, -9.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(0.025F))
                .texOffs(0, 0).addBox(21.0F, -39.0F, -10.0F, 20.0F, 6.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(1, 102).addBox(22.0F, -36.0F, -9.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(0.025F))
                .texOffs(0, 47).mirror().addBox(22.0F, -36.0F, -9.0F, 1.0F, 36.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 47).addBox(39.0F, -36.0F, -9.0F, 1.0F, 36.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(39, 47).addBox(23.0F, -36.0F, 8.0F, 16.0F, 36.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-31.0F, 24.0F, 0.0F));

        PartDefinition door_rotate_neg_92dot5 = bone.addOrReplaceChild("door_rotate_neg_92dot5", CubeListBuilder.create().texOffs(74, 66).addBox(-8.0F, -29.0F, 0.0F, 16.0F, 29.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(39, 86).addBox(-8.0F, -35.0F, 0.0F, 16.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(31.0F, 0.0F, -9.0F));

        PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(31.0F, -39.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition cube_r1 = bone5.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(61, 0).mirror().addBox(-8.0F, 0.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r2 = bone5.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(61, 0).addBox(-2.0F, 0.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition bone4 = bone.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(30.0F, -41.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r3 = bone4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(81, 12).mirror().addBox(-8.0F, 0.0F, -4.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5672F));

        PartDefinition cube_r4 = bone4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(81, 12).addBox(-2.0F, 0.0F, -4.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5672F));
        addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.xRot = (open) ? 1.5f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}