package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.NetworkManager;
import net.minecraft.network.codec.StreamCodec;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

import java.util.Collection;

public record C2SRequestWaypoints() implements CustomPacketPayload, NetworkManager.Handler<C2SRequestWaypoints> {

    public static final CustomPacketPayload.Type<C2SRequestWaypoints> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "request_waypoints"));

    public static final StreamCodec<FriendlyByteBuf, C2SRequestWaypoints> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {}, // No data to write in this case
            buf -> new C2SRequestWaypoints()
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SRequestWaypoints value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        ServerLevel level = player.serverLevel();
        TardisLevelOperator.get(level).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager waypointManager = tardisLevelOperator.getTardisWaypointManager();
            Collection<TardisWaypoint> waypoints = waypointManager.getWaypoints();
            new S2CWaypointsListScreen(waypoints).send(player);
        });
    }
}
