package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class BriefcaseDoorModel extends ShellDoorModel {

    private final ModelPart root;
    private final ModelPart door_open;
    private final ModelPart door_closed;
    private final ModelPart bb_main;

    private boolean isDoorOpen;

    public BriefcaseDoorModel(ModelPart root) {
        this.root = root;
        this.door_open = root.getChild("door_open");
        this.door_closed = root.getChild("door_closed");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door_open = partdefinition.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(51, 17).addBox(-10.0F, -46.9F, -6.0F, 20.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition door_closed = partdefinition.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(0, 17).addBox(-10.0F, -46.9F, -6.0F, 20.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 33).addBox(-10.0F, -47.0F, -6.0F, 20.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -47.75F, -6.0F, 20.0F, 2.0F, 14.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 49).addBox(-7.0F, 1.0F, -2.25F, 14.0F, 48.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -48.0F, 9.25F, -0.1309F, 0.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.isDoorOpen = open;
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (isDoorOpen) {
            door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        } else {
            door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }

        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

}