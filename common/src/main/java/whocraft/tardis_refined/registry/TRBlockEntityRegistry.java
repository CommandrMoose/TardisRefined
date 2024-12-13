package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.blockentity.device.*;
import whocraft.tardis_refined.common.blockentity.door.BulkHeadDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.RootShellDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.life.ArsEggBlockEntity;
import whocraft.tardis_refined.common.blockentity.life.EyeBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.RootPlantBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.RootedShellBlockEntity;

public class TRBlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(TardisRefined.MODID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistryHolder<BlockEntityType<?>, BlockEntityType<RootedShellBlockEntity>> ROOT_SHELL = BLOCK_ENTITY_TYPES.register("root_shell", () -> BlockEntityType.Builder.of(RootedShellBlockEntity::new, TRBlockRegistry.ROOT_SHELL_BLOCK.get()).build(null));

    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<GlobalConsoleBlockEntity>> GLOBAL_CONSOLE_BLOCK = BLOCK_ENTITY_TYPES.register("tardis_console", () -> BlockEntityType.Builder.of(GlobalConsoleBlockEntity::new, TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get()).build(null));

    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<GlobalShellBlockEntity>> GLOBAL_SHELL_BLOCK = BLOCK_ENTITY_TYPES.register("tardis_shell", () -> BlockEntityType.Builder.of(GlobalShellBlockEntity::new, TRBlockRegistry.GLOBAL_SHELL_BLOCK.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<GlobalDoorBlockEntity>> GLOBAL_DOOR_BLOCK = BLOCK_ENTITY_TYPES.register("tardis_door", () -> BlockEntityType.Builder.of(GlobalDoorBlockEntity::new, TRBlockRegistry.GLOBAL_DOOR_BLOCK.get()).build(null));

    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<RootShellDoorBlockEntity>> ROOT_SHELL_DOOR = BLOCK_ENTITY_TYPES.register("root_shell_door", () -> BlockEntityType.Builder.of(RootShellDoorBlockEntity::new, TRBlockRegistry.ROOT_SHELL_DOOR.get()).build(null));

    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<RootPlantBlockEntity>> ROOT_PLANT = BLOCK_ENTITY_TYPES.register("root_plant", () -> BlockEntityType.Builder.of(RootPlantBlockEntity::new, TRBlockRegistry.ROOT_PLANT_BLOCK.get()).build(null));

    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<ArsEggBlockEntity>> ARS_EGG = BLOCK_ENTITY_TYPES.register("ars_egg", () -> BlockEntityType.Builder.of(ArsEggBlockEntity::new, TRBlockRegistry.ARS_EGG.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<BulkHeadDoorBlockEntity>> BULK_HEAD_DOOR = BLOCK_ENTITY_TYPES.register("bulk_head_door", () -> BlockEntityType.Builder.of(BulkHeadDoorBlockEntity::new, TRBlockRegistry.BULK_HEAD_DOOR.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<ConsoleConfigurationBlockEntity>> CONSOLE_CONFIGURATION = BLOCK_ENTITY_TYPES.register("console_configuration", () -> BlockEntityType.Builder.of(ConsoleConfigurationBlockEntity::new, TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<FlightDetectorBlockEntity>> FLIGHT_DETECTOR = BLOCK_ENTITY_TYPES.register("flight_detector", () -> BlockEntityType.Builder.of(FlightDetectorBlockEntity::new, TRBlockRegistry.FLIGHT_DETECTOR.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<ArtronPillarBlockEntity>> ARTRON_PILLAR = BLOCK_ENTITY_TYPES.register("artron_pillar", () -> BlockEntityType.Builder.of(ArtronPillarBlockEntity::new, TRBlockRegistry.ARTRON_PILLAR.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<AstralManipulatorBlockEntity>> ASTRAL_MANIPULATOR = BLOCK_ENTITY_TYPES.register("astral_manipulator", () -> BlockEntityType.Builder.of(AstralManipulatorBlockEntity::new, TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<EyeBlockEntity>> THE_EYE = BLOCK_ENTITY_TYPES.register("the_eye", () -> BlockEntityType.Builder.of(EyeBlockEntity::new, TRBlockRegistry.THE_EYE.get()).build(null));
    public static final RegistryHolder<BlockEntityType<?>,BlockEntityType<CorridorTeleporterBlockEntity>> CORRIDOR_TELEPORTER = BLOCK_ENTITY_TYPES.register("corridor_teleporter", () -> BlockEntityType.Builder.of(CorridorTeleporterBlockEntity::new, TRBlockRegistry.CORRIDOR_TELEPORTER.get()).build(null));


}
