package whocraft.tardis_refined.common.util;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortals;
import whocraft.tardis_refined.registry.TRTagKeys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TRTeleporter {

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/TRTeleporter");

    /**
     * Variant of teleport method where there is no need to track teleported entities
     *
     * @param pEntity
     * @param pLevel
     * @param pX
     * @param pY
     * @param pZ
     * @param pYaw
     * @param pPitch
     * @return
     */
    public static boolean simpleTeleport(Entity pEntity, ServerLevel pLevel, double pX, double pY, double pZ, float pYaw, float pPitch) {
        return performTeleport(pEntity, pLevel, pX, pY, pZ, pYaw, pPitch, false, new HashSet<>());
    }

    /**
     * @param pEntity
     * @param pLevel
     * @param pX
     * @param pY
     * @param pZ
     * @param pYaw
     * @param pPitch
     * @param teleportedEntities - Set of entities that were teleported. Used to track teleported entities to prevent duplicate teleports
     * @return
     */
    public static boolean fullTeleport(Entity pEntity, ServerLevel pLevel, double pX, double pY, double pZ, float pYaw, float pPitch, Set<Entity> teleportedEntities) {
        return performTeleport(pEntity, pLevel, pX, pY, pZ, pYaw, pPitch, false, teleportedEntities);
    }

    /**
     * Intermediate teleport logic that adds in sanity checks and tracks the teleported entities
     *
     * @param pEntity
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param pYaw
     * @param pPitch
     * @param safeBlockCheck
     * @param teleportedEntities
     * @return
     */
    private static boolean performTeleport(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ, float pYaw, float pPitch, boolean safeBlockCheck, Set<Entity> teleportedEntities) {
        Preconditions.checkNotNull(destination, "A target level must be provided for teleportation");
        Preconditions.checkState(!pEntity.level().isClientSide(), "Entities can only be teleported on the server side");

        float updatedYRot = Mth.wrapDegrees(pYaw);
        float updatedXRot = Mth.wrapDegrees(pPitch);

        if (ModCompatChecker.immersivePortals()) {
            pEntity.setYRot(updatedYRot); //Set the desired yRot and xRot before teleportation. For non-players, this means the facing is copied over to the copy of the entity which we recreate. For players, it should update the rotation with the correct facing at the destination
            pEntity.setXRot(updatedXRot);
            ImmersivePortals.teleportViaIp(pEntity, destination, pX, pY, pZ);
            return true;
        }

        if (!pEntity.level().isClientSide()) {
            if (safetyCheck(pEntity, destination, pX, pY, pZ, safeBlockCheck, teleportedEntities)) {
                Entity teleportedEntity;

                //Teleport this entity regardless if it's a vehicle or passenger
                teleportedEntity = teleportLogicCommon(pEntity, destination, pX, pY, pZ, updatedYRot, updatedXRot);

                if (teleportedEntity != null) {

                    // update teleported entities set to keep track of entities teleported. Check if the list already contains our entity because this will be modified by the TardisTeleportData too
                    if (!teleportedEntities.contains(pEntity))
                        teleportedEntities.add(pEntity);

                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Common logic that consolidates both player specific and non-player teleportation logic.
     * <br> Handles post-teleportation logic that is common to all teleported entities
     *
     * @param pEntity
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param pYaw
     * @param pPitch
     * @return
     */
    private static Entity teleportLogicCommon(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ, float pYaw, float pPitch) {

        pEntity.setDeltaMovement(Vec3.ZERO);

        Entity teleportedEntity;
        if (pEntity instanceof ServerPlayer serverPlayer) {
            //Always update player data, do not create new instances of the player
            teleportedEntity = teleportPlayer(serverPlayer, destination, pX, pY, pZ, pYaw, pPitch);

        } else { //If not a player
            teleportedEntity = teleportNonPlayerEntity(pEntity, destination, pX, pY, pZ, pYaw, pPitch);
        }

        if (teleportedEntity != null) {
            return teleportedEntity;
        }
        return null;
    }

    private static Entity postTeleportCommon(Entity teleportedEntity, ServerLevel destination, double pX, double pY, double pZ) {
        ChunkPos chunkpos = new ChunkPos(BlockPos.containing(pX, pY, pZ));
        destination.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, teleportedEntity.getId());

        //Handle Elytra flying - Logic matches vanilla TeleportCommand
        if ((teleportedEntity instanceof LivingEntity livingEntity)) {
            if (!livingEntity.isFallFlying()) {
                teleportedEntity.setOnGround(true);
            }
        }

        if (teleportedEntity instanceof PathfinderMob) {
            ((PathfinderMob) teleportedEntity).getNavigation().stop();
        }

        //Handle active potion effects
        if (teleportedEntity instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof ServerPlayer serverPlayer) {
                for (MobEffectInstance effectInstance : new ArrayList<>(livingEntity.getActiveEffects())) {
                    serverPlayer.connection.send(new ClientboundUpdateMobEffectPacket(serverPlayer.getId(), effectInstance));
                }
            } else {
                reAddStatusEffectTempFix(livingEntity);
            }
        }

        ((ServerLevel) teleportedEntity.level()).resetEmptyTime();
        destination.resetEmptyTime();

        return teleportedEntity;
    }

    /**
     * Common logic that consolidates the player specific teleport logic
     *
     * @param serverPlayer
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param updatedYRot
     * @param updatedXRot
     * @return
     */
    private static ServerPlayer teleportPlayer(ServerPlayer serverPlayer, ServerLevel destination, double pX, double pY, double pZ, float updatedYRot, float updatedXRot) {
        if (serverPlayer.isSleeping()) {
            serverPlayer.stopSleepInBed(true, true);
        }

        Entity vehicle = serverPlayer.getVehicle();

        serverPlayer.setDeltaMovement(Vec3.ZERO); //set velocity to 0 because otherwise we will trigger the "player moved wrongly" hardcoded vanilla check which will result in the player not changing coordinates in the new dimension

        if (destination == serverPlayer.level()) {
            serverPlayer.connection.teleport(pX, pY, pZ, updatedYRot, updatedXRot); //Must update the player position via packets as opposed to raw setPos
        } else {
            serverPlayer = teleportPlayerOtherDimension(serverPlayer, destination, pX, pY, pZ, updatedYRot, updatedXRot);
        }

        //Handle vehicle teleport if the player was riding a vehicle
        if (vehicle != null) {

            ServerPlayer teleportedPlayer = serverPlayer;

            double vehicleOffset = teleportedPlayer.getMyRidingOffset(vehicle);

            teleportedPlayer.stopRiding(); //Dismount the player from riding the vehicle

            Vec3 vehicleOffsetVec = new Vec3(0, vehicleOffset, 0);
            Vec3 newVehiclePos = teleportedPlayer.position().add(vehicleOffsetVec);
            Vec3 newVehicleLastTickPos = lastTickPosOf(teleportedPlayer).add(vehicleOffsetVec);

            Entity teleportedVehicle;

            //If for some reason the vehicle is actually a player (Can occur if the multiplayer server adds a plugin that allows players to mount other players)
            if (vehicle instanceof ServerPlayer vehiclePlayer) {
                vehiclePlayer.teleportTo(destination, pX, pY, pZ, updatedYRot, updatedXRot);
                teleportedVehicle = vehiclePlayer;
            } else { //Normally the vehicle is a non-player entity so we will handle it as an extra non-player entity teleport
                teleportedVehicle = teleportNonPlayerEntity(vehicle, destination, pX, pY, pZ, vehicle.getYRot(), vehicle.getXRot());
                if (teleportedVehicle != null)
                    updatePosAndLastTickPos(teleportedVehicle, newVehiclePos, newVehicleLastTickPos);
            }

            destination.getServer().tell(new TickTask(10, () -> teleportedPlayer.startRiding(teleportedVehicle, true))); //Remount the vehicle after teleporting the vehicle to the player across dimensions

            reSyncVehicleToPassengerPos(serverPlayer);
        }

        //Make sure vehicle is near the player so that the player can remount it
        //The method already handles if the vehicle is null so no need to perform an extra check
        reSyncVehicleToPassengerPos(serverPlayer);

        serverPlayer.connection.resetPosition();
        serverPlayer.setPortalCooldown(); //Prevent player from being teleport by nether portal in the destination dimension
        serverPlayer.setYHeadRot(updatedYRot);
        serverPlayer.setYBodyRot(updatedYRot);

        serverPlayer.onUpdateAbilities();


        //Handle player experience not getting received on client after interdimensional teleport
        serverPlayer.connection.send(new ClientboundSetExperiencePacket(serverPlayer.experienceProgress, serverPlayer.totalExperience, serverPlayer.experienceLevel));
        serverPlayer.connection.send(new ClientboundPlayerAbilitiesPacket(serverPlayer.getAbilities()));//Keep abilities like creative mode flight height etc.
        serverPlayer.connection.send(new ClientboundChangeDifficultyPacket(serverPlayer.level().getDifficulty(), serverPlayer.level().getLevelData().isDifficultyLocked()));


        postTeleportCommon(serverPlayer, destination, pX, pY, pZ);

        return (ServerPlayer) destination.getPlayerByUUID(serverPlayer.getUUID());
    }

    /**
     * Handle cross-dimension teleportation for the player and teleportation of the vehicle that the player was riding prior to the player being teleported
     * <br> The player data is always updated during cross-dimension teleportation, whereas non-player entities require a new instance of the entity, with recreated data, to prevent entity AI from bugging out
     *
     * @param serverPlayer
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param updatedYRot
     * @param updatedXRot
     * @return
     */
    private static ServerPlayer teleportPlayerOtherDimension(ServerPlayer serverPlayer, ServerLevel destination, double pX, double pY, double pZ, float updatedYRot, float updatedXRot) {
        serverPlayer.teleportTo(destination, pX, pY, pZ, updatedYRot, updatedXRot);
        return serverPlayer;
    }

    /**
     * Master logic for Non player entity teleports. Accounts for when the entity is a vehicle and handles passenger teleportation
     *
     * @param pEntity
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param yRot
     * @param xRot
     * @return
     */
    private static Entity teleportNonPlayerEntity(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ, float yRot, float xRot) {

        //For non player entities, always create new instances if inter dimensional teleporting
        float adjustedXRot = Mth.clamp(xRot, -90.0F, 90.0F);
        Entity teleportedEntity = null;

        if (!safetyCheck(pEntity, destination, pX, pY, pZ, false, null)) {
            return null;
        }

        boolean handlePassengerTeleport = false;

        //Make sure this non-player entity isn't a player or if it is a vehicle, none if its passengers are players.
        //We will handle player passengers separately
        if (pEntity.isVehicle() || doesVehicleContainPlayer(pEntity)) {
            handlePassengerTeleport = true;
        }

        //If this entity is a vehicle, preserve metadata about its passengers and eject them
        List<Entity> passengers = pEntity.getPassengers();

        //If we had specified a vehicle entity to ride, start riding assuming we have already teleported
        if (handlePassengerTeleport) { //If one or more passengers, then this is a vehicle and we must teleport all passengers
            if (!passengers.isEmpty()) {
                pEntity.unRide();
                //Teleport the vehicle
                teleportedEntity = teleportNonPlayerEntityRegular(pEntity, destination, pX, pY, pZ, yRot, adjustedXRot);

                Entity finalTeleportedEntity = teleportedEntity;

                //Then, teleport each passenger, and remount them onto the vehicle
                passengers.stream().map(
                        passenger -> teleportPassengerForNonEntityDimensionTeleport(passenger, destination, pX, pY, pZ, yRot, adjustedXRot)
                ).collect(Collectors.toList()).forEach(teleportedPassenger -> {
                    if (teleportedPassenger != null) {
                        destination.getServer().tell(new TickTask(10, () -> teleportedPassenger.startRiding(finalTeleportedEntity, true)));
                    }
                });
            }
        } else {
            teleportedEntity = teleportNonPlayerEntityRegular(pEntity, destination, pX, pY, pZ, yRot, adjustedXRot);
        }

        return teleportedEntity;
    }

    /**
     * Common logic to consolidate non-player teleports
     *
     * @param pEntity
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param yRot
     * @param xRot
     * @return
     */
    private static Entity teleportNonPlayerEntityRegular(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ, float yRot, float xRot) {
        Preconditions.checkState(!pEntity.level().isClientSide(), "Entities can only be teleported on the server side");
        Entity teleportedEntity;
        pEntity.setDeltaMovement(Vec3.ZERO);
        //Teleport the current entity (which we know is a vehicle)
        if (destination == pEntity.level()) {
            teleportedEntity = teleportNonPlayerEntitySameDimension(pEntity, pX, pY, pZ, yRot, xRot);
        } else {
            teleportedEntity = teleportNonPlayerEntityOtherDimension(pEntity, destination, pX, pY, pZ, yRot, xRot);
        }

        teleportedEntity = postTeleportCommon(teleportedEntity, destination, pX, pY, pZ);

        return teleportedEntity;
    }

    /**
     * @param passenger
     * @param destination
     * @param pX
     * @param pY
     * @param pZ
     * @param yRot
     * @param xRot
     * @return
     */
    private static Entity teleportPassengerForNonEntityDimensionTeleport(Entity passenger, ServerLevel destination, double pX, double pY, double pZ, float yRot, float xRot) {
        Entity teleportedPassenger;
        if (passenger instanceof ServerPlayer serverPlayerPassenger) {
            //The player teleportation logic will automatically handle remounting after teleporting
            teleportedPassenger = teleportPlayer(serverPlayerPassenger, destination, pX, pY, pZ, yRot, xRot);
        } else {
            //Use the master logic for non-player teleports incase this passenger happens to be a vehicle as well
            teleportedPassenger = teleportNonPlayerEntity(passenger, destination, pX, pY, pZ, yRot, xRot);
        }
        return teleportedPassenger;
    }

    private static Entity teleportNonPlayerEntitySameDimension(Entity pEntity, double pX, double pY, double pZ, float yRot, float xRot) {
        pEntity.moveTo(pX, pY, pZ, yRot, xRot);
        pEntity.setYHeadRot(yRot);
        pEntity.setYBodyRot(yRot);
        pEntity.setPortalCooldown();
        return pEntity;
    }

    private static Entity teleportNonPlayerEntityOtherDimension(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ, float yRot, float xRot) {
        //Use most of this logic from Entity#changeDimension which is most suitable as it has most syncing logic. However, we want to exclude the use of PortalInfo as it plays the vanilla portal sounds
        Entity newEntity = pEntity;

        newEntity = newEntity.getType().create(destination);
        if (newEntity == null) {
            return null;
        }

        newEntity.restoreFrom(pEntity);
        newEntity.moveTo(pX, pY, pZ, yRot, xRot);
        newEntity.setYHeadRot(yRot);
        newEntity.setPortalCooldown();


        pEntity.remove(Entity.RemovalReason.CHANGED_DIMENSION);
        destination.addDuringTeleport(newEntity); //DO NOT call this before removing the entity, else the game thinks we are teleporting the entity before it is removed yet
        return newEntity;
    }

    /**
     * Helper to allow non-full blocks to properly detect if the player is inside its visual bounding box for teleportation, assuming we are using Block#entityInside as the trigger
     * <br> This is needed as by default, vanilla always considers a full block (1x1) area for Block#entityInside as valid, which we don't want, since our block's bounding box is not a full block
     *
     * @param serverLevel
     * @param blockPos
     * @param entity
     * @param teleportAABB
     * @return
     */
    public static boolean teleportIfCollided(ServerLevel serverLevel, BlockPos blockPos, Entity entity, AABB teleportAABB) {
        AABB entityBoundingBox = TRTeleporter.getBoundingBoxWithMovement(entity);
        double insideBlockExpansion = 1.0E-7D; //Hardcoded value replicates logic from Entity#checkInsideBlocks
        AABB inflatedEntityBoundingBox = entityBoundingBox.inflate(insideBlockExpansion);
        AABB inflatedTeleportBoundingBox = teleportAABB.inflate(insideBlockExpansion);
        if (inflatedTeleportBoundingBox.intersects(inflatedEntityBoundingBox)) {
            return true;
        }
        return false;
    }

    /**
     * Temporary fix until Mojang patches the bug that makes potion effect icons disappear when changing dimension.
     * To fix it ourselves, we remove the effect and re-add it to the entity.
     */
    private static void reAddStatusEffectTempFix(LivingEntity livingEntity) {
        //re-adds potion effects so the icon remains instead of disappearing when changing dimensions due to a bug
        ArrayList<MobEffectInstance> effectInstanceList = new ArrayList<>(livingEntity.getActiveEffects());
        for (int i = effectInstanceList.size() - 1; i >= 0; i--) {
            MobEffectInstance effectInstance = effectInstanceList.get(i);
            if (effectInstance != null) {
                livingEntity.removeEffect(effectInstance.getEffect());
                livingEntity.addEffect(
                        new MobEffectInstance(
                                effectInstance.getEffect(),
                                effectInstance.getDuration(),
                                effectInstance.getAmplifier(),
                                effectInstance.isAmbient(),
                                effectInstance.isVisible(),
                                effectInstance.showIcon()));
            }
        }
    }

    public static AABB getBoundingBoxWithMovement(Entity entity) {
        Vec3 backwardExpand = lastTickPosOf(entity).subtract(entity.position());
        Vec3 forwardExpand = entity.getDeltaMovement();
        AABB box = entity.getBoundingBox()
                .expandTowards(forwardExpand.scale(1.2))
                .expandTowards(backwardExpand);

        return box;
    }

    /**
     * Make sure vehicles are positioned close enough to the specified entity so that remounting after teleportation is possible
     */
    private static boolean reSyncVehicleToPassengerPos(Entity entity) {
        Entity vehicle = entity.getVehicle();
        if (vehicle == null) {
            return false;
        }

        double vehicleOffset = entity.getMyRidingOffset(vehicle);

        Vec3 oldMotion = vehicle.getDeltaMovement();

        Vec3 vehicleOffsetVec = new Vec3(0, vehicleOffset, 0);
        Vec3 newVehiclePos = entity.position().add(vehicleOffsetVec);
        Vec3 newVehicleLastTickPos = lastTickPosOf(entity).add(vehicleOffsetVec);

        //Prevent minecarts, boats and LivingEntity types from interpolating their position so that it doesn't fall into unloaded chunks
        //Directly set their position and rotation so that the interpolation will not happen
        vehicle.setPos(newVehiclePos.x(), newVehiclePos.y(), newVehiclePos.z());
        vehicle.lerpTo(newVehiclePos.x(), newVehiclePos.y(), newVehiclePos.z(), vehicle.getYRot(), vehicle.getXRot(), 0);

        updatePosAndLastTickPos(vehicle, newVehiclePos, newVehicleLastTickPos);

        vehicle.setDeltaMovement(oldMotion);

        return true;

    }

    /**
     * Helper to get the last ticked position of the Entity, vanilla doesn't have a helper, so we add one for our uses
     */
    private static Vec3 lastTickPosOf(Entity entity) {
        return new Vec3(entity.xo, entity.yo, entity.zo);
    }

    /**
     * When we set position of entities during teleportation, we sometimes need to force update their position from the previous tick
     * Some vanilla anti-cheat logic looks at both the last ticked position AND if the movement was abnormal (which can happen during teleportation)
     * then it will reset the entity's position which can produce unexpected results after releportation
     * We want to prevent this so this is a helper to allow for tihs
     */
    private static void updatePosAndLastTickPos(Entity entity, Vec3 updatedPos, Vec3 lastTickPos) {
        entity.setPos(updatedPos);
        entity.xOld = lastTickPos.x;
        entity.yOld = lastTickPos.y;
        entity.zOld = lastTickPos.z;
        entity.xo = lastTickPos.x;
        entity.yo = lastTickPos.y;
        entity.zo = lastTickPos.z;
    }

    private static boolean doesVehicleContainPlayer(Entity vehicle) {
        if (vehicle instanceof Player) {
            return true;
        }
        List<Entity> passengerList = vehicle.getPassengers();
        if (passengerList.isEmpty()) {
            return false;
        }
        return passengerList.stream().anyMatch(passenger -> passenger instanceof Player);
    }

    public static boolean canTeleportTo(BlockPos pPos, Level level, Entity entity) {
        BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(level, pPos.mutable());
        if (blockpathtypes != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockPos blockpos = pPos.subtract(entity.blockPosition());
            return level.noCollision(entity, entity.getBoundingBox().move(blockpos));
        }
    }


    private static boolean safetyCheck(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ, boolean safeBlockCheck, Set<Entity> teleportedEntities) {
        int xRound = (int) pX;
        int yRound = (int) pY;
        int zRound = (int) pZ;
        BlockPos blockpos = new BlockPos(xRound, yRound, zRound);

        if (teleportedEntities != null) {
            if (!teleportedEntities.isEmpty()) {
                if (teleportedEntities.contains(pEntity)) { //If we are calling this method by itself such as teleporting passengers, check if we have already teleported the entity
                    LOGGER.warn("Failed to teleport entity type as it has already been teleported: {}", pEntity.getType());
                    return false;
                }
            }
        }

        if (pEntity.getType().is(TRTagKeys.TARDIS_TELEPORT_BLACKLIST)) { //Stop teleporting if the entity being teleported is blacklisted
            LOGGER.warn("Failed to teleport entity type due to it being blacklisted: {}", pEntity.getType());
            return false;
        }
        if (safeBlockCheck) {
            if (!canTeleportTo(blockpos, destination, pEntity)) {
                LOGGER.warn("Failed to teleport entity type due to destination location being unsafe: {}", pEntity.getType());
                return false;
            }
        }

        if (!Level.isInSpawnableBounds(blockpos)) {
            return false;
        }

        return true;
    }

}