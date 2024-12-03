package whocraft.tardis_refined.common.capability.tardis.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.fabric.TRComponents;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;

import java.util.Objects;
import java.util.Optional;

public class TardisLevelOperatorImpl extends TardisLevelOperator implements ComponentV3 {

    public TardisLevelOperatorImpl(Level level) {
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
    public void readFromNbt(CompoundTag tag) {
        this.deserializeNBT(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        CompoundTag nbt = this.serializeNBT();
        for (String key : nbt.getAllKeys()) {
            tag.put(key, Objects.requireNonNull(nbt.get(key)));
        }
    }
}
