package whocraft.tardis_refined.client.renderer.blockentity.door;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.opengl.GL11;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.model.blockentity.door.interior.ShellDoorModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.client.renderer.vortex.VortexRenderer;
import whocraft.tardis_refined.common.block.door.InternalDoorBlock;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortalsClient;

public class GlobalDoorRenderer implements BlockEntityRenderer<GlobalDoorBlockEntity>, BlockEntityRendererProvider<GlobalDoorBlockEntity> {


    protected static ShellDoorModel currentModel;

    public GlobalDoorRenderer(BlockEntityRendererProvider.Context context) {

    }

    //ResourceLocation testTex = new ResourceLocation(TardisRefined.MODID, "textures/test_texture.png");

    @Override
    public void render(GlobalDoorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        if (ModCompatChecker.immersivePortals()) {
            if (ImmersivePortalsClient.shouldStopRenderingInPortal()) {
                return;
            }
        }

        /*
        Tesselator tess = RenderHelper.beginTextureColor(testTex, VertexFormat.Mode.QUADS, false);

        //Stencil
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        RenderSystem.stencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        RenderSystem.stencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_INCR);
        RenderSystem.stencilMask(0xFF);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);

        RenderSystem.depthMask(false);

        //Portal mesh
        RenderHelper.vertexUVColor(poseStack, 0, 0, 0, 0, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 0, 0, 1, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 1, 0, 1, 1, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 0, 1, 0, 0, 1, 1, 1, 1, 1);

        RenderSystem.depthMask(true);

        //Close Stencil
        RenderSystem.stencilMask(0x00);
        RenderSystem.stencilFunc(GL11.GL_EQUAL, 1, 0xFF);

        RenderSystem.depthFunc(GL11.GL_ALWAYS);
        RenderSystem.setShaderColor(0, 0, 0, 1);

        //originally this was done in GL11 calls and my guess is to black out the background inside the portal
        //PLACEHOLDER
        RenderHelper.vertexUVColor(poseStack, 0, 0, 0, 0, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 0, 0, 1, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 1, 0, 1, 1, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 0, 1, 0, 0, 1, 1, 1, 1, 1);

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        RenderSystem.colorMask(true, true, true, true);

        //Inside Portal
        RenderHelper.vertexUVColor(poseStack, 0, 0, 1, 0, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 0, 1, 1, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 0, 1, 1, 0, 1, 1, 1, 1, 1);

        //Pre Overlay
        RenderSystem.colorMask(false, false, false, false);

        RenderHelper.vertexUVColor(poseStack, 0, 0, 0, 0, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 0, 0, 1, 0, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 1, 1, 0, 1, 1, 1, 1, 1, 1);
        RenderHelper.vertexUVColor(poseStack, 0, 1, 0, 0, 1, 1, 1, 1, 1);

        //Close Portal
        RenderSystem.depthMask(true);
        RenderSystem.colorMask(true, true, true, true);

        GL11.glDisable(GL11.GL_STENCIL_TEST);

        tess.end();
        */


        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
        BlockState blockstate = blockEntity.getBlockState();
        float rotation = blockstate.getValue(InternalDoorBlock.FACING).toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        ResourceLocation theme = blockEntity.theme();
        boolean isOpen = blockstate.getValue(InternalDoorBlock.OPEN);

        // Render slightly off the wall to prevent z-fighting.
        poseStack.translate(0, 0, -0.01);
        currentModel = ShellModelCollection.getInstance().getShellEntry(theme).getShellDoorModel(blockEntity.pattern());

        currentModel.setDoorPosition(isOpen);
        currentModel.renderInteriorDoor(blockEntity, isOpen, true, poseStack, bufferSource.getBuffer(RenderType.entityTranslucentCull(currentModel.getInteriorDoorTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(GlobalDoorBlockEntity blockEntity) {
        return true;
    }

    @Override
    public BlockEntityRenderer<GlobalDoorBlockEntity> create(Context context) {
        return new GlobalDoorRenderer(context);
    }
}
