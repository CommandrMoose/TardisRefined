package whocraft.tardis_refined.patterns.sound;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

/**
 * An abstraction over the SoundEvent object which allows datapacks to define and intended pitch and volume to play.
 **/
public class ConfiguredSound {

    public static final Codec<ConfiguredSound> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                SoundEvent.DIRECT_CODEC.fieldOf("sound_event").forGetter(ConfiguredSound::getSoundEvent),
                Codec.FLOAT.fieldOf("pitch").forGetter(ConfiguredSound::getPitch),
                Codec.FLOAT.fieldOf("volume").forGetter(ConfiguredSound::getVolume)
        ).apply(instance, ConfiguredSound::new);
    });

    private final SoundEvent soundEvent;
    private final float pitch;

    private final float volume;


    /**
     * Constructor for data driven entries
     */
    public ConfiguredSound(SoundEvent soundEvent, float pitch, float volume) {
        this.soundEvent = soundEvent;
        this.pitch = pitch;
        this.volume = volume;
    }

    public ConfiguredSound(SoundEvent soundEvent, float pitch) {
        this(soundEvent, pitch, 1F);
    }

    public ConfiguredSound(SoundEvent event) {
        this(event, 1F, 1F);
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public ResourceLocation getSoundEventKey() {
        return this.soundEvent.getLocation();
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return this.volume;
    }
}
