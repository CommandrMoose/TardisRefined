package whocraft.tardis_refined.common.network.messages.upgrades;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.common.network.NetworkManager;

public record S2CDisplayUpgradeScreen(
        CompoundTag compoundTag) implements CustomPacketPayload, NetworkManager.Handler<S2CDisplayUpgradeScreen> {

    public static final CustomPacketPayload.Type<S2CDisplayUpgradeScreen> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "display_upgrade_screen"));

    public static final StreamCodec<FriendlyByteBuf, S2CDisplayUpgradeScreen> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeNbt(ref.compoundTag()),
            buf -> new S2CDisplayUpgradeScreen(buf.readNbt())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Environment(EnvType.CLIENT)
    private static void display(CompoundTag compoundTag) {
        ScreenHandler.displayUpgradesScreen(compoundTag);
    }

    @Override
    public void receive(S2CDisplayUpgradeScreen value, NetworkManager.Context context) {
        if (context.isClient()) {
            display(value.compoundTag());
        }
    }
}
