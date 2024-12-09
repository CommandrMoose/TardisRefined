package whocraft.tardis_refined.client.model.blockentity.door.interior;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class HieroglyphShellDoor extends ShellDoorModel {

    private final ModelPart root;
    private final ModelPart door_closed;
    private final ModelPart door_open;
    private final ModelPart bone6;
    private final ModelPart pillars;
    private final ModelPart bone18;
    private final ModelPart bb_main;


    public HieroglyphShellDoor(ModelPart modelPart) {
        this.root = modelPart;
        this.door_closed = root.getChild("door_closed");
        this.door_open = root.getChild("door_open");
        this.bone6 = root.getChild("bone6");
        this.pillars = root.getChild("pillars");
        this.bone18 = root.getChild("bone18");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door_closed = partdefinition.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(66, 69).addBox(-7.5F, -33.0F, -7.5F, 15.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(95, 98).addBox(-5.0F, -27.0F, -11.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(0.025F))
                .texOffs(0, 25).addBox(2.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).mirror().addBox(-4.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 33).addBox(-5.0F, -12.0F, -11.0F, 10.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(64, 33).addBox(-4.5F, -11.5F, -10.5F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 12.5F));

        PartDefinition cube_r1 = door_closed.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -9.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r2 = door_closed.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(99, 69).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone29 = door_closed.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(9, 25).addBox(-1.5F, 0.0F, 0.25F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, -9.75F));

        PartDefinition door_open = partdefinition.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(33, 69).addBox(5.5F, -33.0F, -7.5F, 15.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 60).addBox(5.5F, -33.0F, -6.5F, 15.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(95, 98).addBox(8.0F, -27.0F, -11.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(0.025F))
                .texOffs(9, 25).addBox(11.5F, -24.0F, -9.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(0, 25).addBox(15.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).mirror().addBox(8.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 33).addBox(8.0F, -12.0F, -11.0F, 10.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(64, 33).addBox(8.5F, -11.5F, -10.5F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-8.5F, -0.2F, -14.75F, 26.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 24.0F, 8.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r3 = door_open.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 13).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -12.0F, -9.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r4 = door_open.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(99, 69).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -27.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 40).addBox(1.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).mirror().addBox(-7.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 22.0F, 12.5F));

        PartDefinition bone5 = bone6.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.245F, -36.7704F, -8.0F, 0.0F, -0.0044F, 0.0F));

        PartDefinition cube_r5 = bone5.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(33, 60).mirror().addBox(-0.55F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition bone2 = bone6.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(3.245F, -36.7704F, -8.0F, 0.0F, 0.0044F, 0.0F));

        PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(33, 60).addBox(-7.45F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition pillars = partdefinition.addOrReplaceChild("pillars", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 12.5F));

        PartDefinition bone3 = pillars.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(6.5F, -10.0F, -10.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 95).addBox(7.0F, -41.0F, -9.5F, 3.0F, 31.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone4 = pillars.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-10.5F, -10.0F, -10.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 95).mirror().addBox(-10.0F, -41.0F, -9.5F, 3.0F, 31.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone18 = partdefinition.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(98, 50).addBox(-7.5F, -34.0F, -9.25F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 12.5F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(17, 42).addBox(-10.5F, -2.0F, 2.0F, 21.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(-10.5F, -39.0F, 2.0F, 21.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(69, 60).addBox(-6.5F, -45.0F, 6.0F, 13.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(85, 0).addBox(-7.0F, -33.0F, 5.5F, 14.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 105).addBox(-10.5F, -39.0F, 6.0F, 21.0F, 39.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }


    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        pillars.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone18.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

        door_closed.visible = !open;
        door_open.visible = open;
    }

    @Override
    public void renderPortalMask(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public ModelPart root() {
        return root;
    }
}
