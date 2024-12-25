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
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.ArrayList;
import java.util.List;

public class MiscHelper {

    @ExpectPlatform
    public static Packet<ClientGamePacketListener> spawnPacket(Entity entity) {
        throw new RuntimeException(PlatformWarning.addWarning(MiscHelper.class));
    }

    public static String convertTicksToRealTime(int ticks) {
        long totalSeconds = ticks / 20;

        long years = totalSeconds / (60L * 60 * 24 * 30 * 12);
        long remainingAfterYears = totalSeconds % (60L * 60 * 24 * 30 * 12);

        long months = remainingAfterYears / (60L * 60 * 24 * 30);
        long remainingAfterMonths = remainingAfterYears % (60L * 60 * 24 * 30);

        long days = remainingAfterMonths / (60L * 60 * 24);
        long remainingAfterDays = remainingAfterMonths % (60L * 60 * 24);

        long hours = remainingAfterDays / (60L * 60);
        long remainingAfterHours = remainingAfterDays % (60L * 60);

        long minutes = remainingAfterHours / 60;
        long seconds = remainingAfterHours % 60;

        StringBuilder result = new StringBuilder();
        if (years > 0) {
            result.append(years).append(years == 1 ? " year, " : " years, ");
        }
        if (months > 0) {
            result.append(months).append(months == 1 ? " month, " : " months, ");
        }
        if (days > 0) {
            result.append(days).append(days == 1 ? " day, " : " days, ");
        }
        if (hours > 0) {
            result.append(hours).append(hours == 1 ? " hour, " : " hours, ");
        }
        if (minutes > 0) {
            result.append(minutes).append(minutes == 1 ? " minute, " : " minutes, ");
        }
        if (seconds > 0 || result.isEmpty()) {
            result.append(seconds).append(seconds == 1 ? " second" : " seconds");
        } else {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }

    public static boolean isBlockPosInBox(BlockPos blockPos, AABB aabb) {
        return aabb.contains(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static ResourceKey<Level> idToKey(ResourceLocation identifier) {
        return ResourceKey.create(Registries.DIMENSION, identifier);
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

