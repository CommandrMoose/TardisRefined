package whocraft.tardis_refined.common.network.messages.screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.network.NetworkManager;

public record S2COpenShellSelection(ResourceLocation currentShell) implements CustomPacketPayload, NetworkManager.Handler<S2COpenShellSelection> {

    public static final CustomPacketPayload.Type<S2COpenShellSelection> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "open_shell_selection"));

    public static final StreamCodec<FriendlyByteBuf, S2COpenShellSelection> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeResourceLocation(ref.currentShell),
            buf -> new S2COpenShellSelection(buf.readResourceLocation())
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2COpenShellSelection value, NetworkManager.Context context) {
        if (context.isClient()) {
            value.handleScreens();
        }
    }

    @Environment(EnvType.CLIENT)
    private void handleScreens() {
        // Open the shell selection screen.
        ScreenHandler.openShellSelection(currentShell);
    }
}
