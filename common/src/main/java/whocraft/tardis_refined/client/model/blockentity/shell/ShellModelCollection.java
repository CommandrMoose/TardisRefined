package whocraft.tardis_refined.client.model.blockentity.shell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.api.event.TardisClientEvents;
import whocraft.tardis_refined.client.ModelRegistry;
import whocraft.tardis_refined.client.model.blockentity.door.interior.*;
import whocraft.tardis_refined.client.model.blockentity.shell.shells.*;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;

import java.util.HashMap;
import java.util.Map;

public class ShellModelCollection {

    public static Map<ResourceLocation, ShellEntry> SHELL_MODELS = new HashMap<>();
    private static ShellModel factoryShellModel, policeBoxModel, phoneBoothModel, mysticModel, drifterModel,
            presentModel, vendingModel, briefcaseModel, groeningModel, bigBenModel, nukaModel, growthModel,
            portalooModel, pagodaModel, liftModel, hieroglyphModel, castleShellModel, pathfinderShellModel, halfBakedShellModel;
    private static ShellDoorModel factoryDoorModel, policeBoxDoorModel, phoneBoothDoorModel, mysticDoorModel, drifterDoorModel, presentDoorModel, vendingDoorModel, briefcaseDoorModel,
            groeningDoorModel, bigBenDoorModel, nukaDoorModel, growthDoorModel, portalooDoorModel, pagodaDoorModel, liftDoorModel, hieroglyphDoorModel, castleDoorModel, pathfinderDoorModel, halfBakedDoorModel;
    private static ShellModelCollection instance = null;

    public ShellModelCollection() {
        var context = Minecraft.getInstance().getEntityModels();
        this.registerModels(context);
    }

    public static void registerShellEntry(ShellTheme theme, ShellModel shellModel, ShellDoorModel shellDoorModel) {
        SHELL_MODELS.put(ShellTheme.getKey(theme), new ShellEntry(shellModel, shellDoorModel));
    }

    public static void registerShellEntry(ShellTheme theme, ShellEntry shellEntry) {
        SHELL_MODELS.put(ShellTheme.getKey(theme), shellEntry);
    }

    public static ShellModelCollection getInstance() {
        if (ShellModelCollection.instance == null) {
            ShellModelCollection.instance = new ShellModelCollection();
        }

        return instance;
    }

    public void registerModels(EntityModelSet context) {

        // Shells
        factoryShellModel = new FactoryShellModel(context.bakeLayer((ModelRegistry.FACTORY_SHELL)));
        policeBoxModel = new PoliceBoxModel(context.bakeLayer((ModelRegistry.POLICE_BOX_SHELL)));
        phoneBoothModel = new PhoneBoothModel(context.bakeLayer((ModelRegistry.PHONE_BOOTH_SHELL)));
        mysticModel = new MysticShellModel(context.bakeLayer((ModelRegistry.MYSTIC_SHELL)));
        drifterModel = new DrifterShellModel(context.bakeLayer((ModelRegistry.DRIFTER_SHELL)));
        presentModel = new PresentShellModel(context.bakeLayer((ModelRegistry.PRESENT_SHELL)));
        vendingModel = new VendingMachineShellModel(context.bakeLayer((ModelRegistry.VENDING_SHELL)));
        briefcaseModel = new BriefcaseShellModel(context.bakeLayer((ModelRegistry.BRIEFCASE_SHELL)));
        groeningModel = new GroeningShellModel(context.bakeLayer((ModelRegistry.GROENING_SHELL)));
        bigBenModel = new BigBenShellModel(context.bakeLayer((ModelRegistry.BIG_BEN_SHELL)));
        nukaModel = new NukaShellModel(context.bakeLayer((ModelRegistry.NUKA_SHELL)));
        growthModel = new GrowthShellModel(context.bakeLayer((ModelRegistry.GROWTH_SHELL)));
        portalooModel = new PortalooShellModel(context.bakeLayer((ModelRegistry.PORTALOO_SHELL)));
        pagodaModel = new PagodaShellModel(context.bakeLayer((ModelRegistry.PAGODA_SHELL)));
        liftModel = new LiftShellModel(context.bakeLayer((ModelRegistry.LIFT_SHELL)));
        hieroglyphModel = new HieroglyphModel(context.bakeLayer((ModelRegistry.HIEROGLYPH_SHELL)));
        castleShellModel = new CastleShellModel(context.bakeLayer((ModelRegistry.CASTLE_SHELL)));
        pathfinderShellModel = new PathfinderShellModel(context.bakeLayer((ModelRegistry.PATHFINDER_SHELL)));
        halfBakedShellModel = new HalfBakedShellModel(context.bakeLayer((ModelRegistry.HALF_BAKED_SHELL)));

        // Doors
        factoryDoorModel = new LeftRightInteriorDoorModel(context.bakeLayer((ModelRegistry.FACTORY_DOOR)), 250f);
        policeBoxDoorModel = new PoliceBoxDoorModel(context.bakeLayer((ModelRegistry.POLICE_BOX_DOOR)));
        phoneBoothDoorModel = new PhoneBoothDoorModel(context.bakeLayer((ModelRegistry.PHONE_BOOTH_DOOR)));
        mysticDoorModel = new MysticDoorModel(context.bakeLayer((ModelRegistry.MYSTIC_DOOR)));
        drifterDoorModel = new DrifterDoorModel(context.bakeLayer((ModelRegistry.DRIFTER_DOOR)));
        presentDoorModel = new PresentDoorModel(context.bakeLayer((ModelRegistry.PRESENT_DOOR)));
        vendingDoorModel = new VendingMachineDoorModel(context.bakeLayer((ModelRegistry.VENDING_DOOR)));
        briefcaseDoorModel = new BriefcaseDoorModel(context.bakeLayer((ModelRegistry.BRIEFCASE_DOOR)));
        groeningDoorModel = new GroeningDoorModel(context.bakeLayer((ModelRegistry.GROENING_DOOR)));
        bigBenDoorModel = new SingleInteriorDoorModel(context.bakeLayer((ModelRegistry.BIG_BEN_DOOR)), 275f);
        nukaDoorModel = new NukaDoorModel(context.bakeLayer((ModelRegistry.NUKA_DOOR)));
        growthDoorModel = new GrowthDoorModel(context.bakeLayer((ModelRegistry.GROWTH_DOOR)));
        portalooDoorModel = new PortalooDoorModel(context.bakeLayer((ModelRegistry.PORTALOO_DOOR)));
        pagodaDoorModel = new PagodaDoorModel(context.bakeLayer((ModelRegistry.PAGODA_DOOR)));
        liftDoorModel = new LiftShellDoorModel(context.bakeLayer((ModelRegistry.LIFT_DOOR)));
        hieroglyphDoorModel = new HieroglyphShellDoor(context.bakeLayer((ModelRegistry.HIEROGLYPH_DOOR)));
        castleDoorModel = new CastleShellDoorModel(context.bakeLayer((ModelRegistry.CASTLE_DOOR)));
        pathfinderDoorModel = new LeftRightInteriorDoorModel(context.bakeLayer((ModelRegistry.PATHFINDER_DOOR)), 275f);
        halfBakedDoorModel = new HalfBakedDoorModel(context.bakeLayer((ModelRegistry.HALF_BAKED_DOOR)));

        TardisClientEvents.SHELLENTRY_MODELS_SETUP.invoker().setUpShellAndInteriorModels(context);

        registerShellEntry(ShellTheme.FACTORY.get(), factoryShellModel, factoryDoorModel);
        registerShellEntry(ShellTheme.POLICE_BOX.get(), policeBoxModel, policeBoxDoorModel);
        registerShellEntry(ShellTheme.PHONE_BOOTH.get(), phoneBoothModel, phoneBoothDoorModel);
        registerShellEntry(ShellTheme.MYSTIC.get(), mysticModel, mysticDoorModel);
        registerShellEntry(ShellTheme.DRIFTER.get(), drifterModel, drifterDoorModel);
        registerShellEntry(ShellTheme.PRESENT.get(), presentModel, presentDoorModel);
        registerShellEntry(ShellTheme.VENDING.get(), vendingModel, vendingDoorModel);
        registerShellEntry(ShellTheme.BRIEFCASE.get(), briefcaseModel, briefcaseDoorModel);
        registerShellEntry(ShellTheme.GROENING.get(), groeningModel, groeningDoorModel);
        registerShellEntry(ShellTheme.BIG_BEN.get(), bigBenModel, bigBenDoorModel);
        registerShellEntry(ShellTheme.NUKA.get(), nukaModel, nukaDoorModel);
        registerShellEntry(ShellTheme.GROWTH.get(), growthModel, growthDoorModel);
        registerShellEntry(ShellTheme.PORTALOO.get(), portalooModel, portalooDoorModel);
        registerShellEntry(ShellTheme.PAGODA.get(), pagodaModel, pagodaDoorModel);
        registerShellEntry(ShellTheme.LIFT.get(), liftModel, liftDoorModel);
        registerShellEntry(ShellTheme.HIEROGLYPH.get(), hieroglyphModel, hieroglyphDoorModel);
        registerShellEntry(ShellTheme.CASTLE.get(), castleShellModel, castleDoorModel);
        registerShellEntry(ShellTheme.PATHFINDER.get(), pathfinderShellModel, pathfinderDoorModel);
        registerShellEntry(ShellTheme.HALF_BAKED.get(), halfBakedShellModel, halfBakedDoorModel);
        validateModels();
    }

    private void validateModels() {
        for (ResourceLocation resourceLocation : ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.keySet()) {
            if(!SHELL_MODELS.containsKey(resourceLocation)){
                TardisRefined.LOGGER.info("There was no model setup for shell theme {}", resourceLocation);
            }
        }
    }

    /**
     * Get the associated shell model from a shell theme.
     *
     * @param themeId The Shell theme Id.
     * @return Shell model tied with the shell theme.
     **/
    public ShellEntry getShellEntry(ResourceLocation themeId) {
        return SHELL_MODELS.get(themeId);
    }

}
