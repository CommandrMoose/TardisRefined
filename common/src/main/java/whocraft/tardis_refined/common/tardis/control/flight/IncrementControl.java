package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;

public class IncrementControl extends Control {
    public IncrementControl(ResourceLocation id) {
        super(id);
    }

    public IncrementControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return this.incrementCoord(operator, theme, controlEntity, player, 1);
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return this.incrementCoord(operator, theme, controlEntity, player, -1);
    }

    private boolean incrementCoord(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player, int incAmount) {
        if (!operator.getLevel().isClientSide()) {
            TardisPilotingManager pilotManager = operator.getPilotingManager();
            pilotManager.cycleCordIncrement(incAmount);
            int currentIncAmount = pilotManager.getCordIncrement();
            PlayerUtil.sendMessage(player, Component.translatable("x" + currentIncAmount), true);
            return true;
        }
        return false;
    }
}
