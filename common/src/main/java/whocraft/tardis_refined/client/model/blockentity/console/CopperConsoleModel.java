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

public class CopperConsoleModel extends HierarchicalModel implements ConsoleUnit {


    public static final AnimationDefinition IDLE = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/copper/idle.json"));
    public static final AnimationDefinition FLIGHT = Frame.loadAnimation(new ResourceLocation(TardisRefined.MODID, "frame/console/copper/flight.json"));

    private static final ResourceLocation COPPER_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/copper/copper_console.png");

    private final ModelPart root;
    private final ModelPart rotor;
    private final ModelPart misc;
    private final ModelPart misc2;
    private final ModelPart misc3;
    private final ModelPart misc4;
    private final ModelPart misc5;
    private final ModelPart north_left;
    private final ModelPart north_right;
    private final ModelPart east;
    private final ModelPart south_right;
    private final ModelPart south_left;
    private final ModelPart west;
    private final ModelPart modelRoot;
    private final ModelPart throttle;
    private final ModelPart handbrake;

    public CopperConsoleModel(ModelPart root) {
        this.modelRoot = root;
        this.root = root.getChild("root");
        this.rotor = root.getChild("rotor");
        this.misc = root.getChild("misc");
        this.misc2 = root.getChild("misc2");
        this.misc3 = root.getChild("misc3");
        this.misc4 = root.getChild("misc4");
        this.misc5 = root.getChild("misc5");
        this.north_left = root.getChild("north_left");
        this.north_right = root.getChild("north_right");
        this.east = root.getChild("east");
        this.south_right = root.getChild("south_right");
        this.south_left = root.getChild("south_left");
        this.west = root.getChild("west");
        this.throttle = north_right.getChild("bone203").getChild("bone213").getChild("main_lever_control2");
        this.handbrake = Frame.findPart(this, "lever_control8");
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        misc.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        misc2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        misc3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        misc4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        misc5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        north_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        north_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        east.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        south_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        south_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        west.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return modelRoot;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void renderConsole(GlobalConsoleBlockEntity globalConsoleBlock, Level level, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.modelRoot.getAllParts().forEach(ModelPart::resetPose);
        TardisClientData reactions = TardisClientData.getInstance(level.dimension());

        if (globalConsoleBlock != null && globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            if (!reactions.isFlying() && TRConfig.CLIENT.PLAY_CONSOLE_IDLE_ANIMATIONS.get() && globalConsoleBlock != null) {
                this.animate(globalConsoleBlock.liveliness, IDLE, Minecraft.getInstance().player.tickCount);
            } else {
                this.animate(reactions.ROTOR_ANIMATION, FLIGHT, Minecraft.getInstance().player.tickCount);
            }
        }

        float rot = 1f - (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        this.throttle.zRot = rot;

        this.handbrake.zRot = !reactions.isHandbrakeEngaged() ? 1f : -1f;

        modelRoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return COPPER_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.COPPER.getId();
    }
}