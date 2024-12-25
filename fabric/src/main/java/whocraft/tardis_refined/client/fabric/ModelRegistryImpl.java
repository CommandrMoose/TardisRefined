package whocraft.tardis_refined.client.fabric;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import whocraft.tardis_refined.client.model.pallidium.BedrockModelUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class ModelRegistryImpl {

    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        EntityModelLayerRegistry.registerModelLayer(location, definition::get);

        System.out.println(location);
        JsonObject model = BedrockModelUtil.toJsonModel(definition.get(), location.getModel().getPath());


        // Define the export folder
        Path exportFolder = Paths.get("export_models/"+location.getLayer());

        // Ensure the folder exists
        try {
            Files.createDirectories(exportFolder);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create export_models directory", e);
        }

        // Define the file path for the model
        Path modelFile = exportFolder.resolve(location.getModel().getPath().replaceAll("_ext", "").replaceAll("int", "door") + ".json");

        // Write the model to the file
        try (BufferedWriter writer = Files.newBufferedWriter(modelFile)) {
            writer.write(model.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write model to file", e);
        }


        return location;
    }
}
