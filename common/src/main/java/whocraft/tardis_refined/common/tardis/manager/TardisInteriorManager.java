package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.api.event.TardisCommonEvents;
import whocraft.tardis_refined.common.block.door.BulkHeadDoorBlock;
import whocraft.tardis_refined.common.blockentity.door.BulkHeadDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.TardisInternalDoor;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.dimension.TardisTeleportData;
import whocraft.tardis_refined.common.soundscape.hum.HumEntry;
import whocraft.tardis_refined.common.soundscape.hum.TardisHums;
import whocraft.tardis_refined.common.protection.ProtectedZone;
import whocraft.tardis_refined.common.tardis.TardisArchitectureHandler;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.constants.NbtConstants;
import whocraft.tardis_refined.constants.TardisDimensionConstants;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import java.util.ArrayList;
import java.util.List;

public class TardisInteriorManager extends TickableHandler {
    public static final BlockPos STATIC_CORRIDOR_POSITION = new BlockPos(1013, 99, 5);
    private final TardisLevelOperator operator;
    // Pillars
    BlockPos pillarTopLeft = new BlockPos(1024, 78, 55);
    BlockPos pillarTopRight = new BlockPos(1002, 78, 55);
    BlockPos pillarBottomLeft = new BlockPos(1016, 73, 55);
    BlockPos pillarBottomRight = new BlockPos(1010, 73, 55);
    private boolean isWaitingToGenerate = false;
    private boolean isGeneratingDesktop = false;
    private boolean hasGeneratedCorridors = false;
    private int interiorGenerationCooldown = 0;
    private BlockPos corridorAirlockCenter = BlockPos.ZERO;
    private DesktopTheme preparedTheme, currentTheme = TardisDesktops.DEFAULT_OVERGROWN_THEME;
    // Airlock systems.
    private boolean processingWarping = false;
    private int airlockCountdownSeconds = 3;
    private int airlockTimerSeconds = 5;
    private HumEntry humEntry = TardisHums.getDefaultHum();
    private double fuelForIntChange = 100; // The amount of fuel required to change interior

    public TardisInteriorManager(TardisLevelOperator operator) {
        this.operator = operator;
    }

    public DesktopTheme preparedTheme() {
        return preparedTheme;
    }

    public boolean isGeneratingDesktop() {
        return this.isGeneratingDesktop;
    }

    public boolean isWaitingToGenerate() {
        return this.isWaitingToGenerate;
    }

    public int getInteriorGenerationCooldown() {
        return this.interiorGenerationCooldown / 20;
    }

    public ProtectedZone[] unbreakableZones() {

        if (!hasGeneratedCorridors || corridorAirlockCenter == null) return new ProtectedZone[]{};

        ProtectedZone ctrlRoomAirlck = new ProtectedZone(corridorAirlockCenter.below(2).north(2).west(3), corridorAirlockCenter.south(3).east(3).above(5), "control_room_airlock");
        ProtectedZone hubAirlck = new ProtectedZone(STATIC_CORRIDOR_POSITION.below(2).north(2).west(3), STATIC_CORRIDOR_POSITION.south(3).east(3).above(6), "hub_airlock");
        ProtectedZone arsRoom = new ProtectedZone(new BlockPos(1051, 97, 6), new BlockPos(1023, 118, 36), "ars_room");

        return new ProtectedZone[]{ctrlRoomAirlck, hubAirlck, arsRoom};
    }

    /**
     * Gets the @{@link DesktopTheme} which is currently used by this Tardis
     */
    public DesktopTheme currentTheme() {
        return this.currentTheme;
    }

    /**
     * Updates the current @{@link DesktopTheme}.
     *
     * @implNote Should only be used when we are preparing to start a Desktop change
     */
    public TardisInteriorManager setCurrentTheme(DesktopTheme currentTheme) {
        this.currentTheme = currentTheme;
        return this;
    }

    public boolean isCave() {
        return currentTheme == TardisDesktops.DEFAULT_OVERGROWN_THEME;
    }

    public HumEntry getHumEntry() {
        return humEntry;
    }

    public void setHumEntry(HumEntry humEntry) {
        this.humEntry = humEntry;
    }

    @Override
    public CompoundTag saveData(CompoundTag tag) {
        tag.putBoolean(NbtConstants.TARDIS_IM_IS_WAITING_TO_GENERATE, this.isWaitingToGenerate);
        tag.putBoolean(NbtConstants.TARDIS_IM_GENERATING_DESKTOP, this.isGeneratingDesktop);
        tag.putInt(NbtConstants.TARDIS_IM_GENERATION_COOLDOWN, this.interiorGenerationCooldown);
        tag.putBoolean(NbtConstants.TARDIS_IM_GENERATED_CORRIDORS, this.hasGeneratedCorridors);

        if (this.corridorAirlockCenter != null) {
            tag.put(NbtConstants.TARDIS_IM_AIRLOCK_CENTER, NbtUtils.writeBlockPos(this.corridorAirlockCenter));
        }


        tag.putString(NbtConstants.TARDIS_IM_PREPARED_THEME, this.preparedTheme != null ? this.preparedTheme.getIdentifier().toString() : "");
        if (currentTheme != null) {
            tag.putString(NbtConstants.TARDIS_IM_CURRENT_THEME, this.currentTheme.getIdentifier().toString());
        }
        tag.putString(NbtConstants.TARDIS_CURRENT_HUM, this.humEntry.getIdentifier().toString());

        tag.putDouble(NbtConstants.TARDIS_IM_FUEL_FOR_INT_CHANGE, this.fuelForIntChange);

        return tag;
    }

    @Override
    public void loadData(CompoundTag tag) {
        this.isWaitingToGenerate = tag.getBoolean(NbtConstants.TARDIS_IM_IS_WAITING_TO_GENERATE);
        this.isGeneratingDesktop = tag.getBoolean(NbtConstants.TARDIS_IM_GENERATING_DESKTOP);
        this.interiorGenerationCooldown = tag.getInt(NbtConstants.TARDIS_IM_GENERATION_COOLDOWN);
        this.hasGeneratedCorridors = tag.getBoolean(NbtConstants.TARDIS_IM_GENERATED_CORRIDORS);
        this.preparedTheme = TardisDesktops.getDesktopById(new ResourceLocation(tag.getString(NbtConstants.TARDIS_IM_PREPARED_THEME)));
        this.currentTheme = tag.contains(NbtConstants.TARDIS_IM_CURRENT_THEME) ? TardisDesktops.getDesktopById(new ResourceLocation((NbtConstants.TARDIS_IM_CURRENT_THEME))) : preparedTheme;
        this.corridorAirlockCenter = NbtUtils.readBlockPos(tag.getCompound(NbtConstants.TARDIS_IM_AIRLOCK_CENTER));
        this.humEntry = TardisHums.getHumById(new ResourceLocation(tag.getString(NbtConstants.TARDIS_CURRENT_HUM)));

        this.fuelForIntChange = tag.getDouble(NbtConstants.TARDIS_IM_FUEL_FOR_INT_CHANGE);
        if (!tag.contains(NbtConstants.TARDIS_IM_FUEL_FOR_INT_CHANGE)) {
            this.fuelForIntChange = 500; // Default
        }
    }

    @Override
    public void tick(ServerLevel level) {

        RandomSource rand = level.getRandom();

        if (shouldTheEyeBeOpen(level)) {
            this.openTheEye();

        }

        TardisExteriorManager exteriorManager = this.operator.getExteriorManager();
        if (exteriorManager == null) {
            return;
        }
        TardisPilotingManager pilotingManager = this.operator.getPilotingManager();
        if (pilotingManager == null) {
            return;
        }

        this.handleDesktopGeneration(level);

        /// Airlock Logic

        // Check if a player is in the radius of either airlock points
        if (!processingWarping) {
            if (level.getGameTime() % 20 == 0) {
                // Dynamic desktop position.
                List<LivingEntity> desktopEntities = getAirlockEntities(level);
                List<LivingEntity> corridorEntities = getCorridorEntities(level);

                if (!desktopEntities.isEmpty() || !corridorEntities.isEmpty()) {
                    airlockCountdownSeconds--;
                    if (airlockCountdownSeconds <= 0) {

                        this.processingWarping = true;
                        airlockCountdownSeconds = 10;
                        this.airlockTimerSeconds = 0;

                        // Lock the doors.
                        BlockPos desktopDoorPos = corridorAirlockCenter.north(2);
                        if (level.getBlockEntity(desktopDoorPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                            bulkHeadDoorBlockEntity.toggleDoor(level, desktopDoorPos, level.getBlockState(desktopDoorPos), false);
                            level.setBlock(desktopDoorPos, level.getBlockState(desktopDoorPos).setValue(BulkHeadDoorBlock.LOCKED, true), Block.UPDATE_CLIENTS);
                        }

                        BlockPos corridorDoorBlockPos = TardisDimensionConstants.CORRIDOR_AIRLOCK_DOOR_POS;
                        if (level.getBlockEntity(corridorDoorBlockPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                            bulkHeadDoorBlockEntity.toggleDoor(level, corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos), false);
                            level.setBlock(corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos).setValue(BulkHeadDoorBlock.LOCKED, true), Block.UPDATE_CLIENTS);
                        }
                    }
                } else {
                    this.processingWarping = false;
                    this.airlockCountdownSeconds = 3;
                    this.airlockTimerSeconds = 0;
                }

            }
        }

        if (processingWarping) {
            if (level.getGameTime() % 20 == 0) {

                for (ProtectedZone protectedZone : unbreakableZones()) {
                    if (!protectedZone.getName().contains("_airlock")) continue;
                    BlockPos.betweenClosedStream(protectedZone.getArea()).forEach(position -> {
                        double velocityX = (rand.nextDouble() - 0.5) * 0.02;
                        double velocityY = (rand.nextDouble() - 0.5) * 0.02;
                        double velocityZ = (rand.nextDouble() - 0.5) * 0.02;

                        level.sendParticles(ParticleTypes.CLOUD, position.getX(), position.getY(), position.getZ(), 2, velocityX, velocityY, velocityZ, velocityZ);
                    });
                }


                if (airlockTimerSeconds == 1) {
                    level.playSound(null, corridorAirlockCenter, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 5, 0.25f);
                    level.playSound(null, STATIC_CORRIDOR_POSITION, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 5, 0.25f);
                }

                if (airlockTimerSeconds == 3) {
                    List<LivingEntity> desktopEntities = getAirlockEntities(level);
                    List<LivingEntity> corridorEntities = getCorridorEntities(level);

                    desktopEntities.forEach(x -> {
                        Vec3 offsetPos = x.position().subtract(Vec3.atCenterOf(corridorAirlockCenter));
                        TardisTeleportData.scheduleEntityTeleport(x, level.dimension(), STATIC_CORRIDOR_POSITION.getX() + offsetPos.x() + 0.5f, STATIC_CORRIDOR_POSITION.getY() + offsetPos.y() + 0.5f, STATIC_CORRIDOR_POSITION.getZ() + offsetPos.z() + 0.5f, x.getYRot(), x.getXRot());
                    });

                    corridorEntities.forEach(x -> {
                        Vec3 offsetPos = x.position().subtract(Vec3.atCenterOf(STATIC_CORRIDOR_POSITION));
                        TardisTeleportData.scheduleEntityTeleport(x, level.dimension(), corridorAirlockCenter.getX() + offsetPos.x() + 0.5f, corridorAirlockCenter.getY() + offsetPos.y() + 0.5f, corridorAirlockCenter.getZ() + offsetPos.z() + 0.5f, x.getYRot(), x.getXRot());
                    });
                }

                if (airlockTimerSeconds == 5) {
                    this.processingWarping = false;
                    this.airlockTimerSeconds = 20;
                    BlockPos desktopDoorPos = corridorAirlockCenter.north(2);
                    if (level.getBlockEntity(desktopDoorPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                        bulkHeadDoorBlockEntity.toggleDoor(level, desktopDoorPos, level.getBlockState(desktopDoorPos), true);
                        level.setBlock(desktopDoorPos, level.getBlockState(desktopDoorPos).setValue(BulkHeadDoorBlock.LOCKED, false), Block.UPDATE_CLIENTS);
                    }

                    BlockPos corridorDoorBlockPos = TardisDimensionConstants.CORRIDOR_AIRLOCK_DOOR_POS;
                    if (level.getBlockEntity(corridorDoorBlockPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                        bulkHeadDoorBlockEntity.toggleDoor(level, corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos), true);
                        level.setBlock(corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos).setValue(BulkHeadDoorBlock.LOCKED, false), Block.UPDATE_CLIENTS);
                    }
                }


                airlockTimerSeconds++;
            }
        }
    }

    public boolean shouldTheEyeBeOpen(ServerLevel level) {

        return level.getBlockState(pillarTopLeft).getBlock() == TRBlockRegistry.ARTRON_PILLAR.get() && level.getBlockState(pillarTopRight).getBlock() == TRBlockRegistry.ARTRON_PILLAR.get() && level.getBlockState(pillarBottomLeft).getBlock() == TRBlockRegistry.ARTRON_PILLAR.get() && level.getBlockState(pillarBottomRight).getBlock() == TRBlockRegistry.ARTRON_PILLAR.get() && operator.getTardisState() != TardisLevelOperator.STATE_EYE_OF_HARMONY;
    }

    public void openTheEye() {
        openTheEye(false);
    }

    public void setEyePillars(Level level) {
        level.setBlock(pillarTopLeft, TRBlockRegistry.ARTRON_PILLAR.get().defaultBlockState(), Block.UPDATE_ALL);
        level.setBlock(pillarTopRight, TRBlockRegistry.ARTRON_PILLAR.get().defaultBlockState(), Block.UPDATE_ALL);
        level.setBlock(pillarBottomLeft, TRBlockRegistry.ARTRON_PILLAR.get().defaultBlockState(), Block.UPDATE_ALL);
        level.setBlock(pillarBottomRight, TRBlockRegistry.ARTRON_PILLAR.get().defaultBlockState(), Block.UPDATE_ALL);
    }

    public void openTheEye(boolean forced) {

        Level level = this.operator.getLevel();
        this.operator.setTardisState(TardisLevelOperator.STATE_EYE_OF_HARMONY);

        Vec3 eyeCenter = new Vec3(1013, 72, 55);
        AABB portalDoorLength = new AABB(1011, 72, 54, 1015, 71, 56);
        AABB portalDoorWidth = new AABB(1014, 71, 57, 1012, 72, 53);

        if (forced) {
            this.setEyePillars(level);
        }

        // Remove the blocks
        BlockPos.betweenClosedStream(portalDoorLength).forEach(x -> level.setBlock(x, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL));
        BlockPos.betweenClosedStream(portalDoorWidth).forEach(x -> level.setBlock(x, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL));

        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, this.operator.getLevel());
        lightningBolt.setPos(eyeCenter);
        this.operator.getLevel().addFreshEntity(lightningBolt);

        setHumEntry(TardisHums.getDefaultHum());

    }

    public List<LivingEntity> getCorridorEntities(Level level) {
        return level.getEntitiesOfClass(LivingEntity.class, new AABB(STATIC_CORRIDOR_POSITION.north(2).west(2), STATIC_CORRIDOR_POSITION.south(2).east(2).above(4)));
    }

    public List<LivingEntity> getAirlockEntities(Level level) {

        if (corridorAirlockCenter == null) {
            return new ArrayList<>();
        }

        return level.getEntitiesOfClass(LivingEntity.class, new AABB(corridorAirlockCenter.north(2).west(2), corridorAirlockCenter.south(2).east(2).above(4)));
    }

    public boolean isInAirlock(LivingEntity livingEntity) {

        if (!hasGeneratedCorridors) return false;

        List<LivingEntity> airlock = getAirlockEntities(livingEntity.level());
        List<LivingEntity> corridor = getCorridorEntities(livingEntity.level());

        return airlock.contains(livingEntity) || corridor.contains(livingEntity);
    }

    public BlockPos getCorridorAirlockCenter() {
        return this.corridorAirlockCenter;
    }

    public void setCorridorAirlockCenter(BlockPos center) {
        this.corridorAirlockCenter = center;
    }

    /**
     * Master logic that schedules the desktop preparation, generation and aesthetic effects in one place
     * <br> Should be called in the {@link TardisInteriorManager#tick()}
     */
    public void handleDesktopGeneration(ServerLevel level) {
        if (this.isWaitingToGenerate) {
            if (level.random.nextInt(30) == 0) {
                level.playSound(null, TardisArchitectureHandler.DESKTOP_CENTER_POS, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 5.0F + level.random.nextFloat(), level.random.nextFloat() * 0.7F + 0.3F);
            }

            if (level.random.nextInt(100) == 0) {
                level.playSound(null, TardisArchitectureHandler.DESKTOP_CENTER_POS, SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 15.0F + level.random.nextFloat(), 0.1f);
            }
            //This check doesn't actually work for players that respawn, login or teleport to the Tardis dimension when the Tardis is waiting to generate because our tick method is being called at the start of the server tick.
            //To mitigate the problem where players become stuck inside the stone and suffocate to death, we call TardisLevelOperator#ejectPlayer in the relevant Events.
            if (level.players().isEmpty()) {
                if (this.operator.triggerRegenState(true)) { //Make sure we actually triggered the regen state before thinking we are good to go
                    this.operator.forceEjectAllPlayers(); //Teleport all players to the exterior in case they still remain.
                    TardisCommonEvents.DESKTOP_CHANGE_EVENT.invoker().onDesktopChange(operator);
                    this.generateDesktop(this.preparedTheme); //During desktop generation, if the state is still the initial cave state, we will update it to terraformed but no eye activated

                    this.isWaitingToGenerate = false;
                    this.isGeneratingDesktop = true;
                }
            }
        }

        if (this.isGeneratingDesktop) {

            if (!level.isClientSide()) {
                interiorGenerationCooldown--;
            }

            if (interiorGenerationCooldown == 0) {
                if (this.operator.triggerRegenState(false)) //Make sure we actually triggered the regen state before saying we are good to go.
                    this.isGeneratingDesktop = false;
            }

            if (level.getGameTime() % 60 == 0) {
                this.operator.getExteriorManager().playSoundAtShell(SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 1.0F + operator.getLevel().getRandom().nextFloat(), 0.1f);
            }
        }
    }

    /**
     * Performs the desktop generation tasks such as block removal and placement tasks
     */
    public void generateDesktop(DesktopTheme theme) {

        if (operator.getLevel() instanceof ServerLevel serverLevel) {

            if (this.operator.getTardisState() == TardisLevelOperator.STATE_CAVE) { //If transforming from root shell to half baked Tardis, set the state to terraformed but no eye activated
                this.operator.setTardisState(TardisLevelOperator.STATE_TERRAFORMED_NO_EYE);
            }

            // Remove Tardis Interior Door
            TardisInternalDoor tardisInternalDoor = this.operator.getInternalDoor();
            if (tardisInternalDoor != null) {
                serverLevel.removeBlock(tardisInternalDoor.getDoorPosition(), false);
            }

            this.hasGeneratedCorridors = true;

            // Generate Desktop Interior
            TardisArchitectureHandler.generateDesktop(serverLevel, theme);
            setCurrentTheme(theme);

        }
    }

    public boolean isHasGeneratedCorridors() {
        return hasGeneratedCorridors;
    }

    public void setHasGeneratedCorridors(boolean hasGeneratedCorridors) {
        this.hasGeneratedCorridors = hasGeneratedCorridors;
    }

    /**
     * Prepares the Tardis for desktop generation but doesn't actually start it. Handles cooldowns etc.
     */
    public void prepareDesktop(DesktopTheme theme) {
        this.preparedTheme = theme;
        this.isWaitingToGenerate = true;

        // Cooldown based on upgrades
        int cooldownSeconds = 180;

        UpgradeHandler upgradeHandler = this.operator.getUpgradeHandler();

        if (upgradeHandler.isUpgradeUnlocked(TRUpgrades.IMPROVED_GENERATION_TIME_I.get())) {
            cooldownSeconds = 120;
        }

        if (upgradeHandler.isUpgradeUnlocked(TRUpgrades.IMPROVED_GENERATION_TIME_II.get())) {
            cooldownSeconds = 30;
        }

        if (upgradeHandler.isUpgradeUnlocked(TRUpgrades.IMPROVED_GENERATION_TIME_III.get())) {
            cooldownSeconds = 10;
        }

        this.interiorGenerationCooldown = 20 * cooldownSeconds;
    }

    public void cancelDesktopChange() {
        this.preparedTheme = null;
        this.isWaitingToGenerate = false;
    }

    /**
     * Returns whether a Tardis has enough fuel to perform an interior change
     *
     * @return true if the Tardis has enough fuel
     */
    public boolean hasEnoughFuel() {
        return this.operator.getPilotingManager().getFuel() >= this.getRequiredFuel();
    }

    /**
     * The amount of fuel required to change the interior
     *
     * @return double amount of fuel to be removed
     */
    public double getRequiredFuel() {
        return this.fuelForIntChange;
    }

    /**
     * Sets the amount of fuel required to change the interior
     *
     * @param fuel the amount of fuel
     */
    private void setRequiredFuel(double fuel) {
        this.fuelForIntChange = fuel;
    }
}
