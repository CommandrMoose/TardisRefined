package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

import java.util.UUID;

public class RemoveWaypointEntryMessage extends MessageC2S {

    UUID waypointId;

    public RemoveWaypointEntryMessage(UUID waypointId) {
        this.waypointId = waypointId;
    }


    public RemoveWaypointEntryMessage(FriendlyByteBuf buf) {
        waypointId = buf.readUUID();
    }


    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.DEL_WAYPOINT;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(waypointId);
    }

    @Override
    public void handle(MessageContext context) {
        ServerPlayer player = context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager tardisWaypointManager = tardisLevelOperator.getTardisWaypointManager();
            tardisWaypointManager.deleteWaypoint(waypointId);
            new WaypointsListScreenMessage(tardisWaypointManager.getWaypoints()).send(player);
        });
    }
}
