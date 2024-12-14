package whocraft.tardis_refined.common.network.messages.player;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenShellSelection;

public record StartVortexSessionMessage() implements CustomPacketPayload, NetworkManager.Handler<StartVortexSessionMessage> {

    // Register the message type for identification
    public static final CustomPacketPayload.Type<S2COpenShellSelection> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "start_vortex_session"));

    // Serializer for this message
    public static final StreamCodec<FriendlyByteBuf,StartVortexSessionMessage> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                // No data to write as this is an empty message
            },
            buf -> new StartVortexSessionMessage() // No data to read
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


    private void handleServer(NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        Level level = player.level();
        if (level instanceof ServerLevel serverLevel) {
            TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator ->
                    TardisPlayerInfo.get(player).ifPresent(tardisInfo ->
                            tardisInfo.setupPlayerForInspection(player, tardisLevelOperator,
                                    tardisLevelOperator.getPilotingManager().isTakingOff()
                                            ? tardisLevelOperator.getPilotingManager().getCurrentLocation()
                                            : tardisLevelOperator.getPilotingManager().getTargetLocation(),
                                    !tardisLevelOperator.getPilotingManager().isTakingOff())
                    )
            );
        }
    }

    @Override
    public void receive(StartVortexSessionMessage value, NetworkManager.Context context) {
        if (context.isServer()) {
            value.handleServer(context);
        }
    }
}
