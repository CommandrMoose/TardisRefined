package whocraft.tardis_refined.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.List;

import static net.minecraft.core.registries.Registries.DIMENSION;

public class DimensionSamplerItem extends Item {
    private static final String POTENTIAL_DIM_TAG = "potentialDim";
    private static final String SAVED_DIM_TAG = "savedDim";
    private static final String TIMER_TAG = "timer";
    private static final int TIMER_MAX = 6000; // 5 minutes in ticks

    public DimensionSamplerItem(Properties properties) {
        super(properties);
    }

    public static ItemStack forceColor(ItemStack itemStack, int color) {
        itemStack.getOrCreateTagElement("display").putInt("color", color);
        return itemStack;
    }

    public int getColor(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTagElement("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : DyeColor.PINK.getTextColor();
    }



    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        Level level = useOnContext.getLevel();
        InteractionHand hand = useOnContext.getHand();

        ItemStack stack = player.getItemInHand(hand);
        if (level instanceof ServerLevel serverLevel) {
            CompoundTag tag = stack.getOrCreateTag();
            // Save current dimension as potentialDim when right-clicked
            if (!tag.contains(POTENTIAL_DIM_TAG) && DimensionUtil.isAllowedDimension(level.dimension())) {
                forceColor(stack, serverLevel.getBlockTint(useOnContext.getClickedPos(), (biome, d, e) -> biome.getFogColor()));
                savePotentialDim(tag, serverLevel.dimension());
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.DIM_POTENTIAL, MiscHelper.getCleanDimensionName(ResourceKey.create(DIMENSION, new ResourceLocation(tag.getString(SAVED_DIM_TAG))))), true);
            } else {
                PlayerUtil.sendMessage(player, !DimensionUtil.isAllowedDimension(level.dimension()) ? ModMessages.DIM_NOT_ALLOWED : ModMessages.DIM_ALREADY_SAVED, true);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (level instanceof ServerLevel serverLevel && entity instanceof Player player) {
            CompoundTag tag = itemStack.getOrCreateTag();

            if (tag.contains(POTENTIAL_DIM_TAG)) {
                String potentialDim = tag.getString(POTENTIAL_DIM_TAG);
                ResourceKey<Level> currentDim = serverLevel.dimension();

                if (currentDim.location().toString().equals(potentialDim)) {
                    int timer = tag.getInt(TIMER_TAG) + 1;

                    if (timer >= TIMER_MAX) {
                        saveSavedDim(tag, potentialDim);
                    } else {
                        updateTimer(tag, timer);
                    }
                } else {
                    resetProgress(tag);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.NONE;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TIMER_TAG) && tag.contains(POTENTIAL_DIM_TAG)) {
            int timer = tag.getInt(TIMER_TAG);
            double progress = (double) timer / TIMER_MAX * 100;
            tooltip.add(Component.translatable(ModMessages.TOOLTIP_DIM_PROGRESS, Math.round(progress) + "%"));
        } else if (tag != null && tag.contains(SAVED_DIM_TAG)) {
            tooltip.add(Component.translatable(ModMessages.TOOLTIP_DIM_SAVED, MiscHelper.getCleanDimensionName(ResourceKey.create(DIMENSION, new ResourceLocation(tag.getString(SAVED_DIM_TAG))))));
        } else {
            tooltip.add(Component.translatable(ModMessages.TOOLTIP_NO_DIM_SAVED));
        }
    }

    public static boolean hasDimAtAll(ItemStack stack){
        CompoundTag tag = stack.getOrCreateTag();
        return tag.contains(POTENTIAL_DIM_TAG) || tag.contains(SAVED_DIM_TAG);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTag();

        // Check if either of the dimension tags is present
        String dimensionTag = tag.contains(POTENTIAL_DIM_TAG) ? POTENTIAL_DIM_TAG : (tag.contains(SAVED_DIM_TAG) ? SAVED_DIM_TAG : null);

        if (dimensionTag != null) {
            String dimension = MiscHelper.getCleanDimensionName(ResourceKey.create(DIMENSION, new ResourceLocation(tag.getString(dimensionTag))));
            return Component.literal(dimension + " Sample");
        }

        return super.getName(itemStack);
    }


    private void savePotentialDim(CompoundTag tag, ResourceKey<Level> dimension) {
        tag.putString(POTENTIAL_DIM_TAG, dimension.location().toString());
        tag.putInt(TIMER_TAG, 0);
    }

    private void saveSavedDim(CompoundTag tag, String potentialDim) {
        tag.putString(SAVED_DIM_TAG, potentialDim);
        tag.remove(POTENTIAL_DIM_TAG);
        tag.remove(TIMER_TAG);
    }

    private void updateTimer(CompoundTag tag, int timer) {
        tag.putInt(TIMER_TAG, timer);
    }

    private void resetProgress(CompoundTag tag) {
        tag.remove(POTENTIAL_DIM_TAG);
        tag.remove(TIMER_TAG);
    }

    public static ResourceKey<Level> getSavedDim(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag != null && tag.contains(SAVED_DIM_TAG)) {
            String savedDimString = tag.getString(SAVED_DIM_TAG);
            ResourceLocation savedDimLocation = new ResourceLocation(savedDimString);
            return ResourceKey.create(DIMENSION, savedDimLocation);
        }
        return null;
    }

    public static ResourceKey<Level> getPotentialDim(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag != null && tag.contains(POTENTIAL_DIM_TAG)) {
            String savedDimString = tag.getString(POTENTIAL_DIM_TAG);
            ResourceLocation savedDimLocation = new ResourceLocation(savedDimString);
            return ResourceKey.create(DIMENSION, savedDimLocation);
        }
        return null;
    }

}
