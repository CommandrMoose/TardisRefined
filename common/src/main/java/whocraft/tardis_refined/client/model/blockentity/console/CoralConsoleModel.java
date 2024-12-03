package whocraft.tardis_refined.client.model.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
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
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class CoralConsoleModel extends HierarchicalModel implements ConsoleUnit {


    public static final AnimationDefinition IDLE = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/coral/idle.json"));
    public static final AnimationDefinition FLIGHT = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/coral/flight.json"));


    private static final ResourceLocation CORAL_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/coral/coral_console.png");
    private final ModelPart throttle;
    private final ModelPart handbrake;
    private final ModelPart base_console;
    private final ModelPart anim_parts;
    private final ModelPart root;

    public CoralConsoleModel(ModelPart root) {
        this.root = root;
        this.base_console = root.getChild("base_console");
        this.throttle = base_console.getChild("controls").getChild("borders").getChild("bone23").getChild("bone17").getChild("throttle");
        this.anim_parts = root.getChild("anim_parts");
        this.handbrake = JsonToAnimationDefinition.findPart(this, "handbrake");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        base_console.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        anim_parts.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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

        float rot = 0f + (2f * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        this.throttle.xRot = rot;

        this.handbrake.xRot = !reactions.isHandbrakeEngaged() ? 1f : -0.25f;

        base_console.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        anim_parts.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.CORAL.getId();
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return CORAL_TEXTURE;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}