package whocraft.tardis_refined.common.block.device;

import com.mojang.brigadier.StringReader;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.blockentity.device.ConsoleConfigurationBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.constants.ResourceConstants;
import whocraft.tardis_refined.patterns.ConsolePattern;
import whocraft.tardis_refined.patterns.ConsolePatterns;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TRDimensionTypes;
import whocraft.tardis_refined.registry.TRItemRegistry;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.List;
import java.util.stream.Stream;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class ConsoleConfigurationBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static VoxelShape SHAPE = Stream.of(
            Shapes.join(Block.box(2, 6, 8, 14, 11, 8), Block.box(2, 6, 8, 14, 11, 8), OR),
            Block.box(5, 9, 5, 11, 9, 11),
            Block.box(4, 4, 4, 12, 6, 12),
            Block.box(2, 0, 2, 14, 4, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();

    public ConsoleConfigurationBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state.setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (interactionHand != InteractionHand.MAIN_HAND) {
            return ItemInteractionResult.CONSUME;
        }

        if (!player.level().isClientSide()) {

            var offset = blockState.getValue(FACING).getNormal();
            BlockPos consolePos = blockPos.offset(offset);

            if (player.getMainHandItem().getItem() == TRItemRegistry.PATTERN_MANIPULATOR.get()) {
                this.changePattern(level, blockPos, consolePos, player);
                return ItemInteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
            }


            if (level instanceof ServerLevel serverLevel) {
                if (serverLevel.dimensionTypeRegistration() == TRDimensionTypes.TARDIS) {
                    TardisLevelOperator.get(serverLevel).ifPresent(operator -> {
                        if (!operator.getPilotingManager().isInFlight()) {
                            if (player.isShiftKeyDown()) { //If we are destroying the console block
                                this.removeGlobalConsoleBlock(consolePos, level);
                            } else {
                                this.changeConsoleTheme(level, blockPos, consolePos);
                            }
                        } else {
                            PlayerUtil.sendMessage(player, Component.translatable(ModMessages.CONSOLE_CONFIGURATION_NOT_IN_FLIGHT), true);
                        }
                    });
                } else {
                    if (player.isShiftKeyDown()) { //If we are destroying the console block
                        this.removeGlobalConsoleBlock(consolePos, level);
                    } else {
                        this.changeConsoleTheme(level, blockPos, consolePos);
                    }
                }
            }


            return ItemInteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
        }

        return ItemInteractionResult.sidedSuccess(true); //Use InteractionResult.sidedSuccess(true) for client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
    }

    /**
     * Places a Global Console block at the specified position, with the same theme as the Console Configuration block.
     *
     * @param level           The level the Global Console block will be placed in.
     * @param configuratorPos - The position where this current configurator block is at
     * @param consolePos      The position to place the Global Console block at.
     */
    private boolean placeNewGlobalConsoleBlock(Level level, BlockPos configuratorPos, BlockPos consolePos) {
        BlockEntity expectedConfiguratorBlockEntity = level.getBlockEntity(configuratorPos);

        if (expectedConfiguratorBlockEntity instanceof ConsoleConfigurationBlockEntity consoleConfigurationBlockEntity) {

            ResourceLocation consoleThemeId = consoleConfigurationBlockEntity.theme();

            level.setBlockAndUpdate(consolePos, TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get().defaultBlockState());

            BlockEntity expectedConsoleBlockEntity = level.getBlockEntity(consolePos);

            if (expectedConsoleBlockEntity instanceof GlobalConsoleBlockEntity globalConsoleBlockEntity) { //Set console theme
                globalConsoleBlockEntity.setConsoleTheme(consoleThemeId);

                level.playSound(null, consolePos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 3, 0.45f);

                if (level.isClientSide()) {
                    this.playParticles(configuratorPos, level);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the Global Console block at the specified position and kills its controls.
     *
     * @param consolePos The position of the Global Console block to be removed.
     * @param level      The level the Global Console block is in.
     */
    private boolean removeGlobalConsoleBlock(BlockPos consolePos, Level level) {
        BlockEntity blockEntity = level.getBlockEntity(consolePos);
        if (blockEntity instanceof GlobalConsoleBlockEntity consoleBlockEntity) {
            consoleBlockEntity.killControls();
            level.destroyBlock(consolePos, true);
            return true;
        }
        return false;
    }

    /**
     * Change the pattern of the console blocks' current theme
     *
     * @param level
     * @param configuratorPos
     * @param consolePos
     * @param player
     * @return
     */
    private boolean changePattern(Level level, BlockPos configuratorPos, BlockPos consolePos, Player player) {

        BlockEntity expectedConfiguratorBlockEntity = level.getBlockEntity(configuratorPos);

        BlockEntity expectedConsoleBlockEntity = level.getBlockEntity(consolePos);

        if (expectedConfiguratorBlockEntity instanceof ConsoleConfigurationBlockEntity consoleConfigurationBlockEntity) {
            if (expectedConsoleBlockEntity instanceof GlobalConsoleBlockEntity globalConsoleBlockEntity) { //Change console theme
                ResourceLocation currentConsoleThemeId = globalConsoleBlockEntity.theme();

                if (ConsolePatterns.getPatternsForTheme(currentConsoleThemeId).size() == 1) { //If there is only one pattern for this theme, don't iterate and warn the player
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BIT.value(), SoundSource.PLAYERS, 100, (float) (0.1 + (level.getRandom().nextFloat() * 0.5)));
                    return false;
                }

                globalConsoleBlockEntity.setPattern(ConsolePatterns.next(currentConsoleThemeId, globalConsoleBlockEntity.pattern()));
                PlayerUtil.sendMessage(player, Component.Serializer.fromJson(globalConsoleBlockEntity.pattern().name(), level.registryAccess()), true);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), TRSoundRegistry.PATTERN_MANIPULATOR.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

                player.getCooldowns().addCooldown(TRItemRegistry.PATTERN_MANIPULATOR.get(), 20);
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the theme of the Console Configuration block and its adjacent Global Console block using the next theme from the configurator's current theme.
     *
     * @param level           The level the Console Configuration and Global Console blocks are in.
     * @param configuratorPos The position of the Console Configuration block.
     * @param consolePos      - The configurator block entity which we expect to not be null
     */
    private boolean changeConsoleTheme(Level level, BlockPos configuratorPos, BlockPos consolePos) {

        BlockEntity expectedConfiguratorBlockEntity = level.getBlockEntity(configuratorPos);

        BlockEntity expectedConsoleBlockEntity = level.getBlockEntity(consolePos);

        if (expectedConfiguratorBlockEntity instanceof ConsoleConfigurationBlockEntity configurationBlockEntity) { //Change console theme
            ResourceLocation nextTheme = this.nextTheme(configurationBlockEntity);

            configurationBlockEntity.setConsoleTheme(nextTheme);

            if (expectedConsoleBlockEntity instanceof GlobalConsoleBlockEntity consoleBlockEntity) {

                consoleBlockEntity.setConsoleTheme(nextTheme);

                //Kill previous controls and update control positions to that of the current theme
                consoleBlockEntity.spawnControlEntities();

                // Change theme and set the current pattern to the default
                ConsolePattern defaultOrEquivalentPattern = ConsolePatterns.getPatternOrDefault(nextTheme, ResourceConstants.DEFAULT_PATTERN_ID);

                if (defaultOrEquivalentPattern != null) {
                    consoleBlockEntity.setPattern(defaultOrEquivalentPattern);

                    level.playSound(null, consolePos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 3, 0.45f);

                    if (level.isClientSide()) {
                        this.playParticles(configuratorPos, level);
                    }
                }
            }
            return true;
        }
        return false;
    }

    private ResourceLocation nextTheme(ConsoleConfigurationBlockEntity blockEntity) {
        ResourceLocation consoleThemeId = blockEntity.theme();
        //Get next console theme
        List<ResourceLocation> themesList = ConsoleTheme.CONSOLE_THEME_REGISTRY.keySet().stream().toList();
        int index = themesList.indexOf(consoleThemeId);
        int nextIndex = index + 1;
        if (nextIndex >= themesList.size()) {
            nextIndex = 0;
        }
        return themesList.get(nextIndex);

    }

    private void playParticles(BlockPos pos, Level level) {
        for (int i = 0; i < 3; i++) {
            double xCoord = pos.getX() + level.getRandom().nextDouble();
            double yCoord = pos.getY() + level.getRandom().nextDouble() * 0.5D + 0.5D;
            double zCoord = pos.getZ() + level.getRandom().nextDouble();
            level.addParticle(ParticleTypes.FLASH, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.CLOUD, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ConsoleConfigurationBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }
}
