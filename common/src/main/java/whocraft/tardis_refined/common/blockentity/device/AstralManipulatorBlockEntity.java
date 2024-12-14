package whocraft.tardis_refined.common.blockentity.device;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.block.device.AstralManipulatorBlock;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorBlockResult;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipe;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingIngredient;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorItemResult;
import whocraft.tardis_refined.common.items.ScrewdriverItem;
import whocraft.tardis_refined.common.items.ScrewdriverMode;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AstralManipulatorBlockEntity extends BlockEntity {

    // Constants
    public static final String MAN_POINT_A = "man_point_a";
    public static final String MAN_POINT_B = "man_point_b";
    public static final String SHOULD_DISPLAY = "should_display";


    private boolean shouldDisplay = false;
    private BlockPos pointABlockPos;
    private BlockPos pointBBlockPos;

    public AstralManipulatorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TRBlockEntityRegistry.ASTRAL_MANIPULATOR.get(), blockPos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        compoundTag.putBoolean(SHOULD_DISPLAY, this.shouldDisplay);

        if (this.pointABlockPos != null) {
            compoundTag.put(MAN_POINT_A, NbtUtils.writeBlockPos(this.pointABlockPos));
        }

        if (this.pointBBlockPos != null) {
            compoundTag.put(MAN_POINT_B, NbtUtils.writeBlockPos(this.pointBBlockPos));

        }
    }

    public BlockPos getPointABlockPos() {
        return this.pointABlockPos;
    }

    public BlockPos getPointBBlockPos() {
        return this.pointBBlockPos;
    }

    public void setShouldDisplay(boolean display) {
        this.shouldDisplay = display;
        this.pointABlockPos = this.getBlockPos();
        this.pointBBlockPos = this.getBlockPos();

        BlockState blockState = level.getBlockState(getBlockPos());
        if (blockState.getBlock() instanceof AstralManipulatorBlock) {
            level.setBlock(getBlockPos(), blockState.setValue(AstralManipulatorBlock.POWERED, display), Block.UPDATE_ALL);
        }


        sendUpdates();
    }

    public boolean shouldDisplay() {

        return shouldDisplay;
    }

    public void clearDisplay() {
        this.setShouldDisplay(false);
        this.pointABlockPos = this.getBlockPos();
        this.pointBBlockPos = this.getBlockPos();
        sendUpdates();
    }

    @Override
    public void load(CompoundTag tag) {

        if (tag.contains(MAN_POINT_A)) {
            this.pointABlockPos = NbtUtils.readBlockPos(tag.getCompound(MAN_POINT_A));
        }
        if (tag.contains(MAN_POINT_B)) {
            this.pointBBlockPos = NbtUtils.readBlockPos(tag.getCompound(MAN_POINT_B));
        }

        if (tag.contains(SHOULD_DISPLAY)) {
            this.shouldDisplay = tag.getBoolean(SHOULD_DISPLAY);
        }

        super.load(tag);
    }


    public void onRightClick(ItemStack itemStack, Player player) {
        if (itemStack.getItem() instanceof ScrewdriverItem screwdriverItem) {
            if (!screwdriverItem.isScrewdriverMode(itemStack, ScrewdriverMode.DRAWING)) {
                screwdriverItem.setScrewdriverMode(player, itemStack, ScrewdriverMode.DRAWING, getBlockPos(), null);
                setShouldDisplay(true);
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.ASTRAL_MANIPULATOR_ENGAGED), true);

                if (getLevel() instanceof ServerLevel serverLevel) {
                    screwdriverItem.playScrewdriverSound(serverLevel, getBlockPos(), TRSoundRegistry.SCREWDRIVER_CONNECT.get());
                }

            } else {

                List<BlockPos> points = screwdriverItem.getScrewdriverPoint(itemStack);
                screwdriverItem.setScrewdriverMode(player, itemStack, ScrewdriverMode.DISABLED, getBlockPos(), null);

                if (points.size() == 2) {
                    BlockPos pointA = points.get(0);
                    BlockPos pointB = points.get(1);

                    if (attemptToBuild(pointA, pointB)) {
                        level.playSound(null, getBlockPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1, 1.25f);
                    } else {

                        level.playSound(null, getBlockPos(), SoundEvents.ALLAY_HURT, SoundSource.BLOCKS, 1, 0.3f);
                    }

                    screwdriverItem.clearBlockPosFromScrewdriver(itemStack);
                    this.clearDisplay();
                }

                this.clearDisplay();
            }
        }
    }

    public boolean setProjectionBlockPos(BlockPos pos, boolean isPointA) {

        if (!this.shouldDisplay) {
            return false;
        }

        if (isPointA) {
            pointABlockPos = pos;
        } else {
            pointBBlockPos = pos;
        }

        if (checkIfDistanceIsTooGreat(pos)) {
            this.clearDisplay();
            sendUpdates();
            return false;

        }

        sendUpdates();
        return true;
    }

    private boolean checkIfDistanceIsTooGreat(BlockPos pos) {
        var distance = pos.distManhattan(getBlockPos());
        return distance > 25;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void sendUpdates() {
        level.sendBlockUpdated(this.getBlockPos(), level.getBlockState(this.getBlockPos()), level.getBlockState(this.getBlockPos()), Block.UPDATE_CLIENTS);
        setChanged();
    }

    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }


    private boolean attemptToBuild(BlockPos pointA, BlockPos pointB) {

        var submittedBlocks = new ArrayList<ManipulatorCraftingIngredient>();

        // Grab the absolutes for these items.
        float xDiff = Math.abs(pointA.getX() - pointB.getX());
        float yDiff = Math.abs(pointA.getY() - pointB.getY());
        float zDiff = Math.abs(pointA.getZ() - pointB.getZ());

        int smallestPointX = Math.min(pointA.getX(), pointB.getX());
        int smallestPointY = Math.min(pointA.getY(), pointB.getY());
        int smallestPointZ = Math.min(pointA.getZ(), pointB.getZ());

        BlockPos minPoint = new BlockPos(smallestPointX, smallestPointY, smallestPointZ);

        for (int y = 0; y < yDiff + 1; y++) {

            for (int x = 0; x < xDiff + 1; x++) {

                for (int z = 0; z < zDiff + 1; z++) {

                    var blockPosInWorld = new BlockPos(minPoint.getX() + x, minPoint.getY() + y, minPoint.getZ() + z);

                    submittedBlocks.add(new ManipulatorCraftingIngredient(new BlockPos(x, y, z), level.getBlockState(blockPosInWorld)));
                }
            }
        }

        // Filter recipes by the first block, will make it a LOT easier later down the line. (when I make it)
        Optional<ManipulatorCraftingIngredient> firstBlock = submittedBlocks.stream().filter(x -> x.relativeBlockPos().getX() == 0 && x.relativeBlockPos().getY() == 0 && x.relativeBlockPos().getZ() == 0).findFirst();
        if (firstBlock.isEmpty()) {
            return false;
        }

        List<ManipulatorCraftingRecipe> possibleRecipes = new ArrayList<ManipulatorCraftingRecipe>();

        for (ManipulatorCraftingRecipe recipe : ManipulatorCraftingRecipe.getAllRecipes(this.getLevel())) {

            var zeroPos = recipe.ingredients().stream().filter(x -> x.relativeBlockPos().getX() == 0 && x.relativeBlockPos().getY() == 0 && x.relativeBlockPos().getZ() == 0).findFirst();
            if (zeroPos.isPresent()) {
                if (zeroPos.get().inputBlockState().getBlock() == firstBlock.get().inputBlockState().getBlock()) {
                    possibleRecipes.add(recipe);
                }
            }

        }

        // No recipes, so no result!
        if (possibleRecipes.isEmpty()) {
            return false;
        }

        for (ManipulatorCraftingRecipe recipe : possibleRecipes) {

            if (recipe.hasSameItems(submittedBlocks)) {

                // Remove the recipe blocks.
                for (BlockPos blockPos : BlockPos.betweenClosed(pointA, pointB)) {
                    level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                }

                List<ItemEntity> droppedItems = getLevel().getEntitiesOfClass(ItemEntity.class, new AABB(pointABlockPos, pointBBlockPos).inflate(1));
                droppedItems.forEach(Entity::discard);

                if (recipe.result() instanceof ManipulatorBlockResult blockResult) {
                    BlockState blockState = blockResult.recipeOutput();
                    if (blockState != null) {
                        Vec3 centerVector = new AABB(pointABlockPos, pointBBlockPos).getCenter();
                        int min = Math.min(pointABlockPos.getY(), pointBBlockPos.getY());

                        BlockPos centerPos = new BlockPos((int) centerVector.x, min, (int) centerVector.z);
                        level.setBlock(centerPos, blockState, Block.UPDATE_ALL);
                    }

                } else {
                    if (recipe.result() instanceof ManipulatorItemResult itemResult) {
                        ItemStack itemStack = itemResult.recipeOutput();
                        if (itemStack != null) {
                            ItemEntity item = new ItemEntity(level, getBlockPos().getX() + 0.5f, getBlockPos().getY() + 1, getBlockPos().getZ() + 0.5f, itemStack);
                            level.addFreshEntity(item);
                        }
                    }
                }

                return true;
            }

        }

        return false;
    }


}
