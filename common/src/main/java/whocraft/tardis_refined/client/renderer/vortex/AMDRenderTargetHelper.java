package whocraft.tardis_refined.client.renderer.vortex;

import com.mojang.blaze3d.pipeline.RenderTarget;
import org.lwjgl.opengl.GL43C;

public class AMDRenderTargetHelper {

    public static void newCopyDepthStencil(
            RenderTarget from, RenderTarget to
    ) {
        GL43C.glCopyImageSubData(
                from.getDepthTextureId(),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                to.getDepthTextureId(),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                from.width,
                from.height,
                1
        );
    }

    public static void copyColor(
            RenderTarget from, RenderTarget to
    ) {
        GL43C.glCopyImageSubData(
                from.getColorTextureId(),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                to.getColorTextureId(),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                from.width,
                from.height,
                1
        );
    }

}