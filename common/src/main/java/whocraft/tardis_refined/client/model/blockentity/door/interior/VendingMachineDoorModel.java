package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;

public class VendingMachineDoorModel extends ShellDoorModel {

    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone11;

    public VendingMachineDoorModel(ModelPart root) {
        this.root = root;
        this.door = root.getChild("door");
        this.bone11 = root.getChild("bone11");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(47, 0).mirror().addBox(6.975F, -31.0F, -2.0F, 3.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(47, 0).addBox(-9.975F, -31.0F, -2.0F, 3.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(35, 40).addBox(-7.0F, -31.0F, -1.0F, 14.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 40).addBox(-8.0F, -32.0F, 0.0F, 16.0F, 33.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 8.0F));

        PartDefinition bone11 = partdefinition.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(58, 0).addBox(-7.0F, -35.0F, 0.0F, 14.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-10.0F, -32.0F, 2.0F, 20.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 6.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone11.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.xRot = (open) ? (ModCompatChecker.immersivePortals() ? -1.6f : 1.6F) : 0;
    }


    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}