package whocraft.tardis_refined.common.network.messages.waypoints;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;

public record S2COpenEditCoordinatesDisplayMessage(
        TardisWaypoint waypoint) implements CustomPacketPayload, NetworkManager.Handler<S2COpenEditCoordinatesDisplayMessage> {

    // Register the message type for identification
    public static final CustomPacketPayload.Type<S2COpenEditCoordinatesDisplayMessage> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "open_edit_coords_screen"));

    // Serializer for this message
    public static final StreamCodec<FriendlyByteBuf, S2COpenEditCoordinatesDisplayMessage> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeNbt(ref.waypoint.serialise());
            },
            buf -> {
                CompoundTag tardisNav = buf.readNbt();
                TardisWaypoint waypoint = TardisWaypoint.deserialise(tardisNav);
                return new S2COpenEditCoordinatesDisplayMessage(waypoint);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2COpenEditCoordinatesDisplayMessage value, NetworkManager.Context context) {
        if (context.isClient()) {
            value.handleDisplay();
        }
    }

    @Environment(EnvType.CLIENT)
    private void handleDisplay() {
        // Open the edit coordinates screen on the client
        ScreenHandler.openEditCoordinatesScreen(waypoint);
    }
}
