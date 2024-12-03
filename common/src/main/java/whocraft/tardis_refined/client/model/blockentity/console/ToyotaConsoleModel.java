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

public class ToyotaConsoleModel extends HierarchicalModel implements ConsoleUnit {

    public static final AnimationDefinition IDLE = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/toyota/idle.json"));
    public static final AnimationDefinition FLIGHT = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/toyota/flight.json"));


    private static final ResourceLocation TOYOTA_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/toyota/toyota_console.png");
    private final ModelPart bone181;
    private final ModelPart throttle;
    private final ModelPart handbrake;

    public ToyotaConsoleModel(ModelPart root) {
        this.bone181 = root.getChild("bone181");
        this.throttle = JsonToAnimationDefinition.findPart(this, "bone198");
        this.handbrake = JsonToAnimationDefinition.findPart(this, "bone202");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.bone181;
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

        float rot = 1f - (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        this.throttle.xRot = rot;

        this.handbrake.yRot = reactions.isHandbrakeEngaged() ? 2f : -1f;

        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return TOYOTA_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.TOYOTA.getId();
    }
}