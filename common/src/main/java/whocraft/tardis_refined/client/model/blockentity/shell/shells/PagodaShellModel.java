package whocraft.tardis_refined.client.model.blockentity.shell.shells;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PagodaShellModel extends ShellModel {
    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone4;
    private final ModelPart bone;
    private final ModelPart bone17;
    private final ModelPart bone21;
    private final ModelPart bone13;
    private final ModelPart bone9;
    private final ModelPart bb_main;

    public PagodaShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door = root.getChild("door");
        this.bone4 = root.getChild("bone4");
        this.bone = root.getChild("bone");
        this.bone17 = root.getChild("bone17");
        this.bone21 = root.getChild("bone21");
        this.bone13 = root.getChild("bone13");
        this.bone9 = root.getChild("bone9");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = ((PartDefinition) partdefinition).addOrReplaceChild("door", CubeListBuilder.create().texOffs(0, 52).addBox(-14.0F, -14.0F, -0.5F, 14.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-14.0F, -14.0F, 0.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 6.0F, -8.0F));

        PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(31, 71).addBox(7.0F, -32.0F, -8.0F, 2.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(31, 71).addBox(7.0F, -32.0F, -9.0F, 2.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone7 = bone5.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(31, 71).addBox(7.0F, -32.0F, -9.0F, 2.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(31, 71).addBox(7.0F, -32.0F, -9.0F, 2.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 18).addBox(-7.0F, -32.0F, -8.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(31, 45).addBox(-7.0F, -32.0F, -8.5F, 14.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(75, 75).addBox(-4.0F, -28.0F, -10.0F, 8.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(49, 7).addBox(-6.0F, 0.0F, 0.0F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -28.0F, -11.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 18).addBox(-7.0F, -32.0F, -8.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(31, 45).addBox(-7.0F, -32.0F, -8.5F, 14.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(75, 75).addBox(-4.0F, -28.0F, -10.0F, 8.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(49, 7).addBox(-6.0F, 0.0F, 0.0F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -28.0F, -11.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone6 = bone2.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 18).addBox(-7.0F, -32.0F, -8.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(31, 45).addBox(-7.0F, -32.0F, -8.5F, 14.0F, 24.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(75, 75).addBox(-4.0F, -28.0F, -10.0F, 8.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = bone6.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(49, 7).addBox(-6.0F, 0.0F, 0.0F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -28.0F, -11.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone17 = partdefinition.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offset(0.0F, -17.25F, 0.0F));

        PartDefinition cube_r4 = bone17.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(49, 0).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = bone18.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(49, 0).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r6 = bone19.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(49, 0).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r7 = bone20.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(49, 0).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone21 = partdefinition.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -18.25F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r8 = bone21.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(50, 59).addBox(-0.025F, -3.2734F, -8.3557F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(69, 7).addBox(-1.0F, 0.2266F, -6.8557F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r9 = bone22.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(50, 59).addBox(-0.025F, -3.2734F, -8.3557F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(69, 7).addBox(-1.0F, 0.2266F, -6.8557F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r10 = bone23.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(50, 59).addBox(-0.025F, -3.2734F, -8.3557F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(69, 7).addBox(-1.0F, 0.2266F, -6.8557F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r11 = bone24.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(50, 59).addBox(-0.025F, -3.2734F, -8.3557F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(69, 7).addBox(-1.0F, 0.2266F, -6.8557F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone13 = partdefinition.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(0.0F, -11.25F, 0.0F));

        PartDefinition cube_r12 = bone13.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(31, 18).addBox(-9.5F, 0.0F, -6.0F, 19.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r13 = bone14.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(31, 18).addBox(-9.5F, 0.0F, -6.0F, 19.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r14 = bone15.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(31, 18).addBox(-9.5F, 0.0F, -6.0F, 19.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r15 = bone16.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(31, 18).addBox(-9.5F, 0.0F, -6.0F, 19.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone9 = partdefinition.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.25F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r16 = bone9.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(62, 33).addBox(0.0F, -2.5F, -11.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(65, 50).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r17 = bone10.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(62, 33).addBox(0.0F, -2.5F, -11.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(65, 50).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r18 = bone11.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(62, 33).addBox(0.0F, -2.5F, -11.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(65, 50).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r19 = bone12.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(62, 33).addBox(0.0F, -2.5F, -11.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(65, 50).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(31, 27).addBox(-5.0F, -39.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(77, 27).addBox(-2.0F, -43.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(23, 91).addBox(-9.0F, -33.0F, -9.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(0, 111).addBox(-8.0F, -0.025F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? -275f : 0;

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone17.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone21.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone13.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}