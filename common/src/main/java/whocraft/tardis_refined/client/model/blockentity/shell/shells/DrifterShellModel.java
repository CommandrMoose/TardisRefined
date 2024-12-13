package whocraft.tardis_refined.client.model.blockentity.shell.shells;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class DrifterShellModel extends ShellModel {

    private final ModelPart root;
    private final ModelPart door_closed;
    private final ModelPart door_open;
    private final ModelPart bone32;

    public DrifterShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door_closed = root.getChild("door_closed");
        this.door_open = root.getChild("door_open");
        this.bone32 = root.getChild("bone32");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door_closed = partdefinition.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(33, 85).addBox(-7.0F, -32.0F, -9.0F, 14.0F, 30.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 71).addBox(-7.5F, -32.5F, -8.5F, 15.0F, 31.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition door_open = partdefinition.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(33, 133).addBox(-7.0F, -32.0F, -9.0F, 14.0F, 30.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 119).addBox(-7.5F, -32.5F, -8.5F, 15.0F, 31.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone32 = partdefinition.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(64, 93).addBox(-9.5F, -35.0F, -9.5F, 2.0F, 35.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(101, 37).addBox(-10.5F, -18.0F, -2.5F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(101, 49).addBox(-9.4F, -11.025F, 1.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(64, 93).mirror().addBox(7.5F, -35.0F, -9.5F, 2.0F, 35.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 22).mirror().addBox(7.5F, -33.0F, -7.5F, 1.0F, 33.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(68, 39).addBox(-7.5F, -33.0F, 7.5F, 15.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-8.5F, -33.0F, -7.5F, 1.0F, 33.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(64, 93).mirror().addBox(7.5F, -35.0F, 7.5F, 2.0F, 35.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 93).addBox(-9.5F, -35.0F, 7.5F, 2.0F, 35.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 39).addBox(-8.0F, -32.025F, -7.75F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.5F, -34.0F, -9.5F, 19.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(33, 22).addBox(-7.5F, -1.0F, -7.5F, 15.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(58, 11).addBox(-7.0F, -2.0F, -10.5F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(101, 37).addBox(6.4F, -2.5F, -11.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(101, 37).addBox(-7.4F, -2.5F, -11.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(9, 104).addBox(-1.0F, -44.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(8.0F, -29.0F, -3.0F, 1.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(99, 106).addBox(8.0F, -16.0F, 3.75F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(70, 74).mirror().addBox(7.75F, -28.0F, -8.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(70, 74).addBox(-8.75F, -28.0F, -8.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(73, 93).mirror().addBox(7.0F, -22.025F, -6.5F, 3.0F, 22.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(102, 0).mirror().addBox(6.5F, -9.0F, -7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(94, 11).addBox(6.0F, -4.025F, 0.5F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(101, 87).addBox(6.0F, -7.025F, 1.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(86, 106).mirror().addBox(6.5F, -9.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(101, 67).mirror().addBox(6.5F, -21.5F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(101, 67).mirror().addBox(6.5F, -0.5F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 104).mirror().addBox(7.0F, -12.025F, -2.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(77, 16).mirror().addBox(6.5F, -11.5F, -3.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(77, 16).mirror().addBox(6.5F, -1.5F, -3.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(86, 106).addBox(-9.5F, -9.0F, -2.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 104).addBox(-9.0F, -12.025F, -2.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(77, 16).addBox(-9.5F, -11.5F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(77, 16).addBox(-9.5F, -1.5F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(86, 106).addBox(-6.5F, -9.0F, 6.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(18, 22).addBox(-2.5F, -9.025F, 7.0F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(33, 73).addBox(2.5F, -15.025F, 8.5F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(9, 0).addBox(-0.5F, -13.025F, 8.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 104).addBox(-6.0F, -12.025F, 7.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(77, 16).addBox(-6.5F, -11.5F, 6.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(77, 16).addBox(-6.5F, -1.5F, 6.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(73, 93).addBox(-10.0F, -22.025F, -6.5F, 3.0F, 22.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(101, 67).addBox(-10.5F, -0.5F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(101, 67).addBox(-10.5F, -21.5F, -7.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(102, 0).addBox(-10.5F, -14.0F, -7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone2 = bone32.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition bone = bone2.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(33, 74).addBox(-8.5F, 0.0F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-8.5F, -0.25F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(79, 33).addBox(-8.5F, -0.25F, -2.75F, 17.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(33, 74).addBox(-8.5F, 0.0F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-8.5F, -0.25F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(79, 33).addBox(-8.5F, -0.25F, -2.75F, 17.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone5 = bone3.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(33, 74).addBox(-8.5F, 0.0F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-8.5F, -0.25F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(79, 33).addBox(-8.5F, -0.25F, -2.75F, 17.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone7 = bone5.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(33, 74).addBox(-8.5F, 0.0F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-8.5F, -0.25F, -0.75F, 17.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(79, 33).addBox(-8.5F, -0.25F, -2.75F, 17.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone27 = bone32.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(86, 93).addBox(-0.8735F, -1.0806F, -2.5F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -17.25F, -0.5F, 0.0F, 0.0F, 0.5672F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(64, 85).addBox(-3.5F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, 9.75F, -0.7854F, 0.0F, 0.0F));

        PartDefinition bone31 = bone32.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(77, 74).addBox(0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(77, 74).addBox(0.0F, 0.0F, 9.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(0.0F, 0.0F, 4.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.75F, -27.0F, -5.5F, 0.0F, 0.0F, 0.1745F));

        PartDefinition bone30 = bone32.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(106, 23).addBox(-2.0F, 0.0F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4F, -8.5F, 4.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition bone29 = bone32.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(18, 104).addBox(-2.0F, 0.0F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4F, -6.5F, 4.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition bone28 = bone32.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(89, 83).addBox(-2.5F, 0.0F, -2.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4F, -4.0F, 4.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition bone26 = bone32.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(86, 93).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -15.0F, 5.25F, 0.0F, 0.0F, -0.0873F));

        PartDefinition bone25 = bone32.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -2.5F, 1.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -28.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition bone34 = bone32.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(70, 74).mirror().addBox(7.75F, -28.0F, -8.0F, 1.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(8.0F, -29.0F, -3.0F, 1.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -2.5F, 1.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -28.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition bone9 = bone32.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(89, 74).addBox(-4.5F, 0.0F, 3.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone11 = bone9.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(89, 74).addBox(-4.5F, 0.0F, 3.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone13 = bone11.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(89, 74).addBox(-4.5F, 0.0F, 3.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone15 = bone13.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(89, 74).addBox(-4.5F, 0.0F, 3.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -34.0F, -8.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone17 = bone32.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -32.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(79, 11).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -13.0F, 1.1781F, 0.0F, 0.0F));

        PartDefinition bone19 = bone17.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(79, 11).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -13.0F, 1.1781F, 0.0F, 0.0F));

        PartDefinition bone21 = bone19.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(79, 11).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -13.0F, 1.1781F, 0.0F, 0.0F));

        PartDefinition bone23 = bone21.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(79, 11).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -13.0F, 1.1781F, 0.0F, 0.0F));

        ShellModel.addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        door_closed.visible = true;
        door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone32.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
        door_open.visible = open;
        door_closed.visible = !open;
        door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, this.getCurrentAlpha());
        door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, this.getCurrentAlpha());
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}