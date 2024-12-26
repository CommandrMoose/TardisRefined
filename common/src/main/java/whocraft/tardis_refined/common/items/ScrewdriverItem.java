package whocraft.tardis_refined.common.items;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.model.pallidium.BedrockModelUtil;
import whocraft.tardis_refined.common.blockentity.device.AstralManipulatorBlockEntity;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScrewdriverItem extends Item implements DyeableLeatherItem {

    // Constants
    public static final String SCREWDRIVER_MODE = "screwdriver_mode";
    public static final String LINKED_MANIPULATOR_POS = "linked_manipulator_pos";
    public static final String SCREWDRIVER_POINT_A = "screwdriver_point_a";
    public static final String SCREWDRIVER_POINT_B = "screwdriver_point_b";
    public static final String SCREWDRIVER_B_WAS_LAST_UPDATED = "screwdriver_b_was_last_updated_pos";

    public ScrewdriverItem(Properties properties) {
        super(properties);
    }

    public static ItemStack forceColor(ItemStack itemStack, int color) {
        itemStack.getOrCreateTagElement("display").putInt("color", color);
        return itemStack;
    }

    @Override
    public int getColor(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTagElement("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : DyeColor.PINK.getTextColor();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Minecraft.getInstance().getEntityModels().roots.forEach((location, definition) -> {
            TardisRefined.LOGGER.info("EXPORT: " + location);
            JsonObject model = BedrockModelUtil.toJsonModel(definition, location.getModel().getPath());

            // Define the absolute export folder path
            Path exportFolder = Paths.get("export_models", location.getLayer());

            // Ensure the parent folder exists, including any missing directories
            try {
                Files.createDirectories(exportFolder.getParent()); // Create parent directories if they don't exist
            } catch (IOException e) {
                TardisRefined.LOGGER.error("Failed to create directories for: " + exportFolder.getParent(), e);
                return; // Return early if we can't create the directory
            }

            // Define the file path for the model
            Path modelFile = exportFolder.resolve(location.getModel().getPath().replaceAll("_ext", "").replaceAll("int", "door") + ".json");

            // Ensure the model file's parent directory exists
            try {
                Files.createDirectories(modelFile.getParent()); // Create parent directories for model file
            } catch (IOException e) {
                TardisRefined.LOGGER.error("Failed to create parent directories for file: " + modelFile, e);
                return; // Return early if we can't create the parent directories
            }

            // Write the model to the file
            try (BufferedWriter writer = Files.newBufferedWriter(modelFile)) {
                writer.write(model.toString());
            } catch (IOException e) {
                TardisRefined.LOGGER.error("Failed to write model to file: " + modelFile, e);
                throw new RuntimeException("Failed to write model to file", e);
            }
        });

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
        CompoundTag itemTag = stack.getOrCreateTag();
        ScrewdriverMode currentMode = itemTag.contains(SCREWDRIVER_MODE)
                ? ScrewdriverMode.valueOf(itemTag.getString(SCREWDRIVER_MODE))
                : ScrewdriverMode.DISABLED;

        if (serverLevel != null) {
            if (currentMode != ScrewdriverMode.DISABLED && mode == ScrewdriverMode.DISABLED) {
                // Play the disabled sound when transitioning to DISABLED mode
                playScrewdriverSound(serverLevel, sourceChange, TRSoundRegistry.SCREWDRIVER_DISCARD.get());
            }
            if (currentMode == ScrewdriverMode.DRAWING && mode != ScrewdriverMode.DRAWING) {
                // Clear manipulator if leaving DRAWING mode
                clearLinkedManipulator(serverLevel, stack);
            }
        }

        // Update the mode in the item's NBT
        itemTag.putString(SCREWDRIVER_MODE, mode.toString());

        if (mode == ScrewdriverMode.DRAWING) {
            // Save manipulator position for DRAWING mode
            itemTag.put(LINKED_MANIPULATOR_POS, NbtUtils.writeBlockPos(sourceChange));
        }

        Block sourceBlock = player.level().getBlockState(sourceChange).getBlock();
        if(sourceBlock != null && sourceBlock != TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get()) {
            PlayerUtil.sendMessage(player, mode.toString(), true);
        }
        stack.setTag(itemTag);
    }


    public boolean isScrewdriverMode(ItemStack stack, ScrewdriverMode mode) {
        CompoundTag itemtag = stack.getOrCreateTag();

        if (itemtag.contains(SCREWDRIVER_MODE)) {
            ScrewdriverMode currentMode = ScrewdriverMode.valueOf(itemtag.getString(SCREWDRIVER_MODE));
            return mode == currentMode;
        }

        return false;
    }

    private void addBlockPosToScrewdriver(ServerLevel serverLevel, Player player, ItemStack stack, BlockPos pos) {
        CompoundTag itemtag = stack.getOrCreateTag();


        boolean isUpdatingA = true;
        String target = SCREWDRIVER_POINT_A;

        if (itemtag.contains(SCREWDRIVER_B_WAS_LAST_UPDATED)) {
            isUpdatingA = itemtag.getBoolean(SCREWDRIVER_B_WAS_LAST_UPDATED);

            if (!isUpdatingA) {
                target = SCREWDRIVER_POINT_B;
            }

        }

        itemtag.put(target, NbtUtils.writeBlockPos(pos));
        updatedLinkedManipulator(player, (ServerLevel) player.level(), stack, pos, isUpdatingA);

        itemtag.putBoolean(SCREWDRIVER_B_WAS_LAST_UPDATED, !isUpdatingA);
        stack.setTag(itemtag);

        playScrewdriverSound(serverLevel, player.getOnPos(), TRSoundRegistry.SCREWDRIVER_SHORT.get());
    }

    public void playScrewdriverSound(ServerLevel level, BlockPos pos, SoundEvent soundEvent) {
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundEvent, SoundSource.PLAYERS, 1, 0.875f + level.getRandom().nextFloat() / 4);
    }

    private void updatedLinkedManipulator(Player player, ServerLevel level, ItemStack stack, BlockPos pos, boolean isPointA) {
        CompoundTag itemtag = stack.getOrCreateTag();
        if (itemtag.contains(LINKED_MANIPULATOR_POS)) {
            BlockPos manipulator = NbtUtils.readBlockPos(itemtag.getCompound(LINKED_MANIPULATOR_POS));
            if (level.getBlockEntity(manipulator) instanceof AstralManipulatorBlockEntity astralManipulatorBlockEntity) {

                if (!astralManipulatorBlockEntity.setProjectionBlockPos(pos, isPointA)) {
                    setScrewdriverMode(player, stack, ScrewdriverMode.DISABLED, pos, level);
                }

            }
        }
    }

    private void clearLinkedManipulator(ServerLevel level, ItemStack stack) {
        CompoundTag itemtag = stack.getOrCreateTag();
        if (itemtag.contains(LINKED_MANIPULATOR_POS)) {
            BlockPos manipulator = NbtUtils.readBlockPos(itemtag.getCompound(LINKED_MANIPULATOR_POS));
            if (level.getBlockEntity(manipulator) instanceof AstralManipulatorBlockEntity astralManipulatorBlockEntity) {
                astralManipulatorBlockEntity.clearDisplay();
            }

            itemtag.remove(LINKED_MANIPULATOR_POS);
        }
    }

    public void clearBlockPosFromScrewdriver(ItemStack stack) {
        CompoundTag itemtag = stack.getOrCreateTag();
        if (itemtag.contains(SCREWDRIVER_POINT_A)) {
            itemtag.remove(SCREWDRIVER_POINT_A);
        }

        if (itemtag.contains(SCREWDRIVER_POINT_B)) {
            itemtag.remove(SCREWDRIVER_POINT_B);
        }

        if (itemtag.contains(LINKED_MANIPULATOR_POS)) {
            itemtag.remove(LINKED_MANIPULATOR_POS);
        }

        stack.setTag(itemtag);
    }

    public List<BlockPos> getScrewdriverPoint(ItemStack stack) {
        CompoundTag itemtag = stack.getOrCreateTag();
        List<BlockPos> listOfBlockPos = new ArrayList<BlockPos>();

        if (itemtag.contains(SCREWDRIVER_POINT_A)) {
            listOfBlockPos.add(NbtUtils.readBlockPos(itemtag.getCompound(SCREWDRIVER_POINT_A)));
        }

        if (itemtag.contains(SCREWDRIVER_POINT_B)) {
            listOfBlockPos.add(NbtUtils.readBlockPos(itemtag.getCompound(SCREWDRIVER_POINT_B)));
        }

        return listOfBlockPos;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);

        list.add(Component.translatable(ModMessages.TOOLTIP_SCREWDRIVER_DESCRIPTION));
    }
}

