package whocraft.tardis_refined.common.block.console;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.block.properties.ConsoleProperty;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class GlobalConsoleBlock extends BaseEntityBlock {

    public static final ConsoleProperty CONSOLE = ConsoleProperty.create("console");

    public GlobalConsoleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CONSOLE, ConsoleTheme.FACTORY));
        System.out.println("PLACED BITCH");
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GlobalConsoleBlockEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONSOLE);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        return super.getStateForPlacement(blockPlaceContext).setValue(CONSOLE, ConsoleTheme.FACTORY);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {

        if (level.getBlockEntity(blockPos) instanceof GlobalConsoleBlockEntity globalConsoleBlock) {
            globalConsoleBlock.spawnControlEntities();
            System.out.println("PLACED BITCH");
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


}
