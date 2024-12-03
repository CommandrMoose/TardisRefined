package whocraft.tardis_refined.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static whocraft.tardis_refined.common.GravityUtil.easeMovement;

public class GravityClient {

    public static void moveGravity(Player player, CallbackInfo info) {
        Vec3 deltaMovement = player.getDeltaMovement();
        Minecraft minecraft = Minecraft.getInstance();
        Options options = minecraft.options;

        if (GravityUtil.isInGravityShaft(player)) {
            player.setOnGround(true); //Required to stop player attempting to open Elytra. Side effect: Hunger and other behaviours that occur when player is touching the ground will apply.
            player.stopFallFlying();
            player.resetFallDistance();
            player.setNoGravity(true);
            player.setPose(Pose.STANDING);

            if (options.keyJump.isDown()) {
                player.setDeltaMovement(deltaMovement.add(0, easeMovement(), 0));
                info.cancel();
            } else if (options.keyShift.isDown()) {
                player.setPose(Pose.STANDING);
                player.setDeltaMovement(deltaMovement.add(0, -(easeMovement() * 5), 0));
                info.cancel();
            } else {
                player.setDeltaMovement(deltaMovement.x, 0, deltaMovement.z);
            }
        } else {
            player.setNoGravity(false);
        }
    }


}
