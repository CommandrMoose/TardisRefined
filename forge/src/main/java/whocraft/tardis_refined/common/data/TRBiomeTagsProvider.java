package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRTagKeys;

import java.util.concurrent.CompletableFuture;

public class TRBiomeTagsProvider extends BiomeTagsProvider {
    public TRBiomeTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, provider, TardisRefined.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Is mountain or ocean
        tag(TRTagKeys.IS_MOUNTAIN_OR_OCEAN)
                .addTags(BiomeTags.IS_OCEAN)
                .addTags(BiomeTags.IS_DEEP_OCEAN)
                .addTags(BiomeTags.IS_MOUNTAIN)
                .addOptionalTag(Tags.Biomes.IS_MOUNTAIN.location()) //Add Forge Mountain tag as optional because it contains other entries that vanilla excludes
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath("c", "ocean")); //Add Fabric Ocean tag as optional because it contains other entries that vanilla excludes
        //Tardis Root Cluster
        tag(TRTagKeys.TARDIS_ROOT_CLUSTER).addTags(TRTagKeys.IS_MOUNTAIN_OR_OCEAN);

    }
}