package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class ThrottleControl extends Control {
    public ThrottleControl(ResourceLocation id) {
        super(id, true);
    }

    public ThrottleControl(ResourceLocation id, String langId) {
        super(id, langId, true);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {

        if (player.isCrouching()) {
            operator.getPilotingManager().setThrottleStage(TardisPilotingManager.MAX_THROTTLE_STAGE);

        } else {

            int nextStage = operator.getPilotingManager().getThrottleStage() + 1;
            if (nextStage <= TardisPilotingManager.MAX_THROTTLE_STAGE) {
                operator.getPilotingManager().setThrottleStage(nextStage);
            }
        }

        return true;

    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {

        if (player.isCrouching()) {
            operator.getPilotingManager().setThrottleStage(0);
        } else {
            int nextStage = operator.getPilotingManager().getThrottleStage() - 1;
            if (nextStage >= 0) {
                operator.getPilotingManager().setThrottleStage(nextStage);
            }
        }

        return true;
    }
}
