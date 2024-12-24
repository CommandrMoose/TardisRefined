package whocraft.tardis_refined.patterns;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ResourceConstants;
import whocraft.tardis_refined.patterns.sound.ShellSoundProfile;
import whocraft.tardis_refined.patterns.sound.TRShellSoundProfiles;

import java.util.*;

/**
 * Data Manager for all {@link ShellPattern}(s)
 */
public class ShellPatterns {

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/ShellPatterns");


    public static final ShellPattern DEFAULT = (ShellPattern) new ShellPattern(ResourceConstants.DEFAULT_PATTERN_ID, new PatternTexture(exteriorTextureLocation(ShellTheme.FACTORY.getId(), ShellTheme.FACTORY.getId().getPath()), false)
            , new PatternTexture(interiorTextureLocation(ShellTheme.FACTORY.getId(), ShellTheme.FACTORY.getId().getPath()), false), Optional.of(TRShellSoundProfiles.DEFAULT_SOUND_PROFILE)).setThemeId(ConsoleTheme.FACTORY.getId());
    public static PatternReloadListener<ShellPatternCollection, ShellPattern> PATTERNS = PatternReloadListener.createListener(TardisRefined.MODID + "/patterns/shell", ShellPatternCollection.CODEC, patternCollections -> PatternReloadListener.processPatternCollections(patternCollections));
    private static final Map<ResourceLocation, List<ShellPattern>> DEFAULT_PATTERNS = new HashMap<>();

    public static PatternReloadListener<ShellPatternCollection, ShellPattern> getReloadListener() {
        return PATTERNS;
    }

    public static Map<ResourceLocation, List<ShellPattern>> getRegistry() {
        return PATTERNS.getData();
    }


    /**
     * Lookup the list of {@link ShellPattern}(s) in a {@link ShellPatternCollection} for a given {@link ShellTheme}
     */
    public static List<ShellPattern> getPatternsForTheme(ResourceLocation shellThemeId) {
        return PATTERNS.getData().get(shellThemeId);
    }

    /**
     * Retrieves a pattern from a default list of patterns, for use when Capabiliteis or Cardinal Components classloads patterns before datapack loading
     */
    public static List<ShellPattern> getPatternsForThemeDefault(ResourceLocation shellThemeId) {
        return DEFAULT_PATTERNS.get(shellThemeId);
    }

    /**
     * Helper method to get a {@link ShellPatternCollection} by theme ID
     */
    public static List<ShellPattern> getPatternCollectionForTheme(ResourceLocation shellThemeId) {
        return PATTERNS.getData().get(shellThemeId);
    }

    /**
     * Lookup a {@link ShellTheme} based on a singular {@link ShellPattern}
     * <br> As there is a many-to-one relationship between {@link ShellPattern} and {@link ShellTheme}
     * <br> as well as a one-to-one relationship between a {@link ShellPatternCollection} and {@link ShellTheme},
     * we will iterate through all {@link ShellPatternCollection} (which holds the theme ID) and find matchine ones
     */
    public static ResourceLocation getThemeForPattern(ShellPattern pattern) {
        Map<ResourceLocation, List<ShellPattern>> entries = ShellPatterns.getRegistry();
        for (Map.Entry<ResourceLocation, List<ShellPattern>> entry : entries.entrySet()) {
            if (pattern.getThemeId() == entry.getKey()) {
                return pattern.getThemeId();
            }
        }
        return ShellTheme.HALF_BAKED.getId();
    }

    /**
     * Sanity check to make sure a Pattern for a {@link ShellTheme} exists
     * <br> A likely use case for this is when entries for the patterns are being modified in some way, such as when something triggers datapacks to be reloaded
     */
    public static boolean doesPatternExist(ResourceLocation themeId, ResourceLocation patternId) {
        List<ShellPattern> basePatterns = getPatternsForTheme(themeId);
        for (ShellPattern basePattern : basePatterns) {
            if (Objects.equals(basePattern.id(), patternId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lookup up a {@link ShellPattern} within a particular {@link ShellTheme} or get the first one in the list if the input pattern id cannot be found
     */
    public static ShellPattern getPatternOrDefault(ResourceLocation themeId, ResourceLocation patternId) {
        List<ShellPattern> basePatterns = getPatternsForTheme(themeId);
        for (ShellPattern basePattern : basePatterns) {
            if (Objects.equals(basePattern.id(), patternId)) {
                return basePattern;
            }
        }
        return basePatterns.get(0);
    }

    public static ShellPattern next(ResourceLocation shellTheme, ShellPattern currentPattern) {
        List<ShellPattern> collection = getPatternCollectionForTheme(shellTheme);
        return next(collection, currentPattern);
    }

    /**
     * Helper to get the next available {@link ShellPattern} in the current {@link ShellPatternCollection}
     */
    public static ShellPattern next(List<ShellPattern> patterns, ShellPattern currentPattern) {

        if (currentPattern == null) {
            return patterns.get(0);
        }

        int prevIndex = patterns.indexOf(currentPattern);
        if (prevIndex > patterns.size() || prevIndex + 1 >= patterns.size()) {
            return patterns.get(0);
        }
        return patterns.get(prevIndex + 1);
    }

    /**
     * Constructs and a {@link ShellPattern}, then adds it to a {@link ShellPatternCollection}, which is assigned to a {@link ShellTheme}.
     * <br> The {@link ShellPatternCollection} is then added to an internal default map
     * <br> Also assigns the {@link ShellPattern} its parent {@link ShellTheme}'s ID
     *
     * @implSpec INTERNAL USE ONLY
     */
    public static ShellPattern addDefaultPattern(ResourceLocation themeId, ShellPattern datagenPattern) {
        List<ShellPattern> patternList;
        ShellPattern pattern = (ShellPattern) datagenPattern.setThemeId(themeId);
        if (DEFAULT_PATTERNS.containsKey(themeId)) {
            patternList = DEFAULT_PATTERNS.get(themeId);
            List<ShellPattern> currentList = new ArrayList<>(patternList);
            currentList.add(pattern);
            DEFAULT_PATTERNS.replace(themeId, currentList);
            LOGGER.info("Adding Shell Pattern {} for {}", pattern.id(), themeId);
        } else {
            patternList = List.of(pattern);
            DEFAULT_PATTERNS.put(themeId, patternList);
        }
        return pattern;
    }

    public static ShellPattern addDefaultPattern(ResourceLocation themeId, String patternName, boolean hasEmissiveTexture) {
        return addDefaultPattern(themeId, patternName, hasEmissiveTexture, Optional.of(TRShellSoundProfiles.DEFAULT_SOUND_PROFILE));
    }

    public static ShellPattern addDefaultPattern(ResourceLocation themeId, String patternName, boolean hasEmissiveTexture, Optional<ShellSoundProfile> soundProfile) {
        ShellPattern pattern = (ShellPattern) new ShellPattern(patternName, new PatternTexture(exteriorTextureLocation(themeId, patternName), hasEmissiveTexture)
                , new PatternTexture(interiorTextureLocation(themeId, patternName), hasEmissiveTexture), soundProfile).setThemeId(themeId);
        return addDefaultPattern(themeId, pattern);
    }

    public static ResourceLocation exteriorTextureLocation(ResourceLocation themeId, String textureName) {
        return new ResourceLocation(TardisRefined.MODID, "textures/blockentity/shell/" + themeId.getPath() + "/" + textureName + ".png");
    }

    public static ResourceLocation exteriorTextureLocation(ResourceLocation themeId, String modid, String textureName) {
        return new ResourceLocation(modid, "textures/blockentity/shell/" + themeId.getPath() + "/" + textureName + ".png");
    }

    public static ResourceLocation interiorTextureLocation(ResourceLocation themeId, String modid, String textureName) {
        return new ResourceLocation(modid, "textures/blockentity/shell/" + themeId.getPath() + "/" + textureName + "_interior.png");
    }

    public static ResourceLocation interiorTextureLocation(ResourceLocation themeId, String textureName) {
        return new ResourceLocation(TardisRefined.MODID, "textures/blockentity/shell/" + themeId.getPath() + "/" + textureName + "_interior.png");
    }

    /**
     * Gets a default list of Shell Patterns added by Tardis Refined. Useful as a fallback list.
     * <br> Requires calling {@link ShellPatterns#registerDefaultPatterns} first
     *
     * @implNote Used for datagen providers when we may need to lookup the map multiple times, but only need to register default entries once.
     */
    public static Map<ResourceLocation, List<ShellPattern>> getDefaultPatterns() {
        return DEFAULT_PATTERNS;
    }

    public static Map<ResourceLocation, ShellPatternCollection> getDefaultPatternsDatagen() {
        Map<ResourceLocation, ShellPatternCollection> defaults = new HashMap<>();
        DEFAULT_PATTERNS.forEach((key, value) -> defaults.put(key, (ShellPatternCollection) new ShellPatternCollection(value).setThemeId(key)));
        return defaults;
    }

    /**
     * Registers the Tardis Refined default Shell Patterns and returns a map of them by Theme ID
     * <br> Should only be called ONCE when needed
     */
    public static Map<ResourceLocation, List<ShellPattern>> registerDefaultPatterns() {
        DEFAULT_PATTERNS.clear();
        /*Add Base Textures*/
        for (ResourceLocation shellTheme : ShellTheme.SHELL_THEME_REGISTRY.keySet()) {
            boolean hasDefaultEmission = shellTheme == ShellTheme.MYSTIC.getId() || shellTheme == ShellTheme.NUKA.getId() || shellTheme == ShellTheme.PAGODA.getId() || shellTheme == ShellTheme.PHONE_BOOTH.getId() || shellTheme == ShellTheme.POLICE_BOX.getId() || shellTheme == ShellTheme.VENDING.getId();
            String textureName = shellTheme.getPath();
            ShellSoundProfile soundProfile = TRShellSoundProfiles.defaultSoundProfilesByTheme().getOrDefault(shellTheme, TRShellSoundProfiles.DEFAULT_SOUND_PROFILE);

            //Use an overload version of the method for default shells because the texture files were named based on shell theme name
            ShellPattern pattern = new ShellPattern(ResourceConstants.DEFAULT_PATTERN_ID, new PatternTexture(exteriorTextureLocation(shellTheme, textureName), hasDefaultEmission)
                    , new PatternTexture(interiorTextureLocation(shellTheme, textureName), hasDefaultEmission), Optional.of(soundProfile));
            addDefaultPattern(shellTheme, pattern);
        }

        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "faded", true);
        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "fourteen", true);
        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "barbie", true);
//        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "gaudy", false);
//        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "metal", false);
//        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "stone", false);
//        addDefaultPattern(ShellTheme.POLICE_BOX.getId(), "red", false);*/

        addDefaultPattern(ShellTheme.PHONE_BOOTH.getId(), "metal", false);

        addDefaultPattern(ShellTheme.PRESENT.getId(), "cardboard", false);

        addDefaultPattern(ShellTheme.BRIEFCASE.getId(), "intel", false);
        addDefaultPattern(ShellTheme.BRIEFCASE.getId(), "metal", false);
        addDefaultPattern(ShellTheme.BRIEFCASE.getId(), "mesa", false);

        addDefaultPattern(ShellTheme.MYSTIC.getId(), "dwarven", false);

        addDefaultPattern(ShellTheme.BIG_BEN.getId(), "gothic", true);

        return new HashMap<>(DEFAULT_PATTERNS);
    }

}
