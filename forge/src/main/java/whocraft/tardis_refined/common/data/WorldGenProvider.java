package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRDamageSources;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ProviderConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ProviderPlacedFeatures::bootstrap)
            .add(Registries.DAMAGE_TYPE, arg -> {
                arg.register(TRDamageSources.EYE_OF_HARMONY, new DamageType("eye_of_harmony", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
                arg.register(TRDamageSources.CHOKE, new DamageType("choke", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
            })
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifierProvider::bootstrap);


    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BUILDER, Set.of(TardisRefined.MODID));
    }

    public static HolderLookup.Provider createLookup() {
        return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
    }

}
