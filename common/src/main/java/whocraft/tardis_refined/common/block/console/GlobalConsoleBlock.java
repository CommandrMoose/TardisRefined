package whocraft.tardis_refined.common.block.console;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.ClientHelper;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ConsolePatterns;
import whocraft.tardis_refined.registry.TRDimensionTypes;


public class GlobalConsoleBlock extends BaseEntityBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");


    public GlobalConsoleBlock(Properties properties) {

        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
    }




    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GlobalConsoleBlockEntity(blockPos, blockState);
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        //No Operation
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        BlockState state = super.getStateForPlacement(blockPlaceContext);

        return state.setValue(POWERED, false);
    }


    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {

        if (level.getBlockEntity(blockPos) instanceof GlobalConsoleBlockEntity globalConsoleBlockEntity) {
            ResourceLocation theme = globalConsoleBlockEntity.theme();
            globalConsoleBlockEntity.setConsoleTheme(theme);
            globalConsoleBlockEntity.setPattern(ConsolePatterns.DEFAULT);
            globalConsoleBlockEntity.markDirty();
        }

        super.onPlace(blockState, level, blockPos, blockState2, bl);

    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof GlobalConsoleBlockEntity globalConsoleBlock) {
                globalConsoleBlock.tick(level, blockPos, blockState, globalConsoleBlock);
            }
        };
    }


    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {

        if (level.getBlockEntity(blockPos) instanceof GlobalConsoleBlockEntity globalConsoleBlock) {
            globalConsoleBlock.killControls();
        }

        super.onRemove(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {

        if (levelAccessor.getBlockEntity(blockPos) instanceof GlobalConsoleBlockEntity globalConsoleBlock) {
            globalConsoleBlock.killControls();
        }

        levelAccessor.playSound(null, blockPos, SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS, 1, 1);

        super.destroy(levelAccessor, blockPos, blockState);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);

        TardisClientData clientData = TardisClientData.getInstance(level.dimension());

        if (!blockState.getValue(POWERED)) {
            return;
        }

        if (clientData != null) {

            var xCord = (double) blockPos.getX() + randomSource.nextFloat() * 1.5;
            var yCord = (double) blockPos.getY() + randomSource.nextDouble() + 0.5D;
            var zCord = (double) blockPos.getZ() + randomSource.nextFloat() * 1.5;

            if (level.random.nextInt(5) == 0) {
                ClientHelper.playParticle((ClientLevel) level, ParticleTypes.CLOUD, new Vec3(xCord, yCord, zCord), 0.0D, 0.05D, 0.0D);
            }

            if (clientData.isFlying() && level.random.nextInt(1) == 0) {

                ClientHelper.playParticle((ClientLevel) level, ParticleTypes.CLOUD, new Vec3(xCord, yCord, zCord), 0.0D, 0.05D, 0.0D);
            }

            if (clientData.isInRecovery() || clientData.isCrashing()) {

                ClientHelper.playParticle((ClientLevel) level, ParticleTypes.CAMPFIRE_COSY_SMOKE, new Vec3(xCord, yCord, zCord), 0.0D, 0.1D, 0.0D);

                for (int i = 0; i < 5; i++) {
                    ClientHelper.playParticle((ClientLevel) level, ParticleTypes.LARGE_SMOKE, new Vec3(xCord, yCord, zCord), 0.0D, 0.1D, 0.0D);
                }

                if (level.random.nextInt(10) == 0) {
                    for (int i = 0; i < 3; i++) {
                        ClientHelper.playParticle((ClientLevel) level, ParticleTypes.LAVA, new Vec3(xCord, yCord, zCord), -0.5 + level.random.nextFloat(), 0.05D, -0.5 + level.random.nextFloat());
                    }

                    level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1, level.getRandom().nextFloat() + 1f, false);
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (!player.level().isClientSide()) {
            if (level instanceof ServerLevel serverLevel && level.dimensionTypeId() == TRDimensionTypes.TARDIS) {
                TardisLevelOperator.get(serverLevel).ifPresent(operator -> {
                    TardisPilotingManager pilotingManager = operator.getPilotingManager();
                    if (serverLevel.getBlockEntity(blockPos) instanceof GlobalConsoleBlockEntity consoleBlockEntity) {
                        if (pilotingManager.getCurrentConsole() != null) {
                            if (pilotingManager.getCurrentConsole() != consoleBlockEntity) {
                                if (!pilotingManager.isInFlight()) {
                                    pilotingManager.setCurrentConsole(consoleBlockEntity);
                                    consoleBlockEntity.setTicksBooting(1);
                                } else {
                                    PlayerUtil.sendMessage(player, ModMessages.CONSOLE_NOT_IN_FLIGHT, true);
                                }
                            }
                        } else {
                            pilotingManager.setCurrentConsole(consoleBlockEntity);
                            consoleBlockEntity.getUpdatePacket();
                        }
                    }

                });

                // Creative only: Quickly complete the cooldown.
                if (player.isCreative() && player.getItemInHand(interactionHand).getItem() == Items.ICE) {
                    var operatorOptional = TardisLevelOperator.get(serverLevel);
                    if (operatorOptional.isPresent()) {
                        var operator = operatorOptional.get();
                        TardisPilotingManager pilotManager = operator.getPilotingManager();

                        if (pilotManager.isInRecovery()) {
                            pilotManager.endRecovery();
                            return InteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
                        }
                    }

                }

                if(player.getItemInHand(interactionHand).getItem() == Items.LEAD && !level.isClientSide){
                    LeadItem.bindPlayerMobs(player, level, blockPos);
                    return InteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
                }
            }
        }

        if (level.isClientSide) {
            if (level.getBlockEntity(blockPos) instanceof GlobalConsoleBlockEntity consoleBlockEntity) {
                if(!blockState.getValue(POWERED)){
                    consoleBlockEntity.powerOff.stop();
                    consoleBlockEntity.powerOn.start(player.tickCount);
                }
            }
        }


        return InteractionResult.sidedSuccess(true); //Use InteractionResult.sidedSuccess(true) for client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
    }


    @Override
    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }
}
