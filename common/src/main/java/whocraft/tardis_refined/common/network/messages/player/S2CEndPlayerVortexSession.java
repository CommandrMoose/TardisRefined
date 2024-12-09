package whocraft.tardis_refined.common.network.messages.player;

import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.TardisClientLogic;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

public class S2CEndPlayerVortexSession extends MessageS2C {

    public S2CEndPlayerVortexSession() {
    }

    public S2CEndPlayerVortexSession(FriendlyByteBuf friendlyByteBuf) {
    }


    @Override
    public @NotNull MessageType getType() {
        return TardisNetwork.END_VORTEX_SESSION;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        TardisClientLogic.handleClient();
    }


}
