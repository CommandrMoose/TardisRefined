package whocraft.tardis_refined.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.TardisRefined;

public class TRPointOfInterestTypes {

    public static final ResourceKey<PoiType> CONSOLE_UNIT = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(TardisRefined.MODID, "console_unit"));
    public static final ResourceKey<PoiType> ASTRAL_MAP = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(TardisRefined.MODID, "astral_map"));

    ;
    public static final DeferredRegistry<PoiType> POIS = DeferredRegistry.create(TardisRefined.MODID, Registries.POINT_OF_INTEREST_TYPE);
    public static final RegistrySupplier<PoiType> CONSOLE_UNIT_POI = POIS.register("console_unit", () -> registerPointOfInterest(6, 15, TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get()));
    public static final RegistrySupplier<PoiType> ASTRAL_MAP_POI = POIS.register("astral_map", () -> registerPointOfInterest(1, 1, TRBlockRegistry.ASTRAL_MAP.get()));
    private static boolean hasBeenRegistered = false;

    public static void init() {
    }

    private static PoiType registerPointOfInterest(int maxTickets, int validRange, Block... blocks) {

        final ImmutableSet.Builder<BlockState> builder = ImmutableSet.builder();

        for (Block block : blocks) {
            builder.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(builder.build(), maxTickets, validRange);
    }


    // Call this at Server starting - ONLY ON FABRIC!!
    public static void registerBlockStates() {
        if (!hasBeenRegistered) {
            PoiTypes.registerBlockStates(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolderOrThrow(CONSOLE_UNIT), CONSOLE_UNIT_POI.get().matchingStates());
            PoiTypes.registerBlockStates(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolderOrThrow(ASTRAL_MAP), ASTRAL_MAP_POI.get().matchingStates());
            hasBeenRegistered = true;
        }
    }

}
