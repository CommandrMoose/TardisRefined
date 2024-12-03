package whocraft.tardis_refined.client.sounds.soundinstance;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.sounds.LoopingSoundGeneric;
import whocraft.tardis_refined.common.GravityUtil;

public class LoopingGravBlockSound extends LoopingSoundGeneric {
    public LoopingGravBlockSound(@NotNull SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource);
    }

    @Override
    public void playSoundInstance(Player player) {
        BlockPos position = player.blockPosition();
        Vec3 playerVec = position.getCenter();
        this.setLocation(playerVec);
        this.setVolume(0.5F);
    }

    @Override
    public boolean canPlaySound() {
        if (this.player != null) {
            return GravityUtil.isInGravityShaft(this.player);
        }
        return false;
    }
}
