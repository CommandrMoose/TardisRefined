package whocraft.tardis_refined.client.renderer.vortex;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.renderer.RenderHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Custom Time Vortex Renderer
 *
 * @author Edrax
 **/
@Environment(EnvType.CLIENT)
public class VortexRenderer {

    private static final RandomSource RAND = RandomSource.create();

    private static final VortexGradientTint BlueOrngGradient = new VortexGradientTint()
            .add(1f, 0, 46, 128)
            .add(0f, 8, 109, 196)
            .add(-0.5f, 193, 111, 20)
            .add(-1f, 234, 204, 77);

    private static final VortexGradientTint ModernVortex = new VortexGradientTint()
            .add(-1.0f, 238, 164, 107)
            .add(-0.5f, 199, 92, 159)
            .add(0.0f, 238, 164, 107)
            .add(0.5f, 199, 92, 159)
            .add(1.0f, 238, 164, 107);

    private static final VortexGradientTint PastelGradient = new VortexGradientTint()
            .add(1f, 223, 190, 223)
            .add(0.75f, 190, 210, 255)
            .add(0.5f, 243, 209, 215)
            .add(0f, 247, 223, 209)
            .add(-0.75f, 190, 210, 255)
            .add(-0.5f, 228, 190, 207)
            .add(-1f, 223, 190, 223);


    public VortexTypes vortexType;

    public final RenderHelper.DynamicTimeKeep time = new RenderHelper.DynamicTimeKeep(2);
    public VortexRenderer(VortexTypes type) {
        this.vortexType = type;
    }
    private final List<VortexQuad> vortex_quads = new ArrayList<>();
    public float opacity = 1;
    public float lightning_strike = 0;

    /**
     * Renders the Time Vortex
     */
    public void renderVortex(PoseStack pose, float opacity) {
        this.opacity = Math.min(opacity, 1);
        if (vortexType.movingGradient) this.vortexType.gradient.offset = time.getFloat() * 2;
        this.time.update();
        pose.pushPose();

        RenderHelper.rotateZYX(pose, 90.0f, 180, 0.0f);
        pose.scale(1, this.vortexType.rows, 1);

        for (int row = -this.vortexType.rows; row < this.vortexType.rows; row++) {
            Tesselator tesselator = beginTextureColor(Mode.TRIANGLE_STRIP);
            pose.pushPose();
            pose.translate(0, o(row), 0);
            RenderHelper.rotateZYX(pose, 0, row * this.vortexType.twist, 0);

            renderCylinder(pose, row);

            pose.popPose();
            tesselator.end();
        }

        if (this.vortexType.decals) {
            Tesselator tesselator = beginTextureColor(Mode.QUADS);
            for (int i = 0; i < this.vortexType.rows / 2f; i++) {
                pose.pushPose();
                if (vortex_quads.size() < i + 1) {
                    vortex_quads.add(new VortexQuad(this.vortexType, this.time));
                    break;
                }
                vortex_quads.get(i).renderQuad(pose, (float) (i / (this.vortexType.rows / 2f)), this.opacity);
                this.lightning_strike += vortex_quads.get(i).lightning_strike * vortex_quads.get(i).lightning_strike / (this.vortexType.rows / 2f);
                pose.popPose();
            }
            //this.lightning_strike /= this.vortexType.rows / 2f;
            tesselator.end();
        }
        this.lightning_strike *= 0.9f;
        pose.popPose();
    }

    public void renderVortex(GuiGraphics guiGraphics, float opacity) {
        PoseStack pose = guiGraphics.pose();
        renderVortex(pose, opacity);
    }

    private void renderCylinder(PoseStack poseStack, int row) {
        float length = 1f / this.vortexType.rows;

        float oA = o(row + 1), oB = o(row);

        float radiusA = wobbleRadius(oA);
        float radiusB = wobbleRadius(oB);

        for (int s = 0; s <= this.vortexType.sides; s++) {
            float angle = 2 * Mth.PI * s / this.vortexType.sides;

            float xA = radiusA * Mth.cos(angle);
            float zA = radiusA * Mth.sin(angle);
            xA += xWobble(oA, (float) time.speed) * Mth.sin(oA);
            zA += zWobble(oA, (float) time.speed) * Mth.sin(oA);

            float xB = radiusB * Mth.cos(angle);
            float zB = radiusB * Mth.sin(angle);
            xB += xWobble(oB, (float) time.speed) * Mth.sin(oB);
            zB += zWobble(oB, (float) time.speed) * Mth.sin(oB);

            float u = (float) s / this.vortexType.sides * 0.5f;

            float timeOffset = time.getFloat();
            float uvOffset = length * row;
            float vA = length + uvOffset + timeOffset;
            float vB = 0.0f + uvOffset + timeOffset;

            float bA = radiusFunc(oA);
            float bB = radiusFunc(oB);

            vertexUVColor(poseStack, xA, length, zA, u, vA, bA, bA, bA, 1.0f, oA);
            RenderHelper.rotateZYX(poseStack, 0, -this.vortexType.twist, 0);
            vertexUVColor(poseStack, xB, 0, zB, u, vB, bB, bB, bB, 1, oB);
            RenderHelper.rotateZYX(poseStack, 0, this.vortexType.twist, 0);
        }

    }

    private Tesselator beginTextureColor(Mode mode) {
        return RenderHelper.beginTextureColor(this.vortexType.texture, mode, false);
    }

    private void vertexUVColor(@NotNull PoseStack pose, float x, float y, float z, float u, float v, float r, float g, float b, float a, float o) {
        float[] color = this.vortexType.gradient.getRGBf(o);
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
        return row / (float) this.vortexType.rows;
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


    public enum VortexTypes {

        CLOUDS(new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"), 9, 12, 10, true, true, BlueOrngGradient, false),
        WAVES(new ResourceLocation(TardisRefined.MODID, "textures/vortex/waves.png"), 9, 12, 20, true, true, BlueOrngGradient, false),
        STARS(new ResourceLocation(TardisRefined.MODID, "textures/vortex/stars.png"), 9, 12, 5, true, true, PastelGradient, true),
        FLOW(new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"), 9, 12, 5, true, true, ModernVortex, true),
        SPACE(new ResourceLocation(TardisRefined.MODID, "textures/vortex/stars_2.png"), 9, 12, 5, true, true, ModernVortex, false);

        public final ResourceLocation texture;
        public final VortexGradientTint gradient;
        public int sides = 9, rows = 12;
        public boolean decals = true;
        public boolean lightning = false;
        public boolean movingGradient = false;
        float twist = 10;

        VortexTypes(ResourceLocation texture, int sides, int rows, float twist, boolean lightning, boolean decals, VortexGradientTint gradient, boolean movingGradient) {
            this.texture = texture;
            this.lightning = lightning;
            this.sides = sides;
            this.rows = rows;
            this.twist = twist;
            this.decals = decals || lightning;
            this.gradient = gradient;
            this.movingGradient = movingGradient;
        }
    }

    private static class VortexQuad {

        public boolean valid = true, lightning = false;
        private float prev_tO = -1;
        private float u = 0, v = 0;
        private final float uvSize = 0.125f;
        private float lightning_a;
        private final VortexTypes vortexType;
        private final RenderHelper.DynamicTimeKeep time;
        public float lightning_strike = 0;

        public VortexQuad(VortexTypes type, RenderHelper.DynamicTimeKeep time) {
            this.vortexType = type;
            this.time = time;
        }

        private void rndQuad() {
            valid = true;
            prev_tO = 1;
            rndUV();
            lightning = RAND.nextBoolean() && this.vortexType.lightning;
        }

        private void rndUV() {
            u = RAND.nextIntBetweenInclusive(0, 3) * uvSize;
            v = RAND.nextIntBetweenInclusive(0, 3) * uvSize;
        }


        public void renderQuad(PoseStack poseStack, float time_offset, float opacity) {
            if (!valid) rndQuad();

            float tO = -(time.getFloat(time_offset) * 2) - 1;
            if (tO > prev_tO || !valid) {
                valid = false;
                return;
            }

            if (lightning && System.currentTimeMillis() % 5 == 0) if (lightning && Math.random() > 0.95f) {
                lightning_a = 3;
                if (tO > 0) lightning_strike = (opacity * (1 - Mth.abs(tO * tO)));
                assert Minecraft.getInstance().player != null;
                Minecraft.getInstance().player.playSound(RAND.nextBoolean() ? SoundEvents.LIGHTNING_BOLT_IMPACT : SoundEvents.LIGHTNING_BOLT_THUNDER, (opacity * (1 - Mth.abs(tO * tO))) * 0.5F, (float) (Math.random() * (1 - Mth.abs(tO))));
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
            RenderHelper.rotateZYX(poseStack, 0, -this.vortexType.twist, 0);
            RenderHelper.rotateZYX(poseStack, 0, tO * this.vortexType.rows * this.vortexType.twist, 0);
            vertexUVColor(poseStack, x - s, tO, z + s, u0, v1, val, alpha, tO, !lightning);
            vertexUVColor(poseStack, x + s, tO, z + s, u1, v1, val, alpha, tO, !lightning);
            vertexUVColor(poseStack, x + s, tO, z - s, u1, v0, val, alpha, tO, !lightning);
            vertexUVColor(poseStack, x - s, tO, z - s, u0, v0, val, alpha, tO, !lightning);

            poseStack.popPose();
            prev_tO = tO;
            lightning_a *= 0.9f;
            lightning_strike *= 0.9f;

        }

        private void vertexUVColor(@NotNull PoseStack pose, float x, float y, float z, float u, float v, float val, float a, float o, boolean tint) {
            float[] color = this.vortexType.gradient.getRGBf(o);
            if (tint)
                RenderHelper.vertexUVColor(pose, x, y, z, u, v, val * color[0], val * color[1], val * color[2], a);
            else
                RenderHelper.vertexUVColor(pose, x, y, z, u, v, val, val, val, a);
        }
    }

    public static class VortexGradientTint {
        private final Map<Float, float[]> gradient_map = new HashMap<>();
        public float offset = 0;

        public VortexGradientTint() {
        }

        /**
         * Adds a color to the gradient map
         *
         * @param pos position in the gradient the color should go in. can be from -1 to 1
         * @param r   RED 0 to 1
         * @param g   GREEN 0 to 1
         * @param b   BLUE 0 to 1
         * @return The VortexGradient with the color added
         */
        public VortexGradientTint add(float pos, float r, float g, float b) {
            this.gradient_map.put(pos, new float[]{r, g, b});
            return this;
        }

        public VortexGradientTint add(float pos, int r, int g, int b) {
            return add(pos, r / 255.0f, g / 255.0f, b / 255.0f);
        }

        public float[] getRGBf(float pos_original) {
            float r = 1, g = 1, b = 1;
            float[] out = new float[]{r, g, b};
            if (gradient_map.isEmpty()) return out;
            if (gradient_map.size() == 1) {
                for (float p : gradient_map.keySet()) {
                    out = gradient_map.get(p);
                }
                return out;
            }

            float pos = pos_original + offset;
            while (pos > 1) pos -= 2;
            while (pos < -1) pos += 2;

            float first = 0, second = 0, smallest_dist = 9999, second_smallest_dist = 1000;

            for (float p : gradient_map.keySet()) {
                float dist = Mth.abs(pos - p);
                if (dist < smallest_dist) {
                    second_smallest_dist = smallest_dist;
                    smallest_dist = dist;
                    second = first;
                    first = p;
                }
            }

            if (gradient_map.get(first) == null || gradient_map.get(second) == null) return out;

            r = Mth.lerp(smallest_dist / (smallest_dist + second_smallest_dist), gradient_map.get(first)[0], gradient_map.get(second)[0]);
            g = Mth.lerp(smallest_dist / (smallest_dist + second_smallest_dist), gradient_map.get(first)[1], gradient_map.get(second)[1]);
            b = Mth.lerp(smallest_dist / (smallest_dist + second_smallest_dist), gradient_map.get(first)[2], gradient_map.get(second)[2]);

            return new float[]{r, g, b};
        }
    }
}
