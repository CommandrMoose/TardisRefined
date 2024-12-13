package whocraft.tardis_refined.common.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TREntityRegistry;
import whocraft.tardis_refined.registry.TRTagKeys;

import java.util.concurrent.CompletableFuture;

public class ProviderEntityTags extends EntityTypeTagsProvider {

    public static final TagKey<EntityType<?>> ENTITY_BLACKLIST = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("carryon", "entity_blacklist"));


    public ProviderEntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, completableFuture, TardisRefined.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Carry On Mod compat
        tag(ENTITY_BLACKLIST).add(TREntityRegistry.CONTROL_ENTITY.get());

        //Tardis teleport blacklist
        tag(TRTagKeys.TARDIS_TELEPORT_BLACKLIST).add(TREntityRegistry.CONTROL_ENTITY.get());
        tag(TRTagKeys.TARDIS_TELEPORT_BLACKLIST).add(EntityType.ENDER_DRAGON);
        tag(TRTagKeys.TARDIS_TELEPORT_BLACKLIST).add(EntityType.WITHER);

        //Add Create Mod entities to blacklist
        tag(TRTagKeys.TARDIS_TELEPORT_BLACKLIST).addOptionalTag(ResourceLocation.fromNamespaceAndPath("create", "contraption"));
        tag(TRTagKeys.TARDIS_TELEPORT_BLACKLIST).addOptionalTag(ResourceLocation.fromNamespaceAndPath("create", "gantry_contraption"));
        tag(TRTagKeys.TARDIS_TELEPORT_BLACKLIST).addOptionalTag(ResourceLocation.fromNamespaceAndPath("create", "crafting_blueprint"));

    }
}
