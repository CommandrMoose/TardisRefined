package whocraft.tardis_refined.common.network.messages.sync;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.network.handler.HandleSyncDimensions;

public record S2CSyncLevelList(ResourceKey<Level> level, boolean add) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncLevelList> {

    public static final CustomPacketPayload.Type<S2CSyncLevelList> TYPE = new CustomPacketPayload.Type<S2CSyncLevelList>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "sync_levels"));

    public static final StreamCodec<FriendlyByteBuf, S2CSyncLevelList> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeResourceKey(ref.level());
                buf.writeBoolean(ref.add());
            },
            buf -> new S2CSyncLevelList(buf.readResourceKey(Registries.DIMENSION), buf.readBoolean())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncLevelList value, NetworkManager.Context context) {
        HandleSyncDimensions.handleDimSyncPacket(this.level, this.add);
    }
}
