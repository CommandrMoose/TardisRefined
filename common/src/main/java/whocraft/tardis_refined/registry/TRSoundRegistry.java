package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import whocraft.tardis_refined.TardisRefined;

public class TRSoundRegistry {
    public static final DeferredRegistry<SoundEvent> SOUNDS = DeferredRegistry.create(TardisRefined.MODID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> TARDIS_TAKEOFF = setUpSound("tardis_takeoff");
    public static final RegistrySupplier<SoundEvent> TARDIS_LAND = setUpSound("tardis_land");
    public static final RegistrySupplier<SoundEvent> TARDIS_SINGLE_FLY = setUpSound("tardis_single_fly");
    public static final RegistrySupplier<SoundEvent> TIME_BLAST = setUpSound("time_blast");
    public static final RegistrySupplier<SoundEvent> TARDIS_CRASH_LAND = setUpSound("tardis_crash_land");
    public static final RegistrySupplier<SoundEvent> PATTERN_MANIPULATOR = setUpSound("pattern_manipulator");
    public static final RegistrySupplier<SoundEvent> TARDIS_MISC_SPARKLE = setUpSound("tardis_misc_sparkle");
    public static final RegistrySupplier<SoundEvent> STATIC = setUpSound("static");
    public static final RegistrySupplier<SoundEvent> DESTINATION_DING = setUpSound("destination_ding");
    public static final RegistrySupplier<SoundEvent> ARS_HUM = setUpSound("ars_hum");
    public static final RegistrySupplier<SoundEvent> BULKHEAD_LOCKED = setUpSound("bulkhead_locked");

    public static final RegistrySupplier<SoundEvent> INTERIOR_CREAKS = setUpSound("interior_creaks");
    public static final RegistrySupplier<SoundEvent> FLIGHT_FAIL_START = setUpSound("flight_fail_start");
    public static final RegistrySupplier<SoundEvent> CONSOLE_POWER_ON = setUpSound("console_power_on");
    public static final RegistrySupplier<SoundEvent> ALARM = setUpSound("alarm");
    public static final RegistrySupplier<SoundEvent> INTERIOR_VOICE = setUpSound("interior_voice");
    public static final RegistrySupplier<SoundEvent> LOW_FUEL = setUpSound("low_fuel");
    public static final RegistrySupplier<SoundEvent> ARTRON_PILLAR_ACTIVE = setUpSound("artron_pillar_active");

    public static final RegistrySupplier<SoundEvent> CORRIDOR_TELEPORTER = setUpSound("corridor_teleporter");
    public static final RegistrySupplier<SoundEvent> CORRIDOR_TELEPORTER_SUCCESS = setUpSound("corridor_teleporter_success");
    public static final RegistrySupplier<SoundEvent> CLOISTER_BELL = setUpSound("cloister_bell");
    public static final RegistrySupplier<SoundEvent> MALLET = setUpSound("mallet");
    public static final RegistrySupplier<SoundEvent> VORTEX = setUpSound("vortex");

    // Hums

    public static final RegistrySupplier<SoundEvent> HUM_CORAL = setUpSound("hum_coral");
    public static final RegistrySupplier<SoundEvent> HUM_CAVE = setUpSound("hum_cave");
    public static final RegistrySupplier<SoundEvent> HUM_TOYOTA = setUpSound("hum_toyota");
    public static final RegistrySupplier<SoundEvent> HUM_CLASSIC = setUpSound("hum_classic");
    public static final RegistrySupplier<SoundEvent> HUM_VICTORIAN = setUpSound("hum_victorian");
    public static final RegistrySupplier<SoundEvent> HUM_AVIATRAX = setUpSound("hum_aviatrax");


    // Screwdriver
    public static final RegistrySupplier<SoundEvent> SCREWDRIVER_SHORT = setUpSound("screwdriver_short");
    public static final RegistrySupplier<SoundEvent> SCREWDRIVER_CONNECT = setUpSound("screwdriver_connect");
    public static final RegistrySupplier<SoundEvent> SCREWDRIVER_DISCARD = setUpSound("screwdriver_discard");
    public static final RegistrySupplier<SoundEvent> GRAVITY_TUNNEL = setUpSound("gravity_tunnel");

    private static RegistrySupplier<SoundEvent> setUpSound(String soundName) {
        SoundEvent sound = SoundEvent.createVariableRangeEvent(new ResourceLocation(TardisRefined.MODID, soundName));
        return SOUNDS.register(soundName, () -> sound);
    }
}
