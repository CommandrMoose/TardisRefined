package whocraft.tardis_refined.common.capability.tardis.neoforge;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.Optional;
import java.util.function.BiFunction;

@EventBusSubscriber(modid = TardisRefined.MODID)
public class TardisLevelOperatorImpl extends SavedData {

    public static final String DATA_NAME = TardisRefined.MODID + "_tardis_data";
    private final TardisLevelOperator operator;

    public TardisLevelOperatorImpl(ServerLevel level) {
        this.operator = new TardisLevelOperator(level);
    }

    public static TardisLevelOperatorImpl create(ServerLevel serverLevel) {
        return new TardisLevelOperatorImpl(serverLevel);
    }

    public static SavedData getSaveData(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(() -> TardisLevelOperatorImpl.create(level), new BiFunction<CompoundTag, HolderLookup.Provider, SavedData>() {
            @Override
            public SavedData apply(CompoundTag compoundTag, HolderLookup.Provider provider) {
                return TardisLevelOperatorImpl.create(level).load(compoundTag);
            }
        }), DATA_NAME);
    }


    public static Optional<TardisLevelOperator> get(ServerLevel level) {
        if(getSaveData(level) instanceof TardisLevelOperatorImpl tardisLevelOperator){
            return Optional.of(tardisLevelOperator.operator);
        }
        return Optional.empty();
    }





    public TardisLevelOperator getOperator() {
        return operator;
    }

    public TardisLevelOperatorImpl load(CompoundTag compoundTag) {
        operator.deserializeNBT(compoundTag);
        return this;
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent event) {
        if (event.getLevel() instanceof ServerLevel level) {
            if (level.dimensionTypeRegistration() == TRDimensionTypes.TARDIS) {
                TardisLevelOperatorImpl data = (TardisLevelOperatorImpl) getSaveData(level);
                data.getOperator().tick(level);
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag arg, HolderLookup.Provider arg2) {
        return operator.serializeNBT();
    }
}
