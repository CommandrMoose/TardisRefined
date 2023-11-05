package whocraft.tardis_refined.common.block.door;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.door.BulkHeadDoorBlockEntity;

public class BulkHeadDoorBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");

    public BulkHeadDoorBlock(Properties properties) {
        super(properties.sound(SoundType.ANVIL));

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(LOCKED, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, OPEN, LOCKED);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        BlockState state = super.getStateForPlacement(blockPlaceContext);
        return state.setValue(FACING, blockPlaceContext.getHorizontalDirection()).setValue(OPEN, false).setValue(LOCKED, false);
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
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        super.destroy(levelAccessor, blockPos, blockState);

        changeBlockStates((Level) levelAccessor, blockPos, blockState, Blocks.AIR.defaultBlockState());
    }

    private void changeBlockStates(Level level, BlockPos blockPos, BlockState blockState, BlockState blockToSet) {
        level.setBlock(blockPos.above(), blockToSet, 2);
        level.setBlock(blockPos.above(2), blockToSet, 2);


        if (blockState.getValue(FACING) == Direction.NORTH || blockState.getValue(FACING) == Direction.SOUTH) {
            level.setBlock(blockPos.east(), blockToSet, 2);
            level.setBlock(blockPos.above().east(), blockToSet, 2);
            level.setBlock(blockPos.above(2).east(), blockToSet, 2);

            level.setBlock(blockPos.west(), blockToSet, 2);
            level.setBlock(blockPos.above().west(), blockToSet, 2);
            level.setBlock(blockPos.above(2).west(), blockToSet, 2);
        }

        if (blockState.getValue(FACING) == Direction.EAST || blockState.getValue(FACING) == Direction.WEST) {
            level.setBlock(blockPos.north(), blockToSet, 2);
            level.setBlock(blockPos.above().north(), blockToSet, 2);
            level.setBlock(blockPos.above(2).north(), blockToSet, 2);

            level.setBlock(blockPos.south(), blockToSet, 2);
            level.setBlock(blockPos.above().south(), blockToSet, 2);
            level.setBlock(blockPos.above(2).south(), blockToSet, 2);
        }
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
