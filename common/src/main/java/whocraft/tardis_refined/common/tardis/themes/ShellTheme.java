package whocraft.tardis_refined.common.tardis.themes;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryBuilder;
import whocraft.tardis_refined.registry.RegistryHolder;

public class ShellTheme implements Theme {

    /**
     * Registry Key for the ShellTheme registry. For addon mods, use this as the registry key
     */
    public static final ResourceKey<Registry<ShellTheme>> SHELL_THEME_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "shell_theme"));

    public static final Registry<ShellTheme> SHELL_THEME_REGISTRY = RegistryBuilder.create(SHELL_THEME_REGISTRY_KEY).build();

    /**
     * Tardis Refined instance of the ShellTheme registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only
     */
    public static final DeferredRegister<ShellTheme> SHELL_THEME_DEFERRED_REGISTRY = DeferredRegister.create(TardisRefined.MODID, SHELL_THEME_REGISTRY_KEY);

    public static final RegistryHolder<ShellTheme, ShellTheme> HALF_BAKED = registerShellTheme("half_baked"); // The default shell. Do not remove.

    public static final RegistryHolder<ShellTheme, ShellTheme> FACTORY = registerShellTheme("factory");
    public static final RegistryHolder<ShellTheme, ShellTheme> POLICE_BOX = registerShellTheme("police_box", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> PHONE_BOOTH = registerShellTheme("phone_booth", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> MYSTIC = registerShellTheme("mystic", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> PRESENT = registerShellTheme("present");
    public static final RegistryHolder<ShellTheme, ShellTheme> DRIFTER = registerShellTheme("drifter");
    public static final RegistryHolder<ShellTheme, ShellTheme> VENDING = registerShellTheme("vending", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> BRIEFCASE = registerShellTheme("briefcase");
    public static final RegistryHolder<ShellTheme, ShellTheme> GROENING = registerShellTheme("groening", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> BIG_BEN = registerShellTheme("big_ben", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> NUKA = registerShellTheme("nuka", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> GROWTH = registerShellTheme("growth");
    public static final RegistryHolder<ShellTheme, ShellTheme> PORTALOO = registerShellTheme("portaloo");
    public static final RegistryHolder<ShellTheme, ShellTheme> PAGODA = registerShellTheme("pagoda");
    public static final RegistryHolder<ShellTheme, ShellTheme> LIFT = registerShellTheme("lift", true);
    public static final RegistryHolder<ShellTheme, ShellTheme> HIEROGLYPH = registerShellTheme("hieroglyph");
    public static final RegistryHolder<ShellTheme, ShellTheme> CASTLE = registerShellTheme("castle");
    public static final RegistryHolder<ShellTheme, ShellTheme> PATHFINDER = registerShellTheme("pathfinder");

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

    private static RegistryHolder<ShellTheme, ShellTheme> registerShellTheme(String id) {
        return SHELL_THEME_DEFERRED_REGISTRY.register(id, () -> new ShellTheme(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), false));
    }

    private static RegistryHolder<ShellTheme, ShellTheme> registerShellTheme(String id, boolean producesLight) {
        return SHELL_THEME_DEFERRED_REGISTRY.register(id, () -> new ShellTheme(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), producesLight));
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
