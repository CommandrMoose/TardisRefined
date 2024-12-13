package whocraft.tardis_refined.common.data;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DesktopProvider implements DataProvider {

    protected final DataGenerator generator;
    private final boolean addDefaults;
    protected Map<ResourceLocation, DesktopTheme> data = new HashMap<>();

    public DesktopProvider(DataGenerator generator) {
        this(generator, true);
    }

    public DesktopProvider(DataGenerator generator, boolean addDefaults) {
        Preconditions.checkNotNull(generator);
        this.generator = generator;
        this.addDefaults = addDefaults;
    }

    protected void addDesktops() {
    }

    @Override
    public CompletableFuture<?> run(CachedOutput arg) {
        this.data.clear();

        final List<CompletableFuture<?>> futures = new ArrayList<>();

        if (this.addDefaults) {
            TardisDesktops.registerDefaultDesktops();
            data.putAll(TardisDesktops.getDefaultDesktops());
        }

        this.addDesktops();

        if (!data.isEmpty()) {
            data.entrySet().forEach(entry -> {
                try {
                    DesktopTheme desktop = entry.getValue();
                    JsonObject currentDesktop = DesktopTheme.getCodec().encodeStart(JsonOps.INSTANCE, desktop)
                            .ifError(error -> {
                                TardisRefined.LOGGER.error(error.message());
                            }).getOrThrow().getAsJsonObject();
                    String outputPath = "data/" + desktop.getIdentifier().getNamespace() + "/" + TardisDesktops.getReloadListener().getFolderName() + "/" + desktop.getIdentifier().getPath().replace("/", "_") + ".json";
                    futures.add(DataProvider.saveStable(arg, currentDesktop, generator.getPackOutput().getOutputFolder().resolve(outputPath)));
                } catch (Exception exception) {
                    TardisRefined.LOGGER.error("Issue writing Desktop {}! Error: {}", entry.getValue().getIdentifier(), exception.getMessage());
                }
            });
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Desktops";
    }

    protected void addDesktop(DesktopTheme theme) {
        TardisRefined.LOGGER.info("Adding Desktop to datagen {}", theme.getIdentifier());
        data.put(theme.getIdentifier(), theme);
    }

}
