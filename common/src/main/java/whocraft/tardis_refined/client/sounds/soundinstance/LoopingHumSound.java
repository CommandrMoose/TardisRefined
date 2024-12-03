package whocraft.tardis_refined.client.sounds.soundinstance;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.sounds.LoopingTardisInteriorSound;
import whocraft.tardis_refined.constants.NbtConstants;

public class LoopingHumSound extends LoopingTardisInteriorSound {

    public LoopingHumSound(@NotNull SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource);
    }

    @Override
    public void playSoundInstance(Player player) {
        setLocation(player.position());
        if (soundEvent.getLocation().getNamespace().contains(NbtConstants.MINECRAFT)) {
            this.volume = 1f;
        } else {
            this.setVolume(0.10F);
        }
    }

    // So, I don't like this. But it is necessary else we are making another Mixin, which isn't a bad thing but better to avoid
    // You need to call setSoundEvent and then call resolve, it's...yeah ok there's no excusing this
    @Override
    public WeighedSoundEvents resolve(SoundManager soundManager) {
        if (this.location.equals(SoundManager.INTENTIONALLY_EMPTY_SOUND_LOCATION)) {
            this.sound = SoundManager.INTENTIONALLY_EMPTY_SOUND;
            return SoundManager.INTENTIONALLY_EMPTY_SOUND_EVENT;
        } else {
            WeighedSoundEvents weighedSoundEvents = soundManager.getSoundEvent(soundEvent.getLocation());
            if (weighedSoundEvents == null) {
                this.sound = SoundManager.EMPTY_SOUND;
            } else {
                this.sound = weighedSoundEvents.getSound(this.random);
            }

            return weighedSoundEvents;
        }
    }
}
