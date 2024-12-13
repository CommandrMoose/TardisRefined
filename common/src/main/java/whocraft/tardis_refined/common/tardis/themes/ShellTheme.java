package whocraft.tardis_refined.common.tardis.themes;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplierHolder;

public class ShellTheme implements Theme {

    /**
     * Registry Key for the ShellTheme registry. For addon mods, use this as the registry key
     */
    public static final ResourceKey<Registry<ShellTheme>> SHELL_THEME_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "shell_theme"));

    /**
     * Tardis Refined instance of the ShellTheme registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only
     */
    public static final DeferredRegistry<ShellTheme> SHELL_THEME_DEFERRED_REGISTRY = DeferredRegistry.createCustom(TardisRefined.MODID, SHELL_THEME_REGISTRY_KEY, true);

    /**
     * Instance of registry containing all ShellTheme entries. Addon mod entries will be included in this registry as long as they are use the same ResourceKey<Registry<ObjectType>>.
     */
    public static final Registry<ShellTheme> SHELL_THEME_REGISTRY = SHELL_THEME_DEFERRED_REGISTRY.getRegistry().get();

    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> HALF_BAKED = registerShellTheme("half_baked"); // The default shell. Do not remove.

    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> FACTORY = registerShellTheme("factory");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> POLICE_BOX = registerShellTheme("police_box", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> PHONE_BOOTH = registerShellTheme("phone_booth", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> MYSTIC = registerShellTheme("mystic", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> PRESENT = registerShellTheme("present");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> DRIFTER = registerShellTheme("drifter");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> VENDING = registerShellTheme("vending", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> BRIEFCASE = registerShellTheme("briefcase");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> GROENING = registerShellTheme("groening", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> BIG_BEN = registerShellTheme("big_ben", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> NUKA = registerShellTheme("nuka", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> GROWTH = registerShellTheme("growth");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> PORTALOO = registerShellTheme("portaloo");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> PAGODA = registerShellTheme("pagoda");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> LIFT = registerShellTheme("lift", true);
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> HIEROGLYPH = registerShellTheme("hieroglyph");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> CASTLE = registerShellTheme("castle");
    public static final RegistrySupplierHolder<ShellTheme, ShellTheme> PATHFINDER = registerShellTheme("pathfinder");
    private ResourceLocation translationKey;
    private boolean producesLight;

    public ShellTheme(ResourceLocation translationKey, boolean producesLight) {
        this.translationKey = translationKey;
        this.producesLight = producesLight;
    }
    public ShellTheme(ResourceLocation translationKey) {
        this(translationKey, false);
    }

    public static ShellTheme getShellTheme(ResourceLocation resourceLocation) {
        ShellTheme potentialTheme = SHELL_THEME_REGISTRY.get(resourceLocation);
        if (potentialTheme != null) {
            return potentialTheme;
        }
        return HALF_BAKED.get();
    }

    public static ResourceLocation getKey(ShellTheme shellTheme) {
        return SHELL_THEME_REGISTRY.getKey(shellTheme);
    }

    private static RegistrySupplierHolder<ShellTheme, ShellTheme> registerShellTheme(String id) {
        return SHELL_THEME_DEFERRED_REGISTRY.registerHolder(id, () -> new ShellTheme(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), false));
    }

    private static RegistrySupplierHolder<ShellTheme, ShellTheme> registerShellTheme(String id, boolean producesLight) {
        return SHELL_THEME_DEFERRED_REGISTRY.registerHolder(id, () -> new ShellTheme(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), producesLight));
    }


    @Override
    public String getTranslationKey() {
        return Util.makeDescriptionId("shell", this.translationKey);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getTranslationKey());
    }

    public boolean producesLight() {
        return producesLight;
    }


}
