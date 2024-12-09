package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.nbt.CompoundTag;
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

public class C2SEditWaypoint extends MessageC2S {

    TardisWaypoint waypoint;

    public C2SEditWaypoint(TardisWaypoint waypoint) {
        this.waypoint = waypoint;
    }

    public C2SEditWaypoint(FriendlyByteBuf buf) {
        CompoundTag tardisNav = buf.readNbt();
        this.waypoint = TardisWaypoint.deserialise(tardisNav);
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.EDIT_WAYPOINT;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(waypoint.serialise());
    }

    @Override
    public void handle(MessageContext context) {

        ServerPlayer player = context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            TardisWaypointManager tardisWaypointManager = tardisLevelOperator.getTardisWaypointManager();
            tardisWaypointManager.editWaypoint(waypoint);
        });
    }
}
