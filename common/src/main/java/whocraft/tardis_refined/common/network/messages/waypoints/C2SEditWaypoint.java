package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.NetworkManager;
import net.minecraft.network.codec.StreamCodec;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

public record C2SEditWaypoint(TardisWaypoint waypoint) implements CustomPacketPayload, NetworkManager.Handler<C2SEditWaypoint> {

    public static final CustomPacketPayload.Type<C2SEditWaypoint> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "edit_waypoint"));

    public static final StreamCodec<FriendlyByteBuf, C2SEditWaypoint> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeNbt(ref.waypoint().serialise()),
            buf -> new C2SEditWaypoint(TardisWaypoint.deserialise(buf.readNbt()))
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SEditWaypoint value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager tardisWaypointManager = tardisLevelOperator.getTardisWaypointManager();
            tardisWaypointManager.editWaypoint(value.waypoint());
        });
    }
}
