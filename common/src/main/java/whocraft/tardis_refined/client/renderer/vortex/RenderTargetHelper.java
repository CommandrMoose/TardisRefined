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
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.door.interior.ShellDoorModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.block.door.GlobalDoorBlock;
import whocraft.tardis_refined.common.block.door.InternalDoorBlock;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortalsClient;

import static com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS;
import static net.minecraft.client.renderer.RenderStateShard.*;
import static whocraft.tardis_refined.client.overlays.VortexOverlay.VORTEX;

public class RenderTargetHelper {

    private static final RenderTargetHelper RENDER_TARGET_HELPER = new RenderTargetHelper();
    public static StencilBufferStorage stencilBufferStorage = new StencilBufferStorage();
    public RenderTarget renderTarget;


    public static Logger LOGGER = LogManager.getLogger("TardisRefinbed/StencilRendering");

    public static void renderVortex(GlobalDoorBlockEntity blockEntity, float partialTick, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        ResourceLocation theme = blockEntity.theme();
        float rotation = blockstate.getValue(InternalDoorBlock.FACING).toYRot();
        boolean isOpen = blockstate.getValue(InternalDoorBlock.OPEN);
        ShellDoorModel currentModel = ShellModelCollection.getInstance().getShellEntry(theme).getShellDoorModel(blockEntity.pattern());

        if(currentModel == null) return;

        TardisClientData tardisClientData = TardisClientData.getInstance(blockEntity.getLevel().dimension());

        VORTEX.vortexType = VortexRegistry.VORTEX_DEFERRED_REGISTRY.get(tardisClientData.getVortex());

        if (blockstate.hasProperty(GlobalDoorBlock.OFFSET) && blockstate.getValue(GlobalDoorBlock.OFFSET)) {
            Direction facing = blockstate.getValue(InternalDoorBlock.FACING);
            double xOffset = 0.0;
            double zOffset = 0.0;

            switch (facing) {
                case NORTH -> xOffset = -0.5;
                case SOUTH -> xOffset = 0.5;
                case EAST -> zOffset = -0.5;
                case WEST -> zOffset = 0.5;
            }

            stack.translate(xOffset, 0, zOffset);
        }

        if (tardisClientData.isFlying() && TRConfig.CLIENT.RENDER_VORTEX_IN_DOOR.get()) {
            renderDoorOpen(blockEntity, stack, bufferSource, packedLight, rotation, currentModel, isOpen, tardisClientData);
        } else {
            renderNoVortex(blockEntity, stack, bufferSource, packedLight, rotation, currentModel, isOpen);
        }
    }


    private static ResourceLocation BLACK = new ResourceLocation(TardisRefined.MODID, "textures/black_portal.png");

    private static void renderDoorOpen(GlobalDoorBlockEntity blockEntity, PoseStack stack, MultiBufferSource bufferSource, int packedLight, float rotation, ShellDoorModel currentModel, boolean isOpen, TardisClientData tardisClientData) {
        if (ModCompatChecker.immersivePortals()) {
            if (ImmersivePortalsClient.shouldStopRenderingInPortal()) {
                return;
            }
        }


        if(!getIsStencilEnabled(Minecraft.getInstance().getMainRenderTarget())){
            setIsStencilEnabled(Minecraft.getInstance().getMainRenderTarget(), true);
        }

        stack.pushPose();

        // Fix transform
        stack.translate(0.5F, 1.5F, 0.5F);
        stack.mulPose(Axis.ZP.rotationDegrees(180F));
        stack.mulPose(Axis.YP.rotationDegrees(rotation));
        stack.translate(0, 0, -0.01);

        RenderSystem.depthMask(true);

        // Render Door Frame
        MultiBufferSource.BufferSource imBuffer = stencilBufferStorage.getVertexConsumer();
        currentModel.setDoorPosition(isOpen);
        currentModel.renderFrame(blockEntity, isOpen, true, stack, imBuffer.getBuffer(RenderType.entityCutout(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        // Finalize the current batch before changing rendering state
        imBuffer.endBatch();

        // Enable and configure stencil buffer
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glStencilMask(0xFF);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);

        // Render portal mask with depth writing enabled
        RenderSystem.depthMask(true);
        stack.pushPose();
        currentModel.renderPortalMask(blockEntity, isOpen, true, stack, imBuffer.getBuffer(RenderType.entityTranslucentCull(BLACK)), packedLight, OverlayTexture.NO_OVERLAY, 0f, 0f, 0f, 1f);
        imBuffer.endBatch();
        stack.popPose();
        RenderSystem.depthMask(false);

        // Render vortex using stencil buffer
        GL11.glStencilMask(0x00);
        GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
        GlStateManager._depthFunc(GL11.GL_ALWAYS);

        GL11.glColorMask(true, true, true, false);
        stack.pushPose();
        stack.scale(10, 10, 10);

        VORTEX.time.speed = (0.3f + tardisClientData.getThrottleStage() * 0.1f);
        VORTEX.renderVortex(stack, 1, false);
        stack.popPose();

        GlStateManager._depthFunc(GL11.GL_LEQUAL);
        GL11.glColorMask(true, true, true, true);

        // Disable stencil test and restore state
        GL11.glDisable(GL11.GL_STENCIL_TEST);
        GL11.glStencilMask(0xFF);
        RenderSystem.depthMask(true);


        stack.popPose();
    }



    public static void checkGLError(String msg) {
        int error;
        while ((error = GL11.glGetError()) != GL11.GL_NO_ERROR) {
            LOGGER.debug("{}: {}", msg, error);
        }

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
        currentModel.setDoorPosition(isOpen);
        currentModel.renderFrame(blockEntity, isOpen, true, stack, bufferSource.getBuffer(RenderType.entityTranslucent(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        currentModel.renderPortalMask(blockEntity, isOpen, true, stack, bufferSource.getBuffer(RenderType.entityTranslucent(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        stack.popPose();
    }

    @Environment(EnvType.CLIENT)
    public static boolean getIsStencilEnabled(RenderTarget renderTarget) {
        return ((RenderTargetStencil) renderTarget).tr$getisStencilEnabled();
    }

    @Environment(EnvType.CLIENT)
    public static void setIsStencilEnabled(RenderTarget renderTarget, boolean cond) {
        ((RenderTargetStencil) renderTarget).tr$setisStencilEnabledAndReload(cond);
    }

    public void start() {

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_DEBUG, GLFW.GLFW_TRUE);


        Window window = Minecraft.getInstance().getWindow();
        int width = window.getWidth();
        int height = window.getHeight();

        // Check if renderTarget needs to be reinitialized
        if (renderTarget == null || renderTarget.width != width || renderTarget.height != height) {
            renderTarget = new TextureTarget(width, height, true, Minecraft.ON_OSX);
        }

        renderTarget.bindWrite(false);
        renderTarget.checkStatus();

        if (!getIsStencilEnabled(renderTarget)) {
            setIsStencilEnabled(renderTarget, true);
        }
    }


    public void end(boolean clear) {
        renderTarget.clear(clear);
        renderTarget.unbindWrite();
    }

    @Environment(value = EnvType.CLIENT)
    public static class StencilBufferStorage extends RenderBuffers {

        private static final TextureStateShard BLOCK_SHEET_MIPPED_BUTMINE = new TextureStateShard(TextureAtlas.LOCATION_BLOCKS, false, true);


        private final Object2ObjectLinkedOpenHashMap typeBufferBuilder = Util.make(new Object2ObjectLinkedOpenHashMap(), map -> {
            put(map, getConsumer());
        });
        private final MultiBufferSource.BufferSource consumer = MultiBufferSource.immediateWithBuffers(typeBufferBuilder, new BufferBuilder(256));

        public static RenderType getConsumer() {
            RenderType.CompositeState parameters = RenderType.CompositeState.builder()
                    .setTextureState(BLOCK_SHEET_MIPPED_BUTMINE)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setLayeringState(NO_LAYERING).createCompositeState(false);
            return RenderType.create("vortex", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
                    QUADS, 256, false, true, parameters);
        }

        private static void put(Object2ObjectLinkedOpenHashMap<RenderType, BufferBuilder> builderStorage, RenderType layer) {
            builderStorage.put(layer, new BufferBuilder(layer.bufferSize()));
        }

        public MultiBufferSource.BufferSource getVertexConsumer() {
            return this.consumer;
        }
    }
}
