package whocraft.tardis_refined.registry.neoforge;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.neoforge.TardisRefinedForge;
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

    public static class Impl<T> extends DeferredRegister<T> {

        private final String modid;
        private final net.neoforged.neoforge.registries.DeferredRegister<T> register;
        private final List<RegistryHolder<T, ? extends T>> entries;

        public Impl(String modid, ResourceKey<? extends Registry<T>> resourceKey) {
            this.modid = modid;
            this.register = net.neoforged.neoforge.registries.DeferredRegister.create(resourceKey, modid);
            this.entries = new ArrayList<>();
        }

        @Override
        public void register() {
            this.register.register(TardisRefinedForge.getModEventBus(this.modid).orElseThrow(() -> new IllegalStateException("Mod '" + this.modid + "' did not register event bus to PalladiumCore!")));
        }

        @Override
        public <R extends T> RegistryHolder<T, R> register(String id, Supplier<R> supplier) {
            var orig = this.register.register(id, supplier);
            var registrySupplier = new RegistryHolderImpl<>(orig);
            this.entries.add(registrySupplier);
            return registrySupplier;
        }

        @Override
        public Collection<RegistryHolder<T, ? extends T>> getEntries() {
            return ImmutableList.copyOf(this.entries);
        }
    }

    public static class RegistryHolderImpl<R, T extends R> extends RegistryHolder<R, T> {

        private final DeferredHolder<R, T> forgeHolder;

        public RegistryHolderImpl(DeferredHolder<R, T> forgeHolder) {
            this.forgeHolder = forgeHolder;
        }

        @Override
        public ResourceLocation getId() {
            return this.forgeHolder.getId();
        }

        @Override
        public T get() {
            return this.forgeHolder.get();
        }

        @Override
        public @NotNull R value() {
            return this.forgeHolder.value();
        }

        @Override
        public boolean isBound() {
            return this.forgeHolder.isBound();
        }

        @Override
        public boolean is(@NotNull ResourceLocation location) {
            return this.forgeHolder.is(location);
        }

        @Override
        public boolean is(@NotNull ResourceKey<R> resourceKey) {
            return this.forgeHolder.is(resourceKey);
        }

        @Override
        public boolean is(@NotNull Predicate<ResourceKey<R>> predicate) {
            return this.forgeHolder.is(predicate);
        }

        @Override
        public boolean is(@NotNull TagKey<R> tagKey) {
            return this.forgeHolder.is(tagKey);
        }

        @Override
        public boolean is(Holder<R> holder) {
            return this.forgeHolder.is(holder);
        }

        @Override
        public @NotNull Stream<TagKey<R>> tags() {
            return this.forgeHolder.tags();
        }

        @Override
        public @NotNull Either<ResourceKey<R>, R> unwrap() {
            return this.forgeHolder.unwrap();
        }

        @Override
        public @NotNull Optional<ResourceKey<R>> unwrapKey() {
            return this.forgeHolder.unwrapKey();
        }

        @Override
        public @NotNull Kind kind() {
            return this.forgeHolder.kind();
        }

        @Override
        public boolean canSerializeIn(@NotNull HolderOwner<R> owner) {
            return this.forgeHolder.canSerializeIn(owner);
        }
    }
}