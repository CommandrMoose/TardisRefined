package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.nbt.CompoundTag;

/**
 * Common template object to allow for saving of data
 */
public abstract class BaseHandler {

    abstract CompoundTag saveData(CompoundTag tag);

    abstract void loadData(CompoundTag tag);
}
