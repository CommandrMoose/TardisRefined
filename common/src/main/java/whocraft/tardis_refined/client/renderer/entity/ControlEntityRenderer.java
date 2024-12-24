package whocraft.tardis_refined.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.joml.Matrix4f;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TRParticles;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.compat.CuriosTrinketsUtil;


public class ControlEntityRenderer extends NoopRenderer<ControlEntity> {

    private static final ResourceLocation ICON_GOOD = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/control/control_good.png");
    private static final ResourceLocation ICON_SLIPPING = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/control/control_slipping.png");
    private static final ResourceLocation ICON_WARNING = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/control/control_warning.png");
    private static final ResourceLocation ICON_ALERT = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/control/control_alert.png");
    private static final ResourceLocation ICON_DANGER = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/control/control_danger.png");

    public ControlEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    private static void vertex(VertexConsumer builder, PoseStack matrixStack, float x, float y, float z, float u, float v, int light) {
        vertex(builder, matrixStack, x, y, z, u, v, 255, light);
    }

    private static void vertex(VertexConsumer builder, PoseStack matrixStack, float x, float y, float z, float u, float v, int alpha, int light) {
        PoseStack.Pose entry = matrixStack.last();
        builder.vertex(entry.pose(), x, y, z)
                .color(255, 255, 255, alpha)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(entry.normal(), 0F, 0F, -1F)
                .endVertex();
    }

    @Override
    protected boolean shouldShowName(ControlEntity entity) {
        return (TRConfig.CLIENT.CONTROL_NAMES.get() && Minecraft.renderNames() && isMouseOverEntity(entity));
    }

    private boolean isMouseOverEntity(ControlEntity entity) {
        HitResult hitResult = Minecraft.getInstance().hitResult;
        if (hitResult == null) return false;
        if (hitResult instanceof EntityHitResult entityHitResult) {
            return entityHitResult.getEntity() == entity;
        }
        return false;
    }

    @Override
    public void render(ControlEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (this.shouldShowName(entity)) {
            this.renderNameTag(entity, entity.getDisplayName(), poseStack, multiBufferSource, i);
        }

        Level entityLevel = entity.level();
        if (!CuriosTrinketsUtil.getInstance().getFirstFoundGlider(Minecraft.getInstance().player).isEmpty()) {
            if (entity.isTickingDown()) {
                if (entityLevel.random.nextInt(20) == 0) {
                    entityLevel.addParticle(TRParticles.GALLIFREY.get(), entity.getRandomX(0.1), entity.blockPosition().getY(), entity.getRandomZ(0.1), 0.0, 0.0, 0.0);
                }
            }
        }

    }

    @Override
    protected void renderNameTag(ControlEntity entity, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLightCoords) {

        double distanceSquared = this.entityRenderDispatcher.distanceToSqr(entity);
        if (!(distanceSquared > 2050.0)) {

            boolean isSolid = !entity.isDiscrete();
            float boundingBoxHeight = entity.getNameTagOffsetY() - 0.3f;
            int verticalTextOffset = 10;

            poseStack.pushPose();
            poseStack.translate(0.0, boundingBoxHeight, 0.0);

            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            float scale = 0.007F;
            poseStack.scale(-scale, -scale, scale);

            Matrix4f textMatrix = poseStack.last().pose();

            Font font = this.getFont();

            float textHorizontalPosition = (float) (-font.width(component) / 2);

            FormattedCharSequence sequence = component.getVisualOrderText();

            font.drawInBatch8xOutline(sequence, textHorizontalPosition, (float) verticalTextOffset, 16777215, 0, textMatrix, multiBufferSource, packedLightCoords);

            if (isSolid) {
                font.drawInBatch8xOutline(sequence, textHorizontalPosition, (float) verticalTextOffset, 16777215, 0, textMatrix, multiBufferSource, packedLightCoords);
            }

            // Damage used for the icon later on. Left for Jeryn.
            int entityHealth = entity.getControlHealth();

            poseStack.translate(0.0, 5, 0.0);
            renderControlIcon(entity, component, getIconByState(entityHealth), poseStack, multiBufferSource, packedLightCoords);

            poseStack.popPose();


        }


    }

    public ResourceLocation getIconByState(int entityHealth) {
        if (entityHealth == 10) {
            return ICON_GOOD;
        } else if (entityHealth >= 8) {
            return ICON_SLIPPING;
        } else if (entityHealth > 5) {
            return ICON_WARNING;
        } else if (entityHealth > 3) {
            return ICON_ALERT;
        } else {
            return ICON_DANGER;
        }
    }


    private void renderControlIcon(ControlEntity entity, Component component, ResourceLocation texture, PoseStack matrixStackIn, MultiBufferSource buffer, int light) {

        float offset = (float) -(Minecraft.getInstance().font.width(component) / 2 + 18);
        VertexConsumer builder = buffer.getBuffer(RenderType.text(texture));
        int alpha = 32;

        if (entity.isDiscrete()) {
            vertex(builder, matrixStackIn, offset, 16F, 0F, 0F, 1F, alpha, light);
            vertex(builder, matrixStackIn, offset + 16F, 16F, 0F, 1F, 1F, alpha, light);
            vertex(builder, matrixStackIn, offset + 16F, 0F, 0F, 1F, 0F, alpha, light);
            vertex(builder, matrixStackIn, offset, 0F, 0F, 0F, 0F, alpha, light);
        } else {
            vertex(builder, matrixStackIn, offset, 16F, 0F, 0F, 1F, light);
            vertex(builder, matrixStackIn, offset + 16F, 16F, 0F, 1F, 1F, light);
            vertex(builder, matrixStackIn, offset + 16F, 0F, 0F, 1F, 0F, light);
            vertex(builder, matrixStackIn, offset, 0F, 0F, 0F, 0F, light);

            VertexConsumer builderSeeThrough = buffer.getBuffer(RenderType.textSeeThrough(texture));
            vertex(builderSeeThrough, matrixStackIn, offset, 16F, 0F, 0F, 1F, alpha, light);
            vertex(builderSeeThrough, matrixStackIn, offset + 16F, 16F, 0F, 1F, 1F, alpha, light);
            vertex(builderSeeThrough, matrixStackIn, offset + 16F, 0F, 0F, 1F, 0F, alpha, light);
            vertex(builderSeeThrough, matrixStackIn, offset, 0F, 0F, 0F, 0F, alpha, light);
        }

    }

}
