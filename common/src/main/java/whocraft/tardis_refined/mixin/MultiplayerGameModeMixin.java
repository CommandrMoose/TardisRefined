package whocraft.tardis_refined.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import whocraft.tardis_refined.common.util.MiscHelper;

@Mixin(MultiPlayerGameMode.class)
public class MultiplayerGameModeMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    public void tardisRefined$destroyBlock(BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        ClientLevel level = this.minecraft.level;
        if (level == null) return;

        if (MiscHelper.shouldCancelBreaking(level, null, blockPos, level.getBlockState(blockPos))) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
