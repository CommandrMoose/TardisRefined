package whocraft.tardis_refined.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import whocraft.tardis_refined.TardisRefined;

import java.util.function.Supplier;

public class TRVillagerProfession {

    public static final DeferredRegistry<VillagerProfession> PROFESSIONS = DeferredRegistry.create(TardisRefined.MODID, Registries.VILLAGER_PROFESSION);

    public static final RegistrySupplier<VillagerProfession> PILOT = registerVillagerProfession("pilot", () -> createVillagerProfession(
            "pilot",
            TRPointOfInterestTypes.CONSOLE_UNIT,
            TRSoundRegistry.DESTINATION_DING
    ));
    public static final RegistrySupplier<VillagerProfession> ASTRONOMER = registerVillagerProfession("astronomer", () -> createVillagerProfession(
            "astronomer",
            TRPointOfInterestTypes.ASTRAL_MAP,
            new Supplier<SoundEvent>() {
                @Override
                public SoundEvent get() {
                    return SoundEvents.BOOK_PAGE_TURN;
                }
            }
    ));

    public static RegistrySupplier<VillagerProfession> registerVillagerProfession(String name, Supplier<VillagerProfession> villagerProfessionSupplier) {
        return PROFESSIONS.register(name, villagerProfessionSupplier);
    }

    private static VillagerProfession createVillagerProfession(String name, ResourceKey<PoiType> pointOfInterestType, Supplier<SoundEvent> soundEventSupplier) {
        return new VillagerProfession(
                name,
                poiTypeHolder -> poiTypeHolder.is(pointOfInterestType),
                poiTypeHolder -> poiTypeHolder.is(pointOfInterestType),
                ImmutableSet.of(),
                ImmutableSet.of(),
                soundEventSupplier.get()
        );
    }
}
