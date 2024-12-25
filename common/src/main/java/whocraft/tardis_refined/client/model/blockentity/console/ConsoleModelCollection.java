package whocraft.tardis_refined.client.model.blockentity.console;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.client.ModelRegistry;

import java.util.ArrayList;
import java.util.List;

/***
 * A collection of models for rendering the console unit.
 */
public class ConsoleModelCollection {

    public static List<ConsoleUnit> CONSOLE_MODELS = new ArrayList<>();
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

        factoryConsoleModel = new FactoryConsoleModel(context.bakeLayer((ModelRegistry.FACTORY_CONSOLE)));
        nukaConsoleModel = new NukaConsoleModel(context.bakeLayer((ModelRegistry.NUKA_CONSOLE)));
        copperConsoleModel = new CopperConsoleModel(context.bakeLayer((ModelRegistry.COPPER_CONSOLE)));
        coralConsoleModel = new CoralConsoleModel(context.bakeLayer((ModelRegistry.CORAL_CONSOLE)));
        toyotaConsoleModel = new ToyotaConsoleModel(context.bakeLayer((ModelRegistry.TOYOTA_CONSOLE)));
        crystalConsoleModel = new CrystalConsoleModel(context.bakeLayer((ModelRegistry.CRYSTAL_CONSOLE)));
        victorianConsoleModel = new VictorianConsoleModel(context.bakeLayer((ModelRegistry.VICTORIAN_CONSOLE)));
        mystConsoleModel = new MystConsoleModel(context.bakeLayer((ModelRegistry.MYST_CONSOLE)));
        initiativeConsoleModel = new InitiativeConsoleModel(context.bakeLayer((ModelRegistry.INITIATIVE_CONSOLE)));
        refurbishedConsoleModel = new RefurbishedConsoleModel(context.bakeLayer((ModelRegistry.REFURBISHED_CONSOLE)));

        CONSOLE_MODELS.add(factoryConsoleModel);
        CONSOLE_MODELS.add(nukaConsoleModel);
        CONSOLE_MODELS.add(copperConsoleModel);
        CONSOLE_MODELS.add(coralConsoleModel);
        CONSOLE_MODELS.add(toyotaConsoleModel);
        CONSOLE_MODELS.add(crystalConsoleModel);
        CONSOLE_MODELS.add(victorianConsoleModel);
        CONSOLE_MODELS.add(mystConsoleModel);
        CONSOLE_MODELS.add(initiativeConsoleModel);
        CONSOLE_MODELS.add(refurbishedConsoleModel);

    }

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/ConsoleModelCollection");


    /**
     * Get the associated console model from a console theme.
     *
     * @param themeId The Console theme.
     * @return Console unit model tied with the console theme.
     **/
    public ConsoleUnit getConsoleModel(ResourceLocation themeId) {
        for (ConsoleUnit consoleModel : CONSOLE_MODELS) {
            if (consoleModel.getConsoleTheme().toString().equals(themeId.toString())) {
                return consoleModel;
            }
        }
        LOGGER.warn("Could not find model for {}, did you bind it?", themeId);
        return null;
    }
}
