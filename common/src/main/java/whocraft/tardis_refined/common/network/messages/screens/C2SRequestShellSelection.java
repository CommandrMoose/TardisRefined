package whocraft.tardis_refined.common.network.messages.screens;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

public class C2SRequestShellSelection extends MessageC2S {

    public C2SRequestShellSelection() {
    }

    public C2SRequestShellSelection(FriendlyByteBuf friendlyByteBuf) {
    }


    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.REQUEST_SHELL_C2S;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        ServerLevel serverLevel = context.getPlayer().serverLevel();
        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            new S2COpenShellSelection(tardisLevelOperator.getAestheticHandler().getShellTheme()).send(context.getPlayer());
        });
    }
}
