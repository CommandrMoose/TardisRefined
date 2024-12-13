package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class LiftShellModel extends ShellModel {


    private final ModelPart root;
    private final ModelPart doorOpen, doorClosed;

    public LiftShellModel(ModelPart root) {
        super(root);
        this.root = root;
        ModelPart bone28 = root.getChild("bone28");
        this.doorOpen = bone28.getChild("door_open");
        this.doorClosed = bone28.getChild("door_closed");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone28 = partdefinition.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(0, 63).addBox(-8.0F, -32.0F, 16.0F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-9.0F, -35.0F, -1.0F, 18.0F, 3.0F, 18.0F, new CubeDeformation(0.025F))
                .texOffs(94, 33).addBox(-3.0F, -35.025F, 5.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.025F))
                .texOffs(0, 43).addBox(-9.0F, -1.0F, -1.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(0.025F))
                .texOffs(0, 0).addBox(-9.5F, -36.0F, -1.5F, 19.0F, 1.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(80, 4).addBox(-5.5F, -40.5F, -1.025F, 11.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -7.0F));

        PartDefinition door_open = bone28.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(83, 96).addBox(5.0F, -32.0F, -1.0F, 3.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition door_closed = bone28.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(92, 80).addBox(-8.0F, -32.0F, -1.0F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(92, 46).addBox(-8.0F, -32.0F, -0.25F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone7 = door_closed.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone6 = bone7.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(4.0F, -11.0F, -0.5F));

        PartDefinition cube_r1 = bone6.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 12).mirror().addBox(0.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -5.5F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition cube_r2 = bone6.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(11, 21).mirror().addBox(0.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -5.5F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition bone5 = bone7.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-4.0F, -11.0F, -0.5F));

        PartDefinition cube_r3 = bone5.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 12).addBox(-1.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.5F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition cube_r4 = bone5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(11, 21).addBox(-1.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.5F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition bone4 = bone7.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-4.0F, -11.0F, -0.5F));

        PartDefinition cube_r5 = bone4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(11, 21).mirror().addBox(0.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition cube_r6 = bone4.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(11, 21).addBox(-1.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition bone3 = bone7.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(5.0F, -11.0F, -0.5F));

        PartDefinition cube_r7 = bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(11, 21).mirror().addBox(0.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition cube_r8 = bone3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(11, 21).addBox(-1.0F, 0.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition bone2 = bone7.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, -0.5F));

        PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(11, 21).mirror().addBox(0.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition cube_r10 = bone2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(11, 21).addBox(-1.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition bone = bone7.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-4.0F, -22.0F, -0.5F));

        PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(11, 21).mirror().addBox(0.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4189F));

        PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(11, 21).addBox(-1.0F, -5.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4189F));

        PartDefinition bone13 = bone28.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(49, 91).addBox(-1.25F, -7.9546F, 2.8F, 1.0F, 21.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(44, 63).addBox(-2.0F, -11.4546F, 0.75F, 3.0F, 29.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(44, 63).addBox(-2.0F, -11.4546F, -2.0F, 3.0F, 29.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(57, 47).addBox(-1.5F, -12.4546F, -7.0F, 1.0F, 32.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -19.5454F, 9.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone26 = bone13.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offset(-0.5F, -7.4546F, 5.0F));

        PartDefinition cube_r13 = bone26.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 21).addBox(-0.25F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone27 = bone13.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offset(-0.5F, 14.5454F, 5.0F));

        PartDefinition cube_r14 = bone27.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 0).addBox(-0.25F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone24 = bone13.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -9.4546F, -0.5F));

        PartDefinition bone22 = bone13.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offset(-0.75F, -4.4546F, -4.0F));

        PartDefinition cube_r15 = bone22.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone21 = bone13.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offset(-0.5F, -8.4546F, -6.0F));

        PartDefinition cube_r16 = bone21.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 21).addBox(-0.25F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone23 = bone13.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offset(-0.5F, -0.4546F, -6.0F));

        PartDefinition cube_r17 = bone23.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 21).addBox(-0.25F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, -1.3526F, 0.0F, 0.0F));

        PartDefinition bone25 = bone13.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offset(-0.5F, 16.7954F, -0.5F));

        PartDefinition bone12 = bone28.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(49, 91).addBox(-0.75F, -7.5F, 8.8F, 1.0F, 21.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(44, 63).addBox(-1.5F, -11.0F, 6.75F, 3.0F, 29.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(44, 63).addBox(-1.5F, -11.0F, 4.0F, 3.0F, 29.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(57, 47).addBox(-1.0F, -12.0F, -1.0F, 1.0F, 32.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -20.0F, 1.0F));

        PartDefinition bone20 = bone12.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 11.0F));

        PartDefinition cube_r18 = bone20.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 21).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone19 = bone12.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 11.0F));

        PartDefinition cube_r19 = bone19.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone17 = bone12.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 5.5F));

        PartDefinition bone15 = bone12.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offset(-0.25F, -4.0F, 2.0F));

        PartDefinition cube_r20 = bone15.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 0).addBox(-0.75F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone14 = bone12.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition cube_r21 = bone14.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 21).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone16 = bone12.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r22 = bone16.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 21).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.3526F, 0.0F, 0.0F));

        PartDefinition bone18 = bone12.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 17.25F, 5.5F));

        PartDefinition bone11 = bone28.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(35, 63).addBox(8.0F, -37.0F, -2.0F, 2.0F, 37.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(35, 63).mirror().addBox(-10.0F, -37.0F, -2.0F, 2.0F, 37.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 16.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone10 = bone28.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(35, 63).addBox(8.0F, -37.0F, -2.0F, 2.0F, 37.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(6.5F, -33.25F, -3.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(80, 0).addBox(-7.5F, -33.225F, -2.5F, 16.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(35, 63).mirror().addBox(-10.0F, -37.0F, -2.0F, 2.0F, 37.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 51).addBox(-13.0F, -30.0F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 43).addBox(-10.5F, -22.0F, -2.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone9 = bone28.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(59, 3).addBox(0.0F, -5.433F, -9.0F, 1.0F, 3.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -38.067F, 8.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r23 = bone9.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(55, 25).addBox(0.0F, 0.0F, -9.0F, 10.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.65F, 2.067F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r24 = bone9.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(55, 25).mirror().addBox(-10.0F, 0.0F, -9.0F, 10.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.65F, 2.067F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition bone8 = bone28.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(59, 3).addBox(8.65F, -7.5F, -9.0F, 1.0F, 3.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.65F, -36.0F, 8.0F));

        PartDefinition cube_r25 = bone8.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(55, 25).addBox(0.0F, 0.0F, -9.0F, 10.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r26 = bone8.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(55, 25).mirror().addBox(-10.0F, 0.0F, -9.0F, 10.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(17.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition blackface = bone28.addOrReplaceChild("blackface", CubeListBuilder.create().texOffs(0, 97).addBox(-8.0F, -32.0F, -0.75F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition clock_hand = bone28.addOrReplaceChild("clock_hand", CubeListBuilder.create().texOffs(80, 11).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -37.0F, -1.275F));
        addMaterializationPart(partdefinition);
        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
        doorOpen.visible = open;
        doorClosed.visible = !open;
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}
