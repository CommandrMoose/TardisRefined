package whocraft.tardis_refined.common.tardis.themes;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;
import whocraft.tardis_refined.registry.RegistrySupplierHolder;

public class ShellTheme implements Theme {


    /**
     * Registry Key for the ShellTheme registry. For addon mods, use this as the registry key
     */
    public static final ResourceKey<Registry<ShellTheme>> SHELL_THEME_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(TardisRefined.MODID, "shell_theme"));

    /**
     * Tardis Refined instance of the ShellTheme registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only
     */
    public static final DeferredRegistry<ShellTheme> SHELL_THEME_DEFERRED_REGISTRY = DeferredRegistry.createCustom(TardisRefined.MODID, SHELL_THEME_REGISTRY_KEY, true);

    public static final RegistrySupplier<ShellTheme> HALF_BAKED = registerShellTheme("half_baked"); // The default shell. Do not remove.

    public static final RegistrySupplier<ShellTheme> FACTORY = registerShellTheme("factory");
    public static final RegistrySupplier<ShellTheme> POLICE_BOX = registerShellTheme("police_box", true);
    public static final RegistrySupplier<ShellTheme> PHONE_BOOTH = registerShellTheme("phone_booth", true);
    public static final RegistrySupplier<ShellTheme> MYSTIC = registerShellTheme("mystic", true);
    public static final RegistrySupplier<ShellTheme> PRESENT = registerShellTheme("present");
    public static final RegistrySupplier<ShellTheme> DRIFTER = registerShellTheme("drifter");
    public static final RegistrySupplier<ShellTheme> VENDING = registerShellTheme("vending", true);
    public static final RegistrySupplier<ShellTheme> BRIEFCASE = registerShellTheme("briefcase");
    public static final RegistrySupplier<ShellTheme> GROENING = registerShellTheme("groening", true);
    public static final RegistrySupplier<ShellTheme> BIG_BEN = registerShellTheme("big_ben", true);
    public static final RegistrySupplier<ShellTheme> NUKA = registerShellTheme("nuka", true);
    public static final RegistrySupplier<ShellTheme> GROWTH = registerShellTheme("growth");
    public static final RegistrySupplier<ShellTheme> PORTALOO = registerShellTheme("portaloo");
    public static final RegistrySupplier<ShellTheme> PAGODA = registerShellTheme("pagoda");
    public static final RegistrySupplier<ShellTheme> LIFT = registerShellTheme("lift", true);
    public static final RegistrySupplier<ShellTheme> HIEROGLYPH = registerShellTheme("hieroglyph");
    public static final RegistrySupplier<ShellTheme> CASTLE = registerShellTheme("castle");
    public static final RegistrySupplier<ShellTheme> PATHFINDER = registerShellTheme("pathfinder");
    public static final RegistrySupplier<ShellTheme> SHULKER = registerShellTheme("shulker");

    public static ShellTheme getShellTheme(ResourceLocation resourceLocation) {
        ShellTheme potentialTheme = SHELL_THEME_DEFERRED_REGISTRY.get(resourceLocation);
        if (potentialTheme != null) {
            return potentialTheme;
        }
        return HALF_BAKED.get();
    }

    public static ResourceLocation getKey(ShellTheme shellTheme) {
        return SHELL_THEME_DEFERRED_REGISTRY.getKey(shellTheme);
    }

    private ResourceLocation translationKey;
    private boolean producesLight;


    public ShellTheme(ResourceLocation translationKey, boolean producesLight) {
        this.translationKey = translationKey;
        this.producesLight = producesLight;
    }

    public ShellTheme(ResourceLocation translationKey) {
        this(translationKey, false);
    }

    private static RegistrySupplier<ShellTheme> registerShellTheme(String id) {
        return SHELL_THEME_DEFERRED_REGISTRY.register(id, () -> new ShellTheme(new ResourceLocation(TardisRefined.MODID, id)));
    }

    private static RegistrySupplier<ShellTheme> registerShellTheme(String id, boolean producesLight) {
        return SHELL_THEME_DEFERRED_REGISTRY.register(id, () -> new ShellTheme(new ResourceLocation(TardisRefined.MODID, id), producesLight));
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
