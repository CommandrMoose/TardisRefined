package whocraft.tardis_refined.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.model.blockentity.console.*;
import whocraft.tardis_refined.client.model.blockentity.device.ArtronPillarBlockModel;
import whocraft.tardis_refined.client.model.blockentity.door.interior.*;
import whocraft.tardis_refined.client.model.blockentity.shell.internal.door.RootShellDoorModel;
import whocraft.tardis_refined.client.model.blockentity.shell.rootplant.*;
import whocraft.tardis_refined.client.model.blockentity.shell.shells.*;
import whocraft.tardis_refined.common.util.PlatformWarning;

import java.util.function.Supplier;

public class ModelRegistry {

    // Root Plants - Sorry in advance.
    public static ModelLayerLocation ROOT_PLANT_STATE_ONE;
    public static ModelLayerLocation ROOT_PLANT_STATE_TWO;
    public static ModelLayerLocation ROOT_PLANT_STATE_THREE;
    public static ModelLayerLocation ROOT_PLANT_STATE_FOUR;
    public static ModelLayerLocation ROOT_PLANT_STATE_FIVE;

    public static ModelLayerLocation FACTORY_CONSOLE = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "factory"), "console");
    public static ModelLayerLocation NUKA_CONSOLE;
    public static ModelLayerLocation CORAL_CONSOLE = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "coral"), "console");
    public static ModelLayerLocation COPPER_CONSOLE = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "copper"), "console");

    public static ModelLayerLocation TOYOTA_CONSOLE = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "toyota"), "console");
    public static ModelLayerLocation CRYSTAL_CONSOLE;
    public static ModelLayerLocation VICTORIAN_CONSOLE;
    public static ModelLayerLocation MYST_CONSOLE;
    public static ModelLayerLocation INITIATIVE_CONSOLE;
    public static ModelLayerLocation REFURBISHED_CONSOLE;

    public static ModelLayerLocation ROOT_SHELL;
    public static ModelLayerLocation FACTORY_SHELL;
    public static ModelLayerLocation POLICE_BOX_SHELL;
    public static ModelLayerLocation PHONE_BOOTH_SHELL;
    public static ModelLayerLocation MYSTIC_SHELL;
    public static ModelLayerLocation DRIFTER_SHELL;
    public static ModelLayerLocation PRESENT_SHELL;
    public static ModelLayerLocation VENDING_SHELL;
    public static ModelLayerLocation BRIEFCASE_SHELL;
    public static ModelLayerLocation GROENING_SHELL;
    public static ModelLayerLocation BIG_BEN_SHELL;
    public static ModelLayerLocation NUKA_SHELL;
    public static ModelLayerLocation GROWTH_SHELL;
    public static ModelLayerLocation PORTALOO_SHELL;
    public static ModelLayerLocation PAGODA_SHELL;
    public static ModelLayerLocation LIFT_SHELL;
    public static ModelLayerLocation HIEROGLYPH_SHELL;
    public static ModelLayerLocation CASTLE_SHELL;
    public static ModelLayerLocation PATHFINDER_SHELL;
    public static ModelLayerLocation HALF_BAKED_SHELL;


    public static ModelLayerLocation ROOT_SHELL_DOOR;
    public static ModelLayerLocation FACTORY_DOOR = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "factory_door"), "shell");
    public static ModelLayerLocation POLICE_BOX_DOOR = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "police_box_door"), "shell");
    public static ModelLayerLocation PHONE_BOOTH_DOOR;
    public static ModelLayerLocation MYSTIC_DOOR;
    public static ModelLayerLocation DRIFTER_DOOR;
    public static ModelLayerLocation PRESENT_DOOR;
    public static ModelLayerLocation VENDING_DOOR;
    public static ModelLayerLocation BRIEFCASE_DOOR;
    public static ModelLayerLocation GROENING_DOOR;
    public static ModelLayerLocation NUKA_DOOR;
    public static ModelLayerLocation GROWTH_DOOR;
    public static ModelLayerLocation PORTALOO_DOOR;
    public static ModelLayerLocation PAGODA_DOOR;
    public static ModelLayerLocation LIFT_DOOR;
    public static ModelLayerLocation HIEROGLYPH_DOOR;
    public static ModelLayerLocation CASTLE_DOOR;
    public static ModelLayerLocation PATHFINDER_DOOR = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "pathfinder_door"), "shell");;
    public static ModelLayerLocation BIG_BEN_DOOR = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "big_ben_door"), "shell");;
    public static ModelLayerLocation HALF_BAKED_DOOR;


    public static ModelLayerLocation ARS_EGG = new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "ars_egg"), "living");
    public static ModelLayerLocation BULK_HEAD_DOOR;
    public static ModelLayerLocation ARTRON_PILLAR;


    public static void init() {
        ROOT_PLANT_STATE_ONE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_plant_one"), "root_plant_one"), RootPlantStateOneModel::createBodyLayer);
        ROOT_PLANT_STATE_TWO = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_plant_two"), "root_plant_two"), RootPlantStateTwoModel::createBodyLayer);
        ROOT_PLANT_STATE_THREE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_plant_three"), "root_plant_three"), RootPlantStateThreeModel::createBodyLayer);
        ROOT_PLANT_STATE_FOUR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_plant_four"), "root_plant_four"), RootPlantStateFourModel::createBodyLayer);
        ROOT_PLANT_STATE_FIVE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_plant_five"), "root_plant_five"), RootPlantStateFiveModel::createBodyLayer);

        NUKA_CONSOLE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "nuka_console"), "nuka_console"), NukaConsoleModel::createBodyLayer);
        CRYSTAL_CONSOLE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "crystal_console"), "crystal_console"), CrystalConsoleModel::createBodyLayer);
        VICTORIAN_CONSOLE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "victorian_console"), "victorian_console"), VictorianConsoleModel::createBodyLayer);
        MYST_CONSOLE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "myst_console"), "myst_console"), MystConsoleModel::createBodyLayer);
        INITIATIVE_CONSOLE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "initiative_console"), "initiative_console"), InitiativeConsoleModel::createBodyLayer);
        REFURBISHED_CONSOLE = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "refurbished_console"), "refurbished_console"), RefurbishedConsoleModel::createBodyLayer);

        ROOT_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_shell"), "root_shell"), RootShellModel::createBodyLayer);
        FACTORY_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "factory_shell"), "factory_shell"), FactoryShellModel::createBodyLayer);
        POLICE_BOX_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "police_box_shell"), "police_box_shell"), PoliceBoxModel::createBodyLayer);
        PHONE_BOOTH_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "phone_booth_shell"), "phone_booth_shell"), PhoneBoothModel::createBodyLayer);
        MYSTIC_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "mystic_shell"), "mystic_shell"), MysticShellModel::createBodyLayer);
        DRIFTER_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "drifter_shell"), "drifter_shell"), DrifterShellModel::createBodyLayer);
        PRESENT_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "present_shell"), "present_shell"), PresentShellModel::createBodyLayer);
        VENDING_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "vending_shell"), "vending_shell"), VendingMachineShellModel::createBodyLayer);
        BRIEFCASE_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "briefcase_shell"), "briefcase_shell"), BriefcaseShellModel::createBodyLayer);
        GROENING_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "groening_shell"), "groening_shell"), GroeningShellModel::createBodyLayer);
        BIG_BEN_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "big_ben_shell"), "big_ben_shell"), BigBenShellModel::createBodyLayer);
        NUKA_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "nuka_shell"), "nuka_shell"), NukaShellModel::createBodyLayer);
        GROWTH_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "growth_shell"), "growth_shell"), GrowthShellModel::createBodyLayer);
        PORTALOO_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "portaloo_shell"), "portaloo_shell"), PortalooShellModel::createBodyLayer);
        PAGODA_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "pagoda_shell"), "pagoda_shell"), PagodaShellModel::createBodyLayer);
        LIFT_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "lift_shell"), "lift_shell"), LiftShellModel::createBodyLayer);
        HIEROGLYPH_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "hieroglyph_shell"), "hieroglyph_shell"), HieroglyphModel::createBodyLayer);
        CASTLE_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "castle_shell"), "castle_shell"), CastleShellModel::createBodyLayer);
        PATHFINDER_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "pathfinder_shell"), "pathfinder_shell"), PathfinderShellModel::createBodyLayer);
        HALF_BAKED_SHELL = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "half_baked_shell"), "half_baked_shell"), HalfBakedShellModel::createBodyLayer);


        ROOT_SHELL_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "root_shell_door"), "root_shell_door"), RootShellDoorModel::createBodyLayer);
        PHONE_BOOTH_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "phone_booth_door"), "phone_booth_door"), PhoneBoothDoorModel::createBodyLayer);
        MYSTIC_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "mystic_door"), "mystic_door"), MysticDoorModel::createBodyLayer);
        DRIFTER_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "drifter_door"), "drifter_door"), DrifterDoorModel::createBodyLayer);
        PRESENT_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "present_door"), "present_door"), PresentDoorModel::createBodyLayer);
        VENDING_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "vending_door"), "vending_door"), VendingMachineDoorModel::createBodyLayer);
        BRIEFCASE_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "briefcase_door"), "briefcase_door"), BriefcaseDoorModel::createBodyLayer);
        GROENING_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "groening_door"), "groening_door"), GroeningDoorModel::createBodyLayer);
        NUKA_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "nuka_door"), "nuka_door"), NukaDoorModel::createBodyLayer);
        GROWTH_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "growth_door"), "growth_door"), GrowthDoorModel::createBodyLayer);
        PORTALOO_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "portaloo_door"), "portaloo_door"), PortalooDoorModel::createBodyLayer);
        PAGODA_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "pagoda_door"), "pagoda_door"), PagodaDoorModel::createBodyLayer);
        LIFT_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "lift_door"), "lift_door"), LiftShellDoorModel::createBodyLayer);
        HIEROGLYPH_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "hieroglyph_door"), "hieroglyph_door"), HieroglyphShellDoor::createBodyLayer);
        CASTLE_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "castle_door"), "castle_door"), CastleShellDoorModel::createBodyLayer);
        HALF_BAKED_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "half_baked_door"), "half_baked_door"), HalfBakedDoorModel::createBodyLayer);

        BULK_HEAD_DOOR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "bulk_head_door"), "bulk_head_door"), BulkHeadDoorModel::createBodyLayer);
        ARTRON_PILLAR = register(new ModelLayerLocation(new ResourceLocation(TardisRefined.MODID, "artron_pillar"), "artron_pillar"), ArtronPillarBlockModel::createBodyLayer);

    }

    @ExpectPlatform
    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definitionSupplier) {
        throw new RuntimeException(PlatformWarning.addWarning(ModelRegistry.class));
    }

}
