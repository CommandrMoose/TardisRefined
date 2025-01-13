package whocraft.tardis_refined.mixin.render.buffer;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL30C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import whocraft.tardis_refined.client.TRShaders;
import whocraft.tardis_refined.client.renderer.vortex.RenderTargetStencil;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBDepthBufferFloat.GL_DEPTH32F_STENCIL8;
import static org.lwjgl.opengl.ARBDepthBufferFloat.GL_FLOAT_32_UNSIGNED_INT_24_8_REV;
import static org.lwjgl.opengl.GL11.GL_DEPTH_COMPONENT;
import static org.lwjgl.opengl.GL30.GL_DEPTH24_STENCIL8;

@Mixin(RenderTarget.class)
public abstract class MixinRenderTarget implements RenderTargetStencil {

    @Shadow
    public int width;
    @Shadow
    public int height;
    @Unique
    private boolean tardisRefined$isStencilEnabled;

    @Shadow
    public abstract void clear(boolean getError);

    @Shadow
    public abstract void resize(int width, int height, boolean clearError);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(
            boolean useDepth,
            CallbackInfo ci
    ) {
        tardisRefined$isStencilEnabled = false;
    }


    @Redirect(
            method = "createBuffers(IIZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/platform/GlStateManager;_texImage2D(IIIIIIIILjava/nio/IntBuffer;)V"
            )
    )
    private void redirectTexImage2d(
            int target, int level, int internalFormat,
            int width, int height,
            int border, int format, int type,
            IntBuffer pixels
    ) {
        if (internalFormat == GL_DEPTH_COMPONENT && tardisRefined$isStencilEnabled) {
            GlStateManager._texImage2D(
                    target,
                    level,
                    TRShaders.shouldUseCompatMode() ? GL_DEPTH32F_STENCIL8 : GL_DEPTH24_STENCIL8,
                    width,
                    height,
                    border,
                    ARBFramebufferObject.GL_DEPTH_STENCIL,
                    TRShaders.shouldUseCompatMode() ? GL_FLOAT_32_UNSIGNED_INT_24_8_REV : GL30.GL_UNSIGNED_INT_24_8,
                    pixels
            );
        } else {
            GlStateManager._texImage2D(
                    target, level, internalFormat, width, height,
                    border, format, type, pixels
            );
        }
    }

    @Redirect(
            method = "createBuffers(IIZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/platform/GlStateManager;_glFramebufferTexture2D(IIIII)V"
            )
    )
    private void redirectFrameBufferTexture2d(
            int target, int attachment, int textureTarget, int texture, int level
    ) {

        if (attachment == GL30C.GL_DEPTH_ATTACHMENT && tardisRefined$isStencilEnabled) {
            GlStateManager._glFramebufferTexture2D(
                    target, GL30.GL_DEPTH_STENCIL_ATTACHMENT, textureTarget, texture, level
            );
        } else {
            GlStateManager._glFramebufferTexture2D(target, attachment, textureTarget, texture, level);
        }
    }

    @Override
    public boolean tr$getisStencilEnabled() {
        return tardisRefined$isStencilEnabled;
    }

    @Override
    public void tr$setisStencilEnabledAndReload(boolean cond) {
        if (tardisRefined$isStencilEnabled != cond) {
            tardisRefined$isStencilEnabled = cond;
            resize(width, height, Minecraft.ON_OSX);
        }
    }
}