package whocraft.tardis_refined.common.capability.tardis;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import whocraft.tardis_refined.api.event.ShellChangeSource;
import whocraft.tardis_refined.api.event.ShellChangeSources;
import whocraft.tardis_refined.api.event.TardisCommonEvents;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.block.shell.ShellBaseBlock;
import whocraft.tardis_refined.common.blockentity.door.RootShellDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.TardisInternalDoor;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.blockentity.shell.ExteriorShell;
import whocraft.tardis_refined.common.tardis.TardisArchitectureHandler;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.*;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortals;
import whocraft.tardis_refined.constants.NbtConstants;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import java.util.Optional;
import java.util.UUID;

import static whocraft.tardis_refined.common.block.shell.ShellBaseBlock.REGEN;

public class TardisLevelOperator {
    public static final int STATE_CAVE = 0;
    public static final int STATE_TERRAFORMED_NO_EYE = 1;
    public static final int STATE_EYE_OF_HARMONY = 2;
    private final Level level;
    private final ResourceKey<Level> levelKey;
    // Managers
    private final TardisExteriorManager exteriorManager;
    private final TardisInteriorManager interiorManager;
    private final TardisPilotingManager pilotingManager;
    private final TardisWaypointManager tardisWaypointManager;
    private final FlightDanceManager flightDanceManager;
    private final TardisClientData tardisClientData;
    private final UpgradeHandler upgradeHandler;
    private final AestheticHandler aestheticHandler;
    private final ProgressionManager progressionManager;

    private boolean hasInitiallyGenerated = false;
    private TardisInternalDoor internalDoor = null;
    // TARDIS state refers to different stages of TARDIS creation. This allows for different logic to operate in those moments.
    private int tardisState = 0;


    public TardisLevelOperator(Level level) {
        this.level = level;
        this.levelKey = level.dimension();
        this.exteriorManager = new TardisExteriorManager(this);
        this.interiorManager = new TardisInteriorManager(this);
        this.pilotingManager = new TardisPilotingManager(this);
        this.tardisWaypointManager = new TardisWaypointManager(this);
        this.tardisClientData = new TardisClientData(level.dimension());
        this.upgradeHandler = new UpgradeHandler(this);
        this.aestheticHandler = new AestheticHandler(this);
        this.flightDanceManager = new FlightDanceManager(this);
        this.progressionManager = new ProgressionManager();
    }

    @ExpectPlatform
    public static Optional<TardisLevelOperator> get(ServerLevel level) {
        throw new AssertionError();
    }

    public UpgradeHandler getUpgradeHandler() {
        return upgradeHandler;
    }

    public TardisClientData tardisClientData() {
        return tardisClientData;
    }

    public AestheticHandler getAestheticHandler() {
        return aestheticHandler;
    }

    public FlightDanceManager getFlightDanceManager() {
        return this.flightDanceManager;
    }

    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean(NbtConstants.TARDIS_IS_SETUP, this.hasInitiallyGenerated);

        if (this.internalDoor != null) {
            compoundTag.putString(NbtConstants.TARDIS_INTERNAL_DOOR_ID, this.internalDoor.getID());
            compoundTag.put(NbtConstants.TARDIS_INTERNAL_DOOR_POSITION, NbtUtils.writeBlockPos(this.internalDoor.getDoorPosition()));
        }

        compoundTag = this.exteriorManager.saveData(compoundTag);
        compoundTag = this.interiorManager.saveData(compoundTag);
        compoundTag = this.pilotingManager.saveData(compoundTag);
        compoundTag = this.tardisWaypointManager.saveData(compoundTag);
        compoundTag = this.upgradeHandler.saveData(compoundTag);
        compoundTag = this.aestheticHandler.saveData(compoundTag);
        compoundTag = this.flightDanceManager.saveData(compoundTag);
        compoundTag = this.progressionManager.saveData(compoundTag);

        compoundTag.putInt("tardis_state", this.tardisState);

        return compoundTag;
    }

    public void deserializeNBT(CompoundTag tag) {
        this.hasInitiallyGenerated = tag.getBoolean(NbtConstants.TARDIS_IS_SETUP);

        CompoundTag doorPos = tag.getCompound(NbtConstants.TARDIS_INTERNAL_DOOR_POSITION);
        if (doorPos != null) {
            if (level.getBlockEntity(NbtUtils.readBlockPos(doorPos)) instanceof TardisInternalDoor door) {
                this.internalDoor = door;
                this.internalDoor.setID(tag.getString(NbtConstants.TARDIS_INTERNAL_DOOR_ID));
            }
        }

        // Managers
        this.exteriorManager.loadData(tag);
        this.interiorManager.loadData(tag);
        this.pilotingManager.loadData(tag);
        this.tardisWaypointManager.loadData(tag);
        this.upgradeHandler.loadData(tag);
        this.aestheticHandler.loadData(tag);
        this.flightDanceManager.loadData(tag);
        this.progressionManager.loadData(tag);

        this.tardisState = tag.getInt("tardis_state");

        tardisClientData.sync();
    }

    public Level getLevel() {
        return this.level;
    }

    public ResourceKey<Level> getLevelKey() {
        return this.levelKey;
    }

    public void tick(ServerLevel level) {

        if (interiorManager != null) {
            interiorManager.tick(level);
        }
        if (pilotingManager != null) {
            pilotingManager.tick(level);
        }
        if (flightDanceManager != null) {
            flightDanceManager.tick(level);
        }

        // Update Viewing Players!
        level.getServer().getPlayerList().getPlayers().forEach(serverPlayer -> {
            TardisPlayerInfo.get(serverPlayer).ifPresent(tardisPlayerInfo -> {
                tardisPlayerInfo.tick(this, serverPlayer);
            });
        });

        var shouldSync = level.getGameTime() % 40 == 0;
        if (shouldSync) {
            tardisClientData.setIsOnCooldown(pilotingManager.isInRecovery());
            tardisClientData.setShellTheme(aestheticHandler.getShellTheme());
            tardisClientData.setShellPattern(aestheticHandler.shellPattern().id());
            tardisClientData.setHumEntry(interiorManager.getHumEntry());
            tardisClientData.setFuel(pilotingManager.getFuel());
            tardisClientData.setMaximumFuel(pilotingManager.getMaximumFuel());
            tardisClientData.setTardisState(tardisState);
            tardisClientData.setRecoveryProgress(pilotingManager.getCrashRecoveryTicks());

            //Needed now for flight viewer
            tardisClientData.setFlying(pilotingManager.isInFlight());
            tardisClientData.setIsLanding(exteriorManager.isLanding());
            tardisClientData.setIsTakingOff(exteriorManager.isTakingOff());
            tardisClientData.setIsCrashing(pilotingManager.isCrashing());
            tardisClientData.setVortex(aestheticHandler.getVortex());

            float percentageCompleted = (getPilotingManager().getFlightPercentageCovered() * 100f);
            if (percentageCompleted > 100) {
                percentageCompleted = 100;
            }

            tardisClientData.setJourneyProgress(percentageCompleted);

            tardisClientData.sync();
        } else {
            tardisClientData.setIsCrashing(pilotingManager.isCrashing());
            tardisClientData.setRecoveryProgress(pilotingManager.getCrashRecoveryTicks());
            tardisClientData.setFlying(pilotingManager.isInFlight());
            tardisClientData.setIsLanding(exteriorManager.isLanding());
            tardisClientData.setIsTakingOff(exteriorManager.isTakingOff());
            tardisClientData.setThrottleStage(pilotingManager.getThrottleStage());
            tardisClientData.setHandbrakeEngaged(pilotingManager.isHandbrakeOn());
            tardisClientData.setVortex(aestheticHandler.getVortex());

            tardisClientData.sync();
        }
    }

    public boolean hasInitiallyGenerated() {
        return hasInitiallyGenerated;
    }

    public void setInitiallyGenerated(boolean hasInitiallyGenerated) {
        this.hasInitiallyGenerated = hasInitiallyGenerated;
    }

    public ProgressionManager getProgressionManager() {
        return progressionManager;
    }

    /**
     * Moves the entity into the TARDIS. If the TARDIS has no door established, the entity is sent to 0,100,0.
     **/
    public boolean enterTardis(Entity entity, BlockPos externalShellPos, ServerLevel shellLevel, Direction shellDirection) {
        if (!(this.level instanceof ServerLevel targetServerLevel)) {
            return false;
        }

        // Determine target position and direction
        BlockPos targetPosition = internalDoor != null ? internalDoor.getTeleportPosition() : TardisArchitectureHandler.DESKTOP_CENTER_POS.above();
        Direction targetDirection = internalDoor != null ? internalDoor.getTeleportRotation() : entity.getDirection();

        // Define source and target locations
        TardisNavLocation sourceLocation = new TardisNavLocation(externalShellPos, shellDirection, shellLevel);
        TardisNavLocation targetLocation = new TardisNavLocation(targetPosition, targetDirection, targetServerLevel);

        // Update current location
        this.pilotingManager.setCurrentLocation(new TardisNavLocation(externalShellPos, shellDirection.getOpposite(), shellLevel));

        // Handle teleportation
        if (entity instanceof ServerPlayer serverPlayer) {
            TardisPlayerInfo.get(serverPlayer).ifPresent(tardisPlayerInfo -> {
                if (!tardisPlayerInfo.isViewingTardis()) {
                    TardisHelper.teleportEntityTardis(this, entity, sourceLocation, targetLocation, true);
                }
            });
        } else {
            TardisHelper.teleportEntityTardis(this, entity, sourceLocation, targetLocation, true);
        }
        return true;
    }

    public boolean isTardisReady() {
        return !this.getInteriorManager().isGeneratingDesktop();
    }

    public boolean exitTardis(Entity entity, ServerLevel doorLevel, BlockPos doorPos, Direction doorDirection, boolean ignoreDoor) {

        if (!ignoreDoor && !this.internalDoor.isOpen()) {
            return false;
        }

        if (aestheticHandler == null || pilotingManager == null) {
            return false;
        }

        if (aestheticHandler.getShellTheme() != null) {
            ResourceLocation theme = aestheticHandler.getShellTheme();
            if (ModCompatChecker.immersivePortals() && !(this.internalDoor instanceof RootShellDoorBlockEntity)) {
                if (!ignoreDoor && ImmersivePortals.isShellThemeSupported(theme) && ImmersivePortals.doPortalsExistForTardis(UUID.fromString(doorLevel.dimension().location().getPath()))) {
                    return false;
                }
            }
        }

        if (this.pilotingManager.getCurrentLocation() != null) {

            TardisNavLocation currentLocation = this.pilotingManager.getCurrentLocation();
            BlockPos exteriorPos = currentLocation.getPosition();
            ServerLevel targetLevel = currentLocation.getLevel();
            Direction targetDirection = currentLocation.getDirection().getOpposite();

            BlockPos teleportPos = exteriorPos;

            if (targetLevel.getBlockEntity(exteriorPos) instanceof ExteriorShell exteriorShell) {
                teleportPos = exteriorShell.getTeleportPosition();
                targetDirection = exteriorShell.getTeleportRotation(); //Use the exterior shell's facing instead of the target direction to cover a case where the direction is changed as the player exits
            }

            TardisNavLocation sourceLocation = new TardisNavLocation(doorPos, doorDirection, doorLevel);
            TardisNavLocation destinationLocation = new TardisNavLocation(teleportPos, targetDirection, targetLevel);

            TardisHelper.teleportEntityTardis(this, entity, sourceLocation, destinationLocation, false);
            return true;
        }

        return false;
    }

    public void forceEjectAllPlayers() {
        for (Player player : this.level.players()) {
            if (player instanceof ServerPlayer serverPlayer) {
                this.forceEjectPlayer(serverPlayer);
            }
        }
    }

    /**
     * Helper to automatically teleport the player to the Tardis' current location
     * <br> Used for emergency eject feature or to prevent players from remaining in the Tardis during desktop generaton
     */
    public boolean forceEjectPlayer(ServerPlayer player) {
        if (player != null) {
            TardisNavLocation location = this.getPilotingManager().getCurrentLocation();
            return this.exitTardis(player, location.getLevel(), location.getPosition(), location.getDirection(), true);
        }
        return false;
    }

    /**
     * Unified logic to update blockstates and data
     *
     * @param startRegen - True if we should mark the Tardis is regenerating itself.
     * @return True if successfully triggered, false if failed
     */
    public boolean triggerRegenState(boolean startRegen) {

        TardisPilotingManager pilotingManager = this.getPilotingManager();
        if (pilotingManager == null) {
            return false;
        }

        TardisNavLocation currentPosition = this.getPilotingManager().getCurrentLocation();
        if (currentPosition == null) return false;
        BlockPos currentBlockPos = currentPosition.getPosition();
        ServerLevel currentLevel = currentPosition.getLevel();

        BlockState state = currentLevel.getBlockState(currentBlockPos);
        if (currentLevel == null) return false;
        if (state.getBlock() instanceof ShellBaseBlock shellBaseBlock && state.hasProperty(REGEN)) { //Check if this is our shell block and that its type has a Regen block state
            if (startRegen) //Only close the door if we are starting the regeneration process. Otherwise, don't update the door.
                this.setDoorClosed(true); //Set the door closed. Must call this instead of simply updating the blockstate because this way we update the internal door too.
            this.setDoorLocked(startRegen); //Set the exterior shell door to be locked.

            //Fetch a new instance of the Blockstate after we have applied the door closing and locking updates above.
            //This is needed to ensure the LOCKED and OPEN blockstate properties on the Shell block are being kept.
            BlockState blockStateAfterDoorUpdates = currentLevel.getBlockState(currentBlockPos);

            //Extra sanity check to ensure the player didn't rapidly replace the block at this position with another block.
            //Unlikely, but you never know what players are capable of.
            if (blockStateAfterDoorUpdates.hasProperty(ShellBaseBlock.REGEN)) {
                BlockState updatedBlockState = blockStateAfterDoorUpdates.setValue(ShellBaseBlock.REGEN, startRegen); //Set the block to be in a regenerating state
                if (this.getTardisState() == STATE_CAVE || this.getTardisState() == STATE_TERRAFORMED_NO_EYE) { //If either in a cave state or terraformed without an activated eye of harmony, assume we are transforming from root shell to half baked Tardis
                    //If starting regen for a root shell, update, but do not, create a new blockstate instance
                    //We only want to create a new blockstate instance when generation is finished so that a new GlobalShellBlockEntity instance can be placed with the half-baked theme applied
                    //The logic to handle whether to use the passed in blockstate is being handled in setOrUpdateExteriorBlock
                    this.setOrUpdateExteriorBlock(currentPosition, Optional.of(updatedBlockState), startRegen, ShellChangeSources.ROOT_TO_TARDIS);
                } else {
                    this.setOrUpdateExteriorBlock(currentPosition, Optional.of(updatedBlockState), startRegen, ShellChangeSources.REGEN_EXISTING_TARDIS);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Convenience method to use when we are going to update the exterior block but we are not setting up a new Tardis
     *
     * @param location         - location of the block we are updating
     * @param targetBlockState - Only leave this empty if we are landing the Tardis after finishing flight, otherwise provide a value to indicate we are updating specific data on an existing Tardis shell.
     * @implNote You must call this after calling {@link TardisLevelOperator#setShellTheme(ResourceLocation, ResourceLocation, ShellChangeSource)}
     */
    public void setOrUpdateExteriorBlock(TardisNavLocation location, Optional<BlockState> targetBlockState) {
        setOrUpdateExteriorBlock(location, targetBlockState, false, ShellChangeSources.GENERIC_UPDATE);
    }

    /**
     * Common logic to set or update the exterior shell block. This is needed to ensure we preserve data on the exterior shell such as Shell Patterns.
     *
     * @param location             - target position we are performing block updates on.
     * @param overridingBlockState - Pass in an updated exterior shell blockstate with the data you want to include. Only leave this empty if we are landing the Tardis after finishing flight, otherwise provide a value to indicate we are updating specific data on an existing Tardis shell.
     * @param startingRegen        - If we are starting or finishing the regenerating of the Tardis. e.g. During Desktop generation.
     * @param shellChangeSource    - Source of the shell update. If the Shell Change event was caused by a Tardis being setup from a Root Shell to a fully functioning version.
     * @implNote If we have updated the ShellTheme but haven't updated the Exterior data yet, you must call this after calling {@link TardisLevelOperator#setShellTheme(ResourceLocation, ResourceLocation, ShellChangeSource)}
     */
    public void setOrUpdateExteriorBlock(TardisNavLocation location, Optional<BlockState> overridingBlockState, boolean startingRegen, ShellChangeSource shellChangeSource) {
        AestheticHandler aestheticHandler = this.getAestheticHandler();
        ResourceLocation theme = (aestheticHandler.getShellTheme() != null) ? aestheticHandler.getShellTheme() : ShellTheme.HALF_BAKED.getId();
        ShellTheme shellTheme = ShellTheme.getShellTheme(theme);
        ShellPattern shellPattern = aestheticHandler.getShellTheme() != null ? aestheticHandler.shellPattern() : null;

        ServerLevel targetLevel = location.getLevel();
        BlockPos targetLocation = location.getPosition();

        //In the below logic, we will check if we need to update the existing blockstate. Otherwise, utilise a new blockstate instance of the exterior block

        //New instance of an exterior block created if we need to use it, such as for when the Tardis starts landing after flight, or finishing the root shell to half baked terraforming
        //Do not update the REGEN, OPEN or LOCKED property, because that should be manually called when player interacts with the door, or during events the Tardis triggers such as regenerating desktop, or the DoorControl
        BlockState newExteriorBlock = TRBlockRegistry.GLOBAL_SHELL_BLOCK.get().defaultBlockState().setValue(ShellBaseBlock.FACING, location.getDirection().getOpposite())
                .setValue(ShellBaseBlock.REGEN, false)
                .setValue(ShellBaseBlock.WATERLOGGED, location.getLevel().getBlockState(targetLocation).getFluidState().getType() == Fluids.WATER);

        //If the Tardis is landing after a flight or we are setting up a Tardis for the first time, utilise a new blockstate.
        //Otherwise, simply update the values of the passed-in blockstate so that we don't need to change things we don't want.
        BlockState selectedBlockState;
        if (shellChangeSource == ShellChangeSources.ROOT_TO_TARDIS) {
            //If starting regen for a root shell, update, but do not, create a new blockstate instance
            //We only want to create a new blockstate instance when generation is finished so that a new GlobalShellBlockEntity instance can be placed with the half-baked theme applied
            selectedBlockState = startingRegen ? overridingBlockState.orElse(newExteriorBlock) : newExteriorBlock;
        } else {
            //Otherwise, if we are regenerating a Tardis that is fully terraformed and eye of harmony is activated, use the passed in blockstate for specific data updates, or if the provided blockstate is NULL, use the new exterior shell instance
            selectedBlockState = overridingBlockState.orElse(newExteriorBlock);
        }

        //Update the FACING and WATERLOGGED blockstate property on the Shell block.
        //Do not update the REGEN, OPEN or LOCKED property, because that should be manually called when player interacts with the door, or during events the Tardis triggers such as regenerating desktop, or the DoorControl
        BlockState updatedBlockState = selectedBlockState.setValue(ShellBaseBlock.FACING, location.getDirection().getOpposite())
                .setValue(ShellBaseBlock.WATERLOGGED, location.getLevel().getBlockState(targetLocation).getFluidState().getType() == Fluids.WATER);

        if (updatedBlockState.hasProperty(GlobalShellBlock.LIT) && shellTheme != null) { //Special logic to account for RootedShellBlock not having the LIT blockstate property
            updatedBlockState.setValue(GlobalShellBlock.LIT, shellTheme.producesLight());
        }


        //Place the exterior block
        targetLevel.setBlock(targetLocation, updatedBlockState, Block.UPDATE_ALL);

        // Set TARDIS Id, so the block actually knows what it is
        if (targetLevel.getBlockEntity(targetLocation) instanceof GlobalShellBlockEntity globalShellBlockEntity) {
            globalShellBlockEntity.setTardisId(levelKey);
            globalShellBlockEntity.setPattern(shellPattern);
            globalShellBlockEntity.sendUpdates();
        }

        //Copy over important data points such as patterns, and update the internal doors
        //TODO: Implement a system that allows updating of specific Tardis data, so that we don't need to update the theme and patterns when we don't need to.
        if (theme != null && shellPattern != null)
            this.setShellTheme(theme, shellPattern.id(), shellChangeSource);

        targetLevel.sendBlockUpdated(targetLocation, updatedBlockState, updatedBlockState, Block.UPDATE_CLIENTS);
    }

    /**
     * Unified logic to close or open a door
     * <br> Updates both internal door and exterior shell door OPEN state
     * <br> Fires the CloseDoor/OpenDoor events
     */
    public void setDoorClosed(boolean closeDoor) {
        TardisInternalDoor intDoor = getInternalDoor();
        //Closed the internal door
        if (intDoor != null) {
            intDoor.setClosed(closeDoor);
        }
        //Close the exterior shell door
        if (this.pilotingManager != null) {
            if (this.pilotingManager.getCurrentLocation() != null) {
                this.exteriorManager.setDoorClosed(closeDoor);
            }
        }

        //After closing/opening both exterior and interior doors, fire the events
        if (closeDoor) {
            TardisCommonEvents.DOOR_CLOSED_EVENT.invoker().onDoorClosed(this);
        } else {
            TardisCommonEvents.DOOR_OPENED_EVENT.invoker().onDoorOpen(this);
        }

    }

    /**
     * Unified logic to lock or unlock a door
     * <br> Updates both internal door and exterior shell door LOCK state
     * <br> Fires the LockDoor/UnlockDoor events
     */
    public void setDoorLocked(boolean lockDoor) {
        TardisInternalDoor intDoor = getInternalDoor();

        if (intDoor != null) {
            intDoor.setLocked(lockDoor);
        }

        if (this.pilotingManager != null) {
            if (this.pilotingManager.getCurrentLocation() != null) {
                if (this.exteriorManager != null) {
                    this.exteriorManager.setLocked(lockDoor);
                }
            }
        }
        //After locking/unlocking both exterior and interior doors, fire the events
        if (lockDoor) {
            TardisCommonEvents.DOOR_LOCKED_EVENT.invoker().onDoorLocked(this);
        } else {
            TardisCommonEvents.DOOR_UNLOCKED_EVENT.invoker().onDoorUnlocked(this);
        }

    }

    /**
     * Unified logic to update the Tardis' ShellTheme and Pattern, as well as the exterior and internal doors
     */
    public void setShellTheme(ResourceLocation shellTheme, ResourceLocation shellPattern, ShellChangeSource shellChangeSource) {
        aestheticHandler.setShellTheme(shellTheme, shellPattern, this.getPilotingManager().getCurrentLocation());
        aestheticHandler.setShellPattern(ShellPatterns.getPatternOrDefault(shellTheme, shellPattern));

        tardisClientData.setShellTheme(shellTheme);
        tardisClientData.setShellPattern(aestheticHandler.shellPattern().id());
        tardisClientData.sync();
        TardisCommonEvents.SHELL_CHANGE_EVENT.invoker().onShellChange(this, shellTheme, shellChangeSource);
    }

    /**
     * Sets up data and prepares the desktop theme for when the Tardis is generating for the first time.
     * <br> DO NOT update the exterior/internal doors here, because door updating logic is unified in {@link TardisLevelOperator#triggerRegenState(boolean)}
     * <br> We do not need to open the Tardis doors here because it is always being called in {@link whocraft.tardis_refined.common.block.shell.RootedShellBlock}
     */
    public void setupInitialCave(ServerLevel shellServerLevel, BlockState shellBlockState, BlockPos shellBlockPos) {
        this.interiorManager.generateDesktop(TardisDesktops.DEFAULT_OVERGROWN_THEME);

        Direction direction = shellBlockState.getValue(ShellBaseBlock.FACING).getOpposite();
        TardisNavLocation navLocation = new TardisNavLocation(shellBlockPos, direction, shellServerLevel);
        this.pilotingManager.setCurrentLocation(navLocation);
        this.pilotingManager.setTargetLocation(navLocation);

        this.setInitiallyGenerated(true);
        this.setTardisState(TardisLevelOperator.STATE_CAVE);
        this.interiorManager.setHumEntry(TardisHums.CAVE);
    }

    public TardisExteriorManager getExteriorManager() {
        return this.exteriorManager;
    }

    public TardisInternalDoor getInternalDoor() {
        return this.internalDoor;
    }

    /**
     * Sets the main operating door of an interior.
     *
     * @param door Internal door object.
     **/
    public void setInternalDoor(TardisInternalDoor door) {
        if (this.internalDoor != null) {
            this.internalDoor.onSetMainDoor(false);
        }
        this.internalDoor = door;
        if (door != null) //If the new door value is not null
            this.internalDoor.onSetMainDoor(true);
    }

    public TardisInteriorManager getInteriorManager() {
        return this.interiorManager;
    }

    public TardisPilotingManager getPilotingManager() {
        return this.pilotingManager;
    }

    public TardisWaypointManager getTardisWaypointManager() {
        return tardisWaypointManager;
    }

    public int getTardisState() {
        return tardisState;
    }

    public void setTardisState(int state) {
        this.tardisState = state;
    }
}
