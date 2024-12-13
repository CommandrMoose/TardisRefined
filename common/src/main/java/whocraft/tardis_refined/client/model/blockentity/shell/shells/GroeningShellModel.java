package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class GroeningShellModel extends ShellModel {

    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone8;

    public GroeningShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door = root.getChild("door");
        this.bone8 = root.getChild("bone8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(19, 56).addBox(-14.725F, 9.0F, -2.25F, 15.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(82, 69).addBox(-14.725F, -17.0F, -1.25F, 15.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.225F, 7.0F, -8.75F));

        PartDefinition cube_r1 = door.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(19, 66).addBox(-8.0F, -2.0F, 0.0F, 15.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.725F, 9.0F, -2.25F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone8 = partdefinition.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(47, 108).addBox(-3.0F, -44.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-14.5F, 0.975F, -14.5F, 29.0F, 1.0F, 29.0F, new CubeDeformation(1.0F)).texOffs(88, 0).addBox(-9.0F, -39.0F, -11.25F, 18.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(81, 31).addBox(-8.0F, -36.0F, 7.0F, 16.0F, 36.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 56).addBox(-8.0F, -36.0F, -8.0F, 1.0F, 36.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 56).mirror().addBox(7.0F, -36.0F, -8.0F, 1.0F, 36.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 16).addBox(-6.0F, -32.5F, 7.75F, 12.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 31).addBox(-10.0F, -40.0F, -10.0F, 20.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone6 = bone8.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(35, 56).mirror().addBox(-8.0F, -8.0F, -8.304F, 4.0F, 8.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(17, 0).mirror().addBox(-8.0F, -8.0F, 10.0F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 31).addBox(-5.2267F, -30.0F, -2.652F, 1.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(99, 106).addBox(-6.2267F, -19.0F, -3.652F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.7733F, 0.0F, -1.348F));

        PartDefinition cube_r2 = bone6.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(35, 87).mirror().addBox(0.0F, 0.0F, -9.5F, 5.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.0F, -8.0F, 1.348F, 0.0F, 0.0F, -0.6981F));

        PartDefinition bone7 = bone8.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(35, 56).addBox(4.0F, -8.0F, -8.304F, 4.0F, 8.0F, 19.0F, new CubeDeformation(0.0F)).texOffs(0, 56).addBox(4.0F, -19.5F, 3.696F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(35, 84).addBox(4.2267F, -18.0F, -3.304F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(26, 108).addBox(4.2267F, -31.0F, -3.304F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 109).addBox(4.2267F, -12.0F, -4.304F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(17, 0).addBox(4.0F, -8.0F, 10.0F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.7733F, 0.0F, -1.348F));

        PartDefinition cube_r3 = bone7.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(35, 87).addBox(-5.0F, 0.0F, -9.5F, 5.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -8.0F, 1.348F, 0.0F, 0.0F, 0.6981F));

        PartDefinition bone2 = bone8.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(63, 56).addBox(-1.5F, -8.0F, -15.15F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(81, 106).addBox(-1.0F, -36.0F, -12.5F, 2.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(105, 14).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.5F, -8.0F, -15.15F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(81, 106).addBox(-1.0F, -36.0F, -12.5F, 2.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(61, 31).addBox(-1.5F, -12.0F, -15.15F, 3.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, -12.0F, -15.15F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(61, 31).addBox(-1.5F, -12.0F, -15.15F, 3.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(81, 106).addBox(-1.0F, -36.0F, -12.5F, 2.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r6 = bone4.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, -12.0F, -15.15F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(63, 56).addBox(-1.5F, -8.0F, -15.15F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(81, 106).addBox(-1.0F, -36.0F, -12.5F, 2.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(92, 106).addBox(-0.5F, -36.0F, -10.5F, 2.0F, 36.0F, 1.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r7 = bone5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(88, 9).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.5F, -8.0F, -15.15F, 0.9599F, 0.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? -275f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root().render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}