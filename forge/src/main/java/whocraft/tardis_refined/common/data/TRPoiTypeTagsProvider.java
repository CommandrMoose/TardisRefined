package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRPointOfInterestTypes;

import java.util.concurrent.CompletableFuture;

public class TRPoiTypeTagsProvider extends PoiTypeTagsProvider {

    public TRPoiTypeTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, TardisRefined.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        var appender = tag(PoiTypeTags.ACQUIRABLE_JOB_SITE);
        appender.add(TRPointOfInterestTypes.CONSOLE_UNIT);
       // appender.add(TRPointOfInterestTypes.ASTRAL_MAP);
    }

}
