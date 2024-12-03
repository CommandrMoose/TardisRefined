package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class HandbrakeControl extends Control {
    public HandbrakeControl(ResourceLocation id) {
        super(id, true);
    }

    public HandbrakeControl(ResourceLocation id, String langId) {
        super(id, langId, true);
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {

        if (operator.getPilotingManager().isInFlight()) {

            if (operator.getPilotingManager().isTakingOff() || operator.getPilotingManager().isLanding()) {
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.NO_FLIGHT_TRANSITIVE), true); // MAKE THIS IS A TRANSLATION
                return false;
            }

            operator.getPilotingManager().setHandbrakeOn(true);
            PlayerUtil.sendMessage(player, Component.translatable(operator.getPilotingManager().isHandbrakeOn() ? ModMessages.HANDBRAKE_ENGAGED : ModMessages.HANDBRAKE_DISENGAGED), true);
            return true;
        }

        return false;
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if (operator.getPilotingManager().isInFlight()) {

            PlayerUtil.sendMessage(player, Component.translatable(ModMessages.HANDBRAKE_WARNING), true);
            return false;
        } else {
            operator.getPilotingManager().setHandbrakeOn(!operator.getPilotingManager().isHandbrakeOn());
            PlayerUtil.sendMessage(player, Component.translatable(operator.getPilotingManager().isHandbrakeOn() ? ModMessages.HANDBRAKE_ENGAGED : ModMessages.HANDBRAKE_DISENGAGED), true);
            return true;
        }
    }
}
