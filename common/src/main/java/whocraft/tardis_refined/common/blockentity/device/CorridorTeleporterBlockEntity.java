package whocraft.tardis_refined.common.blockentity.device;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import whocraft.tardis_refined.common.dimension.TardisTeleportData;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.util.TRTeleporter;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;
import whocraft.tardis_refined.registry.TRDimensionTypes;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.List;

public class CorridorTeleporterBlockEntity extends BlockEntity implements BlockEntityTicker<CorridorTeleporterBlockEntity> {

    private static int TICKS_FOR_COOLDOWN = 100;
    private static int TICKS_TO_TRIGGER_TELEPORT = 160;

    private int timeSinceTriggeredTicks = 0;
    private int cooldownTicks = 0;

    public CorridorTeleporterBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TRBlockEntityRegistry.CORRIDOR_TELEPORTER.get(), blockPos, blockState);
    }

    public void startTeleporterTimer() {
        if (cooldownTicks == 0 && timeSinceTriggeredTicks == 0) {
            this.timeSinceTriggeredTicks += 1;
            level.playSound(null, getBlockPos(), TRSoundRegistry.CORRIDOR_TELEPORTER.get(), SoundSource.BLOCKS, 1, 1);
        }
    }


    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, CorridorTeleporterBlockEntity blockEntity) {

        // If we're meant to be ticking, let's tick.
        if (this.timeSinceTriggeredTicks > 0 && cooldownTicks == 0) {
            this.timeSinceTriggeredTicks += 1;
        }

        if (this.cooldownTicks > 0) {
            this.cooldownTicks -= 1;
        }

        if (timeSinceTriggeredTicks == TICKS_TO_TRIGGER_TELEPORT) {
            timeSinceTriggeredTicks = 0;
            this.cooldownTicks = TICKS_FOR_COOLDOWN;

            if (level.dimensionTypeRegistration() != TRDimensionTypes.TARDIS) {
                level.playSound(null, getBlockPos(), SoundEvents.BLAZE_DEATH, SoundSource.BLOCKS, 1, 0.5f);
                return;
            }


            if (level instanceof ServerLevel serverLevel) {
                AABB box = new AABB(blockPos).inflate(0.25f, 1, 0.25f);
                List<Entity> entities = serverLevel.getEntitiesOfClass(Entity.class, box);
                BlockPos corridorAirlock = TardisInteriorManager.STATIC_CORRIDOR_POSITION;
                for (Entity entity : entities) {
                    TardisTeleportData.scheduleEntityTeleport(entity, serverLevel.dimension(), corridorAirlock.getX() + 0.5f, corridorAirlock.getY(), corridorAirlock.getZ() + 0.5f, 0, 0);
                }

                if (entities.stream().count() > 0) {
                    // Play at the source block and at the exit point.
                    level.playSound(null, getBlockPos(), TRSoundRegistry.CORRIDOR_TELEPORTER_SUCCESS.get(), SoundSource.BLOCKS, 1, 1);
                    level.playSound(null, corridorAirlock, TRSoundRegistry.CORRIDOR_TELEPORTER_SUCCESS.get(), SoundSource.BLOCKS, 1, 1);
                }
            }


        }
    }
}
