package whocraft.tardis_refined.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.device.AstralManipulatorBlockEntity;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.ArrayList;
import java.util.List;

import static whocraft.tardis_refined.common.items.TRItemData.*;

public class ScrewdriverItem extends Item {

    public ScrewdriverItem(Properties properties) {
        super(properties);
    }

    public int getColor(ItemStack itemStack) {
        DyedItemColor dyedItemColor = itemStack.get(DataComponents.DYED_COLOR);
        if (dyedItemColor != null) {
            return dyedItemColor.rgb();
        }

        return DyeColor.PINK.getTextColor();
    }

    public static ItemStack forceColor(ItemStack itemStack, int color) {
        itemStack.set(DataComponents.DYED_COLOR, new DyedItemColor(color, false));
        return itemStack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!(context.getLevel() instanceof ServerLevel serverLevel)) {
            return super.useOn(context);
        }

        var player = context.getPlayer();
        var itemInHand = context.getItemInHand();
        var clickedPos = context.getClickedPos();
        var blockState = context.getLevel().getBlockState(clickedPos);

        if (player.isCrouching()) {
            ScrewdriverMode newMode = isScrewdriverMode(itemInHand, ScrewdriverMode.ENABLED)
                    ? ScrewdriverMode.DISABLED
                    : ScrewdriverMode.ENABLED;
            setScrewdriverMode(context.getPlayer(), itemInHand, newMode, clickedPos, serverLevel);
        } else if (isScrewdriverMode(itemInHand, ScrewdriverMode.DRAWING)
                && blockState.getBlock() != TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get()) {
            addBlockPosToScrewdriver(serverLevel, player, itemInHand, clickedPos);
        }

        return super.useOn(context);
    }

    public void setScrewdriverMode(Player player, ItemStack stack, ScrewdriverMode mode, BlockPos sourceChange, @Nullable ServerLevel serverLevel) {
        ScrewdriverMode currentMode = stack.get(SCREWDRIVER_MODE.get());

        if (serverLevel != null) {
            if (currentMode != ScrewdriverMode.DISABLED && mode == ScrewdriverMode.DISABLED) {
                playScrewdriverSound(serverLevel, sourceChange, TRSoundRegistry.SCREWDRIVER_DISCARD.get());
            }
            if (currentMode == ScrewdriverMode.DRAWING && mode != ScrewdriverMode.DRAWING) {
                clearLinkedManipulator(serverLevel, stack);
            }
        }

        // Update the mode in the DataComponent
        stack.set(SCREWDRIVER_MODE.get(), mode);

        if (mode == ScrewdriverMode.DRAWING) {
            stack.set(LINKED_MANIPULATOR_POS.get(), sourceChange);
        }
        PlayerUtil.sendMessage(player, mode.toString(), true);
    }

    public boolean isScrewdriverMode(ItemStack stack, ScrewdriverMode mode) {
        return stack.get(SCREWDRIVER_MODE.get()) == mode;
    }

    private void addBlockPosToScrewdriver(ServerLevel serverLevel, Player player, ItemStack stack, BlockPos pos) {
        boolean isUpdatingA = Boolean.TRUE.equals(stack.get(SCREWDRIVER_B_WAS_LAST_UPDATED.get()));
        DataComponentType<BlockPos> target = isUpdatingA ? SCREWDRIVER_POINT_A.get() : SCREWDRIVER_POINT_B.get();

        stack.set(target, pos);
        updatedLinkedManipulator(player, serverLevel, stack, pos, isUpdatingA);

        stack.set(SCREWDRIVER_B_WAS_LAST_UPDATED.get(), !isUpdatingA);

        playScrewdriverSound(serverLevel, player.getOnPos(), TRSoundRegistry.SCREWDRIVER_SHORT.get());
    }

    public void playScrewdriverSound(ServerLevel level, BlockPos pos, SoundEvent soundEvent) {
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundEvent, SoundSource.PLAYERS, 1, 0.875f + level.getRandom().nextFloat() / 4);
    }

    private void updatedLinkedManipulator(Player player, ServerLevel level, ItemStack stack, BlockPos pos, boolean isPointA) {
        BlockPos manipulator = stack.get(LINKED_MANIPULATOR_POS.get());
        if (level.getBlockEntity(manipulator) instanceof AstralManipulatorBlockEntity astralManipulatorBlockEntity) {
            if (!astralManipulatorBlockEntity.setProjectionBlockPos(pos, isPointA)) {
                setScrewdriverMode(player, stack, ScrewdriverMode.DISABLED, pos, level);
            }
        }
    }

    private void clearLinkedManipulator(ServerLevel level, ItemStack stack) {
        BlockPos manipulator = stack.get(LINKED_MANIPULATOR_POS.get());
        if (level.getBlockEntity(manipulator) instanceof AstralManipulatorBlockEntity astralManipulatorBlockEntity) {
            astralManipulatorBlockEntity.clearDisplay();
        }

        stack.remove(LINKED_MANIPULATOR_POS.get());
    }

    public void clearBlockPosFromScrewdriver(ItemStack stack) {
        stack.remove(SCREWDRIVER_POINT_A.get());
        stack.remove(SCREWDRIVER_POINT_B.get());
        stack.remove(LINKED_MANIPULATOR_POS.get());
    }

    public List<BlockPos> getScrewdriverPoint(ItemStack stack) {
        List<BlockPos> listOfBlockPos = new ArrayList<>();
        if (stack.has(SCREWDRIVER_POINT_A.get())) {
            listOfBlockPos.add(stack.get(SCREWDRIVER_POINT_A.get()));
        }
        if (stack.has(SCREWDRIVER_POINT_B.get())) {
            listOfBlockPos.add(stack.get(SCREWDRIVER_POINT_B.get()));
        }
        return listOfBlockPos;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
        list.add(Component.translatable(ModMessages.TOOLTIP_SCREWDRIVER_DESCRIPTION));
    }
}
