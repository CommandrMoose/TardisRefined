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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DesktopProvider implements DataProvider {

    protected final DataGenerator generator;

    protected Map<ResourceLocation,DesktopTheme> data = new HashMap<>();

    private final boolean addDefaults;

    public DesktopProvider(DataGenerator generator) {
        this(generator, true);
    }

    public DesktopProvider(DataGenerator generator, boolean addDefaults) {
        Preconditions.checkNotNull(generator);
        this.generator = generator;
        this.addDefaults = addDefaults;
    }

    protected void addDesktops(){}

    @Override
    public void run(CachedOutput arg) throws IOException {
        this.data.clear();

        if(this.addDefaults){
            TardisDesktops.registerDefaultDesktops();
            data.putAll(TardisDesktops.getDefaultDesktops());
        }

        this.addDesktops();

        if (!data.isEmpty()){
            data.entrySet().forEach(entry -> {
                try {
                    DesktopTheme desktop = entry.getValue();
                    JsonObject currentDesktop = DesktopTheme.getCodec().encodeStart(JsonOps.INSTANCE, desktop).get()
                            .ifRight(right -> {
                                TardisRefined.LOGGER.error(right.message());
                            }).orThrow().getAsJsonObject();
                    String outputPath = "data/" + desktop.getIdentifier().getNamespace() + "/" + TardisDesktops.getReloadListener().getFolderName() + "/" +  desktop.getIdentifier().getPath().replace("/", "_") + ".json";
                    DataProvider.saveStable(arg, currentDesktop, generator.getOutputFolder().resolve(outputPath));
                } catch (Exception exception) {
                    TardisRefined.LOGGER.debug("Issue writing Desktop {}! Error: {}", entry.getValue().getIdentifier(), exception.getMessage());
                }
            });
        }
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
