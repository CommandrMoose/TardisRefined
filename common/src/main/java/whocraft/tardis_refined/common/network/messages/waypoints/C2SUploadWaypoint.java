package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.NetworkManager;
import net.minecraft.network.codec.StreamCodec;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

public record C2SUploadWaypoint(TardisNavLocation tardisNavLocation, CoordInputType coordInputType)
        implements CustomPacketPayload, NetworkManager.Handler<C2SUploadWaypoint> {

    public static final CustomPacketPayload.Type<C2SUploadWaypoint> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "upload_waypoint"));

    public static final StreamCodec<FriendlyByteBuf, C2SUploadWaypoint> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeNbt(ref.tardisNavLocation().serialise());
                buf.writeUtf(ref.coordInputType().name());
            },
            buf -> new C2SUploadWaypoint(
                    TardisNavLocation.deserialize(buf.readNbt()),
                    CoordInputType.valueOf(buf.readUtf())
            )
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SUploadWaypoint value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            if (value.coordInputType() == CoordInputType.WAYPOINT) {
                TardisWaypointManager tardisWaypointManager = tardisLevelOperator.getTardisWaypointManager();
                tardisWaypointManager.addWaypoint(value.tardisNavLocation().copy(), value.tardisNavLocation().getName());
            } else {
                TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
                pilotManager.setTargetLocation(value.tardisNavLocation().copy());
            }
        });
    }
}
