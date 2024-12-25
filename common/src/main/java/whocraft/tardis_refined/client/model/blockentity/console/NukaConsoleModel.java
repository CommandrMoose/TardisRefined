package whocraft.tardis_refined.client.model.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.frame.tardis.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class NukaConsoleModel extends HierarchicalModel implements ConsoleUnit {



    public static final AnimationDefinition FLIGHT = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/nuka/flight.json"));


    private static final ResourceLocation NUKA_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/nuka/nuka_console.png");
    private final ModelPart rotor_zminus3_yplus5_rotateY;
    private final ModelPart panels;
    private final ModelPart console;
    private final ModelPart bone37;
    private final ModelPart bone43;
    private final ModelPart bone67;
    private final ModelPart bone61;
    private final ModelPart root;

    private final ModelPart throttle;


    public NukaConsoleModel(ModelPart root) {
        this.root = root;
        this.rotor_zminus3_yplus5_rotateY = root.getChild("rotor_zminus3_yplus5_rotateY");
        this.panels = root.getChild("panels");
        this.console = root.getChild("console");
        this.bone37 = root.getChild("bone37");
        this.bone43 = root.getChild("bone43");
        this.bone67 = root.getChild("bone67");
        this.bone61 = root.getChild("bone61");
        this.throttle = this.panels.getChild("North").getChild("bone148").getChild("bigLever1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition rotor_zminus3_yplus5_rotateY = partdefinition.addOrReplaceChild("rotor_zminus3_yplus5_rotateY", CubeListBuilder.create().texOffs(62, 23).addBox(-5.0F, -22.0F, -8.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition bone111 = rotor_zminus3_yplus5_rotateY.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(62, 23).addBox(-5.0F, -22.0F, -8.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(62, 23).addBox(-5.0F, -22.0F, -8.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition panels = partdefinition.addOrReplaceChild("panels", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition North = panels.addOrReplaceChild("North", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone148 = North.addOrReplaceChild("bone148", CubeListBuilder.create().texOffs(88, 46).addBox(1.0F, -0.75F, 6.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(88, 46).addBox(-2.75F, -0.75F, 6.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(61, 55).addBox(-3.5F, -0.1F, 5.75F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(-1.0F, -0.75F, 1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 87).addBox(-5.25F, -0.025F, 1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(14, 47).addBox(1.25F, -0.125F, 3.25F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(82, 54).addBox(0.75F, -0.025F, 3.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(77, 8).addBox(0.25F, -0.075F, 1.25F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition gauge1 = bone148.addOrReplaceChild("gauge1", CubeListBuilder.create().texOffs(13, 73).addBox(-0.25F, -0.5F, -0.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.35F, 8.0F, 0.0F, 2.1817F, 0.0F));

        PartDefinition gauge2 = bone148.addOrReplaceChild("gauge2", CubeListBuilder.create().texOffs(13, 73).addBox(-0.25F, -0.5F, -0.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -0.35F, 8.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bigLever1 = bone148.addOrReplaceChild("bigLever1", CubeListBuilder.create().texOffs(88, 51).addBox(-1.5F, -2.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(41, 90).addBox(-1.5F, -2.25F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-3.75F, 0.475F, 2.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition smallLever1 = bone148.addOrReplaceChild("smallLever1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, -2.625F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(6, 39).addBox(-0.5F, -2.875F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.125F, 3.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition North_left = panels.addOrReplaceChild("North_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone114 = North_left.addOrReplaceChild("bone114", CubeListBuilder.create().texOffs(0, 65).addBox(-6.5F, -0.25F, 1.5F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(31, 25).addBox(-4.75F, -0.05F, 1.5F, 11.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(88, 12).addBox(0.5F, -0.75F, 5.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(82, 87).addBox(0.75F, -0.85F, 5.25F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(-1.0F, -0.75F, 2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(87, 79).addBox(-6.25F, -0.35F, 1.75F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 73).addBox(-4.5F, -0.35F, 1.75F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition smallLever2 = bone114.addOrReplaceChild("smallLever2", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, -2.625F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(6, 39).addBox(-0.5F, -2.875F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.125F, 4.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone119 = bone114.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(76, 61).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 81).addBox(-2.5F, -0.025F, -2.75F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(28, 84).addBox(-2.0F, -0.125F, -2.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, 0.0F, 4.5F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone115 = bone114.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -8.5F, 2.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 91).addBox(-1.0F, -9.5F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 6.5F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone116 = bone115.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(34, 66).addBox(-0.5F, -1.0F, -1.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 2.5F, 0.5236F, 0.0F, 0.0F));

        PartDefinition South_left = panels.addOrReplaceChild("South_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone120 = South_left.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(69, 87).addBox(-4.0F, -0.75F, 5.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 87).addBox(-5.0F, -0.25F, 3.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(75, 2).addBox(-6.0F, -0.025F, 0.75F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 35).addBox(-0.75F, -0.025F, 1.25F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(82, 54).addBox(1.5F, -0.025F, 1.25F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(14, 47).addBox(2.0F, -0.125F, 1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 87).addBox(1.75F, -0.025F, 4.25F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(19, 59).addBox(-2.0F, -0.525F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 35).addBox(-0.25F, -0.525F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 59).addBox(-6.0F, -0.525F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bigLever = bone120.addOrReplaceChild("bigLever", CubeListBuilder.create().texOffs(88, 51).addBox(-1.5F, -2.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(41, 90).addBox(-1.5F, -2.25F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(3.25F, 0.475F, 5.75F, -0.9163F, 0.0F, 0.0F));

        PartDefinition button2 = bone120.addOrReplaceChild("button2", CubeListBuilder.create().texOffs(90, 0).addBox(-3.5F, -18.0F, -12.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 16.0F, 18.6F));

        PartDefinition bone124 = bone120.addOrReplaceChild("bone124", CubeListBuilder.create().texOffs(28, 47).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -0.025F, 2.0F, 0.5672F, 0.0F, 0.0F));

        PartDefinition South = panels.addOrReplaceChild("South", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone127 = South.addOrReplaceChild("bone127", CubeListBuilder.create().texOffs(18, 68).addBox(0.75F, -1.025F, 1.25F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(45, 77).addBox(-2.75F, -0.075F, 1.25F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(61, 35).addBox(-6.0F, -0.075F, 0.75F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 38).addBox(-6.75F, -0.575F, 1.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-4.25F, -0.175F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 22).addBox(-3.0F, -0.225F, 1.75F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(58, 80).addBox(1.25F, -1.125F, 1.75F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition smallLever = bone127.addOrReplaceChild("smallLever", CubeListBuilder.create().texOffs(0, 15).addBox(-3.75F, -2.625F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(6, 39).addBox(-3.75F, -2.875F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.125F, 3.75F, -0.6981F, 0.0F, 0.0F));

        PartDefinition reallysmallLever = bone127.addOrReplaceChild("reallysmallLever", CubeListBuilder.create().texOffs(19, 55).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -0.575F, 2.25F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone131 = bone127.addOrReplaceChild("bone131", CubeListBuilder.create().texOffs(0, 39).addBox(-4.25F, -16.75F, -16.1F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 18.35F));

        PartDefinition bone121 = bone127.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(50, 6).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 86).addBox(-3.5F, -0.025F, -2.25F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(84, 41).addBox(-3.6F, -0.125F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(-3.25F, -1.525F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, 10.75F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone122 = bone121.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(11, 91).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.025F, -2.75F, 0.3927F, 0.0F, 0.0F));

        PartDefinition button = bone122.addOrReplaceChild("button", CubeListBuilder.create().texOffs(90, 0).addBox(1.5F, -16.525F, -10.6F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offset(-2.5F, 15.775F, 10.6F));

        PartDefinition South_right = panels.addOrReplaceChild("South_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone117 = South_right.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(42, 55).addBox(-7.0F, -0.075F, 1.75F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 15).addBox(4.0F, -0.325F, -0.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(69, 17).addBox(-0.5F, -0.825F, 3.75F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 35).addBox(4.5F, -0.325F, 4.75F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(80, 31).addBox(-5.25F, -0.325F, 4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition switches = bone117.addOrReplaceChild("switches", CubeListBuilder.create().texOffs(19, 52).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -0.075F, 2.25F, 0.4363F, 0.0F, 0.0F));

        PartDefinition dial1 = bone117.addOrReplaceChild("dial1", CubeListBuilder.create().texOffs(24, 33).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, -0.575F, 5.75F, -0.3054F, 0.0F, 0.0F));

        PartDefinition dial2 = bone117.addOrReplaceChild("dial2", CubeListBuilder.create().texOffs(24, 33).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.575F, 5.75F, 0.5672F, 0.0F, 0.0F));

        PartDefinition dial3 = bone117.addOrReplaceChild("dial3", CubeListBuilder.create().texOffs(24, 33).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, -0.575F, 5.75F, -0.6109F, 0.0F, 0.0F));

        PartDefinition smallLever3 = bone117.addOrReplaceChild("smallLever3", CubeListBuilder.create().texOffs(10, 15).addBox(0.0F, -2.25F, -0.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 39).addBox(-0.5F, -3.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.175F, 5.75F, -0.7854F, 0.0F, 0.0F));

        PartDefinition keyboard = bone117.addOrReplaceChild("keyboard", CubeListBuilder.create().texOffs(50, 0).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(65, 12).addBox(-4.0F, -0.1F, -3.75F, 8.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 1.5F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone128 = bone117.addOrReplaceChild("bone128", CubeListBuilder.create().texOffs(84, 17).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.825F, 7.75F, 0.829F, 0.0F, 0.0F));

        PartDefinition North_right = panels.addOrReplaceChild("North_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone136 = North_right.addOrReplaceChild("bone136", CubeListBuilder.create().texOffs(77, 8).addBox(-0.25F, -0.075F, 1.25F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(83, 73).addBox(-4.25F, -0.075F, 1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(75, 82).addBox(-4.25F, -0.275F, 1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(80, 37).addBox(-4.0F, -0.075F, 4.25F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 23).addBox(-3.5F, -1.075F, 6.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition smallLever4 = bone136.addOrReplaceChild("smallLever4", CubeListBuilder.create().texOffs(0, 15).addBox(-3.75F, -2.625F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(6, 39).addBox(-3.75F, -2.875F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -0.125F, 3.25F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone125 = bone136.addOrReplaceChild("bone125", CubeListBuilder.create().texOffs(35, 55).addBox(2.0F, -17.325F, -15.1F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(71, 75).addBox(1.5F, -16.575F, -15.6F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 18.6F));

        PartDefinition bone140 = bone136.addOrReplaceChild("bone140", CubeListBuilder.create().texOffs(0, 39).addBox(-4.25F, -16.75F, -16.1F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 16.0F, 17.85F));

        PartDefinition bone139 = bone136.addOrReplaceChild("bone139", CubeListBuilder.create().texOffs(0, 10).addBox(-2.25F, 0.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -0.075F, 5.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone137 = bone136.addOrReplaceChild("bone137", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, -0.825F, 8.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone138 = bone137.addOrReplaceChild("bone138", CubeListBuilder.create().texOffs(70, 31).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition console = partdefinition.addOrReplaceChild("console", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone85 = console.addOrReplaceChild("bone85", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone73 = bone85.addOrReplaceChild("bone73", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone79 = bone73.addOrReplaceChild("bone79", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -19.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -19.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone76 = bone74.addOrReplaceChild("bone76", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -19.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone78 = bone76.addOrReplaceChild("bone78", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80 = bone78.addOrReplaceChild("bone80", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -19.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone81 = bone78.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -19.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone83 = bone81.addOrReplaceChild("bone83", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -19.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone92 = bone85.addOrReplaceChild("bone92", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create().texOffs(34, 46).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -12.0F, -15.6F, 1.1345F, 0.0F, 0.0F));

        PartDefinition bone94 = bone92.addOrReplaceChild("bone94", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create().texOffs(34, 46).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -12.0F, -15.6F, 1.1345F, 0.0F, 0.0F));

        PartDefinition bone96 = bone94.addOrReplaceChild("bone96", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = bone96.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(34, 46).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -12.0F, -15.6F, 1.1345F, 0.0F, 0.0F));

        PartDefinition bone98 = bone96.addOrReplaceChild("bone98", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(34, 46).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -12.0F, -15.6F, 1.1345F, 0.0F, 0.0F));

        PartDefinition bone100 = bone98.addOrReplaceChild("bone100", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(34, 46).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -12.0F, -15.6F, 1.1345F, 0.0F, 0.0F));

        PartDefinition bone102 = bone100.addOrReplaceChild("bone102", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone103 = bone102.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(34, 46).addBox(-8.0F, 0.0F, 0.0F, 17.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -12.0F, -15.6F, 1.1345F, 0.0F, 0.0F));

        PartDefinition bone55 = bone85.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(28, 89).addBox(-2.0F, -11.0F, -9.475F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone56 = bone55.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(28, 89).addBox(-2.0F, -11.0F, -9.475F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone57 = bone56.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(28, 89).addBox(-2.0F, -11.0F, -9.475F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58 = bone57.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(28, 89).addBox(-2.0F, -11.0F, -9.475F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone59 = bone58.addOrReplaceChild("bone59", CubeListBuilder.create().texOffs(28, 89).addBox(-2.0F, -11.0F, -9.475F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = bone59.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(28, 89).addBox(-2.0F, -11.0F, -9.475F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49 = bone85.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(76, 67).addBox(-3.0F, -11.0F, -9.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone50 = bone49.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(76, 67).addBox(-3.0F, -11.0F, -9.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(76, 67).addBox(-3.0F, -11.0F, -9.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(76, 67).addBox(-3.0F, -11.0F, -9.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(76, 67).addBox(-3.0F, -11.0F, -9.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(76, 67).addBox(-3.0F, -11.0F, -9.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = bone85.addOrReplaceChild("bone25", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition bone31 = bone25.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone32 = bone26.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone29 = bone27.addOrReplaceChild("bone29", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone33 = bone29.addOrReplaceChild("bone33", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone35 = bone33.addOrReplaceChild("bone35", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -18.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition bone13 = bone85.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone19 = bone13.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -22.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone20 = bone14.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -22.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -22.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone17 = bone15.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -22.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone21 = bone17.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -22.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone23 = bone21.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -22.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition bone7 = bone85.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(28, 75).addBox(-2.0F, -12.0F, -22.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(28, 75).addBox(-2.0F, -12.0F, -22.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(28, 75).addBox(-2.0F, -12.0F, -22.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(28, 75).addBox(-2.0F, -12.0F, -22.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(28, 75).addBox(-2.0F, -12.0F, -22.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(28, 75).addBox(-2.0F, -12.0F, -22.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone86 = bone85.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(71, 43).addBox(-2.0F, -14.0F, -11.35F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create().texOffs(71, 43).addBox(-2.0F, -14.0F, -11.35F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(71, 43).addBox(-2.0F, -14.0F, -11.35F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(71, 43).addBox(-2.0F, -14.0F, -11.35F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(71, 43).addBox(-2.0F, -14.0F, -11.35F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91 = bone90.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(71, 43).addBox(-2.0F, -14.0F, -11.35F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone104 = bone85.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, -20.0F, -10.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, -20.0F, -10.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, -20.0F, -10.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone107 = bone106.addOrReplaceChild("bone107", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, -20.0F, -10.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, -20.0F, -10.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone109 = bone108.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, -20.0F, -10.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone142 = bone85.addOrReplaceChild("bone142", CubeListBuilder.create().texOffs(35, 35).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition bone143 = bone142.addOrReplaceChild("bone143", CubeListBuilder.create().texOffs(35, 35).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone144 = bone143.addOrReplaceChild("bone144", CubeListBuilder.create().texOffs(35, 35).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone145 = bone144.addOrReplaceChild("bone145", CubeListBuilder.create().texOffs(35, 35).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone146 = bone145.addOrReplaceChild("bone146", CubeListBuilder.create().texOffs(35, 35).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone147 = bone146.addOrReplaceChild("bone147", CubeListBuilder.create().texOffs(35, 35).addBox(-4.0F, -9.0F, -9.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone = bone85.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(24, 15).addBox(-9.0F, -11.0F, -19.6F, 18.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(24, 15).addBox(-9.0F, -11.0F, -19.6F, 18.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(24, 15).addBox(-9.0F, -11.0F, -19.6F, 18.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(24, 15).addBox(-9.0F, -11.0F, -19.6F, 18.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(24, 15).addBox(-9.0F, -11.0F, -19.6F, 18.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(24, 15).addBox(-9.0F, -11.0F, -19.6F, 18.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = partdefinition.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(19, 75).addBox(-1.0F, -23.0F, -8.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone38 = bone37.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(19, 75).addBox(-1.0F, -23.0F, -8.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(19, 75).addBox(-1.0F, -23.0F, -8.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(19, 75).addBox(-1.0F, -23.0F, -8.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(19, 75).addBox(-1.0F, -23.0F, -8.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(19, 75).addBox(-1.0F, -23.0F, -8.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone43 = partdefinition.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(61, 63).addBox(-3.0F, -23.0F, -7.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

        PartDefinition bone44 = bone43.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(61, 63).addBox(-3.0F, -23.0F, -7.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(61, 63).addBox(-3.0F, -23.0F, -7.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(61, 63).addBox(-3.0F, -23.0F, -7.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(85, 23).addBox(-2.5F, -21.5F, -7.35F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 84).addBox(-2.5F, -16.5F, -7.35F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(61, 63).addBox(-3.0F, -23.0F, -7.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(61, 63).addBox(-3.0F, -23.0F, -7.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone67 = partdefinition.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(0, 33).addBox(-3.0F, -10.0F, -9.175F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition bone68 = bone67.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(0, 33).addBox(-3.0F, -10.0F, -9.175F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(0, 33).addBox(-3.0F, -10.0F, -9.175F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone70 = bone69.addOrReplaceChild("bone70", CubeListBuilder.create().texOffs(0, 33).addBox(-3.0F, -10.0F, -9.175F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(0, 33).addBox(-3.0F, -10.0F, -9.175F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone72 = bone71.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(0, 33).addBox(-3.0F, -10.0F, -9.175F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone61 = partdefinition.addOrReplaceChild("bone61", CubeListBuilder.create().texOffs(19, 55).addBox(-2.0F, -11.0F, -9.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone62 = bone61.addOrReplaceChild("bone62", CubeListBuilder.create().texOffs(19, 55).addBox(-2.0F, -11.0F, -9.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone63 = bone62.addOrReplaceChild("bone63", CubeListBuilder.create().texOffs(19, 55).addBox(-2.0F, -11.0F, -9.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create().texOffs(19, 55).addBox(-2.0F, -11.0F, -9.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create().texOffs(19, 55).addBox(-2.0F, -11.0F, -9.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66 = bone65.addOrReplaceChild("bone66", CubeListBuilder.create().texOffs(19, 55).addBox(-2.0F, -11.0F, -9.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        rotor_zminus3_yplus5_rotateY.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        panels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        console.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone37.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone43.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone67.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone61.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        panels.getAllParts().forEach(ModelPart::resetPose);
        console.getAllParts().forEach(ModelPart::resetPose);

        TardisClientData reactions = TardisClientData.getInstance(level.dimension());

        if (globalConsoleBlock != null && globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            this.animate(reactions.ROTOR_ANIMATION, FLIGHT, Minecraft.getInstance().player.tickCount);
        }

        float rot = -1f + (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        throttle.xRot = rot;

        rotor_zminus3_yplus5_rotateY.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        panels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        console.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone37.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone43.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone67.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bone61.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return NUKA_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.NUKA.getId();
    }
}