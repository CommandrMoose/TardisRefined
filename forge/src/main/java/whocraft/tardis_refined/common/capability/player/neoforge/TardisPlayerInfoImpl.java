package whocraft.tardis_refined.common.capability.player.neoforge;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = TardisRefined.MODID)
public class TardisPlayerInfoImpl {

    public static Capability<TardisPlayerInfo> TARDIS_PLAYER_INFO = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent e) {
        e.register(TardisPlayerInfo.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof Player player) {
            e.addCapability(new ResourceLocation(TardisRefined.MODID, "tardis_player_info"), new TardisPlayerInfoProvider(new TardisPlayerInfo(player)));
        }
    }


    public static Optional<TardisPlayerInfo> get(LivingEntity player) {
        return player.getCapability(TARDIS_PLAYER_INFO).resolve();
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