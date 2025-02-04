package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRItemRegistry;
import whocraft.tardis_refined.registry.TRTagKeys;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends ItemTagsProvider {

    public ItemTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Item>> completableFuture2, CompletableFuture<TagLookup<Block>> completableFuture3, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, completableFuture2, completableFuture3, TardisRefined.MODID, existingFileHelper);
    }

    public ItemTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, completableFuture2, TardisRefined.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(TRTagKeys.CURIOS_HEAD).add(TRItemRegistry.GLASSES.get());
        tag(TRTagKeys.TRINKETS_HEAD).add(TRItemRegistry.GLASSES.get());
        tag(TRTagKeys.TRINKETS_FACE).add(TRItemRegistry.GLASSES.get());
        tag(Tags.Items.INGOTS).add(TRItemRegistry.ZEITON_INGOT.get());
    }
}
