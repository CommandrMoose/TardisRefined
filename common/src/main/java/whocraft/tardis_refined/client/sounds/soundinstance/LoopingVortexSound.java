package whocraft.tardis_refined.client.sounds.soundinstance;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

public class LoopingVortexSound extends LoopingFlightSound {


    public LoopingVortexSound(SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource);
    }

    @Override
    public boolean canPlaySound() {
        return true;
    }

    @Override
    public void playSoundInstance(Player player) {
        TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> {
            TardisClientData tardisClientData = TardisClientData.getInstance(tardisPlayerInfo.getPlayerPreviousPos().getDimensionKey());
            if (tardisPlayerInfo.isRenderVortex() && tardisPlayerInfo.isViewingTardis() && !tardisClientData.isLanding() && !tardisClientData.isTakingOff()) {
                Vec3 facingDirection = player.getLookAngle();
                Vec3 newPosition = player.position().add(facingDirection.scale(3));
                setLocation(newPosition);
                this.setVolume(0.5F);
            } else {
                setLocation(player.position().add(0, 0, 0));
                setVolume(0);
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
    }
}
