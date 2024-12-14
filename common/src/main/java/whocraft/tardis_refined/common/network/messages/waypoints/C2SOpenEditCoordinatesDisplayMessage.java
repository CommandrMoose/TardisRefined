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
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

import java.util.UUID;

public record C2SOpenEditCoordinatesDisplayMessage(UUID waypointId) implements CustomPacketPayload, NetworkManager.Handler<C2SOpenEditCoordinatesDisplayMessage> {

    public static final CustomPacketPayload.Type<C2SOpenEditCoordinatesDisplayMessage> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "edit_coords")
    );

    public static final StreamCodec<FriendlyByteBuf, C2SOpenEditCoordinatesDisplayMessage> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeUUID(ref.waypointId()),
            buf -> new C2SOpenEditCoordinatesDisplayMessage(buf.readUUID())
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SOpenEditCoordinatesDisplayMessage value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        ServerLevel level = player.serverLevel();

        TardisLevelOperator.get(level).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager waypointManager = tardisLevelOperator.getTardisWaypointManager();
            TardisWaypoint waypoint = waypointManager.getWaypointById(value.waypointId());
            if (waypoint != null) {
                NetworkManager.get().sendToPlayer(player, new S2COpenEditCoordinatesDisplayMessage(waypoint));
            }
        });
    }
}
