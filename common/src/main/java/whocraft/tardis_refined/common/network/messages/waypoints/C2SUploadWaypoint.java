package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;

public class C2SUploadWaypoint extends MessageC2S {

    TardisNavLocation tardisNavLocation;
    CoordInputType coordInputType;

    public C2SUploadWaypoint(TardisNavLocation tardisNavLocation, CoordInputType coordInputType) {
        this.tardisNavLocation = tardisNavLocation;
        this.coordInputType = coordInputType;
    }


    public C2SUploadWaypoint(FriendlyByteBuf buf) {
        CompoundTag tardisNav = buf.readNbt();
        this.tardisNavLocation = TardisNavLocation.deserialize(tardisNav);
        this.coordInputType = CoordInputType.valueOf(buf.readUtf());
    }


    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.UPLOAD_WAYPOINT;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(tardisNavLocation.serialise());
        buf.writeUtf(coordInputType.name());
    }

    @Override
    public void handle(MessageContext context) {
        ServerPlayer player = context.getPlayer();
        ServerLevel serverLevel = player.serverLevel();

        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            if (coordInputType == CoordInputType.WAYPOINT) {
                TardisWaypointManager tardisWaypointManager = tardisLevelOperator.getTardisWaypointManager();
                tardisWaypointManager.addWaypoint(tardisNavLocation.copy(), tardisNavLocation.getName());
            } else {
                TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
                pilotManager.setTargetLocation(tardisNavLocation.copy());
            }
        });
    }
}
