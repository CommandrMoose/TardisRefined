
package whocraft.tardis_refined.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Utility class to help with managing registry entries.
 * Maintains a list of all suppliers for entries and registers them automatically.
 * Suppliers should return NEW instances every time.
 * Example Usage:
 * <pre>{@code
 *   private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MODID, Registry.ITEM_REGISTRY);
 *   private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID, Registry.BLOCK_REGISTRY);
 *
 *   public static final RegistryHolder<Block> ROCK_BLOCK = BLOCKS.register("rock", () -> new Block(Block.Properties.create(Material.ROCK)));
 *   public static final RegistryHolder<Item> ROCK_ITEM = ITEMS.register("rock", () -> new BlockItem(ROCK_BLOCK.get(), new Item.Properties().group(ItemGroup.MISC)));
 *
 *   public ExampleMod() {
 *       ITEMS.register();
 *       BLOCKS.register();
 *   }
 * }</pre>
 *
 * @param <T> The base registry type
 */
public abstract class DeferredRegister<T> implements Iterable<RegistryHolder<T, ? extends T>> {

    public static final List<RegistryHolder<PoiType, ? extends PoiType>> POI_TYPES_TO_FIX = new ArrayList<>();

    /**
     * This MUST be called during mod-initialization to make sure the {@link DeferredRegister} is registed to the event bus on the Forge side
     */
    public abstract void register();

    /**
     * Adds a new supplier to the list of entries to be registered, and returns a {@link RegistryHolder} that will be populated with the created entry automatically.
     *
     * @param id       ID for the given registered object
     * @param supplier Supplier that returns the object to be registered
     * @return A {@link RegistryHolder} that will contain the registered object
     */
    public abstract <R extends T> RegistryHolder<T, R> register(String id, Supplier<R> supplier);

    /**
     * @return Unmodifiable list of all registered objects
     */
    public abstract Collection<RegistryHolder<T, ? extends T>> getEntries();

    @NotNull
    @Override
    public Iterator<RegistryHolder<T, ? extends T>> iterator() {
        return this.getEntries().iterator();
    }

    /**
     * Creates a new instance of a {@link DeferredRegister} with the given resource key.
     *
     * @param modId       The namespace that will be applied to all registered entries
     * @param resourceKey The resource key for the registry that the entries are registered into
     * @param <T>         Generic type of the registry
     * @return A new instance of a {@link DeferredRegister}
     */
    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    public static <T> DeferredRegister<T> create(String modId, ResourceKey<? extends Registry<T>> resourceKey) {
     /*   if (Platform.isArchitecturyLoaded()) {
            ResourceKey key = resourceKey;
            return ArchDeferredRegisterWrapper.get(modId, key);
        } else {*/
        return createInternal(modId, resourceKey);
        /* }*/
    }

    @ExpectPlatform
    public static <T> DeferredRegister<T> createInternal(String modId, ResourceKey<? extends Registry<T>> resourceKey) {
        throw new AssertionError();
    }

    /**
     * Creates a new instance of a {@link DeferredRegister} with the given {@link RegistryBuilder}
     *
     * @param modId    The namespace that will be applied to all registered entries
     * @param registry Instance of a custom {@link RegistryBuilder} that will be used
     * @param <T>      Generic type of the registry
     * @return A new instance of a {@link DeferredRegister}
     */
    public static <T> DeferredRegister<T> create(String modId, Registry<T> registry) {
        return create(modId, registry.key());
    }

}