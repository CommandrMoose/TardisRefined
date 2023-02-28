package whocraft.tardis_refined.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class TRParticles {

    public static final DeferredRegistry<ParticleType<?>> TYPES = DeferredRegistry.create(TardisRefined.MODID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> GALLIFREY = TYPES.register("gallifrey", TRParticles::getParticleType);


    @ExpectPlatform
    public static SimpleParticleType getParticleType(){
        throw new RuntimeException(TardisRefined.PLATFORM_ERROR);
    }

}
