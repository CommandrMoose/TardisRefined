package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

import java.util.UUID;

public record C2SRemoveWaypointEntry(UUID waypointId) implements CustomPacketPayload, NetworkManager.Handler<C2SRemoveWaypointEntry> {

    public static final CustomPacketPayload.Type<C2SRemoveWaypointEntry> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "delete_waypoint")
    );

    public static final StreamCodec<FriendlyByteBuf, C2SRemoveWaypointEntry> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeUUID(ref.waypointId()),
            buf -> new C2SRemoveWaypointEntry(buf.readUUID())
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SRemoveWaypointEntry value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager waypointManager = tardisLevelOperator.getTardisWaypointManager();
            waypointManager.deleteWaypoint(value.waypointId());
            NetworkManager.get().sendToPlayer(player, new S2CWaypointsListScreen(waypointManager.getWaypoints()));
        });
    }
}
