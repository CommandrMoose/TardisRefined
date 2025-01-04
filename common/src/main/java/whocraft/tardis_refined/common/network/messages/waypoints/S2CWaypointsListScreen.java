package whocraft.tardis_refined.common.network.messages.waypoints;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;

import java.util.ArrayList;
import java.util.Collection;

public class S2CWaypointsListScreen extends MessageS2C {

    private Collection<TardisWaypoint> waypoints;

    public S2CWaypointsListScreen(Collection<TardisWaypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public S2CWaypointsListScreen(FriendlyByteBuf friendlyByteBuf) {
        waypoints = new ArrayList<>();
        int size = friendlyByteBuf.readInt();
        for (int i = 0; i < size; i++) {
            CompoundTag tardisWay = friendlyByteBuf.readNbt();
            TardisWaypoint waypoint = TardisWaypoint.deserialise(tardisWay);
            waypoints.add(waypoint);
        }
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.OPEN_WAYPOINTS_DISPLAY;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(waypoints.size());
        for (TardisWaypoint waypoint : waypoints) {
            buf.writeNbt(waypoint.serialise());
        }
    }

    @Override
    public void handle(MessageContext context) {
        handleScreens();
    }

    @Environment(EnvType.CLIENT)
    private void handleScreens() {
        ScreenHandler.setWaypointScreen(waypoints);
    }
}
