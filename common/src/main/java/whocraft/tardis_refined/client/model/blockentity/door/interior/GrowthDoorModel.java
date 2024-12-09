package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class GrowthDoorModel extends ShellDoorModel {
    private final ModelPart root;
    private final ModelPart door_closed;
    private final ModelPart door_open;
    private final ModelPart bb_main;
    boolean isDoorOpen = false;

    public GrowthDoorModel(ModelPart root) {
        this.root = root;
        this.door_closed = root.getChild("door_closed");
        this.door_open = root.getChild("door_open");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door_closed = partdefinition.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(33, 46).mirror().addBox(7.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 46).addBox(11.5F, -32.0F, -12.0F, 15.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(33, 46).addBox(26.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.0F, 24.0F, 19.55F));

        PartDefinition door_open = partdefinition.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(33, 80).mirror().addBox(7.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 80).addBox(11.5F, -32.0F, -12.0F, 15.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(33, 80).addBox(26.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.0F, 24.0F, 19.55F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(44, 46).addBox(-5.5F, -34.0F, 7.55F, 11.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-15.5F, -44.0F, 7.825F, 31.0F, 44.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door_closed.visible = !isDoorOpen;
        door_open.visible = isDoorOpen;

        door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        this.isDoorOpen = open;
    }

}