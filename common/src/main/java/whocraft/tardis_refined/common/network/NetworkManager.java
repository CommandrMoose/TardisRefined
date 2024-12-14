package whocraft.tardis_refined.common.network;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import org.jetbrains.annotations.Nullable;

public abstract class NetworkManager {

    private static final NetworkManager INSTANCE = make();

    public static NetworkManager get() {
        return INSTANCE;
    }

    @ExpectPlatform
    private static NetworkManager make() {
        throw new AssertionError();
    }

    @Environment(EnvType.CLIENT)
    public abstract <T extends CustomPacketPayload> void registerS2C(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, NetworkManager.Handler<T> receiver);

    public abstract <T extends CustomPacketPayload> void registerC2S(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, NetworkManager.Handler<T> receiver);

    public abstract <T extends CustomPacketPayload> Packet<?> toC2SPacket(T payload);

    public abstract <T extends CustomPacketPayload> Packet<?> toS2CPacket(T payload);

    @Environment(EnvType.CLIENT)
    public abstract void sendToServer(CustomPacketPayload payload, CustomPacketPayload... payloads);

    public abstract void sendToPlayer(ServerPlayer player, CustomPacketPayload payload, CustomPacketPayload... payloads);

    public abstract void sendToPlayersInDimension(ServerLevel level, CustomPacketPayload payload, CustomPacketPayload... payloads);

    public abstract void sendToPlayersNear(
            ServerLevel level,
            @Nullable ServerPlayer excluded,
            double x,
            double y,
            double z,
            double radius,
            CustomPacketPayload payload,
            CustomPacketPayload... payloads);

    public abstract void sendToAllPlayers(CustomPacketPayload payload, CustomPacketPayload... payloads);

    public abstract void sendToPlayersTrackingEntity(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads);

    public abstract void sendToPlayersTrackingEntityAndSelf(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads);

    public abstract void sendToPlayersTrackingChunk(ServerLevel level, ChunkPos chunkPos, CustomPacketPayload payload, CustomPacketPayload... payloads);

    @FunctionalInterface
    public interface Handler<T> {
        void receive(T value, Context context);
    }

    public interface Context {

        Player getPlayer();

        void queue(Runnable runnable);

        boolean isClient();

        default boolean isServer() {
            return !this.isClient();
        }

        RegistryAccess getRegistryAccess();

    }

}