package whocraft.tardis_refined.common.block.device;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.blockentity.device.AstralManipulatorBlockEntity;
import whocraft.tardis_refined.common.items.ScrewdriverItem;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenCraftingScreen;
import whocraft.tardis_refined.common.tardis.CorridorGenerator;


public class AstralManipulatorBlock extends Block implements EntityBlock {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");


    public AstralManipulatorBlock(Properties properties) {
        super(properties);
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
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.join(Block.box(0, 0, 0, 16, 12, 16), Block.box(4, 12, 4, 12, 14, 12), BooleanOp.OR);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!(level instanceof ServerLevel serverLevel) || interactionHand != InteractionHand.MAIN_HAND) {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }

        if (!(level.getBlockEntity(blockPos) instanceof AstralManipulatorBlockEntity astralManipulatorBlockEntity)) {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }

        ItemStack itemStack = player.getItemInHand(interactionHand);

        if (itemStack.isEmpty()) {
            if(player instanceof ServerPlayer serverPlayer) {
                new S2COpenCraftingScreen().send(serverPlayer);
            }
            astralManipulatorBlockEntity.clearDisplay();
            return InteractionResult.sidedSuccess(false);
        }

        if (itemStack.getItem() instanceof ScrewdriverItem) {
            astralManipulatorBlockEntity.onRightClick(itemStack, player);
        }

        CorridorGenerator.onAttemptToUse(serverLevel, itemStack, blockPos, player);
        return InteractionResult.sidedSuccess(true);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AstralManipulatorBlockEntity(blockPos, blockState);
    }
}
