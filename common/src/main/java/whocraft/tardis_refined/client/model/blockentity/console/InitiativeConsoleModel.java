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

public class InitiativeConsoleModel extends HierarchicalModel implements ConsoleUnit {

    public static final AnimationDefinition IDLE = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/initiative/idle.json"));
    public static final AnimationDefinition FLIGHT = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/initiative/flight.json"));


    private static final ResourceLocation INITIATIVE_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/initiative/initiative_console.png");
    private final ModelPart root;
    private final ModelPart throttle;
    private final ModelPart handbrake;
    private final ModelPart rotor_on;

    public InitiativeConsoleModel(ModelPart root) {
        this.root = root;
        this.throttle = Frame.findPart(this, "bone178");
        this.rotor_on = Frame.findPart(this, "rotor_on");
        this.handbrake = Frame.findPart(this, "bone185");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition baseconsole = root.addOrReplaceChild("baseconsole", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone73 = baseconsole.addOrReplaceChild("bone73", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone73_r1 = bone73.addOrReplaceChild("bone73_r1", CubeListBuilder.create().texOffs(54, 31).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -21.585F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone74_r1 = bone74.addOrReplaceChild("bone74_r1", CubeListBuilder.create().texOffs(54, 31).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -21.585F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75_r1 = bone75.addOrReplaceChild("bone75_r1", CubeListBuilder.create().texOffs(58, 48).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -21.585F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone212 = bone75.addOrReplaceChild("bone212", CubeListBuilder.create().texOffs(98, 104).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.025F, -12.935F));

        PartDefinition side = bone212.addOrReplaceChild("side", CubeListBuilder.create().texOffs(35, 75).addBox(0.0F, -11.025F, -14.0208F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(35, 70).addBox(0.0F, -11.025F, -17.8492F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.025F, 12.935F));

        PartDefinition side_r1 = side.addOrReplaceChild("side_r1", CubeListBuilder.create().texOffs(32, 59).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.025F, -15.435F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone187 = side.addOrReplaceChild("bone187", CubeListBuilder.create().texOffs(5, 33).addBox(-0.5F, -24.275F, -15.935F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(0, 33).addBox(-0.5F, -25.025F, -15.935F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition bone76 = bone75.addOrReplaceChild("bone76", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone76_r1 = bone76.addOrReplaceChild("bone76_r1", CubeListBuilder.create().texOffs(54, 31).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -21.585F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone77_r1 = bone77.addOrReplaceChild("bone77_r1", CubeListBuilder.create().texOffs(54, 31).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -21.585F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone78_r1 = bone78.addOrReplaceChild("bone78_r1", CubeListBuilder.create().texOffs(54, 31).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -21.585F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone85 = baseconsole.addOrReplaceChild("bone85", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone86 = bone85.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone31 = baseconsole.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(36, 114).addBox(-1.5F, -2.0F, -9.59F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone32 = bone31.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(36, 114).addBox(-1.5F, -2.0F, -9.59F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(36, 114).addBox(-1.5F, -2.0F, -9.59F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(36, 114).addBox(-1.5F, -2.0F, -9.59F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(36, 114).addBox(-1.5F, -2.0F, -9.59F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(36, 114).addBox(-1.5F, -2.0F, -9.59F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49 = baseconsole.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(0, 96).addBox(0.0F, -12.0F, -11.09F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone50 = bone49.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(0, 96).addBox(0.0F, -12.0F, -11.09F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(0, 96).addBox(0.0F, -12.0F, -11.09F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(0, 96).addBox(0.0F, -12.0F, -11.09F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(0, 96).addBox(0.0F, -12.0F, -11.09F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(0, 96).addBox(0.0F, -12.0F, -11.09F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = baseconsole.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(8, 111).addBox(-1.5F, -2.5F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -61.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone255 = bone97.addOrReplaceChild("bone255", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.065F));

        PartDefinition bone98 = bone97.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(8, 111).addBox(-1.5F, -2.5F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone256 = bone98.addOrReplaceChild("bone256", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.065F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(8, 111).addBox(-1.5F, -2.5F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone257 = bone99.addOrReplaceChild("bone257", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.065F));

        PartDefinition bone100 = bone99.addOrReplaceChild("bone100", CubeListBuilder.create().texOffs(8, 111).addBox(-1.5F, -2.5F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone258 = bone100.addOrReplaceChild("bone258", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.065F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(8, 111).addBox(-1.5F, -2.5F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone259 = bone101.addOrReplaceChild("bone259", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.065F));

        PartDefinition bone102 = bone101.addOrReplaceChild("bone102", CubeListBuilder.create().texOffs(8, 111).addBox(-1.5F, -2.5F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone260 = bone102.addOrReplaceChild("bone260", CubeListBuilder.create().texOffs(10, 106).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.065F));

        PartDefinition bone7 = baseconsole.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(110, 110).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(110, 110).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(110, 110).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(110, 110).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(110, 110).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(110, 110).addBox(-1.5F, -3.0F, -9.59F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone61 = baseconsole.addOrReplaceChild("bone61", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone61_r1 = bone61.addOrReplaceChild("bone61_r1", CubeListBuilder.create().texOffs(22, 72).addBox(-0.475F, -5.0F, 0.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -19.585F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone62 = bone61.addOrReplaceChild("bone62", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone62_r1 = bone62.addOrReplaceChild("bone62_r1", CubeListBuilder.create().texOffs(22, 72).addBox(-0.475F, -5.0F, 0.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -19.585F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone63 = bone62.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone63_r1 = bone63.addOrReplaceChild("bone63_r1", CubeListBuilder.create().texOffs(22, 72).addBox(-0.475F, -5.0F, 0.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -19.585F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone64_r1 = bone64.addOrReplaceChild("bone64_r1", CubeListBuilder.create().texOffs(22, 72).addBox(-0.475F, -5.0F, 0.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -19.585F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone65_r1 = bone65.addOrReplaceChild("bone65_r1", CubeListBuilder.create().texOffs(22, 72).addBox(-0.475F, -5.0F, 0.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -19.585F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone66 = bone65.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66_r1 = bone66.addOrReplaceChild("bone66_r1", CubeListBuilder.create().texOffs(22, 72).addBox(-0.475F, -5.0F, 0.0F, 1.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -19.585F, -0.4363F, 0.0F, 0.0F));

        PartDefinition bone19 = baseconsole.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(58, 99).addBox(-1.5F, -4.0F, -21.585F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(58, 99).addBox(-1.5F, -4.0F, -21.585F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(58, 99).addBox(-1.5F, -4.0F, -21.585F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(100, 62).addBox(-1.5F, -4.0F, -21.585F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone209 = bone22.addOrReplaceChild("bone209", CubeListBuilder.create().texOffs(38, 106).addBox(-1.5F, -1.525F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, -23.085F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(58, 99).addBox(-1.5F, -4.0F, -21.585F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(58, 99).addBox(-1.5F, -4.0F, -21.585F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone103 = baseconsole.addOrReplaceChild("bone103", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone103_r1 = bone103.addOrReplaceChild("bone103_r1", CubeListBuilder.create().texOffs(16, 91).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone104 = bone103.addOrReplaceChild("bone104", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone104_r1 = bone104.addOrReplaceChild("bone104_r1", CubeListBuilder.create().texOffs(16, 91).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone105_r1 = bone105.addOrReplaceChild("bone105_r1", CubeListBuilder.create().texOffs(16, 91).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106_r1 = bone106.addOrReplaceChild("bone106_r1", CubeListBuilder.create().texOffs(16, 91).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone107 = bone106.addOrReplaceChild("bone107", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone107_r1 = bone107.addOrReplaceChild("bone107_r1", CubeListBuilder.create().texOffs(16, 91).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108_r1 = bone108.addOrReplaceChild("bone108_r1", CubeListBuilder.create().texOffs(89, 89).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone109 = baseconsole.addOrReplaceChild("bone109", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone109_r1 = bone109.addOrReplaceChild("bone109_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -0.25F, 0.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone110 = bone109.addOrReplaceChild("bone110", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone110_r1 = bone110.addOrReplaceChild("bone110_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -0.25F, 0.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone111_r1 = bone111.addOrReplaceChild("bone111_r1", CubeListBuilder.create().texOffs(73, 75).addBox(-6.0F, -0.275F, 4.0F, 12.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-7.0F, -0.25F, 0.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.8727F, 0.0F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone111_r2 = bone112.addOrReplaceChild("bone111_r2", CubeListBuilder.create().texOffs(73, 75).addBox(-6.0F, 1.5F, -7.575F, 12.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.775F, -22.31F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone113_r1 = bone112.addOrReplaceChild("bone113_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -0.0627F, -8.0756F, 14.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.2F, -11.41F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone113 = bone112.addOrReplaceChild("bone113", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone113_r2 = bone113.addOrReplaceChild("bone113_r2", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -0.25F, 0.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone114 = bone113.addOrReplaceChild("bone114", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone114_r1 = bone114.addOrReplaceChild("bone114_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-7.0F, -0.25F, 0.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone67 = baseconsole.addOrReplaceChild("bone67", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone67_r1 = bone67.addOrReplaceChild("bone67_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone68 = bone67.addOrReplaceChild("bone68", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone68_r1 = bone68.addOrReplaceChild("bone68_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone69_r1 = bone69.addOrReplaceChild("bone69_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone70 = bone69.addOrReplaceChild("bone70", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone70_r1 = bone70.addOrReplaceChild("bone70_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone71_r1 = bone71.addOrReplaceChild("bone71_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone72 = bone71.addOrReplaceChild("bone72", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone72_r1 = bone72.addOrReplaceChild("bone72_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 20.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone115 = baseconsole.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(53, 7).addBox(-9.5F, -3.5F, -19.935F, 19.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone116 = bone115.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(53, 7).addBox(-9.5F, -3.25F, -19.935F, 19.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone117 = bone116.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(53, 7).addBox(-9.5F, -3.5F, -19.935F, 19.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone118 = bone117.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(53, 7).addBox(-9.5F, -3.5F, -19.935F, 19.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone119 = bone118.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(53, 7).addBox(-9.5F, -3.5F, -19.935F, 19.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone120 = bone119.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(53, 7).addBox(-9.5F, -3.5F, -19.935F, 19.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone13 = baseconsole.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(53, 0).addBox(-9.5F, -3.0F, -19.435F, 19.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone239 = bone13.addOrReplaceChild("bone239", CubeListBuilder.create().texOffs(55, 14).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -1.0F, -19.035F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(53, 0).addBox(-9.5F, -3.0F, -19.435F, 19.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone240 = bone14.addOrReplaceChild("bone240", CubeListBuilder.create().texOffs(55, 14).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -1.0F, -19.035F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(53, 0).addBox(-9.5F, -3.0F, -19.435F, 19.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone241 = bone15.addOrReplaceChild("bone241", CubeListBuilder.create().texOffs(55, 14).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -1.0F, -19.035F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(53, 0).addBox(-9.5F, -3.0F, -19.435F, 19.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone242 = bone16.addOrReplaceChild("bone242", CubeListBuilder.create().texOffs(55, 14).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -1.0F, -19.035F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(53, 0).addBox(-9.5F, -3.0F, -19.435F, 19.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone243 = bone17.addOrReplaceChild("bone243", CubeListBuilder.create().texOffs(55, 14).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -1.0F, -19.035F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(53, 0).addBox(-9.5F, -3.0F, -19.435F, 19.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone245 = bone18.addOrReplaceChild("bone245", CubeListBuilder.create().texOffs(55, 14).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -1.0F, -19.035F));

        PartDefinition bone155 = baseconsole.addOrReplaceChild("bone155", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone155_r1 = bone155.addOrReplaceChild("bone155_r1", CubeListBuilder.create().texOffs(85, 104).mirror().addBox(5.5F, -2.675F, -9.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(85, 104).addBox(-6.5F, -2.675F, -9.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.025F, -8.06F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone155_r2 = bone155.addOrReplaceChild("bone155_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-9.5F, -0.25F, -10.0F, 19.0F, 1.0F, 10.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, -0.025F, -8.06F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone200 = bone155.addOrReplaceChild("bone200", CubeListBuilder.create().texOffs(77, 48).addBox(-6.5F, 0.0F, -1.0F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.025F, -14.56F));

        PartDefinition bone152 = baseconsole.addOrReplaceChild("bone152", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone206 = bone152.addOrReplaceChild("bone206", CubeListBuilder.create(), PartPose.offset(0.0F, 0.075F, -8.06F));

        PartDefinition bone205 = bone206.addOrReplaceChild("bone205", CubeListBuilder.create().texOffs(0, 14).addBox(-9.5F, -0.25F, -10.0F, 19.0F, 1.0F, 10.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone152_r1 = bone205.addOrReplaceChild("bone152_r1", CubeListBuilder.create().texOffs(85, 104).mirror().addBox(5.5F, -2.675F, -9.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(85, 104).addBox(-6.5F, -2.675F, -9.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone199 = bone206.addOrReplaceChild("bone199", CubeListBuilder.create().texOffs(77, 48).addBox(-6.5F, 0.0F, -1.0F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.5F));

        PartDefinition bone151 = baseconsole.addOrReplaceChild("bone151", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition bone198 = bone151.addOrReplaceChild("bone198", CubeListBuilder.create(), PartPose.offset(0.0F, 0.075F, -8.06F));

        PartDefinition bone210 = bone198.addOrReplaceChild("bone210", CubeListBuilder.create().texOffs(0, 14).addBox(-9.5F, -0.25F, -10.0F, 19.0F, 1.0F, 10.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone152_r2 = bone210.addOrReplaceChild("bone152_r2", CubeListBuilder.create().texOffs(85, 104).mirror().addBox(5.5F, -2.675F, -9.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(85, 104).addBox(-6.5F, -2.675F, -9.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone211 = bone198.addOrReplaceChild("bone211", CubeListBuilder.create().texOffs(77, 48).addBox(-6.5F, 0.0F, -1.0F, 13.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.5F));

        PartDefinition bone145 = baseconsole.addOrReplaceChild("bone145", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -55.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone145_r1 = bone145.addOrReplaceChild("bone145_r1", CubeListBuilder.create().texOffs(58, 65).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -5.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone192 = bone145.addOrReplaceChild("bone192", CubeListBuilder.create().texOffs(0, 120).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone146 = bone145.addOrReplaceChild("bone146", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone146_r1 = bone146.addOrReplaceChild("bone146_r1", CubeListBuilder.create().texOffs(17, 48).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -5.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone197 = bone146.addOrReplaceChild("bone197", CubeListBuilder.create().texOffs(0, 137).addBox(-3.0F, 0.999F, 0.0436F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 137).addBox(-3.0F, 0.999F, -0.7064F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -4.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone147 = bone146.addOrReplaceChild("bone147", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone147_r1 = bone147.addOrReplaceChild("bone147_r1", CubeListBuilder.create().texOffs(58, 65).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -5.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone193 = bone147.addOrReplaceChild("bone193", CubeListBuilder.create().texOffs(0, 120).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone148 = bone147.addOrReplaceChild("bone148", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone148_r1 = bone148.addOrReplaceChild("bone148_r1", CubeListBuilder.create().texOffs(17, 48).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -5.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone196 = bone148.addOrReplaceChild("bone196", CubeListBuilder.create().texOffs(0, 137).addBox(-3.0F, 0.999F, 0.0436F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 137).addBox(-3.0F, 0.999F, -0.7064F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -4.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone149 = bone148.addOrReplaceChild("bone149", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone149_r1 = bone149.addOrReplaceChild("bone149_r1", CubeListBuilder.create().texOffs(58, 65).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -5.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone194 = bone149.addOrReplaceChild("bone194", CubeListBuilder.create().texOffs(0, 120).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone150 = bone149.addOrReplaceChild("bone150", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone150_r1 = bone150.addOrReplaceChild("bone150_r1", CubeListBuilder.create().texOffs(17, 48).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -5.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone195 = bone150.addOrReplaceChild("bone195", CubeListBuilder.create().texOffs(0, 137).addBox(-3.0F, 0.999F, 0.0436F, 6.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 137).addBox(-3.0F, 0.999F, -0.7064F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -4.21F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone55 = baseconsole.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(0, 26).addBox(-9.5F, -1.025F, -18.06F, 19.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition bone56 = bone55.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(0, 14).addBox(-9.5F, -1.025F, -18.06F, 19.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone57 = bone56.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(0, 26).addBox(-9.5F, -1.025F, -18.06F, 19.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58 = bone57.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(0, 26).addBox(-9.5F, -1.025F, -18.06F, 19.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone59 = bone58.addOrReplaceChild("bone59", CubeListBuilder.create().texOffs(0, 14).addBox(-9.5F, -1.025F, -18.06F, 19.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = bone59.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(0, 14).addBox(-9.5F, -1.025F, -18.06F, 19.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone139 = baseconsole.addOrReplaceChild("bone139", CubeListBuilder.create().texOffs(73, 95).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -48.0F, 0.0F));

        PartDefinition bone140 = bone139.addOrReplaceChild("bone140", CubeListBuilder.create().texOffs(41, 95).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone141 = bone140.addOrReplaceChild("bone141", CubeListBuilder.create().texOffs(73, 95).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone142 = bone141.addOrReplaceChild("bone142", CubeListBuilder.create().texOffs(41, 95).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone143 = bone142.addOrReplaceChild("bone143", CubeListBuilder.create().texOffs(73, 95).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone144 = bone143.addOrReplaceChild("bone144", CubeListBuilder.create().texOffs(41, 95).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone133 = baseconsole.addOrReplaceChild("bone133", CubeListBuilder.create().texOffs(95, 9).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -23.0F, 0.0F));

        PartDefinition bone134 = bone133.addOrReplaceChild("bone134", CubeListBuilder.create().texOffs(95, 9).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone135 = bone134.addOrReplaceChild("bone135", CubeListBuilder.create().texOffs(95, 9).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone136 = bone135.addOrReplaceChild("bone136", CubeListBuilder.create().texOffs(95, 9).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone137 = bone136.addOrReplaceChild("bone137", CubeListBuilder.create().texOffs(95, 9).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone138 = bone137.addOrReplaceChild("bone138", CubeListBuilder.create().texOffs(95, 9).addBox(-3.5F, -8.0F, -6.56F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone153 = baseconsole.addOrReplaceChild("bone153", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone154 = bone153.addOrReplaceChild("bone154", CubeListBuilder.create().texOffs(100, 55).addBox(-2.5F, -1.675F, -4.06F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(107, 79).addBox(-3.5F, -2.675F, -5.06F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -8.5F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone154_r1 = bone154.addOrReplaceChild("bone154_r1", CubeListBuilder.create().texOffs(89, 83).addBox(-3.5F, -0.75F, 8.5F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.25F, -13.81F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone79 = baseconsole.addOrReplaceChild("bone79", CubeListBuilder.create().texOffs(11, 98).addBox(-3.5F, -2.925F, -9.56F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -23.0F, 0.0F));

        PartDefinition bone80 = bone79.addOrReplaceChild("bone80", CubeListBuilder.create().texOffs(11, 98).addBox(-3.5F, -2.925F, -9.56F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create().texOffs(11, 98).addBox(-3.5F, -2.925F, -9.56F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create().texOffs(11, 98).addBox(-3.5F, -2.925F, -9.56F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone83 = bone82.addOrReplaceChild("bone83", CubeListBuilder.create().texOffs(11, 98).addBox(-3.5F, -2.925F, -9.56F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create().texOffs(106, 38).addBox(-3.5F, -2.925F, -8.56F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = baseconsole.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(49, 31).addBox(-3.5F, -2.0F, -9.06F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(49, 31).addBox(-3.5F, -2.0F, -9.06F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(49, 31).addBox(-3.5F, -2.0F, -9.06F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(49, 31).addBox(-3.5F, -2.0F, -9.06F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(49, 31).addBox(-3.5F, -2.0F, -9.06F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(49, 31).addBox(-3.5F, -2.0F, -9.06F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone43 = baseconsole.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(32, 48).addBox(-0.5F, -9.0F, -7.86F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone44 = bone43.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(32, 48).addBox(-0.5F, -9.0F, -7.86F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(32, 48).addBox(-0.5F, -9.0F, -7.86F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(32, 48).addBox(-0.5F, -9.0F, -7.86F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(32, 48).addBox(-0.5F, -9.0F, -7.86F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(32, 48).addBox(-0.5F, -9.0F, -7.86F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = baseconsole.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(97, 29).addBox(-3.5F, -7.0F, -7.06F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

        PartDefinition bone38 = bone37.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(147, 19).addBox(-3.5F, -7.0F, -7.06F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone261 = bone38.addOrReplaceChild("bone261", CubeListBuilder.create().texOffs(148, 11).addBox(-1.5F, -3.0F, -1.0125F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(158, 9).addBox(-1.5F, -3.25F, -0.9875F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -6.0725F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(97, 29).addBox(-3.5F, -7.0F, -7.06F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(97, 29).addBox(-3.5F, -7.0F, -7.06F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(97, 29).addBox(-3.5F, -7.0F, -7.06F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(97, 29).addBox(-3.5F, -7.0F, -7.06F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone162 = baseconsole.addOrReplaceChild("bone162", CubeListBuilder.create().texOffs(93, 104).addBox(-1.0F, -3.0F, -7.475F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -31.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone163 = bone162.addOrReplaceChild("bone163", CubeListBuilder.create().texOffs(93, 104).addBox(-1.0F, -3.0F, -7.475F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone164 = bone163.addOrReplaceChild("bone164", CubeListBuilder.create().texOffs(93, 104).addBox(-1.0F, -3.0F, -7.475F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone165 = bone164.addOrReplaceChild("bone165", CubeListBuilder.create().texOffs(93, 104).addBox(-1.0F, -3.0F, -7.475F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone166 = bone165.addOrReplaceChild("bone166", CubeListBuilder.create().texOffs(93, 104).addBox(-1.0F, -3.0F, -7.475F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone167 = bone166.addOrReplaceChild("bone167", CubeListBuilder.create().texOffs(93, 104).addBox(-1.0F, -3.0F, -7.475F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone127 = baseconsole.addOrReplaceChild("bone127", CubeListBuilder.create().texOffs(37, 81).addBox(-1.0F, -5.0F, -7.725F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -56.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone128 = bone127.addOrReplaceChild("bone128", CubeListBuilder.create().texOffs(37, 81).addBox(-1.0F, -5.0F, -7.725F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone129 = bone128.addOrReplaceChild("bone129", CubeListBuilder.create().texOffs(37, 81).addBox(-1.0F, -5.0F, -7.725F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone130 = bone129.addOrReplaceChild("bone130", CubeListBuilder.create().texOffs(37, 81).addBox(-1.0F, -5.0F, -7.725F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone131 = bone130.addOrReplaceChild("bone131", CubeListBuilder.create().texOffs(37, 81).addBox(-1.0F, -5.0F, -7.725F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone132 = bone131.addOrReplaceChild("bone132", CubeListBuilder.create().texOffs(37, 81).addBox(-1.0F, -5.0F, -7.725F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone169 = baseconsole.addOrReplaceChild("bone169", CubeListBuilder.create().texOffs(73, 65).addBox(-4.5F, -3.65F, -7.56F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

        PartDefinition bone170 = bone169.addOrReplaceChild("bone170", CubeListBuilder.create().texOffs(73, 65).addBox(-4.5F, -3.65F, -7.56F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone171 = bone170.addOrReplaceChild("bone171", CubeListBuilder.create().texOffs(73, 65).addBox(-4.5F, -3.65F, -7.56F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone172 = bone171.addOrReplaceChild("bone172", CubeListBuilder.create().texOffs(73, 65).addBox(-4.5F, -3.65F, -7.56F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone173 = bone172.addOrReplaceChild("bone173", CubeListBuilder.create().texOffs(73, 65).addBox(-4.5F, -3.65F, -7.56F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone174 = bone173.addOrReplaceChild("bone174", CubeListBuilder.create().texOffs(73, 65).addBox(-4.5F, -3.65F, -7.56F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone156 = baseconsole.addOrReplaceChild("bone156", CubeListBuilder.create().texOffs(106, 20).addBox(-3.0F, -3.0F, -7.175F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

        PartDefinition bone157 = bone156.addOrReplaceChild("bone157", CubeListBuilder.create().texOffs(123, 14).addBox(-3.0F, -3.0F, -7.175F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone246 = bone157.addOrReplaceChild("bone246", CubeListBuilder.create().texOffs(127, 6).addBox(-3.0F, -1.0F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -1.25F, -6.775F));

        PartDefinition bone227 = bone157.addOrReplaceChild("bone227", CubeListBuilder.create().texOffs(127, 10).addBox(-3.0F, -1.0F, -0.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -1.25F, -6.75F));

        PartDefinition bone226 = bone157.addOrReplaceChild("bone226", CubeListBuilder.create().texOffs(123, 20).addBox(-3.0F, -1.25F, -1.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.025F))
                .texOffs(126, 26).addBox(-1.0F, -1.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.75F, -6.175F));

        PartDefinition bone158 = bone157.addOrReplaceChild("bone158", CubeListBuilder.create().texOffs(106, 20).addBox(-3.0F, -3.0F, -7.175F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone159 = bone158.addOrReplaceChild("bone159", CubeListBuilder.create().texOffs(106, 20).addBox(-3.0F, -3.0F, -7.175F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone160 = bone159.addOrReplaceChild("bone160", CubeListBuilder.create().texOffs(106, 20).addBox(-3.0F, -3.0F, -7.175F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone161 = bone160.addOrReplaceChild("bone161", CubeListBuilder.create().texOffs(106, 20).addBox(-3.0F, -3.0F, -7.175F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone121 = baseconsole.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(74, 12).addBox(-3.0F, -4.0F, -7.175F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -56.0F, 0.0F));

        PartDefinition bone122 = bone121.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(74, 12).addBox(-3.0F, -4.0F, -7.175F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone123 = bone122.addOrReplaceChild("bone123", CubeListBuilder.create().texOffs(74, 12).addBox(-3.0F, -4.0F, -7.175F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone124 = bone123.addOrReplaceChild("bone124", CubeListBuilder.create().texOffs(74, 12).addBox(-3.0F, -4.0F, -7.175F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone125 = bone124.addOrReplaceChild("bone125", CubeListBuilder.create().texOffs(74, 12).addBox(-3.0F, -4.0F, -7.175F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone126 = bone125.addOrReplaceChild("bone126", CubeListBuilder.create().texOffs(74, 12).addBox(-3.0F, -4.0F, -7.175F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91 = baseconsole.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(49, 16).addBox(-3.5F, -4.0F, -9.96F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -60.0F, 0.0F));

        PartDefinition bone176 = bone91.addOrReplaceChild("bone176", CubeListBuilder.create().texOffs(149, 28).addBox(-2.5F, -1.5F, -1.0125F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(148, 33).addBox(-2.5F, -1.0F, -0.9625F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.9725F));

        PartDefinition bone92 = bone91.addOrReplaceChild("bone92", CubeListBuilder.create().texOffs(49, 16).addBox(-3.5F, -4.0F, -9.96F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone250 = bone92.addOrReplaceChild("bone250", CubeListBuilder.create().texOffs(149, 28).addBox(-2.5F, -1.5F, -1.0125F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(148, 33).addBox(-2.5F, -1.0F, -0.9625F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.9725F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create().texOffs(49, 16).addBox(-3.5F, -4.0F, -9.96F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone251 = bone93.addOrReplaceChild("bone251", CubeListBuilder.create().texOffs(149, 28).addBox(-2.5F, -1.5F, -1.0125F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(148, 33).addBox(-2.5F, -1.0F, -0.9625F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.9725F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(49, 16).addBox(-3.5F, -4.0F, -9.96F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone252 = bone94.addOrReplaceChild("bone252", CubeListBuilder.create().texOffs(149, 28).addBox(-2.5F, -1.5F, -1.0125F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(148, 33).addBox(-2.5F, -1.0F, -0.9625F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.9725F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create().texOffs(49, 16).addBox(-3.5F, -4.0F, -9.96F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone253 = bone95.addOrReplaceChild("bone253", CubeListBuilder.create().texOffs(149, 28).addBox(-2.5F, -1.5F, -1.0125F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(148, 33).addBox(-2.5F, -1.0F, -0.9625F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.9725F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create().texOffs(49, 16).addBox(-3.5F, -4.0F, -9.96F, 7.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone254 = bone96.addOrReplaceChild("bone254", CubeListBuilder.create().texOffs(149, 28).addBox(-2.5F, -1.5F, -1.0125F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(148, 33).addBox(-2.5F, -1.0F, -0.9625F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.9725F));

        PartDefinition bone = baseconsole.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(73, 31).addBox(-3.5F, -3.0F, -9.06F, 7.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(73, 31).addBox(-3.5F, -3.0F, -9.06F, 7.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(73, 31).addBox(-3.5F, -3.0F, -9.06F, 7.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(73, 31).addBox(-3.5F, -3.0F, -9.06F, 7.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(73, 31).addBox(-3.5F, -3.0F, -9.06F, 7.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(73, 31).addBox(-3.5F, -3.0F, -9.06F, 7.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition rotor_on = root.addOrReplaceChild("rotor_on", CubeListBuilder.create(), PartPose.offset(0.0F, -39.4F, 0.0F));

        PartDefinition bone244 = rotor_on.addOrReplaceChild("bone244", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rotor_r1 = bone244.addOrReplaceChild("rotor_r1", CubeListBuilder.create().texOffs(140, 41).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone238 = bone244.addOrReplaceChild("bone238", CubeListBuilder.create().texOffs(41, 44).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone237 = bone244.addOrReplaceChild("bone237", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rotor_r2 = bone237.addOrReplaceChild("rotor_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 32.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition controls = root.addOrReplaceChild("controls", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition monitor = controls.addOrReplaceChild("monitor", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -32.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone168 = monitor.addOrReplaceChild("bone168", CubeListBuilder.create().texOffs(90, 96).addBox(-4.0F, -2.069F, -3.0286F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(111, 0).addBox(-2.5F, -0.069F, -2.0286F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.931F, -7.1464F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone168_r1 = bone168.addOrReplaceChild("bone168_r1", CubeListBuilder.create().texOffs(108, 49).addBox(-2.5F, -1.0F, -3.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.931F, -0.5286F, 0.7854F, 0.0F, 0.0F));

        PartDefinition north = controls.addOrReplaceChild("north", CubeListBuilder.create().texOffs(114, 92).addBox(-2.0F, -11.2F, -10.06F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 85).addBox(0.5F, -10.7F, -10.56F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(74, 16).addBox(-1.75F, -10.7F, -10.16F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(109, 96).addBox(-2.5F, -12.45F, -9.66F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone190 = north.addOrReplaceChild("bone190", CubeListBuilder.create().texOffs(16, 85).addBox(0.0F, -0.2498F, -2.0109F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -9.7F, -10.56F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone231 = north.addOrReplaceChild("bone231", CubeListBuilder.create().texOffs(123, 38).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(125, 97).addBox(0.25F, -2.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -9.7F, -9.635F));

        PartDefinition bone181 = north.addOrReplaceChild("bone181", CubeListBuilder.create().texOffs(112, 9).addBox(3.5F, -0.75F, 1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(113, 26).addBox(-2.0F, -1.25F, 9.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(112, 9).mirror().addBox(-6.5F, -0.75F, 1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(63, 12).addBox(-3.5F, -0.35F, 0.75F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(25, 101).addBox(-1.5F, -1.25F, 1.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 114).mirror().addBox(-2.5F, -0.35F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 69).mirror().addBox(-3.75F, -0.35F, 4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 69).addBox(2.75F, -0.35F, 4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 114).addBox(1.5F, -0.35F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone191 = bone181.addOrReplaceChild("bone191", CubeListBuilder.create().texOffs(73, 31).addBox(-1.0F, -1.75F, -1.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 3.25F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone228 = bone181.addOrReplaceChild("bone228", CubeListBuilder.create().texOffs(77, 114).mirror().addBox(-6.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(77, 114).addBox(4.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 2.5F));

        PartDefinition north_left = controls.addOrReplaceChild("north_left", CubeListBuilder.create().texOffs(109, 96).addBox(-2.5F, -12.45F, -9.66F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(109, 96).mirror().addBox(-2.5F, -10.95F, -9.66F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone236 = north_left.addOrReplaceChild("bone236", CubeListBuilder.create().texOffs(125, 97).addBox(-0.5F, -25.45F, -9.635F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(125, 95).addBox(-1.0F, -26.95F, -9.635F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 14.5F, 0.0F));

        PartDefinition bone177 = north_left.addOrReplaceChild("bone177", CubeListBuilder.create().texOffs(87, 44).addBox(-4.0F, -0.35F, 1.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(87, 44).addBox(-4.0F, -0.35F, 5.5F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(4.5F, -3.25F, 1.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(2.0F, -2.25F, 6.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-4.0F, -2.25F, 6.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-1.0F, -2.25F, 6.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).mirror().addBox(-6.5F, -3.25F, 1.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(96, 113).addBox(-4.5F, -0.35F, 3.25F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(96, 113).mirror().addBox(0.5F, -0.35F, 3.25F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(108, 102).addBox(-2.5F, -0.85F, 8.65F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone214 = bone177.addOrReplaceChild("bone214", CubeListBuilder.create().texOffs(115, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.125F, 2.0F));

        PartDefinition bone247 = bone177.addOrReplaceChild("bone247", CubeListBuilder.create().texOffs(119, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.125F, 2.0F));

        PartDefinition bone215 = bone177.addOrReplaceChild("bone215", CubeListBuilder.create().texOffs(115, 54).addBox(3.0F, -0.55F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.15F, 2.5F));

        PartDefinition bone248 = bone177.addOrReplaceChild("bone248", CubeListBuilder.create().texOffs(119, 54).addBox(3.0F, -0.55F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.15F, 2.5F));

        PartDefinition bone232 = bone177.addOrReplaceChild("bone232", CubeListBuilder.create().texOffs(91, 115).mirror().addBox(0.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 0.125F, 4.25F));

        PartDefinition bone233 = bone177.addOrReplaceChild("bone233", CubeListBuilder.create().texOffs(91, 115).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.125F, 4.25F));

        PartDefinition bone249 = bone177.addOrReplaceChild("bone249", CubeListBuilder.create().texOffs(91, 117).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.125F, 4.25F));

        PartDefinition south_left = controls.addOrReplaceChild("south_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone184 = south_left.addOrReplaceChild("bone184", CubeListBuilder.create().texOffs(94, 1).addBox(1.0F, -0.35F, 1.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(114, 30).addBox(3.5F, -0.45F, 1.25F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(73, 65).addBox(2.25F, -0.75F, 4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(73, 65).addBox(2.25F, -0.75F, 1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(49, 106).addBox(-1.75F, -0.75F, 1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(112, 14).addBox(-4.75F, -2.25F, 2.25F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(49, 106).addBox(-1.75F, -0.75F, 4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.8727F, 0.0F, 0.0F));

        PartDefinition bone114_r2 = bone184.addOrReplaceChild("bone114_r2", CubeListBuilder.create().texOffs(73, 36).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -1.75F, 3.75F, -0.7418F, 0.0F, 0.0F));

        PartDefinition bone229 = bone184.addOrReplaceChild("bone229", CubeListBuilder.create().texOffs(122, 32).addBox(-1.0F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.075F, 2.75F));

        PartDefinition bone188 = bone184.addOrReplaceChild("bone188", CubeListBuilder.create().texOffs(73, 83).addBox(-0.75F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -0.75F, 5.5F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone189 = bone184.addOrReplaceChild("bone189", CubeListBuilder.create().texOffs(73, 83).addBox(-0.75F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -0.75F, 2.5F, -0.2618F, 0.0F, 0.0F));

        PartDefinition south = controls.addOrReplaceChild("south", CubeListBuilder.create().texOffs(77, 59).addBox(-2.5F, -11.925F, -10.56F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(109, 96).mirror().addBox(-2.5F, -9.95F, -9.66F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone179 = south.addOrReplaceChild("bone179", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone180 = bone179.addOrReplaceChild("bone180", CubeListBuilder.create().texOffs(108, 44).addBox(-3.0F, -2.0627F, -3.0756F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(91, 59).addBox(-1.0F, -1.5627F, -7.0756F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(53, 110).addBox(1.5F, -0.1627F, -7.0756F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(53, 110).mirror().addBox(-5.5F, -0.1627F, -7.0756F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 7).mirror().addBox(-4.75F, -0.2627F, -7.3256F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 21).mirror().addBox(-4.75F, -1.0127F, -4.5756F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 21).mirror().addBox(-4.75F, -1.0127F, -5.5756F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 21).mirror().addBox(-4.75F, -1.0127F, -6.5756F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 21).addBox(2.75F, -1.0127F, -6.5756F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(2.75F, -1.0127F, -5.5756F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(2.75F, -1.0127F, -4.5756F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(2.75F, -0.2627F, -7.3256F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.2F, -11.41F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone178 = bone180.addOrReplaceChild("bone178", CubeListBuilder.create().texOffs(50, 81).mirror().addBox(-1.1F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 81).addBox(0.1F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0627F, -5.8256F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone222 = bone180.addOrReplaceChild("bone222", CubeListBuilder.create().texOffs(109, 87).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0627F, -2.0756F));

        PartDefinition bone219 = bone180.addOrReplaceChild("bone219", CubeListBuilder.create().texOffs(110, 84).addBox(-0.75F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.25F, -7.0627F, -1.8506F));

        PartDefinition bone220 = bone180.addOrReplaceChild("bone220", CubeListBuilder.create().texOffs(110, 84).addBox(-0.75F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -6.0627F, -2.1006F));

        PartDefinition bone221 = bone180.addOrReplaceChild("bone221", CubeListBuilder.create().texOffs(114, 84).addBox(-0.75F, 3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -7.5627F, -2.1006F));

        PartDefinition bone208 = south.addOrReplaceChild("bone208", CubeListBuilder.create().texOffs(32, 65).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.425F, -9.56F));

        PartDefinition bone207 = bone208.addOrReplaceChild("bone207", CubeListBuilder.create().texOffs(51, 85).addBox(0.5F, -1.5627F, -3.0756F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.5627F, 1.1006F));

        PartDefinition south_right = controls.addOrReplaceChild("south_right", CubeListBuilder.create().texOffs(109, 96).mirror().addBox(-2.5F, -9.95F, -9.66F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone186 = south_right.addOrReplaceChild("bone186", CubeListBuilder.create().texOffs(108, 44).addBox(-3.0F, -1.5F, -2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.2627F, -9.4856F, 1.8326F, 0.0F, 0.0F));

        PartDefinition bone230 = bone186.addOrReplaceChild("bone230", CubeListBuilder.create().texOffs(125, 46).addBox(-2.0F, 0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.975F, -1.0F));

        PartDefinition bone223 = bone186.addOrReplaceChild("bone223", CubeListBuilder.create().texOffs(109, 87).addBox(-3.0F, -2.75F, 0.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.75F, -1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone224 = bone223.addOrReplaceChild("bone224", CubeListBuilder.create().texOffs(114, 84).addBox(0.5F, 3.0F, 0.025F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.75F, -5.5F, -0.05F));

        PartDefinition bone225 = bone223.addOrReplaceChild("bone225", CubeListBuilder.create().texOffs(114, 84).addBox(0.5F, 3.0F, 0.025F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(114, 84).addBox(2.5F, 3.25F, 0.025F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.75F, -5.5F, -0.05F));

        PartDefinition bone182 = south_right.addOrReplaceChild("bone182", CubeListBuilder.create().texOffs(11, 105).addBox(-5.0F, -0.35F, 1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(104, 73).addBox(1.0F, -0.35F, 1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(49, 20).addBox(-0.75F, -0.35F, 1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 114).mirror().addBox(1.25F, -0.35F, 5.25F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(66, 114).addBox(-4.25F, -0.35F, 5.25F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(49, 14).addBox(-0.5F, -2.25F, 3.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(108, 102).addBox(-2.5F, -0.85F, 8.65F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone183 = bone182.addOrReplaceChild("bone183", CubeListBuilder.create().texOffs(70, 107).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(25, 108).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(3.0F, -0.35F, 3.0F, -0.1745F, -0.0873F, 0.0F));

        PartDefinition bone201 = bone182.addOrReplaceChild("bone201", CubeListBuilder.create().texOffs(70, 107).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(25, 108).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-3.0F, -0.35F, 3.0F, 0.1745F, -0.0873F, 0.0F));

        PartDefinition bone234 = bone182.addOrReplaceChild("bone234", CubeListBuilder.create().texOffs(61, 114).addBox(-2.0F, -16.325F, -17.31F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, 16.0F, 23.06F));

        PartDefinition bone235 = bone182.addOrReplaceChild("bone235", CubeListBuilder.create().texOffs(61, 116).addBox(-2.0F, -16.325F, -17.31F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.75F, 16.0F, 23.06F));

        PartDefinition bone185 = bone182.addOrReplaceChild("bone185", CubeListBuilder.create().texOffs(67, 48).addBox(-0.5F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).mirror().addBox(-1.0F, -2.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, -2.0F, 5.0F, -0.6545F, 0.0F, 0.0F));

        PartDefinition north_right = controls.addOrReplaceChild("north_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition GRUM_core = north_right.addOrReplaceChild("GRUM_core", CubeListBuilder.create().texOffs(0, 85).addBox(-2.5F, -2.8217F, -4.5569F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.025F))
                .texOffs(73, 83).addBox(-2.5F, -2.8217F, -4.5569F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.275F)), PartPose.offsetAndRotation(0.0F, -12.25F, -11.5F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone203 = GRUM_core.addOrReplaceChild("bone203", CubeListBuilder.create().texOffs(15, 118).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.025F)), PartPose.offset(0.0F, -0.3217F, -4.7069F));

        PartDefinition bone202 = GRUM_core.addOrReplaceChild("bone202", CubeListBuilder.create().texOffs(80, 106).mirror().addBox(0.475F, -0.25F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(80, 106).addBox(-2.475F, -0.25F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.7467F, -5.0319F));

        PartDefinition bone213 = GRUM_core.addOrReplaceChild("bone213", CubeListBuilder.create(), PartPose.offset(0.0F, -9.8217F, 1.1931F));

        PartDefinition bone176_r1 = bone213.addOrReplaceChild("bone176_r1", CubeListBuilder.create().texOffs(58, 48).addBox(-1.0F, -4.5F, -1.5F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, -1.25F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone204 = GRUM_core.addOrReplaceChild("bone204", CubeListBuilder.create().texOffs(111, 62).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.3217F, -4.0569F, 0.5672F, 0.0F, 0.0F));

        PartDefinition bone175 = north_right.addOrReplaceChild("bone175", CubeListBuilder.create().texOffs(113, 26).addBox(-2.0F, -1.25F, 9.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(37, 38).addBox(-5.5F, -0.75F, 1.5F, 11.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(77, 54).addBox(-4.75F, -0.85F, 2.25F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -18.31F, 0.5236F, 0.0F, 0.0F));

        PartDefinition bone175_r1 = bone175.addOrReplaceChild("bone175_r1", CubeListBuilder.create().texOffs(84, 25).addBox(-5.25F, -1.5F, 0.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.85F, 4.5F, -0.48F, 0.0F, 0.0F));

        PartDefinition bone216 = bone175.addOrReplaceChild("bone216", CubeListBuilder.create().texOffs(115, 66).addBox(-5.0F, -0.5F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, -0.325F, 2.75F));

        PartDefinition bone217 = bone175.addOrReplaceChild("bone217", CubeListBuilder.create().texOffs(84, 28).addBox(-5.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 28).addBox(-2.25F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 28).addBox(0.75F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.85F, 4.5F, -1.0472F, 0.0F, 0.0F));

        PartDefinition bone218 = bone175.addOrReplaceChild("bone218", CubeListBuilder.create().texOffs(84, 28).addBox(3.75F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 28).addBox(-0.75F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.85F, 4.5F, -0.48F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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

        if (globalConsoleBlock != null && globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            if (reactions.isFlying()) {
                this.animate(reactions.ROTOR_ANIMATION, FLIGHT, Minecraft.getInstance().player.tickCount);
            } else {
                if (TRConfig.CLIENT.PLAY_CONSOLE_IDLE_ANIMATIONS.get() && globalConsoleBlock != null) {
                    this.animate(globalConsoleBlock.liveliness, IDLE, Minecraft.getInstance().player.tickCount);
                }
            }
        }

        float rot = -1f + (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        throttle.xRot = rot;

        handbrake.xRot = reactions.isHandbrakeEngaged() ? 1f : 0f;

        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return INITIATIVE_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.INITIATIVE.getId();
    }
}