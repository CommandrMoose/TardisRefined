package whocraft.tardis_refined.common.mixin;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import whocraft.tardis_refined.client.TRKeybinds;
import whocraft.tardis_refined.client.overlays.ExteriorViewOverlay;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.network.messages.player.ExitTardisViewMessage;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Shadow
    public Input input;

    @Unique
    private long lastToggleInfoTime = 0;

    @Inject(method = "aiStep()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getTutorial()Lnet/minecraft/client/tutorial/Tutorial;"))
    private void inputEdit(CallbackInfo ci) {
        LocalPlayer localPlayer = (LocalPlayer) (Object) this;
        handleInput(localPlayer, input);
    }

    private void handleInput(LocalPlayer localPlayer, Input input) {
        TardisPlayerInfo.get(localPlayer).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                blockMovement(input);
            }
        });
    }

    private void blockMovement(Input moveType) {
        // Set all movement-related fields to false or 0.0F to block movement

        if (TRKeybinds.EXIT_EXTERIOR_VIEW.isDown()) {
            new ExitTardisViewMessage().send();
            return;
        }

        if (TRKeybinds.TOGGLE_INFO_EXTERIOR_VIEW.isDown()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastToggleInfoTime >= 500) {
                ExteriorViewOverlay.shouldRender = !ExteriorViewOverlay.shouldRender;
                lastToggleInfoTime = currentTime;
            }
        }

        moveType.right = false;
        moveType.left = false;
        moveType.down = false;
        moveType.jumping = false;
        moveType.forwardImpulse = 0.0F;
        moveType.shiftKeyDown = false;
        moveType.leftImpulse = 0.0F;
    }
}
