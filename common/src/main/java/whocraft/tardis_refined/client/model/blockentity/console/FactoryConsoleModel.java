package whocraft.tardis_refined.client.model.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.frame.tardis.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
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

public class FactoryConsoleModel extends HierarchicalModel implements ConsoleUnit {

    // Load Animations in
    public static final AnimationDefinition IDLE = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/factory/idle.json"));
    public static final AnimationDefinition FLIGHT = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/factory/flight.json"));
    public static final AnimationDefinition CRASH = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/factory/crash.json"));
    public static final AnimationDefinition POWER_ON = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/factory/power_on.json"));
    public static final AnimationDefinition POWER_OFF = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/factory/power_off.json"));
    
    private static final ResourceLocation FACTORY_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/factory/factory_console.png");
  
  
    private final ModelPart root;
    private final ModelPart throttleLever;
    private final ModelPart handbrake;


    public FactoryConsoleModel(ModelPart root) {
        this.root = root;
        this.throttleLever = Frame.findPart(this, "lever2");
        this.handbrake = Frame.findPart(this, "lever3");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root().getAllParts().forEach(ModelPart::resetPose);
        root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public void renderConsole(GlobalConsoleBlockEntity globalConsoleBlock, Level level, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root().getAllParts().forEach(ModelPart::resetPose);

        if (globalConsoleBlock == null || globalConsoleBlock.getBlockState() == null) return;

        Boolean powered = globalConsoleBlock.getBlockState() == null ? true : globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED);


        // Store tick count for later use
        int tickCount = Minecraft.getInstance().player.tickCount;

        TardisClientData reactions = TardisClientData.getInstance(level.dimension());

        // Booting logic
        if (powered) {

            if(globalConsoleBlock.getTicksBooting() > 0) {
                if (!globalConsoleBlock.powerOn.isStarted()) {
                    globalConsoleBlock.powerOff.stop();
                    globalConsoleBlock.powerOn.start(tickCount);
                }
                this.animate(globalConsoleBlock.powerOn, POWER_ON, tickCount);
            }
            
            // Handle animations based on the current state (with flying first)
            if (reactions.isFlying()) {
                this.animate(reactions.ROTOR_ANIMATION, FLIGHT, tickCount);
            } else if (reactions.isCrashing()) {
                this.animate(reactions.CRASHING_ANIMATION, CRASH, tickCount);
            } else {
                if (TRConfig.CLIENT.PLAY_CONSOLE_IDLE_ANIMATIONS.get()) {
                    this.animate(globalConsoleBlock.liveliness, IDLE, tickCount);
                }
            }

        } else {
            // Power off animation if not booting
            if (!globalConsoleBlock.powerOff.isStarted()) {
                globalConsoleBlock.powerOn.stop();
                globalConsoleBlock.powerOff.start(tickCount);
            }
            this.animate(globalConsoleBlock.powerOff, POWER_OFF, tickCount);
        }

        // Throttle and handbrake controls
        this.throttleLever.xRot = -125 - (30 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        this.handbrake.xRot = reactions.isHandbrakeEngaged() ? -155f : -125f;

        // Final render call
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
    public ResourceLocation getDefaultTexture() {
        return FACTORY_TEXTURE;
    }
}