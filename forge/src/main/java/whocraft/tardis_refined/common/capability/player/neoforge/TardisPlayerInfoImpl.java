package whocraft.tardis_refined.common.capability.player.neoforge;

import com.mojang.serialization.Codec;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

import java.util.Optional;
import java.util.function.Supplier;

import static net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion.MOD_ID;

@EventBusSubscriber(modid = TardisRefined.MODID)
public class TardisPlayerInfoImpl {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MOD_ID);

    private static final Supplier<AttachmentType<CompoundTag>> TARDIS_PLAYER_INFO = ATTACHMENT_TYPES.register(
            "tardis_player_info", () -> AttachmentType.builder(CompoundTag::new).serialize(CompoundTag.CODEC).build());


    public static Optional<TardisPlayerInfo> get(LivingEntity player) {
        return player.getCapability(TARDIS_PLAYER_INFO.get());
    }

    public static class TardisPlayerInfoProvider implements ICapabilitySerializable<CompoundTag> {

        public final TardisPlayerInfo capability;
        public final LazyOptional<TardisPlayerInfo> lazyOptional;

        public TardisPlayerInfoProvider(TardisPlayerInfo capability) {
            this.capability = capability;
            this.lazyOptional = LazyOptional.of(() -> capability);
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
            return capability == TARDIS_PLAYER_INFO ? this.lazyOptional.cast() : LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.capability.saveData();
        }

        @Override
        public void deserializeNBT(CompoundTag arg) {
            this.capability.loadData(arg);
        }
    }

}