package whocraft.tardis_refined.common.tardis;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.util.CodecJsonReloadListener;
import whocraft.tardis_refined.common.util.Platform;

import java.util.HashMap;
import java.util.Map;


/**
 * Data manager for all DesktopTheme(s)
 */
public class TardisDesktops {

    /**
     * Static reference to the overgrown cave theme. <br> DO NOT REGISTER THIS, we don't want it to show in the selection screen. <br> It is only intended to be used once for the root shell.
     */
    public static final DesktopTheme DEFAULT_OVERGROWN_THEME = new DesktopTheme("default_overgrown", "cave/cave_generation_one");
    /**
     * A reference to the default Factory theme, intended for convenience. <br> DO NOT REGISTER THIS, it has already been included in the default list of desktops.
     */
    public static final DesktopTheme FACTORY_THEME = new DesktopTheme("factory", "desktop/factory");
    public static final DesktopTheme TERRAFORMED = new DesktopTheme("terraformed", "desktop/terraformed");
    private static final CodecJsonReloadListener<DesktopTheme> RELOAD_LISTENER = createReloadListener();
    private static Map<ResourceLocation, DesktopTheme> DEFAULT_DESKTOPS = new HashMap<>();

    /**
     * A factory method to create the instance of our reload listener.
     * <br> The TardisDesktops class itself is not a reload listener because the CodecJsonReloadListener needs platform specific implementations.
     * <br> That introduces side effects associated with generic types and the ExpectPlatform annotation, so we manually call this factory method to create an instance.
     *
     * @return
     */
    private static CodecJsonReloadListener<DesktopTheme> createReloadListener() {
        CodecJsonReloadListener<DesktopTheme> instance = CodecJsonReloadListener.create(TardisRefined.MODID + "/" + "desktops", DesktopTheme.getCodec());
        return instance;
    }

    public static CodecJsonReloadListener<DesktopTheme> getReloadListener() {
        return RELOAD_LISTENER;
    }

    public static Map<ResourceLocation, DesktopTheme> getRegistry() {
        return RELOAD_LISTENER.getData();
    }

    public static DesktopTheme getDesktopById(ResourceLocation location) {
        return RELOAD_LISTENER.getData().getOrDefault(location, TardisDesktops.FACTORY_THEME);
    }

    /**
     * Creates and adds the Tardis Refined default list of DesktopThemes to a standalone map.
     * Can be used for datagenerators or as a fallback registry
     *
     * @return
     */
    public static Map<ResourceLocation, DesktopTheme> registerDefaultDesktops() {
        DEFAULT_DESKTOPS.clear();
        addDefaultDesktop(new DesktopTheme("arnet", "desktop/arnet"));
        addDefaultDesktop(new DesktopTheme("copper", "desktop/copper"));
        addDefaultDesktop(new DesktopTheme("coral", "desktop/coral"));
        addDefaultDesktop(new DesktopTheme("crystal", "desktop/crystal"));
        addDefaultDesktop(TardisDesktops.FACTORY_THEME);
        addDefaultDesktop(new DesktopTheme("future_nostalgia", "desktop/future_nostalgia"));
        addDefaultDesktop(new DesktopTheme("hellbound", "desktop/hellbound"));
        addDefaultDesktop(new DesktopTheme("ironguard", "desktop/ironguard"));
        addDefaultDesktop(new DesktopTheme("lodestone", "desktop/lodestone"));
        addDefaultDesktop(new DesktopTheme("mechanical", "desktop/mechanical"));
        addDefaultDesktop(new DesktopTheme("mesh", "desktop/mesh"));
        addDefaultDesktop(new DesktopTheme("newwave", "desktop/newwave"));
        addDefaultDesktop(new DesktopTheme("nuka", "desktop/nuka"));
        addDefaultDesktop(new DesktopTheme("radiance", "desktop/radiance"));
        addDefaultDesktop(new DesktopTheme("refurbished", "desktop/refurbished"));
        addDefaultDesktop(new DesktopTheme("toyota", "desktop/toyota"));
        addDefaultDesktop(new DesktopTheme("vapor", "desktop/vapor"));
        addDefaultDesktop(new DesktopTheme("victorian", "desktop/victorian"));
        addDefaultDesktop(new DesktopTheme("violet_eye", "desktop/violet_eye"));
        addDefaultDesktop(new DesktopTheme("watchface", "desktop/watchface"));
        addDefaultDesktop(new DesktopTheme("classic", "desktop/classic"));
        addDefaultDesktop(new DesktopTheme("aquarius", "desktop/aquarius"));

        return DEFAULT_DESKTOPS;
    }

    private static void addDefaultDesktop(DesktopTheme theme) {
        if (!Platform.isProduction()) //Enable Logging in development environment
            TardisRefined.LOGGER.info("Adding default Desktop {}", theme.getIdentifier());
        DEFAULT_DESKTOPS.put(theme.getIdentifier(), theme);
    }

    /**
     * Gets a default list of Desktops added by Tardis Refined. Useful as a fallback list.
     */
    public static Map<ResourceLocation, DesktopTheme> getDefaultDesktops() {
        return DEFAULT_DESKTOPS;
    }

}
