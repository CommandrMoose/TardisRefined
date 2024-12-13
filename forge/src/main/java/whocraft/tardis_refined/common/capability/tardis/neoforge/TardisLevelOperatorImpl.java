package whocraft.tardis_refined.common.capability.tardis.neoforge;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.capabilities.*;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
import net.neoforged.neoforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.Optional;

@EventBusSubscriber(modid = TardisRefined.MODID)
public class TardisLevelOperatorImpl implements ICapabilitySerializable<CompoundTag> {

    public static Capability<TardisLevelOperator> TARDIS_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });
    public final TardisLevelOperator operator;
    public final LazyOptional<TardisLevelOperator> lazyOptional;


    public TardisLevelOperatorImpl(ServerLevel level) {
        this.operator = new TardisLevelOperator(level);
        this.lazyOptional = LazyOptional.of(() -> this.operator);
    }

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent event) {
        event.register(TardisLevelOperator.class);
    }

    @SubscribeEvent
    public static void onLevelCapabilities(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject() instanceof ServerLevel level) {
            if (level.dimensionTypeRegistration().location() == TRDimensionTypes.TARDIS.location()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "tardis_data"), new TardisLevelOperatorImpl((ServerLevel) event.getObject()));
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.level instanceof ServerLevel level) {
            if (event.phase == TickEvent.Phase.START) {
                if (event.level.dimensionTypeRegistration().location() == TRDimensionTypes.TARDIS.location()) {
                    event.level.getCapability(TardisLevelOperatorImpl.TARDIS_DATA).ifPresent(x -> x.tick(level));
                }
            }
        }
    }

    public static Optional<TardisLevelOperator> get(ServerLevel level) {
        if (level == null) {
            return Optional.empty();
        }
        return level.getCapability(TARDIS_DATA).resolve();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return capability == TARDIS_DATA ? this.lazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.operator.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.operator.deserializeNBT(arg);
    }

}
