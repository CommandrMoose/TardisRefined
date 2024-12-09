package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.manager.TardisWaypointManager;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.UUID;

public class C2STravelToWaypoint extends MessageC2S {

    UUID waypointId;

    public C2STravelToWaypoint(UUID waypointId) {
        this.waypointId = waypointId;
    }


    public C2STravelToWaypoint(FriendlyByteBuf buf) {
        waypointId = buf.readUUID();
    }


    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.SET_WAYPOINT;
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

            TardisWaypoint waypoint = tardisWaypointManager.getWaypointById(waypointId);
            TardisNavLocation waypointLoc = waypoint.getLocation().copy();

            TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
            pilotManager.setTargetLocation(waypointLoc);

            serverLevel.playSound(player, BlockPos.containing(player.position()), SoundEvents.STONE_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1, 1);
            PlayerUtil.sendMessage(player, Component.translatable(ModMessages.WAYPOINT_LOADED, waypointLoc.getName()), true);

        });
    }
}
