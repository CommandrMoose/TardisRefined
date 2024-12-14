package whocraft.tardis_refined.common.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TRItemData {

    public static final DeferredRegister<DataComponentType<?>> ITEMS = DeferredRegister.create(TardisRefined.MODID, Registries.DATA_COMPONENT_TYPE);

    // Screwdriver
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<ScrewdriverMode>> SCREWDRIVER_MODE = ITEMS.register("screwdriver_mode", () -> DataComponentType.<ScrewdriverMode>builder().persistent(ScrewdriverMode.CODEC).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<BlockPos>> LINKED_MANIPULATOR_POS = ITEMS.register("linked_manipulator_pos", () -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<BlockPos>> SCREWDRIVER_POINT_A = ITEMS.register("screwdriver_point_a", () -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<BlockPos>> SCREWDRIVER_POINT_B = ITEMS.register("screwdriver_point_b", () -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<Boolean>> SCREWDRIVER_B_WAS_LAST_UPDATED = ITEMS.register("screwdriver_b_was_last_updated", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<Integer>> TIMER = ITEMS.register("timer", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<ResourceLocation>> POTENTIAL_DIM = ITEMS.register("potential_dim", () -> DataComponentType.<ResourceLocation>builder().persistent(ResourceLocation.CODEC).build());
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<ResourceLocation>> SAVED_DIM = ITEMS.register("saved_dim", () -> DataComponentType.<ResourceLocation>builder().persistent(ResourceLocation.CODEC).build());

    // Key Item
    public static final RegistryHolder<DataComponentType<?>, DataComponentType<List<ResourceKey<Level>>>> KEYCHAIN = ITEMS.register("keychain", () -> DataComponentType.<List<ResourceKey<Level>>>builder().persistent(listOfResourceKeys()).build());


    public static <T> Codec<List<ResourceKey<T>>> listOfResourceKeys() {
        return Codec.list(ResourceLocation.CODEC)
                .comapFlatMap(
                        resourceLocations -> {
                            List<ResourceKey<T>> resourceKeys = new ArrayList<>();
                            for (ResourceLocation location : resourceLocations) {
                                try {
                                    resourceKeys.add(ResourceKey.create(ResourceKey.createRegistryKey(location), location));
                                } catch (Exception e) {
                                    return DataResult.error(() -> "Failed to convert ResourceLocation to ResourceKey: " + location);
                                }
                            }
                            return DataResult.success(resourceKeys);
                        },
                        resourceKeys -> {
                            List<ResourceLocation> resourceLocations = new ArrayList<>();
                            for (ResourceKey<T> resourceKey : resourceKeys) {
                                resourceLocations.add(resourceKey.location());
                            }
                            return resourceLocations;
                        }
                );
    }


}
