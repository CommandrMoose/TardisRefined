package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class ReadoutControl extends whocraft.tardis_refined.common.tardis.control.Control {
    public ReadoutControl(ResourceLocation id) {
        super(id);
    }

    public ReadoutControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {

        TardisNavLocation currentPosition = operator.getPilotingManager().getCurrentLocation();
        PlayerUtil.sendMessage(player, Component.translatable(ModMessages.CURRENT).append(" - X: " + currentPosition.getPosition().getX() + " Y: " + currentPosition.getPosition().getY() + " Z: " + currentPosition.getPosition().getZ() + " F: " + currentPosition.getDirection().getName() + " D: " + currentPosition.getDimensionKey().location().getPath()), true);


        return true;
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {

        TardisNavLocation targetLocation = operator.getPilotingManager().getTargetLocation();
        PlayerUtil.sendMessage(player, Component.translatable(ModMessages.DESTINATION).append(" - X: " + targetLocation.getPosition().getX() + " Y: " + targetLocation.getPosition().getY() + " Z: " + targetLocation.getPosition().getZ() + " F: " + targetLocation.getDirection().getName() + " D: " + targetLocation.getDimensionKey().location().getPath()), true);

        return true;
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }
}
