package whocraft.tardis_refined.common.network.messages.hums;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.hum.HumEntry;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;

import java.util.Optional;

public record C2SChangeHum(ResourceKey<Level> resourceKey, HumEntry humEntry) implements CustomPacketPayload, NetworkManager.Handler<C2SChangeHum> {

    // Register the message type for identification
    public static final CustomPacketPayload.Type<C2SChangeHum> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "change_hum"));

    public static final StreamCodec<FriendlyByteBuf, C2SChangeHum> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeResourceKey(ref.resourceKey);
                buf.writeResourceLocation(ref.humEntry.getIdentifier());
            },
            buf -> new C2SChangeHum(
                    buf.readResourceKey(Registries.DIMENSION),
                    TardisHums.getHumById(buf.readResourceLocation())
            )
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SChangeHum value, NetworkManager.Context context) {
        if (context.isServer()) {
            value.handleServer(context);
        }
    }

    private void handleServer(NetworkManager.Context context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.ifPresent(x -> {
            TardisLevelOperator.get(x).ifPresent(operator -> {
                TardisInteriorManager tardisInteriorManager = operator.getInteriorManager();
                TardisClientData tardisClientData = operator.tardisClientData();
                tardisInteriorManager.setHumEntry(humEntry);
                tardisClientData.setHumEntry(humEntry);
                tardisClientData.sync();
            });
        });
    }
}
