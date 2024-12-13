package whocraft.tardis_refined.common.tardis.themes;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.common.tardis.themes.console.*;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;


public class ConsoleTheme implements Theme {

    /**
     * Registry Key for the ConsoleTheme registry. For addon mods, use this as the registry key
     */
    public static final ResourceKey<Registry<ConsoleTheme>> CONSOLE_THEME_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "console_theme"));

    /**
     * Tardis Refined instance of the ConsoleTheme registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only
     */
    public static final DeferredRegister<ConsoleTheme> CONSOLE_THEME_DEFERRED_REGISTRY = DeferredRegister.create(TardisRefined.MODID, CONSOLE_THEME_REGISTRY_KEY);

    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> FACTORY = registerConsoleTheme("factory", new FactoryConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> CRYSTAL = registerConsoleTheme("crystal", new CrystalConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> COPPER = registerConsoleTheme("copper", new CopperConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> CORAL = registerConsoleTheme("coral", new CoralConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> TOYOTA = registerConsoleTheme("toyota", new ToyotaConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> VICTORIAN = registerConsoleTheme("victorian", new VictorianConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> MYST = registerConsoleTheme("myst", new MystConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> NUKA = registerConsoleTheme("nuka", new NukaConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> INITIATIVE = registerConsoleTheme("initiative", new InitiativeConsoleTheme());
    public static final RegistryHolder<ConsoleTheme, ConsoleTheme> REFURBISHED = registerConsoleTheme("refurbished", new RefurbishedConsoleTheme());
    private final ConsoleThemeDetails consoleThemeDetails;
    private ResourceLocation translationKey;

    public ConsoleTheme(ResourceLocation translationKey, ConsoleThemeDetails consoleThemeDetails) {
        this.translationKey = translationKey;
        this.consoleThemeDetails = consoleThemeDetails;
    }

    private static RegistryHolder<ConsoleTheme, ConsoleTheme> registerConsoleTheme(String id, ConsoleThemeDetails themeDetails) {
        return CONSOLE_THEME_DEFERRED_REGISTRY.register(id, () -> new ConsoleTheme(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), themeDetails));
    }

    public ControlSpecification[] getControlSpecificationList() {
        return consoleThemeDetails.getControlSpecification();
    }

    @Override
    public String getTranslationKey() {
        return Util.makeDescriptionId("console", this.translationKey);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getTranslationKey());
    }
}
