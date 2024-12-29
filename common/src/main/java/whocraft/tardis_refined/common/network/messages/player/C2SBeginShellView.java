package whocraft.tardis_refined.common.network.messages.player;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

public class C2SBeginShellView extends MessageC2S {

    public C2SBeginShellView() {
    }

    public C2SBeginShellView(FriendlyByteBuf buf) {
    }


    @Override
    public @NotNull MessageType getType() {
        return TardisNetwork.START_VORTEX_SESSION;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        ServerPlayer player = context.getPlayer();
        Level level = context.getPlayer().level();
        if (level instanceof ServerLevel serverLevel) {
            TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> TardisPlayerInfo.get(context.getPlayer()).ifPresent(tardisInfo ->
                    tardisInfo.startShellView(player, tardisLevelOperator, tardisLevelOperator.getPilotingManager().isTakingOff() ? tardisLevelOperator.getPilotingManager().getCurrentLocation() : tardisLevelOperator.getPilotingManager().getTargetLocation(), tardisLevelOperator.getPilotingManager().isInFlight())
            ));
        }
    }
}
