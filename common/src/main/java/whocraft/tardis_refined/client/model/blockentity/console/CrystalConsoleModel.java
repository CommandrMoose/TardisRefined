package whocraft.tardis_refined.client.model.blockentity.console;

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class CrystalConsoleModel extends HierarchicalModel implements ConsoleUnit {

    public static final AnimationDefinition IDLE = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/crystal/idle.json"));
    public static final AnimationDefinition FLIGHT = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/crystal/flight.json"));


    private static final ResourceLocation CRYSTAL_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/crystal/crystal_console.png");
    private final ModelPart base_control;
    private final ModelPart rotor;
    private final ModelPart rotor_purple;
    private final ModelPart controls;
    private final ModelPart spinninglight;
    private final ModelPart bb_main;
    private final ModelPart root;
    private final ModelPart throttle;
    private final ModelPart handbrake;

    public CrystalConsoleModel(ModelPart root) {
        this.root = root;
        this.base_control = root.getChild("base_control");
        this.rotor = root.getChild("rotor");
        this.rotor_purple = root.getChild("rotor_purple");
        this.controls = root.getChild("controls");
        this.spinninglight = root.getChild("spinninglight");
        this.bb_main = root.getChild("bb_main");
        this.throttle = JsonToAnimationDefinition.findPart(this, "large_lever_control_throttle");
        this.handbrake = JsonToAnimationDefinition.findPart(this, "large_lever3_control");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base_control = partdefinition.addOrReplaceChild("base_control", CubeListBuilder.create(), PartPose.offset(0.0F, 27.0F, 0.0F));

        PartDefinition bone = base_control.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(105, 85).addBox(-5.0F, -2.0F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 81).addBox(-6.0F, -2.025F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(105, 85).addBox(-5.0F, -2.0F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 81).addBox(-6.0F, -2.025F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(105, 85).addBox(-5.0F, -2.0F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 81).addBox(-6.0F, -2.025F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(105, 85).addBox(-5.0F, -2.0F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 81).addBox(-6.0F, -2.025F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(105, 85).addBox(-5.0F, -2.0F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(0, 81).addBox(-6.0F, -2.025F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(105, 85).addBox(-5.0F, -2.0F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(0, 81).addBox(-6.0F, -2.025F, -22.3F, 11.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone13 = base_control.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, -3.0F, -24.65F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, -3.0F, -24.65F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, -3.0F, -24.65F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, -3.0F, -24.65F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, -3.0F, -24.65F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(76, 31).addBox(-0.5F, -0.5F, -25.65F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, -3.0F, -24.65F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = base_control.addOrReplaceChild("bone97", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone98 = bone97.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(86, 54).addBox(-4.5F, 0.0F, -9.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone99 = bone97.addOrReplaceChild("bone99", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone100 = bone99.addOrReplaceChild("bone100", CubeListBuilder.create().texOffs(86, 54).addBox(-4.5F, 0.0F, -9.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone101 = bone99.addOrReplaceChild("bone101", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone102 = bone101.addOrReplaceChild("bone102", CubeListBuilder.create().texOffs(86, 54).addBox(-4.5F, 0.0F, -9.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone103 = bone101.addOrReplaceChild("bone103", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone104 = bone103.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(86, 54).addBox(-4.5F, 0.0F, -9.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone105 = bone103.addOrReplaceChild("bone105", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(86, 54).addBox(-4.5F, 0.0F, -9.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone107 = bone105.addOrReplaceChild("bone107", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(86, 54).addBox(-4.5F, 0.0F, -9.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone55 = base_control.addOrReplaceChild("bone55", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone56 = bone55.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(23, 34).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone57 = bone55.addOrReplaceChild("bone57", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58 = bone57.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(23, 34).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone59 = bone57.addOrReplaceChild("bone59", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = bone59.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(23, 34).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone61 = bone59.addOrReplaceChild("bone61", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone62 = bone61.addOrReplaceChild("bone62", CubeListBuilder.create().texOffs(23, 34).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone63 = bone61.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create().texOffs(23, 34).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone65 = bone63.addOrReplaceChild("bone65", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66 = bone65.addOrReplaceChild("bone66", CubeListBuilder.create().texOffs(23, 34).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone196 = base_control.addOrReplaceChild("bone196", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone198 = bone196.addOrReplaceChild("bone198", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition bone57_r1 = bone198.addOrReplaceChild("bone57_r1", CubeListBuilder.create().texOffs(80, 152).addBox(-9.5F, -0.5F, -2.0F, 19.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -18.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone199 = bone198.addOrReplaceChild("bone199", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone58_r1 = bone199.addOrReplaceChild("bone58_r1", CubeListBuilder.create().texOffs(83, 157).addBox(-8.0F, -0.5F, -2.0F, 16.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -14.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone200 = bone199.addOrReplaceChild("bone200", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone59_r1 = bone200.addOrReplaceChild("bone59_r1", CubeListBuilder.create().texOffs(85, 162).addBox(-7.0F, -0.5F, -2.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone201 = bone196.addOrReplaceChild("bone201", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58_r2 = bone201.addOrReplaceChild("bone58_r2", CubeListBuilder.create().texOffs(80, 152).addBox(-9.5F, -0.5F, -2.0F, 19.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -18.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone202 = bone201.addOrReplaceChild("bone202", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone59_r2 = bone202.addOrReplaceChild("bone59_r2", CubeListBuilder.create().texOffs(83, 157).addBox(-8.0F, -0.5F, -2.0F, 16.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -14.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone203 = bone202.addOrReplaceChild("bone203", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone60_r1 = bone203.addOrReplaceChild("bone60_r1", CubeListBuilder.create().texOffs(85, 162).addBox(-7.0F, -0.5F, -2.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone204 = bone196.addOrReplaceChild("bone204", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone59_r3 = bone204.addOrReplaceChild("bone59_r3", CubeListBuilder.create().texOffs(80, 152).addBox(-9.5F, -0.5F, -2.0F, 19.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -18.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone205 = bone204.addOrReplaceChild("bone205", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone60_r2 = bone205.addOrReplaceChild("bone60_r2", CubeListBuilder.create().texOffs(83, 157).addBox(-8.0F, -0.5F, -2.0F, 16.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -14.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone206 = bone205.addOrReplaceChild("bone206", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone61_r1 = bone206.addOrReplaceChild("bone61_r1", CubeListBuilder.create().texOffs(85, 162).addBox(-7.0F, -0.5F, -2.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone207 = bone196.addOrReplaceChild("bone207", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone60_r3 = bone207.addOrReplaceChild("bone60_r3", CubeListBuilder.create().texOffs(80, 152).addBox(-9.5F, -0.5F, -2.0F, 19.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -18.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone208 = bone207.addOrReplaceChild("bone208", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone61_r2 = bone208.addOrReplaceChild("bone61_r2", CubeListBuilder.create().texOffs(83, 157).addBox(-8.0F, -0.5F, -2.0F, 16.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -14.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone209 = bone208.addOrReplaceChild("bone209", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone62_r1 = bone209.addOrReplaceChild("bone62_r1", CubeListBuilder.create().texOffs(85, 162).addBox(-7.0F, -0.5F, -2.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone210 = bone196.addOrReplaceChild("bone210", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone61_r3 = bone210.addOrReplaceChild("bone61_r3", CubeListBuilder.create().texOffs(80, 152).addBox(-9.5F, -0.5F, -2.0F, 19.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -18.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone211 = bone210.addOrReplaceChild("bone211", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone62_r2 = bone211.addOrReplaceChild("bone62_r2", CubeListBuilder.create().texOffs(83, 157).addBox(-8.0F, -0.5F, -2.0F, 16.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -14.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone212 = bone211.addOrReplaceChild("bone212", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone63_r1 = bone212.addOrReplaceChild("bone63_r1", CubeListBuilder.create().texOffs(85, 162).addBox(-7.0F, -0.5F, -2.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone213 = bone196.addOrReplaceChild("bone213", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone62_r3 = bone213.addOrReplaceChild("bone62_r3", CubeListBuilder.create().texOffs(80, 152).addBox(-9.5F, -0.5F, -2.0F, 19.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -18.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone214 = bone213.addOrReplaceChild("bone214", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone63_r2 = bone214.addOrReplaceChild("bone63_r2", CubeListBuilder.create().texOffs(83, 157).addBox(-8.0F, -0.5F, -2.0F, 16.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -14.3F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone215 = bone214.addOrReplaceChild("bone215", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone64_r1 = bone215.addOrReplaceChild("bone64_r1", CubeListBuilder.create().texOffs(85, 162).addBox(-7.0F, -0.5F, -2.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.3F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone19 = base_control.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, 0.0F));

        PartDefinition bone25 = bone19.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(80, 75).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -24.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(80, 75).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -24.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone22 = bone20.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(80, 75).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -24.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone24 = bone22.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone26 = bone24.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(80, 75).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -24.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone27 = bone24.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(80, 75).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -24.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone29 = bone27.addOrReplaceChild("bone29", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(80, 75).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -24.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone49 = base_control.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(68, 75).addBox(-4.5F, -2.0F, -11.4F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -25.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone50 = bone49.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(68, 75).addBox(-4.5F, -2.0F, -11.4F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(68, 75).addBox(-4.5F, -2.0F, -11.4F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(68, 75).addBox(-4.5F, -2.0F, -11.4F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(68, 75).addBox(-4.5F, -2.0F, -11.4F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(68, 75).addBox(-4.5F, -2.0F, -11.4F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone43 = base_control.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(15, 126).addBox(-3.5F, 1.0F, -10.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -26.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone44 = bone43.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(15, 126).addBox(-3.5F, 1.0F, -10.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(15, 126).addBox(-3.5F, 1.0F, -10.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(15, 126).addBox(-3.5F, 1.0F, -10.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(15, 126).addBox(-3.5F, 1.0F, -10.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(15, 126).addBox(-3.5F, 1.0F, -10.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = base_control.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(121, 42).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, 0.0F));

        PartDefinition bone38 = bone37.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(121, 42).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(121, 42).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(121, 42).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(121, 42).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(121, 42).addBox(-2.0F, -3.0F, -12.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone31 = base_control.addOrReplaceChild("bone31", CubeListBuilder.create(), PartPose.offset(0.0F, -25.0F, 0.0F));

        PartDefinition bone31_r1 = bone31.addOrReplaceChild("bone31_r1", CubeListBuilder.create().texOffs(126, 125).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -12.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone32 = bone31.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone32_r1 = bone32.addOrReplaceChild("bone32_r1", CubeListBuilder.create().texOffs(126, 125).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -12.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone33_r1 = bone33.addOrReplaceChild("bone33_r1", CubeListBuilder.create().texOffs(126, 125).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -12.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34_r1 = bone34.addOrReplaceChild("bone34_r1", CubeListBuilder.create().texOffs(126, 125).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -12.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35_r1 = bone35.addOrReplaceChild("bone35_r1", CubeListBuilder.create().texOffs(126, 125).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -12.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36_r1 = bone36.addOrReplaceChild("bone36_r1", CubeListBuilder.create().texOffs(126, 125).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -12.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone70 = base_control.addOrReplaceChild("bone70", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(89, 31).addBox(-2.0F, 0.0F, 0.025F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition bone87 = bone70.addOrReplaceChild("bone87", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(89, 31).addBox(-2.0F, 0.0F, 0.025F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition bone89 = bone87.addOrReplaceChild("bone89", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(89, 31).addBox(-2.0F, 0.0F, 0.025F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition bone91 = bone89.addOrReplaceChild("bone91", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone92 = bone91.addOrReplaceChild("bone92", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(89, 31).addBox(-2.0F, 0.0F, 0.025F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition bone93 = bone91.addOrReplaceChild("bone93", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(101, 0).addBox(-0.5F, 0.25F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(89, 31).addBox(-2.0F, 0.0F, 0.025F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition bone95 = bone93.addOrReplaceChild("bone95", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(89, 31).addBox(-2.0F, 0.0F, 0.025F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition bone68 = base_control.addOrReplaceChild("bone68", CubeListBuilder.create(), PartPose.offset(0.0F, -25.0F, 0.0F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(49, 0).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone77 = bone68.addOrReplaceChild("bone77", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create().texOffs(49, 0).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone79 = bone77.addOrReplaceChild("bone79", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80 = bone79.addOrReplaceChild("bone80", CubeListBuilder.create().texOffs(49, 0).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone81 = bone79.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create().texOffs(49, 0).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone83 = bone81.addOrReplaceChild("bone83", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create().texOffs(49, 0).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.025F))
                .texOffs(0, 92).addBox(-0.5F, -7.0F, -1.4F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone85 = bone83.addOrReplaceChild("bone85", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone86 = bone85.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(49, 0).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, 17.5F, -12.75F, 0.3054F, 0.0F, 0.0F));

        PartDefinition bone109 = base_control.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -3.0F, -11.75F, 5.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition bone110 = bone109.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -3.0F, -11.75F, 5.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -3.0F, -11.75F, 5.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -3.0F, -11.75F, 5.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone113 = bone112.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -3.0F, -11.75F, 5.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone114 = bone113.addOrReplaceChild("bone114", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -3.0F, -11.75F, 5.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone121 = base_control.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, -3.0F, -10.75F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone142 = bone121.addOrReplaceChild("bone142", CubeListBuilder.create().texOffs(38, 119).addBox(-1.5F, -5.5F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 4.5F, -15.25F));

        PartDefinition bone143 = bone142.addOrReplaceChild("bone143", CubeListBuilder.create().texOffs(38, 145).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition bone145 = bone142.addOrReplaceChild("bone145", CubeListBuilder.create().texOffs(38, 139).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.0175F, 0.0F));

        PartDefinition bone149 = bone142.addOrReplaceChild("bone149", CubeListBuilder.create().texOffs(38, 133).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition bone155 = bone142.addOrReplaceChild("bone155", CubeListBuilder.create().texOffs(126, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, 0.0F));

        PartDefinition bone153 = bone142.addOrReplaceChild("bone153", CubeListBuilder.create().texOffs(126, 0).mirror().addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 6.5F, 0.0F));

        PartDefinition bone122 = bone121.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, -3.0F, -10.75F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone123 = bone122.addOrReplaceChild("bone123", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, -3.0F, -10.75F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone225 = bone123.addOrReplaceChild("bone225", CubeListBuilder.create().texOffs(0, 132).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 5.5F, -18.25F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone216 = bone123.addOrReplaceChild("bone216", CubeListBuilder.create().texOffs(128, 73).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -0.5F, -17.0F));

        PartDefinition bone217 = bone123.addOrReplaceChild("bone217", CubeListBuilder.create().texOffs(135, 7).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 2.0F, -17.0F));

        PartDefinition bone218 = bone123.addOrReplaceChild("bone218", CubeListBuilder.create().texOffs(135, 12).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 5.0F, -17.0F));

        PartDefinition bone219 = bone123.addOrReplaceChild("bone219", CubeListBuilder.create().texOffs(13, 131).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 5.5F, -17.0F));

        PartDefinition bone220 = bone123.addOrReplaceChild("bone220", CubeListBuilder.create().texOffs(109, 125).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 5.5F, -17.0F));

        PartDefinition bone221 = bone123.addOrReplaceChild("bone221", CubeListBuilder.create().texOffs(13, 131).mirror().addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.5F, 5.5F, -17.0F));

        PartDefinition bone223 = bone123.addOrReplaceChild("bone223", CubeListBuilder.create().texOffs(135, 12).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, 5.0F, -17.0F));

        PartDefinition bone222 = bone123.addOrReplaceChild("bone222", CubeListBuilder.create().texOffs(135, 7).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, 2.0F, -17.0F));

        PartDefinition bone224 = bone123.addOrReplaceChild("bone224", CubeListBuilder.create().texOffs(128, 73).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -0.5F, -17.0F));

        PartDefinition bone124 = bone123.addOrReplaceChild("bone124", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, -3.0F, -10.75F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(105, 73).addBox(-4.0F, -1.0F, -15.75F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone125 = bone124.addOrReplaceChild("bone125", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone126 = bone125.addOrReplaceChild("bone126", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone165 = bone126.addOrReplaceChild("bone165", CubeListBuilder.create().texOffs(25, 147).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 11.0F, -13.25F));

        PartDefinition bone161 = bone126.addOrReplaceChild("bone161", CubeListBuilder.create().texOffs(25, 142).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 9.0F, -13.25F));

        PartDefinition bone160 = bone126.addOrReplaceChild("bone160", CubeListBuilder.create().texOffs(25, 137).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, -13.25F));

        PartDefinition bone169 = bone126.addOrReplaceChild("bone169", CubeListBuilder.create().texOffs(108, 15).addBox(-3.5F, -2.0F, -2.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, -13.25F));

        PartDefinition bone171 = bone169.addOrReplaceChild("bone171", CubeListBuilder.create().texOffs(0, 132).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -1.75F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone177 = bone126.addOrReplaceChild("bone177", CubeListBuilder.create().texOffs(66, 133).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 4.0F, -13.75F));

        PartDefinition bone181 = bone126.addOrReplaceChild("bone181", CubeListBuilder.create().texOffs(67, 138).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, 4.0F, -13.75F));

        PartDefinition bone186 = bone126.addOrReplaceChild("bone186", CubeListBuilder.create().texOffs(66, 143).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, 4.0F, -11.25F));

        PartDefinition bone174 = bone126.addOrReplaceChild("bone174", CubeListBuilder.create(), PartPose.offset(-11.5575F, 4.0F, -11.0429F));

        PartDefinition bone132_r1 = bone174.addOrReplaceChild("bone132_r1", CubeListBuilder.create().texOffs(17, 119).addBox(-7.0F, -1.525F, 0.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0386F, 0.025F, -0.0132F, 0.0F, 2.0071F, 0.0F));

        PartDefinition bone132 = bone126.addOrReplaceChild("bone132", CubeListBuilder.create().texOffs(113, 54).addBox(-4.45F, -2.3F, -2.975F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2752F, 4.8F, -10.4719F, 0.0F, 0.48F, 0.0F));

        PartDefinition bone127 = bone121.addOrReplaceChild("bone127", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone133 = bone127.addOrReplaceChild("bone133", CubeListBuilder.create().texOffs(26, 103).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -24.6F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone128 = bone127.addOrReplaceChild("bone128", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone129 = bone128.addOrReplaceChild("bone129", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone130 = bone129.addOrReplaceChild("bone130", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone131 = bone130.addOrReplaceChild("bone131", CubeListBuilder.create().texOffs(53, 103).addBox(-1.5F, -6.0F, -26.75F, 3.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone67 = base_control.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(106, 31).addBox(-2.0F, -3.0F, -12.75F, 4.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition bone72 = bone67.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(106, 31).addBox(-2.0F, -3.0F, -12.75F, 4.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone73 = bone72.addOrReplaceChild("bone73", CubeListBuilder.create().texOffs(106, 31).addBox(-2.0F, -3.0F, -12.75F, 4.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create().texOffs(106, 31).addBox(-2.0F, -3.0F, -12.75F, 4.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(106, 31).addBox(-2.0F, -3.0F, -12.75F, 4.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone76 = bone75.addOrReplaceChild("bone76", CubeListBuilder.create().texOffs(106, 31).addBox(-2.0F, -3.0F, -12.75F, 4.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone115 = base_control.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(49, 0).addBox(-9.5F, -1.05F, -20.15F, 19.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone116 = bone115.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(49, 0).addBox(-9.5F, -1.05F, -20.15F, 19.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone117 = bone116.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(49, 0).addBox(-9.5F, -1.05F, -20.15F, 19.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone118 = bone117.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(49, 0).addBox(-9.5F, -1.05F, -20.15F, 19.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone119 = bone118.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(49, 0).addBox(-9.5F, -1.05F, -20.15F, 19.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone120 = bone119.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(49, 0).addBox(-9.5F, -1.05F, -20.15F, 19.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone187 = base_control.addOrReplaceChild("bone187", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 0.0F));

        PartDefinition root_r1 = bone187.addOrReplaceChild("root_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -9.0F, -8.0F, 16.0F, 17.0F, 16.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rotor = partdefinition.addOrReplaceChild("rotor", CubeListBuilder.create().texOffs(65, 15).addBox(-7.0F, -23.475F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(1.0F))
                .texOffs(31, 75).addBox(-6.0F, -41.0F, -6.0F, 12.0F, 15.0F, 12.0F, new CubeDeformation(-0.25F))
                .texOffs(80, 95).addBox(-3.0F, -47.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.25F))
                .texOffs(43, 50).addBox(-7.0F, -34.0F, -7.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition root_r2 = rotor.addOrReplaceChild("root_r2", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -50.0F, -10.0F, 1.0F, 26.0F, 20.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition root_r3 = rotor.addOrReplaceChild("root_r3", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -50.0F, -10.0F, 1.0F, 26.0F, 20.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rotor_purple = partdefinition.addOrReplaceChild("rotor_purple", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

        PartDefinition cube_r1 = rotor_purple.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 224).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(64, 224).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 2.1817F));

        PartDefinition bone239 = rotor_purple.addOrReplaceChild("bone239", CubeListBuilder.create().texOffs(130, 240).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = bone239.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(130, 240).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r3 = bone239.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(130, 240).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r4 = bone239.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(130, 240).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition controls = partdefinition.addOrReplaceChild("controls", CubeListBuilder.create(), PartPose.offset(0.0F, 27.0F, 0.0F));

        PartDefinition north_right = controls.addOrReplaceChild("north_right", CubeListBuilder.create().texOffs(0, 87).addBox(-4.5F, -14.5F, -10.3F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

        PartDefinition bone134 = north_right.addOrReplaceChild("bone134", CubeListBuilder.create().texOffs(128, 61).addBox(-1.5F, -2.0F, 8.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 92).addBox(5.5F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 82).addBox(-4.5F, -1.5F, 5.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition button_control = bone134.addOrReplaceChild("button_control", CubeListBuilder.create().texOffs(91, 75).addBox(-0.5F, -18.5F, -11.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 20.3F));

        PartDefinition bone136 = bone134.addOrReplaceChild("bone136", CubeListBuilder.create().texOffs(51, 127).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -1.0F, 1.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition large_valve_control = bone136.addOrReplaceChild("large_valve_control", CubeListBuilder.create().texOffs(0, 132).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.7F, 0.0F, 0.0F, -0.6981F));

        PartDefinition lever_control = bone134.addOrReplaceChild("lever_control", CubeListBuilder.create().texOffs(80, 87).addBox(-2.5F, -0.5F, -0.75F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -0.525F, 1.5F, 0.0F, 1.1781F, 0.0F));

        PartDefinition bone146 = bone134.addOrReplaceChild("bone146", CubeListBuilder.create().texOffs(100, 64).addBox(-11.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone147 = bone134.addOrReplaceChild("bone147", CubeListBuilder.create().texOffs(100, 64).mirror().addBox(1.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone139 = north_right.addOrReplaceChild("bone139", CubeListBuilder.create().texOffs(21, 87).addBox(0.5F, -1.375F, -5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(21, 87).addBox(0.5F, -1.375F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(86, 118).addBox(-2.0F, -0.5F, -8.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(86, 118).addBox(-2.0F, -0.5F, -6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone235 = bone139.addOrReplaceChild("bone235", CubeListBuilder.create().texOffs(86, 132).addBox(-0.25F, -0.5F, -0.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -0.1F, -5.5F));

        PartDefinition bone236 = bone139.addOrReplaceChild("bone236", CubeListBuilder.create().texOffs(86, 132).addBox(-0.25F, -0.5F, -0.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -0.1F, -7.75F));

        PartDefinition bone140 = bone139.addOrReplaceChild("bone140", CubeListBuilder.create().texOffs(101, 0).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, -5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition small_valve_control = bone139.addOrReplaceChild("small_valve_control", CubeListBuilder.create().texOffs(127, 15).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.15F, -5.0F, 0.0F, -0.7418F, 0.0F));

        PartDefinition small_valve2_control = bone139.addOrReplaceChild("small_valve2_control", CubeListBuilder.create().texOffs(127, 15).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.15F, -7.0F, 0.0F, -1.3526F, 0.0F));

        PartDefinition north_left = controls.addOrReplaceChild("north_left", CubeListBuilder.create().texOffs(114, 10).addBox(-5.5F, -14.5F, -10.3F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(121, 97).addBox(-2.75F, -11.5F, -15.3F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone230 = north_left.addOrReplaceChild("bone230", CubeListBuilder.create().texOffs(123, 103).addBox(-1.5045F, -0.9097F, -0.7637F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.025F)), PartPose.offset(-0.2455F, -10.5903F, -14.5363F));

        PartDefinition bone135 = north_left.addOrReplaceChild("bone135", CubeListBuilder.create().texOffs(119, 108).addBox(-2.0F, -0.5F, 5.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(112, 118).addBox(-2.75F, -0.6F, 5.25F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(89, 44).addBox(0.75F, -2.1F, 6.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone150 = bone135.addOrReplaceChild("bone150", CubeListBuilder.create().texOffs(100, 64).addBox(-11.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone192 = bone135.addOrReplaceChild("bone192", CubeListBuilder.create().texOffs(64, 127).addBox(-8.0F, -1.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition switch_control2 = bone192.addOrReplaceChild("switch_control2", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, 0.75F));

        PartDefinition bone151_r1 = switch_control2.addOrReplaceChild("bone151_r1", CubeListBuilder.create().texOffs(77, 128).addBox(0.0F, -0.75F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition bone151 = bone135.addOrReplaceChild("bone151", CubeListBuilder.create().texOffs(100, 64).mirror().addBox(1.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone152 = north_left.addOrReplaceChild("bone152", CubeListBuilder.create().texOffs(108, 24).addBox(-1.5F, -0.975F, -8.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition control_random = bone152.addOrReplaceChild("control_random", CubeListBuilder.create().texOffs(132, 83).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.975F, -6.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone148 = bone152.addOrReplaceChild("bone148", CubeListBuilder.create().texOffs(101, 0).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, -5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition west = controls.addOrReplaceChild("west", CubeListBuilder.create().texOffs(0, 87).addBox(-4.5F, -14.5F, -10.3F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition exterior_control = west.addOrReplaceChild("exterior_control", CubeListBuilder.create().texOffs(133, 103).addBox(1.0F, -24.0F, -14.4F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(140, 111).addBox(1.0F, -24.75F, -14.4F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
        //Hide the bone (bone141) above the hologram stand to allow the Tardis hologram render to be shown
//        PartDefinition bone141 = exterior_control.addOrReplaceChild("bone141", CubeListBuilder.create().texOffs(143, 116).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -27.75F, -12.9F, 0.0F, -0.48F, 0.0F));

        PartDefinition bone159 = west.addOrReplaceChild("bone159", CubeListBuilder.create().texOffs(76, 44).addBox(-2.0F, -1.0F, 8.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(99, 129).addBox(-7.0F, -0.5F, 1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(21, 87).addBox(-6.5F, -1.875F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(21, 87).addBox(-6.5F, -1.875F, 3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(68, 82).addBox(-4.5F, -1.5F, 5.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.025F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone159_r1 = bone159.addOrReplaceChild("bone159_r1", CubeListBuilder.create().texOffs(42, 110).addBox(-0.5F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -1.0F, 8.75F, -0.3491F, 0.0F, 0.0F));

        PartDefinition small_valve3_control = bone159.addOrReplaceChild("small_valve3_control", CubeListBuilder.create().texOffs(127, 15).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -1.65F, 3.5F, 0.0F, -0.7418F, 0.0F));

        PartDefinition small_valve4_control = bone159.addOrReplaceChild("small_valve4_control", CubeListBuilder.create().texOffs(127, 15).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -1.65F, 1.5F, 0.0F, -0.3491F, 0.0F));

        PartDefinition bone162 = bone159.addOrReplaceChild("bone162", CubeListBuilder.create().texOffs(100, 64).addBox(-11.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone163 = bone159.addOrReplaceChild("bone163", CubeListBuilder.create().texOffs(100, 64).mirror().addBox(1.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone164 = west.addOrReplaceChild("bone164", CubeListBuilder.create().texOffs(118, 26).addBox(-2.5F, -0.6F, -8.25F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(57, 0).addBox(2.75F, -0.85F, -5.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(57, 0).mirror().addBox(-3.75F, -0.85F, -5.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(43, 60).addBox(-2.0F, -1.1F, -8.25F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone166 = bone164.addOrReplaceChild("bone166", CubeListBuilder.create().texOffs(121, 33).addBox(-1.5F, -2.994F, -2.1669F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.3639F, -9.3805F, 0.48F, 0.0F, 0.0F));

        PartDefinition switch2_control = bone166.addOrReplaceChild("switch2_control", CubeListBuilder.create().texOffs(101, 10).addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.744F, 1.8331F));

        PartDefinition south_right = controls.addOrReplaceChild("south_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -2.618F, 0.0F));

        PartDefinition bone168 = south_right.addOrReplaceChild("bone168", CubeListBuilder.create().texOffs(65, 23).addBox(-2.5F, -4.0F, 8.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone231 = bone168.addOrReplaceChild("bone231", CubeListBuilder.create().texOffs(123, 103).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.025F)), PartPose.offset(0.0F, -3.5F, 9.0F));

        PartDefinition bone170 = bone168.addOrReplaceChild("bone170", CubeListBuilder.create().texOffs(100, 64).addBox(-11.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone189 = bone168.addOrReplaceChild("bone189", CubeListBuilder.create().texOffs(64, 127).addBox(-8.0F, -1.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition switch3_control2 = bone189.addOrReplaceChild("switch3_control2", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, 0.75F));

        PartDefinition bone171_r1 = switch3_control2.addOrReplaceChild("bone171_r1", CubeListBuilder.create().texOffs(77, 128).addBox(0.0F, -0.75F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition bone172 = bone168.addOrReplaceChild("bone172", CubeListBuilder.create().texOffs(100, 64).mirror().addBox(1.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone173 = south_right.addOrReplaceChild("bone173", CubeListBuilder.create().texOffs(86, 118).addBox(-3.25F, -0.85F, -7.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(128, 67).addBox(-1.0F, -1.1F, -7.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(86, 118).mirror().addBox(1.25F, -0.85F, -7.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false)
                .texOffs(86, 118).addBox(-1.0F, -0.85F, -5.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(23, 50).addBox(-2.0F, -0.85F, -3.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(121, 51).addBox(-1.0F, -1.85F, -2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition large_valve2_control_rotate = bone173.addOrReplaceChild("large_valve2_control_rotate", CubeListBuilder.create().texOffs(65, 103).addBox(-1.75F, -0.5F, -1.25F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.85F, -6.75F, 0.0F, -0.4363F, 0.0F));

        PartDefinition bone175 = bone173.addOrReplaceChild("bone175", CubeListBuilder.create().texOffs(101, 0).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, -5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition south_left = controls.addOrReplaceChild("south_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 2.618F, 0.0F));

        PartDefinition bone191 = south_left.addOrReplaceChild("bone191", CubeListBuilder.create().texOffs(17, 105).addBox(-2.0F, -4.0F, 6.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone229 = bone191.addOrReplaceChild("bone229", CubeListBuilder.create().texOffs(91, 82).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 8.5F));

        PartDefinition bone193 = bone191.addOrReplaceChild("bone193", CubeListBuilder.create().texOffs(100, 64).addBox(-11.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone190 = bone191.addOrReplaceChild("bone190", CubeListBuilder.create().texOffs(64, 127).addBox(-8.0F, -1.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition switch4_control_increment2 = bone190.addOrReplaceChild("switch4_control_increment2", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, 0.75F));

        PartDefinition bone194_r1 = switch4_control_increment2.addOrReplaceChild("bone194_r1", CubeListBuilder.create().texOffs(77, 128).addBox(0.0F, -0.75F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition bone194 = bone191.addOrReplaceChild("bone194", CubeListBuilder.create().texOffs(100, 64).mirror().addBox(1.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone195 = south_left.addOrReplaceChild("bone195", CubeListBuilder.create().texOffs(128, 67).addBox(-1.0F, -1.1F, -6.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(91, 118).addBox(-3.0F, 0.5F, -11.75F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 81).addBox(-2.0F, 0.4F, -10.75F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition small_valve5_control_x = bone195.addOrReplaceChild("small_valve5_control_x", CubeListBuilder.create().texOffs(127, 15).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(21, 87).addBox(-0.5F, -0.225F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-2.25F, -0.55F, -10.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition small_valve6_control_z = bone195.addOrReplaceChild("small_valve6_control_z", CubeListBuilder.create().texOffs(127, 15).mirror().addBox(-0.75F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(21, 87).mirror().addBox(-0.5F, -0.225F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(2.25F, -0.55F, -10.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition small_valve7_control_y = bone195.addOrReplaceChild("small_valve7_control_y", CubeListBuilder.create().texOffs(127, 15).mirror().addBox(-0.75F, 0.0F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(21, 87).addBox(-0.5F, -0.225F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, -0.55F, -10.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition lever2_control = bone195.addOrReplaceChild("lever2_control", CubeListBuilder.create().texOffs(65, 31).addBox(-0.5F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.1F, -5.75F, 0.0F, -0.8727F, 0.0F));

        PartDefinition bone197 = bone195.addOrReplaceChild("bone197", CubeListBuilder.create().texOffs(101, 0).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, -5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition east = controls.addOrReplaceChild("east", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition bone180 = east.addOrReplaceChild("bone180", CubeListBuilder.create().texOffs(65, 23).addBox(-2.5F, -4.0F, 8.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(128, 115).addBox(4.5F, -0.5F, 0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(108, 118).addBox(-3.5F, -1.0F, 5.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 103).addBox(-0.5F, -1.25F, 2.5F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(105, 111).addBox(-0.75F, -1.35F, 5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(108, 118).mirror().addBox(1.5F, -1.0F, 5.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone232 = bone180.addOrReplaceChild("bone232", CubeListBuilder.create().texOffs(123, 103).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.025F)), PartPose.offset(0.0F, -3.5F, 9.0F));

        PartDefinition large_lever4_control_door_toggle = bone180.addOrReplaceChild("large_lever4_control_door_toggle", CubeListBuilder.create().texOffs(65, 15).addBox(-0.25F, -1.5F, -4.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(65, 15).mirror().addBox(-0.75F, -1.5F, -4.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(30, 105).addBox(-0.5F, -2.5F, 0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -0.5F, 2.25F, -0.6981F, 0.0F, 0.0F));

        PartDefinition large_valve3_control = bone180.addOrReplaceChild("large_valve3_control", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.0F, -1.0F, -0.7F, -0.1745F, -1.1345F, 0.0F));

        PartDefinition bone226 = large_valve3_control.addOrReplaceChild("bone226", CubeListBuilder.create().texOffs(0, 132).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.25F));

        PartDefinition switch5_control = bone180.addOrReplaceChild("switch5_control", CubeListBuilder.create().texOffs(73, 15).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0F, 6.5F, -0.48F, 0.0F, 0.0F));

        PartDefinition switch6_control = bone180.addOrReplaceChild("switch6_control", CubeListBuilder.create().texOffs(73, 15).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.0F, 6.5F, -0.48F, 0.0F, 0.0F));

        PartDefinition bone182 = bone180.addOrReplaceChild("bone182", CubeListBuilder.create().texOffs(100, 64).addBox(-11.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone183 = bone180.addOrReplaceChild("bone183", CubeListBuilder.create().texOffs(100, 64).mirror().addBox(1.0F, -0.5F, -1.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.4F, 5.75F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone184 = east.addOrReplaceChild("bone184", CubeListBuilder.create().texOffs(86, 118).addBox(-3.25F, -0.5F, -6.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 92).addBox(-3.75F, -0.1F, -12.25F, 7.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(86, 118).mirror().addBox(1.25F, -0.5F, -6.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(91, 118).addBox(-3.0F, 0.5F, -11.75F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -12.3F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone233 = bone184.addOrReplaceChild("bone233", CubeListBuilder.create().texOffs(86, 132).addBox(-0.25F, -0.5F, -0.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, -0.1F, -5.75F));

        PartDefinition bone234 = bone184.addOrReplaceChild("bone234", CubeListBuilder.create().texOffs(86, 132).addBox(-0.25F, -0.5F, -0.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.25F, -0.1F, -5.75F));

        PartDefinition bone185 = east.addOrReplaceChild("bone185", CubeListBuilder.create().texOffs(70, 108).addBox(-1.5F, -2.5F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -14.5F, -9.3F, 0.0F, -0.48F, 0.0F));

        PartDefinition spinnything_control = bone185.addOrReplaceChild("spinnything_control", CubeListBuilder.create().texOffs(86, 54).addBox(-1.25F, -2.0F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.5F, -0.5F, -0.7418F, 0.0F, 0.0F));

        PartDefinition north_side = controls.addOrReplaceChild("north_side", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition bone144 = north_side.addOrReplaceChild("bone144", CubeListBuilder.create().texOffs(105, 95).addBox(-1.0F, -2.75F, -1.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone144_r1 = bone144.addOrReplaceChild("bone144_r1", CubeListBuilder.create().texOffs(43, 50).addBox(-2.0F, -1.5F, -4.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.0F, 10.75F, -0.5672F, 0.0F, 0.0F));

        PartDefinition microphone_control = bone144.addOrReplaceChild("microphone_control", CubeListBuilder.create().texOffs(65, 15).addBox(-0.25F, -23.0F, -11.05F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 103).addBox(-1.25F, -24.5F, -11.05F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 20.3F));

        PartDefinition bone144_r2 = microphone_control.addOrReplaceChild("bone144_r2", CubeListBuilder.create().texOffs(124, 131).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -23.0F, -11.55F, -0.6545F, 0.0F, 0.0F));

        PartDefinition north_right_side = controls.addOrReplaceChild("north_right_side", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone137 = north_right_side.addOrReplaceChild("bone137", CubeListBuilder.create().texOffs(105, 95).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone137_r1 = bone137.addOrReplaceChild("bone137_r1", CubeListBuilder.create().texOffs(87, 126).addBox(0.0F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.5F, 5.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition binocular_control = bone137.addOrReplaceChild("binocular_control", CubeListBuilder.create().texOffs(105, 101).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 10.0F, 0.0F, -0.5672F, 0.0F));

        PartDefinition bone227 = binocular_control.addOrReplaceChild("bone227", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 10.3F));

        PartDefinition bone138 = binocular_control.addOrReplaceChild("bone138", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition bone228 = bone138.addOrReplaceChild("bone228", CubeListBuilder.create().texOffs(23, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone138_r1 = bone228.addOrReplaceChild("bone138_r1", CubeListBuilder.create().texOffs(100, 64).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.5F, -0.7854F, 0.0F, 0.0F));

        PartDefinition south_left_side = controls.addOrReplaceChild("south_left_side", CubeListBuilder.create().texOffs(121, 91).addBox(-2.5F, -13.5F, -13.05F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(105, 108).addBox(-2.25F, -14.5F, -10.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(105, 108).addBox(0.25F, -14.5F, -10.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone167 = south_left_side.addOrReplaceChild("bone167", CubeListBuilder.create().texOffs(105, 95).addBox(-1.0F, -3.25F, -1.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone188 = bone167.addOrReplaceChild("bone188", CubeListBuilder.create().texOffs(105, 108).addBox(-1.5F, -1.0F, -3.5F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.75F, 3.5F));

        PartDefinition large_lever3_control = south_left_side.addOrReplaceChild("large_lever3_control", CubeListBuilder.create().texOffs(0, 123).addBox(-2.75F, -1.0F, -5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 123).addBox(0.75F, -1.0F, -5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(90, 44).addBox(-2.25F, -1.0F, -5.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(90, 44).addBox(1.25F, -1.0F, -5.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(100, 31).addBox(-1.5F, -1.0F, -5.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.8F, -0.6109F, 0.0F, 0.0F));

        PartDefinition south = controls.addOrReplaceChild("south", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone176 = south.addOrReplaceChild("bone176", CubeListBuilder.create().texOffs(105, 95).addBox(-1.0F, -3.25F, -1.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(36, 50).addBox(-1.5F, -6.9F, 9.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition large_lever2_control = bone176.addOrReplaceChild("large_lever2_control", CubeListBuilder.create().texOffs(24, 131).addBox(0.15F, -2.5F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(2.35F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, -6.9F, 10.0F, -0.8727F, 0.0F, 0.0F));

        PartDefinition monitor_control = south.addOrReplaceChild("monitor_control", CubeListBuilder.create().texOffs(150, 96).addBox(-1.0F, -4.25F, -9.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(134, 79).addBox(-1.0F, -2.25F, -8.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, -18.3F));

        PartDefinition bone237 = monitor_control.addOrReplaceChild("bone237", CubeListBuilder.create().texOffs(143, 91).addBox(-0.5F, -6.0F, -3.5F, 1.0F, 7.0F, 7.0F, new CubeDeformation(2.0F)), PartPose.offset(-2.5F, -4.75F, -4.0F));

        PartDefinition south_right_side = controls.addOrReplaceChild("south_right_side", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone178 = south_right_side.addOrReplaceChild("bone178", CubeListBuilder.create().texOffs(105, 95).addBox(-1.0F, -2.75F, -4.75F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(17, 114).addBox(-1.0F, -7.4F, 9.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone179 = bone178.addOrReplaceChild("bone179", CubeListBuilder.create().texOffs(10, 0).addBox(-0.25F, -1.95F, -0.375F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(70, 115).addBox(-0.25F, -2.95F, -0.375F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 87).addBox(2.75F, -2.95F, -0.375F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 50).addBox(2.75F, 4.05F, 0.625F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -7.45F, 10.375F, -0.2618F, 0.0F, 0.0F));

        PartDefinition north_left_side = controls.addOrReplaceChild("north_left_side", CubeListBuilder.create().texOffs(121, 91).addBox(-2.5F, -13.5F, -13.05F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(105, 108).addBox(-2.25F, -14.5F, -10.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(105, 108).addBox(0.25F, -14.5F, -10.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone154 = north_left_side.addOrReplaceChild("bone154", CubeListBuilder.create().texOffs(105, 95).addBox(-1.75F, -3.25F, -1.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(99, 95).addBox(-1.75F, -2.5F, -3.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(126, 19).addBox(0.75F, -3.0F, 1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -20.3F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone156 = bone154.addOrReplaceChild("bone156", CubeListBuilder.create().texOffs(14, 34).addBox(-0.75F, -0.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -3.0F, 1.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone157 = bone154.addOrReplaceChild("bone157", CubeListBuilder.create().texOffs(14, 34).addBox(-0.75F, -0.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -3.0F, 3.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone158 = bone154.addOrReplaceChild("bone158", CubeListBuilder.create().texOffs(14, 34).addBox(-0.75F, -0.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -3.0F, 5.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition large_lever_control_throttle = north_left_side.addOrReplaceChild("large_lever_control_throttle", CubeListBuilder.create().texOffs(0, 123).addBox(-2.75F, -1.0F, -5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 123).addBox(0.75F, -1.0F, -5.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(90, 44).addBox(-2.25F, -1.0F, -5.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(90, 44).addBox(1.25F, -1.0F, -5.75F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(100, 31).addBox(-1.5F, -1.0F, -5.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.5F, -10.8F, -0.6109F, 0.0F, 0.0F));

        PartDefinition spinninglight = partdefinition.addOrReplaceChild("spinninglight", CubeListBuilder.create().texOffs(88, 147).addBox(-1.0F, -0.5F, -22.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(84, 147).mirror().addBox(-1.0F, -0.5F, 20.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.25F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 209).addBox(-7.0F, -21.475F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        base_control.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor_purple.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        controls.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        spinninglight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }


    @Override
    public void renderConsole(GlobalConsoleBlockEntity globalConsoleBlock, Level level, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root().getAllParts().forEach(ModelPart::resetPose);
        TardisClientData reactions = TardisClientData.getInstance(level.dimension());


        if (globalConsoleBlock != null && globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            if (reactions.isFlying()) {
                this.animate(reactions.ROTOR_ANIMATION, FLIGHT, Minecraft.getInstance().player.tickCount);
            } else {
                if (TRConfig.CLIENT.PLAY_CONSOLE_IDLE_ANIMATIONS.get() && globalConsoleBlock != null) {
                    this.animate(globalConsoleBlock.liveliness, IDLE, Minecraft.getInstance().player.tickCount);
                }
            }
        }


        float rot = -0.5f + (0.5f * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));

        this.throttle.xRot = rot;

        this.handbrake.xRot = reactions.isHandbrakeEngaged() ? 0f : -0.5f;

        base_control.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor_purple.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        controls.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        spinninglight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return CRYSTAL_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.CRYSTAL.getId();
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}