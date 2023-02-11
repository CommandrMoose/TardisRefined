package whocraft.tardis_refined.common.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TRParticles;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParticleProvider implements DataProvider {

    private DataGenerator gen;

    public ParticleProvider(DataGenerator gen) {
        this.gen = gen;
    }

    @Override
    public void run(CachedOutput arg) throws IOException {
        Path base = gen.getOutputFolder();

        ArrayList<ResourceLocation> resourceLocations = new ArrayList<>();
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_01"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_02"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_03"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_04"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_05"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_06"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_07"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_08"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_09"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/l_gold_sym_10"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/m_gold_sym_01"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/m_gold_sym_02"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/m_gold_sym_03"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/m_gold_sym_04"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/m_gold_sym_05"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/m_gold_sym_06"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_01"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_02"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_03"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_04"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_05"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_06"));
        resourceLocations.add(new ResourceLocation("tardis_refined:gold/s_gold_sym_07"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_01"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_02"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_03"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_04"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_05"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_06"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_07"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_08"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_09"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/l_silver_sym_10"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/m_silver_sym_01"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/m_silver_sym_02"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/m_silver_sym_03"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/m_silver_sym_04"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/m_silver_sym_05"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/m_silver_sym_06"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_01"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_02"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_03"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_04"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_05"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_06"));
        resourceLocations.add(new ResourceLocation("tardis_refined:silver/s_silver_sym_07"));

        makeParticle(TRParticles.GALLIFREY.get(), createParticle(resourceLocations), arg, base);

    }

    private void makeParticle(SimpleParticleType simpleParticleType, JsonObject particle, CachedOutput arg, Path base) throws IOException {
        DataProvider.saveStable(arg, particle, getPath(base, ForgeRegistries.PARTICLE_TYPES.getKey(simpleParticleType)));
    }

    public void makeParticle(ParticleType<?> type, ResourceLocation textureName, int count, CachedOutput cache, Path base) throws IOException {
        DataProvider.saveStable(cache, this.createParticle(textureName, count), getPath(base, ForgeRegistries.PARTICLE_TYPES.getKey(type)));
    }

    public static Path getPath(Path base, ResourceLocation name) {
        return base.resolve("assets/" + name.getNamespace() + "/particles/" + name.getPath() + ".json");
    }

    public JsonObject createParticle(ResourceLocation baseName, int max){
        JsonObject root = new JsonObject();

        JsonArray textures = new JsonArray();

        for(int i = 0; i < max; ++i) {
            textures.add(baseName.getNamespace() + ":" + baseName.getPath() + i);
        }

        root.add("textures", textures);

        return root;
    }

    public JsonObject createParticle(List<ResourceLocation> resourceLocationList){
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
