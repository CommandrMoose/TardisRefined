package whocraft.tardis_refined.common.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.items.KeyItem;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class LandingPadBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public LandingPadBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state.setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (!player.level().isClientSide()) {
            if (level instanceof ServerLevel serverLevel) {
                ItemStack itemStack = player.getItemInHand(interactionHand);
                if (itemStack.getItem() instanceof KeyItem) {
                    var keyChain = KeyItem.getKeychain(itemStack);
                    if (!keyChain.isEmpty()) {
                        ResourceKey<Level> dimension = KeyItem.getKeychain(itemStack).get(0);
                        var tardisLevel = Platform.getServer().getLevel(dimension);
                        var operatorOptional = TardisLevelOperator.get(tardisLevel);
                        if (operatorOptional.isEmpty()) {
                            return InteractionResult.PASS;
                        }
                        var operator = operatorOptional.get();

                        if (serverLevel.isEmptyBlock(blockPos.above()) && operator.getProgressionManager().isLevelDiscovered(level.dimension())) {

                            TardisPilotingManager pilotManager = operator.getPilotingManager();
                            UpgradeHandler upgradeHandler = operator.getUpgradeHandler();

                            if (TRUpgrades.LANDING_PAD.get().isUnlocked(upgradeHandler) && pilotManager.beginFlight(true, null) && !pilotManager.isInRecovery()) {
                                pilotManager.setTargetLocation(new TardisNavLocation(blockPos.above(), player.getDirection().getOpposite(), serverLevel));
                                serverLevel.playSound(null, blockPos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1f, 1f);
                                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.TARDIS_IS_ON_THE_WAY), true);
                                return InteractionResult.PASS;
                            } else {

                                if (TRUpgrades.LANDING_PAD.get().isUnlocked(upgradeHandler)) {
                                    PlayerUtil.sendMessage(player, Component.translatable(ModMessages.LANDING_PAD_TRANSIENT), true);
                                    serverLevel.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_BIT.value(), SoundSource.BLOCKS, 100, (float) (0.1 + (serverLevel.getRandom().nextFloat() * 0.25)));
                                } else {
                                    serverLevel.playSound(null, blockPos, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1f, 1f);
                                    PlayerUtil.sendMessage(player, Component.translatable(ModMessages.LANDING_PAD_NOT_UNLOCKED), true);
                                }

                                return InteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
                            }
                        }

                        serverLevel.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_BIT.value(), SoundSource.BLOCKS, 100, (float) (0.1 + (serverLevel.getRandom().nextFloat() * 0.25)));
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }
}
