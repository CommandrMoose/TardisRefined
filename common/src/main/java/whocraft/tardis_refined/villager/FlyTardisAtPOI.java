package whocraft.tardis_refined.villager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.behavior.WorkAtPoi;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.registry.TRVillagerProfession;

import java.util.Optional;
import java.util.Random;

public class FlyTardisAtPOI extends WorkAtPoi {

    private Direction direction = Direction.NORTH;
    private static final int EMERALD_FLIGHT_TIME = 5 * 20 * 60; // 5 minutes in ticks
    private static final double FAILURE_RATE = 0.1; // 10% failure rate per villager

    public void rotateDirection() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
            default -> throw new IllegalStateException("Invalid direction: " + direction);
        }
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel serverLevel, Villager villager) {
        GlobalPos globalPos = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).orElse(null);
        TardisLevelOperator tardisLevelOperator = TardisLevelOperator.get(serverLevel).orElse(null);

        if (globalPos == null || tardisLevelOperator == null) {
            return false;
        }

        return tardisLevelOperator.getPilotingManager().isInFlight() && globalPos.dimension() == serverLevel.dimension();
    }

    @Override
    protected void useWorkstation(ServerLevel serverLevel, Villager villager) {
        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
            GlobalConsoleBlockEntity console = pilotManager.getCurrentConsole();
            Brain<Villager> brain = villager.getBrain();
            if (console == null) return;

            // Check emeralds for flight time
            if (!hasEmeraldsForFlight(villager)) {
                villager.setUnhappyCounter(40);
                return;
            }

            if (pilotManager.isInFlight()) {

                if (pilotManager.isCrashing()) {
                    BlockPos runAwayPosition = villager.blockPosition().relative(direction, 10);
                    villager.getNavigation().moveTo(runAwayPosition.getX(), runAwayPosition.getY(), runAwayPosition.getZ(), 2);
                    return;
                }

                for (ControlEntity controlEntity : console.getControlEntityList()) {
                    if (controlEntity.isTickingDown()) {
                        rotateDirection();
                        if (controlEntity.level().random.nextBoolean()) {
                            for (int i = 0; i < 5; i++) {
                                controlEntity.realignControl();
                            }
                            villager.setUnhappyCounter(40);
                            return;
                        }
                    }
                }

                if (serverLevel.random.nextDouble() < FAILURE_RATE) {
                  //TODO  pilotManager.setTargetLocation();
                }
            }
        });

        super.useWorkstation(serverLevel, villager);
    }

    private boolean hasEmeraldsForFlight(Villager villager) {
        if (villager.getVillagerData().getProfession() == TRVillagerProfession.PILOT.get()) {
            ItemStack emeralds = villager.getInventory().getItem(0);
            if (emeralds.getItem() == Items.EMERALD && emeralds.getCount() > 0) {
                emeralds.shrink(1);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void start(ServerLevel serverLevel, Villager villager, long l) {
        Brain<Villager> brain = villager.getBrain();
        brain.setMemory(MemoryModuleType.LAST_WORKED_AT_POI, l);
        brain.getMemory(MemoryModuleType.JOB_SITE).ifPresent((globalPos) -> {
            BlockPos position = globalPos.pos().relative(direction, 2);
            brain.setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(position));
            villager.getNavigation().moveTo(position.getX(), position.getY(), position.getZ(), 1);
            villager.getLookControl().setLookAt(globalPos.pos().getX(), globalPos.pos().getY(), globalPos.pos().getZ());
        });

        if (villager.tickCount % 80 == 0) {
            rotateDirection();
        }

        this.useWorkstation(serverLevel, villager);
    }


    @Override
    protected boolean canStillUse(ServerLevel serverLevel, Villager villager, long l) {
        Optional<GlobalPos> optional = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE);
        TardisLevelOperator tardisLevelOperator = TardisLevelOperator.get(serverLevel).orElse(null);
        if (optional.isEmpty()) {
            return false;
        } else {
            GlobalPos globalPos = optional.get();
            return tardisLevelOperator.getPilotingManager().isInFlight() && globalPos.dimension() == serverLevel.dimension() && globalPos.pos().closerToCenterThan(villager.position(), 1.73);
        }
    }
}
