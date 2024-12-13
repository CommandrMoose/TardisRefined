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

public class GrowthShellModel extends ShellModel {

    private final ModelPart root;
    private final ModelPart door_closed;
    private final ModelPart door_open;
    private final ModelPart bone50;

    public GrowthShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door_closed = root.getChild("door_closed");
        this.door_open = root.getChild("door_open");
        this.bone50 = root.getChild("bone50");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door_closed = partdefinition.addOrReplaceChild("door_closed", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone51 = door_closed.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(58, 54).addBox(-0.25F, -12.0F, -19.775F, 1.0F, 40.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone52 = door_closed.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(58, 54).mirror().addBox(-0.75F, -12.0F, -19.775F, 1.0F, 40.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

        PartDefinition bone57 = door_closed.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(0, 93).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone63 = door_closed.addOrReplaceChild("bone63", CubeListBuilder.create().texOffs(0, 51).addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition door_open = partdefinition.addOrReplaceChild("door_open", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone53 = door_open.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(59, 118).addBox(-0.25F, -12.0F, -19.775F, 1.0F, 40.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone54 = door_open.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(59, 118).mirror().addBox(-0.75F, -12.0F, -19.775F, 1.0F, 40.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

        PartDefinition bone55 = door_open.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(1, 157).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone56 = door_open.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(1, 115).addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone50 = partdefinition.addOrReplaceChild("bone50", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition black = bone50.addOrReplaceChild("black", CubeListBuilder.create().texOffs(98, 0).mirror().addBox(-5.45F, -38.0F, -7.75F, 1.0F, 38.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 0).addBox(4.45F, -38.0F, -7.75F, 1.0F, 38.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(73, 0).addBox(-5.0F, -38.0F, -6.85F, 10.0F, 38.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone44 = bone50.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(46, 41).addBox(-6.5F, -0.2F, -10.275F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(46, 41).addBox(-6.5F, -0.2F, -10.275F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(46, 41).addBox(-6.5F, -0.2F, -10.275F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(46, 41).addBox(-6.5F, -0.2F, -10.275F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(46, 41).addBox(-6.5F, -0.2F, -10.275F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49 = bone48.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(46, 41).addBox(-6.5F, -0.2F, -10.275F, 13.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone26 = bone50.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(36, 23).addBox(-4.5F, -40.025F, -7.775F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(36, 23).addBox(-4.5F, -40.025F, -7.775F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(36, 23).addBox(-4.5F, -40.025F, -7.775F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(36, 23).addBox(-4.5F, -40.025F, -7.775F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(36, 23).addBox(-4.5F, -40.025F, -7.775F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone31 = bone30.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(36, 23).addBox(-4.5F, -40.025F, -7.775F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone38 = bone50.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -12.0F, -9.025F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -12.0F, -9.025F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -12.0F, -9.025F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -12.0F, -9.025F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -12.0F, -9.025F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone43 = bone42.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(36, 34).addBox(-1.0F, -12.0F, -9.025F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone32 = bone50.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -28.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, -12.0F, -15.025F, 1.0F, 40.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, -12.0F, -15.025F, 1.0F, 40.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, -12.0F, -15.025F, 1.0F, 40.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(21, 23).addBox(-1.0F, -12.0F, -15.025F, 1.0F, 40.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = bone36.addOrReplaceChild("bone37", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone20 = bone50.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -40.0F, -7.775F, 9.0F, 40.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone8 = bone50.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(0.0F, -31.0F, 0.0F));

        PartDefinition bone14 = bone8.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -9.025F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -9.025F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone11 = bone9.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -9.025F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone13 = bone11.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone15 = bone13.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -9.025F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone16 = bone13.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -9.025F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone18 = bone16.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -9.025F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone2 = bone50.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(21, 0).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(21, 0).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(21, 0).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(21, 0).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(21, 0).addBox(-6.5F, -21.0F, -11.275F, 13.0F, 21.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,  float red, float green, float blue, float alpha) {
        int finalColor = RenderHelper.rgbaToInt(red, green, blue, this.getCurrentAlpha());
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        door_open.visible = open;
        door_closed.visible = !open;
        door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
        door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        door_closed.visible = true;
        door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone50.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}