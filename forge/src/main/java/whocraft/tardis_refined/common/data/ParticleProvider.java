package whocraft.tardis_refined.common.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.client.TRParticles;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ParticleProvider implements DataProvider {

    protected List<CompletableFuture<?>> futures = new ArrayList<>();
    private DataGenerator gen;

    public ParticleProvider(DataGenerator gen) {
        this.gen = gen;
    }

    public static Path getPath(Path base, ResourceLocation name) {
        return base.resolve("assets/" + name.getNamespace() + "/particles/" + name.getPath() + ".json");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput arg) {
        Path base = gen.getPackOutput().getOutputFolder();

        ArrayList<ResourceLocation> resourceLocations = new ArrayList<>();
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_01"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_02"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_03"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_04"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_05"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_06"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_07"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_08"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_09"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/l_gold_sym_10"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/m_gold_sym_01"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/m_gold_sym_02"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/m_gold_sym_03"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/m_gold_sym_04"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/m_gold_sym_05"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/m_gold_sym_06"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_01"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_02"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_03"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_04"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_05"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_06"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:gold/s_gold_sym_07"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_01"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_02"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_03"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_04"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_05"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_06"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_07"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_08"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_09"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/l_silver_sym_10"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/m_silver_sym_01"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/m_silver_sym_02"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/m_silver_sym_03"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/m_silver_sym_04"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/m_silver_sym_05"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/m_silver_sym_06"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_01"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_02"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_03"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_04"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_05"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_06"));
        resourceLocations.add(ResourceLocation.parse("tardis_refined:silver/s_silver_sym_07"));

        makeParticle(TRParticles.GALLIFREY.get(), createParticle(resourceLocations), arg, base);
        makeParticle(TRParticles.ARS_LEAVES.get(), createParticle(List.of(ResourceLocation.parse("minecraft:generic_0"))), arg, base);

        return CompletableFuture.allOf(this.futures.toArray(CompletableFuture[]::new));

    }

    private void makeParticle(SimpleParticleType simpleParticleType, JsonObject particle, CachedOutput arg, Path base) {
        futures.add(DataProvider.saveStable(arg, particle, getPath(base, BuiltInRegistries.PARTICLE_TYPE.getKey(simpleParticleType))));
    }

    public void makeParticle(ParticleType<?> type, ResourceLocation textureName, int count, CachedOutput cache, Path base) {
        futures.add(DataProvider.saveStable(cache, this.createParticle(textureName, count), getPath(base, BuiltInRegistries.PARTICLE_TYPE.getKey(type))));
    }

    public JsonObject createParticle(ResourceLocation baseName, int max) {
        JsonObject root = new JsonObject();

        JsonArray textures = new JsonArray();

        for (int i = 0; i < max; ++i) {
            textures.add(baseName.getNamespace() + ":" + baseName.getPath() + i);
        }

        root.add("textures", textures);

        return root;
    }

    public JsonObject createParticle(List<ResourceLocation> resourceLocationList) {
        JsonObject root = new JsonObject();
        JsonArray textures = new JsonArray();
        for (ResourceLocation location : resourceLocationList) {
            textures.add(location.toString());
        }
        root.add("textures", textures);
        return root;
    }


    @Override
    public String getName() {
        return "Particles";
    }
}
