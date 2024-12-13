package whocraft.tardis_refined.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRControlRegistry;

import java.util.Collections;
import java.util.List;

public class KeyItem extends Item {

    public KeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        if (KeyItemData.getKeychain(itemStack).size() >= 2) {
            return Component.translatable(ModMessages.ITEM_KEYCHAIN);
        }
        return super.getName(itemStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (player.getOffhandItem().is(Items.GOAT_HORN) && !level.isClientSide) {
            List<ResourceKey<Level>> keychain = KeyItemData.getKeychain(player.getMainHandItem());
            if (!keychain.isEmpty()) {
                var tardisLevel = Platform.getServer().getLevel(keychain.get(0));
                var operatorOptional = tardisLevel != null ? TardisLevelOperator.get(tardisLevel) : null;
                if (operatorOptional != null && operatorOptional.get().getPilotingManager() != null && !operatorOptional.get().getPilotingManager().isInRecovery()) {
                    var pilotManager = operatorOptional.get().getPilotingManager();
                    pilotManager.setTargetLocation(new TardisNavLocation(player.blockPosition(), player.getDirection().getOpposite(), (ServerLevel) player.level()));
                    pilotManager.beginFlight(true, null);
                }
            }
        }

        return super.use(level, player, interactionHand);
    }

    public boolean interactMonitor(ItemStack itemStack, Player player, ControlEntity controlEntity, InteractionHand interactionHand) {
        if (controlEntity.level() instanceof ServerLevel serverLevel) {
            ResourceKey<Level> tardis = serverLevel.dimension();
            if (controlEntity.controlSpecification().control() == TRControlRegistry.MONITOR.get()) {
                if (KeyItemData.containsTardis(itemStack, tardis)) {
                    return false;
                }

                KeyItemData.addTardis(itemStack, tardis);
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.MSG_KEY_BOUND, tardis.location().getPath()), true);
                player.playSound(SoundEvents.PLAYER_LEVELUP, 1, 0.5F);
                return true;
            }
        }
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        ItemStack itemStack = context.getItemInHand();
        if (level instanceof ServerLevel serverLevel) {
            if (context.getPlayer().isShiftKeyDown()) {
                List<ResourceKey<Level>> keychain = KeyItemData.getKeychain(itemStack);
                if (!keychain.isEmpty()) {
                    Collections.rotate(keychain, -1);
                    KeyItemData.setKeychain(itemStack, keychain);
                    PlayerUtil.sendMessage(context.getPlayer(), Component.translatable(ModMessages.MSG_KEY_CYCLED, keychain.get(0).location().getPath()), true);
                    level.playSound(null, context.getPlayer().blockPosition(), SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.BLOCKS, 1, 2);
                }
            }
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);

        List<ResourceKey<Level>> keychain = KeyItemData.getKeychain(itemStack);

        if (!keychain.isEmpty()) {
            ResourceKey<Level> mainTardisLevel = keychain.get(0);

            if (TardisClientData.getInstance(mainTardisLevel).isFlying()) {
                list.add(Component.translatable(ModMessages.TOOLTIP_IN_FLIGHT));
            }

            list.add(Component.translatable(ModMessages.TOOLTIP_TARDIS_LIST_TITLE));

            for (int i = 0; i < keychain.size(); i++) {
                MutableComponent hyphen = Component.literal((i == 0) ? ChatFormatting.YELLOW + "> " : ChatFormatting.GRAY + "- ");
                list.add(hyphen.append(Component.literal(keychain.get(i).location().getPath().substring(0, 5))));
            }

            TardisClientData tardisClientData = TardisClientData.getInstance(mainTardisLevel);
            if (tardisClientData.isInRecovery()) {
                int cooldownTicks = tardisClientData.getRecoveryTicks();
                int maxCooldownTicks = TardisPilotingManager.TICKS_COOLDOWN_MAX;
                int percentage = (int) ((cooldownTicks / (float) maxCooldownTicks) * 100);
                list.add(Component.translatable(ModMessages.RECOVERY_PROGRESS, percentage + "%"));
            }
        }

    }
}
