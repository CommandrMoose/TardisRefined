package whocraft.tardis_refined.client.renderer;

import com.mojang.authlib.yggdrasil.ProfileResult;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.util.UUID;

public class RenderHelper {

    public static Tesselator tesselator;

    public static void renderPlayerFace(GuiGraphics guiGraphics, int i, int j, int k, UUID uUID) {
        Minecraft minecraft = Minecraft.getInstance();
        ProfileResult profileResult = minecraft.getMinecraftSessionService().fetchProfile(uUID, false);
        PlayerSkin playerSkin = profileResult != null ? minecraft.getSkinManager().getInsecureSkin(profileResult.profile()) : DefaultPlayerSkin.get(uUID);
        PlayerFaceRenderer.draw(guiGraphics, playerSkin.texture(), i, j, k);
    }

    public static void renderFilledBox(PoseStack stack, VertexConsumer vertexConsumer, AABB box, float red, float green, float blue, float alpha, int combinedLightIn) {
        Matrix4f matrix = stack.last().pose();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.maxY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.minY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.minY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.minY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.minY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.maxY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.minY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.minY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.minY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.maxY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.maxX, (float) box.minY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.minY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.minY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.maxY, (float) box.maxZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        vertexConsumer.vertex(matrix, (float) box.minX, (float) box.maxY, (float) box.minZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
    }


    public static void drawGlowingBox(PoseStack poseStack, VertexConsumer consumer, float length, float height, float width, float red, float green, float blue, float alpha, int combinedLightIn) {
        AABB box = new AABB(-length / 2F, -height / 2f, -width / 2F, length / 2F, height / 2f, width / 2F);
        renderFilledBox(poseStack, consumer, box, 1F, 1F, 1F, alpha, combinedLightIn);

        for (int i = 0; i < 3; i++) {
            renderFilledBox(poseStack, consumer, box.inflate(i * 0.5F * 0.0625F), red, green, blue, (1F / i / 2) * alpha, combinedLightIn);
        }
    }

    public static void rotateZYX(PoseStack poseStack, float x, float y, float z) {
        poseStack.mulPose((new Quaternionf()).rotationZYX(Mth.DEG_TO_RAD * z, Mth.DEG_TO_RAD * y, Mth.DEG_TO_RAD * x));
    }

    public static Tesselator beginTextureColor(ResourceLocation texture, VertexFormat.Mode mode, boolean cull) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.enableBlend();
        if (cull) RenderSystem.enableCull();
        else RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        tesselator = Tesselator.getInstance();
        tesselator.getBuilder().begin(mode, DefaultVertexFormat.POSITION_TEX_COLOR);
        return tesselator;
    }

    public static void vertexUVColor(@NotNull PoseStack pose, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        tesselator.getBuilder().vertex(pose.last().pose(), x, y, z).uv(u, v).color(r, g, b, a).endVertex();
    }

    public static class CustomProgressBar {
        private final ResourceLocation texture;

        private final int height, width;
        private final int framesU, framesV, mspf;
        private int anim = 1;
        private long last_frame;
        private boolean started = false;
        public boolean animate = true;

        public CustomProgressBar(ResourceLocation texture, int texWidth, int texHeight, int height, int width, int fps) {
            this.texture = texture;
            this.height = height;
            this.width = width;
            this.mspf = 1000 / fps;
            this.framesU = texHeight / height;
            this.framesV = texWidth / width;
        }

        public void blit(GuiGraphics gg, int x, int y, double progress) {
            if (!started) {
                started = true;
                last_frame = System.currentTimeMillis();
            }

            int u = anim / framesU;
            int v = anim % framesU;

            gg.blit(texture, x, y, 0, 0, width, height);
            gg.blit(texture, x, y, width * u, height * v, (int) (width * progress), height);

            if (System.currentTimeMillis() - last_frame > mspf) {
                anim++;
                last_frame = System.currentTimeMillis();
            }
            if (anim >= framesU * framesV || !animate) anim = 1;
        }
    }


    public static class DynamicTimeKeep {
        public double speed = 1f;
        private long time = Long.MIN_VALUE;
        private long last_time = System.currentTimeMillis();

        public DynamicTimeKeep() {
            this.last_time = System.currentTimeMillis();
        }

        public DynamicTimeKeep(double speed) {
            this.speed = speed;
            this.last_time = System.currentTimeMillis();
        }

        public void update() {
            long diff = System.currentTimeMillis() - this.last_time;
            diff *= speed;
            this.time += diff;
            this.last_time = System.currentTimeMillis();
        }

        public float getFloat() {
            return (this.time % 1000L) / 1000.0f;
        }

        public double getDouble() {
            return (this.time % 1000L) / 1000.0;
        }

        public float getFloat(float offset) {
            long offsetTime = this.time + (long) (offset * 1000);
            return (offsetTime % 1000L) / 1000.0f;
        }

        public double getDouble(double offset) {
            long offsetTime = this.time + (long) (offset * 1000);
            return (offsetTime % 1000L) / 1000.0f;
        }

        public void speedUp(double amount) {
            this.speed += amount;
        }

        public void slowDown(double amount) {
            this.speed -= amount;
        }
    }
}
