package whocraft.tardis_refined.client.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;

/**
 * Generic implementation of LoopingSound that isn't specific to a Tardis dimension and based on the player's level
 */
public abstract class LoopingSoundGeneric extends LoopingSound {
    public LoopingSoundGeneric(@NotNull SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource);
    }

    @Override
    public void tick() {

        if (this.player == null) {
            TardisRefined.LOGGER.warn("Cannot play Looping Sound. No target player defined by LoopingSound instance: " + this.toString());
            this.setVolume(0f);
            this.stop();
        } else {
            if (this.canPlaySound()) {
                this.playSoundInstance(this.player);
            }
        }
    }
}
