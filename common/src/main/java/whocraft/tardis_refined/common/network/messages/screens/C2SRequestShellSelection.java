package whocraft.tardis_refined.common.network.messages.screens;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.network.TardisNetwork;

public record C2SRequestShellSelection() implements CustomPacketPayload, NetworkManager.Handler<C2SRequestShellSelection> {

    public static final CustomPacketPayload.Type<C2SRequestShellSelection> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "request_shell_selection"));

    public static final StreamCodec<FriendlyByteBuf, C2SRequestShellSelection> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {},
            buf -> new C2SRequestShellSelection()
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SRequestShellSelection value, NetworkManager.Context context) {
        ServerLevel serverLevel = (ServerLevel) context.getPlayer().level();
        TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {
            new S2COpenShellSelection(tardisLevelOperator.getAestheticHandler().getShellTheme()).send((ServerPlayer) context.getPlayer());
        });
    }
}
