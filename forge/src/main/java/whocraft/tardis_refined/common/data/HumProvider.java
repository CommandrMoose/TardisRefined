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
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.soundscape.hum.HumEntry;
import whocraft.tardis_refined.common.soundscape.hum.TardisHums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HumProvider implements DataProvider {

    protected final DataGenerator generator;
    private final boolean addDefaults;
    protected Map<ResourceLocation, HumEntry> data = new HashMap<>();
    public static Logger LOGGER = LogManager.getLogger("TardisRefined/HumProvider");


    public HumProvider(DataGenerator generator) {
        this(generator, true);
    }

    public HumProvider(DataGenerator generator, boolean addDefaults) {
        Preconditions.checkNotNull(generator);
        this.generator = generator;
        this.addDefaults = addDefaults;
    }

    protected void addHums() {
    }

    @Override
    public CompletableFuture<?> run(CachedOutput arg) {
        this.data.clear();

        final List<CompletableFuture<?>> futures = new ArrayList<>();

        if (this.addDefaults) {
            TardisHums.registerDefaultHums();
            data.putAll(TardisHums.getDefaultHums());
            addHum(TardisHums.getDefaultHum());
            addHum(TardisHums.VICTORIAN);
            addHum(TardisHums.TOYOTA);
            addHum(TardisHums.CLASSIC);
            addHum(TardisHums.CAVE);
            addHum(TardisHums.AVIATRAX);
            addHum(TardisHums.CRIMSON_FOREST);
            addHum(TardisHums.BASALT_DELTAS);
            addHum(TardisHums.NETHER_WASTES);
            addHum(TardisHums.SOUL_SAND_VALLEY);
            addHum(TardisHums.WARPED_FOREST);
            addHum(TardisHums.UNDER_WATER);
            addHum(TardisHums.COPPER);
        }

        this.addHums();

        if (!data.isEmpty()) {
            data.forEach((key, hum) -> {
                TardisRefined.LOGGER.debug("Writing Hum {}", key);
                try {
                    JsonObject currentHum = HumEntry.codec().encodeStart(JsonOps.INSTANCE, hum).get()
                            .ifRight(right -> {
                                TardisRefined.LOGGER.error(right.message());
                            }).orThrow().getAsJsonObject();
                    String outputPath = "data/" + hum.getIdentifier().getNamespace() + "/" + TardisHums.getReloadListener().getFolderName() + "/" + hum.getIdentifier().getPath().replace("/", "_") + ".json";
                    futures.add(DataProvider.saveStable(arg, currentHum, generator.getPackOutput().getOutputFolder().resolve(outputPath)));
                } catch (Exception exception) {
                    LOGGER.error("Issue writing Hum {{}}! Error: {}", hum.getIdentifier(), exception.getMessage());
                }
            });
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public @NotNull String getName() {
        return "Tardis Refined - Hums";
    }

    protected void addHum(HumEntry hum) {
        LOGGER.info("Adding Interior Hum {{}} to Data Generation", hum.getIdentifier());
        data.put(hum.getIdentifier(), hum);
    }

}
