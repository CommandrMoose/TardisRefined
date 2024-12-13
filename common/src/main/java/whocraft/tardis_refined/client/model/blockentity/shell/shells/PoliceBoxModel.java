package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PoliceBoxModel extends ShellModel {
    private final ModelPart root;
    private final ModelPart left_door;
    private final ModelPart right_door;
    private final ModelPart frame;

    public PoliceBoxModel(ModelPart root) {
        super(root);
        this.root = root;
        this.frame = root.getChild("tardis_frame");
        this.right_door = root.getChild("right_door");
        this.left_door = root.getChild("left_door");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tardis_frame = partdefinition.addOrReplaceChild("tardis_frame", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone = tardis_frame.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition frame = bone.addOrReplaceChild("frame", CubeListBuilder.create().texOffs(64, 41).addBox(-9.0F, -36.25F, -11.25F, 18.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-10.5F, -43.0F, -10.5F, 21.0F, 4.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -45.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-2.0F, -48.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-2.5F, -49.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(64, 28).addBox(-10.5F, -39.5F, -12.5F, 21.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-12.5F, -2.0F, -12.5F, 25.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(0, 54).addBox(-9.5F, -33.5F, -1.75F, 19.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(41, 54).addBox(-9.5F, -34.275F, -9.25F, 19.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition frame_r1 = frame.addOrReplaceChild("frame_r1", CubeListBuilder.create().texOffs(64, 28).addBox(-10.5F, -39.5F, -12.5F, 21.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition frame_r2 = frame.addOrReplaceChild("frame_r2", CubeListBuilder.create().texOffs(64, 28).addBox(-10.5F, -39.5F, -12.5F, 21.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition frame_r3 = frame.addOrReplaceChild("frame_r3", CubeListBuilder.create().texOffs(64, 28).addBox(-10.5F, -39.5F, -12.5F, 21.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone3 = frame.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(64, 35).addBox(-9.0F, -36.25F, -11.25F, 18.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone11 = bone3.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(-6.5F, -8.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 37).addBox(-6.5F, -16.0F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(-6.5F, -23.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 88).addBox(-7.5F, -32.0F, 0.75F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-6.5F, -31.25F, 0.15F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 16).addBox(-5.5F, -28.75F, 0.075F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -1.5F, -11.25F));

        PartDefinition bone12 = bone3.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(0, 37).addBox(0.5F, -8.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(0.5F, -16.0F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(0.5F, -23.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 88).addBox(-0.5F, -32.0F, 0.75F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 107).addBox(7.5F, -32.0F, 0.25F, 1.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -1.5F, -11.25F));

        PartDefinition bone4 = bone12.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-3.25F, -8.25F, 0.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 16).addBox(-2.25F, -5.75F, 0.825F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.75F, -23.0F, -0.75F));

        PartDefinition bone6 = frame.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(64, 35).addBox(-9.0F, -36.25F, -11.25F, 18.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone13 = bone6.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(76, 0).mirror().addBox(-6.5F, -8.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 37).addBox(-6.5F, -16.0F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(-6.5F, -23.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 88).addBox(-7.5F, -32.0F, 0.75F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-6.5F, -31.25F, 0.15F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 16).addBox(-5.5F, -28.75F, 0.075F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -1.5F, -11.25F));

        PartDefinition bone14 = bone6.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(0, 37).addBox(0.5F, -8.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(0.5F, -16.0F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(0.5F, -23.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 88).addBox(-0.5F, -32.0F, 0.75F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 107).addBox(7.5F, -32.0F, 0.25F, 1.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -1.5F, -11.25F));

        PartDefinition bone7 = bone14.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-3.25F, -8.25F, 0.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 16).addBox(-2.25F, -5.75F, 0.825F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.75F, -23.0F, -0.75F));

        PartDefinition bone8 = frame.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(64, 35).addBox(-9.0F, -36.25F, -11.25F, 18.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone15 = bone8.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(-6.5F, -8.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(76, 0).addBox(-6.5F, -16.0F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(-6.5F, -23.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 88).addBox(-7.5F, -32.0F, 0.75F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-6.5F, -31.25F, 0.15F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 16).addBox(-5.5F, -28.75F, 0.075F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -1.5F, -11.25F));

        PartDefinition bone16 = bone8.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(76, 0).addBox(0.5F, -8.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(0.5F, -16.0F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(0.5F, -23.5F, 0.25F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 88).addBox(-0.5F, -32.0F, 0.75F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 107).addBox(7.5F, -32.0F, 0.25F, 1.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -1.5F, -11.25F));

        PartDefinition bone9 = bone16.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-3.25F, -8.25F, 0.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 16).addBox(-2.25F, -5.75F, 0.825F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.75F, -23.0F, -0.75F));

        PartDefinition bone2 = frame.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(80, 64).addBox(8.5F, -41.0F, -11.5F, 3.0F, 39.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(67, 64).addBox(-11.5F, -41.0F, -11.5F, 3.0F, 39.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone10 = frame.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(54, 64).addBox(8.5F, -41.0F, -11.5F, 3.0F, 39.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(41, 64).addBox(-11.5F, -41.0F, -11.5F, 3.0F, 39.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition right_door = partdefinition.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(-7.0F, -8.5F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 37).addBox(-7.0F, -16.0F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 0).addBox(-7.0F, -23.5F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(41, 54).addBox(-8.5F, -22.0F, -1.75F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(88, 47).addBox(-6.5F, -23.0F, -0.525F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(93, 98).addBox(-8.0F, -32.0F, 0.0F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 9).addBox(-7.0F, -31.25F, -0.6F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 18).addBox(-6.0F, -28.75F, -0.675F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, 22.5F, -10.5F));

        PartDefinition left_door = partdefinition.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(0, 37).addBox(1.0F, -8.5F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(1.0F, -16.0F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(1.0F, -23.5F, -0.5F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 17).addBox(1.5F, -23.0F, -0.525F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(5.5F, -21.5F, -1.275F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(93, 64).addBox(0.0F, -32.0F, 0.0F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(45, 107).addBox(8.0F, -32.0F, -0.5F, 1.0F, 32.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, 22.5F, -10.5F));

        PartDefinition bone5 = left_door.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(76, 9).mirror().addBox(-3.25F, -8.25F, 0.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(76, 18).addBox(-2.25F, -5.75F, 0.825F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.25F, -23.0F, -1.5F));
        addMaterializationPart(partdefinition);
        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        poseStack.scale(1.05f, 1.05f, 1.05f);
        poseStack.translate(0, -0.07, 0);
        frame.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        this.right_door.yRot = (open) ? -275f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (isBaseModel) {
            poseStack.scale(1.05f, 1.05f, 1.05f);
            poseStack.translate(0, -0.07, 0);
        }
        int finalColor = RenderHelper.rgbaToInt(red, green, blue, getCurrentAlpha());
        handleAllAnimations(entity, frame, isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());

        frame.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
    }


}