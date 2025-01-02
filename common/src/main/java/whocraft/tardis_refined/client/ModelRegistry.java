package whocraft.tardis_refined.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.util.PlatformWarning;

import java.util.function.Supplier;

import static whocraft.tardis_refined.TardisRefined.MODID;

public class ModelRegistry {

    public static ModelLayerLocation ROOT_PLANT_STATE_ONE = createLocation("root_plant_one", "growth");
    public static ModelLayerLocation ROOT_PLANT_STATE_TWO = createLocation("root_plant_two", "growth");
    public static ModelLayerLocation ROOT_PLANT_STATE_THREE = createLocation("root_plant_three", "growth");
    public static ModelLayerLocation ROOT_PLANT_STATE_FOUR = createLocation("root_plant_four", "growth");
    public static ModelLayerLocation ROOT_PLANT_STATE_FIVE = createLocation("root_plant_five", "growth");

    public static ModelLayerLocation FACTORY_CONSOLE = console("factory");
    public static ModelLayerLocation NUKA_CONSOLE = console("nuka");
    public static ModelLayerLocation CORAL_CONSOLE = console("coral");
    public static ModelLayerLocation COPPER_CONSOLE = console("copper");
    public static ModelLayerLocation TOYOTA_CONSOLE = console("toyota");
    public static ModelLayerLocation CRYSTAL_CONSOLE = console("crystal");
    public static ModelLayerLocation VICTORIAN_CONSOLE = console("victorian");
    public static ModelLayerLocation MYST_CONSOLE = console("myst");
    public static ModelLayerLocation INITIATIVE_CONSOLE = console("initiative");
    public static ModelLayerLocation REFURBISHED_CONSOLE = console("refurbished");


    public static ModelLayerLocation ROOT_SHELL = shell("root_shell");
    public static ModelLayerLocation FACTORY_SHELL = shell("factory_shell");
    public static ModelLayerLocation POLICE_BOX_SHELL = shell("police_box_shell");
    public static ModelLayerLocation PHONE_BOOTH_SHELL = shell("phone_booth_shell");
    public static ModelLayerLocation MYSTIC_SHELL = shell("mystic_shell");
    public static ModelLayerLocation DRIFTER_SHELL = shell("drifter_shell");
    public static ModelLayerLocation PRESENT_SHELL = shell("present_shell");
    public static ModelLayerLocation VENDING_SHELL = shell("vending_shell");
    public static ModelLayerLocation BRIEFCASE_SHELL = shell("briefcase_shell");
    public static ModelLayerLocation GROENING_SHELL = shell("groening_shell");
    public static ModelLayerLocation BIG_BEN_SHELL = shell("big_ben_shell");
    public static ModelLayerLocation NUKA_SHELL = shell("nuka_shell");
    public static ModelLayerLocation GROWTH_SHELL = shell("growth_shell");
    public static ModelLayerLocation PORTALOO_SHELL = shell("portaloo_shell");
    public static ModelLayerLocation PAGODA_SHELL = shell("pagoda_shell");
    public static ModelLayerLocation LIFT_SHELL = shell("lift_shell");
    public static ModelLayerLocation HIEROGLYPH_SHELL = shell("hieroglyph_shell");
    public static ModelLayerLocation CASTLE_SHELL = shell("castle_shell");
    public static ModelLayerLocation PATHFINDER_SHELL = shell("pathfinder_shell");
    public static ModelLayerLocation HALF_BAKED_SHELL = shell("half_baked_shell");
    public static ModelLayerLocation SHULKER_SHELL = shell("shulker_shell");

    public static ModelLayerLocation ROOT_SHELL_DOOR = interiorDoor("root_shell_door");
    public static ModelLayerLocation FACTORY_DOOR = interiorDoor("factory_door");
    public static ModelLayerLocation POLICE_BOX_DOOR = interiorDoor("police_box_door");
    public static ModelLayerLocation DRIFTER_DOOR = interiorDoor("drifter_door");
    public static ModelLayerLocation MYSTIC_DOOR = interiorDoor("mystic_door");
    public static ModelLayerLocation PHONE_BOOTH_DOOR = interiorDoor("phone_booth_door");
    public static ModelLayerLocation PRESENT_DOOR = interiorDoor("present_door");
    public static ModelLayerLocation GROENING_DOOR = interiorDoor("groening_door");
    public static ModelLayerLocation BRIEFCASE_DOOR = interiorDoor("briefcase_door");
    public static ModelLayerLocation GROWTH_DOOR = interiorDoor("growth_door");
    public static ModelLayerLocation PAGODA_DOOR = interiorDoor("pagoda_door");
    public static ModelLayerLocation HIEROGLYPH_DOOR = interiorDoor("hieroglyph_door");
    public static ModelLayerLocation CASTLE_DOOR = interiorDoor("castle_door");
    public static ModelLayerLocation NUKA_DOOR = interiorDoor("nuka_door");
    public static ModelLayerLocation PORTALOO_DOOR = interiorDoor("portaloo_door");
    public static ModelLayerLocation VENDING_DOOR = interiorDoor("vending_door");
    public static ModelLayerLocation LIFT_DOOR = interiorDoor("lift_door");
    public static ModelLayerLocation PATHFINDER_DOOR = interiorDoor("pathfinder_door");
    public static ModelLayerLocation BIG_BEN_DOOR = interiorDoor("big_ben_door");
    public static ModelLayerLocation HALF_BAKED_DOOR = interiorDoor("half_baked_door");
    public static ModelLayerLocation SHULKER_DOOR = interiorDoor("shulker_door");

    public static ModelLayerLocation ARS_EGG = createLocation("ars_egg", "living");
    public static ModelLayerLocation BULK_HEAD_DOOR = interiorDoor("bulk_head_door");

    public static ModelLayerLocation ARTRON_PILLAR = createLocation("artron_pillar", "technology");

    private static ModelLayerLocation interiorDoor(String name) {
        return createLocation(name, "door");
    }

    private static ModelLayerLocation console(String name) {
        return createLocation(name, "console");
    }

    private static ModelLayerLocation shell(String name) {
        return createLocation(name, "shell");
    }


    private static ModelLayerLocation createLocation(String name, String layer) {
        return new ModelLayerLocation(new ResourceLocation(MODID, name), layer);
    }

    public static void init() {

    }

    @ExpectPlatform
    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definitionSupplier) {
        throw new RuntimeException(PlatformWarning.addWarning(ModelRegistry.class));
    }

}
