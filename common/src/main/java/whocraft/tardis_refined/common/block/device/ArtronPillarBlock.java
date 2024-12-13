package whocraft.tardis_refined.common.block.device;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.device.ArtronPillarBlockEntity;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRSoundRegistry;

public class ArtronPillarBlock extends BaseEntityBlock {

    public static BooleanProperty ACTIVE = BlockStateProperties.POWERED;

    public ArtronPillarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        super.onPlace(blockState, level, blockPos, blockState2, bl);

        if (level.getBlockState(blockPos.below()).getBlock() == TRBlockRegistry.ARTRON_PILLAR_PORT.get()) {
            level.setBlock(blockPos, blockState.setValue(ACTIVE, true), Block.UPDATE_ALL);
            level.playSound(null, blockPos, TRSoundRegistry.ARTRON_PILLAR_ACTIVE.get(), SoundSource.BLOCKS, 100, 1 + (level.getRandom().nextFloat() * 0.25f));
        }


    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ArtronPillarBlockEntity(blockPos, blockState);
    }
}
