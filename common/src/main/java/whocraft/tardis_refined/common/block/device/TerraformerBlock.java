package whocraft.tardis_refined.common.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.items.ScrewdriverItem;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.registry.TRDimensionTypes;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class TerraformerBlock extends Block {

    public static BooleanProperty ACTIVE = BlockStateProperties.POWERED;

    public static VoxelShape SHAPE = Stream.of(
            Stream.of(
                    Block.box(0.5, 5, 13.5, 2.5, 10, 15.5),
                    Block.box(1.5, 10, 13.5, 1.5, 18, 15.5),
                    Block.box(0, 0, 13, 3, 5, 16)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get(),
            Stream.of(
                    Block.box(0.5, 5, 0.5, 2.5, 10, 2.5),
                    Block.box(1.5, 10, 0.5, 1.5, 18, 2.5),
                    Block.box(0, 0, 0, 3, 5, 3)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get(),
            Stream.of(
                    Block.box(13.5, 5, 0.5, 15.5, 10, 2.5),
                    Block.box(14.5, 10, 0.5, 14.5, 18, 2.5),
                    Block.box(13, 0, 0, 16, 5, 3)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get(),
            Stream.of(
                    Block.box(13.5, 5, 13.5, 15.5, 10, 15.5),
                    Block.box(14.5, 10, 13.5, 14.5, 18, 15.5),
                    Block.box(13, 0, 13, 16, 5, 16)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get(),
            Stream.of(
                    Block.box(12.5, 17.5, 12.5, 16.5, 21.5, 16.5),
                    Block.box(0, 19, 0, 16, 19, 16),
                    Block.box(12.5, 17.5, -0.5, 16.5, 21.5, 3.5),
                    Block.box(-0.5, 17.5, -0.5, 3.5, 21.5, 3.5),
                    Block.box(-0.5, 17.5, 12.5, 3.5, 21.5, 16.5),
                    Block.box(2, 17, 2, 14, 20, 14),
                    Block.box(5, 15.5, 5, 11, 17.5, 11)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get(),
            Block.box(2, 0, 2, 14, 4, 14),
            Block.box(4, 4, 4, 12, 6, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public TerraformerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVE);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        super.onPlace(blockState, level, blockPos, blockState2, bl);
        if (blockState.getValue(ACTIVE)) {
            return;
        }

    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(ACTIVE)) {
            for (int particleCount = 0; particleCount < 3; ++particleCount) {
                double particleX = (double) blockPos.getX() + randomSource.nextDouble();
                double particleY = (double) blockPos.getY() + randomSource.nextDouble() * 0.5D + 0.5D;
                double particleZ = (double) blockPos.getZ() + randomSource.nextDouble();
                level.addParticle(ParticleTypes.FLASH, particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        super.onRemove(blockState, level, blockPos, blockState2, bl);

        if (level instanceof ServerLevel serverLevel) {
            if (blockState.getValue(ACTIVE)) {
                TardisLevelOperator.get(serverLevel).ifPresent(cap -> {
                    TardisInteriorManager interiorManager = cap.getInteriorManager();
                    if (interiorManager.isWaitingToGenerate()) {
                        interiorManager.cancelDesktopChange();
                    }
                });
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (level instanceof ServerLevel serverLevel) {
            if (interactionHand == InteractionHand.MAIN_HAND) {
                if (player.getMainHandItem().getItem() instanceof ScrewdriverItem screwdriverItem) {
                    screwdriverItem.playScrewdriverSound(serverLevel, blockPos, TRSoundRegistry.SCREWDRIVER_SHORT.get());

                    if (checkIfStructure(serverLevel, blockPos)) {
                        TardisLevelOperator.get(serverLevel).ifPresent(cap -> {
                            TardisInteriorManager interiorManager = cap.getInteriorManager();
                            if (interiorManager.isWaitingToGenerate()) {
                                level.destroyBlock(blockPos, true);
                            } else {
                                if (cap.getTardisState() == TardisLevelOperator.STATE_CAVE) {
                                    interiorManager.prepareDesktop(TardisDesktops.TERRAFORMED);
                                    destroyStructure(serverLevel, blockPos);
                                    serverLevel.setBlock(blockPos, blockState.setValue(ACTIVE, true), Block.UPDATE_ALL);
                                }
                            }
                        });
                    } else {
                        blockState.setValue(ACTIVE, false);
                    }

                }
            }
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    private boolean checkIfStructure(Level level, BlockPos blockPos) {

        if (level.dimensionTypeId() != TRDimensionTypes.TARDIS) {
            return false;
        }

        BlockPos startingCorner = new BlockPos(blockPos.getX() - 1, blockPos.getY() - 1, blockPos.getZ() - 1);
        for (int x = startingCorner.getX(); x < startingCorner.getX() + 3; x++) {
            for (int z = startingCorner.getZ(); z < startingCorner.getZ() + 3; z++) {

                BlockState state = level.getBlockState(new BlockPos(x, startingCorner.getY(), z));

                if (x == startingCorner.getX() + 1 && z == startingCorner.getZ() + 1) {
                    if (state.getBlock() != Blocks.REDSTONE_BLOCK) {
                        return false;
                    }
                } else {
                    if (state.getBlock() != Blocks.COPPER_BLOCK) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    private void destroyStructure(Level level, BlockPos blockPos) {
        if (level instanceof ServerLevel serverLevel) {
            BlockPos startingCorner = new BlockPos(blockPos.getX() - 1, blockPos.getY() - 1, blockPos.getZ() - 1);
            for (int x = startingCorner.getX(); x < startingCorner.getX() + 3; x++) {
                for (int z = startingCorner.getZ(); z < startingCorner.getZ() + 3; z++) {
                    serverLevel.destroyBlock(new BlockPos(x, startingCorner.getY(), z), false);
                }
            }
            serverLevel.setBlockAndUpdate(blockPos.below(), Blocks.STONE.defaultBlockState());
            serverLevel.playSound(null, blockPos, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.BLOCKS, 10, 1.75f);
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }
}
