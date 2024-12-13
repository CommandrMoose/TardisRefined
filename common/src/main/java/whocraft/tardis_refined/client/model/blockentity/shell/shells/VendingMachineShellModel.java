package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class VendingMachineShellModel extends ShellModel {

    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone11;

    public VendingMachineShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door = root.getChild("door");
        this.bone11 = root.getChild("bone11");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(65, 0).mirror().addBox(5.0F, -31.0F, -2.0F, 3.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(65, 0).addBox(-8.0F, -31.0F, -2.0F, 3.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(94, 51).addBox(-8.0F, -31.0F, 0.0F, 16.0F, 33.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(65, 36).addBox(-6.0F, -11.0F, -4.0F, 12.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(33, 103).mirror().addBox(-5.5F, -28.6F, 3.7F, 16.0F, 16.0F, 1.0F, new CubeDeformation(-5.0F)).mirror(false).texOffs(33, 71).mirror().addBox(-10.75F, -28.6F, 3.7F, 16.0F, 16.0F, 1.0F, new CubeDeformation(-5.0F)).mirror(false).texOffs(0, 71).mirror().addBox(-10.75F, -34.6F, 3.7F, 16.0F, 16.0F, 1.0F, new CubeDeformation(-5.0F)).mirror(false).texOffs(0, 103).mirror().addBox(-5.25F, -34.6F, 3.7F, 16.0F, 16.0F, 1.0F, new CubeDeformation(-5.0F)).mirror(false).texOffs(0, 87).mirror().addBox(-5.5F, -22.6F, 3.7F, 16.0F, 16.0F, 1.0F, new CubeDeformation(-5.0F)).mirror(false).texOffs(33, 87).mirror().addBox(-10.75F, -22.6F, 3.7F, 16.0F, 16.0F, 1.0F, new CubeDeformation(-5.0F)).mirror(false).texOffs(98, 29).addBox(-5.0F, -31.0F, -0.1F, 10.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -5.0F));

        PartDefinition cube_r1 = door.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(57, 49).addBox(-5.0F, -9.0F, 0.0F, 10.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone11 = partdefinition.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(0, 49).addBox(-7.0F, -35.0F, -1.0F, 14.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-10.0F, -32.0F, 2.0F, 20.0F, 36.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(91, 0).addBox(-4.0F, -23.0F, 14.0F, 8.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(91, 17).addBox(-4.0F, -6.0F, 14.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(91, 23).addBox(-3.0F, -8.0F, 14.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(76, 10).addBox(10.0F, -26.0F, 5.0F, 1.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(76, 10).mirror().addBox(-11.0F, -26.0F, 5.0F, 1.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 20.0F, -7.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.xRot = (open) ? 1.6f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}