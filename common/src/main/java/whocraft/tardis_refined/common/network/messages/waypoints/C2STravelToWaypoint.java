package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.NetworkManager;
import net.minecraft.network.codec.StreamCodec;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.UUID;

public record C2STravelToWaypoint(UUID waypointId) implements CustomPacketPayload, NetworkManager.Handler<C2STravelToWaypoint> {

    public static final CustomPacketPayload.Type<C2STravelToWaypoint> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "travel_to_waypoint"));

    public static final StreamCodec<FriendlyByteBuf, C2STravelToWaypoint> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeUUID(ref.waypointId()),
            buf -> new C2STravelToWaypoint(buf.readUUID())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2STravelToWaypoint value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager tardisWaypointManager = tardisLevelOperator.getTardisWaypointManager();

            TardisWaypoint waypoint = tardisWaypointManager.getWaypointById(value.waypointId());
            TardisNavLocation waypointLoc = waypoint.getLocation().copy();

            TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
            pilotManager.setTargetLocation(waypointLoc);

            serverLevel.playSound(player, BlockPos.containing(player.position()), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1, 1);
            PlayerUtil.sendMessage(player, Component.translatable(ModMessages.WAYPOINT_LOADED, waypointLoc.getName()), true);
        });
    }
}
