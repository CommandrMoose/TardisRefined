package whocraft.tardis_refined.client.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Object to define a SoundEvent that can be looped. We can define how and when it should be played
 */
public abstract class LoopingSound extends AbstractTickableSoundInstance {

    protected SoundEvent soundEvent;
    protected Player player;
    protected Level level;
    protected float defaultVolume = 0.5F;

    public LoopingSound(SoundEvent soundEvent, SoundSource soundSource, Attenuation attenuation) {
        super(soundEvent, soundSource, SoundInstance.createUnseededRandom());
        this.soundEvent = soundEvent;
        this.attenuation = attenuation;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.5f;
    }

    public LoopingSound(SoundEvent soundEvent, SoundSource soundSource) {
        this(soundEvent, soundSource, Attenuation.NONE);
    }

    @Override
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setLocation(Vec3 location) {
        this.x = location.x;
        this.y = location.y;
        this.z = location.z;
    }

    /**
     * Gets the player which will be hearing the sound. Doign this saves us having to create multiple instances of the target player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player which will be hearing the sound. Doign this saves us having to create multiple instances of the target player
     */
    public LoopingSound setPlayer(Player player) {
        this.player = player;
        return this;
    }

    /**
     * Gets the desired level to play this sound in. By default, it will pick the player's level.
     * <br> Can be useful for sounds that need to be played in another dimension which the player is not currently located in
     */
    public Level getLevel() {
        if (this.level == null && this.player != null) {
            return this.player.level();
        }
        return this.level;
    }

    /**
     * Sets the desired level to play this sound in. By default, it will pick the player's level.
     * <br> Can be useful for sounds that need to be played in another dimension which the player is not currently located in
     */
    public LoopingSound setLevel(Level targetLevel) {
        this.level = targetLevel;
        return this;
    }

    public void stopSound() {
        Minecraft.getInstance().getSoundManager().stop(this);
    }

    @Override
    public Sound getSound() {
        return super.getSound();
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    /**
     * Add logic here to determine how the sound should be played given it is able to play.
     * <br> Define logic such as volume, attenuation, delays etc.
     */
    public void playSoundInstance(Player player) {

    }

    /**
     * Gets the default volume that will be used if the sound needs to be replayed after having its volume set to zero in a previous tick
     * <br> If volume is zero, the sound won't be played again, so it must be set to a value larger than zero to be 'restarted'
     */
    public float getDefaultVolume() {
        return this.defaultVolume;
    }

    public LoopingSound setDefaultVolume(float defaultVolume) {
        this.defaultVolume = defaultVolume;
        return this;
    }

    /**
     * Helper method to restart a sound if it was at volume of zero before.
     * It sets the volume to a value (should be higher than zero) using the default volume via {@link LoopingSound#getDefaultVolume()}
     *
     * @return
     */
    public LoopingSound restartSoundPlaying() {
        if (this.getVolume() <= 0) {
            if (this.getDefaultVolume() > 0)
                this.setVolume(this.getDefaultVolume());
            else
                this.setVolume(0.5F);
        }
        return this;
    }

}
