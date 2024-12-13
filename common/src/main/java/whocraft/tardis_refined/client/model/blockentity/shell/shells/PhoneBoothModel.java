package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class PhoneBoothModel extends ShellModel {

    private final ModelPart bone9;
    private final ModelPart door;
    private final ModelPart root;

    public PhoneBoothModel(ModelPart root) {
        super(root);
        this.root = root;
        this.bone9 = root.getChild("bone9");
        this.door = this.bone9.getChild("Door");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone9 = partdefinition.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(61, 0).addBox(-8.0F, -42.5F, -8.25F, 16.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -2.0F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(40, 71).addBox(-7.0F, -36.0F, -7.0F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 71).addBox(-7.0F, -36.0F, -8.0F, 14.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 50).addBox(-8.0F, -42.0F, -8.0F, 16.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-8.5F, -46.0F, -8.5F, 17.0F, 4.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-8.0F, -45.5F, -8.0F, 16.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(61, 7).addBox(-7.0F, -38.0F, -9.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Door = bone9.addOrReplaceChild("Door", CubeListBuilder.create().texOffs(0, 66).addBox(0.0F, -15.25F, -0.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(9, 0).addBox(10.0F, -3.75F, -1.5F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 8).addBox(13.1F, -3.25F, -3.0F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(69, 23).addBox(0.5F, -14.25F, 0.0F, 13.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -20.75F, -8.0F));

        PartDefinition bone6 = bone9.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(61, 7).addBox(-7.0F, -38.0F, -9.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-7.0F, -36.0F, -8.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 0).addBox(-8.0F, -42.5F, -8.25F, 16.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(69, 23).addBox(-6.5F, -35.0F, -8.0F, 13.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(61, 7).addBox(-7.0F, -38.0F, -9.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-7.0F, -36.0F, -8.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 0).addBox(-8.0F, -42.5F, -8.25F, 16.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(69, 23).addBox(-6.5F, -35.0F, -8.0F, 13.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(61, 7).addBox(-7.0F, -38.0F, -9.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-7.0F, -36.0F, -8.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 0).addBox(-8.0F, -42.5F, -8.25F, 16.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(69, 23).addBox(-6.5F, -35.0F, -8.0F, 13.0F, 25.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone4 = bone9.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(31, 66).mirror().addBox(-0.5F, -20.0F, -1.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-0.5F, 8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(0, 23).mirror().addBox(-0.5F, -20.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(7.5F, -18.0F, 8.0F));

        PartDefinition bone2 = bone9.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(31, 66).mirror().addBox(-0.5F, -20.0F, -1.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).mirror().addBox(-0.5F, 8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(0, 23).mirror().addBox(-0.5F, -20.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(7.5F, -18.0F, -8.0F));

        PartDefinition bone3 = bone9.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(31, 66).addBox(-1.5F, -20.0F, -1.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, 8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(0, 23).addBox(-1.5F, -20.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-7.5F, -18.0F, 8.0F));

        PartDefinition bone = bone9.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(31, 66).addBox(-1.5F, -20.0F, -1.0F, 2.0F, 36.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, 8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(0, 23).addBox(-1.5F, -20.75F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-7.5F, -18.0F, -8.0F));

        ShellModel.addMaterializationPart(partdefinition);


        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
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
        this.door.yRot = (open) ? 1.75f : 0;
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}