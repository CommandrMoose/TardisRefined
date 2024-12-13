package whocraft.tardis_refined.common.world;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.world.chunk.TardisChunkGenerator;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class ChunkGenerators {
    public static final DeferredRegister<MapCodec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(TardisRefined.MODID, Registries.CHUNK_GENERATOR);
    public static final ResourceKey<Biome> TARDIS_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "tardis"));
    public static final RegistryHolder<MapCodec<? extends ChunkGenerator>, MapCodec<? extends ChunkGenerator>> TARDIS_CHUNK_GENERATOR = CHUNK_GENERATORS.register("tardis", () -> TardisChunkGenerator.CODEC);
}
