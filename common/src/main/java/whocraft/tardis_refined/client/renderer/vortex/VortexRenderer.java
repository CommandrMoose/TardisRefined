package whocraft.tardis_refined.client.renderer.vortex;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Time Vortex Renderer
 *
 * @author Edrax
 **/
@Environment(EnvType.CLIENT)
public class VortexRenderer {

    private static final RandomSource RAND = RandomSource.create();
    public VortexRegistry vortexType;
    public final RenderHelper.DynamicTimeKeep time = new RenderHelper.DynamicTimeKeep(2);
    private final List<VortexQuad> vortex_quads = new ArrayList<>();
    public float opacity = 1;
    public float lightning_strike = 0;

    public VortexRenderer(VortexRegistry type) {
        this.vortexType = type;
    }

    /**
     * Renders the Time Vortex
     */
    public void renderVortex(PoseStack pose, float opacity, boolean half) {
        this.opacity = Math.min(opacity, 1);
        if (vortexType.isMovingGradient()) this.vortexType.getGradient().offset = time.getFloat() * 2;
        this.time.update();
        pose.pushPose();
        RenderHelper.rotateZYX(pose, 90.0f, 180, 0.0f);
        pose.scale(1, this.vortexType.getRows(), 1);

        for (int row = half ? 0 : -this.vortexType.getRows(); row < this.vortexType.getRows(); row++) {
            Tesselator tesselator = beginTextureColor(Mode.TRIANGLE_STRIP);
            pose.pushPose();
            pose.translate(0, o(row), 0);
            RenderHelper.rotateZYX(pose, 0, row * this.vortexType.getTwist(), 0);

            renderCylinder(pose, row);

            pose.popPose();
            tesselator.end();
        }

        if (this.vortexType.hasDecals()) {
            Tesselator tesselator = beginTextureColor(Mode.QUADS);
            for (int i = 0; i < this.vortexType.getRows() / 2f; i++) {
                if (vortex_quads.size() < i + 1) {
                    vortex_quads.add(new VortexQuad());
                    break;
                }
                pose.pushPose();
                vortex_quads.get(i).renderQuad(pose, time, vortexType, i / (this.vortexType.getRows() / 2f), this.opacity);
                this.lightning_strike += vortex_quads.get(i).lightning_strike * vortex_quads.get(i).lightning_strike / (this.vortexType.getRows() / 2f);
                pose.popPose();
            }
            //this.lightning_strike /= this.vortexType.rows / 2f;
            tesselator.end();
        }
        this.lightning_strike *= 0.9f;
        pose.popPose();
    }

    public void renderVortex(GuiGraphics guiGraphics, float opacity, boolean half) {
        PoseStack pose = guiGraphics.pose();
        renderVortex(pose, opacity, half);
    }

    public void renderVortex(GuiGraphics guiGraphics, float opacity) {
        renderVortex(guiGraphics, opacity, false);
    }

    private void renderCylinder(PoseStack poseStack, int row) {
        float length = 1f / this.vortexType.getRows();

        float oA = o(row + 1), oB = o(row);

        float radiusA = wobbleRadius(oA);
        float radiusB = wobbleRadius(oB);

        for (int s = 0; s <= this.vortexType.getSides(); s++) {
            float angle = 2 * Mth.PI * s / this.vortexType.getSides();

            float xA = radiusA * Mth.cos(angle);
            float zA = radiusA * Mth.sin(angle);
            xA += xWobble(oA, (float) time.speed) * Mth.sin(oA);
            zA += zWobble(oA, (float) time.speed) * Mth.sin(oA);

            float xB = radiusB * Mth.cos(angle);
            float zB = radiusB * Mth.sin(angle);
            xB += xWobble(oB, (float) time.speed) * Mth.sin(oB);
            zB += zWobble(oB, (float) time.speed) * Mth.sin(oB);

            float u = (float) s / this.vortexType.getSides() * 0.5f;

            float timeOffset = time.getFloat();
            float uvOffset = length * row;
            float vA = length + uvOffset + timeOffset;
            float vB = 0.0f + uvOffset + timeOffset;

            float bA = radiusFunc(oA);
            float bB = radiusFunc(oB);

            vertexUVColor(poseStack, xA, length, zA, u, vA, bA, bA, bA, 1.0f, oA);
            RenderHelper.rotateZYX(poseStack, 0, -this.vortexType.getTwist(), 0);
            vertexUVColor(poseStack, xB, 0, zB, u, vB, bB, bB, bB, 1, oB);
            RenderHelper.rotateZYX(poseStack, 0, this.vortexType.getTwist(), 0);
        }

    }

    private Tesselator beginTextureColor(Mode mode) {
        return RenderHelper.beginTextureColor(this.vortexType.getTexture(), mode, false);
    }

    private void vertexUVColor(@NotNull PoseStack pose, float x, float y, float z, float u, float v, float r, float g, float b, float a, float o) {
        float[] color = this.vortexType.getGradient().getRGBf(o);
        RenderHelper.vertexUVColor(pose, x, y, z, u, v, r * color[0], g * color[1], b * color[2], a * this.opacity);
    }

    private static float timingWithOffset(float speed, float offset) {
        long long_speed = (long) (speed * 1000L);
        long time = System.currentTimeMillis() + (long) (1000L * offset);
        try {
            return (time % long_speed) / (speed * 1000.0f);
        } catch (Exception e) {
            return 1;
        }
    }

    private static float timing(float speed) {
        return timingWithOffset(speed, 0.0f);
    }

    private float o(int row) {
        return row / (float) this.vortexType.getRows();
    }

    private static float radiusFunc(float o) {
        return -(o * o) + 1;
    }

    private static float wobbleRadius(float o) {
        return radiusFunc(o) * (1 + (0.05f) * Mth.sin(Mth.DEG_TO_RAD * 360 * (o + timing(687))) * Mth.sin(Mth.DEG_TO_RAD * 360 * (o + timing(9852))));
    }

    private static float xWobble(float o, float SPEED) {
        return (Mth.sin(o * 1 + timing(1.999f) * 2 * Mth.PI) + Mth.sin(o * 0.5f + timing(3.778f) * 2 * Mth.PI)) * SPEED * 2;
    }

    private static float zWobble(float o, float SPEED) {
        return (Mth.cos(o * 1 + timing(2.256f) * 2 * Mth.PI) + Mth.cos(o * 0.5f + timing(3.271f) * 2 * Mth.PI)) * SPEED * 2;
    }


    private static class VortexQuad {

        public boolean valid = true, lightning = false;
        private float prev_tO = -1;
        private float u = 0, v = 0;
        private final float uvSize = 0.125f;
        private float lightning_a;
        public float lightning_strike = 0;

        public VortexQuad() {
        }

        private void rndQuad(VortexRegistry vortexType) {
            valid = true;
            prev_tO = 1;
            rndUV();
            lightning = RAND.nextBoolean() && vortexType.hasLightning();
        }

        private void rndUV() {
            u = RAND.nextIntBetweenInclusive(0, 3) * uvSize;
            v = RAND.nextIntBetweenInclusive(0, 3) * uvSize;
        }


        public void renderQuad(PoseStack poseStack, RenderHelper.DynamicTimeKeep time, VortexRegistry vortexType, float time_offset, float opacity) {
            if (!valid) rndQuad(vortexType);

            float tO = -(time.getFloat(time_offset) * 2) - 1;
            if (tO > prev_tO || !valid) {
                valid = false;
                return;
            }

            if (lightning && System.currentTimeMillis() % 5 == 0) if (lightning && Math.random() > 0.95f) {
                lightning_a = 3;
                if (tO > 0) lightning_strike = (opacity * (1 - Mth.abs(tO * tO)));
                TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
                    if (!tardisPlayerInfo.isViewingTardis()) return;
                    if (!tardisPlayerInfo.isRenderVortex()) return;
                    assert Minecraft.getInstance().player != null;
                    Minecraft.getInstance().player.playSound(RAND.nextBoolean() ? SoundEvents.LIGHTNING_BOLT_IMPACT : SoundEvents.LIGHTNING_BOLT_THUNDER, (opacity * (1 - Mth.abs(tO * tO))) * 0.5F, (float) (Math.random() * (1 - Mth.abs(tO))));
                });

                rndUV();
            }

            float u0 = 0.5f + u, v0 = v + (lightning ? 0.5f : 0);
            float u1 = u0 + uvSize, v1 = v0 + uvSize;

            float x = xWobble(tO, (float) time.speed) * Mth.sin(tO), z = zWobble(tO, (float) time.speed) * Mth.sin(tO);
            float s = wobbleRadius(tO);
            float val = lightning ? 1 : radiusFunc(tO);

            float alpha = lightning ? lightning_a : val;
            alpha = Math.min(alpha, 1);
            alpha *= opacity;
            poseStack.pushPose();
            RenderHelper.rotateZYX(poseStack, 0, -vortexType.getTwist(), 0);
            RenderHelper.rotateZYX(poseStack, 0, tO * vortexType.getRows() * vortexType.getTwist(), 0);
            vertexUVColor(poseStack, x - s, tO, z + s, u0, v1, val, alpha, tO, !lightning, vortexType);
            vertexUVColor(poseStack, x + s, tO, z + s, u1, v1, val, alpha, tO, !lightning, vortexType);
            vertexUVColor(poseStack, x + s, tO, z - s, u1, v0, val, alpha, tO, !lightning, vortexType);
            vertexUVColor(poseStack, x - s, tO, z - s, u0, v0, val, alpha, tO, !lightning, vortexType);

            poseStack.popPose();
            prev_tO = tO;
            lightning_a *= 0.9f;
            lightning_strike *= 0.9f;

        }

        private void vertexUVColor(@NotNull PoseStack pose, float x, float y, float z, float u, float v, float val, float a, float o, boolean tint, VortexRegistry vortexType) {
            float[] color = vortexType.getGradient().getRGBf(o);
            if (tint)
                RenderHelper.vertexUVColor(pose, x, y, z, u, v, val * color[0], val * color[1], val * color[2], a);
            else
                RenderHelper.vertexUVColor(pose, x, y, z, u, v, val, val, val, a);
        }
    }

}
