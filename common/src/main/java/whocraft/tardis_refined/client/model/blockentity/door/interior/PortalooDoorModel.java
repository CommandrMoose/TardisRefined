package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;

public class PortalooDoorModel extends ShellDoorModel {

    private final ModelPart bone;
    private final ModelPart Door;
    private final ModelPart root;

    public PortalooDoorModel(ModelPart root) {
        this.root = root;
        this.bone = root.getChild("bone");
        this.Door = root.getChild("Door");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 26.0F, 5.75F));

        PartDefinition bone4 = bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(31, 42).addBox(8.0F, -35.0F, 0.0F, 3.0F, 33.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(31, 42).mirror().addBox(-11.0F, -35.0F, 0.0F, 3.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(-11.0F, -37.0F, 1.25F, 22.0F, 35.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(31, 37).addBox(-11.0F, -37.0F, 0.0F, 22.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(47, 0).addBox(-7.0F, -35.0F, 0.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 42).addBox(7.0F, -35.0F, 0.0F, 1.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 42).mirror().addBox(-8.0F, -35.0F, 0.0F, 1.0F, 33.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.5F));

        PartDefinition Door = partdefinition.addOrReplaceChild("Door", CubeListBuilder.create().texOffs(0, 37).addBox(-14.0F, -9.5F, -1.0F, 14.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(47, 4).addBox(-13.0F, -10.5F, -1.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(47, 7).addBox(-13.0F, 2.0F, -2.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 2.5F, 6.75F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        this.Door.yRot = (open) ? (ModCompatChecker.immersivePortals() ? 1.75f : -1.75f) : 0;
    }

}