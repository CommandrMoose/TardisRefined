package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

import java.util.Calendar;

public class BigBenShellModel extends ShellModel {
    private final ModelPart root;
    private final ModelPart door;
    private final ModelPart sides;
    private final ModelPart bone9;
    private final ModelPart bone24;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone28;
    private final ModelPart bone15;
    private final ModelPart bone19;
    private final ModelPart bone27;
    private final ModelPart N_big_hand;
    private final ModelPart N_small_hand;
    private final ModelPart S_big_hand;
    private final ModelPart S_small_hand;
    private final ModelPart W_small_hand;
    private final ModelPart W_big_hand;
    private final ModelPart E_big_hand;
    private final ModelPart E_small_hand;
    private final ModelPart bb_main;

    public BigBenShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.door = root.getChild("door");
        this.sides = root.getChild("sides");
        this.bone9 = root.getChild("bone9");
        this.bone24 = root.getChild("bone24");
        this.bone4 = root.getChild("bone4");
        this.bone5 = root.getChild("bone5");
        this.bone28 = root.getChild("bone28");
        this.bone15 = root.getChild("bone15");
        this.bone19 = root.getChild("bone19");
        this.bone27 = root.getChild("bone27");
        this.N_big_hand = root.getChild("N_big_hand");
        this.N_small_hand = root.getChild("N_small_hand");
        this.S_big_hand = root.getChild("S_big_hand");
        this.S_small_hand = root.getChild("S_small_hand");
        this.W_small_hand = root.getChild("W_small_hand");
        this.W_big_hand = root.getChild("W_big_hand");
        this.E_big_hand = root.getChild("E_big_hand");
        this.E_small_hand = root.getChild("E_small_hand");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(62, 77).addBox(-14.025F, -17.0F, 0.0F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(93, 77).addBox(-14.025F, -17.0F, 0.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.025F, 7.0F, -7.5F));

        PartDefinition sides = partdefinition.addOrReplaceChild("sides", CubeListBuilder.create().texOffs(31, 77).addBox(-7.0F, -34.0F, -8.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 67).addBox(-7.0F, -34.0F, -9.4F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone13 = sides.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(31, 77).addBox(-7.0F, -34.0F, -8.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 67).addBox(-7.0F, -34.0F, -9.4F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(31, 77).addBox(-7.0F, -34.0F, -8.5F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 67).addBox(-7.0F, -34.0F, -9.4F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone9 = partdefinition.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r1 = bone9.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(107, 0).addBox(-5.0F, -11.8377F, -1.7322F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -60.0F, -3.25F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r2 = bone10.addOrReplaceChild("root_r2", CubeListBuilder.create().texOffs(107, 0).addBox(-5.0F, -11.8377F, -1.7322F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -60.0F, -3.25F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r3 = bone11.addOrReplaceChild("root_r3", CubeListBuilder.create().texOffs(107, 0).addBox(-5.0F, -11.8377F, -1.7322F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -60.0F, -3.25F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r4 = bone12.addOrReplaceChild("root_r4", CubeListBuilder.create().texOffs(107, 0).addBox(-5.0F, -11.8377F, -1.7322F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -60.0F, -3.25F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone24 = partdefinition.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r5 = bone24.addOrReplaceChild("root_r5", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition root_r6 = bone24.addOrReplaceChild("root_r6", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone23 = bone24.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r7 = bone23.addOrReplaceChild("root_r7", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition root_r8 = bone23.addOrReplaceChild("root_r8", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone25 = bone23.addOrReplaceChild("bone25", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r9 = bone25.addOrReplaceChild("root_r9", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition root_r10 = bone25.addOrReplaceChild("root_r10", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition root_r11 = bone26.addOrReplaceChild("root_r11", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition root_r12 = bone26.addOrReplaceChild("root_r12", CubeListBuilder.create().texOffs(106, 55).addBox(-7.0F, -3.0F, 0.0F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, -10.0F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(52, 113).addBox(6.0F, -38.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition bone = bone4.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(35, 113).addBox(6.5F, -18.0F, -9.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(13, 103).addBox(7.0F, -34.0F, -9.0F, 3.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 113).addBox(7.5F, -34.0F, -8.5F, 2.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone4.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 47).addBox(6.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(6.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone3 = bone4.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(110, 21).addBox(6.0F, -47.0F, -10.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(73, 113).addBox(6.0F, -52.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(52, 113).mirror().addBox(-11.0F, -38.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(35, 113).mirror().addBox(-10.5F, -18.0F, -9.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 103).mirror().addBox(-10.0F, -34.0F, -9.0F, 3.0F, 34.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 113).mirror().addBox(-9.5F, -34.0F, -8.5F, 2.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone7 = bone5.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(-10.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 23).mirror().addBox(-10.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone8 = bone5.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(110, 21).mirror().addBox(-11.0F, -47.0F, -10.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 113).mirror().addBox(-11.0F, -52.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone28 = partdefinition.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(106, 41).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(106, 41).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(106, 41).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone15 = partdefinition.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(52, 113).mirror().addBox(-11.0F, -38.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(35, 113).mirror().addBox(-10.5F, -18.0F, -9.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(13, 103).mirror().addBox(-10.0F, -34.0F, -9.0F, 3.0F, 34.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 113).mirror().addBox(-9.5F, -34.0F, -8.5F, 2.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone17 = bone15.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(-10.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 23).mirror().addBox(-10.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone18 = bone15.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(110, 21).mirror().addBox(-11.0F, -47.0F, -10.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 113).mirror().addBox(-11.0F, -52.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone19 = partdefinition.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(52, 113).addBox(6.0F, -38.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(35, 113).addBox(6.5F, -18.0F, -9.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(13, 103).addBox(7.0F, -34.0F, -9.0F, 3.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 113).addBox(7.5F, -34.0F, -8.5F, 2.0F, 34.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone21 = bone19.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(0, 47).addBox(6.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(6.0F, -47.0F, -9.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone22 = bone19.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(110, 21).addBox(6.0F, -47.0F, -10.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(73, 113).addBox(6.0F, -52.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone27 = partdefinition.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(106, 41).addBox(-6.0F, -6.0F, 0.0F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, -8.0F));

        PartDefinition N_big_hand = partdefinition.addOrReplaceChild("N_big_hand", CubeListBuilder.create().texOffs(55, 47).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, -8.0F));

        PartDefinition N_small_hand = partdefinition.addOrReplaceChild("N_small_hand", CubeListBuilder.create().texOffs(55, 56).addBox(-0.5F, -3.0F, -0.525F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, -8.0F));

        PartDefinition S_big_hand = partdefinition.addOrReplaceChild("S_big_hand", CubeListBuilder.create().texOffs(55, 47).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 8.0F));

        PartDefinition S_small_hand = partdefinition.addOrReplaceChild("S_small_hand", CubeListBuilder.create().texOffs(55, 56).addBox(-0.5F, -3.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 8.0F));

        PartDefinition W_small_hand = partdefinition.addOrReplaceChild("W_small_hand", CubeListBuilder.create().texOffs(60, 56).addBox(-0.5F, -3.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -19.0F, 0.0F));

        PartDefinition W_big_hand = partdefinition.addOrReplaceChild("W_big_hand", CubeListBuilder.create().texOffs(60, 47).addBox(-0.525F, -5.0F, -0.475F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -19.0F, 0.0F));

        PartDefinition E_big_hand = partdefinition.addOrReplaceChild("E_big_hand", CubeListBuilder.create().texOffs(60, 47).mirror().addBox(-0.475F, -5.0F, -0.475F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -19.0F, 0.0F));

        PartDefinition E_small_hand = partdefinition.addOrReplaceChild("E_small_hand", CubeListBuilder.create().texOffs(60, 56).mirror().addBox(-0.5F, -3.0F, -0.475F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -19.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 23).addBox(-10.0F, -37.0F, -10.0F, 20.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(-9.0F, -34.5F, -9.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(-0.475F))
                .texOffs(0, 0).addBox(-10.5F, -0.1F, -10.5F, 21.0F, 1.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-10.0F, -52.0F, -10.0F, 20.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(57, 51).addBox(-8.0F, -60.0F, -8.0F, 16.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -74.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(61, 23).addBox(-8.0F, -61.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-7.0F, -66.0F, -7.0F, 14.0F, 6.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-7.0F, -59.0F, -7.0F, 14.0F, 6.0F, 14.0F, new CubeDeformation(3.0F))
                .texOffs(0, 103).addBox(-8.5F, -34.0F, 5.5F, 3.0F, 34.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 103).mirror().addBox(5.5F, -34.0F, 5.5F, 3.0F, 34.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

        addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setDoorPosition(boolean open) {
        this.door.yRot = (open) ? -275f : 0;

    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, getCurrentAlpha());
    }

    @Override
    public void handleSpecialAnimation(GlobalShellBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        double hourHandDegree = (hour + minute / 60.0) / 12 * 360;
        double minuteHandDegree = minute / 60.0 * 360;

        //North
        N_big_hand.zRot = (float) Math.toRadians(minuteHandDegree);
        N_small_hand.zRot = (float) Math.toRadians(hourHandDegree);

        //East
        E_big_hand.xRot = (float) Math.toRadians(minuteHandDegree);
        E_small_hand.xRot = (float) Math.toRadians(hourHandDegree);

        //West
        W_big_hand.xRot = (float) Math.toRadians(-minuteHandDegree);
        W_small_hand.xRot = (float) Math.toRadians(-hourHandDegree);

        //South
        S_big_hand.zRot = (float) Math.toRadians(-minuteHandDegree);
        S_small_hand.zRot = (float) Math.toRadians(-hourHandDegree);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        double hourHandDegree = (hour + minute / 60.0) / 12 * 360;
        double minuteHandDegree = minute / 60.0 * 360;

        //North
        N_big_hand.zRot = (float) Math.toRadians(minuteHandDegree);
        N_small_hand.zRot = (float) Math.toRadians(hourHandDegree);

        //East
        E_big_hand.xRot = (float) Math.toRadians(minuteHandDegree);
        E_small_hand.xRot = (float) Math.toRadians(hourHandDegree);

        //West
        W_big_hand.xRot = (float) Math.toRadians(-minuteHandDegree);
        W_small_hand.xRot = (float) Math.toRadians(-hourHandDegree);

        //South
        S_big_hand.zRot = (float) Math.toRadians(-minuteHandDegree);
        S_small_hand.zRot = (float) Math.toRadians(-hourHandDegree);

        door.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        sides.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone24.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone28.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone15.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone19.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bone27.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        N_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        N_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        S_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        S_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        W_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        W_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        E_big_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        E_small_hand.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}