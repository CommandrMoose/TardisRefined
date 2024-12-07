package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;

import java.util.ArrayList;
import java.util.List;

public class FlightDanceManager extends TickableHandler {


    private TardisLevelOperator operator;
    private TardisPilotingManager pilotingManager;
    private List<ControlEntity> controlEntityList = new ArrayList<>();
    private int damagedControlCount = 0;

    private boolean weAreDancing = false;

    public FlightDanceManager(TardisLevelOperator operator) {
        this.operator = operator;
        this.pilotingManager = this.operator.getPilotingManager();
    }

    public TardisLevelOperator getOperator() {
        return this.operator;
    }

    public boolean isDancing() {
        return this.weAreDancing;
    }

    private List<ControlEntity> getNonCriticalControls(GlobalConsoleBlockEntity controllerConsole) {
        var allControlsOnConsole = controllerConsole.getControlEntityList();
        List<ControlEntity> updatedList = new ArrayList<>(allControlsOnConsole); //Copy over all entries to a new which we can manipulate
        for (ControlEntity controlEntity : allControlsOnConsole) { //Out of all controls in the original control list, remove any from our copy which are considered critical for normal operations.
            if (controlEntity.controlSpecification().control().isCriticalForTardisOperation()) {
                updatedList.remove(controlEntity); //Remove entries from our copy
            }
        }
        return updatedList;
    }

    public void startFlightDance(GlobalConsoleBlockEntity controllerConsole) {
        if (controllerConsole == null) return;
        this.controlEntityList = getNonCriticalControls(controllerConsole);
        this.weAreDancing = true;
    }

    @Override
    public void tick(ServerLevel operatorLevel) {
        if (this.weAreDancing) {
            if (operatorLevel.getGameTime() % (1 * 20) == 0) {
                this.onDanceTick(operatorLevel);
            }
        }
    }

    @Override
    public CompoundTag saveData(CompoundTag tag) {
        tag.putBoolean("weAreDancing", weAreDancing);
        tag.putInt("damagedControlCount", damagedControlCount);
        return tag;
    }

    @Override
    public void loadData(CompoundTag tag) {
        weAreDancing = tag.getBoolean("weAreDancing");
        damagedControlCount = tag.getInt("damagedControlCount");
    }

    public void stopDancing() {
        this.updateControlsAfterDance();
        this.controlEntityList = new ArrayList<>();
        this.damagedControlCount = 0;
        this.weAreDancing = false;
    }

    // A dance tick that runs every 20 ticks.
    private void onDanceTick(ServerLevel operatorLevel) {

        if (damagedControlCount >= 5) {
            this.stopDancing();
            this.operator.getPilotingManager().crash();
            return;
        }

        int chance = 20 - this.operator.getPilotingManager().getThrottleStage() * 2;
        if (operatorLevel.random.nextInt(chance) == 0) {
            this.triggerNextEvent();
        }

    }

    private void triggerNextEvent() {
        if (controlEntityList.isEmpty()) {
            GlobalConsoleBlockEntity console = operator.getPilotingManager().getCurrentConsole();
            // Someone logged out during flight / a desync happened - we will just nicely end the flight
            if (console == null) {
                stopDancing();
                operator.getPilotingManager().endFlight(true, false);
                return;
            } else {
                console.killControls(); // Just incase
                console.spawnControlEntities();
                controlEntityList.addAll(console.getControlEntityList());

            }
        }
        ControlEntity randomControlEntity = controlEntityList.get(this.operator.getLevel().random.nextInt(controlEntityList.size() - 1));
        randomControlEntity.setTickingDown(this);
    }

    public void updateDamageList() {
        this.damagedControlCount += 1;
    }

    private void updateControlsAfterDance() {
        if (this.pilotingManager.getCurrentConsole() != null) {
            this.pilotingManager.getCurrentConsole().killControls();
            this.pilotingManager.getCurrentConsole().spawnControlEntities();
        }

    }
}
