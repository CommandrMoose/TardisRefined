package whocraft.tardis_refined.client.model.blockentity.shell.shells;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PortalooShellModel extends ShellModel {

    private final ModelPart bone;
    private final ModelPart door;
    private final ModelPart root;

    public PortalooShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.bone = root.getChild("bone");
        this.door = root.getChild("door");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F, -38.0F, -1.0F, 12.0F, 2.0F, 19.0F, new CubeDeformation(0.025F))
                .texOffs(44, 114).addBox(-7.0F, -35.0F, 1.5F, 14.0F, 1.0F, 13.0F, new CubeDeformation(0.025F))
                .texOffs(80, 17).addBox(-8.5F, -36.0F, 0.0F, 17.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 17).addBox(-8.5F, -36.0F, 16.0F, 17.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 43).addBox(-8.0F, -35.0F, 14.0F, 16.0F, 33.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -8.0F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(39, 43).addBox(0.0F, 0.0F, -9.5F, 7.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -38.0F, 8.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(39, 43).mirror().addBox(-7.0F, 0.0F, -9.5F, 7.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, -38.0F, 8.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition base = bone.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.975F, -0.5F, 18.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(100, 82).addBox(7.0F, -33.975F, 1.5F, 1.0F, 33.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(100, 82).mirror().addBox(-8.0F, -33.975F, 1.5F, 1.0F, 33.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(54, 2).addBox(9.0F, -3.0F, -1.0F, 3.0F, 3.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(54, 2).mirror().addBox(-12.0F, -3.0F, -1.0F, 3.0F, 3.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone4 = bone.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 80).mirror().addBox(-11.0F, -35.0F, 0.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 80).addBox(8.0F, -35.0F, 0.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone5 = bone.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(73, 25).mirror().addBox(-11.0F, -35.0F, -3.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 25).addBox(8.0F, -35.0F, -3.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 17.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(86, 25).addBox(-7.0F, -35.0F, 0.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 80).addBox(-8.0F, -35.0F, 0.0F, 1.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 80).mirror().addBox(7.0F, -35.0F, 0.0F, 1.0F, 33.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.5F));

        PartDefinition bone6 = bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(39, 65).mirror().addBox(-11.0F, -35.0F, 3.0F, 3.0F, 33.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(80, 0).mirror().addBox(-12.0F, -33.0F, 3.0F, 1.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone7 = bone.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(39, 65).addBox(8.0F, -35.0F, 3.0F, 3.0F, 33.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(80, 0).addBox(11.0F, -33.0F, 3.0F, 1.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(68, 65).addBox(0.0F, -9.5F, -1.0F, 14.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 25).addBox(1.0F, -10.5F, -1.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(11.0F, 2.0F, -2.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 0.5F, -7.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? 1.75f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root, isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}