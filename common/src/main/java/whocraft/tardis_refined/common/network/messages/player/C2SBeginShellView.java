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

public record C2SBeginShellView() implements CustomPacketPayload, NetworkManager.Handler<C2SBeginShellView> {

    public static final CustomPacketPayload.Type<C2SBeginShellView> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "begin_shell_view"));

    public static final StreamCodec<FriendlyByteBuf, C2SBeginShellView> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> { },
            buf -> new C2SBeginShellView()
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SBeginShellView value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();
        Level level = player.level();
        if (level instanceof ServerLevel serverLevel) {
            TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator ->
                    TardisPlayerInfo.get(player).ifPresent(tardisInfo ->
                            tardisInfo.setupPlayerForInspection(player, tardisLevelOperator,
                                    tardisLevelOperator.getPilotingManager().isTakingOff()
                                            ? tardisLevelOperator.getPilotingManager().getCurrentLocation()
                                            : tardisLevelOperator.getPilotingManager().getTargetLocation(),
                                    !tardisLevelOperator.getPilotingManager().isTakingOff()))
            );
        }
    }
}
