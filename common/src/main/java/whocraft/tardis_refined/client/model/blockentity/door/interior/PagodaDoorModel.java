package whocraft.tardis_refined.client.model.blockentity.door.interior;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class PagodaDoorModel extends ShellDoorModel {
    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart bone10;
    private final ModelPart bone9;
    private final ModelPart bone13;
    private final ModelPart bone;
    private final ModelPart bone4;
    private final ModelPart bb_main;

    public PagodaDoorModel(ModelPart root) {
        this.root = root;
        this.door = root.getChild("door");
        this.bone10 = root.getChild("bone10");
        this.bone9 = root.getChild("bone9");
        this.bone13 = root.getChild("bone13");
        this.bone = root.getChild("bone");
        this.bone4 = root.getChild("bone4");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(43, 0).mirror().addBox(-0.1F, -14.0F, -0.5F, 14.0F, 24.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 42).mirror().addBox(-0.1F, -14.0F, 0.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.9F, 6.0F, 4.0F));

        PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.25F, 12.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r1 = bone10.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(31, 45).mirror().addBox(-1.0F, -2.5F, -11.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(31, 62).mirror().addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone9 = partdefinition.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.25F, 12.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r2 = bone9.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(31, 45).addBox(0.0F, -2.5F, -11.0F, 1.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(31, 62).addBox(-1.0F, -1.0F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone13 = partdefinition.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(0.0F, -11.25F, 12.0F));

        PartDefinition cube_r3 = bone13.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(37, 36).addBox(-9.5F, 0.0F, -6.0F, 19.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 12.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone6 = bone2.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(58, 45).addBox(7.0F, -33.0F, -8.0F, 2.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 11.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone7 = bone5.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(58, 45).addBox(7.0F, -33.0F, -9.0F, 2.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -38.0F, 5.0F, 18.0F, 38.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 76).addBox(-9.0F, -0.025F, 3.0F, 18.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderFrame(GlobalDoorBlockEntity doorBlockEntity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone13.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        this.door.yRot = (open) ? -275f : 0;
    }

}