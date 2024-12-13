package whocraft.tardis_refined.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;
import whocraft.tardis_refined.registry.RegistrySupplier;

public class TRParticles {

    public static final DeferredRegister<ParticleType<?>> TYPES = DeferredRegister.create(TardisRefined.MODID, Registries.PARTICLE_TYPE);

    public static final RegistryHolder<ParticleType<?>, SimpleParticleType> GALLIFREY = TYPES.register("gallifrey", TRParticles::getParticleType);
    public static final RegistryHolder<ParticleType<?>, SimpleParticleType> ARS_LEAVES = TYPES.register("ars_leaves", TRParticles::getParticleType);


    @ExpectPlatform
    public static SimpleParticleType getParticleType() {
        throw new RuntimeException(TardisRefined.PLATFORM_ERROR);
    }

}
