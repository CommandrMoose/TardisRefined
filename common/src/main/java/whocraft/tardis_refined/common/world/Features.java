package whocraft.tardis_refined.common.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.world.feature.NbtTemplateFeature;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class Features {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(TardisRefined.MODID, Registries.FEATURE);

    public static final RegistryHolder<Feature<?>, NbtTemplateFeature> NBT_FEATURE = FEATURES.register("nbt_feature", NbtTemplateFeature::new);
}