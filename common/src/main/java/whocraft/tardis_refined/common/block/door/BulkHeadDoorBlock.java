package whocraft.tardis_refined.common.block.door;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.door.BulkHeadDoorBlockEntity;
import whocraft.tardis_refined.registry.TRItemRegistry;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.Arrays;

public class BulkHeadDoorBlock extends BaseEntityBlock {

    public enum BulkHeadType implements StringRepresentable {
        ROUGH("rough"), MODERN("modern");

        private final String name;

        BulkHeadType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final EnumProperty<BulkHeadType> TYPE = EnumProperty.create("bulkhead", BulkHeadType.class);

    public BulkHeadDoorBlock(Properties properties) {
        super(properties.sound(SoundType.ANVIL));

        this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, BulkHeadType.MODERN).setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(LOCKED, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, OPEN, LOCKED, TYPE);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {

        BlockState state = super.getStateForPlacement(blockPlaceContext);
        if (canSurvive(state, blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos())) {
            return state.setValue(FACING, blockPlaceContext.getHorizontalDirection()).setValue(OPEN, false).setValue(LOCKED, false).setValue(TYPE, BulkHeadType.MODERN);
        }
        return null;
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BulkHeadDoorBlockEntity(blockPos, blockState);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        super.onPlace(blockState, level, blockPos, blockState2, bl);

        if (blockState.getValue(OPEN)) {
            changeBlockStates(level, blockPos, blockState, Blocks.AIR.defaultBlockState());
        } else {
            changeBlockStates(level, blockPos, blockState, Blocks.BARRIER.defaultBlockState());
        }
    }


    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).getItem() == TRItemRegistry.PATTERN_MANIPULATOR.get()) {
            if (blockState.hasProperty(TYPE)) {
                BlockState nextType = blockState.cycle(TYPE);
                level.setBlock(blockPos, nextType, 3);
                level.playSound(player, blockPos, TRSoundRegistry.PATTERN_MANIPULATOR.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }


    @Override
    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(level, player, blockPos, blockState, blockEntity, itemStack);
        destroy(level, blockPos, blockState);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        super.destroy(levelAccessor, blockPos, blockState);

        changeBlockStates((Level) levelAccessor, blockPos, blockState, Blocks.AIR.defaultBlockState());
    }

    private void changeBlockStates(Level level, BlockPos blockPos, BlockState blockState, BlockState blockToSet) {
        level.setBlock(blockPos.above(), blockToSet, Block.UPDATE_CLIENTS);
        level.setBlock(blockPos.above(2), blockToSet, Block.UPDATE_CLIENTS);


        if (blockState.getValue(FACING) == Direction.NORTH || blockState.getValue(FACING) == Direction.SOUTH) {
            level.setBlock(blockPos.east(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above().east(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above(2).east(), blockToSet, Block.UPDATE_CLIENTS);

            level.setBlock(blockPos.west(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above().west(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above(2).west(), blockToSet, Block.UPDATE_CLIENTS);
        }

        if (blockState.getValue(FACING) == Direction.EAST || blockState.getValue(FACING) == Direction.WEST) {
            level.setBlock(blockPos.north(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above().north(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above(2).north(), blockToSet, Block.UPDATE_CLIENTS);

            level.setBlock(blockPos.south(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above().south(), blockToSet, Block.UPDATE_CLIENTS);
            level.setBlock(blockPos.above(2).south(), blockToSet, Block.UPDATE_CLIENTS);
        }
    }


    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);

    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return checkAirBlockStates(world, pos) && super.canSurvive(state, world, pos);
    }


    private boolean checkAirBlockStates(LevelReader world, BlockPos pos) {
        for (int y = 0; y < 3; y++) {
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    BlockPos checkPos = pos.offset(x, y, z);
                    BlockState checkState = world.getBlockState(checkPos);
                    if (!checkState.isAir() && !checkState.is(this)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(OPEN) ? Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D) : Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                bulkHeadDoorBlockEntity.tick(pLevel, blockPos, blockState, bulkHeadDoorBlockEntity);
            }
        };
    }
}
