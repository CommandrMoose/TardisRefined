package whocraft.tardis_refined.client.model.blockentity.console;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.api.event.TardisClientEvents;
import whocraft.tardis_refined.client.ModelRegistry;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

import java.util.HashMap;

/***
 * A collection of models for rendering the console unit.
 */
public class ConsoleModelCollection {

    public static HashMap<ResourceLocation, ConsoleModelEntry> CONSOLE_MODELS = new HashMap<>();
    private static ConsoleModelCollection instance = null;
    ConsoleUnit toyotaConsoleModel, coralConsoleModel, copperConsoleModel, nukaConsoleModel, factoryConsoleModel, crystalConsoleModel, victorianConsoleModel, mystConsoleModel, initiativeConsoleModel, refurbishedConsoleModel;

    public ConsoleModelCollection() {
        var context = Minecraft.getInstance().getEntityModels();
        this.registerModels(context);
    }

    public static ConsoleModelCollection getInstance() {
        if (ConsoleModelCollection.instance == null) {
            ConsoleModelCollection.instance = new ConsoleModelCollection();
        }

        return instance;
    }

    public void registerModels(EntityModelSet context) {

        factoryConsoleModel = new FactoryConsoleModel(context.bakeLayer(ModelRegistry.FACTORY_CONSOLE));
        nukaConsoleModel = new NukaConsoleModel(context.bakeLayer(ModelRegistry.NUKA_CONSOLE));
        copperConsoleModel = new CopperConsoleModel(context.bakeLayer(ModelRegistry.COPPER_CONSOLE));
        coralConsoleModel = new CoralConsoleModel(context.bakeLayer(ModelRegistry.CORAL_CONSOLE));
        toyotaConsoleModel = new ToyotaConsoleModel(context.bakeLayer(ModelRegistry.TOYOTA_CONSOLE));
        crystalConsoleModel = new CrystalConsoleModel(context.bakeLayer(ModelRegistry.CRYSTAL_CONSOLE));
        victorianConsoleModel = new VictorianConsoleModel(context.bakeLayer(ModelRegistry.VICTORIAN_CONSOLE));
        mystConsoleModel = new MystConsoleModel(context.bakeLayer(ModelRegistry.MYST_CONSOLE));
        initiativeConsoleModel = new InitiativeConsoleModel(context.bakeLayer(ModelRegistry.INITIATIVE_CONSOLE));
        refurbishedConsoleModel = new RefurbishedConsoleModel(context.bakeLayer(ModelRegistry.REFURBISHED_CONSOLE));

        registerModel(ConsoleTheme.FACTORY.getId(), new ConsoleModelEntry(factoryConsoleModel));
        registerModel(ConsoleTheme.NUKA.getId(), new ConsoleModelEntry(nukaConsoleModel));
        registerModel(ConsoleTheme.COPPER.getId(), new ConsoleModelEntry(copperConsoleModel));
        registerModel(ConsoleTheme.CORAL.getId(), new ConsoleModelEntry(coralConsoleModel));
        registerModel(ConsoleTheme.TOYOTA.getId(), new ConsoleModelEntry(toyotaConsoleModel));
        registerModel(ConsoleTheme.CRYSTAL.getId(), new ConsoleModelEntry(crystalConsoleModel));
        registerModel(ConsoleTheme.VICTORIAN.getId(), new ConsoleModelEntry(victorianConsoleModel));
        registerModel(ConsoleTheme.MYST.getId(), new ConsoleModelEntry(mystConsoleModel));
        registerModel(ConsoleTheme.INITIATIVE.getId(), new ConsoleModelEntry(initiativeConsoleModel));
        registerModel(ConsoleTheme.REFURBISHED.getId(), new ConsoleModelEntry(refurbishedConsoleModel));

        TardisClientEvents.CONSOLE_MODELS_SETUP.invoker().setupConsoleModels(this, context);


    }

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/ConsoleModelCollection");

    /**
     * Register a new console model.
     *
     * @param consoleModel The ConsoleUnit to register.
     */
    public void registerModel(ResourceLocation resourceLocation, ConsoleModelEntry consoleModel) {
        if (consoleModel == null) {
            LOGGER.warn("Attempted to register a null console model.");
            return;
        }

        if (CONSOLE_MODELS.containsKey(resourceLocation)) {
            LOGGER.warn("Attempted to register a console model that is already registered: {}", resourceLocation);
            return;
        }

        CONSOLE_MODELS.put(resourceLocation, consoleModel);
        LOGGER.info("Registered console model: {}", resourceLocation);
    }


    public ConsoleModelEntry getConsoleEntry(ResourceLocation themeId) {
        ConsoleModelEntry consoleModelEntry = CONSOLE_MODELS.get(themeId);
        if (consoleModelEntry == null) {
            LOGGER.warn("Could not find model for {}, did you bind it?", themeId);
        }
        return consoleModelEntry;
    }
}
