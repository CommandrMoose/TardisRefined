package whocraft.tardis_refined.client.model.blockentity.shell.shells;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PathfinderShellModel extends ShellModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    private final ModelPart root;
    private final ModelPart bone3;
    private final ModelPart r_door;
    private final ModelPart l_door;
    private final ModelPart bb_main;

    public PathfinderShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.bone3 = root.getChild("bone3");
        this.r_door = root.getChild("r_door");
        this.l_door = root.getChild("l_door");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(47, 19).addBox(-10.0F, -54.0F, 2.775F, 19.0F, 4.0F, 4.0F, new CubeDeformation(0.025F))
                .texOffs(27, 36).addBox(-12.0F, -50.0F, 3.025F, 23.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(47, 0).addBox(-12.0F, -50.0F, 4.025F, 23.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 29.0F, 1.25F));

        PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 47).mirror().addBox(-4.0F, -14.0F, -1.975F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(15.9565F, -41.351F, 4.75F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 47).addBox(0.0F, -14.0F, -1.975F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.9565F, -41.351F, 4.75F, 0.0F, 0.0F, 0.5236F));

        PartDefinition r_door = partdefinition.addOrReplaceChild("r_door", CubeListBuilder.create().texOffs(0, 36).addBox(-10.0F, -18.0F, -1.0F, 11.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 8.0F, 6.75F));

        PartDefinition cube_r3 = r_door.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(47, 28).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, 0.0F, -1.0F, -0.2618F, 0.0F, -0.2618F));

        PartDefinition l_door = partdefinition.addOrReplaceChild("l_door", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-1.0F, -18.0F, -1.0F, 11.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.0F, 8.0F, 6.75F));

        PartDefinition cube_r4 = l_door.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(47, 28).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, 0.0F, -1.0F, -0.2618F, 0.0F, 0.2618F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(27, 47).addBox(-16.0F, -34.0F, 4.0F, 5.0F, 34.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 61).addBox(-17.0F, -37.0F, 3.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(47, 11).addBox(-10.0F, -37.0F, 4.0F, 20.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 61).mirror().addBox(10.0F, -37.0F, 3.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(27, 47).mirror().addBox(11.0F, -34.0F, 4.0F, 5.0F, 34.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(-11.0F, -34.025F, 7.0F, 22.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
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
        this.l_door.yRot = (open) ? -275f : 0;
        this.r_door.yRot = (open) ? 275f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (isBaseModel) {
            poseStack.scale(1.05f, 1.05f, 1.05f);
            poseStack.translate(0, -0.07, 0);
        }
        handleAllAnimations(entity, bone3, isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());

        int finalColor = RenderHelper.rgbaToInt(red, green, blue, this.getCurrentAlpha());

        bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
        l_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
        r_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, finalColor);
    }

}