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