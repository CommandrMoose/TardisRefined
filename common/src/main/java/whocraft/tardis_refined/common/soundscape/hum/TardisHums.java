package whocraft.tardis_refined.common.soundscape.hum;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.util.CodecJsonReloadListener;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.*;

public class TardisHums {


    public static final HumEntry CAVE = new HumEntry("cave", TRSoundRegistry.HUM_CAVE.getId());
    public static final HumEntry TOYOTA = new HumEntry("toyota", TRSoundRegistry.HUM_TOYOTA.getId());
    public static final HumEntry CLASSIC = new HumEntry("classic", TRSoundRegistry.HUM_CLASSIC.getId());
    public static final HumEntry VICTORIAN = new HumEntry("victorian", TRSoundRegistry.HUM_VICTORIAN.getId());
    public static final HumEntry BASALT_DELTAS = new HumEntry(
            "basalt_deltas",
            SoundEvents.AMBIENT_BASALT_DELTAS_LOOP.value().getLocation(),
            createSoundList(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS.value(), SoundEvents.AMBIENT_BASALT_DELTAS_MOOD.value())
    );
    public static final HumEntry CRIMSON_FOREST = new HumEntry(
            "crimson_forest",
            SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP.value().getLocation(),
            createSoundList(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS.value(), SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD.value())
    );
    public static final HumEntry NETHER_WASTES = new HumEntry(
            "nether_wastes",
            SoundEvents.AMBIENT_NETHER_WASTES_LOOP.value().getLocation(),
            createSoundList(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS.value(), SoundEvents.AMBIENT_NETHER_WASTES_MOOD.value())
    );
    public static final HumEntry UNDER_WATER = new HumEntry(
            "under_water",
            SoundEvents.AMBIENT_UNDERWATER_LOOP.getLocation(),
            createSoundList(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS, SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE, SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE)
    );
    public static final HumEntry SOUL_SAND_VALLEY = new HumEntry(
            "soul_sand_valley",
            SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP.value().getLocation(),
            createSoundList(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS.value(), SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD.value())
    );
    public static final HumEntry WARPED_FOREST = new HumEntry(
            "warped_forest",
            SoundEvents.AMBIENT_WARPED_FOREST_LOOP.value().getLocation(),
            createSoundList(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS.value(), SoundEvents.AMBIENT_WARPED_FOREST_MOOD.value())
    );
    public static final HumEntry AVIATRAX = new HumEntry(
            "aviatrax",
            TRSoundRegistry.HUM_AVIATRAX.getId(), new ArrayList<>()
    );

    public static final HumEntry COPPER = new HumEntry(
            "copper",
            TRSoundRegistry.HUM_COPPER.getId(), new ArrayList<>()
    );

    private static final CodecJsonReloadListener<HumEntry> RELOAD_LISTENER = createReloadListener();
    private static final Map<ResourceLocation, HumEntry> DEFAULT_HUMS = new HashMap<>();

    private static List<ResourceLocation> createSoundList(SoundEvent... sounds) {
        ArrayList<ResourceLocation> soundList = new ArrayList<>();
        for (SoundEvent sound : sounds) {
            soundList.add(sound.getLocation());
        }
        return soundList;
    }

    /**
     * A factory method to create the instance of our reload listener.
     *
     * @return
     */
    private static CodecJsonReloadListener<HumEntry> createReloadListener() {
        return CodecJsonReloadListener.create(TardisRefined.MODID + "/hums", HumEntry.codec());
    }

    public static CodecJsonReloadListener<HumEntry> getReloadListener() {
        return RELOAD_LISTENER;
    }

    public static Map<ResourceLocation, HumEntry> getRegistry() {
        return RELOAD_LISTENER.getData();
    }

    public static HumEntry getHumById(ResourceLocation location) {
        return RELOAD_LISTENER.getData().getOrDefault(location, getDefaultHum());
    }

    /**
     * Creates and adds the Tardis Refined default list of HumEntries to a standalone map.
     * Can be used for datagenerators or as a fallback registry
     *
     * @return
     */
    public static Map<ResourceLocation, HumEntry> registerDefaultHums() {
        DEFAULT_HUMS.clear();
        addDefaultHum(getDefaultHum());
        addDefaultHum(CAVE);
        addDefaultHum(TOYOTA);
        addDefaultHum(VICTORIAN);
        addDefaultHum(CLASSIC);
        addDefaultHum(AVIATRAX);
        return DEFAULT_HUMS;
    }

    private static HumEntry addDefaultHum(HumEntry hum) {
        DEFAULT_HUMS.put(hum.getIdentifier(), hum);
        return hum;
    }


    /**
     * Gets a default HumEntry added by Tardis Refined. Useful as a fallback entry.
     */
    public static HumEntry getDefaultHum() {
        ArrayList<ResourceLocation> arrayList = new ArrayList();
        arrayList.add(new ResourceLocation(TardisRefined.MODID, "interior_creaks"));
        return new HumEntry(new ResourceLocation(TardisRefined.MODID, "coral"), TRSoundRegistry.HUM_CORAL.getId(), arrayList);
    }

    public static Map<ResourceLocation, HumEntry> getDefaultHums() {
        return DEFAULT_HUMS;
    }
}
