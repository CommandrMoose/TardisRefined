package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class CastleShellModel extends ShellModel {

    private final ModelPart r_door;
    private final ModelPart l_door;
    private final ModelPart bone38;
    private final ModelPart bone8;
    private final ModelPart bone3;
    private final ModelPart bone10;
    private final ModelPart bone16;
    private final ModelPart bone32;
    private final ModelPart blackbox;
    private final ModelPart bb_main;
    private final ModelPart root;

    public CastleShellModel(ModelPart root) {
        super(root);
        this.r_door = root.getChild("r_door");
        this.l_door = root.getChild("l_door");
        this.bone38 = root.getChild("bone38");
        this.bone8 = root.getChild("bone8");
        this.bone3 = root.getChild("bone3");
        this.bone10 = root.getChild("bone10");
        this.bone16 = root.getChild("bone16");
        this.bone32 = root.getChild("bone32");
        this.blackbox = root.getChild("blackbox");
        this.bb_main = root.getChild("bb_main");
        this.root = root;
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition r_door = partdefinition.addOrReplaceChild("r_door", CubeListBuilder.create().texOffs(48, 46).mirror().addBox(-0.25F, -16.0F, -0.5F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 8.0F, -13.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition l_door = partdefinition.addOrReplaceChild("l_door", CubeListBuilder.create().texOffs(48, 46).addBox(-7.75F, -16.0F, -0.5F, 8.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 8.0F, -13.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone38 = partdefinition.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offset(9.0F, -12.0F, -13.0F));

        PartDefinition cube_r1 = bone38.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, -1.1781F, 0.0F));

        PartDefinition cube_r2 = bone38.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.25F, 20.0F, 0.0F, -0.3491F, 1.0036F, 0.0F));

        PartDefinition cube_r3 = bone38.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0F, 12.0F, -0.75F, 0.1309F, 2.0071F, 0.0F));

        PartDefinition cube_r4 = bone38.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, 0.0F, 0.0F, -0.2618F, 1.1781F, 0.0F));

        PartDefinition cube_r5 = bone38.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, 0.25F, 0.0F, -0.6109F, 0.3491F, 0.0F));

        PartDefinition cube_r6 = bone38.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 1.0F, 0.0F, -0.5236F, -0.2618F, 0.0F));

        PartDefinition cube_r7 = bone38.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 0.0F, -0.5236F, -0.0436F, 0.0F));

        PartDefinition cube_r8 = bone38.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(80, 73).addBox(-1.5F, -3.0F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, 1.0F, -0.5F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r9 = bone38.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(80, 67).addBox(-1.5F, -3.0F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.75F, 1.0F, -0.5F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r10 = bone38.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 1.25F, 0.0F, -1.0472F, -0.48F, 0.0F));

        PartDefinition cube_r11 = bone38.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 1.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create(), PartPose.offset(-21.0F, 34.5F, 5.75F));

        PartDefinition cube_r12 = bone39.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.25F, -0.5F, -0.6981F, 1.4835F, 0.0F));

        PartDefinition cube_r13 = bone39.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(80, 73).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6279F, 1.0782F, -0.6892F));

        PartDefinition cube_r14 = bone39.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.75F, 2.5F, -0.0873F, 1.4835F, 0.0F));

        PartDefinition cube_r15 = bone39.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 2.25F, 0.0F, -1.2217F, 1.4399F, 0.0F));

        PartDefinition cube_r16 = bone39.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.5F, -1.75F, -0.3491F, 1.0036F, 0.0F));

        PartDefinition cube_r17 = bone39.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -4.5F, -1.75F, 0.1309F, 1.3526F, 0.0F));

        PartDefinition bone40 = bone38.addOrReplaceChild("bone40", CubeListBuilder.create(), PartPose.offset(3.0F, 34.5F, 5.75F));

        PartDefinition cube_r18 = bone40.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 1.25F, -0.5F, -0.6981F, -1.4835F, 0.0F));

        PartDefinition cube_r19 = bone40.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(80, 67).mirror().addBox(-1.5F, -3.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6279F, -1.0782F, 0.6892F));

        PartDefinition cube_r20 = bone40.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -0.75F, 2.5F, -0.0873F, -1.4835F, 0.0F));

        PartDefinition cube_r21 = bone40.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 2.25F, 0.0F, -1.2217F, -1.4399F, 0.0F));

        PartDefinition cube_r22 = bone40.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -9.25F, -6.5F, -0.9599F, -0.8727F, 0.0F));

        PartDefinition cube_r23 = bone40.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -14.0F, -6.5F, 0.2618F, -1.2217F, 0.0F));

        PartDefinition cube_r24 = bone40.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -5.0F, -6.5F, -0.5672F, -0.3927F, 0.0F));

        PartDefinition cube_r25 = bone40.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.75F, -2.5F, -7.0F, 0.1309F, -0.9599F, 0.0F));

        PartDefinition cube_r26 = bone40.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -0.5F, -1.75F, -0.3491F, -1.0036F, 0.0F));

        PartDefinition cube_r27 = bone40.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(64, 32).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -4.5F, -1.75F, 0.1309F, -1.3526F, 0.0F));

        PartDefinition bone8 = partdefinition.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(35, 46).addBox(-10.5F, -36.0F, 7.0F, 3.0F, 36.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(35, 46).mirror().addBox(7.5F, -36.0F, 7.0F, 3.0F, 36.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(48, 80).addBox(-2.5F, -36.0F, 5.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 7).addBox(2.5F, -35.0F, 5.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).addBox(6.5F, -36.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).mirror().addBox(-10.5F, -36.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(33, 7).mirror().addBox(-6.5F, -35.0F, 5.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -20.0F));

        PartDefinition bone = bone8.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(33, 0).addBox(7.5F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 25).addBox(7.5F, -8.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).addBox(7.5F, -12.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 25).addBox(7.5F, -16.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 60).addBox(7.5F, -20.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 25).addBox(7.5F, -24.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 60).addBox(7.5F, -28.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 25).addBox(7.5F, -32.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone8.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(-11.5F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 25).mirror().addBox(-10.5F, -8.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(33, 0).mirror().addBox(-11.5F, -12.0F, 5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 25).mirror().addBox(-10.5F, -16.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(80, 60).mirror().addBox(-9.5F, -20.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 25).mirror().addBox(-10.5F, -24.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(80, 60).mirror().addBox(-9.5F, -28.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 25).mirror().addBox(-10.5F, -32.0F, 5.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(77, 0).addBox(-7.5F, -42.0F, -13.0F, 15.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -42.0F, -13.0F, 15.0F, 42.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -42.0F, -13.0F, 15.0F, 42.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 41).addBox(-5.5F, -32.0F, -14.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 54).addBox(-6.5F, -21.0F, -15.0F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone5.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(80, 73).addBox(0.5F, -25.0F, -6.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 67).addBox(-5.25F, -25.0F, -6.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 67).addBox(-1.25F, -23.0F, -6.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

        PartDefinition cube_r28 = bone28.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -21.0F, -6.0F, -0.6981F, -0.5236F, 0.0F));

        PartDefinition cube_r29 = bone28.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -21.0F, -6.0F, -0.6981F, -0.5236F, 0.0F));

        PartDefinition cube_r30 = bone28.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -21.0F, -6.0F, -0.6981F, 0.5672F, 0.0F));

        PartDefinition cube_r31 = bone28.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(80, 73).addBox(-1.5F, -3.0F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -21.5F, -6.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r32 = bone28.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -21.0F, -6.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -42.0F, -13.0F, 15.0F, 42.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -42.0F, -13.0F, 15.0F, 42.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 41).addBox(-5.5F, -32.0F, -14.0F, 11.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 54).addBox(-6.5F, -21.0F, -15.0F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone29 = bone7.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(80, 73).addBox(0.5F, -25.0F, -6.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 67).addBox(-5.25F, -25.0F, -6.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 67).addBox(-1.25F, -23.0F, -6.75F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

        PartDefinition cube_r33 = bone29.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -21.0F, -6.0F, -0.6981F, -0.5236F, 0.0F));

        PartDefinition cube_r34 = bone29.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -21.0F, -6.0F, -0.6981F, -0.5236F, 0.0F));

        PartDefinition cube_r35 = bone29.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -21.0F, -6.0F, -0.6981F, 0.5672F, 0.0F));

        PartDefinition cube_r36 = bone29.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(80, 73).addBox(-1.5F, -3.0F, -0.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -21.5F, -6.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r37 = bone29.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(64, 32).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -21.0F, -6.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone9 = bone7.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.5F, -42.0F, -13.0F, 15.0F, 42.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(74, 15).addBox(-8.5F, -42.0F, -14.725F, 17.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(74, 15).addBox(-8.5F, -42.0F, -14.725F, 17.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(74, 15).addBox(-8.5F, -42.0F, -14.725F, 17.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone13 = bone12.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(74, 15).addBox(-8.5F, -42.0F, -14.725F, 17.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(74, 15).addBox(-8.5F, -42.0F, -14.725F, 17.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(74, 15).addBox(-8.5F, -42.0F, -14.725F, 17.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone16 = partdefinition.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone22 = bone16.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(33, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -42.0F, -13.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r38 = bone22.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(64, 25).mirror().addBox(0.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, 0.4407F, 0.0F));

        PartDefinition cube_r39 = bone22.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(64, 25).addBox(-2.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.4407F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone17.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(33, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -42.0F, -13.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r40 = bone23.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(64, 25).addBox(-2.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.4407F, 0.0F));

        PartDefinition cube_r41 = bone23.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(64, 25).mirror().addBox(0.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, 0.4407F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(33, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -42.0F, -13.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r42 = bone19.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(64, 25).addBox(-2.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.4407F, 0.0F));

        PartDefinition cube_r43 = bone19.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(64, 25).mirror().addBox(0.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, 0.4407F, 0.0F));

        PartDefinition bone20 = bone18.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(33, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -42.0F, -13.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r44 = bone21.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(64, 25).addBox(-2.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.4407F, 0.0F));

        PartDefinition cube_r45 = bone21.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(64, 25).mirror().addBox(0.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, 0.4407F, 0.0F));

        PartDefinition bone24 = bone20.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(33, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -42.0F, -13.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r46 = bone25.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(64, 25).addBox(-2.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.4407F, 0.0F));

        PartDefinition cube_r47 = bone25.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(64, 25).mirror().addBox(0.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, 0.4407F, 0.0F));

        PartDefinition bone26 = bone24.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(33, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -42.0F, -13.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r48 = bone27.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(64, 25).addBox(-2.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 0.0F, 0.0F, 0.0F, -0.4407F, 0.0F));

        PartDefinition cube_r49 = bone27.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(64, 25).mirror().addBox(0.0F, 0.025F, 0.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, 0.4407F, 0.0F));

        PartDefinition bone32 = partdefinition.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(19, 30).addBox(-7.5F, -1.2F, -13.0F, 15.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(19, 30).addBox(-7.5F, -1.2F, -13.0F, 15.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(19, 30).addBox(-7.5F, -1.2F, -13.0F, 15.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(19, 30).addBox(-7.5F, -1.2F, -13.0F, 15.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(19, 30).addBox(-7.5F, -1.2F, -13.0F, 15.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = bone36.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(19, 30).addBox(-7.5F, -1.2F, -13.0F, 15.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition blackbox = partdefinition.addOrReplaceChild("blackbox", CubeListBuilder.create().texOffs(67, 46).mirror().addBox(-9.0F, -33.0F, -6.0F, 1.0F, 33.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(67, 46).addBox(8.0F, -33.0F, -6.0F, 1.0F, 33.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(33, 15).addBox(-8.0F, -34.0F, -9.0F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-8.0F, -33.0F, -1.0F, 16.0F, 33.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -4.25F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(74, 79).addBox(-3.0F, -53.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        addMaterializationPart(partdefinition);
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setDoorPosition(boolean open) {
        r_door.yRot = open ? (float) Math.toRadians(90) : (float) Math.toRadians(0);
        l_door.yRot = open ? (float) Math.toRadians(-90) : (float) Math.toRadians(0);
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}
