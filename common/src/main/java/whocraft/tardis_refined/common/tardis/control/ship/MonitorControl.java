package whocraft.tardis_refined.common.tardis.control.ship;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.items.DimensionSamplerItem;
import whocraft.tardis_refined.common.items.KeyItem;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenMonitor;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class MonitorControl extends Control {
    public MonitorControl(ResourceLocation id) {
        super(id, true);
    }


    public MonitorControl(ResourceLocation id, String langId) {
        super(id, langId, true);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if (!player.level().isClientSide()) {

            if (operator.getTardisState() != TardisLevelOperator.STATE_EYE_OF_HARMONY || operator.getPilotingManager().isOutOfFuel()) {
                PlayerUtil.sendMessage(player, ModMessages.FUEL_OFFLINE, true);
                return false;
            }

            ItemStack hand = player.getMainHandItem();

            if(hand.getItem() instanceof DimensionSamplerItem dimTrackerItem){
                ResourceKey<Level> levelResourceKey = DimensionSamplerItem.getSavedDim(hand);
                if(levelResourceKey != null) {
                    operator.getProgressionManager().addDiscoveredLevel(levelResourceKey);
                    PlayerUtil.sendMessage(player, Component.translatable(ModMessages.DIM_ADDED_TO_TARDIS, MiscHelper.getCleanDimensionName(levelResourceKey)), true);
                    hand.setCount(0);
                }
                return false;
            }


            boolean isSyncingKey = false;
            if (hand.getItem() instanceof KeyItem key) {
                if (key.interactMonitor(hand, player, controlEntity, player.getUsedItemHand()))
                    isSyncingKey = true;
            }
            if (!isSyncingKey)
                new S2COpenMonitor(operator.getInteriorManager().isWaitingToGenerate(), operator.getPilotingManager().getCurrentLocation(), operator.getPilotingManager().getTargetLocation(), operator.getUpgradeHandler(), operator.getAestheticHandler().getShellTheme()).send((ServerPlayer) player);
            return true;
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
        if (operator.getPilotingManager().isInFlight() && !operator.getPilotingManager().isLanding()) {
            float percentageCompleted = (operator.getPilotingManager().getFlightPercentageCovered() * 100f);
            if (percentageCompleted > 100) {
                percentageCompleted = 100;
            }
            return Component.translatable(controlSpecification.control().getTranslationKey()).append(" (" + (int) percentageCompleted + "%)");
        }

        return super.getCustomControlName(operator, entity, controlSpecification);
    }
}
