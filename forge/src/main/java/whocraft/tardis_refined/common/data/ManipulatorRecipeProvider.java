package whocraft.tardis_refined.common.data;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipe;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipeSerializer;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorRecipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ManipulatorRecipeProvider implements DataProvider {

    protected final DataGenerator generator;
    private final boolean addDefaults;
    private final String modid;
    protected Map<ResourceLocation, ManipulatorCraftingRecipe> data = new HashMap<>();

    public ManipulatorRecipeProvider(DataGenerator generator, String modid) {
        this(generator, modid, true);
    }

    public ManipulatorRecipeProvider(DataGenerator generator, String modid, boolean addDefaults) {
        Preconditions.checkNotNull(generator);
        this.generator = generator;
        this.modid = modid;
        this.addDefaults = addDefaults;
    }

    protected void addRecipes() {
    }

    @Override
    public CompletableFuture<?> run(CachedOutput arg) {
        this.data.clear();

        final List<CompletableFuture<?>> futures = new ArrayList<>();

        if (this.addDefaults) {
            ManipulatorRecipes.registerRecipes();
            data.putAll(ManipulatorRecipes.MANIPULATOR_CRAFTING_RECIPES);
        }

        this.addRecipes();

        if (!data.isEmpty()) {
            data.entrySet().forEach(entry -> {
                try {
                    ManipulatorCraftingRecipe recipe = entry.getValue();
                    JsonObject currentRecipe = ManipulatorCraftingRecipe.CODEC.encodeStart(JsonOps.INSTANCE, recipe)
                            .ifSuccess(element -> { //Must add type field so that vanilla recognises this as a recipe type
                                JsonObject json = element.getAsJsonObject();
                                json.addProperty("type", ManipulatorCraftingRecipeSerializer.SERIALIZER_ID.toString());
                            })
                            .ifError(error -> {
                                TardisRefined.LOGGER.error(error.message());
                            }).getOrThrow().getAsJsonObject();
                    String outputPath = "data/" + recipe.getId().getNamespace() + "/" + "recipes/astral_manipulator" + "/" + recipe.getId().getPath().replace("/", "_") + ".json";
                    futures.add(DataProvider.saveStable(arg, currentRecipe, generator.getPackOutput().getOutputFolder().resolve(outputPath)));
                } catch (Exception exception) {
                    TardisRefined.LOGGER.error("Issue writing ManipulatorRecipe {}! Error: {}", entry.getValue().getId(), exception.getMessage());
                }
            });
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "AstralManipulatorRecipes";
    }

    protected void addRecipe(ResourceLocation id, ManipulatorCraftingRecipe recipe) {
        TardisRefined.LOGGER.info("Adding astral manipulator recipe to datagen {}", id);
        data.put(id, recipe.setRegistryId(id));
    }

}
