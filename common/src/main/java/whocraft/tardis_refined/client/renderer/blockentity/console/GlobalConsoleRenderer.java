package whocraft.tardis_refined.client.renderer.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.console.ConsoleModelCollection;
import whocraft.tardis_refined.client.model.blockentity.console.ConsoleUnit;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.client.screen.selections.ShellSelectionScreen;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

public class GlobalConsoleRenderer implements BlockEntityRenderer<GlobalConsoleBlockEntity>, BlockEntityRendererProvider<GlobalConsoleBlockEntity> {

    private static final Vec3 crystalHolo = new Vec3(0.3f, -1.725, 0.655);
    private static final Vec3 crystalHoloColor = new Vec3(1f, 0.64f, 0f);

    private static final Vec3 initiativeHolo = new Vec3(-1.23, -1.225, 1.775F);
    private static final Vec3 initiativeHoloColor = new Vec3(0, 0.8f, 1f);


    public GlobalConsoleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(GlobalConsoleBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));

        ResourceLocation theme = blockEntity.theme();

        ConsoleUnit consoleModel = ConsoleModelCollection.getInstance().getConsoleModel(theme);
        consoleModel.renderConsole(blockEntity, blockEntity.getLevel(), poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(consoleModel.getTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        if (blockEntity != null && blockEntity.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            if (blockEntity.pattern() != null && blockEntity.pattern().patternTexture().emissive()) {
                consoleModel.renderConsole(blockEntity, blockEntity.getLevel(), poseStack, bufferSource.getBuffer(RenderType.entityTranslucentEmissive(consoleModel.getTexture(blockEntity, true))), 15728640, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
            }
        }

        poseStack.popPose();

        if (blockEntity != null && blockEntity.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            if (theme.toString().equals(ConsoleTheme.CRYSTAL.getId().toString())) {
                renderHoloShell(crystalHolo, 270, blockEntity, poseStack, bufferSource, packedLight, crystalHoloColor);
            }

            if (theme.toString().equals(ConsoleTheme.INITIATIVE.getId().toString())) {
                renderHoloShell(initiativeHolo, -30 + 180, blockEntity, poseStack, bufferSource, packedLight, initiativeHoloColor);
            }
        }


    }

    private void renderHoloShell(Vec3 offset, int rotation, GlobalConsoleBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Vec3 color) {
        if (blockEntity.getLevel().random.nextInt(20) != 0) {
            poseStack.pushPose();

            // Fetch shell data
            TardisClientData reactions = TardisClientData.getInstance(blockEntity.getLevel().dimension());
            ResourceLocation shellTheme = reactions.getShellTheme();
            ResourceLocation shellPattern = reactions.getShellPattern();
            ShellPattern pattern = ShellPatterns.getPatternOrDefault(shellTheme, shellPattern);

            var model = ShellModelCollection.getInstance().getShellEntry(shellTheme).getShellModel(pattern);
            model.setDoorPosition(false);

            // Base rotation and positioning
            poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
            poseStack.translate(offset.x, offset.y, offset.z);

            // Add subtle floating animation
            if (reactions.isFlying()) {
                float floatingOffset = (float) Math.sin(blockEntity.getLevel().getGameTime() / 15.0) * 0.05f;
                poseStack.translate(0, floatingOffset, 0);
            }

            // Random subtle jitter
            poseStack.translate(
                    blockEntity.getLevel().random.nextFloat() * 0.005f - 0.0025f,
                    blockEntity.getLevel().random.nextFloat() * 0.005f - 0.0025f,
                    blockEntity.getLevel().random.nextFloat() * 0.005f - 0.0025f
            );

            float scaleModifier = 0.1f + (float) Math.sin(blockEntity.getLevel().getGameTime() / 20.0) * 0.005f;
            poseStack.scale(scaleModifier, scaleModifier, scaleModifier);

            // Add rotation effect
            if (reactions.isFlying()) {
                // Time-based calculations for loop able motion and rotation
                long time = System.currentTimeMillis();
                float timeFactor = (time % 4000L) / 4000.0f * (float) (2 * Math.PI);

                // Chaotic but loop able rotations
                float xR = (float) Math.sin(timeFactor * 2) * 15.0f; // Wobble on X-axis
                float yR = ((timeFactor * 360 / (float) (2 * Math.PI)) % 360) * reactions.getThrottleStage(); // Continuous spin on Y-axis
                float zR = (float) Math.cos(timeFactor * 3) * 10.0f; // Wobble on Z-axis
                int control = 1;
                RenderHelper.rotateZYX(poseStack, xR * control, yR * control, zR * control);

            } else {
                poseStack.mulPose(Axis.YP.rotationDegrees(rotation % 360));
            }

            if (ShellSelectionScreen.globalShellBlockEntity == null) {
                ShellSelectionScreen.generateDummyGlobalShell();
            }

            ShellSelectionScreen.globalShellBlockEntity.setTardisId(reactions.getLevelKey());

            // Dynamic flickering alpha for a hologram effect
            float flickerAlpha = 0.2f + blockEntity.getLevel().random.nextFloat() * 0.1f;

            boolean recoveryOrCrashing = reactions.isCrashing() || reactions.isInRecovery();

            float time = blockEntity.getLevel().getGameTime() / 100.0f;
            float red = recoveryOrCrashing ? 0.5f + (float) Math.sin(time) * 0.5f : (float) color.x;
            float green = recoveryOrCrashing ? 0.5f + (float) Math.sin(time + Math.PI / 2) * 0.5f : (float) color.y;
            float blue = recoveryOrCrashing ? 0.5f + (float) Math.sin(time + Math.PI) * 0.5f : (float) color.z;

            model.setIgnoreAnmationAlpha(!reactions.isTakingOff() && !reactions.isLanding());
            model.renderShell(
                    ShellSelectionScreen.globalShellBlockEntity,
                    false,
                    true,
                    poseStack,
                    bufferSource.getBuffer(RenderType.entityTranslucent(pattern.exteriorDoorTexture().texture())),
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    red,
                    green,
                    blue,
                    flickerAlpha
            );
            model.setIgnoreAnmationAlpha(false);

            poseStack.popPose();
        }

    }

    @Override
    public boolean shouldRenderOffScreen(GlobalConsoleBlockEntity blockEntity) {
        return true;
    }

    @Override
    public BlockEntityRenderer<GlobalConsoleBlockEntity> create(BlockEntityRendererProvider.Context context) {
        return new GlobalConsoleRenderer(context);
    }


}
