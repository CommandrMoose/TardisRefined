package whocraft.tardis_refined.common.capability.player.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;

public class TardisPlayerComponents implements EntityComponentInitializer {

    public static final ComponentKey<TardisPlayerInfoImpl> TARDIS_PLAYER_INFO =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation(TardisRefined.MODID, "tardis_player_info"), TardisPlayerInfoImpl.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(TARDIS_PLAYER_INFO, TardisPlayerInfoImpl::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}