package whocraft.tardis_refined.common.data;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.patterns.ConsolePattern;
import whocraft.tardis_refined.patterns.ConsolePatternCollection;
import whocraft.tardis_refined.patterns.ConsolePatterns;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ConsolePatternProvider implements DataProvider {

    protected final DataGenerator generator;
    private final boolean addDefaults;
    protected Map<ResourceLocation, ConsolePatternCollection> data = new HashMap<>();

    public ConsolePatternProvider(DataGenerator generator) {
        this(generator, true);
    }

    public ConsolePatternProvider(DataGenerator generator, boolean addDefaults) {
        Preconditions.checkNotNull(generator);
        this.generator = generator;
        this.addDefaults = addDefaults;
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
            ConsolePatterns.registerDefaultPatterns();
            data.putAll(ConsolePatterns.getDefaultPatternsDatagen());
        }

        this.addPatterns();

        if (!data.isEmpty()) {
            data.entrySet().forEach(entry -> {
                try {
                    ConsolePatternCollection patternCollection = entry.getValue();
                    JsonObject currentPatternCollection = ConsolePatternCollection.CODEC.encodeStart(JsonOps.INSTANCE, patternCollection).get()
                            .ifRight(right -> {
                                TardisRefined.LOGGER.error(right.message());
                            }).orThrow().getAsJsonObject();
                    Path output = getPath(patternCollection.themeId());
                    futures.add(DataProvider.saveStable(arg, currentPatternCollection, output));
                } catch (Exception exception) {
                    TardisRefined.LOGGER.error("Issue writing ConsolePatternCollection {}! Error: {}", entry.getValue().themeId(), exception.getMessage());
                }
            });
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    protected ConsolePattern addPatternToDatagen(ResourceLocation themeId, ConsolePattern consolePattern) {
        ConsolePattern pattern = (ConsolePattern) consolePattern.setThemeId(themeId);
        ConsolePatternCollection collection;
        if (this.data.containsKey(themeId)) {
            collection = this.data.get(themeId);
            List<ConsolePattern> currentList = new ArrayList<>();
            currentList.addAll(collection.patterns());
            currentList.add(pattern);
            collection.setPatterns(currentList);
            this.data.replace(themeId, collection);
        } else {
            collection = (ConsolePatternCollection) new ConsolePatternCollection(List.of(pattern)).setThemeId(themeId);
            this.data.put(themeId, collection);
        }
        TardisRefined.LOGGER.info("Adding ConsolePattern {} for {}", pattern.id(), themeId);
        return pattern;
    }

    protected ResourceLocation createConsolePatternLocation(ResourceLocation path) {
        return ResourceLocation.fromNamespaceAndPath(path.getNamespace(), "textures/blockentity/console/" + path + ".png");
    }

    private ResourceLocation createConsolePatternLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/blockentity/console/" + path + ".png");
    }

    protected Path getPath(ResourceLocation themeId) {
        return generator.getPackOutput().getOutputFolder().resolve("data/" + TardisRefined.MODID + "/" + TardisRefined.MODID + "/patterns/console/" + themeId.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Console Patterns";
    }
}
