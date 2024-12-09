package whocraft.tardis_refined.client.renderer.vortex;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.opengl.GL11;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.door.interior.ShellDoorModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.block.door.InternalDoorBlock;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

import java.util.SortedMap;

import static com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS;
import static net.minecraft.client.renderer.RenderStateShard.*;
import static whocraft.tardis_refined.client.overlays.VortexOverlay.VORTEX;

public class RenderTargetHelper {

    public RenderTarget renderTarget;
    private static final RenderTargetHelper RENDER_TARGET_HELPER = new RenderTargetHelper();
    public static StencilBufferStorage stencilBufferStorage = new StencilBufferStorage();

    public static void renderVortex(GlobalDoorBlockEntity blockEntity, float partialTick, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        BlockState blockstate = blockEntity.getBlockState();
        ResourceLocation theme = blockEntity.theme();

        float rotation = blockstate.getValue(InternalDoorBlock.FACING).toYRot();
        boolean isOpen = blockstate.getValue(InternalDoorBlock.OPEN);
        ShellDoorModel currentModel = ShellModelCollection.getInstance().getShellEntry(theme).getShellDoorModel(blockEntity.pattern());
        TardisClientData tardisClientData = TardisClientData.getInstance(blockEntity.getLevel().dimension());


        VORTEX.vortexType = VortexRegistry.VORTEX_DEFERRED_REGISTRY.get(tardisClientData.getVortex());

        if (tardisClientData.isFlying() && isOpen) {
            renderDoorOpen(blockEntity, stack, packedLight, rotation, currentModel, isOpen, tardisClientData);
        } else {
            renderNoVortex(blockEntity, stack, bufferSource, packedLight, rotation, currentModel, isOpen);
        }
    }

    public static void copyRenderTarget(RenderTarget src, RenderTarget dest) {
        GlStateManager._glBindFramebuffer(GlConst.GL_READ_FRAMEBUFFER, src.frameBufferId);
        GlStateManager._glBindFramebuffer(GlConst.GL_DRAW_FRAMEBUFFER, dest.frameBufferId);
        GlStateManager._glBlitFrameBuffer(0, 0, src.width, src.height, 0, 0, dest.width, dest.height, GlConst.GL_DEPTH_BUFFER_BIT | GlConst.GL_COLOR_BUFFER_BIT, GlConst.GL_NEAREST);
    }

    private static void renderDoorOpen(GlobalDoorBlockEntity blockEntity, PoseStack stack, int packedLight, float rotation, ShellDoorModel currentModel, boolean isOpen, TardisClientData tardisClientData) {
        stack.pushPose();

        // Fix transform
        stack.translate(0.5F, 1.5F, 0.5F);
        stack.mulPose(Axis.ZP.rotationDegrees(180F));
        stack.mulPose(Axis.YP.rotationDegrees(rotation));
        stack.translate(0, 0, -0.01);

        // Unbind RenderTarget
        Minecraft.getInstance().getMainRenderTarget().unbindWrite();
        RENDER_TARGET_HELPER.start();
        copyRenderTarget(Minecraft.getInstance().getMainRenderTarget(), RENDER_TARGET_HELPER.renderTarget);

        // Render Door Frame
        MultiBufferSource.BufferSource imBuffer = stencilBufferStorage.getVertexConsumer();
        currentModel.setDoorPosition(isOpen);
        currentModel.renderFrame(blockEntity, isOpen, true, stack, imBuffer.getBuffer(RenderType.entityTranslucent(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        imBuffer.endBatch();

        // Enable and configure stencil buffer
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glStencilMask(0xFF); // Ensure stencil mask is set before clearing
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT); // Clear stencil buffer
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);

        // Render portal mask with depth writing enabled
        RenderSystem.depthMask(true);
        stack.pushPose();
        currentModel.renderPortalMask(blockEntity, isOpen, true, stack, imBuffer.getBuffer(RenderType.entityTranslucent(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 0f, 0f, 0f, 1f);
        imBuffer.endBatch();
        stack.popPose();
        RenderSystem.depthMask(false); // Disable depth writing for subsequent rendering

        // Render vortex based on stencil buffer
        GL11.glStencilMask(0x00);
        GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
        GlStateManager._depthFunc(GL11.GL_ALWAYS); // Ignore depth buffer

        GL11.glColorMask(true, true, true, false);
        stack.pushPose();
        stack.scale(10, 10, 10);
        VORTEX.time.speed = (0.3f + tardisClientData.getThrottleStage() * 0.1f);
        VORTEX.renderVortex(stack, 1, false);
        stack.popPose();
        GlStateManager._depthFunc(GL11.GL_LEQUAL); // Restore depth function
        GL11.glColorMask(false, false, false, true);

        // Copy render target back to main buffer

        Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
        copyRenderTarget(RENDER_TARGET_HELPER.renderTarget, Minecraft.getInstance().getMainRenderTarget());
        GL11.glDisable(GL11.GL_STENCIL_TEST); // Disable stencil test
        GL11.glColorMask(true, true, true, true);

        stack.popPose();
    }

    private static void renderNoVortex(GlobalDoorBlockEntity blockEntity, PoseStack stack, MultiBufferSource bufferSource, int packedLight, float rotation, ShellDoorModel currentModel, boolean isOpen) {
        stack.pushPose();
        //Fix transform
        {
            stack.translate(0.5F, 1.5F, 0.5F);
            stack.mulPose(Axis.ZP.rotationDegrees(180F));
            stack.mulPose(Axis.YP.rotationDegrees(rotation));
            stack.translate(0, 0, -0.01);
        }

        currentModel.renderFrame(blockEntity, isOpen, true, stack, bufferSource.getBuffer(RenderType.entityTranslucent(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        currentModel.renderPortalMask(blockEntity, isOpen, true, stack, bufferSource.getBuffer(RenderType.entityTranslucent(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        stack.popPose();
    }


    public void start() {
        Window window = Minecraft.getInstance().getWindow();
        int width = window.getWidth();
        int height = window.getHeight();

        if (renderTarget == null || renderTarget.width != width || renderTarget.height != height)
            renderTarget = new TextureTarget(width, height, true, Minecraft.ON_OSX);

        renderTarget.bindWrite(false);
        renderTarget.checkStatus();
        if (!getIsStencilEnabled(renderTarget))
            setIsStencilEnabled(renderTarget, true);
    }


    public void end() {
        renderTarget.clear(Minecraft.ON_OSX);
        renderTarget.unbindWrite();
    }


    @Environment(EnvType.CLIENT)
    public static boolean getIsStencilEnabled(RenderTarget renderTarget) {
        return ((RenderTargetStencil) renderTarget).tr$getisStencilEnabled();
    }

    @Environment(EnvType.CLIENT)
    public static void setIsStencilEnabled(RenderTarget renderTarget, boolean cond) {
        ((RenderTargetStencil) renderTarget).tr$setisStencilEnabledAndReload(cond);
    }


    @Environment(value = EnvType.CLIENT)
    public static class StencilBufferStorage extends RenderBuffers {

        private final SortedMap<RenderType, BufferBuilder> typeBufferBuilder = Util.make(new Object2ObjectLinkedOpenHashMap(), map -> {
            put(map, getConsumer());
        });

        public static RenderType getConsumer() {
            RenderType.CompositeState parameters = RenderType.CompositeState.builder()
                    .setTextureState(BLOCK_SHEET_MIPPED)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setLayeringState(NO_LAYERING).createCompositeState(false);
            return RenderType.create("vortex", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
                    QUADS, 256, false, true, parameters);
        }

        private final MultiBufferSource.BufferSource consumer = MultiBufferSource.immediateWithBuffers(typeBufferBuilder, new BufferBuilder(256));

        private static void put(Object2ObjectLinkedOpenHashMap<RenderType, BufferBuilder> builderStorage, RenderType layer) {
            builderStorage.put(layer, new BufferBuilder(layer.bufferSize()));
        }

        public MultiBufferSource.BufferSource getVertexConsumer() {
            return this.consumer;
        }
    }
}
