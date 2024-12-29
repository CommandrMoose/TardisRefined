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
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class VictorianConsoleModel extends HierarchicalModel implements ConsoleUnit {


    public static final AnimationDefinition IDLE = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/victorian/idle.json"));
    public static final AnimationDefinition FLIGHT = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/victorian/flight.json"));
    public static final AnimationDefinition CRASH = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/victorian/crash.json"));
    public static final AnimationDefinition POWER_ON = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/victorian/power_on.json"));
    public static final AnimationDefinition POWER_OFF = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/victorian/power_off.json"));

    private static final ResourceLocation VICTORIAN_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/victorian/victorian_console.png");

    private final ModelPart root;

    private final ModelPart throttle_control;

    public VictorianConsoleModel(ModelPart root) {
        this.root = root;
        this.throttle_control = Frame.findPart(this, "bone187");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition controls = partdefinition.addOrReplaceChild("controls", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition north = controls.addOrReplaceChild("north", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone176 = north.addOrReplaceChild("bone176", CubeListBuilder.create().texOffs(73, 38).addBox(-2.5F, -0.7333F, -10.0271F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 42).addBox(-1.5F, -0.0833F, -5.0271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(82, 32).addBox(-2.5F, -0.0833F, -8.0271F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(17, 70).addBox(2.5F, -0.0833F, -9.5271F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(17, 70).mirror().addBox(-5.5F, -0.0833F, -9.5271F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(87, 49).addBox(-1.0F, -0.4833F, -4.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone176_r1 = bone176.addOrReplaceChild("bone176_r1", CubeListBuilder.create().texOffs(69, 81).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.7333F, -9.0271F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone163 = bone176.addOrReplaceChild("bone163", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, 0.725F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -0.8333F, -3.0271F));

        PartDefinition bone165 = bone176.addOrReplaceChild("bone165", CubeListBuilder.create().texOffs(88, 39).addBox(-1.0F, 0.725F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -0.8333F, -3.0271F));

        PartDefinition bone164 = bone176.addOrReplaceChild("bone164", CubeListBuilder.create().texOffs(86, 35).addBox(-1.0F, 0.725F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.8333F, -4.0271F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone166 = bone176.addOrReplaceChild("bone166", CubeListBuilder.create().texOffs(88, 39).addBox(-1.0F, 0.725F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.8333F, -4.0271F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone234 = bone176.addOrReplaceChild("bone234", CubeListBuilder.create(), PartPose.offset(-3.5F, 0.0167F, -2.0271F));

        PartDefinition bone177_r1 = bone234.addOrReplaceChild("bone177_r1", CubeListBuilder.create().texOffs(72, 58).addBox(-6.5F, -3.5F, 0.0F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.5672F, -0.3054F));

        PartDefinition bone127 = bone234.addOrReplaceChild("bone127", CubeListBuilder.create(), PartPose.offset(-2.8824F, -1.5544F, 2.4674F));

        PartDefinition bone178_r1 = bone127.addOrReplaceChild("bone178_r1", CubeListBuilder.create().texOffs(0, 91).addBox(-6.25F, -3.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8824F, 1.5544F, -2.4674F, -0.3491F, 0.5672F, -0.3054F));

        PartDefinition bone128 = bone234.addOrReplaceChild("bone128", CubeListBuilder.create(), PartPose.offset(-2.8824F, -1.5544F, 2.4674F));

        PartDefinition bone179_r1 = bone128.addOrReplaceChild("bone179_r1", CubeListBuilder.create().texOffs(0, 94).addBox(-6.25F, -3.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.8824F, 1.5544F, -2.4674F, -0.3491F, 0.5672F, -0.3054F));

        PartDefinition bone135 = bone176.addOrReplaceChild("bone135", CubeListBuilder.create().texOffs(80, 53).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.4167F, -0.7771F));

        PartDefinition bone136 = bone176.addOrReplaceChild("bone136", CubeListBuilder.create().texOffs(93, 53).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.3917F, -0.7771F));

        PartDefinition bone178 = bone176.addOrReplaceChild("bone178", CubeListBuilder.create().texOffs(24, 63).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 51).addBox(-1.0F, -0.5F, -0.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2333F, -3.5271F, 0.0F, -0.3927F, 0.0F));

        PartDefinition bone175 = bone176.addOrReplaceChild("bone175", CubeListBuilder.create().texOffs(9, 87).addBox(-1.0F, -0.9F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 61).addBox(-0.5F, -1.4F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.4167F, -5.5271F));

        PartDefinition bone175_r1 = bone175.addOrReplaceChild("bone175_r1", CubeListBuilder.create().texOffs(0, 67).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone193 = bone175.addOrReplaceChild("bone193", CubeListBuilder.create().texOffs(27, 85).addBox(-4.5F, -22.6559F, -13.2736F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 21.2809F, 12.7736F));

        PartDefinition bone177 = bone176.addOrReplaceChild("bone177", CubeListBuilder.create().texOffs(9, 87).mirror().addBox(-1.0F, -0.9F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(90, 61).mirror().addBox(-0.5F, -1.4F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.4167F, -5.5271F));

        PartDefinition bone177_r2 = bone177.addOrReplaceChild("bone177_r2", CubeListBuilder.create().texOffs(0, 67).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone194 = bone177.addOrReplaceChild("bone194", CubeListBuilder.create().texOffs(27, 85).mirror().addBox(3.5F, -22.6559F, -13.2736F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 21.2809F, 12.7736F));

        PartDefinition north_right = controls.addOrReplaceChild("north_right", CubeListBuilder.create().texOffs(56, 39).addBox(-7.5F, -9.1F, -19.5236F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(73, 27).addBox(-6.5F, -9.15F, -19.5236F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(67, 0).addBox(2.5F, -9.4F, -19.2736F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone181 = north_right.addOrReplaceChild("bone181", CubeListBuilder.create().texOffs(78, 85).addBox(-1.0F, -0.4833F, -2.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).addBox(-3.5F, -0.4833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).mirror().addBox(1.5F, -0.4833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 73).addBox(-3.0F, -0.7333F, -5.5271F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(83, 89).addBox(-4.0F, -0.7333F, -5.5271F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(83, 89).addBox(-1.0F, -0.4833F, -10.5271F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(83, 89).mirror().addBox(0.0F, -0.4833F, -10.5271F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 73).mirror().addBox(-3.0F, -0.4833F, -8.0271F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 73).mirror().addBox(-7.5F, -0.4833F, -10.5271F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 73).addBox(1.5F, -0.4833F, -10.5271F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 81).addBox(-6.25F, -0.3333F, -8.0271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 39).addBox(3.5F, -0.0833F, -7.7771F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone255 = bone181.addOrReplaceChild("bone255", CubeListBuilder.create().texOffs(38, 49).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(81, 0).addBox(-2.5F, -0.25F, -3.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(81, 0).addBox(-7.0F, -0.25F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 49).addBox(2.0F, -0.25F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2333F, -4.5271F));

        PartDefinition bone256 = bone181.addOrReplaceChild("bone256", CubeListBuilder.create().texOffs(81, 0).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 63).addBox(-2.5F, -0.25F, -3.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 49).addBox(-7.0F, -0.25F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 63).addBox(2.0F, -0.25F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2333F, -4.5271F));

        PartDefinition bone257 = bone181.addOrReplaceChild("bone257", CubeListBuilder.create().texOffs(82, 21).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 21).addBox(-2.5F, -0.25F, -3.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 49).addBox(-7.0F, -0.25F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 49).addBox(2.0F, -0.25F, -5.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2333F, -4.5271F));

        PartDefinition bone232 = bone181.addOrReplaceChild("bone232", CubeListBuilder.create(), PartPose.offset(0.0F, -0.0833F, -1.5271F));

        PartDefinition bone178_r2 = bone232.addOrReplaceChild("bone178_r2", CubeListBuilder.create().texOffs(19, 79).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

        PartDefinition bone245 = bone181.addOrReplaceChild("bone245", CubeListBuilder.create(), PartPose.offset(2.5F, -0.0833F, -2.0271F));

        PartDefinition bone179_r2 = bone245.addOrReplaceChild("bone179_r2", CubeListBuilder.create().texOffs(19, 79).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone244 = bone181.addOrReplaceChild("bone244", CubeListBuilder.create(), PartPose.offset(-2.5F, -0.0833F, -2.0271F));

        PartDefinition bone179_r3 = bone244.addOrReplaceChild("bone179_r3", CubeListBuilder.create().texOffs(19, 79).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.5744F, 0.0F));

        PartDefinition bone258 = bone181.addOrReplaceChild("bone258", CubeListBuilder.create().texOffs(13, 59).addBox(0.0F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -0.3025F, -6.5271F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone259 = bone181.addOrReplaceChild("bone259", CubeListBuilder.create().texOffs(13, 59).addBox(0.0F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -0.3025F, -7.5271F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone192 = bone181.addOrReplaceChild("bone192", CubeListBuilder.create().texOffs(44, 54).addBox(-2.75F, -25.7809F, -10.7736F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 59).addBox(-3.25F, -26.7809F, -11.0236F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 22.6975F, 4.4965F));

        PartDefinition bone233 = bone192.addOrReplaceChild("bone233", CubeListBuilder.create(), PartPose.offset(-2.75F, -23.2809F, -10.2736F));

        PartDefinition bone184_r1 = bone233.addOrReplaceChild("bone184_r1", CubeListBuilder.create().texOffs(47, 0).addBox(0.0F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition bone182 = north_right.addOrReplaceChild("bone182", CubeListBuilder.create().texOffs(13, 59).addBox(0.0F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -8.75F, -16.7736F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone249 = north_right.addOrReplaceChild("bone249", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.75F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -8.75F, -16.7736F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone250 = north_right.addOrReplaceChild("bone250", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.75F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -8.75F, -17.7736F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone251 = north_right.addOrReplaceChild("bone251", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.75F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -8.75F, -18.7736F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone252 = north_right.addOrReplaceChild("bone252", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.75F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -8.75F, -18.7736F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone253 = north_right.addOrReplaceChild("bone253", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.75F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -8.75F, -17.7736F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone254 = north_right.addOrReplaceChild("bone254", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.75F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -8.75F, -16.7736F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone236 = north_right.addOrReplaceChild("bone236", CubeListBuilder.create().texOffs(13, 59).addBox(0.0F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -8.75F, -17.7736F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone248 = north_right.addOrReplaceChild("bone248", CubeListBuilder.create().texOffs(13, 59).addBox(0.0F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.75F, -8.75F, -18.7736F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone272 = north_right.addOrReplaceChild("bone272", CubeListBuilder.create().texOffs(87, 85).addBox(-1.5F, -1.25F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -9.4F, -18.5236F));

        PartDefinition bone238 = north_right.addOrReplaceChild("bone238", CubeListBuilder.create().texOffs(87, 85).mirror().addBox(-1.5F, -1.25F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -9.65F, -17.7736F));

        PartDefinition bone239 = north_right.addOrReplaceChild("bone239", CubeListBuilder.create(), PartPose.offset(5.0F, -9.4F, -17.2736F));

        PartDefinition bone180_r1 = bone239.addOrReplaceChild("bone180_r1", CubeListBuilder.create().texOffs(85, 7).addBox(-1.5F, -1.5F, 0.25F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition south_right = controls.addOrReplaceChild("south_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone183 = south_right.addOrReplaceChild("bone183", CubeListBuilder.create().texOffs(21, 49).addBox(-1.5F, -0.0833F, -5.0271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(47, 0).addBox(-3.5F, -0.4833F, -10.2771F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(84, 12).addBox(-1.0F, -0.0833F, -2.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(69, 51).addBox(-0.5F, -0.4833F, -1.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 72).mirror().addBox(-6.25F, -0.5833F, -9.5271F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(82, 72).addBox(4.25F, -0.5833F, -9.5271F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone246 = bone183.addOrReplaceChild("bone246", CubeListBuilder.create().texOffs(85, 24).addBox(-0.75F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -0.5083F, -7.7771F));

        PartDefinition bone247 = bone183.addOrReplaceChild("bone247", CubeListBuilder.create().texOffs(90, 89).addBox(-0.25F, -0.5F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 89).addBox(1.75F, -0.5F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 89).addBox(1.75F, -0.5F, -3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 89).addBox(-0.25F, -0.5F, -3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -0.5083F, -6.2771F));

        PartDefinition bone235 = bone183.addOrReplaceChild("bone235", CubeListBuilder.create().texOffs(73, 32).addBox(-3.25F, -22.7059F, -18.0236F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(69, 66).addBox(-8.0F, -22.7059F, -18.0236F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 22.1975F, 7.9965F));

        PartDefinition bone133 = bone183.addOrReplaceChild("bone133", CubeListBuilder.create().texOffs(0, 87).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.2333F, -3.5271F));

        PartDefinition bone134 = bone183.addOrReplaceChild("bone134", CubeListBuilder.create().texOffs(70, 90).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.2667F, -3.5271F));

        PartDefinition bone260 = bone183.addOrReplaceChild("bone260", CubeListBuilder.create().texOffs(76, 89).mirror().addBox(-3.25F, -0.75F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(76, 89).addBox(2.25F, -0.75F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0167F, -2.0271F, -0.2618F, 0.0F, 0.0F));

        PartDefinition south = controls.addOrReplaceChild("south", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone184 = south.addOrReplaceChild("bone184", CubeListBuilder.create().texOffs(47, 0).addBox(-3.5F, -0.4833F, -10.2771F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(74, 48).addBox(0.0F, -1.4833F, -9.2771F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(90, 70).addBox(-1.375F, -0.9833F, -9.2771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 70).addBox(-2.625F, -0.9833F, -9.2771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 66).addBox(-3.25F, -0.5833F, -8.0271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(49, 80).addBox(-2.0F, -0.4833F, -2.5271F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).mirror().addBox(-3.5F, -0.5833F, -4.2771F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 29).addBox(1.5F, -0.5833F, -4.2771F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 49).addBox(-1.5F, -0.0833F, -5.0271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(34, 89).addBox(4.25F, -0.5834F, -9.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(18, 85).addBox(4.25F, -1.0833F, -9.5271F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 89).mirror().addBox(-6.25F, -0.5834F, -9.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(18, 85).mirror().addBox(-6.25F, -1.0833F, -9.5271F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone183_r1 = bone184.addOrReplaceChild("bone183_r1", CubeListBuilder.create().texOffs(58, 88).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -0.5833F, -7.7771F, 0.0F, -0.6109F, 0.0F));

        PartDefinition bone222 = bone184.addOrReplaceChild("bone222", CubeListBuilder.create().texOffs(0, 59).addBox(-0.75F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(86, 16).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -1.0833F, -7.2771F, 0.0F, -0.48F, 0.0F));

        PartDefinition bone223 = bone184.addOrReplaceChild("bone223", CubeListBuilder.create().texOffs(0, 59).addBox(-0.75F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(86, 16).addBox(-1.25F, -1.0F, -0.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -0.3333F, -7.2771F, 0.0F, 0.829F, 0.0F));

        PartDefinition bone168 = bone184.addOrReplaceChild("bone168", CubeListBuilder.create(), PartPose.offset(2.5F, -0.8333F, -3.7771F));

        PartDefinition bone182_r1 = bone168.addOrReplaceChild("bone182_r1", CubeListBuilder.create().texOffs(89, 57).addBox(-0.25F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition bone167 = bone184.addOrReplaceChild("bone167", CubeListBuilder.create(), PartPose.offset(-2.5F, -0.8333F, -3.7771F));

        PartDefinition bone183_r2 = bone167.addOrReplaceChild("bone183_r2", CubeListBuilder.create().texOffs(89, 57).mirror().addBox(-1.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition bone131 = bone184.addOrReplaceChild("bone131", CubeListBuilder.create().texOffs(60, 83).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0167F, -3.7771F));

        PartDefinition bone132 = bone184.addOrReplaceChild("bone132", CubeListBuilder.create().texOffs(70, 90).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0167F, -3.7771F));

        PartDefinition south_left = controls.addOrReplaceChild("south_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone185 = south_left.addOrReplaceChild("bone185", CubeListBuilder.create().texOffs(13, 73).addBox(-5.0F, -0.2333F, -10.0271F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(59, 58).addBox(-1.5F, -0.4833F, -10.0271F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-1.25F, -0.5833F, -10.2771F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(90, 89).addBox(-3.5F, -0.7333F, -9.2771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(90, 89).addBox(-3.5F, -0.7333F, -7.7771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).addBox(-3.5F, -0.4833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).mirror().addBox(1.5F, -0.4833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 39).mirror().addBox(-1.0F, -0.4833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(69, 85).addBox(3.0F, -1.4833F, -9.0271F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 77).mirror().addBox(2.0F, -0.0833F, -5.5271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(82, 77).addBox(-5.0F, -0.0833F, -5.5271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone204 = bone185.addOrReplaceChild("bone204", CubeListBuilder.create(), PartPose.offset(-2.5F, -0.0833F, -2.0271F));

        PartDefinition bone181_r1 = bone204.addOrReplaceChild("bone181_r1", CubeListBuilder.create().texOffs(19, 79).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));

        PartDefinition bone205 = bone185.addOrReplaceChild("bone205", CubeListBuilder.create(), PartPose.offset(0.0F, -0.0833F, -2.0271F));

        PartDefinition bone182_r2 = bone205.addOrReplaceChild("bone182_r2", CubeListBuilder.create().texOffs(19, 79).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));

        PartDefinition bone206 = bone185.addOrReplaceChild("bone206", CubeListBuilder.create(), PartPose.offset(2.5F, -0.0833F, -2.0271F));

        PartDefinition bone183_r3 = bone206.addOrReplaceChild("bone183_r3", CubeListBuilder.create().texOffs(19, 79).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));

        PartDefinition bone207 = bone185.addOrReplaceChild("bone207", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.4833F, -5.0271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone208 = bone185.addOrReplaceChild("bone208", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.4833F, -6.0271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone209 = bone185.addOrReplaceChild("bone209", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.4833F, -7.0271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone224 = bone185.addOrReplaceChild("bone224", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.4833F, -8.0271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone225 = bone185.addOrReplaceChild("bone225", CubeListBuilder.create().texOffs(17, 90).addBox(-0.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.4833F, -9.0271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone187 = bone185.addOrReplaceChild("bone187", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -1.4833F, -8.0271F, 0.0F, 0.5236F, 0.0F));

        PartDefinition bone185_r1 = bone187.addOrReplaceChild("bone185_r1", CubeListBuilder.create().texOffs(26, 34).addBox(-0.25F, -0.9F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, -0.25F, 0.0873F, 0.0F, 0.0F));

        PartDefinition bone190 = bone185.addOrReplaceChild("bone190", CubeListBuilder.create().texOffs(82, 3).addBox(-3.5F, -22.8808F, -9.7736F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 22.6975F, 3.9965F));

        PartDefinition bone189 = bone185.addOrReplaceChild("bone189", CubeListBuilder.create().texOffs(82, 3).mirror().addBox(0.5F, -22.8808F, -9.7736F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.25F, 22.6975F, 3.9965F));

        PartDefinition north_left = controls.addOrReplaceChild("north_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone179 = north_left.addOrReplaceChild("bone179", CubeListBuilder.create().texOffs(56, 45).addBox(-3.0F, -0.2333F, -10.5271F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(47, 7).addBox(0.0F, -0.3333F, -10.2771F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(67, 90).addBox(-1.5F, -2.8333F, -9.2771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(39, 73).addBox(-1.25F, -1.8333F, -8.7771F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(25, 88).addBox(-2.0F, -0.4833F, -9.7771F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(35, 78).addBox(3.5F, -0.0083F, -10.5271F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(90, 70).addBox(-5.5F, -0.5833F, -9.0271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 54).addBox(-4.0F, -0.4833F, -5.2771F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(59, 54).addBox(-4.0F, -0.4833F, -6.7771F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).addBox(-3.5F, -0.2333F, -2.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 39).mirror().addBox(1.5F, -0.2333F, -2.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 39).mirror().addBox(-1.0F, -0.2333F, -2.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone191 = bone179.addOrReplaceChild("bone191", CubeListBuilder.create().texOffs(84, 44).addBox(2.5F, -22.8058F, -17.2736F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, 22.6975F, 6.7465F));

        PartDefinition bone162 = bone179.addOrReplaceChild("bone162", CubeListBuilder.create().texOffs(9, 79).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.4167F, -8.5271F));

        PartDefinition bone161 = bone179.addOrReplaceChild("bone161", CubeListBuilder.create().texOffs(19, 92).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.4167F, -8.5271F));

        PartDefinition bone138 = bone179.addOrReplaceChild("bone138", CubeListBuilder.create().texOffs(95, 64).mirror().addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -0.0083F, -4.2771F));

        PartDefinition bone160 = bone179.addOrReplaceChild("bone160", CubeListBuilder.create().texOffs(95, 66).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.0083F, -4.2771F));

        PartDefinition bone137 = bone179.addOrReplaceChild("bone137", CubeListBuilder.create().texOffs(95, 64).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -0.0083F, -4.2771F));

        PartDefinition bone158 = bone179.addOrReplaceChild("bone158", CubeListBuilder.create().texOffs(95, 64).mirror().addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -0.0083F, -5.7771F));

        PartDefinition bone157 = bone179.addOrReplaceChild("bone157", CubeListBuilder.create().texOffs(95, 66).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.0083F, -5.7771F));

        PartDefinition bone159 = bone179.addOrReplaceChild("bone159", CubeListBuilder.create().texOffs(95, 64).addBox(-1.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -0.0083F, -5.7771F));

        PartDefinition bone130 = bone179.addOrReplaceChild("bone130", CubeListBuilder.create(), PartPose.offset(-2.5F, -0.7333F, -1.5271F));

        PartDefinition bone186_r1 = bone130.addOrReplaceChild("bone186_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone185_r2 = bone130.addOrReplaceChild("bone185_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone129 = bone179.addOrReplaceChild("bone129", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7333F, -1.5271F));

        PartDefinition bone185_r3 = bone129.addOrReplaceChild("bone185_r3", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone184_r2 = bone129.addOrReplaceChild("bone184_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone195 = bone179.addOrReplaceChild("bone195", CubeListBuilder.create(), PartPose.offset(2.5F, -0.7333F, -1.5271F));

        PartDefinition bone184_r3 = bone195.addOrReplaceChild("bone184_r3", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone183_r4 = bone195.addOrReplaceChild("bone183_r4", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone188 = bone179.addOrReplaceChild("bone188", CubeListBuilder.create(), PartPose.offset(-2.5F, -0.7333F, -1.5271F));

        PartDefinition bone184_r4 = bone188.addOrReplaceChild("bone184_r4", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone183_r5 = bone188.addOrReplaceChild("bone183_r5", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone180 = bone179.addOrReplaceChild("bone180", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7333F, -1.5271F));

        PartDefinition bone182_r3 = bone180.addOrReplaceChild("bone182_r3", CubeListBuilder.create().texOffs(56, 23).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone181_r2 = bone180.addOrReplaceChild("bone181_r2", CubeListBuilder.create().texOffs(56, 23).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone186 = bone179.addOrReplaceChild("bone186", CubeListBuilder.create(), PartPose.offset(2.5F, -0.7333F, -1.5271F));

        PartDefinition bone183_r6 = bone186.addOrReplaceChild("bone183_r6", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone182_r4 = bone186.addOrReplaceChild("bone182_r4", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition base_console = partdefinition.addOrReplaceChild("base_console", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone61 = base_console.addOrReplaceChild("bone61", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone61_r1 = bone61.addOrReplaceChild("bone61_r1", CubeListBuilder.create().texOffs(29, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone62 = bone61.addOrReplaceChild("bone62", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone62_r1 = bone62.addOrReplaceChild("bone62_r1", CubeListBuilder.create().texOffs(29, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone63 = bone62.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone63_r1 = bone63.addOrReplaceChild("bone63_r1", CubeListBuilder.create().texOffs(29, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone64_r1 = bone64.addOrReplaceChild("bone64_r1", CubeListBuilder.create().texOffs(29, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone65_r1 = bone65.addOrReplaceChild("bone65_r1", CubeListBuilder.create().texOffs(29, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone66 = bone65.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66_r1 = bone66.addOrReplaceChild("bone66_r1", CubeListBuilder.create().texOffs(29, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone145 = base_console.addOrReplaceChild("bone145", CubeListBuilder.create().texOffs(0, 77).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone146 = bone145.addOrReplaceChild("bone146", CubeListBuilder.create().texOffs(0, 77).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone147 = bone146.addOrReplaceChild("bone147", CubeListBuilder.create().texOffs(0, 77).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone148 = bone147.addOrReplaceChild("bone148", CubeListBuilder.create().texOffs(0, 77).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone149 = bone148.addOrReplaceChild("bone149", CubeListBuilder.create().texOffs(0, 77).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone150 = bone149.addOrReplaceChild("bone150", CubeListBuilder.create().texOffs(0, 77).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone31 = base_console.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(75, 14).addBox(-1.5F, -3.975F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone32 = bone31.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(75, 14).addBox(-1.5F, -3.975F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(75, 14).addBox(-1.5F, -3.975F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(75, 14).addBox(-1.5F, -3.975F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(75, 14).addBox(-1.5F, -3.975F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(75, 14).addBox(-1.5F, -3.975F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone151 = base_console.addOrReplaceChild("bone151", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone151_r1 = bone151.addOrReplaceChild("bone151_r1", CubeListBuilder.create().texOffs(51, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone152 = bone151.addOrReplaceChild("bone152", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone152_r1 = bone152.addOrReplaceChild("bone152_r1", CubeListBuilder.create().texOffs(51, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone153 = bone152.addOrReplaceChild("bone153", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone153_r1 = bone153.addOrReplaceChild("bone153_r1", CubeListBuilder.create().texOffs(51, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone154 = bone153.addOrReplaceChild("bone154", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone154_r1 = bone154.addOrReplaceChild("bone154_r1", CubeListBuilder.create().texOffs(51, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone155 = bone154.addOrReplaceChild("bone155", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone155_r1 = bone155.addOrReplaceChild("bone155_r1", CubeListBuilder.create().texOffs(51, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone156 = bone155.addOrReplaceChild("bone156", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone156_r1 = bone156.addOrReplaceChild("bone156_r1", CubeListBuilder.create().texOffs(51, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone43 = base_console.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(13, 61).addBox(-1.0F, -2.025F, -22.725F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-0.5F, -2.35F, -23.075F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone44 = bone43.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(13, 61).addBox(-1.0F, -2.025F, -22.725F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-0.5F, -2.35F, -23.075F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(13, 61).addBox(-1.0F, -2.025F, -22.725F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-0.5F, -2.35F, -23.075F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(13, 61).addBox(-1.0F, -2.025F, -22.725F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-0.5F, -2.35F, -23.075F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(13, 61).addBox(-1.0F, -2.025F, -22.725F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-0.5F, -2.35F, -23.075F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(13, 61).addBox(-1.0F, -2.025F, -22.725F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(56, 21).addBox(-0.5F, -2.35F, -23.075F, 1.0F, 3.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone7 = base_console.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(51, 84).addBox(-1.0F, -1.975F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(51, 84).addBox(-1.0F, -1.975F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(51, 84).addBox(-1.0F, -1.975F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(51, 84).addBox(-1.0F, -1.975F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(51, 84).addBox(-1.0F, -1.975F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(51, 84).addBox(-1.0F, -1.975F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49 = base_console.addOrReplaceChild("bone49", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone50_r1 = bone49.addOrReplaceChild("bone50_r1", CubeListBuilder.create().texOffs(40, 24).addBox(-0.5F, -0.35F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(-0.25F))
                .texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone50 = bone49.addOrReplaceChild("bone50", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50_r2 = bone50.addOrReplaceChild("bone50_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-0.5F, -0.35F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(-0.25F))
                .texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50_r3 = bone51.addOrReplaceChild("bone50_r3", CubeListBuilder.create().texOffs(40, 24).addBox(-0.5F, -0.35F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(-0.25F))
                .texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50_r4 = bone52.addOrReplaceChild("bone50_r4", CubeListBuilder.create().texOffs(40, 24).addBox(-0.5F, -0.35F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(-0.25F))
                .texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50_r5 = bone53.addOrReplaceChild("bone50_r5", CubeListBuilder.create().texOffs(40, 24).addBox(-0.5F, -0.35F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(-0.25F))
                .texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50_r6 = bone54.addOrReplaceChild("bone50_r6", CubeListBuilder.create().texOffs(40, 24).addBox(-0.5F, -0.35F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(-0.25F))
                .texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone19 = base_console.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone55 = bone19.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone55_r1 = bone55.addOrReplaceChild("bone55_r1", CubeListBuilder.create().texOffs(40, 39).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone55_r2 = bone55.addOrReplaceChild("bone55_r2", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone21_r1 = bone21.addOrReplaceChild("bone21_r1", CubeListBuilder.create().texOffs(40, 39).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone21_r2 = bone21.addOrReplaceChild("bone21_r2", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone22 = bone20.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone23_r1 = bone23.addOrReplaceChild("bone23_r1", CubeListBuilder.create().texOffs(40, 39).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone23_r2 = bone23.addOrReplaceChild("bone23_r2", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone24 = bone22.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone56 = bone24.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone56_r1 = bone56.addOrReplaceChild("bone56_r1", CubeListBuilder.create().texOffs(40, 39).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone56_r2 = bone56.addOrReplaceChild("bone56_r2", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone57 = bone24.addOrReplaceChild("bone57", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58 = bone57.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone58_r1 = bone58.addOrReplaceChild("bone58_r1", CubeListBuilder.create().texOffs(40, 39).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone58_r2 = bone58.addOrReplaceChild("bone58_r2", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone59 = bone57.addOrReplaceChild("bone59", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = bone59.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone60_r1 = bone60.addOrReplaceChild("bone60_r1", CubeListBuilder.create().texOffs(40, 39).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone60_r2 = bone60.addOrReplaceChild("bone60_r2", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone73 = base_console.addOrReplaceChild("bone73", CubeListBuilder.create().texOffs(42, 84).addBox(-1.0F, -10.025F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create().texOffs(42, 84).addBox(-1.0F, -10.025F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(42, 84).addBox(-1.0F, -10.025F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone76 = bone75.addOrReplaceChild("bone76", CubeListBuilder.create().texOffs(42, 84).addBox(-1.0F, -10.025F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create().texOffs(42, 84).addBox(-1.0F, -10.025F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create().texOffs(42, 84).addBox(-1.0F, -10.025F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone109 = base_console.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.975F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -45.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone110 = bone109.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.975F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.975F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.975F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone113 = bone112.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.975F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone114 = bone113.addOrReplaceChild("bone114", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.975F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone85 = base_console.addOrReplaceChild("bone85", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.025F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone86 = bone85.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.025F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.025F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.025F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.025F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.025F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone121 = base_console.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -12.475F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -51.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone122 = bone121.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -12.475F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone123 = bone122.addOrReplaceChild("bone123", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -12.475F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone124 = bone123.addOrReplaceChild("bone124", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -12.475F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone125 = bone124.addOrReplaceChild("bone125", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -12.475F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone126 = bone125.addOrReplaceChild("bone126", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -12.475F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone103 = base_console.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(33, 84).addBox(-1.0F, -10.475F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -47.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone104 = bone103.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(33, 84).addBox(-1.0F, -10.475F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(33, 84).addBox(-1.0F, -10.475F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(33, 84).addBox(-1.0F, -10.475F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone107 = bone106.addOrReplaceChild("bone107", CubeListBuilder.create().texOffs(33, 84).addBox(-1.0F, -10.475F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(33, 84).addBox(-1.0F, -10.475F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone115 = base_console.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -12.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -51.5F, 0.0F));

        PartDefinition bone116 = bone115.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -12.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone117 = bone116.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -12.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone118 = bone117.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -12.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone119 = bone118.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -12.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone120 = bone119.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -12.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = base_console.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(27, 67).addBox(-2.5F, -10.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -47.5F, 0.0F));

        PartDefinition bone98 = bone97.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(27, 67).addBox(-2.5F, -10.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(27, 67).addBox(-2.5F, -10.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone100 = bone99.addOrReplaceChild("bone100", CubeListBuilder.create().texOffs(27, 67).addBox(-2.5F, -10.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(27, 67).addBox(-2.5F, -10.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone102 = bone101.addOrReplaceChild("bone102", CubeListBuilder.create().texOffs(27, 67).addBox(-2.5F, -10.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone139 = base_console.addOrReplaceChild("bone139", CubeListBuilder.create(), PartPose.offset(0.0F, -52.0F, 0.0F));

        PartDefinition bone80_r1 = bone139.addOrReplaceChild("bone80_r1", CubeListBuilder.create().texOffs(43, 89).addBox(-1.0F, -31.025F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 89).addBox(-1.0F, -35.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 27.0F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone140 = bone139.addOrReplaceChild("bone140", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80_r2 = bone140.addOrReplaceChild("bone80_r2", CubeListBuilder.create().texOffs(43, 89).addBox(-1.0F, -32.025F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 89).addBox(-1.0F, -36.0F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone141 = bone140.addOrReplaceChild("bone141", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80_r3 = bone141.addOrReplaceChild("bone80_r3", CubeListBuilder.create().texOffs(43, 89).addBox(-1.0F, -32.025F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 89).addBox(-1.0F, -36.0F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone142 = bone141.addOrReplaceChild("bone142", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80_r4 = bone142.addOrReplaceChild("bone80_r4", CubeListBuilder.create().texOffs(43, 89).addBox(-1.0F, -32.025F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 89).addBox(-1.0F, -36.0F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone143 = bone142.addOrReplaceChild("bone143", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80_r5 = bone143.addOrReplaceChild("bone80_r5", CubeListBuilder.create().texOffs(43, 89).addBox(-1.0F, -32.025F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 89).addBox(-1.0F, -36.0F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone144 = bone143.addOrReplaceChild("bone144", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80_r6 = bone144.addOrReplaceChild("bone80_r6", CubeListBuilder.create().texOffs(43, 89).addBox(-1.0F, -32.025F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 89).addBox(-1.0F, -36.0F, -0.5044F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 28.0F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone91 = base_console.addOrReplaceChild("bone91", CubeListBuilder.create(), PartPose.offset(0.0F, -52.5F, 0.0F));

        PartDefinition bone91_r1 = bone91.addOrReplaceChild("bone91_r1", CubeListBuilder.create().texOffs(52, 7).addBox(-2.0F, -6.0F, 0.0F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, -5.475F, 0.0044F, 0.0F, 0.0F));

        PartDefinition bone92 = bone91.addOrReplaceChild("bone92", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91_r2 = bone92.addOrReplaceChild("bone91_r2", CubeListBuilder.create().texOffs(52, 7).addBox(-2.0F, -7.0F, 0.0044F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -5.475F, 0.0044F, 0.0F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91_r3 = bone93.addOrReplaceChild("bone91_r3", CubeListBuilder.create().texOffs(52, 7).addBox(-2.0F, -7.0F, 0.0044F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -5.475F, 0.0044F, 0.0F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91_r4 = bone94.addOrReplaceChild("bone91_r4", CubeListBuilder.create().texOffs(52, 7).addBox(-2.0F, -7.0F, 0.0044F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -5.475F, 0.0044F, 0.0F, 0.0F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91_r5 = bone95.addOrReplaceChild("bone91_r5", CubeListBuilder.create().texOffs(52, 7).addBox(-2.0F, -7.0F, 0.0044F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -5.475F, 0.0044F, 0.0F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91_r6 = bone96.addOrReplaceChild("bone91_r6", CubeListBuilder.create().texOffs(52, 7).addBox(-2.0F, -7.0F, 0.0044F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -5.475F, 0.0044F, 0.0F, 0.0F));

        PartDefinition bone79 = base_console.addOrReplaceChild("bone79", CubeListBuilder.create(), PartPose.offset(0.0F, -15.5F, 0.0F));

        PartDefinition bone80_r7 = bone79.addOrReplaceChild("bone80_r7", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone79_r1 = bone79.addOrReplaceChild("bone79_r1", CubeListBuilder.create().texOffs(44, 54).addBox(-2.0F, -0.0131F, 0.0065F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.9869F, -5.4707F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone80 = bone79.addOrReplaceChild("bone80", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone81_r1 = bone80.addOrReplaceChild("bone81_r1", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 54).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82_r1 = bone81.addOrReplaceChild("bone82_r1", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 54).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone83_r1 = bone82.addOrReplaceChild("bone83_r1", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 54).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone83 = bone82.addOrReplaceChild("bone83", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84_r1 = bone83.addOrReplaceChild("bone84_r1", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 54).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone85_r1 = bone84.addOrReplaceChild("bone85_r1", CubeListBuilder.create().texOffs(50, 89).addBox(-1.0F, -2.25F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 54).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone67 = base_console.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(66, 21).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

        PartDefinition bone68 = bone67.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(66, 21).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(66, 21).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone70 = bone69.addOrReplaceChild("bone70", CubeListBuilder.create().texOffs(66, 21).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(66, 21).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone72 = bone71.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(66, 21).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = base_console.addOrReplaceChild("bone37", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone37_r1 = bone37.addOrReplaceChild("bone37_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone38 = bone37.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone38_r1 = bone38.addOrReplaceChild("bone38_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone39_r1 = bone39.addOrReplaceChild("bone39_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40_r1 = bone40.addOrReplaceChild("bone40_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41_r1 = bone41.addOrReplaceChild("bone41_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42_r1 = bone42.addOrReplaceChild("bone42_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone169 = base_console.addOrReplaceChild("bone169", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone170 = bone169.addOrReplaceChild("bone170", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone171 = bone170.addOrReplaceChild("bone171", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone172 = bone171.addOrReplaceChild("bone172", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone173 = bone172.addOrReplaceChild("bone173", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone174 = bone173.addOrReplaceChild("bone174", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = base_console.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(69, 72).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone210 = bone25.addOrReplaceChild("bone210", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -3.5F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, -5.825F));

        PartDefinition bone211 = bone210.addOrReplaceChild("bone211", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -4.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, 0.0F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(69, 72).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone213 = bone26.addOrReplaceChild("bone213", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -7.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -5.725F));

        PartDefinition bone212 = bone26.addOrReplaceChild("bone212", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -7.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -5.825F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(69, 72).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone214 = bone27.addOrReplaceChild("bone214", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -3.5F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, -5.825F));

        PartDefinition bone215 = bone214.addOrReplaceChild("bone215", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -4.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(69, 72).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone216 = bone28.addOrReplaceChild("bone216", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -3.5F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, -5.825F));

        PartDefinition bone217 = bone216.addOrReplaceChild("bone217", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -4.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(69, 72).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone218 = bone29.addOrReplaceChild("bone218", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -3.5F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, -5.825F));

        PartDefinition bone219 = bone218.addOrReplaceChild("bone219", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -4.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(69, 72).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone220 = bone30.addOrReplaceChild("bone220", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -3.5F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, -5.825F));

        PartDefinition bone221 = bone220.addOrReplaceChild("bone221", CubeListBuilder.create().texOffs(28, 73).addBox(-2.0F, -4.0F, -0.5F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, 0.0F));

        PartDefinition bone13 = base_console.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(68, 7).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(68, 7).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(68, 7).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(68, 7).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(68, 7).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(68, 7).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone = base_console.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition rotor_bottom = partdefinition.addOrReplaceChild("rotor_bottom", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition low_rotor = rotor_bottom.addOrReplaceChild("low_rotor", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -6.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -6.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone197 = low_rotor.addOrReplaceChild("bone197", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -6.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -6.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone198 = bone197.addOrReplaceChild("bone198", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -6.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -6.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone199 = bone198.addOrReplaceChild("bone199", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -6.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -6.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone200 = bone199.addOrReplaceChild("bone200", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -6.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -6.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone201 = bone200.addOrReplaceChild("bone201", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -6.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -6.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition low_rotor2 = rotor_bottom.addOrReplaceChild("low_rotor2", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 3.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone196 = low_rotor2.addOrReplaceChild("bone196", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 3.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone202 = bone196.addOrReplaceChild("bone202", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 3.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone203 = bone202.addOrReplaceChild("bone203", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 3.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone226 = bone203.addOrReplaceChild("bone226", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 3.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone227 = bone226.addOrReplaceChild("bone227", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 3.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition low_rotor3 = rotor_bottom.addOrReplaceChild("low_rotor3", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 3.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone228 = low_rotor3.addOrReplaceChild("bone228", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 3.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone229 = bone228.addOrReplaceChild("bone229", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 3.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone230 = bone229.addOrReplaceChild("bone230", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 3.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone231 = bone230.addOrReplaceChild("bone231", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 3.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone237 = bone231.addOrReplaceChild("bone237", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 3.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition rotor_top = partdefinition.addOrReplaceChild("rotor_top", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -29.0F, 0.0F, 3.1416F, -0.5236F, 0.0F));

        PartDefinition low_rotor4 = rotor_top.addOrReplaceChild("low_rotor4", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -5.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -5.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone240 = low_rotor4.addOrReplaceChild("bone240", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -5.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -5.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone241 = bone240.addOrReplaceChild("bone241", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -5.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -5.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone242 = bone241.addOrReplaceChild("bone242", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -5.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -5.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone243 = bone242.addOrReplaceChild("bone243", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -5.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -5.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone261 = bone243.addOrReplaceChild("bone261", CubeListBuilder.create().texOffs(62, 66).addBox(-1.0F, -5.0F, -4.225F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 64).addBox(0.0F, -5.0F, -4.225F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition low_rotor5 = rotor_top.addOrReplaceChild("low_rotor5", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 4.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone262 = low_rotor5.addOrReplaceChild("bone262", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 4.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone263 = bone262.addOrReplaceChild("bone263", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 4.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone264 = bone263.addOrReplaceChild("bone264", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 4.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone265 = bone264.addOrReplaceChild("bone265", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 4.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone266 = bone265.addOrReplaceChild("bone266", CubeListBuilder.create().texOffs(9, 84).addBox(-2.0F, 4.0F, -3.464F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition low_rotor6 = rotor_top.addOrReplaceChild("low_rotor6", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 4.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone267 = low_rotor6.addOrReplaceChild("bone267", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 4.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone268 = bone267.addOrReplaceChild("bone268", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 4.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone269 = bone268.addOrReplaceChild("bone269", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 4.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone270 = bone269.addOrReplaceChild("bone270", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 4.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone271 = bone270.addOrReplaceChild("bone271", CubeListBuilder.create().texOffs(40, 34).addBox(-2.5F, 4.0F, -4.33F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root().getAllParts().forEach(ModelPart::resetPose);
        root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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
        if (globalConsoleBlock == null) return;
        if (globalConsoleBlock.getBlockState() == null) return;

        Boolean powered = globalConsoleBlock.getBlockState() == null ? true : globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED);


        if (powered) {
            if (!globalConsoleBlock.powerOn.isStarted()) {
                globalConsoleBlock.powerOff.stop();
                globalConsoleBlock.powerOn.start(Minecraft.getInstance().player.tickCount);
            }
            this.animate(globalConsoleBlock.powerOn, POWER_ON, Minecraft.getInstance().player.tickCount);

            if (reactions.isCrashing()) {
                // Handle crashing animation
                this.animate(reactions.CRASHING_ANIMATION, CRASH, Minecraft.getInstance().player.tickCount);
            } else if (reactions.isFlying()) {
                // Handle flying animation
                this.animate(reactions.ROTOR_ANIMATION, FLIGHT, Minecraft.getInstance().player.tickCount);
            } else {
                // Handle idle animation
                if (TRConfig.CLIENT.PLAY_CONSOLE_IDLE_ANIMATIONS.get() && globalConsoleBlock != null) {
                    this.animate(globalConsoleBlock.liveliness, IDLE, Minecraft.getInstance().player.tickCount);
                }
            }

        } else {
            if (globalConsoleBlock != null) {
                if (!globalConsoleBlock.powerOff.isStarted()) {
                    globalConsoleBlock.powerOn.stop();
                    globalConsoleBlock.powerOff.start(Minecraft.getInstance().player.tickCount);
                }
                this.animate(globalConsoleBlock.powerOff, POWER_OFF, Minecraft.getInstance().player.tickCount);
            }
        }


        float rot = 1f + (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        throttle_control.yRot = rot;

        root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public ResourceLocation getDefaultTexture() {
        return VICTORIAN_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.VICTORIAN.getId();
    }
}