package whocraft.tardis_refined.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class TRItemData {

    public static final DeferredRegister<DataComponentType<?>> ITEMS = DeferredRegister.create(TardisRefined.MODID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<?>> SCREWDRIVER_MODE = ITEMS.register("screwdriver_mode", () -> DataComponentType.builder().persistent(ScrewdriverMode.CODEC).build());
    public static final RegistrySupplier<DataComponentType<BlockPos>> LINKED_MANIPULATOR_POS = ITEMS.register("linked_manipulator_pos", () -> DataComponentType.create(BlockPos.CODEC));
    public static final RegistrySupplier<DataComponentType<BlockPos>> SCREWDRIVER_POINT_A = ITEMS.register("screwdriver_point_a", () -> DataComponentType.builder().persistent(BlockPos.CODEC));
    public static final RegistrySupplier<DataComponentType<BlockPos>> SCREWDRIVER_POINT_B = ITEMS.register("screwdriver_point_b", () -> DataComponentType.create(BlockPos.CODEC));
    public static final RegistrySupplier<DataComponentType<Boolean>> SCREWDRIVER_B_WAS_LAST_UPDATED = ITEMS.register("screwdriver_b_was_last_updated", () -> DataComponentType.create(ExtraCodecs.BOOLEAN_CODEC));

}
