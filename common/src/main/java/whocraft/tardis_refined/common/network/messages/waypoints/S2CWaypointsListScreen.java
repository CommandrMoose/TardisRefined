package whocraft.tardis_refined.common.network.messages.waypoints;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;

import java.util.ArrayList;
import java.util.Collection;

public record S2CWaypointsListScreen(Collection<TardisWaypoint> waypoints) implements CustomPacketPayload, NetworkManager.Handler<S2CWaypointsListScreen> {

    // Register the message type for identification
    public static final CustomPacketPayload.Type<S2CWaypointsListScreen> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "open_waypoints_display"));

    // Serializer for this message
    public static final StreamCodec<FriendlyByteBuf, S2CWaypointsListScreen> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeInt(ref.waypoints.size());
                for (TardisWaypoint waypoint : ref.waypoints) {
                    buf.writeNbt(waypoint.serialise());
                }
            },
            buf -> {
                int size = buf.readInt();
                Collection<TardisWaypoint> waypoints = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    CompoundTag tardisWay = buf.readNbt();
                    TardisWaypoint waypoint = TardisWaypoint.deserialise(tardisWay);
                    waypoints.add(waypoint);
                }
                return new S2CWaypointsListScreen(waypoints);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CWaypointsListScreen value, NetworkManager.Context context) {
        if (context.isClient()) {
            value.handleScreens();
        }
    }

    @Environment(EnvType.CLIENT)
    private void handleScreens() {
        // Open the waypoints screen on the client
        ScreenHandler.setWaypointScreen(waypoints);
    }
}
