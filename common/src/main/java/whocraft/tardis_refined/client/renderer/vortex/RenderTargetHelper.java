package whocraft.tardis_refined.client.renderer.vortex;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
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
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.opengl.GL11;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.door.interior.ShellDoorModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.client.overlays.VortexOverlay;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.block.door.InternalDoorBlock;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

import java.util.SortedMap;

import static com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS;
import static net.minecraft.client.renderer.RenderStateShard.*;
import static whocraft.tardis_refined.client.renderer.blockentity.door.GlobalDoorRenderer.renderDoor;

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

        VortexOverlay.VORTEX.vortexType = VortexRegistry.VORTEX_DEFERRED_REGISTRY.get(tardisClientData.getVortex());
        stack.pushPose();
        //Fix transform
        {
            stack.translate(0.5F, 1.5F, 0.5F);
            stack.mulPose(Axis.ZP.rotationDegrees(180F));
            stack.mulPose(Axis.YP.rotationDegrees(rotation));
            stack.translate(0, 0, -0.01);
        }
        //Unbind RenderTarget
        Minecraft.getInstance().getMainRenderTarget().unbindWrite();
        RENDER_TARGET_HELPER.start();

        //Render Door Frame
        MultiBufferSource.BufferSource imBuffer = stencilBufferStorage.getVertexConsumer();
        currentModel.setDoorPosition(isOpen);
        currentModel.renderFrame(blockEntity, isOpen, true, stack, imBuffer.getBuffer(RenderType.entityCutout(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        imBuffer.endBatch();

        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
        GL11.glStencilMask(0xFF);

        RenderSystem.depthMask(false);
        stack.pushPose();
        currentModel.renderPortalMask(blockEntity, isOpen, true, stack, imBuffer.getBuffer(RenderType.entityTranslucentCull(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 0f, 0f, 0f, 1f);
        imBuffer.endBatch();
        stack.popPose();
        RenderSystem.depthMask(true);

        GL11.glStencilMask(0x00);
        GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);

        GL11.glColorMask(true, true, true, false);
        stack.pushPose();
        stack.scale(10, 10, 10);
        VortexOverlay.VORTEX.time.speed = 0.3;
        VortexOverlay.VORTEX.renderVortex(stack, 1, false);
        stack.popPose();
        GL11.glColorMask(false, false, false, true);

        Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
        //Put rendertarget on screen;
        {
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RENDER_TARGET_HELPER.renderTarget.blitToScreen(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight(), false);
            RENDER_TARGET_HELPER.end();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
        }
        stack.popPose();
        GL11.glDisable(GL11.GL_STENCIL_TEST);
        //Minecraft.getInstance().getMainRenderTarget().bindWrite(false);

        GL11.glColorMask(true, true, true, true);
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
