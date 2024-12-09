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

public class PhoneBoothDoorModel extends ShellDoorModel {

    private final ModelPart Door2;
    private final ModelPart bone5;
    private final ModelPart bone10;
    private final ModelPart bb_main;
    private final ModelPart root;

    public PhoneBoothDoorModel(ModelPart root) {
        this.root = root;
        this.Door2 = root.getChild("Door2");
        this.bone5 = root.getChild("bone5");
        this.bone10 = root.getChild("bone10");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Door2 = partdefinition.addOrReplaceChild("Door2", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -15.25F, -0.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 33).addBox(-14.0F, -17.25F, -1.5F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 37).addBox(-13.0F, -3.75F, -1.3F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 47).addBox(-13.1F, -3.25F, -3.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(31, 0).addBox(-13.5F, -14.25F, -1.0F, 13.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 5.25F, 7.5F));

        PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 36).addBox(34.5F, -18.0F, 14.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 36).addBox(34.5F, 9.75F, 13.95F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(9, 47).addBox(34.5F, -18.75F, 13.95F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-27.5F, 6.0F, -8.0F));

        PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(31, 27).addBox(-36.5F, -18.0F, 14.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(9, 36).addBox(-36.5F, 9.75F, 13.95F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(40, 45).addBox(-36.5F, -18.75F, 13.95F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(27.5F, 6.0F, -8.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(40, 27).addBox(-8.5F, -40.0F, 7.225F, 17.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 79).addBox(-9.0F, -37.0F, 7.25F, 18.0F, 37.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);


        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Door2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        this.Door2.yRot = (open) ? (ModCompatChecker.immersivePortals() ? 1.75f : -1.75f) : 0;
    }
}