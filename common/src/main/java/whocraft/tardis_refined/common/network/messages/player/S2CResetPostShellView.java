package whocraft.tardis_refined.common.network.messages.player;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientLogic;
import whocraft.tardis_refined.common.network.NetworkManager;

public record S2CResetPostShellView() implements CustomPacketPayload, NetworkManager.Handler<S2CResetPostShellView> {

    // Register the message type for identification
    public static final CustomPacketPayload.Type<S2CResetPostShellView> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "end_vortex_session"));

    // Serializer for this message
    public static final StreamCodec<FriendlyByteBuf,S2CResetPostShellView> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                // No data to write as this is an empty message
            },
            buf -> new S2CResetPostShellView() // No data to read
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private void handleClient() {
        // Handle the client-side logic for resetting the post-shell view
        TardisClientLogic.handleClient();
    }

    @Override
    public void receive(S2CResetPostShellView value, NetworkManager.Context context) {
        if (context.isClient()) {
            value.handleClient();
        }
    }
}
