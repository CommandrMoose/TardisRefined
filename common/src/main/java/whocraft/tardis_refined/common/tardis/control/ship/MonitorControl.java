package whocraft.tardis_refined.common.tardis.control.ship;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.items.KeyItem;
import whocraft.tardis_refined.common.network.messages.OpenMonitorMessage;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.registry.ItemRegistry;

public class MonitorControl extends Control {

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if (!player.level().isClientSide()){
            boolean isSyncingKey = false;
            if (PlayerUtil.isInMainHand(player, ItemRegistry.KEY.get())){
                KeyItem key = (KeyItem)player.getMainHandItem().getItem();
                if (key.interactMonitor(player.getMainHandItem(),player, controlEntity, player.getUsedItemHand()))
                    isSyncingKey = true;
            }
            if (!isSyncingKey)
                new OpenMonitorMessage(operator.getInteriorManager().isWaitingToGenerate(), operator.getExteriorManager().getLastKnownLocation(), operator.getPilotingManager().getTargetLocation(), operator.getUpgradeHandler()).send((ServerPlayer) player);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return false;
    }
}
