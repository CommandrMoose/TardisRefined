package whocraft.tardis_refined.common.items;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.List;

import static net.minecraft.core.registries.Registries.DIMENSION;

public class DimensionSamplerItem extends Item {
    private static final int TIMER_MAX = 6000; // 5 minutes in ticks

    public DimensionSamplerItem(Properties properties) {
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
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        Level level = useOnContext.getLevel();
        InteractionHand hand = useOnContext.getHand();

        ItemStack stack = player.getItemInHand(hand);
        if (level instanceof ServerLevel serverLevel) {
            // Save current dimension as potentialDim when right-clicked
            if (!stack.has(TRItemData.POTENTIAL_DIM.get()) && DimensionUtil.isAllowedDimension(level.dimension())) {
                forceColor(stack, serverLevel.getBlockTint(useOnContext.getClickedPos(), (biome, d, e) -> biome.getFogColor()));
                savePotentialDim(stack, serverLevel.dimension());
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.DIM_POTENTIAL, MiscHelper.getCleanDimensionName(ResourceKey.create(DIMENSION, stack.get(TRItemData.SAVED_DIM.get())))), true);
            } else {
                PlayerUtil.sendMessage(player, !DimensionUtil.isAllowedDimension(level.dimension()) ? ModMessages.DIM_NOT_ALLOWED : ModMessages.DIM_ALREADY_SAVED, true);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (level instanceof ServerLevel serverLevel && entity instanceof Player player) {
            if (itemStack.has(TRItemData.POTENTIAL_DIM.get())) {
                ResourceLocation potentialDim = itemStack.get(TRItemData.POTENTIAL_DIM.get());
                ResourceKey<Level> currentDim = serverLevel.dimension();

                if (currentDim.location().toString().equals(potentialDim)) {
                    int timer = itemStack.get(TRItemData.TIMER.get()) + 1;

                    if (timer >= TIMER_MAX) {
                        saveSavedDim(itemStack, ResourceKey.create(DIMENSION, potentialDim));
                    } else {
                        updateTimer(itemStack, timer);
                    }
                } else {
                    resetProgress(itemStack);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.NONE;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        if (itemStack.has(TRItemData.TIMER.get()) && itemStack.has(TRItemData.POTENTIAL_DIM.get())) {
            int timer = itemStack.get(TRItemData.TIMER.get());
            double progress = (double) timer / TIMER_MAX * 100;
            list.add(Component.translatable(ModMessages.TOOLTIP_DIM_PROGRESS, Math.round(progress) + "%"));
        } else if (itemStack.has(TRItemData.SAVED_DIM.get())) {
            list.add(Component.translatable(ModMessages.TOOLTIP_DIM_SAVED, MiscHelper.getCleanDimensionName(ResourceKey.create(DIMENSION, itemStack.get(TRItemData.SAVED_DIM.get())))));
        } else {
            list.add(Component.translatable(ModMessages.TOOLTIP_NO_DIM_SAVED));
        }
    }

    public static boolean hasDimAtAll(ItemStack stack){
        return stack.has(TRItemData.POTENTIAL_DIM.get()) || stack.has(TRItemData.SAVED_DIM.get()) ;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        // Check if either of the dimension tags is present
        DataComponentType<ResourceLocation> dimensionTag = itemStack.has(TRItemData.POTENTIAL_DIM.get()) ? TRItemData.POTENTIAL_DIM.get() : (itemStack.has(TRItemData.SAVED_DIM.get()) ? TRItemData.SAVED_DIM.get() : null);

        if (dimensionTag != null) {
            String dimension = MiscHelper.getCleanDimensionName(ResourceKey.create(DIMENSION, itemStack.get(dimensionTag)));
            return Component.literal(dimension + " Sample");
        }

        return super.getName(itemStack);
    }


    private void savePotentialDim(ItemStack itemStack, ResourceKey<Level> dimension) {
        itemStack.set(TRItemData.POTENTIAL_DIM.get(), dimension.location());
        itemStack.set(TRItemData.TIMER.get(), 0);
    }

    private void saveSavedDim(ItemStack itemStack, ResourceKey<Level> dimension) {
        itemStack.set(TRItemData.SAVED_DIM.get(), dimension.location());
        itemStack.remove(TRItemData.POTENTIAL_DIM.get());
        itemStack.remove(TRItemData.TIMER.get());

    }

    private void updateTimer(ItemStack itemStack, int timer) {
        itemStack.set(TRItemData.TIMER.get(), timer);

    }

    private void resetProgress(ItemStack itemStack) {
        itemStack.remove(TRItemData.POTENTIAL_DIM.get());
        itemStack.remove(TRItemData.TIMER.get());
    }

    public static ResourceKey<Level> getSavedDim(ItemStack stack) {
        if (stack != null && stack.has(TRItemData.SAVED_DIM.get())) {
            ResourceLocation savedDimLocation = stack.get(TRItemData.SAVED_DIM.get());
            if (savedDimLocation != null) {
                return ResourceKey.create(DIMENSION, savedDimLocation);
            }
        }
        return null;
    }

    public static ResourceKey<Level> getPotentialDim(ItemStack stack) {
        if (stack != null && stack.has(TRItemData.POTENTIAL_DIM.get())) {
            ResourceLocation savedDimLocation = stack.get(TRItemData.POTENTIAL_DIM.get());
            if (savedDimLocation != null) {
                return ResourceKey.create(DIMENSION, savedDimLocation);
            }
        }
        return null;
    }

}
