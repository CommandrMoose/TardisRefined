package whocraft.tardis_refined.common.network.messages.sync;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.network.NetworkManager;

public record S2CSyncTardisPlayerView(int entityID,
                                      CompoundTag nbt) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncTardisPlayerView> {

    public static final CustomPacketPayload.Type<S2CSyncTardisPlayerView> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "tardis_player_info"));

    public static final StreamCodec<FriendlyByteBuf, S2CSyncTardisPlayerView> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeInt(ref.entityID());
                buf.writeNbt(ref.nbt());
            },
            buf -> {
                int entityID = buf.readInt();
                CompoundTag nbt = buf.readNbt();
                return new S2CSyncTardisPlayerView(entityID, nbt);
            }
    );


    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncTardisPlayerView value, NetworkManager.Context context) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        Entity entity = level.getEntity(value.entityID());
        if (entity instanceof Player player) {
            TardisPlayerInfo.get(player).ifPresent(info -> info.loadData(value.nbt()));
        }
    }
}
