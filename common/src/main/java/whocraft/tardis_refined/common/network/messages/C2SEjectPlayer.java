package whocraft.tardis_refined.common.network.messages;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;

public record C2SEjectPlayer() implements CustomPacketPayload, NetworkManager.Handler<C2SEjectPlayer> {

    public static final CustomPacketPayload.Type<C2SEjectPlayer> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "eject_player"));

    public static final StreamCodec<FriendlyByteBuf, C2SEjectPlayer> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> { },
            buf -> new C2SEjectPlayer()
    );

    @Override
    public @NotNull CustomPacketPayload.Type<?> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SEjectPlayer value, NetworkManager.Context context) {

        ServerPlayer player = (ServerPlayer) context.getPlayer();
        Level playerLevel = player.level();

        if (playerLevel instanceof ServerLevel serverLevel) {
            TardisLevelOperator.get(serverLevel).ifPresent(operator -> {

                if (!operator.getPilotingManager().isInFlight()) {
                    operator.forceEjectPlayer(player);
                } else {
                    PlayerUtil.sendMessage(player, Component.translatable(ModMessages.UI_EJECT_CANNOT_IN_FLIGHT), true);
                    player.playSound(SoundEvents.ITEM_BREAK);
                }

            });
        }

    }
}
