package whocraft.tardis_refined.common;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.renderer.vortex.VortexGradientTint;
import whocraft.tardis_refined.common.tardis.themes.Theme;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;

public class VortexRegistry implements Theme {

    /**
     * Registry Key for the Vortex registry.
     */
    public static final ResourceKey<Registry<VortexRegistry>> VORTEX_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "vortex"));

    /**
     * Deferred Registry for Vortex entries.
     */
    public static final DeferredRegister<VortexRegistry> VORTEX_DEFERRED_REGISTRY = DeferredRegister.create(TardisRefined.MODID, VORTEX_REGISTRY_KEY);

    /**
     * Registry instance containing all Vortex entries.
     */
    public static final Registry<VortexRegistry> VORTEX_REGISTRY = VORTEX_DEFERRED_REGISTRY.getRegistry().get();

    // Vortex entries
    public static final RegistryHolder<VortexRegistry, VortexRegistry> CLOUDS = registerVortex(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "clouds"), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"), 9, 12, 10f, true, true, VortexGradientTint.BlueOrngGradient, false);
    public static final RegistryHolder<VortexRegistry, VortexRegistry> WAVES = registerVortex(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "waves"), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/waves.png"), 9, 12, 20f, true, true, VortexGradientTint.BlueOrngGradient, false);
    public static final RegistryHolder<VortexRegistry, VortexRegistry> STARS = registerVortex(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "stars"), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/stars.png"), 9, 12, 5f, true, true, VortexGradientTint.PASTEL_GRADIENT, true);
    public static final RegistryHolder<VortexRegistry, VortexRegistry> FLOW = registerVortex(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "flow"), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"), 9, 12, 5f, true, true, VortexGradientTint.MODERN_VORTEX, true);
    public static final RegistryHolder<VortexRegistry, VortexRegistry> SPACE = registerVortex(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "space"), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/stars_2.png"), 9, 12, 5f, true, true, VortexGradientTint.MODERN_VORTEX, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> TWILIGHT_GLOW = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "twilight_glow"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.TWILIGHT_GLOW, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> AURORA_DREAMS = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "aurora_dreams"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.AURORA_DREAMS, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> DESERT_MIRAGE = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "desert_mirage"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.DESERT_MIRAGE, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> NEON_PULSE = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "neon_pulse"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.NEON_PULSE, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> OCEAN_BREEZE = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "ocean_breeze"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.OCEAN_BREEZE, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> SOLAR_FLARE = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "solar_flare"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.SOLAR_FLARE, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> CRYSTAL_LAGOON = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "crystal_lagoon"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.CRYSTAL_LAGOON, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> VELVET_NIGHT = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "velvet_night"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.VELVET_NIGHT, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> CANDY_POP = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "candy_pop"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.CANDY_POP, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> EMERALD_FOREST = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "emerald_forest"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.EMERALD_FOREST, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> LGBT_RAINBOW = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "lgbt_rainbow"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.LGBT_RAINBOW, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> TRANSGENDER_FLAG = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "transgender_flag"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.TRANSGENDER_FLAG, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> BISEXUAL_FLAG = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "bisexual_flag"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.BISEXUAL_FLAG, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> LESBIAN_FLAG = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "lesbian_flag"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.LESBIAN_FLAG, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> NON_BINARY_FLAG = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "non_binary_flag"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.NON_BINARY_FLAG, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> AGENDER_FLAG = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "agender_flag"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.AGENDER_FLAG, false);

    public static final RegistryHolder<VortexRegistry, VortexRegistry> GAY_FLAG = registerVortex(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "gay_flag"),
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.GAY_FLAG, false);


    private final ResourceLocation texture;
    private final int sides;
    private final int rows;
    private final float twist;
    private final boolean lightning;
    private final boolean decals;
    private final VortexGradientTint gradient;
    private final boolean movingGradient;
    private ResourceLocation translationKey;

    public VortexRegistry(ResourceLocation id, ResourceLocation texture, int sides, int rows, float twist, boolean lightning, boolean decals, VortexGradientTint gradient, boolean movingGradient) {
        this.texture = texture;
        this.sides = sides;
        this.rows = rows;
        this.twist = twist;
        this.lightning = lightning;
        this.decals = decals;
        this.gradient = gradient;
        this.movingGradient = movingGradient;
        this.translationKey = id;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public int getSides() {
        return sides;
    }

    public int getRows() {
        return rows;
    }

    public float getTwist() {
        return twist;
    }

    public boolean hasLightning() {
        return lightning;
    }

    public boolean hasDecals() {
        return decals;
    }

    public VortexGradientTint getGradient() {
        return gradient;
    }

    public boolean isMovingGradient() {
        return movingGradient;
    }

    private static RegistryHolder<VortexRegistry, VortexRegistry> registerVortex(ResourceLocation id, ResourceLocation texturePath, int sides, int rows, float twist, boolean lightning, boolean decals, VortexGradientTint gradient, boolean movingGradient) {
        return VORTEX_DEFERRED_REGISTRY.registerHolder(id.getPath(), () -> new VortexRegistry(
                id,
                texturePath,
                sides,
                rows,
                twist,
                lightning,
                decals,
                gradient,
                movingGradient
        ));
    }

    @Override
    public String getTranslationKey() {
        return Util.makeDescriptionId("vortex", this.translationKey);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getTranslationKey());
    }
}
