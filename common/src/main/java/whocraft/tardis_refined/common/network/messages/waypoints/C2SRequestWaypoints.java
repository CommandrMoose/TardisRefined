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
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

import java.util.Collection;

public class C2SRequestWaypoints extends MessageC2S {


    public C2SRequestWaypoints(FriendlyByteBuf friendlyByteBuf) {
    }

    public C2SRequestWaypoints() {
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.REQUEST_WAYPOINTS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        ServerPlayer player = context.getPlayer();
        ServerLevel level = player.serverLevel();
        TardisLevelOperator.get(level).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager waypointManager = tardisLevelOperator.getTardisWaypointManager();
            Collection<TardisWaypoint> waypoints = waypointManager.getWaypoints();
            new S2CWaypointsListScreen(waypoints).send(player);
        });

    }
}
