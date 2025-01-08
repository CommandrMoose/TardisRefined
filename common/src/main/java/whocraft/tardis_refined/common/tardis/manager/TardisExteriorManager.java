package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.blockentity.shell.ExteriorShell;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.constants.NbtConstants;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * External Shell data.
 **/
public class TardisExteriorManager extends BaseHandler {
    private final TardisLevelOperator operator;
    private double fuelForShellChange = 15; // Amount of fuel required to change the shell
    private boolean locked;
    private boolean isLanding;
    private boolean isTakingOff;
    public TardisExteriorManager(TardisLevelOperator operator) {
        this.operator = operator;
    }

    /**
     * Determine if the Tardis's doors, no matter the external shell or internal door, should be locked
     */
    public boolean locked() {
        return this.locked;
    }

    /**
     * Update the external shell block's locked property so that players cannot enter it without a synced Key item
     */
    public void setLocked(boolean locked) {

        TardisPilotingManager pilotingManager = this.operator.getPilotingManager();


        this.locked = locked;
        if (pilotingManager != null) {

            if (pilotingManager.isInFlight()) {
                return;
            }

            TardisNavLocation currentLocation = pilotingManager.getCurrentLocation();
            Level level = currentLocation.getLevel();
            BlockPos extPos = currentLocation.getPosition();
            if (level.getBlockEntity(extPos) != null) {
                BlockEntity extShellBlockEntity = level.getBlockEntity(extPos);
                if (extShellBlockEntity instanceof ExteriorShell exteriorShell) {
                    exteriorShell.setLocked(locked);
                }
            }
        }

    }

    public boolean isLanding() {
        return this.isLanding;
    }

    public boolean isTakingOff() {
        return this.isTakingOff;
    }

    public void setIsTakingOff(boolean isTakingOff) {
        this.isTakingOff = isTakingOff;
        operator.tardisClientData().setIsTakingOff(true);
        operator.tardisClientData().sync();
    }

    @Override
    public CompoundTag saveData(CompoundTag tag) {

        tag.putBoolean(NbtConstants.LOCKED, locked);

        return tag;
    }

    @Override
    public void loadData(CompoundTag tag) {
        locked = tag.getBoolean(NbtConstants.LOCKED);
    }

    public void playSoundAtShell(SoundEvent event, SoundSource source, float volume, float pitch) {
        TardisPilotingManager pilotingManager = this.operator.getPilotingManager();
        if (pilotingManager != null) {
            if (pilotingManager.getCurrentLocation() != null) {
                TardisNavLocation currentLocation = pilotingManager.getCurrentLocation();
                ServerLevel lastKnownLocationLevel = currentLocation.getLevel();

                lastKnownLocationLevel.playSound(null, currentLocation.getPosition(), event, source, volume, pitch);
            }
        }
    }

    /**
     * Sets the Exterior Shell to be opened or closed
     */
    public void setDoorClosed(boolean closeDoor) {

        TardisNavLocation currentPosition = this.operator.getPilotingManager().getCurrentLocation();

        if (currentPosition == null) return;
        ServerLevel lastKnownLocationLevel = currentPosition.getLevel();

        // Get the exterior block.
        BlockEntity blockEntity = lastKnownLocationLevel.getBlockEntity(currentPosition.getPosition());
        if (blockEntity instanceof ExteriorShell exteriorShell) {
            exteriorShell.setClosed(closeDoor);
        }
    }

    public void removeExteriorBlock() {
        this.isLanding = false;

        TardisPilotingManager pilotingManager = this.operator.getPilotingManager();
        if (pilotingManager == null) {
            return;
        }

        TardisNavLocation currentPosition = this.operator.getPilotingManager().getCurrentLocation();
        if (currentPosition != null) {
            BlockPos lastKnownLocationPosition = currentPosition.getPosition();
            ServerLevel lastKnownLocationLevel = currentPosition.getLevel();
            ChunkPos chunkPos = lastKnownLocationLevel.getChunk(lastKnownLocationPosition).getPos();
            //Force load chunk
            lastKnownLocationLevel.setChunkForced(chunkPos.x, chunkPos.z, true); //Set chunk to be force loaded to properly remove block
            //Remove block
            if (lastKnownLocationLevel.getBlockEntity(lastKnownLocationPosition) instanceof GlobalShellBlockEntity globalShellBlockEntity) {
                lastKnownLocationLevel.removeBlock(lastKnownLocationPosition, false); //Set block to air with drop items flag to false
            }
            //Un-force load chunk
            lastKnownLocationLevel.setChunkForced(chunkPos.x, chunkPos.z, false); //Set chunk to not be force loaded after we remove the block
        }
    }

    /**
     * Setup the landing data updates and physical placement of the shell block
     */
    public void startLanding(TardisLevelOperator operator, TardisNavLocation location) {
        ServerLevel targetLevel = location.getLevel();
        BlockPos lastKnownLocationPosition = location.getPosition();
        ChunkPos chunkPos = location.getLevel().getChunk(lastKnownLocationPosition).getPos();

        this.isLanding = true;

        //Force load target chunk
        targetLevel.setChunkForced(chunkPos.x, chunkPos.z, true); //Set chunk to be force loaded to properly place block
        this.isLanding = true;
        operator.tardisClientData().setIsLanding(true);
        operator.tardisClientData().sync();

        this.placeExteriorBlockForLanding(location);

        //Un-force load target chunk
        targetLevel.setChunkForced(chunkPos.x, chunkPos.z, false); //Set chunk to be not be force loaded after we place the block

    }

    /**
     * Convenience method to place the exterior block when the Tardis is landing
     */
    public void placeExteriorBlockForLanding(TardisNavLocation location) {
        this.operator.setOrUpdateExteriorBlock(location, Optional.empty());
    }

    /**
     * Returns whether a Tardis has enough fuel to perform an interior change
     *
     * @return true if the Tardis has enough fuel
     */
    public boolean hasEnoughFuelForShellChange() {
        return this.operator.getPilotingManager().getFuel() >= this.getFuelForShellChange();
    }

    /**
     * The amount of fuel required to change the exterior shell
     *
     * @return double amount of fuel to be removed
     */
    public double getFuelForShellChange() {
        return this.fuelForShellChange;
    }

    /**
     * Sets the amount of fuel required to change the exterior shell
     *
     * @param fuel the amount of fuel
     */
    private void setFuelForShellChange(double fuel) {
        this.fuelForShellChange = fuel;
    }
}
