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
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;

import java.util.Optional;

public class FlyTardisAtPOI extends WorkAtPoi {

    private Direction direction = Direction.NORTH;

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
        GlobalPos globalPos = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).get();
        TardisLevelOperator tardisLevelOperator = TardisLevelOperator.get(serverLevel).orElse(null);

        return tardisLevelOperator.getPilotingManager().isInFlight() && globalPos.dimension() == serverLevel.dimension();
    }

    @Override
    protected void useWorkstation(ServerLevel serverLevel, Villager villager) {

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
            GlobalConsoleBlockEntity console = pilotManager.getCurrentConsole();
            Brain<Villager> brain = villager.getBrain();
            if (console == null) return;

            if (pilotManager.isInFlight()) {



            /*    if(pilotManager.canEndFlight()){
                    pilotManager.setThrottleStage(0);
                    pilotManager.setHandbrakeOn(true);
                } else {
                    if(pilotManager.getTargetLocation().getPosition().getX() != 45){
                        pilotManager.getTargetLocation().setPosition(new BlockPos(45,45,45));
                        pilotManager.setThrottleStage(4);
                        pilotManager.setHandbrakeOn(false);
                    }
                }*/

                for (ControlEntity controlEntity : console.getControlEntityList()) {
                    if (controlEntity.isTickingDown()) {
                        rotateDirection();
                        // Adjust bounding box check to ensure proximity, but without intersecting
                        if (controlEntity.level().random.nextBoolean()) {
                            for (int i = 0; i < 5; i++) {
                                controlEntity.realignControl();
                            }
                            villager.setUnhappyCounter(40);
                            return;
                        }
                    }
                }
            }
        });

        super.useWorkstation(serverLevel, villager);
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
