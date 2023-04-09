package whocraft.tardis_refined.common.block.shell;

import com.mojang.brigadier.StringReader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.block.properties.ShellProperty;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.manager.TardisExteriorManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.patterns.ConsolePatterns;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.ItemRegistry;
import whocraft.tardis_refined.registry.SoundRegistry;

public class GlobalShellBlock extends ShellBaseBlock{

    public static final ShellProperty SHELL = ShellProperty.create("shell");

    public GlobalShellBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(SHELL, ShellTheme.FACTORY).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHELL);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        return super.getStateForPlacement(blockPlaceContext).setValue(SHELL, ShellTheme.FACTORY);
    }

    protected static final VoxelShape COLLISION = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);


    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return COLLISION;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if(blockState.getValue(SHELL) == ShellTheme.BRIEFCASE)
            return COLLISION;
        return super.getShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GlobalShellBlockEntity(blockPos, blockState);
    }


    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (level instanceof ServerLevel serverLevel) {

            if (player.getMainHandItem().getItem() == ItemRegistry.PATTERN_MANIPULATOR.get()) {

                if (level.getBlockEntity(blockPos) instanceof GlobalShellBlockEntity globalShellBlockEntity) {
                    ResourceKey<Level> dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(TardisRefined.MODID, globalShellBlockEntity.TARDIS_ID.toString()));

                    TardisLevelOperator lvlOps = TardisLevelOperator.get(Platform.getServer().getLevel(dimension)).get();
                    TardisExteriorManager extManager = lvlOps.getExteriorManager();

                    ShellTheme shellTheme = globalShellBlockEntity.getBlockState().getValue(SHELL);

                    if (ShellPatterns.getPatternsForTheme(shellTheme).size() == 1) {
                        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BIT, SoundSource.PLAYERS, 100, (float) (0.1 + (level.getRandom().nextFloat() * 0.5)));
                        return InteractionResult.SUCCESS;
                    }

                    ShellPattern nextPattern = ShellPatterns.next(shellTheme, globalShellBlockEntity.pattern());
                    extManager.setShellPattern(nextPattern);
                    globalShellBlockEntity.setPattern(nextPattern);
                    PlayerUtil.sendMessage(player, Component.Serializer.fromJson(new StringReader(nextPattern.name())), true);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.PATTERN_MANIPULATOR.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    globalShellBlockEntity.sendUpdates();
                    lvlOps.tardisClientData().sync();
                    player.getCooldowns().addCooldown(ItemRegistry.PATTERN_MANIPULATOR.get(), 20);
                }

                return InteractionResult.SUCCESS;
            }

            if (blockHitResult.getDirection().getOpposite() == blockState.getValue(FACING)) {
                if (serverLevel.getBlockEntity(blockPos) instanceof GlobalShellBlockEntity entity) {
                    ItemStack itemStack = player.getItemInHand(interactionHand);
                    entity.onRightClick(blockState, itemStack);
                    return InteractionResult.SUCCESS;
                }

            }
        }

        return InteractionResult.SUCCESS;
    }
}
