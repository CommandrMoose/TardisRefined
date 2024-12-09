package whocraft.tardis_refined.mixin.render.buffer;

import com.mojang.blaze3d.pipeline.MainTarget;
import com.mojang.blaze3d.pipeline.RenderTarget;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import whocraft.tardis_refined.client.renderer.vortex.RenderTargetStencil;

import static org.lwjgl.opengl.GL30.GL_DEPTH32F_STENCIL8;
import static org.lwjgl.opengl.GL30.GL_FLOAT_32_UNSIGNED_INT_24_8_REV;


@Mixin(MainTarget.class)
public abstract class MixinMainTarget extends RenderTarget {

    public MixinMainTarget(boolean useDepth) {
        super(useDepth);
        throw new RuntimeException();
    }

    @ModifyArgs(method = "allocateDepthAttachment(Lcom/mojang/blaze3d/pipeline/MainTarget$Dimension;)Z", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;_texImage2D(IIIIIIIILjava/nio/IntBuffer;)V", remap = false))
    private void tardisRefined$modifyTexImage2D(Args args) {
        boolean isStencilEnabled = ((RenderTargetStencil) this).tr$getisStencilEnabled();

        if (isStencilEnabled) {
            args.set(2, GL_DEPTH32F_STENCIL8);
            args.set(6, ARBFramebufferObject.GL_DEPTH_STENCIL);
            args.set(7, GL_FLOAT_32_UNSIGNED_INT_24_8_REV);
        }
    }

    @ModifyArgs(method = "createFrameBuffer(II)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;_glFramebufferTexture2D(IIIII)V", remap = false))
    private void tardisRefined$modifyFrameBufferTexture2d(Args args) {
        boolean isStencilEnabled = ((RenderTargetStencil) this).tr$getisStencilEnabled();

        if (isStencilEnabled) {
            if ((int) args.get(1) == GL30.GL_DEPTH_ATTACHMENT) {
                args.set(1, GL30.GL_DEPTH_STENCIL_ATTACHMENT);
            }
        }
    }

}