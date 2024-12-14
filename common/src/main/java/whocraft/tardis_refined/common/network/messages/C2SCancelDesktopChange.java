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

import java.util.Optional;

public record C2SCancelDesktopChange(ResourceKey<Level> resourceKey) implements CustomPacketPayload, NetworkManager.Handler<C2SCancelDesktopChange> {

    public static final CustomPacketPayload.Type<C2SCancelDesktopChange> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "cancel_change_desktop"));

    public static final StreamCodec<FriendlyByteBuf, C2SCancelDesktopChange> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeResourceKey(ref.resourceKey()),
            buf -> new C2SCancelDesktopChange(buf.readResourceKey(Registries.DIMENSION))
    );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SCancelDesktopChange value, NetworkManager.Context context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.ifPresent(x -> {
            TardisLevelOperator.get(x).ifPresent(y -> y.getInteriorManager().cancelDesktopChange());
        });
    }
}
