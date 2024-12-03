package whocraft.tardis_refined.client.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class QuickSimpleSound extends AbstractTickableSoundInstance {

    public QuickSimpleSound(@NotNull SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource, SoundInstance.createUnseededRandom());
        attenuation = Attenuation.NONE;
        looping = false;
        delay = 0;
        volume = 0.5f;
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
        x = location.x;
        y = location.y;
        z = location.z;
    }

    public void stopSound() {
        Minecraft.getInstance()
                .getSoundManager()
                .stop(this);
    }

    @Override
    public Sound getSound() {
        return super.getSound();
    }


    @Override
    public void tick() {

    }
}
