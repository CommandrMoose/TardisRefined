package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.entity.ControlEntity;

public class TREntityRegistry {

    public static final DeferredRegistry<EntityType<?>> ENTITY_TYPES = DeferredRegistry.create(TardisRefined.MODID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<ControlEntity>> CONTROL_ENTITY = ENTITY_TYPES.register("console_control", () -> registerStatic(ControlEntity::new, MobCategory.MISC, 0.125F, 0.125F, 64, 40, "console_control"));

    /**
     * @param factory
     * @param classification - Choose MISC if not a moving entity (mob)
     * @param width
     * @param height
     * @param trackingRange  - Radius in chunks around the entity within which the entity is synced to clients
     * @param updateFreq     - How many ticks before the entity updates
     * @param name           - Registry name. We automatically append the modid to it
     * @param <T>
     * @return
     */
    public static <T extends Entity> EntityType<T> registerStatic(EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height, int trackingRange, int updateFreq, String name) {
        ResourceLocation loc = new ResourceLocation(TardisRefined.MODID, name);
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, classification);
        builder.clientTrackingRange(trackingRange);
        builder.updateInterval(updateFreq);
        builder.sized(width, height);
        builder.fireImmune();
        return builder.build(loc.toString());
    }

}

