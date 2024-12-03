package whocraft.tardis_refined.common.capability.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.fabric.TardisLevelOperatorImpl;
import whocraft.tardis_refined.registry.TRDimensionTypes;

public class TRComponents implements WorldComponentInitializer {

    public static final ComponentKey<TardisLevelOperatorImpl> TARDIS_DATA = ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation(TardisRefined.MODID, "tardis_data"), TardisLevelOperatorImpl.class);

    /**
     * Registers the appropriate world component factory for the current environment.
     *
     * @param registry the registry to register the factory to
     */
    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(TARDIS_DATA, level -> {

            if (level instanceof ServerLevel serverLevel && level.dimensionTypeId().location() == TRDimensionTypes.TARDIS.location()) {
                return new TardisLevelOperatorImpl(serverLevel);
            }
            return new TardisLevelOperatorDummy(level);
        });
    }
}
