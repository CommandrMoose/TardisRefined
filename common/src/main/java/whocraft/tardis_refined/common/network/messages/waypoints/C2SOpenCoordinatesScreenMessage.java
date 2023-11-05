package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisControlManager;
import whocraft.tardis_refined.common.util.DimensionUtil;

import java.util.List;
import java.util.Set;

public class C2SOpenCoordinatesScreenMessage extends MessageC2S {

    private CoordInputType coordInput;

    public C2SOpenCoordinatesScreenMessage(CoordInputType coordInputType) {
        this.coordInput = coordInputType;
    }

    public C2SOpenCoordinatesScreenMessage(FriendlyByteBuf friendlyByteBuf) {
        this.coordInput = CoordInputType.valueOf(friendlyByteBuf.readUtf());
    }


    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.CLIENT_OPEN_COORDS_SCREEN;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(coordInput.name());
    }

    @Override
    public void handle(MessageContext context) {
        ServerPlayer serverPlayer = context.getPlayer();
        MinecraftServer server = context.getPlayer().server;
        List<ResourceKey<Level>> dimensions = DimensionUtil.getAllowedDimensions(server).stream().toList();
        ServerLevel level = serverPlayer.getLevel();
        TardisLevelOperator.get(level).ifPresent(tardisLevelOperator -> {
            TardisControlManager controlManager = tardisLevelOperator.getControlManager();
            TardisNavLocation tardisTarget = controlManager.getTargetLocation() == null ? TardisNavLocation.ORIGIN : controlManager.getTargetLocation();
            new S2COpenCoordinatesScreenMessage(dimensions, coordInput, tardisTarget).send(serverPlayer);
        });
    }
}
