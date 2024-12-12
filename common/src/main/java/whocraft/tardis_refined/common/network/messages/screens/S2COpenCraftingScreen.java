package whocraft.tardis_refined.common.network.messages.screens;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;

public class S2COpenCraftingScreen extends MessageS2C {

    public S2COpenCraftingScreen() {
    }

    public S2COpenCraftingScreen(FriendlyByteBuf friendlyByteBuf) {
    }


    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.OPEN_CRAFTING_SCREEN;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        ScreenHandler.openCraftingScreen();
    }
}
