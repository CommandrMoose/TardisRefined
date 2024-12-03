package whocraft.tardis_refined.client.model.blockentity.shell.shells;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;


public class MysticShellModel extends ShellModel {

    public static final AnimationDefinition IDLE = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/shell/myst/idle.json"));

    private final ModelPart right_door;
    private final ModelPart left_door;
    private final ModelPart bone3;
    private final ModelPart bone6;
    private final ModelPart bone7;
    private final ModelPart bone10;
    private final ModelPart bone11;
    private final ModelPart bone;
    private final ModelPart side_animations;
    private final ModelPart bone56;
    private final ModelPart bone58;
    private final ModelPart gold_animations;
    private final ModelPart bb_main;
    private final ModelPart root;

    public MysticShellModel(ModelPart root) {
        super(root);
        this.root = root;
        this.right_door = root.getChild("right_door");
        this.left_door = root.getChild("left_door");
        this.bone3 = root.getChild("bone3");
        this.bone6 = root.getChild("bone6");
        this.bone7 = root.getChild("bone7");
        this.bone10 = root.getChild("bone10");
        this.bone11 = root.getChild("bone11");
        this.bone = root.getChild("bone");
        this.side_animations = root.getChild("side_animations");
        this.bone56 = root.getChild("bone56");
        this.bone58 = root.getChild("bone58");
        this.gold_animations = root.getChild("gold_animations");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition right_door = partdefinition.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(0, 0).addBox(-6.975F, -3.0F, -2.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 11).addBox(-7.0F, -16.0F, -1.0F, 7.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 5.0F, -7.0F));

        PartDefinition bone47 = right_door.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(130, 166).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-2.5F, 12.0F, 0.6F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone44 = right_door.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(130, 158).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

        PartDefinition bone45 = right_door.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(130, 166).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.0F, 0.1F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone49 = right_door.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -4.25F, 0.2F));

        PartDefinition bone46 = right_door.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(130, 176).addBox(-4.5F, -4.5F, -0.5F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -11.0F, 0.0F, 0.0F, 0.0F, -2.6616F));

        PartDefinition bone48 = right_door.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(130, 187).addBox(-0.5F, -4.5F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.75F, -7.25F, -0.1F));

        PartDefinition bone17 = right_door.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offset(-7.0F, 19.0F, 8.0F));

        PartDefinition bone16 = bone17.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(72, 196).addBox(0.0F, -14.0F, -1.05F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -7.1F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(72, 204).addBox(0.0F, -7.0F, -1.025F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.1F));

        PartDefinition bone15 = bone17.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(74, 153).addBox(0.0F, -24.0F, -1.0F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -7.1F));

        PartDefinition bone14 = bone17.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(72, 176).addBox(0.0F, -9.0F, -1.0F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -7.1F));

        PartDefinition bone19 = bone17.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(72, 212).addBox(0.0F, -1.0F, 0.0F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -34.0F, -8.125F));

        PartDefinition left_door = partdefinition.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.975F, -3.0F, -2.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 11).mirror().addBox(0.0F, -16.0F, -1.0F, 7.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, 5.0F, -7.0F));

        PartDefinition bone53 = left_door.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.6F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, -7.0F, 0.2F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone54 = left_door.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.45F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone55 = left_door.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(2.0F, 14.0F, 1.2F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone52 = left_door.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -9.25F, 0.2F));

        PartDefinition bone51 = left_door.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(130, 158).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -14.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

        PartDefinition bone50 = left_door.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(130, 166).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(1.25F, 5.0F, 1.1F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone20 = left_door.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offset(7.0F, 19.0F, 8.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(72, 196).mirror().addBox(-7.0F, -14.0F, -1.05F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -3.0F, -7.1F));

        PartDefinition bone22 = bone20.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(72, 204).mirror().addBox(-7.0F, -7.0F, -1.025F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -10.0F, -7.1F));

        PartDefinition bone23 = bone20.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(74, 153).mirror().addBox(-5.0F, -24.0F, -1.0F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.0F, -7.1F));

        PartDefinition bone24 = bone20.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(72, 176).mirror().addBox(-7.0F, -9.0F, -1.0F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -12.0F, -7.1F));

        PartDefinition bone25 = bone20.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(72, 212).mirror().addBox(-7.0F, -1.0F, 0.0F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -34.0F, -8.125F));

        PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(31, 32).addBox(-7.0F, -35.0F, -8.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(31, 32).addBox(-7.0F, -35.0F, -8.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(31, 32).addBox(-7.0F, -35.0F, -8.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(79, 11).addBox(-5.0F, -3.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 66).addBox(-5.0F, -38.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone7 = partdefinition.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(79, 11).addBox(-5.0F, -3.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 66).addBox(-5.0F, -38.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(79, 11).addBox(-5.0F, -3.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 66).addBox(-5.0F, -38.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(79, 11).addBox(-5.0F, -3.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 66).addBox(-5.0F, -38.0F, -9.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(73, 77).addBox(5.0F, -26.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(73, 77).mirror().addBox(20.0F, -26.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(77, 40).mirror().addBox(20.0F, 9.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(77, 40).addBox(5.0F, 9.0F, -10.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(29, 72).addBox(5.0F, 9.0F, 5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(29, 72).mirror().addBox(20.0F, 9.0F, 5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-15.0F, 10.0F, 0.0F));

        PartDefinition bone11 = partdefinition.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(73, 66).addBox(5.0F, -26.0F, 5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(73, 66).mirror().addBox(20.0F, -26.0F, 5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-15.0F, 10.0F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(62, 45).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -40.0F, -11.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(62, 45).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -40.0F, -11.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone12 = bone2.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = bone12.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(62, 45).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -40.0F, -11.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone13 = bone12.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r4 = bone13.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 45).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -40.0F, -11.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition side_animations = partdefinition.addOrReplaceChild("side_animations", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone26 = side_animations.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(94, 196).addBox(-7.0F, -14.0F, -1.05F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -7.1F));

        PartDefinition bone28 = bone26.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(94, 204).addBox(-7.0F, -7.0F, -1.025F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.1F));

        PartDefinition bone29 = bone26.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(98, 153).addBox(-5.0F, -24.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -7.1F));

        PartDefinition bone30 = bone26.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(94, 176).addBox(-7.0F, -9.0F, -1.0F, 14.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -7.1F));

        PartDefinition bone31 = bone26.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(94, 212).addBox(-7.0F, -1.0F, 0.0F, 14.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -34.0F, -8.125F));

        PartDefinition bone32 = side_animations.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(94, 196).addBox(-7.0F, -14.0F, -1.05F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -7.1F));

        PartDefinition bone34 = bone32.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(94, 204).addBox(-7.0F, -7.0F, -1.025F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.1F));

        PartDefinition bone35 = bone32.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(98, 153).addBox(-5.0F, -24.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -7.1F));

        PartDefinition bone36 = bone32.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(94, 176).addBox(-7.0F, -9.0F, -1.0F, 14.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -7.1F));

        PartDefinition bone37 = bone32.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(94, 212).addBox(-7.0F, -1.0F, 0.0F, 14.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -34.0F, -8.125F));

        PartDefinition bone38 = side_animations.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(94, 196).addBox(-7.0F, -14.0F, -1.05F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -7.1F));

        PartDefinition bone40 = bone38.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(94, 204).addBox(-7.0F, -7.0F, -1.025F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -7.1F));

        PartDefinition bone41 = bone38.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(98, 153).addBox(-5.0F, -24.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -7.1F));

        PartDefinition bone42 = bone38.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(94, 176).addBox(-7.0F, -9.0F, -1.0F, 14.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -7.1F));

        PartDefinition bone43 = bone38.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(94, 212).addBox(-7.0F, -1.0F, 0.0F, 14.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -34.0F, -8.125F));

        PartDefinition bone56 = partdefinition.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(148, 158).addBox(-4.5F, -4.5F, -0.5F, 9.0F, 9.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(148, 158).mirror().addBox(-4.5F, -4.5F, 13.2F, 9.0F, 9.0F, 1.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offset(0.0F, -14.25F, -6.475F));

        PartDefinition bone58 = partdefinition.addOrReplaceChild("bone58", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone57 = bone58.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(148, 158).addBox(-4.5F, -4.5F, -0.5F, 9.0F, 9.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(148, 158).mirror().addBox(-4.5F, -4.5F, 13.2F, 9.0F, 9.0F, 1.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offset(0.0F, -38.25F, -6.475F));

        PartDefinition gold_animations = partdefinition.addOrReplaceChild("gold_animations", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone60 = gold_animations.addOrReplaceChild("bone60", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone59 = bone60.addOrReplaceChild("bone59", CubeListBuilder.create().texOffs(130, 176).addBox(-4.5F, -4.5F, -0.5F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(1.5F, -30.0F, -6.5F, 0.0F, 0.0F, -2.6616F));

        PartDefinition bone61 = bone60.addOrReplaceChild("bone61", CubeListBuilder.create().texOffs(130, 166).addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-2.75F, -25.0F, -5.9F, 0.0F, 0.0F, 0.7854F));

        PartDefinition bone62 = bone60.addOrReplaceChild("bone62", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(3.0F, -19.0F, -5.8F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone63 = bone60.addOrReplaceChild("bone63", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.6F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, -5.8F, 0.0F, 0.0F, 0.6545F));

        PartDefinition bone64 = bone60.addOrReplaceChild("bone64", CubeListBuilder.create().texOffs(147, 170).addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(3.5F, -11.5F, -5.8F, 0.0F, 0.0F, 0.1309F));

        PartDefinition bone65 = bone60.addOrReplaceChild("bone65", CubeListBuilder.create().texOffs(130, 158).addBox(-3.0F, -3.0F, 0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-2.75F, -7.0F, -7.0F, 0.0F, 0.0F, 0.6545F));

        PartDefinition bone66 = gold_animations.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone67 = bone66.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(130, 176).mirror().addBox(-4.5F, -4.5F, -0.5F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-1.5F, -30.0F, -6.5F, 0.0F, 0.0F, 2.6616F));

        PartDefinition bone68 = bone66.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(130, 166).mirror().addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, -25.0F, -5.9F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bone69 = bone66.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(147, 170).mirror().addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -19.0F, -5.8F, 0.0F, 0.0F, 0.48F));

        PartDefinition bone70 = bone66.addOrReplaceChild("bone70", CubeListBuilder.create().texOffs(147, 170).mirror().addBox(-1.5F, -1.5F, -0.6F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.0F, -5.8F, 0.0F, 0.0F, -0.6545F));

        PartDefinition bone71 = bone66.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(147, 170).mirror().addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, -11.5F, -5.8F, 0.0F, 0.0F, -0.1309F));

        PartDefinition bone72 = bone66.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(130, 158).mirror().addBox(-3.0F, -3.0F, 0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, -7.0F, -7.0F, 0.0F, 0.0F, -0.6545F));

        PartDefinition bone73 = gold_animations.addOrReplaceChild("bone73", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create().texOffs(130, 176).mirror().addBox(-4.5F, -4.5F, -0.5F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-1.5F, -30.0F, -6.5F, 0.0F, 0.0F, 2.6616F));

        PartDefinition bone75 = bone73.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(130, 166).mirror().addBox(-3.5F, -3.5F, -0.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, -25.0F, -5.9F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bone76 = bone73.addOrReplaceChild("bone76", CubeListBuilder.create().texOffs(147, 170).mirror().addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -19.0F, -5.8F, 0.0F, 0.0F, 0.48F));

        PartDefinition bone77 = bone73.addOrReplaceChild("bone77", CubeListBuilder.create().texOffs(147, 170).mirror().addBox(-1.5F, -1.5F, -0.6F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.0F, -5.8F, 0.0F, 0.0F, -0.6545F));

        PartDefinition bone78 = bone73.addOrReplaceChild("bone78", CubeListBuilder.create().texOffs(147, 170).mirror().addBox(-1.5F, -1.5F, -0.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, -11.5F, -5.8F, 0.0F, 0.0F, -0.1309F));

        PartDefinition bone79 = bone73.addOrReplaceChild("bone79", CubeListBuilder.create().texOffs(130, 158).mirror().addBox(-3.0F, -3.0F, 0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(2.75F, -7.0F, -7.0F, 0.0F, 0.0F, -0.6545F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(9, 77).addBox(-10.0F, -23.0F, -10.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(-9.0F, -35.0F, -9.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).mirror().addBox(7.0F, -35.0F, -9.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 66).mirror().addBox(7.0F, -35.0F, 7.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 66).addBox(-9.0F, -35.0F, 7.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(9, 77).mirror().addBox(6.0F, -23.0F, -10.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(43, 16).addBox(-10.0F, -23.0F, 6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(43, 16).mirror().addBox(6.0F, -23.0F, 6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 16).addBox(-7.0F, -37.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(9, 66).addBox(-3.0F, -45.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(65, 57).addBox(-3.0F, -44.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.5F))
                .texOffs(0, 32).addBox(-7.0F, -35.0F, 1.0F, 14.0F, 32.0F, 1.0F, new CubeDeformation(-0.025F))
                .texOffs(54, 58).addBox(6.9F, -35.0F, -7.0F, 1.0F, 32.0F, 8.0F, new CubeDeformation(-0.025F))
                .texOffs(54, 58).mirror().addBox(-7.9F, -35.0F, -7.0F, 1.0F, 32.0F, 8.0F, new CubeDeformation(-0.025F)).mirror(false)
                .texOffs(44, 0).addBox(-6.9F, -35.9F, -7.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(-0.025F))
                .texOffs(44, 0).addBox(-6.9F, -4.1F, -7.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(-0.025F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        addMaterializationPart(partdefinition);

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        right_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone11.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        side_animations.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone56.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone58.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        gold_animations.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void setDoorPosition(boolean open) {
        if (open) {
            this.left_door.yRot = 250f;
            this.right_door.yRot = -250f;
        } else {
            this.left_door.yRot = 0;
            this.right_door.yRot = 0;
        }
    }

    @Override
    public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        handleAllAnimations(entity, root(), isBaseModel, open, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void handleSpecialAnimation(GlobalShellBlockEntity entity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float baseAlpha) {
        TardisClientData reactions = TardisClientData.getInstance(entity.getTardisId());
        this.animate(entity.liveliness, IDLE, Minecraft.getInstance().player.tickCount, reactions.isFlying() ? 5 : 1);
    }
}