package whocraft.tardis_refined.common.block.shell;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.blockentity.shell.RootedShellBlockEntity;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisExteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RootedShellBlock extends ShellBaseBlock {

    public RootedShellBlock(BlockBehaviour.Properties properties) {

        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(REGEN, false).setValue(WATERLOGGED, false).setValue(LOCKED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!player.getMainHandItem().is(Items.SHEARS)) {
            if (!blockState.getValue(OPEN)) { //If there are roots covering the entrance, tell the player
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.ROOT_PLANT_CUT_OPEN), true);
                level.playSound(player, blockPos, SoundEvents.AZALEA_LEAVES_HIT, SoundSource.BLOCKS, 1, 0.75f + level.getRandom().nextFloat());
                return ItemInteractionResult.sidedSuccess(level.isClientSide); //Stops hand swinging twice. If InteractionResult = SUCCESS then the hand swing packet is sent twice.
            }
            return ItemInteractionResult.FAIL; //Return fail result if the entrance is now opened, so that we don't play the message and exit this method early.
        }

        //From now one, we assume that the player is holding a vanilla Shears item in their main hand. We will try to generate the Tardis dimension if it doesn't exist, then open the root shell door
        if (level.getBlockEntity(blockPos) instanceof RootedShellBlockEntity rootedShellBlockEntity) {
            rootedShellBlockEntity.setUpTardisOnNextTick();
        }

        //Logic to play sounds if the player tries to break the root plant for additional sound design
        if (player != null) {
            player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            level.playSound(player, player.blockPosition(), SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0f, 1.0f);
            level.playSound(player, player.blockPosition(), SoundEvents.SLIME_JUMP, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide); //Stops hand swinging twice. If InteractionResult = SUCCESS then the hand swing packet is sent twice.
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return (level1, blockPos, block, t) -> {
            if (t instanceof RootedShellBlockEntity rootedShellBlockEntity) {
                rootedShellBlockEntity.tick(level1, blockPos, blockState, rootedShellBlockEntity);
            }
        };
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RootedShellBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        BlockState state = super.getStateForPlacement(blockPlaceContext);
        return state.setValue(FACING, blockPlaceContext.getHorizontalDirection()).setValue(OPEN, false).setValue(REGEN, false);
    }
}
