package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import whocraft.tardis_refined.TardisRefined;

public class TRDimensionTypes {

    public static ResourceKey<DimensionType> TARDIS;

    public static void register() {
        TARDIS = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "tardis"));
    }
}
