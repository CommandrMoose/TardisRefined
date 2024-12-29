package whocraft.tardis_refined.client.renderer.vortex;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

import static whocraft.tardis_refined.client.overlays.VortexOverlay.VORTEX;
import static whocraft.tardis_refined.client.screen.screens.ShellSelectionScreen.GLOBALSHELL_BLOCKENTITY;

public class ShellRenderer {


    public static void renderShell(GuiGraphics guiGraphics, float control, float alpha, int throttle) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {

            TardisClientData tardisClientData = TardisClientData.getInstance(tardisPlayerInfo.getPlayerPreviousPos().getDimensionKey());
            ResourceLocation shellPattern = tardisClientData.getShellPattern();
            ResourceLocation shellTheme = tardisClientData.getShellTheme();
            ShellPattern fullPattern = ShellPatterns.getPatternOrDefault(shellTheme, shellPattern);
            ShellModel model = ShellModelCollection.getInstance().getShellEntry(shellTheme).getShellModel(fullPattern);
            model.setDoorPosition(false);

            Lighting.setupForEntityInInventory();

            PoseStack pose = guiGraphics.pose();
            pose.pushPose();

            // Position the shell and apply scale
            RenderHelper.rotateZYX(pose, 180, 0, 0);

            // Time-based calculations for loop able motion and rotation
            long time = System.currentTimeMillis();
            float timeFactor = (time % 4000L) / 4000.0f * (float) (2 * Math.PI);

            // Chaotic but loop able rotations
            float xR = (float) Math.sin(timeFactor * 2) * 15.0f; // Wobble on X-axis
            float yR = ((timeFactor * 360 / (float) (2 * Math.PI)) % 360) * (1 + throttle * 0.5f); // Continuous spin on Y-axis
            float zR = (float) Math.cos(timeFactor * 3) * 10.0f; // Wobble on Z-axis

            //pose.translate(0, -1.5, 0);//yR * control
            RenderHelper.rotateZYX(pose, xR * control, 0, zR * control);
            RenderHelper.rotateZYX(pose, 0, yR * control, 0);
            //pose.translate(0, 1.5, 0);
            VertexConsumer vertexConsumer = guiGraphics.bufferSource().getBuffer(model.renderType(model.getShellTexture(ShellPatterns.getPatternOrDefault(shellTheme, shellPattern), false)));
            RenderSystem.enableBlend();
            GLOBALSHELL_BLOCKENTITY.setTardisId(tardisClientData.getLevelKey());

            if(Platform.isForge()) {

                float scale = 2.5F;
                pose.scale(scale, scale, scale);
            }


            model.renderShell(GLOBALSHELL_BLOCKENTITY, false, false, pose, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);

            if (fullPattern.shellTexture().emissive()) {
                VertexConsumer vertexConsumerLighting = guiGraphics.bufferSource().getBuffer(RenderType.entityTranslucentEmissive(model.getShellTexture(ShellPatterns.getPatternOrDefault(shellTheme, shellPattern), true)));
                model.renderShell(GLOBALSHELL_BLOCKENTITY, false, false, pose, vertexConsumerLighting, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);
            }


            VertexConsumer lightning = guiGraphics.bufferSource().getBuffer(RenderType.entityGlint());
            if (VORTEX.lightning_strike > 0.4) {
                model.renderShell(GLOBALSHELL_BLOCKENTITY, false, false, pose, lightning, 15728880, OverlayTexture.NO_OVERLAY, 1, 1, 1, alpha);
            }

            guiGraphics.flush();
            pose.popPose();
        });
    }
}
