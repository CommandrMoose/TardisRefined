package whocraft.tardis_refined.common.network.messages.screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;

public record S2COpenMonitor(
        boolean desktopGenerating,
        TardisNavLocation currentLocation,
        TardisNavLocation targetLocation,
        CompoundTag upgradeHandlerNbt
) implements CustomPacketPayload, NetworkManager.Handler<S2COpenMonitor> {

    public static final CustomPacketPayload.Type<S2COpenMonitor> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "open_monitor"));

    public static final StreamCodec<FriendlyByteBuf, S2COpenMonitor> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeBoolean(ref.desktopGenerating);
                buf.writeNbt(ref.currentLocation.serialise());
                buf.writeNbt(ref.targetLocation.serialise());
                buf.writeNbt(ref.upgradeHandlerNbt);
            },
            buf -> new S2COpenMonitor(
                    buf.readBoolean(),
                    TardisNavLocation.deserialize(buf.readNbt()),
                    TardisNavLocation.deserialize(buf.readNbt()),
                    buf.readNbt()
            )
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2COpenMonitor value, NetworkManager.Context context) {
        if (context.isClient()) {
            value.handleScreens();
        }
    }

    @Environment(EnvType.CLIENT)
    private void handleScreens() {
        // Open the monitor.
        ScreenHandler.openMonitorScreen(desktopGenerating, upgradeHandlerNbt, currentLocation, targetLocation);
    }
}
