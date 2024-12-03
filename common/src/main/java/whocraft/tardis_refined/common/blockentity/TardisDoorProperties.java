package whocraft.tardis_refined.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface TardisDoorProperties {

    boolean isOpen();

    /**
     * Source of truth logic for setting this Door to be opened/closed.
     */
    void setClosed(boolean closeDoor);

    BlockPos getDoorPosition();

    /**
     * Get the position which we want the player to land at when it is arrived at the door block, for teleportation purposes.
     *
     * @implNote This should be the opposite facing of the door block
     */
    BlockPos getTeleportPosition();

    /**
     * Get the direction which we want the player to face when it is arrived at the door block, for teleportation purposes.
     *
     * @implNote This should be the opposite facing of the door block
     */
    Direction getTeleportRotation();

    /**
     * The true facing of the door based off its blockstate
     */
    Direction getRotation();

    void onEntityExit(ServerEntity entity);

    /**
     * Sets the internal door to be locked
     */
    void setLocked(boolean locked);

    /**
     * Determines if this particular door block thinks it is locked or not.
     * <br> When multiple internal doors are placed inside a Tardis dimension, and the player interacts on a door that is not the main door, the TardisExteriorManager may still be tracking the previous door's data
     * <br> Hence the ExteriorManager will need to check this door's local value.
     */
    boolean locked();

    void onAttemptEnter(BlockState blockState, Level level, BlockPos doorPos, Entity entity);
}
