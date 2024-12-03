package whocraft.tardis_refined.common.block.door;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.door.InternalDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.TardisInternalDoor;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.util.TRTeleporter;

public class InternalDoorBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    /**
     * This is this door instance's understanding of if it is locked or not.
     * <br> This is needed to account for when multiple internal doors are in a Tardis, and the player is locking a different door
     */
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    protected static final VoxelShape COLLISION = Block.box(0, 0, 0, 16, 32, 16);
    protected static BlockEntity blockEntity;


    public InternalDoorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(LOCKED, false));
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (level.getBlockEntity(blockPos) instanceof InternalDoorBlockEntity door) {
            door.onBlockPlaced();
        }
        super.onPlace(blockState, level, blockPos, blockState2, bl);

    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return COLLISION;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return this.getShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return this.getDoorBlockEntity();
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public BlockEntity getDoorBlockEntity() {
        return this.blockEntity;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPEN, FACING, LOCKED);
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

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {

        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            if (serverLevel.getBlockEntity(blockPos) instanceof TardisInternalDoor door) {

                if (TardisLevelOperator.get(serverLevel).isPresent()) {
                    AABB teleportAABB = this.getCollisionShape(blockState, level, blockPos, CollisionContext.of(entity)).bounds().move(blockPos);
                    if (TRTeleporter.teleportIfCollided(serverLevel, blockPos, entity, teleportAABB)) {
                        door.onAttemptEnter(blockState, serverLevel, blockPos, entity);
                    }
                }


            }
        }
    }


}
