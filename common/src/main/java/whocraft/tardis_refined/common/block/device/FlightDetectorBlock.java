package whocraft.tardis_refined.common.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.device.FlightDetectorBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;

public class FlightDetectorBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 16);
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    public FlightDetectorBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FlightDetectorBlockEntity(blockPos, blockState);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return blockState.getValue(LEVEL);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (level1, blockPos, blockState1, blockEntity) -> {
            if (level1.getGameTime() % 20L == 0L) {
                if (level1 instanceof ServerLevel serverLevel) {
                    TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
                        TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
                        int powerLevel = pilotManager.isInFlight() ? Mth.clamp((int) (pilotManager.getFlightPercentageCovered() * 16), 1, 16) : 0;
                        serverLevel.setBlock(blockPos, blockState1.setValue(LEVEL, powerLevel), Block.UPDATE_ALL);
                    });
                }
            }
        };
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state.setValue(FACING, context.getHorizontalDirection()).setValue(LEVEL, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEVEL);
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }
}
