package whocraft.tardis_refined.common.block.life;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.life.ArsEggBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;
import whocraft.tardis_refined.common.util.TardisHelper;

import java.util.stream.Stream;

public class ArsEggBlock extends BaseEntityBlock {

    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty ALIVE = BooleanProperty.create("alive");
    ;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape AABB = Shapes.join(Stream.of(
            Block.box(4, 9, 7, 12, 10, 9),
            Block.box(4, 9, 7, 12, 10, 9),
            Block.box(5, 9, 5, 11, 11, 11),
            Block.box(6, 11, 6, 10, 13, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Block.box(4, 0, 4, 12, 9, 12), BooleanOp.OR);

    private static final VoxelShape NO_CLAMP_AABB = Block.box(4, 0, 4, 12, 9, 12);

    public ArsEggBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, false).setValue(WATERLOGGED, false).setValue(ALIVE, true));
    }

    protected static Direction getConnectedDirection(BlockState blockState) {
        return blockState.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(HANGING) ? AABB.move(0, 0.3, 0) : (blockState.getValue(ALIVE) ? AABB : NO_CLAMP_AABB);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        Direction[] var3 = blockPlaceContext.getNearestLookingDirections();

        for (Direction direction : var3) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (blockState.canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos())) {
                    return blockState.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER).setValue(ALIVE, TardisHelper.isInArsArea(blockPlaceContext.getClickedPos()));
                }
            }
        }

        return null;
    }

    @Override
    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return getConnectedDirection(blockState).getOpposite() == direction && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED, ALIVE);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = getConnectedDirection(blockState).getOpposite();
        return Block.canSupportCenter(levelReader, blockPos.relative(direction), direction.getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ArsEggBlockEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player instanceof ServerPlayer serverPlayer) {
            TardisLevelOperator.get(serverPlayer.serverLevel()).ifPresent(tardisLevelOperator -> {
                if (TardisHelper.isInArsArea(blockPos)) {
                    CompoundTag upgradeNbt = tardisLevelOperator.getUpgradeHandler().saveData(new CompoundTag());
                    new S2CDisplayUpgradeScreen(upgradeNbt).send(serverPlayer);
                }
            });
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
