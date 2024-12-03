package whocraft.tardis_refined.common.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.apache.commons.lang3.text.WordUtils;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.block.life.EyeBlock;
import whocraft.tardis_refined.common.block.shell.ShellBaseBlock;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.protection.ProtectedZone;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.ArrayList;
import java.util.List;

public class MiscHelper {

    @ExpectPlatform
    public static Packet<ClientGamePacketListener> spawnPacket(Entity entity) {
        throw new RuntimeException(TardisRefined.PLATFORM_ERROR);
    }

    public static boolean isBlockPosInBox(BlockPos blockPos, AABB aabb) {
        return aabb.contains(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static ResourceKey<Level> idToKey(ResourceLocation identifier) {
        return ResourceKey.create(Registries.DIMENSION, identifier);
    }

    public static boolean performTeleport(Entity pEntity, ServerLevel pLevel, double pX, double pY, double pZ, float pYaw, float pPitch) {

        TardisRefined.LOGGER.debug("Teleported {} to {} {} {}", pEntity.getDisplayName().getString(), pX, pY, pZ);
        int xRound = (int) pX;
        int yRound = (int) pY;
        int zRound = (int) pZ;

        BlockPos blockpos = new BlockPos(xRound, yRound, zRound);

        if (!Level.isInSpawnableBounds(blockpos)) {
            return false;
        } else {
            float f = Mth.wrapDegrees(pYaw);
            float f1 = Mth.wrapDegrees(pPitch);
            if (pEntity instanceof ServerPlayer serverPlayer) {
                ChunkPos chunkpos = new ChunkPos(blockpos);
                pLevel.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, pEntity.getId());
                pEntity.stopRiding();
                if (serverPlayer.isSleeping()) {
                    serverPlayer.stopSleepInBed(true, true);
                }

                if (pLevel == pEntity.level()) {
                    serverPlayer.connection.teleport(pX, pY, pZ, f, f1);
                } else {
                    serverPlayer.teleportTo(pLevel, pX, pY, pZ, f, f1);
                }
                pEntity.setYHeadRot(f);
            } else {
                float f2 = Mth.clamp(f1, -90.0F, 90.0F);
                if (pLevel == pEntity.level()) {
                    pEntity.moveTo(pX, pY, pZ, f, f2);
                    pEntity.setYHeadRot(f);
                } else {
                    pEntity.unRide();
                    Entity entity = pEntity;
                    pEntity = pEntity.getType().create(pLevel);
                    if (pEntity == null) {
                        return false;
                    }

                    pEntity.restoreFrom(entity);
                    pEntity.moveTo(pX, pY, pZ, f, f2);
                    pEntity.setYHeadRot(f);
                    entity.setRemoved(Entity.RemovalReason.CHANGED_DIMENSION);
                    pLevel.addDuringTeleport(pEntity);
                }
            }

            if (!(pEntity instanceof LivingEntity) || !((LivingEntity) pEntity).isFallFlying()) {
                pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
                pEntity.setOnGround(true);
            }

            if (pEntity instanceof PathfinderMob) {
                ((PathfinderMob) pEntity).getNavigation().stop();
            }

            return true;
        }
    }

    public static boolean shouldStopItem(Level level, Player player, BlockPos blockPos, ItemStack itemInHand) {
        if (level.dimensionTypeId() == TRDimensionTypes.TARDIS && level instanceof ServerLevel serverLevel) {
            TardisLevelOperator data = TardisLevelOperator.get(serverLevel).get();

            // Consoles
            Item consoleItem = TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get().asItem();
            Item consoleConfigItem = TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get().asItem();
            if (data.getInteriorManager().isCave() && (itemInHand.getItem() == consoleConfigItem || itemInHand.getItem() == consoleItem)) {
                return true;
            }

            // Protected Zones
            for (ProtectedZone protectedZone : data.getInteriorManager().unbreakableZones()) {
                boolean shouldCancel = !protectedZone.isAllowPlacement() && isBlockPosInBox(blockPos, protectedZone.getArea());
                if (shouldCancel) return true;
            }
        }
        return false;
    }

    public static boolean shouldCancelBreaking(Level world, Entity entity, BlockPos pos, BlockState state) {

        if (world.dimensionTypeId() == TRDimensionTypes.TARDIS && world instanceof ServerLevel serverLevel) {
            TardisLevelOperator data = TardisLevelOperator.get(serverLevel).get();
            for (ProtectedZone protectedZone : data.getInteriorManager().unbreakableZones()) {
                boolean shouldCancel = !protectedZone.isAllowBreaking() && isBlockPosInBox(pos, protectedZone.getArea());
                if (shouldCancel) return true;
            }
        }

        return (state.getBlock() instanceof GlobalConsoleBlock && world.dimensionTypeId() == TRDimensionTypes.TARDIS) || state.getBlock() instanceof ShellBaseBlock || state.getBlock() instanceof EyeBlock;
    }

    public static String getCleanDimensionName(ResourceKey<Level> dimensionKey) {
        return getCleanName(dimensionKey.location().getPath());
    }

    public static String getCleanName(String name) {
        var noUnderscores = name.replace("_", " ");
        return WordUtils.capitalizeFully(noUnderscores);
    }

    public static DamageSource getDamageSource(ServerLevel level, ResourceKey<DamageType> damageTypeResourceKey) {
        Holder.Reference<DamageType> damageType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(damageTypeResourceKey);
        DamageSource source = new DamageSource(damageType);
        return source;
    }

    /**
     * Combines elements of two sets togethor into a new set
     *
     * @param setOne
     * @param setTwo
     * @param <T>
     * @return
     */
    public static <T extends Object> ArrayList<T> unionList(List<T> setOne, List<T> setTwo) {
        if (setOne != null) {
            if (setTwo != null) {
                ArrayList<T> finalSet = new ArrayList<>();
                finalSet.addAll(setOne);
                finalSet.addAll(setTwo);
                return finalSet;
            }
            throw new NullPointerException("set2");
        }
        throw new NullPointerException("set1");
    }

}

