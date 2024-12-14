package whocraft.tardis_refined.registry.fabric;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.registry.DeferredRegister;
import whocraft.tardis_refined.registry.RegistryHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DeferredRegisterImpl {

    public static <T> DeferredRegister<T> createInternal(String modid, ResourceKey<? extends Registry<T>> resourceKey) {
        return new Impl<>(modid, resourceKey);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions", "rawtypes"})
    public static class Impl<T> extends DeferredRegister<T> {

        private final String modid;
        private final Registry<T> registry;
        private final List<RegistryHolder<T, ? extends T>> entries;

        public Impl(String modid, ResourceKey<? extends Registry<T>> resourceKey) {
            this.modid = modid;
            this.registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(resourceKey.location());
            this.entries = new ArrayList<>();
        }

        @Override
        public void register() {

        }

        @SuppressWarnings("UnnecessaryLocalVariable")
        @Override
        public <R extends T> RegistryHolder<T, R> register(String id, Supplier<R> supplier) {
            ResourceKey<R> registeredId = (ResourceKey<R>) ResourceKey.create(this.registry.key(), ResourceLocation.fromNamespaceAndPath(this.modid, id));
            Registry registry1 = this.registry;
            RegistryHolder registryHolder = new RegistryHolderImpl(registeredId, Registry.register(registry1, registeredId, supplier.get()), this.registry);
            this.entries.add(registryHolder);
            return registryHolder;
        }

        @Override
        public Collection<RegistryHolder<T, ? extends T>> getEntries() {
            return ImmutableList.copyOf(this.entries);
        }
    }

    public static class RegistryHolderImpl<R, T extends R> extends RegistryHolder<R, T> {

        private final ResourceKey<T> id;
        private final T object;
        private final Holder<R> holder;

        @SuppressWarnings("unchecked")
        public RegistryHolderImpl(ResourceKey<T> id, T object, Registry<T> registry) {
            this.id = id;
            this.object = object;
            this.holder = (Holder<R>) registry.getHolder(id).orElseThrow();
        }

        @Override
        public ResourceLocation getId() {
            return this.id.location();
        }

        @Override
        public T get() {
            return this.object;
        }

        @Override
        public @NotNull R value() {
            return this.holder.value();
        }

        @Override
        public boolean isBound() {
            return this.holder.isBound();
        }

        @Override
        public boolean is(ResourceLocation location) {
            return this.holder.is(location);
        }

        @Override
        public boolean is(ResourceKey<R> resourceKey) {
            return this.holder.is(resourceKey);
        }

        @Override
        public boolean is(Predicate<ResourceKey<R>> predicate) {
            return this.holder.is(predicate);
        }

        @Override
        public boolean is(TagKey<R> tagKey) {
            return this.holder.is(tagKey);
        }

        @Override
        public boolean is(Holder<R> holder) {
            return this.holder.is(holder);
        }

        @Override
        public @NotNull Stream<TagKey<R>> tags() {
            return this.holder.tags();
        }

        @Override
        public @NotNull Either<ResourceKey<R>, R> unwrap() {
            return this.holder.unwrap();
        }

        @Override
        public @NotNull Optional<ResourceKey<R>> unwrapKey() {
            return this.holder.unwrapKey();
        }

        @Override
        public @NotNull Kind kind() {
            return this.holder.kind();
        }

        @Override
        public boolean canSerializeIn(HolderOwner<R> owner) {
            return this.holder.canSerializeIn(owner);
        }
    }

}