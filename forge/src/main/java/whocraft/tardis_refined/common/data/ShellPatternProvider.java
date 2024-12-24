package whocraft.tardis_refined.common.data;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatternCollection;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ShellPatternProvider implements DataProvider {

    protected final DataGenerator generator;
    private final boolean addDefaults;
    private final String modid;
    protected Map<ResourceLocation, ShellPatternCollection> data = new HashMap<>();
    public static Logger LOGGER = LogManager.getLogger("TardisRefined/ShellPatternProvider");

    public ShellPatternProvider(DataGenerator generator, String modid) {
        this(generator, modid, true);
    }

    public ShellPatternProvider(DataGenerator generator, String modid, boolean addDefaults) {
        Preconditions.checkNotNull(generator);
        this.generator = generator;
        this.addDefaults = addDefaults;
        this.modid = modid;
    }

    /**
     * To be used by child classes to add new patterns after defaults are registered
     */
    protected void addPatterns() {
    }

    @Override
    public CompletableFuture<?> run(CachedOutput arg) {
        this.data.clear();

        final List<CompletableFuture<?>> futures = new ArrayList<>();

        if (this.addDefaults) {
            ShellPatterns.registerDefaultPatterns();
            data.putAll(ShellPatterns.getDefaultPatternsDatagen());
        }

        this.addPatterns();

        if (!data.isEmpty()) {
            data.forEach((key, patternCollection) -> {
                try {
                    JsonObject currentPatternCollection = ShellPatternCollection.CODEC.encodeStart(JsonOps.INSTANCE, patternCollection).get()
                            .ifRight(right -> {
                                LOGGER.error(right.message());
                            }).orThrow().getAsJsonObject();
                    Path output = getPath(patternCollection.themeId());
                    futures.add(DataProvider.saveStable(arg, currentPatternCollection, output));
                } catch (Exception exception) {
                    LOGGER.error("Issue writing ShellPatternCollection {}! Error: {}", patternCollection.themeId(), exception.getMessage());
                }
            });
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    public ShellPattern addPattern(ResourceLocation themeId, ShellPattern shellPattern) {
        ShellPattern pattern = (ShellPattern) shellPattern.setThemeId(themeId);
        ShellPatternCollection collection;
        if (this.data.containsKey(themeId)) {
            collection = this.data.get(themeId);
            List<ShellPattern> currentList = new ArrayList<>(collection.patterns());
            currentList.add(pattern);
            collection.setPatterns(currentList);
            this.data.replace(themeId, collection);
        } else {
            collection = (ShellPatternCollection) new ShellPatternCollection(List.of(pattern)).setThemeId(themeId);
            this.data.put(themeId, collection);
        }
        LOGGER.info("Adding ShellPattern {} for {}", pattern.id(), themeId);
        return pattern;
    }

    public ResourceLocation exteriorTextureLocation(ResourceLocation themeId) {
        return new ResourceLocation(themeId.getNamespace(), "textures/blockentity/shell/" + themeId.getPath() + "/" + themeId.getPath() + ".png");
    }

    public ResourceLocation interiorTextureLocation(ResourceLocation themeId) {
        return new ResourceLocation(themeId.getNamespace(), "textures/blockentity/shell/" + themeId.getPath() + "/" + themeId.getPath() + "_interior.png");
    }

    protected Path getPath(ResourceLocation themeId) {
        return generator.getPackOutput().getOutputFolder().resolve("data/" + modid + "/" + TardisRefined.MODID + "/patterns/shell/" + themeId.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Shell Patterns";
    }
}
