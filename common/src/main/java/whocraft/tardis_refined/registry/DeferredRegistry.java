package whocraft.tardis_refined.registry;

import com.mojang.serialization.Codec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/** Abstraction of a Registry handler based off the design patterns of Forge's DeferredRegister.
 * This also acts as a dedicated wrapper object for object types that require a custom registry.
 * <br> This object stores a vanilla {@link Registry} instance based of a {@link ResourceKey<Registry<T>>} which means as long as mods register to the same {@link ResourceKey}, they will be correctly appending new entries correctly.
 * <br> For example, here is what the main Tardis Refined mod's registry class might look like:
 * <pre>{@code
 * public class TRControlRegistry {
 *
 *     public static final ResourceKey<Registry<Control>> CONTROL_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(TardisRefined.MODID, "control"));
 *
 *     public static final DeferredRegistry<Control> TR_CONTROLS =  DeferredRegistry.createCustom(TardisRefined.MODID, CONTROL_REGISTRY_KEY, true);
 *
 *     // Source of truth for all entries is derived from the
 *     // Due to Forge in 1.20.1 using its entirely isolated registry system from vanilla, we cannot use the underlying vanilla registry as a source of truth
 *     // Therefore, for 1.20.1 ONLY, we must only call the lookup methods provided such as DeferredRegistry#entrySet
 *     public static final Map.Entry<ResourceKey<Control>, Control> GLOBAL_CONTROL_REGISTRY = TR_CONTROLS.entrySet();
 *
 *     //Register using RegistrySupplier if you only need the raw object and no compatibiltiy with other vanilla features
 *     public static final RegistrySupplier<Control> THROTTLE = TR_CONTROLS.register("throttle", () -> new GenericControl(new ResourceLocation(TardisRefined.MODID, "throttle"));
 *
 *     //Unfortunately, we cannot register using RegistrySupplierHolder in 1.20.1 because Forge's Registries are isolated from vanilla, so in order to have unified modding interface we must only use suppliers
 *
 *}
 *
 * public class TardisConsoleBlock {
 *
 *     public Set<ResourceLocation> getAllControls() {
 *         return TRControlRegistry.TR_CONTROLS.keySet();
 *     }
 * }
 *
 * public class TardisRefined {
 *     public TardisRefined() {
 *         TR_CONTROLS.registerToModBus();
 *     }
 * }
 * }</pre>
 * <br> And here is how the addon mod's registry class may look like:
 * <pre>{@code
 * public class AddonModControlRegistry {
 *     //The addon mod uses the same ResourceKey<Registry<Control>> as the TardisRefined DeferredRegistry BUT with the addon mod's namespace (MyAddonMod.MODID). This way the addon mod is registering new entries to the same source of truth as Tardis Refined but under its own namespace
 *     //DO NOT use Tardis Refined's namespace
 *     private static final DeferredRegistry<Control> MY_ADDON_CONTROLS =  DeferredRegistry.createCustom(MyAddonMod.MODID, TRControlRegistry.CONTROL_REGISTRY_KEY, true);
 *
 *     //Register entries to the addon mod's instance of the deferred registry using a RegistrySupplier
 *     public static final RegistrySupplier<Control> SPEED = MY_ADDON_CONTROLS.register("speed", () -> new GenericControl(new ResourceLocation(MyAddonMod.MODID, "speed"));
 *
 *     //Unfortunately, we cannot register using RegistrySupplierHolder in 1.20.1 because Forge's Registries are isolated from vanilla, so in order to have unified modding interface we must only use suppliers
 * }
 *
 * public class MyAddonMod {
 *
 *     public static final String MODID = "my_addon_mod";
 *
 *     public MyAddonMod() {
 *         MY_ADDON_CONTROLS.registerToModBus();
 *     }
 * }
 * }</pre>
 * <br> As you can see, the Addon Mod just needs to create its own DeferredRegistry instance and reference the TardisRefined Registry's ResourceKey.
 * <br> This way, registering objects is greatly simplified along with the ability to append new entries to an existing registry
 * <br> This also ensures that there is a global source of truth for all our TardisRefined object types, and we don't accidentally restrict the mod to only use Tardis Refined's entries in areas where addon mods are likely to add additional content. E.g. Upgrades
 * */
public abstract class DeferredRegistry<T> {
    /** Call in main mod constructor to classload the registry class. On Forge/NeoForge environments, the necessary event buses for registries will be called*/
    public abstract void registerToModBus();
    /** Register using a Supplier */
    public abstract <R extends T> RegistrySupplier<R> register(String id, Supplier<R> supplier);

    /** 1.20.1: Comment out due to Forge 1.20.1 not exposing the vanilla registry, thus we need to exclude this to have a common interface for both Fabric and Forge
    /** Create a RegistrySupplierHolder. This has similar behaviour to a Supplier but has vanilla Holder attributes such as use in tags
    public abstract <I extends T> RegistrySupplierHolder<T, I> registerHolder(final String name, final Supplier<I> sup);
    */

    /** 1.20.1: Must comment this out because of Forge 1.20.1 not exposing the underlying vanilla registry
     *  Therefore, to maintain a consistent API for both Fabric and Forge, we cannot grab the vanilla registry unfortunately.
     *  This means we cannot utilise vanilla registry features such as compatibility with tags in datapacks
    /** Get the underlying registry, which includes all entries added by any mod that has a DeferredRegistry with the same ResourceKey.
     * All lookup methods should be called from here.
    public abstract Supplier<Registry<T>> getRegistry();
     */



    /**
     * Create a DeferredRegistry instance for vanilla registries
     * @param modid - Your Mod's unique identifier
     * @param resourceKey - Resource Key for the Registry
     * @return
     * @param <T>
     */
    @ExpectPlatform
    public static <T> DeferredRegistry<T> create(String modid, ResourceKey<? extends Registry<T>> resourceKey) {
        throw new RuntimeException(TardisRefined.PLATFORM_ERROR);
    }

    /**
     * Create a DeferredRegistry instance for custom registries
     * @param modid - Your Mod's unique identifier
     * @param resourceKey - Resource Key for the Registry
     * @param syncToClient - True if we want the objects to sync to the client.
     * @return
     * @param <T>
     */
    @ExpectPlatform
    public static <T> DeferredRegistry<T> createCustom(String modid, ResourceKey<Registry<T>> resourceKey, boolean syncToClient) {
        throw new AssertionError();
    }

    /** Below are exposing of the methods in Registry for convenience. This reduces verbosity for lookup operations which we will often be performing*/

    public abstract ResourceKey<? extends Registry<T>> key();

    public abstract T get(ResourceLocation key);

    public abstract ResourceLocation getKey(T object);

    public abstract Set<ResourceLocation> keySet();

    /** Gets the values in the underlying registry ordered by key. This is sufficient for most purposes */
    public abstract Set<Map.Entry<ResourceKey<T>, T>> entrySet();

    public abstract boolean containsKey(ResourceLocation key);


    /** Gets the underlying Codec for the registry object type, if defined.
     * <br> Required for reading/writing data to different formats for data-driven features*/
    public abstract Supplier<Codec<T>> getCodec();

}
