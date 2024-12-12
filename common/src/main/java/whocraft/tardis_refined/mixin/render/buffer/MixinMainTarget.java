package whocraft.tardis_refined.mixin.render.buffer;

import com.mojang.blaze3d.pipeline.MainTarget;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL30C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import whocraft.tardis_refined.client.renderer.vortex.RenderTargetStencil;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_DEPTH_COMPONENT;
import static org.lwjgl.opengl.GL30.GL_DEPTH32F_STENCIL8;
import static org.lwjgl.opengl.GL30.GL_FLOAT_32_UNSIGNED_INT_24_8_REV;


@Mixin(MainTarget.class)
public abstract class MixinMainTarget extends RenderTarget {

    public MixinMainTarget(boolean useDepth) {
        super(useDepth);
        throw new RuntimeException();
    }

    @Redirect(
            method = "allocateDepthAttachment(Lcom/mojang/blaze3d/pipeline/MainTarget$Dimension;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/platform/GlStateManager;_texImage2D(IIIIIIIILjava/nio/IntBuffer;)V"
            )
    )
    private void onTexImage2D(
            int target, int level, int internalFormat,
            int width, int height, int border, int format, int type, IntBuffer pixels
    ) {
        boolean isStencilBufferEnabled = ((RenderTargetStencil) this).tr$getisStencilEnabled();

        if (internalFormat == GL_DEPTH_COMPONENT && isStencilBufferEnabled) {
            GlStateManager._texImage2D(
                    target,
                    level,
                    GL_DEPTH32F_STENCIL8,
                    width,
                    height,
                    border,
                    ARBFramebufferObject.GL_DEPTH_STENCIL,
                    GL_FLOAT_32_UNSIGNED_INT_24_8_REV,
                    pixels
            );
        }
        else {
            GlStateManager._texImage2D(
                    target, level, internalFormat, width, height,
                    border, format, type, pixels
            );
        }
    }

    @Redirect(
            method = "createFrameBuffer(II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/platform/GlStateManager;_glFramebufferTexture2D(IIIII)V"
            )
    )
    private void redirectFrameBufferTexture2d(
            int target, int attachment, int textureTarget, int texture, int level
    ) {
        boolean isStencilBufferEnabled = ((RenderTargetStencil) this).tr$getisStencilEnabled();

        if (attachment == GL30C.GL_DEPTH_ATTACHMENT && isStencilBufferEnabled) {
            GlStateManager._glFramebufferTexture2D(
                    target, GL30.GL_DEPTH_STENCIL_ATTACHMENT, textureTarget, texture, level
            );
        }
        else {
            GlStateManager._glFramebufferTexture2D(target, attachment, textureTarget, texture, level);
        }
    }


}