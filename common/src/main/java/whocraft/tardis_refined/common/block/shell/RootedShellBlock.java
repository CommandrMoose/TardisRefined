package whocraft.tardis_refined.common.block.shell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.blockentity.shell.RootedShellBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.ShellBaseBlockEntity;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisExteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.util.UUID;

public class RootedShellBlock extends ShellBaseBlock {

    public RootedShellBlock(BlockBehaviour.Properties properties) {

        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(REGEN, false));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if(!player.getMainHandItem().is(Items.SHEARS)) return InteractionResult.FAIL;

        this.setUpTardis(blockState, level, blockPos);

        return InteractionResult.SUCCESS; //Prevents processing the ShellBaseBlockEntity generating another UUID and causing a second dimension to be created
    }

    private boolean setUpTardis(BlockState blockState, Level level, BlockPos blockPos){
        if (level instanceof ServerLevel serverLevel) {
            if (level.getBlockEntity(blockPos) instanceof ShellBaseBlockEntity shellBaseBlockEntity) {
                if (shellBaseBlockEntity.shouldSetup()){
                    //Create a Level Key with a randomised UUID
                    ResourceKey<Level> generatedLevelKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TardisRefined.MODID, UUID.randomUUID().toString()));

                    //Set the shell with this level
                    shellBaseBlockEntity.setTardisId(generatedLevelKey);
                    //Create the Level on demand which will create our capability
                    ServerLevel interior = DimensionHandler.getOrCreateInterior(level, shellBaseBlockEntity.getTardisId().location());

                    TardisLevelOperator.get(interior).ifPresent(tardisLevelOperator -> {
                        TardisInteriorManager intManager = tardisLevelOperator.getInteriorManager();
                        TardisExteriorManager extManager = tardisLevelOperator.getExteriorManager();
                        TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
                        if (!tardisLevelOperator.hasInitiallyGenerated()) {
                            intManager.generateDesktop(TardisDesktops.DEFAULT_OVERGROWN_THEME);
                            Direction direction = blockState.getValue(FACING).getOpposite();
                            TardisNavLocation navLocation = new TardisNavLocation(blockPos, direction, serverLevel);
                            extManager.setLastKnownLocation(navLocation);
                            pilotManager.setTargetLocation(navLocation);
                            tardisLevelOperator.setInitiallyGenerated(true);
                            level.setBlock(blockPos, blockState.setValue(OPEN, true), Block.UPDATE_ALL);
                        }
                    });
                    return true;
                }
            }
        }
        return false;
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
