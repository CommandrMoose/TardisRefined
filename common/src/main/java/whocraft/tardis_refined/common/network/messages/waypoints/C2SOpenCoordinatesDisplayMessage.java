package whocraft.tardis_refined.common.network.messages.waypoints;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.network.messages.upgrades.C2SUnlockUpgrade;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.DimensionUtil;

import java.util.List;

public record C2SOpenCoordinatesDisplayMessage(CoordInputType coordInput) implements CustomPacketPayload, NetworkManager.Handler<C2SOpenCoordinatesDisplayMessage> {

    public static final CustomPacketPayload.Type<C2SOpenCoordinatesDisplayMessage> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "open_coords_display"));

    public static final StreamCodec<FriendlyByteBuf, C2SOpenCoordinatesDisplayMessage> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeUtf(ref.coordInput().name()),
            buf -> new C2SOpenCoordinatesDisplayMessage(CoordInputType.valueOf(buf.readUtf()))
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SOpenCoordinatesDisplayMessage value, NetworkManager.Context context) {
        ServerPlayer serverPlayer = (ServerPlayer) context.getPlayer();
        ServerLevel level = serverPlayer.serverLevel();

        TardisLevelOperator.get(level).ifPresent(tardisLevelOperator -> {
            List<ResourceKey<Level>> dimensions = tardisLevelOperator.getProgressionManager().getDiscoveredLevels();
            TardisPilotingManager pilotManager = tardisLevelOperator.getPilotingManager();
            TardisNavLocation tardisTarget = pilotManager.getTargetLocation() == null
                    ? TardisNavLocation.ORIGIN
                    : pilotManager.getTargetLocation();
            NetworkManager.get().sendToPlayer(serverPlayer, new S2COpenCoordinatesDisplayMessage(dimensions, value.coordInput(), tardisTarget));
        });
    }
}
