package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.Level.OVERWORLD;

public class DimensionalControl extends Control {
    public DimensionalControl(ResourceLocation id) {
        super(id);
    }

    public DimensionalControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    private List<ServerLevel> getAllowedDimensions(MinecraftServer server) {
        var filteredDimensions = new ArrayList<ServerLevel>();
        var filteredLevels = server.getAllLevels();

        filteredLevels.forEach(x -> {
            if (DimensionUtil.isAllowedDimension(x.dimension())) {
                filteredDimensions.add(x);
            }
        });

        return filteredDimensions;
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return changeDim(operator, theme, controlEntity, player, false);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return changeDim(operator, theme, controlEntity, player, true);
    }

    private boolean changeDim(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player, boolean forward) {
        if (!operator.getLevel().isClientSide()) {

            TardisPilotingManager pilotManager = operator.getPilotingManager();
            UpgradeHandler upgradeHandler = operator.getUpgradeHandler();

            if (!TRUpgrades.DIMENSION_TRAVEL.get().isUnlocked(upgradeHandler)) {
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.HARDWARE_OFFLINE), true);
                pilotManager.getTargetLocation().setDimensionKey(OVERWORLD);
                return false;
            }

            var server = operator.getLevel().getServer();
            var dimensions = getAllowedDimensions(server);
            var currentIndex = dimensions.indexOf(pilotManager.getTargetLocation().getLevel());
            var nextIndex = forward ? ((currentIndex >= dimensions.size() - 1) ? 0 : currentIndex + 1) : ((currentIndex <= 0) ? dimensions.size() - 1 : currentIndex - 1);

            var nextDimension = dimensions.get(nextIndex);

            // We want to filter out the end if the end hasn't been completed whilst the player is in flight. We can keep it pre-flight because we do some fancy sounds to tell the player
            // it's not an option.
            if (nextDimension.dimension() == Level.END && pilotManager.isInFlight()) {
                if (!TardisHelper.hasTheEndBeenCompleted(pilotManager.getTargetLocation().getLevel())) {
                    nextIndex += forward ? 1 : -1;
                }
            }

            pilotManager.getTargetLocation().setLevel(dimensions.get(nextIndex));

            PlayerUtil.sendMessage(player, Component.translatable(ModMessages.CONTROL_DIMENSION_SELECTED, MiscHelper.getCleanDimensionName(pilotManager.getTargetLocation().getDimensionKey())), true);

            if (pilotManager.isInFlight()) {
                operator.getPilotingManager().recalculateFlightDistance();
            }
            return true;
        }
        return false;
    }

}