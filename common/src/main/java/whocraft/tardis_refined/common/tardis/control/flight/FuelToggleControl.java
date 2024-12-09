package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class FuelToggleControl extends Control {

    public FuelToggleControl(ResourceLocation id) {
        super(id);
    }

    public FuelToggleControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if (!operator.getLevel().isClientSide()) {
            if (operator.getTardisState() != TardisLevelOperator.STATE_EYE_OF_HARMONY) {
                return false;
            }

            TardisPilotingManager pilotManager = operator.getPilotingManager();

            boolean successful = pilotManager.setPassivelyRefuelling(!pilotManager.isPassivelyRefuelling());

            if (successful) {
                PlayerUtil.sendMessage(player, Component.translatable(pilotManager.isPassivelyRefuelling() ? ModMessages.REFUEL : ModMessages.STOP_REFUEL), true);
            }

            return successful;
        }
        return false;
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return false;
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public Component getCustomControlName(TardisLevelOperator operator, ControlEntity entity, ControlSpecification controlSpecification) {
        if (operator == null) {
            return super.getCustomControlName(operator, entity, controlSpecification);
        }
        boolean offline = operator.getTardisState() != TardisLevelOperator.STATE_EYE_OF_HARMONY;
        if (offline) {
            return Component.translatable(ModMessages.FUEL_OFFLINE);
        }

        return Component.translatable(ModMessages.FUEL, String.valueOf((Math.round((operator.getPilotingManager().getFuelPercentage() * 100))))).append("%");
    }
}
