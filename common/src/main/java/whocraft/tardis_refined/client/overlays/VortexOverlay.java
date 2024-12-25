package whocraft.tardis_refined.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexSorting;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.renderer.vortex.VortexRenderer;
import whocraft.tardis_refined.client.screen.screens.ShellSelectionScreen;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

import static whocraft.tardis_refined.client.renderer.vortex.ShellRenderer.renderShell;
import static whocraft.tardis_refined.client.screen.main.MonitorOS.MonitorOSExtension.GLOBALSHELL_BLOCKENTITY;

public class VortexOverlay {

    public static final VortexRenderer VORTEX = new VortexRenderer(VortexRegistry.CLOUDS.get());

    private static double tardisX = 0.0D;
    private static double tardisY = 0.0D;
    private static double velX = 0.0D;
    private static double velY = 0.0D;
    private static float DEMAT = 0.0f;
    private static float IMMERSION = 0.0f;
    private static float YROT = 0.0f;
    private static float VYR = 0.0f;
    private static long LAST_TIME = System.currentTimeMillis();

    public static void update(GuiGraphics gg) {
        if (GLOBALSHELL_BLOCKENTITY == null) {
            ShellSelectionScreen.generateDummyGlobalShell();
            return;
        }

        double speed = 0.01D;
        Minecraft mc = Minecraft.getInstance();

        if (mc.screen == null) { // Ensure no screen (like inventory) is open
            if (mc.options.keyUp.isDown()) velY += speed;

            if (mc.options.keyDown.isDown()) velY -= speed;

            if (mc.options.keyLeft.isDown()) velX -= speed;

            if (mc.options.keyRight.isDown()) velX += speed;
        }

        if (DEMAT > 1) DEMAT = 1;
        if (DEMAT < 0) DEMAT = 0;

        if (DEMAT >= 1) {
            IMMERSION += (System.currentTimeMillis() - LAST_TIME) / (1000.0f + (5000.0f * IMMERSION));
        } else {
            IMMERSION *= 0.9f;
        }

        if (IMMERSION > 1) IMMERSION = 1;
        if (IMMERSION < 0) IMMERSION = 0;

        if (VORTEX.lightning_strike > 0.4)
            velX -= (Math.random() > 0.5 ? 0.001 : -0.001) * VORTEX.lightning_strike * 90 * Mth.sin(VORTEX.lightning_strike);
        if (VORTEX.lightning_strike > 0.4)
            velY -= (Math.random() > 0.5 ? 0.001 : -0.001) * VORTEX.lightning_strike * 90 * Mth.sin(VORTEX.lightning_strike);


        if (tardisX * tardisX + tardisY * tardisY > 1) {
            try {
                double f = Math.sqrt(tardisY * tardisY + tardisX * tardisX);
                tardisX /= f;
                tardisY /= f;
            } finally {

            }
        }

        if (velX > 1) velX = 1;
        if (velX < -1) velX = -1;
        if (velY > 1) velY = 1;
        if (velY < -1) velY = -1;

        tardisX += velX;
        tardisY += velY;
        velX *= 0.9;
        velY *= 0.9;
        tardisX *= 0.99;
        tardisY *= 0.99;
    }

    public static void renderOverlay(GuiGraphics gg) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            /*Activation Logic*/
            TardisClientData tardisClientData = TardisClientData.getInstance(tardisPlayerInfo.getPlayerPreviousPos().getDimensionKey());

            Minecraft mc = Minecraft.getInstance();
            PoseStack pose = gg.pose();
            float width = gg.guiWidth();
            float height = gg.guiHeight();

            VORTEX.vortexType = VortexRegistry.VORTEX_DEFERRED_REGISTRY.get(tardisClientData.getVortex());

            /*
                Needs tweaking, but am not quite sure how to fix.
             */

            boolean takeoff = tardisClientData.isTakingOff() || tardisClientData.isFlying();
            boolean land = tardisClientData.isLanding() || !tardisClientData.isFlying();

            //DEV TESTING
            //takeoff = mc.options.keyShift.isDown();
            //land = !takeoff;

            if (takeoff) {
                DEMAT += (System.currentTimeMillis() - LAST_TIME) / 12000.0f;
            }
            if (land) {
                DEMAT -= (System.currentTimeMillis() - LAST_TIME) / 12000.0f;
            }


            if (!tardisPlayerInfo.isViewingTardis()) return;
            if (!tardisPlayerInfo.isRenderVortex()) return;

            VortexOverlay.update(gg);

            float demat_transparency = Mth.cos(DEMAT * (Mth.PI) / (2f)) * (Mth.cos(16f * Mth.PI * DEMAT) * 0.5f + 0.5f) * (-DEMAT * 0.5f + 0.5f) - DEMAT * 0.5f + 0.5f;

            Camera camera = mc.gameRenderer.getMainCamera();
            Vec3 camPos = camera.getPosition().subtract(mc.player.position()).subtract(0, 1.62, 0);
            double camdist = Math.sqrt(camPos.x * camPos.x + camPos.y * camPos.y + camPos.z * camPos.z);

            float mul = IMMERSION;
            float mulinv = 1 - IMMERSION;

            float xRot = camera.getXRot() * mulinv;
            if (DEMAT < 1) {
                YROT = camera.getYRot() + 180;
                while (YROT > 180) YROT -= 360;
                while (YROT < -180) YROT += 360;
            }

            long time = System.currentTimeMillis();
            float timeFactor = (time % 4000L) / 4000.0f * (float) (2 * Math.PI);
            if (DEMAT < 1)
                VYR = ((timeFactor * 360 / (float) (2 * Math.PI)) % 360) * (1 + tardisClientData.getThrottleStage() * 0.5f);

            if (DEMAT > 0 && DEMAT < 1)
                mc.getCameraEntity().setYRot(mc.getCameraEntity().getYRot() - ((System.currentTimeMillis() - LAST_TIME) / 10.0f) * (1 + tardisClientData.getThrottleStage() * 0.5f));

            VORTEX.time.speed = (0.3f + tardisClientData.getThrottleStage() * 0.1f);

            /*Perspective Rendering*/
            RenderSystem.backupProjectionMatrix();

            Matrix4f perspective = new Matrix4f();
            perspective.perspective((float) Math.toRadians(mc.options.fov().get()), width / height, 1, 9999, false, perspective);
            perspective.translate(0, 0, 11000f - (float) camdist * mulinv - 5 * mul);
            RenderSystem.setProjectionMatrix(perspective, VertexSorting.DISTANCE_TO_ORIGIN);

            pose.pushPose();
            pose.mulPose(Axis.XP.rotationDegrees(xRot));
            pose.mulPose(Axis.YP.rotationDegrees(YROT * mulinv));
            //Vortex
            pose.pushPose();
            pose.scale(100, 100, 100);
            pose.mulPose(Axis.YP.rotationDegrees(VYR * mulinv));
            VORTEX.renderVortex(gg, 1 - demat_transparency);
            pose.popPose();

            //Box
            pose.translate(4 * tardisX * mul, 4 * tardisY * mul, 0);
            pose.mulPose(Axis.ZP.rotationDegrees((float) (mul * -450 * velX)));
            pose.mulPose(Axis.ZP.rotationDegrees(mul * VORTEX.lightning_strike * 90 * Mth.sin(VORTEX.lightning_strike)));

            pose.pushPose();
            pose.scale(0.95f, 0.95f, 0.95f);
            renderShell(gg, IMMERSION, 1 - demat_transparency, tardisClientData.getThrottleStage());
            pose.popPose();

            pose.popPose();

            //Restore Ortho view
            RenderSystem.restoreProjectionMatrix();
        });
        LAST_TIME = System.currentTimeMillis();
    }
}
