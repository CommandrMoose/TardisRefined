package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.block.RootPlantBlock;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.block.device.*;
import whocraft.tardis_refined.common.block.door.BulkHeadDoorBlock;
import whocraft.tardis_refined.common.block.door.GlobalDoorBlock;
import whocraft.tardis_refined.common.block.door.RootShellDoorBlock;
import whocraft.tardis_refined.common.block.life.ARSLeavesBlock;
import whocraft.tardis_refined.common.block.life.ArsEggBlock;
import whocraft.tardis_refined.common.block.life.EyeBlock;
import whocraft.tardis_refined.common.block.life.GrowthStoneBlock;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.block.shell.RootedShellBlock;
import whocraft.tardis_refined.common.block.shell.ShellBaseBlock;

import java.util.function.Supplier;

public class TRBlockRegistry {

    public static final DeferredRegistry<Block> BLOCKS = DeferredRegistry.create(TardisRefined.MODID, Registries.BLOCK);
    // Shell Blocks
    public static final RegistrySupplier<ShellBaseBlock> ROOT_SHELL_BLOCK = register("root_shell", () -> new RootedShellBlock(BlockBehaviour.Properties.of().noOcclusion().strength(1000, 1000).sound(SoundType.CORAL_BLOCK)), true, true);
    public static final RegistrySupplier<ShellBaseBlock> GLOBAL_SHELL_BLOCK = register("tardis_shell", () -> new GlobalShellBlock(BlockBehaviour.Properties.of().noOcclusion().strength(1000, 1000).sound(SoundType.STONE).lightLevel((blocksState) -> {
        return blocksState.getValue(GlobalShellBlock.LIT) ? 13 : 0;
    })), false, false);
    // Interior
    public static final RegistrySupplier<GlobalDoorBlock> GLOBAL_DOOR_BLOCK = register("tardis_door", () -> new GlobalDoorBlock(BlockBehaviour.Properties.of().noOcclusion().strength(10, 10).sound(SoundType.STONE)), true, true);
    public static final RegistrySupplier<RootShellDoorBlock> ROOT_SHELL_DOOR = register("root_shell_door", () -> new RootShellDoorBlock(BlockBehaviour.Properties.of().noOcclusion().strength(1000, 1000)), false, true);
    // Generation Blocks
    public static final RegistrySupplier<Block> FOOLS_STONE = register("fools_stone", () -> new GrowthStoneBlock(BlockBehaviour.Properties.of().strength(3)), true, true);
    // Roots
    public static final RegistrySupplier<RootPlantBlock> ROOT_PLANT_BLOCK = register("root_plant", () -> new RootPlantBlock(BlockBehaviour.Properties.of().noOcclusion().strength(3, 3).sound(SoundType.CORAL_BLOCK)), true, true);
    public static final RegistrySupplier<BulkHeadDoorBlock> BULK_HEAD_DOOR = register("bulk_head_door", () -> new BulkHeadDoorBlock(BlockBehaviour.Properties.of().noOcclusion().strength(3, 3).sound(SoundType.CORAL_BLOCK)), true, true);
    // ARS Tree
    public static final RegistrySupplier<ArsEggBlock> ARS_EGG = register("ars_egg", () -> new ArsEggBlock(BlockBehaviour.Properties.of().noOcclusion().strength(3, 3).sound(SoundType.AZALEA_LEAVES).lightLevel((x) -> 12)), true, true);
    public static final RegistrySupplier<Block> ARS_LEAVES = register("ars_leaves", () -> new ARSLeavesBlock(BlockBehaviour.Properties.of().noOcclusion().strength(3, 3).sound(SoundType.AZALEA_LEAVES)), false, true);

    //////////// REMOVE THESE BLOCKS FROM CREATIVE TABS BEFORE PRODUCTION
    public static final RegistrySupplier<SlabBlock> ARS_LEAVES_SLAB = register("ars_leaves_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().noOcclusion().strength(3, 3).sound(SoundType.AZALEA_LEAVES)), false, true);
    public static final RegistrySupplier<FenceBlock> ARS_LEAVES_FENCE = register("ars_leaves_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().noOcclusion().strength(3, 3).sound(SoundType.AZALEA_LEAVES)), false, true);
    // Devices
    public static final RegistrySupplier<TerraformerBlock> TERRAFORMER_BLOCK = register("terraformer", () -> new TerraformerBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), true, true);
    public static final RegistrySupplier<AirLockGenerationBlock> AIR_LOCK_GENERATION_BLOCK = register("air_lock_generator", () -> new AirLockGenerationBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), false, true);

    ///////////////////////////////////////////////////////////////////////////////
    public static final RegistrySupplier<ConsoleConfigurationBlock> CONSOLE_CONFIGURATION_BLOCK = register("console_configuration", () -> new ConsoleConfigurationBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), true, true);
    public static final RegistrySupplier<AstralManipulatorBlock> ASTRAL_MANIPULATOR_BLOCK = register("astral_manipulator", () -> new AstralManipulatorBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion().lightLevel((x) -> {
        return x.getValue(AstralManipulatorBlock.POWERED) ? 15 : 0;
    })), true, true);
    public static final RegistrySupplier<AntiGravityBlock> GRAVITY_WELL = register("gravity_well", () -> new AntiGravityBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), true, true);
    public static final RegistrySupplier<CorridorTeleporterBlock> CORRIDOR_TELEPORTER = register("corridor_teleporter", () -> new CorridorTeleporterBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), true, true);
    public static final RegistrySupplier<LandingPadBlock> LANDING_PAD = register("landing_pad", () -> new LandingPadBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion().lightLevel((x) -> {
        return 12;
    })), true, true);
    public static final RegistrySupplier<FlightDetectorBlock> FLIGHT_DETECTOR = register("flight_detector", () -> new FlightDetectorBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), true, true);
    public static final RegistrySupplier<ArtronPillarBlock> ARTRON_PILLAR = register("artron_pillar", () -> new ArtronPillarBlock(BlockBehaviour.Properties.of().strength(3, 3).sound(SoundType.ANVIL).noOcclusion()), false, false);
    // Console
    public static final RegistrySupplier<GlobalConsoleBlock> GLOBAL_CONSOLE_BLOCK = register("tardis_console", () -> new GlobalConsoleBlock(BlockBehaviour.Properties.of().strength(1000, 1000).sound(SoundType.ANVIL).noOcclusion().lightLevel((x) -> {
        return x.getValue(GlobalConsoleBlock.POWERED) ? 15 : 0;
    })), true, true);
    // Blocks
    public static final RegistrySupplier<Block> ZEITON_BLOCK = register("zeiton_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL)), true, true);
    public static final RegistrySupplier<Block> ZEITON_FUSED_IRON_BLOCK = register("zeiton_fused_iron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL)), true, true);
    public static final RegistrySupplier<Block> ZEITON_FUSED_COPPER_BLOCK = register("zeiton_fused_copper_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.COPPER)), true, true);
    public static final RegistrySupplier<Block> ZEITON_ORE = register("zeiton_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)), true, true);
    public static final RegistrySupplier<Block> ZEITON_ORE_DEEPSLATE = register("deepslate_zeiton_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE).requiresCorrectToolForDrops()), true, true);
    public static final RegistrySupplier<Block> THE_EYE = register("the_eye", () -> new EyeBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)), false, false);
    public static final RegistrySupplier<LanternBlock> ZEITON_LANTERN = register("zeiton_lantern", () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).requiresCorrectToolForDrops()), true, true);
    public static final RegistrySupplier<Block> ARTRON_PILLAR_PORT = register("artron_pillar_port", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)), false, false);
  //  public static final RegistrySupplier<Block> ASTRAL_MAP = register("astral_map", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), true, true);

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, boolean addToTab, boolean registerItem) {
        RegistrySupplier<T> registryObject = BLOCKS.register(id, blockSupplier);
        if (registerItem) {
            RegistrySupplier<Item> itemSupplier = TRItemRegistry.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties()));
            if (addToTab) {
                TRItemRegistry.TAB_ITEMS.add(itemSupplier);
            }
        }
        return registryObject;
    }

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, boolean addToTab) {
        return register(id, blockSupplier, addToTab, true);
    }

}
