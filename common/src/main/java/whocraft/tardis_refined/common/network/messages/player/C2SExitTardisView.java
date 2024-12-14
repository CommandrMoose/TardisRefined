package whocraft.tardis_refined.common.network.messages.player;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.util.Platform;

public record C2SExitTardisView() implements CustomPacketPayload, NetworkManager.Handler<C2SExitTardisView> {

    public static final CustomPacketPayload.Type<C2SExitTardisView> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "exit_tardis_view"));

    public static final StreamCodec<FriendlyByteBuf, C2SExitTardisView> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> { },
            buf -> new C2SExitTardisView()
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SExitTardisView value, NetworkManager.Context context) {
        ServerPlayer player = (ServerPlayer) context.getPlayer();

        TardisPlayerInfo.get(player).ifPresent(tardisInfo -> {
            if (tardisInfo.isViewingTardis()) {
                ResourceKey<Level> key = ResourceKey.create(Registries.DIMENSION,
                        ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, tardisInfo.getViewedTardis().toString()));
                ServerLevel tardisLevel = Platform.getServer().getLevel(key);
                if (tardisLevel != null) {
                    TardisLevelOperator.get(tardisLevel).ifPresent(tardisLevelOperator -> {
                        tardisInfo.endPlayerForInspection(player);
                    });
                }
            }
        });
    }
}
