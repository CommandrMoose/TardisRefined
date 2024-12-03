package whocraft.tardis_refined.common.capability.fabric;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.fabric.TardisLevelOperatorImpl;

import java.util.Optional;

public class TardisLevelOperatorDummy extends TardisLevelOperatorImpl {

    public TardisLevelOperatorDummy(Level level) {
        super(level);
    }

    public static Optional<TardisLevelOperator> get(ServerLevel level) {
        if (level == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(TRComponents.TARDIS_DATA.get(level));
        } catch (Exception e) {
            TardisRefined.LOGGER.info(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void writeToNbt(CompoundTag tag) {

    }

    @Override
    public void readFromNbt(CompoundTag tag) {

    }


}
