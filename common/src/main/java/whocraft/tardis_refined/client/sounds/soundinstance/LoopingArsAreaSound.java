package whocraft.tardis_refined.client.sounds.soundinstance;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.client.sounds.LoopingTardisInteriorSound;
import whocraft.tardis_refined.common.util.TardisHelper;

public class LoopingArsAreaSound extends LoopingTardisInteriorSound {

    public LoopingArsAreaSound(SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource);
    }

    @Override
    public void playSoundInstance(Player player) {
        BlockPos position = player.blockPosition();
        Vec3 playerVec = position.getCenter();
        this.setLocation(playerVec);
        double distance = playerVec.distanceTo(new Vec3(x, y, z));
        double maxDistance = 11.0;
        double fadeFactor = Math.max(1.0 - distance / maxDistance, 0.0);
        float defaultVolume = 1.0f;
        this.setVolume((float) (fadeFactor * defaultVolume));
    }

    @Override
    public boolean canPlaySound() {
        if (this.player != null) {
            return super.canPlaySound() && TardisHelper.isInArsArea(player.blockPosition());
        }
        return false;
    }

    @Override
    public boolean requiresClientData() {
        return false;
    }
}
