package whocraft.tardis_refined.client.sounds;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import whocraft.tardis_refined.client.sounds.soundinstance.*;
import whocraft.tardis_refined.registry.TRSoundRegistry;

public class TRSoundInstances {
    public static LoopingArsAreaSound ARS_HUMMING = new LoopingArsAreaSound(TRSoundRegistry.ARS_HUM.get(), SoundSource.AMBIENT);
    public static LoopingGravBlockSound GRAVITY_LOOP = new LoopingGravBlockSound(TRSoundRegistry.GRAVITY_TUNNEL.get(), SoundSource.AMBIENT);

    public static LoopingFlightSound TARDIS_SINGLE_FLY = new LoopingFlightSound(TRSoundRegistry.TARDIS_SINGLE_FLY.get(), SoundSource.AMBIENT);
    public static LoopingVortexFlight TARDIS_SINGLE_FLY_VORTEX = new LoopingVortexFlight(TRSoundRegistry.TARDIS_SINGLE_FLY.get(), SoundSource.AMBIENT);

    public static QuickSimpleSound INTERIOR_VOICE = new QuickSimpleSound(TRSoundRegistry.INTERIOR_VOICE.get(), SoundSource.AMBIENT);
    public static LoopingVortexSound VORTEX_WINDS = new LoopingVortexSound(SoundEvents.ELYTRA_FLYING, SoundSource.AMBIENT);

    public static boolean shouldMinecraftMusicStop(SoundManager soundManager) {
        return soundManager.isActive(VORTEX_WINDS) || soundManager.isActive(TARDIS_SINGLE_FLY) || soundManager.isActive(ARS_HUMMING) || soundManager.isActive(HumSoundManager.getCurrentHumSound());
    }

}
