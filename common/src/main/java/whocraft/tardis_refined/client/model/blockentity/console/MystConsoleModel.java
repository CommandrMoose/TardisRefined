package whocraft.tardis_refined.client.model.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.frame.tardis.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class MystConsoleModel extends HierarchicalModel implements ConsoleUnit {

    public static final AnimationDefinition MODEL_ROTOR_LOOP = AnimationDefinition.Builder.withLength(4f).looping()
            .addAnimation("rotor",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, -4f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("rotor",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.degreeVec(0f, 240f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    private static final ResourceLocation MYST_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/myst/myst_console.png");
    private final ModelPart root;
    private final ModelPart base_console;
    private final ModelPart controls;
    private final ModelPart rotor;
    private final ModelPart throttle_control;
    private final ModelPart handbrake;

    public MystConsoleModel(ModelPart root) {
        this.root = root;
        this.base_console = root.getChild("base_console");
        this.controls = root.getChild("controls");
        this.rotor = root.getChild("rotor");
        this.throttle_control = controls.getChild("south").getChild("bone120").getChild("throttle_control");
        this.handbrake = Frame.findPart(this, "door_control");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base_console = partdefinition.addOrReplaceChild("base_console", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone25 = base_console.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(27, 94).addBox(-1.0F, -3.0F, -22.73F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(27, 94).addBox(-1.0F, -3.0F, -22.73F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(27, 94).addBox(-1.0F, -3.0F, -22.73F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(27, 94).addBox(-1.0F, -3.0F, -22.73F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(27, 94).addBox(-1.0F, -3.0F, -22.73F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(27, 94).addBox(-1.0F, -3.0F, -22.73F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone7 = base_console.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, -8.0F, -8.73F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, -8.0F, -8.73F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, -8.0F, -8.73F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, -8.0F, -8.73F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, -8.0F, -8.73F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, -8.0F, -8.73F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone86 = base_console.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(25, 61).addBox(0.0F, -13.0F, -10.23F, 1.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create().texOffs(25, 61).addBox(0.0F, -14.0F, -10.23F, 1.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(25, 61).addBox(0.0F, -13.0F, -10.23F, 1.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(25, 61).addBox(0.0F, -14.0F, -10.23F, 1.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(25, 61).addBox(0.0F, -13.0F, -10.23F, 1.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91 = bone90.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(25, 61).addBox(0.0F, -14.0F, -10.23F, 1.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone32 = base_console.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone32_r1 = bone32.addOrReplaceChild("bone32_r1", CubeListBuilder.create().texOffs(35, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone33_r1 = bone33.addOrReplaceChild("bone33_r1", CubeListBuilder.create().texOffs(35, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34_r1 = bone34.addOrReplaceChild("bone34_r1", CubeListBuilder.create().texOffs(35, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35_r1 = bone35.addOrReplaceChild("bone35_r1", CubeListBuilder.create().texOffs(35, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36_r1 = bone36.addOrReplaceChild("bone36_r1", CubeListBuilder.create().texOffs(35, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone37 = bone36.addOrReplaceChild("bone37", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37_r1 = bone37.addOrReplaceChild("bone37_r1", CubeListBuilder.create().texOffs(35, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone80 = base_console.addOrReplaceChild("bone80", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone80_r1 = bone80.addOrReplaceChild("bone80_r1", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, 7.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone81_r1 = bone81.addOrReplaceChild("bone81_r1", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, 7.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82_r1 = bone82.addOrReplaceChild("bone82_r1", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, 7.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone83 = bone82.addOrReplaceChild("bone83", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone83_r1 = bone83.addOrReplaceChild("bone83_r1", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, 7.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84_r1 = bone84.addOrReplaceChild("bone84_r1", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, 7.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone85 = bone84.addOrReplaceChild("bone85", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone85_r1 = bone85.addOrReplaceChild("bone85_r1", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, 7.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -22.73F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone50 = base_console.addOrReplaceChild("bone50", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -16.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone50_r1 = bone50.addOrReplaceChild("bone50_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.5F, -3.0F, 1.0F, 11.0F, 16.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.75F, 3.0F, -22.73F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone75 = bone50.addOrReplaceChild("bone75", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75_r1 = bone75.addOrReplaceChild("bone75_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.5F, -3.0F, 1.0F, 11.0F, 16.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.75F, 3.0F, -22.73F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone76 = bone75.addOrReplaceChild("bone76", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone76_r1 = bone76.addOrReplaceChild("bone76_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.5F, -3.0F, 1.0F, 11.0F, 16.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.75F, 3.0F, -22.73F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone77_r1 = bone77.addOrReplaceChild("bone77_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.5F, -3.0F, 1.0F, 11.0F, 16.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.75F, 3.0F, -22.73F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone78_r1 = bone78.addOrReplaceChild("bone78_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.5F, -3.0F, 1.0F, 11.0F, 16.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.75F, 3.0F, -22.73F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone79 = bone78.addOrReplaceChild("bone79", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone79_r1 = bone79.addOrReplaceChild("bone79_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.5F, -3.0F, 1.0F, 11.0F, 16.0F, new CubeDeformation(-0.225F)), PartPose.offsetAndRotation(0.75F, 3.0F, -22.73F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone38 = base_console.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone38_r1 = bone38.addOrReplaceChild("bone38_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, 0.25F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(63, 24).mirror().addBox(-9.0F, -0.475F, 1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 15).addBox(-20.0F, -0.5F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone39_r1 = bone38.addOrReplaceChild("bone39_r1", CubeListBuilder.create().texOffs(19, 53).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, -19.175F, 0.8727F, 0.0F, 0.0F));

        PartDefinition bone39_r2 = bone38.addOrReplaceChild("bone39_r2", CubeListBuilder.create().texOffs(12, 71).addBox(-12.0F, -2.0F, 3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -2.3433F, -19.0458F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone39_r3 = bone39.addOrReplaceChild("bone39_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, 0.25F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-20.0F, -0.5F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40_r1 = bone40.addOrReplaceChild("bone40_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, 0.25F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-20.0F, -0.5F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone39_r4 = bone40.addOrReplaceChild("bone39_r4", CubeListBuilder.create().texOffs(19, 53).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, -19.175F, 0.8727F, 0.0F, 0.0F));

        PartDefinition bone39_r5 = bone40.addOrReplaceChild("bone39_r5", CubeListBuilder.create().texOffs(12, 71).addBox(-12.0F, -2.0F, 3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, -2.3433F, -19.0458F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41_r1 = bone41.addOrReplaceChild("bone41_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, 0.25F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(63, 24).addBox(-16.0F, -0.475F, 1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-20.0F, -0.5F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42_r1 = bone42.addOrReplaceChild("bone42_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, 0.25F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(63, 24).mirror().addBox(-9.0F, -0.475F, 1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(63, 24).addBox(-16.0F, -0.475F, 1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-20.0F, -0.5F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone43 = bone42.addOrReplaceChild("bone43", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone43_r1 = bone43.addOrReplaceChild("bone43_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, 0.25F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(-20.0F, -0.5F, 0.0F, 21.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone19 = base_console.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(56, 0).addBox(-10.5F, -3.0F, -20.175F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(56, 0).addBox(-10.5F, -3.0F, -20.175F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(56, 0).addBox(-10.5F, -3.0F, -20.175F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(56, 0).addBox(-10.5F, -3.0F, -20.175F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(56, 0).addBox(-10.5F, -3.0F, -20.175F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(56, 0).addBox(-10.5F, -3.0F, -20.175F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone92 = base_console.addOrReplaceChild("bone92", CubeListBuilder.create(), PartPose.offset(0.0F, -19.0F, 0.0F));

        PartDefinition bone92_r1 = bone92.addOrReplaceChild("bone92_r1", CubeListBuilder.create().texOffs(19, 43).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone93_r1 = bone93.addOrReplaceChild("bone93_r1", CubeListBuilder.create().texOffs(19, 43).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone94_r1 = bone94.addOrReplaceChild("bone94_r1", CubeListBuilder.create().texOffs(19, 43).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone95_r1 = bone95.addOrReplaceChild("bone95_r1", CubeListBuilder.create().texOffs(19, 43).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone96_r1 = bone96.addOrReplaceChild("bone96_r1", CubeListBuilder.create().texOffs(19, 43).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone97 = bone96.addOrReplaceChild("bone97", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97_r1 = bone97.addOrReplaceChild("bone97_r1", CubeListBuilder.create().texOffs(19, 43).addBox(-3.5F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone114 = base_console.addOrReplaceChild("bone114", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -19.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone93_r2 = bone114.addOrReplaceChild("bone93_r2", CubeListBuilder.create().texOffs(41, 98).addBox(-1.0F, 0.025F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone121 = bone114.addOrReplaceChild("bone121", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone94_r2 = bone121.addOrReplaceChild("bone94_r2", CubeListBuilder.create().texOffs(41, 98).addBox(-1.0F, 0.025F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone126 = bone121.addOrReplaceChild("bone126", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone95_r2 = bone126.addOrReplaceChild("bone95_r2", CubeListBuilder.create().texOffs(41, 98).addBox(-1.0F, 0.025F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone129 = bone126.addOrReplaceChild("bone129", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone96_r2 = bone129.addOrReplaceChild("bone96_r2", CubeListBuilder.create().texOffs(41, 98).addBox(-1.0F, 0.025F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone130 = bone129.addOrReplaceChild("bone130", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97_r2 = bone130.addOrReplaceChild("bone97_r2", CubeListBuilder.create().texOffs(41, 98).addBox(-1.0F, 0.025F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone131 = bone130.addOrReplaceChild("bone131", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone98_r1 = bone131.addOrReplaceChild("bone98_r1", CubeListBuilder.create().texOffs(41, 98).addBox(-1.0F, 0.025F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -7.925F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone51 = base_console.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(0, 30).addBox(-10.5F, -1.0F, -18.175F, 21.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(0, 30).addBox(-10.5F, -1.0F, -18.175F, 21.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(0, 30).addBox(-10.5F, -1.0F, -18.175F, 21.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(0, 30).addBox(-10.5F, -1.0F, -18.175F, 21.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone55 = bone54.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(0, 30).addBox(-10.5F, -1.0F, -18.175F, 21.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone74 = bone55.addOrReplaceChild("bone74", CubeListBuilder.create().texOffs(0, 30).addBox(-10.5F, -1.0F, -18.175F, 21.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone44 = base_console.addOrReplaceChild("bone44", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone44_r1 = bone44.addOrReplaceChild("bone44_r1", CubeListBuilder.create().texOffs(56, 6).addBox(-9.5F, 0.0F, -3.0F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.175F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone45_r1 = bone45.addOrReplaceChild("bone45_r1", CubeListBuilder.create().texOffs(56, 6).addBox(-9.5F, 0.0F, -3.0F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.175F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46_r1 = bone46.addOrReplaceChild("bone46_r1", CubeListBuilder.create().texOffs(56, 6).addBox(-9.5F, 0.0F, -3.0F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.175F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47_r1 = bone47.addOrReplaceChild("bone47_r1", CubeListBuilder.create().texOffs(56, 6).addBox(-9.5F, 0.0F, -3.0F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.175F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48_r1 = bone48.addOrReplaceChild("bone48_r1", CubeListBuilder.create().texOffs(56, 6).addBox(-9.5F, 0.0F, -3.0F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.175F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone49 = bone48.addOrReplaceChild("bone49", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49_r1 = bone49.addOrReplaceChild("bone49_r1", CubeListBuilder.create().texOffs(56, 6).addBox(-9.5F, 0.0F, -3.0F, 19.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.175F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone13 = base_console.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone62 = bone13.addOrReplaceChild("bone62", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.75F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone62_r1 = bone62.addOrReplaceChild("bone62_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.75F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone15_r1 = bone15.addOrReplaceChild("bone15_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone16 = bone14.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.75F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone17_r1 = bone17.addOrReplaceChild("bone17_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone18 = bone16.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone56 = bone18.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.75F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone56_r1 = bone56.addOrReplaceChild("bone56_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone57 = bone18.addOrReplaceChild("bone57", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58 = bone57.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.75F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone58_r1 = bone58.addOrReplaceChild("bone58_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone59 = bone57.addOrReplaceChild("bone59", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = bone59.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -10.75F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone60_r1 = bone60.addOrReplaceChild("bone60_r1", CubeListBuilder.create().texOffs(0, 43).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone = base_console.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(56, 33).addBox(-4.5F, -3.0F, -9.8F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(56, 33).addBox(-4.5F, -3.0F, -9.8F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(56, 33).addBox(-4.5F, -3.0F, -9.8F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(56, 33).addBox(-4.5F, -3.0F, -9.8F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(56, 33).addBox(-4.5F, -3.0F, -9.8F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(56, 33).addBox(-4.5F, -3.0F, -9.8F, 9.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone61 = base_console.addOrReplaceChild("bone61", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition bone68 = bone61.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(67, 68).addBox(-4.5F, -11.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.3F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone68_r1 = bone68.addOrReplaceChild("bone68_r1", CubeListBuilder.create().texOffs(56, 47).addBox(-6.5F, -5.5F, 0.0F, 11.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -0.65F, 0.5672F, 0.0F, 0.0F));

        PartDefinition bone63 = bone61.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone69 = bone63.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(67, 68).addBox(-4.5F, -11.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.3F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone69_r1 = bone69.addOrReplaceChild("bone69_r1", CubeListBuilder.create().texOffs(56, 47).addBox(-6.5F, -5.5F, 0.0F, 11.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -0.65F, 0.5672F, 0.0F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create().texOffs(67, 68).addBox(-4.5F, -11.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.3F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone65_r1 = bone65.addOrReplaceChild("bone65_r1", CubeListBuilder.create().texOffs(56, 47).addBox(-6.5F, -5.5F, 0.0F, 11.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -0.65F, 0.5672F, 0.0F, 0.0F));

        PartDefinition bone66 = bone64.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone67 = bone66.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(67, 68).addBox(-4.5F, -11.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.3F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone67_r1 = bone67.addOrReplaceChild("bone67_r1", CubeListBuilder.create().texOffs(56, 47).addBox(-6.5F, -5.5F, 0.0F, 11.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -0.65F, 0.5672F, 0.0F, 0.0F));

        PartDefinition bone70 = bone66.addOrReplaceChild("bone70", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(67, 68).addBox(-4.5F, -11.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.3F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone71_r1 = bone71.addOrReplaceChild("bone71_r1", CubeListBuilder.create().texOffs(56, 47).addBox(-6.5F, -5.5F, 0.0F, 11.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -0.65F, 0.5672F, 0.0F, 0.0F));

        PartDefinition bone72 = bone70.addOrReplaceChild("bone72", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone73 = bone72.addOrReplaceChild("bone73", CubeListBuilder.create().texOffs(67, 68).addBox(-4.5F, -11.0F, 0.0F, 9.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -9.3F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone73_r1 = bone73.addOrReplaceChild("bone73_r1", CubeListBuilder.create().texOffs(56, 47).addBox(-6.5F, -5.5F, 0.0F, 11.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -5.0F, -0.65F, 0.5672F, 0.0F, 0.0F));

        PartDefinition controls = partdefinition.addOrReplaceChild("controls", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition north = controls.addOrReplaceChild("north", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition north_r1 = north.addOrReplaceChild("north_r1", CubeListBuilder.create().texOffs(74, 88).mirror().addBox(-1.0F, -9.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -3.5F, -10.175F, 0.0F, 0.4363F, 0.0F));

        PartDefinition north_r2 = north.addOrReplaceChild("north_r2", CubeListBuilder.create().texOffs(74, 88).addBox(-1.0F, -9.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -3.5F, -10.175F, 0.0F, -0.4363F, 0.0F));

        PartDefinition rotate_control = north.addOrReplaceChild("rotate_control", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.0F, -11.575F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone114_r1 = rotate_control.addOrReplaceChild("bone114_r1", CubeListBuilder.create().texOffs(0, 91).addBox(-1.5F, -2.5F, -0.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.75F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bone110 = north.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(44, 84).addBox(-16.0F, -3.5F, 1.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(85, 30).addBox(-6.5F, -0.6F, 1.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(19, 85).addBox(-6.25F, -0.7F, 1.75F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(62, 93).addBox(-10.5F, -2.25F, 2.75F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(42, 43).addBox(-15.0F, -3.75F, 2.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition north_r3 = bone110.addOrReplaceChild("north_r3", CubeListBuilder.create().texOffs(88, 70).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -3.5F, 3.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition north_r4 = bone110.addOrReplaceChild("north_r4", CubeListBuilder.create().texOffs(94, 93).addBox(-1.25F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -2.25F, 5.75F, -0.3054F, 0.0F, 0.0F));

        PartDefinition increment_control = bone110.addOrReplaceChild("increment_control", CubeListBuilder.create().texOffs(77, 97).addBox(-1.25F, -1.25F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -1.1F, 2.75F, 0.0F, 0.48F, 0.0F));

        PartDefinition bone127 = bone110.addOrReplaceChild("bone127", CubeListBuilder.create().texOffs(57, 84).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(95, 75).addBox(-0.65F, 0.25F, -1.35F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-9.5F, -2.75F, 4.25F, 0.0F, -0.6109F, 0.0F));

        PartDefinition south = controls.addOrReplaceChild("south", CubeListBuilder.create().texOffs(0, 71).addBox(-1.0F, -11.75F, -13.175F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(72, 97).addBox(0.5F, -9.75F, -12.675F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone120 = south.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(54, 30).addBox(-5.0F, -2.6F, 1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 84).addBox(-6.0F, -0.6F, 0.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(85, 36).addBox(0.5F, -1.0F, 0.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(82, 75).addBox(0.0F, -0.6F, -0.5F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone120_r1 = bone120.addOrReplaceChild("bone120_r1", CubeListBuilder.create().texOffs(82, 11).addBox(-2.5F, -0.45F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(81, 53).mirror().addBox(-2.5F, -0.35F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.5F, -0.25F, 3.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone120_r2 = bone120.addOrReplaceChild("bone120_r2", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -1.0F, 2.5F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone120_r3 = bone120.addOrReplaceChild("bone120_r3", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.325F, -0.5F, 3.75F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone120_r4 = bone120.addOrReplaceChild("bone120_r4", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -1.0F, 1.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone120_r5 = bone120.addOrReplaceChild("bone120_r5", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -1.0F, 4.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition throttle_control = bone120.addOrReplaceChild("throttle_control", CubeListBuilder.create().texOffs(0, 56).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 91).addBox(-1.5F, -2.75F, -0.75F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-4.0F, -1.6F, 3.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition bone122 = bone120.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(34, 53).addBox(-1.25F, -1.25F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.575F, -0.85F, 1.925F, 0.0F, 0.48F, 0.0F));

        PartDefinition bone115 = south.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(83, 93).addBox(0.0F, -1.0F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -12.175F, 0.0F, 0.6545F, 0.0F));

        PartDefinition bone116 = south.addOrReplaceChild("bone116", CubeListBuilder.create(), PartPose.offsetAndRotation(1.75F, -9.5F, -12.175F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone116_r1 = bone116.addOrReplaceChild("bone116_r1", CubeListBuilder.create().texOffs(32, 87).addBox(0.0F, -1.25F, -4.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition south_left = controls.addOrReplaceChild("south_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone125 = south_left.addOrReplaceChild("bone125", CubeListBuilder.create().texOffs(82, 24).addBox(4.0F, -0.75F, 0.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(82, 24).addBox(-5.75F, -0.75F, 3.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(54, 37).addBox(-5.25F, -1.75F, 4.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 24).addBox(-0.5F, -0.6F, 7.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(51, 93).addBox(0.5F, -1.6F, 8.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 15).addBox(0.75F, -4.6F, 10.75F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 84).addBox(5.0F, -2.75F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(89, 18).addBox(-0.5F, -4.75F, 4.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 97).addBox(0.25F, -2.525F, 1.35F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 84).addBox(5.5F, -3.75F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 88).addBox(4.5F, -1.75F, 1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone123_r1 = bone125.addOrReplaceChild("bone123_r1", CubeListBuilder.create().texOffs(97, 52).addBox(0.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -2.5F, 3.9F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bone124_r1 = bone125.addOrReplaceChild("bone124_r1", CubeListBuilder.create().texOffs(0, 43).addBox(0.0F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.1F, 9.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition door_control = bone125.addOrReplaceChild("door_control", CubeListBuilder.create().texOffs(88, 65).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 47).addBox(-1.5F, -0.5F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -1.75F, 5.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone124 = bone125.addOrReplaceChild("bone124", CubeListBuilder.create().texOffs(19, 43).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -4.5F, 3.975F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone128 = south_left.addOrReplaceChild("bone128", CubeListBuilder.create().texOffs(56, 24).addBox(-0.5F, -1.0F, 1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.5F, -19.175F, 0.8727F, 0.0F, 0.0F));

        PartDefinition north_left = controls.addOrReplaceChild("north_left", CubeListBuilder.create().texOffs(83, 83).addBox(-3.5F, -14.0F, -11.175F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition random_control = north_left.addOrReplaceChild("random_control", CubeListBuilder.create().texOffs(78, 82).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, 1.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(59, 61).addBox(-1.5F, 4.25F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -13.5F, -11.175F));

        PartDefinition bone31 = north_left.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(82, 47).addBox(-10.0F, -1.5F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 96).addBox(-11.0F, -1.025F, 4.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 47).addBox(-8.5F, -1.5F, 2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(69, 11).addBox(-11.75F, -1.1F, 2.5F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 47).mirror().addBox(-11.5F, -1.5F, 2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(95, 43).addBox(-16.0F, -0.5F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(59, 66).addBox(-6.0F, -0.85F, 5.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(59, 66).addBox(-6.0F, -0.85F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone31_r1 = bone31.addOrReplaceChild("bone31_r1", CubeListBuilder.create().texOffs(81, 53).mirror().addBox(-2.5F, -0.35F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(38, 61).mirror().addBox(-1.5F, -0.45F, -1.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.5F, -0.25F, 4.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone31_r2 = bone31.addOrReplaceChild("bone31_r2", CubeListBuilder.create().texOffs(38, 61).addBox(-2.5F, -0.35F, -1.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(81, 53).addBox(-2.5F, -0.25F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.5F, -0.35F, 4.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone31_r3 = bone31.addOrReplaceChild("bone31_r3", CubeListBuilder.create().texOffs(39, 85).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.25F, -1.025F, 5.25F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone31_r4 = bone31.addOrReplaceChild("bone31_r4", CubeListBuilder.create().texOffs(62, 81).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -0.5F, 4.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone117 = bone31.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(34, 53).addBox(-1.25F, -1.25F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -1.1F, 2.25F, 0.0F, 0.48F, 0.0F));

        PartDefinition bone112 = bone31.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(36, 97).addBox(-0.5F, -3.25F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 97).addBox(-1.0F, -4.25F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -0.35F, 4.0F, -0.7418F, 0.0F, 0.0F));

        PartDefinition bone113 = bone31.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(82, 18).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -1.1F, 5.75F, -0.7418F, 0.0F, 0.0F));

        PartDefinition south_right = controls.addOrReplaceChild("south_right", CubeListBuilder.create().texOffs(81, 60).addBox(-3.5F, -3.35F, -20.675F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 77).addBox(-2.75F, -3.6F, -20.425F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition south_right_r1 = south_right.addOrReplaceChild("south_right_r1", CubeListBuilder.create().texOffs(19, 48).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.35F, -20.175F, 0.7418F, 0.0F, 0.0F));

        PartDefinition bone111 = south_right.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(95, 36).addBox(-4.0F, -0.85F, 1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(51, 61).addBox(-3.5F, -1.6F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 93).addBox(-17.25F, -0.7F, 0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone111_r1 = bone111.addOrReplaceChild("bone111_r1", CubeListBuilder.create().texOffs(81, 53).addBox(-4.25F, -0.5F, -2.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, -0.1F, 3.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition monitor_control = bone111.addOrReplaceChild("monitor_control", CubeListBuilder.create().texOffs(56, 15).addBox(-14.0F, -0.6F, 2.5F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition south_right2 = controls.addOrReplaceChild("south_right2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone118 = south_right2.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(54, 30).addBox(-5.0F, -2.6F, 1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(64, 58).addBox(-15.0F, -0.6F, 0.5F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(85, 47).addBox(-11.5F, -0.6F, 8.75F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(95, 24).addBox(-13.25F, -0.625F, 5.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 53).addBox(-13.25F, -0.625F, 1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(11, 56).addBox(-14.5F, -1.35F, 7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 56).addBox(-14.5F, -1.35F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 56).addBox(-14.5F, -1.35F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(85, 36).addBox(-10.5F, -1.0F, 1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(82, 75).addBox(-11.0F, -0.6F, 0.5F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -2.5F, -19.175F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone118_r1 = bone118.addOrReplaceChild("bone118_r1", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.75F, -1.0F, 3.5F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone118_r2 = bone118.addOrReplaceChild("bone118_r2", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.75F, -1.0F, 5.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone118_r3 = bone118.addOrReplaceChild("bone118_r3", CubeListBuilder.create().texOffs(10, 43).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.75F, -1.0F, 2.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone118_r4 = bone118.addOrReplaceChild("bone118_r4", CubeListBuilder.create().texOffs(38, 67).addBox(-1.0F, 0.0F, -0.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.25F, -0.6F, 5.5F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone123 = bone118.addOrReplaceChild("bone123", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -0.6F, 10.25F, 0.0F, -0.4363F, 0.0F));

        PartDefinition Z_control = bone118.addOrReplaceChild("Z_control", CubeListBuilder.create().texOffs(34, 53).addBox(-1.3F, -1.25F, 0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-14.025F, -1.35F, 1.5F, 0.0F, 0.829F, 0.0F));

        PartDefinition Y_control = bone118.addOrReplaceChild("Y_control", CubeListBuilder.create().texOffs(34, 53).addBox(-1.3F, -1.25F, 0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-14.025F, -1.35F, 4.5F, 0.0F, 0.1745F, 0.0F));

        PartDefinition X_control = bone118.addOrReplaceChild("X_control", CubeListBuilder.create().texOffs(34, 53).addBox(-1.3F, -1.25F, 0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-14.025F, -1.35F, 7.5F, 0.0F, -0.3927F, 0.0F));

        PartDefinition bone119 = bone118.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(0, 56).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 91).addBox(-1.5F, -2.75F, -0.75F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-4.0F, -1.6F, 3.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition rotor = partdefinition.addOrReplaceChild("rotor", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition bone98 = rotor.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(48, 61).addBox(0.0F, -14.0F, -7.98F, 1.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(48, 61).addBox(0.0F, -14.0F, -7.98F, 1.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone100 = bone99.addOrReplaceChild("bone100", CubeListBuilder.create().texOffs(48, 61).addBox(0.0F, -14.0F, -7.98F, 1.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(48, 61).addBox(0.0F, -14.0F, -7.98F, 1.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone102 = bone101.addOrReplaceChild("bone102", CubeListBuilder.create().texOffs(48, 61).addBox(0.0F, -14.0F, -7.98F, 1.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone103 = bone102.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(48, 61).addBox(0.0F, -14.0F, -7.98F, 1.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone104 = rotor.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -10.0F, -2.605F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -10.0F, -2.605F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -10.0F, -2.605F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone107 = bone106.addOrReplaceChild("bone107", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -10.0F, -2.605F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -10.0F, -2.605F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone109 = bone108.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, -10.0F, -2.605F, 3.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        base_console.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        controls.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void renderConsole(GlobalConsoleBlockEntity globalConsoleBlock, Level level, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root().getAllParts().forEach(ModelPart::resetPose);
        TardisClientData reactions = TardisClientData.getInstance(level.dimension());
        this.animate(reactions.ROTOR_ANIMATION, MODEL_ROTOR_LOOP, Minecraft.getInstance().player.tickCount);

        float rot = -1f + (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        throttle_control.xRot = rot;

        handbrake.xRot = reactions.isHandbrakeEngaged() ? 1f : 0f;

        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return MYST_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.MYST.getId();
    }
}