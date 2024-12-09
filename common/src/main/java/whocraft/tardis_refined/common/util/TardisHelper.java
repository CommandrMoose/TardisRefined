package whocraft.tardis_refined.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.api.event.ShellChangeSources;
import whocraft.tardis_refined.api.event.TardisCommonEvents;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.block.shell.ShellBaseBlock;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.dimension.TardisTeleportData;
import whocraft.tardis_refined.common.tardis.TardisArchitectureHandler;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisExteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.mixin.EndDragonFightAccessor;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static whocraft.tardis_refined.common.block.shell.ShellBaseBlock.LOCKED;
import static whocraft.tardis_refined.constants.TardisDimensionConstants.ARS_TREE_CORNER_A;
import static whocraft.tardis_refined.constants.TardisDimensionConstants.ARS_TREE_CORNER_B;

public class TardisHelper {

    public static void playCloisterBell(TardisLevelOperator tardisLevelOperator) {
        for (int i = 0; i < 3; i++) {
            tardisLevelOperator.getLevel().playSound(null, TardisArchitectureHandler.DESKTOP_CENTER_POS, SoundEvents.BELL_BLOCK, SoundSource.BLOCKS, 1000f, 0.1f);
        }
    }

    public static List<Player> getPlayersInRange(Level world, double x, double y, double z, double range) {
        // Create a bounding box around the specified position
        AABB boundingBox = new AABB(
                x - range, y - range, z - range,
                x + range, y + range, z + range
        );

        // Get all players within the bounding box
        return world.getEntitiesOfClass(Player.class, boundingBox);
    }

    public static List<Player> getPlayersInRange(Level world, Player sourcePlayer, double range) {
        return getPlayersInRange(world, sourcePlayer.getX(), sourcePlayer.getY(), sourcePlayer.getZ(), range);
    }

    public static boolean isInArsArea(BlockPos blockPos) {

        BlockPos corner1 = ARS_TREE_CORNER_A;
        BlockPos corner2 = ARS_TREE_CORNER_B;

        int minX = Math.min(corner1.getX(), corner2.getX());
        int maxX = Math.max(corner1.getX(), corner2.getX());

        int minY = Math.min(corner1.getY(), corner2.getY());
        int maxY = Math.max(corner1.getY(), corner2.getY());

        int minZ = Math.min(corner1.getZ(), corner2.getZ());
        int maxZ = Math.max(corner1.getZ(), corner2.getZ());

        return blockPos.getX() >= minX && blockPos.getX() <= maxX && blockPos.getY() >= minY && blockPos.getY() <= maxY && blockPos.getZ() >= minZ && blockPos.getZ() <= maxZ;
    }

    public static boolean createTardis(BlockPos blockPos, ServerLevel serverLevel, ResourceKey<Level> generatedLevelKey, ResourceLocation shellTheme, DesktopTheme desktopTheme) {

        AtomicBoolean generated = new AtomicBoolean(false);

        //Set global shell block
        BlockState targetBlockState = TRBlockRegistry.GLOBAL_SHELL_BLOCK.get().defaultBlockState().setValue(GlobalShellBlock.FACING, Direction.NORTH).setValue(GlobalShellBlock.REGEN, false).setValue(LOCKED, false).setValue(GlobalShellBlock.WATERLOGGED, serverLevel.getBlockState(blockPos).getFluidState().getType() == Fluids.WATER);

        serverLevel.setBlock(blockPos, targetBlockState, Block.UPDATE_ALL);

        if (serverLevel.getBlockEntity(blockPos) instanceof GlobalShellBlockEntity shellBaseBlockEntity) {
            if (shellBaseBlockEntity.shouldSetup()) {

                //Set the shell with this level
                shellBaseBlockEntity.setTardisId(generatedLevelKey);

                //Create the Level on demand which will create our capability
                ServerLevel interior = DimensionHandler.getOrCreateInterior(serverLevel, shellBaseBlockEntity.getTardisId().location());

                TardisLevelOperator.get(interior).ifPresent(tardisLevelOperator -> {
                    TardisInteriorManager intManager = tardisLevelOperator.getInteriorManager();
                    TardisExteriorManager extManager = tardisLevelOperator.getExteriorManager();
                    TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
                    if (!tardisLevelOperator.hasInitiallyGenerated()) {
                        intManager.generateDesktop(desktopTheme);
                        tardisLevelOperator.getProgressionManager().addDiscoveredLevel(serverLevel.dimension());
                        Direction direction = targetBlockState.getValue(ShellBaseBlock.FACING).getOpposite();
                        TardisNavLocation navLocation = new TardisNavLocation(blockPos, direction, serverLevel);
                        pilotManager.setCurrentLocation(navLocation);
                        pilotManager.setTargetLocation(navLocation);
                        pilotManager.setFuel(pilotManager.getMaximumFuel());
                        tardisLevelOperator.setInitiallyGenerated(true);
                        tardisLevelOperator.setTardisState(TardisLevelOperator.STATE_EYE_OF_HARMONY);
                        intManager.openTheEye(true);
                        serverLevel.setBlock(blockPos, targetBlockState.setValue(ShellBaseBlock.OPEN, true), Block.UPDATE_ALL);
                        generated.set(true);
                        tardisLevelOperator.setShellTheme(shellTheme, ShellPatterns.getPatternsForTheme(shellTheme).get(0).id(), ShellChangeSources.ROOT_TO_TARDIS);
                        tardisLevelOperator.setOrUpdateExteriorBlock(navLocation, Optional.of(targetBlockState), false, ShellChangeSources.ROOT_TO_TARDIS);
                    }
                });

                return generated.get();
            }
        }
        return false;

    }

    public static MutableComponent createTardisIdComponent(ResourceLocation levelId) {
        String id = levelId.toString();
        String displayId = levelId.getPath().substring(0, 5);
        MutableComponent tardisId = CommandHelper.createComponentWithTooltip(displayId, id);
        return tardisId;
    }

    public static boolean teleportEntityTardis(TardisLevelOperator cap, Entity entity, TardisNavLocation sourceLocation, TardisNavLocation destinationLocation, boolean enterTardis) {

        if (entity.level() instanceof ServerLevel teleportingEntityLevel) {

            BlockPos destinationPos = destinationLocation.getPosition();
            ServerLevel destinationLevel = destinationLocation.getLevel();
            Direction destinationDirection = destinationLocation.getDirection(); //Do not use the opposite facing of the destination, we will handle the correct facing outside of this method.

            Direction sourceDirection = sourceLocation.getDirection();


            BlockPos targetTeleportPos = destinationPos;

            /**If for some reason we are trying to enter the Tardis, but the destination dimension is not a Tardis dimension type, don't teleport
             This can occur if the exterior shell we are entering has an invalid {@link whocraft.tardis_refined.common.blockentity.shell.ShellBaseBlockEntity#TARDIS_ID} which will occur for older releases due to a bug that was present until 2.0.2
             */
            if (enterTardis && destinationLevel.dimensionTypeId() != TRDimensionTypes.TARDIS) {
                return false;
            }

            //Calculate entity motion and rotation, taking into account for the internal door's direction and rotation
            float entityYRot = entity.getYRot();
            float destinationRotationYaw = destinationDirection.toYRot();
            float sourceRotationYaw = sourceDirection.toYRot();
            //Calculate the difference between the entity's rotation and the source direction's rotation. Get the difference and find the final rotation that preserves the entities' rotation but facing the direction at the destination
            float diff = LevelHelper.getAdjustedRotation(entityYRot) - LevelHelper.getAdjustedRotation(sourceRotationYaw);

            float adjustedRotationYaw = destinationRotationYaw + diff;

            if (entity.getType().getDimensions().width > 1F) {
                targetTeleportPos = destinationPos.offset(destinationDirection.getNormal());
            }

            BlockPos finalTeleportPos = targetTeleportPos;

            Vec3 centredTarget = LevelHelper.centerPos(finalTeleportPos, false);

            TardisTeleportData.scheduleEntityTeleport(entity, destinationLevel.dimension(), centredTarget.x(), centredTarget.y(), centredTarget.z(), adjustedRotationYaw, entity.getXRot());

            //Fire exit or enter events
            if (entity instanceof LivingEntity livingEntity) {
                if (enterTardis) {
                    TardisCommonEvents.TARDIS_ENTRY_EVENT.invoker().onEnterTardis(cap, livingEntity, sourceLocation, destinationLocation);
                } else {
                    TardisCommonEvents.TARDIS_EXIT_EVENT.invoker().onExitTardis(cap, livingEntity, sourceLocation, destinationLocation);
                }
            }

            cap.tardisClientData().sync();

            return true;
        }
        return false;

    }

    public static boolean hasTheEndBeenCompleted(ServerLevel serverLevel) {

        if (serverLevel.dimension() == Level.END) {
            if (serverLevel.getDragonFight() != null) {
                return ((EndDragonFightAccessor) serverLevel.getDragonFight()).isDragonKilled();
            }

            return false; // Better safe than sorry.
        }

        return false;
    }

    /**
     * Common logic that we should apply to players if they happen to teleport to, respawn, or login to a Tardis
     * <br> Ejecting players that happen to login to a Tardis dimension whilst the Tardis is still generating a desktop, we don't want them to suffocate
     */
    public static void handlePlayerJoinWorldEvents(ServerPlayer serverPlayer) {
        if (serverPlayer != null) {
            if (serverPlayer.serverLevel() != null) {
                ServerLevel playerLevel = serverPlayer.serverLevel();
                if (TardisLevelOperator.get(playerLevel).isPresent()) {
                    TardisLevelOperator cap = TardisLevelOperator.get(playerLevel).get();

                    //Handle ejecting players if they login to a Tardis dimension where the Tardis is in progress of generating a desktop
                    if (cap.getInteriorManager().isGeneratingDesktop()) {
                        //Delay the force ejecting to account for the chunk not being fully loaded, which can happen in the player change dimension event.
                        playerLevel.getServer().tell(new TickTask(10, () ->
                                cap.forceEjectPlayer(serverPlayer)
                        ));
                    }

                }
            }
        }
    }

}
