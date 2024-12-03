package whocraft.tardis_refined.common.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "HEAD"), cancellable = true)
    public void renderHotbar(float f, GuiGraphics guiGraphics, CallbackInfo ci) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                ci.cancel();
            }
        });
    }

    @Inject(method = "renderCrosshair(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderCrosshair(GuiGraphics guiGraphics, CallbackInfo ci) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                ci.cancel();
            }
        });
    }

    @Inject(method = "renderSpyglassOverlay(Lnet/minecraft/client/gui/GuiGraphics;F)V", at = @At(value = "HEAD"), cancellable = true)
    public void renderSpyglassOverlay(GuiGraphics guiGraphics, float f, CallbackInfo ci) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                ci.cancel();
            }
        });
    }

    @Inject(method = "renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V", at = @At(value = "HEAD"), cancellable = true)
    public void renderExperienceBar(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                ci.cancel();
            }
        });
    }

    @Inject(method = "renderSpyglassOverlay(Lnet/minecraft/client/gui/GuiGraphics;F)V", at = @At(value = "HEAD"), cancellable = true)
    protected void renderTextureOverlay(GuiGraphics guiGraphics, float f, CallbackInfo ci) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                ci.cancel();
            }
        });
    }

    @Inject(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderPlayerHealth(GuiGraphics guiGraphics, CallbackInfo ci) {
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                ci.cancel();
            }
        });
    }
}
