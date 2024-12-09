package whocraft.tardis_refined.common;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.renderer.vortex.VortexGradientTint;
import whocraft.tardis_refined.common.tardis.themes.Theme;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;
import whocraft.tardis_refined.registry.RegistrySupplierHolder;

public class VortexRegistry implements Theme {

    /**
     * Registry Key for the Vortex registry.
     */
    public static final ResourceKey<Registry<VortexRegistry>> VORTEX_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(TardisRefined.MODID, "vortex"));

    /**
     * Deferred Registry for Vortex entries.
     */
    public static final DeferredRegistry<VortexRegistry> VORTEX_DEFERRED_REGISTRY = DeferredRegistry.createCustom(TardisRefined.MODID, VORTEX_REGISTRY_KEY, true);

  
    // Vortex entries
    public static final RegistrySupplier<VortexRegistry> CLOUDS = registerVortex(new ResourceLocation(TardisRefined.MODID, "clouds"), new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"), 9, 12, 10f, true, true, VortexGradientTint.BlueOrngGradient, false);
    public static final RegistrySupplier<VortexRegistry> WAVES = registerVortex(new ResourceLocation(TardisRefined.MODID, "waves"), new ResourceLocation(TardisRefined.MODID, "textures/vortex/waves.png"), 9, 12, 20f, true, true, VortexGradientTint.BlueOrngGradient, false);
    public static final RegistrySupplier<VortexRegistry> STARS = registerVortex(new ResourceLocation(TardisRefined.MODID, "stars"), new ResourceLocation(TardisRefined.MODID, "textures/vortex/stars.png"), 9, 12, 5f, true, true, VortexGradientTint.PASTEL_GRADIENT, true);
    public static final RegistrySupplier<VortexRegistry> FLOW = registerVortex(new ResourceLocation(TardisRefined.MODID, "flow"), new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"), 9, 12, 5f, true, true, VortexGradientTint.MODERN_VORTEX, true);
    public static final RegistrySupplier<VortexRegistry> SPACE = registerVortex(new ResourceLocation(TardisRefined.MODID, "space"), new ResourceLocation(TardisRefined.MODID, "textures/vortex/stars_2.png"), 9, 12, 5f, true, true, VortexGradientTint.MODERN_VORTEX, false);

    public static final RegistrySupplier<VortexRegistry> TWILIGHT_GLOW = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "twilight_glow"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.TWILIGHT_GLOW, false);

    public static final RegistrySupplier<VortexRegistry> AURORA_DREAMS = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "aurora_dreams"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.AURORA_DREAMS, false);

    public static final RegistrySupplier<VortexRegistry> DESERT_MIRAGE = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "desert_mirage"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.DESERT_MIRAGE, false);

    public static final RegistrySupplier<VortexRegistry> NEON_PULSE = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "neon_pulse"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.NEON_PULSE, false);

    public static final RegistrySupplier<VortexRegistry> OCEAN_BREEZE = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "ocean_breeze"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.OCEAN_BREEZE, false);

    public static final RegistrySupplier<VortexRegistry> SOLAR_FLARE = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "solar_flare"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.SOLAR_FLARE, false);

    public static final RegistrySupplier<VortexRegistry> CRYSTAL_LAGOON = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "crystal_lagoon"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.CRYSTAL_LAGOON, false);

    public static final RegistrySupplier<VortexRegistry> VELVET_NIGHT = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "velvet_night"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.VELVET_NIGHT, false);

    public static final RegistrySupplier<VortexRegistry> CANDY_POP = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "candy_pop"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.CANDY_POP, false);

    public static final RegistrySupplier<VortexRegistry> EMERALD_FOREST = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "emerald_forest"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.EMERALD_FOREST, false);

    public static final RegistrySupplier<VortexRegistry> LGBT_RAINBOW = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "lgbt_rainbow"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.LGBT_RAINBOW, false);

    public static final RegistrySupplier<VortexRegistry> TRANSGENDER_FLAG = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "transgender_flag"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.TRANSGENDER_FLAG, false);

    public static final RegistrySupplier<VortexRegistry> BISEXUAL_FLAG = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "bisexual_flag"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.BISEXUAL_FLAG, false);

    public static final RegistrySupplier<VortexRegistry> LESBIAN_FLAG = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "lesbian_flag"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.LESBIAN_FLAG, false);

    public static final RegistrySupplier<VortexRegistry> NON_BINARY_FLAG = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "non_binary_flag"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.NON_BINARY_FLAG, false);

    public static final RegistrySupplier<VortexRegistry> AGENDER_FLAG = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "agender_flag"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
            9, 12, 10f, true, true, VortexGradientTint.AGENDER_FLAG, false);

    public static final RegistrySupplier<VortexRegistry> GAY_FLAG = registerVortex(
            new ResourceLocation(TardisRefined.MODID, "gay_flag"),
            new ResourceLocation(TardisRefined.MODID, "textures/vortex/clouds.png"),
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

    private static RegistrySupplier<VortexRegistry> registerVortex(ResourceLocation id, ResourceLocation texturePath, int sides, int rows, float twist, boolean lightning, boolean decals, VortexGradientTint gradient, boolean movingGradient) {
        return VORTEX_DEFERRED_REGISTRY.register(id.getPath(), () -> new VortexRegistry(
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

    @Override
    public String getTranslationKey() {
        return Util.makeDescriptionId("vortex", this.translationKey);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getTranslationKey());
    }
}
