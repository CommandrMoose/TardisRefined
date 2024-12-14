package whocraft.tardis_refined.common.network.messages;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import net.minecraft.network.codec.StreamCodec;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;

import java.util.Optional;

public record C2SChangeVortex(ResourceKey<Level> resourceKey, ResourceLocation vortex) implements CustomPacketPayload, NetworkManager.Handler<C2SChangeVortex> {

    public static final CustomPacketPayload.Type<C2SChangeVortex> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "change_vortex"));

    public static final StreamCodec<FriendlyByteBuf, C2SChangeVortex> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeResourceKey(ref.resourceKey());
                buf.writeResourceLocation(ref.vortex());
            },
            buf -> new C2SChangeVortex(
                    buf.readResourceKey(Registries.DIMENSION),
                    buf.readResourceLocation()
            )
    );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SChangeVortex value, NetworkManager.Context context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.flatMap(TardisLevelOperator::get).ifPresent(y -> y.getAestheticHandler().setVortex(this.vortex));
    }
}
