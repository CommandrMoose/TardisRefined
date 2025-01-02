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