package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class HieroglyphModel extends ShellModel {


    private final ModelPart door_closed;
    private final ModelPart door_open;
    private final ModelPart sides;
    private final ModelPart bone6;
    private final ModelPart pillars;
    private final ModelPart bone18;
    private final ModelPart bb_main;
    private final ModelPart root;

    public HieroglyphModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door_closed = root.getChild("door_closed");
        this.door_open = root.getChild("door_open");
        this.sides = root.getChild("sides");
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
                .texOffs(64, 33).addBox(-4.5F, -11.5F, -10.5F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

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
                .texOffs(0, 49).addBox(-8.5F, -0.2F, -14.75F, 26.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 24.0F, -4.5F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r3 = door_open.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 13).addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -12.0F, -9.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r4 = door_open.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(99, 69).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -27.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition sides = partdefinition.addOrReplaceChild("sides", CubeListBuilder.create().texOffs(66, 69).addBox(-7.5F, -33.0F, -7.5F, 15.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(95, 98).addBox(-5.0F, -27.0F, -11.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(0.025F))
                .texOffs(0, 25).addBox(2.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).mirror().addBox(-4.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 33).addBox(-5.0F, -12.0F, -11.0F, 10.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(9, 32).addBox(-2.0F, -16.0F, -10.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(-0.25F, -15.0F, -9.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(1.0F, -14.25F, -9.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(-1.25F, -13.5F, -10.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(0.5F, -13.5F, -10.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 33).addBox(-4.5F, -11.5F, -10.5F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = sides.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(99, 69).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone30 = sides.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(9, 25).addBox(-1.5F, 0.0F, 1.25F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, -9.75F));

        PartDefinition bone = sides.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -17.5F, -9.0F));

        PartDefinition bone23 = sides.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, -0.5F, -0.075F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(13, 0).addBox(-0.9F, 0.5F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(13, 0).addBox(-2.75F, 0.5F, -1.175F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -14.25F, -9.65F));

        PartDefinition bone22 = sides.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(66, 69).addBox(-7.5F, -33.0F, -7.5F, 15.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(95, 98).addBox(-5.0F, -27.0F, -11.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(0.025F))
                .texOffs(0, 25).addBox(2.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).mirror().addBox(-4.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 33).addBox(-5.0F, -12.0F, -11.0F, 10.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(9, 32).addBox(-2.0F, -16.0F, -10.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(-0.25F, -15.0F, -9.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(1.0F, -14.25F, -9.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(-1.25F, -13.5F, -10.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(0.5F, -13.5F, -10.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 33).addBox(-4.5F, -11.5F, -10.5F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r6 = bone22.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(99, 69).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone31 = bone22.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(9, 25).addBox(-1.5F, 0.0F, 1.25F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, -9.75F));

        PartDefinition bone26 = bone22.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, -0.5F, -0.075F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(13, 0).addBox(-0.9F, 0.5F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(13, 0).addBox(-2.75F, 0.5F, -1.175F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -14.25F, -9.65F));

        PartDefinition bone25 = bone22.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -17.5F, -9.0F));

        PartDefinition bone24 = bone22.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(66, 69).addBox(-7.5F, -33.0F, -7.5F, 15.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(95, 98).addBox(-5.0F, -27.0F, -11.0F, 10.0F, 3.0F, 4.0F, new CubeDeformation(0.025F))
                .texOffs(0, 25).addBox(2.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).mirror().addBox(-4.5F, -24.0F, -9.5F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 33).addBox(-5.0F, -12.0F, -11.0F, 10.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(9, 32).addBox(-2.0F, -16.0F, -10.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(-0.25F, -15.0F, -9.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(1.0F, -14.25F, -9.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(-1.25F, -13.5F, -10.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 13).addBox(0.5F, -13.5F, -10.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 33).addBox(-4.5F, -11.5F, -10.5F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r7 = bone24.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(99, 69).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.0F, -11.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone32 = bone24.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(9, 25).addBox(-1.5F, 0.0F, 1.25F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, -9.75F));

        PartDefinition bone27 = bone24.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, -0.5F, -0.075F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(13, 0).addBox(-0.9F, 0.5F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F))
                .texOffs(13, 0).addBox(-2.75F, 0.5F, -1.175F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.5F, -14.25F, -9.65F));

        PartDefinition bone28 = bone24.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -17.5F, -9.0F));

        PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 40).addBox(1.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).mirror().addBox(-7.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition bone5 = bone6.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.245F, -36.7704F, -8.0F, 0.0F, -0.0044F, 0.0F));

        PartDefinition cube_r8 = bone5.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(33, 60).mirror().addBox(-0.55F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition bone2 = bone6.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(3.245F, -36.7704F, -8.0F, 0.0F, 0.0044F, 0.0F));

        PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(33, 60).addBox(-7.45F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 40).addBox(1.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).mirror().addBox(-7.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.245F, -36.7704F, -8.0F, 0.0F, -0.0044F, 0.0F));

        PartDefinition cube_r10 = bone8.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(33, 60).mirror().addBox(-0.55F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition bone9 = bone7.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(3.245F, -36.7704F, -8.0F, 0.0F, 0.0044F, 0.0F));

        PartDefinition cube_r11 = bone9.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(33, 60).addBox(-7.45F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition bone10 = bone7.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(0, 40).addBox(1.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).mirror().addBox(-7.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.245F, -36.7704F, -8.0F, 0.0F, -0.0044F, 0.0F));

        PartDefinition cube_r12 = bone11.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(33, 60).mirror().addBox(-0.55F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition bone12 = bone10.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(3.245F, -36.7704F, -8.0F, 0.0F, 0.0044F, 0.0F));

        PartDefinition cube_r13 = bone12.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(33, 60).addBox(-7.45F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition bone13 = bone10.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(0, 40).addBox(1.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).mirror().addBox(-7.25F, -40.0F, -8.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.245F, -36.7704F, -8.0F, 0.0F, -0.0044F, 0.0F));

        PartDefinition cube_r14 = bone14.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(33, 60).mirror().addBox(-0.55F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition bone15 = bone13.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(3.245F, -36.7704F, -8.0F, 0.0F, 0.0044F, 0.0F));

        PartDefinition cube_r15 = bone15.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(33, 60).addBox(-7.45F, 0.0F, 0.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.755F, -0.2296F, -1.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition pillars = partdefinition.addOrReplaceChild("pillars", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone3 = pillars.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(6.5F, -10.0F, -10.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 95).addBox(7.0F, -41.0F, -10.0F, 3.0F, 31.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone4 = pillars.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-10.5F, -10.0F, -10.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 95).mirror().addBox(-10.0F, -41.0F, -10.0F, 3.0F, 31.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone16 = pillars.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(0, 0).addBox(6.5F, -10.0F, 6.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 95).addBox(7.0F, -41.0F, 7.0F, 3.0F, 31.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone17 = pillars.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-10.5F, -10.0F, 6.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 95).mirror().addBox(-10.0F, -41.0F, 7.0F, 3.0F, 31.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone18 = partdefinition.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(98, 50).addBox(-7.5F, -34.0F, -9.5F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(98, 50).addBox(-7.5F, -34.0F, -9.5F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(98, 50).addBox(-7.5F, -34.0F, -9.5F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(98, 50).addBox(-7.5F, -34.0F, -9.5F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 25).addBox(-10.5F, -2.0F, -10.5F, 21.0F, 2.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.5F, -39.0F, -10.5F, 21.0F, 3.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(58, 49).addBox(-6.5F, -45.0F, -6.5F, 13.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(85, 0).addBox(-7.0F, -33.0F, -7.0F, 14.0F, 31.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        addMaterializationPart(partdefinition);
        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setDoorPosition(boolean open) {

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
        door_open.visible = open;
        door_closed.visible = !open;
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}
