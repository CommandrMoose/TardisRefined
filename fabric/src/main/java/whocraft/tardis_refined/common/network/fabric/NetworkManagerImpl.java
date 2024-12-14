package whocraft.tardis_refined.common.network.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.util.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NetworkManagerImpl extends NetworkManager {

    public static NetworkManager make() {
        return new NetworkManagerImpl();
    }

    public <T extends CustomPacketPayload> void registerS2C(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, NetworkManager.Handler<T> receiver) {
        PayloadTypeRegistry.playS2C().register(type, codec);

        if (Platform.isClient()) {
            registerClientReceiver(type, codec, receiver);
        }
    }

    @Environment(EnvType.CLIENT)
    private static <T extends CustomPacketPayload> void registerClientReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, NetworkManager.Handler<T> receiver) {
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> receiver.receive(payload, makeContext(context.player(), context.client(), true)));
    }

    public <T extends CustomPacketPayload> void registerC2S(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, NetworkManager.Handler<T> receiver) {
        PayloadTypeRegistry.playC2S().register(type, codec);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            receiver.receive(payload, makeContext(context.player(), context.player().getServer(), false));
        });
    }

    @Override
    public <T extends CustomPacketPayload> Packet<?> toC2SPacket(T payload) {
        return ClientPlayNetworking.createC2SPacket(payload);
    }

    @Override
    public <T extends CustomPacketPayload> Packet<?> toS2CPacket(T payload) {
        return ServerPlayNetworking.createS2CPacket(payload);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void sendToServer(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        ClientPlayNetworking.send(payload);
        for (CustomPacketPayload packetPayload : payloads) {
            ClientPlayNetworking.send(packetPayload);
        }
    }

    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        player.connection.send(makeClientboundPacket(payload, payloads));
    }

    @Override
    public void sendToPlayersInDimension(ServerLevel level, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        level.getServer().getPlayerList().broadcastAll(makeClientboundPacket(payload, payloads), level.dimension());
    }

    @Override
    public void sendToPlayersNear(ServerLevel level, @Nullable ServerPlayer excluded, double x, double y, double z, double radius, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        Packet<?> packet = makeClientboundPacket(payload, payloads);
        level.getServer().getPlayerList().broadcast(excluded, x, y, z, radius, level.dimension(), packet);
    }

    @Override
    public void sendToAllPlayers(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        MinecraftServer server = Objects.requireNonNull(Platform.getServer(), "Cannot send clientbound payloads on the client");
        server.getPlayerList().broadcastAll(makeClientboundPacket(payload, payloads));
    }

    @Override
    public void sendToPlayersTrackingEntity(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        for (ServerPlayer player : PlayerLookup.tracking(entity)) {
            player.connection.send(makeClientboundPacket(payload, payloads));
        }
    }

    @Override
    public void sendToPlayersTrackingEntityAndSelf(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        if (entity instanceof ServerPlayer player) {
            player.connection.send(makeClientboundPacket(payload, payloads));
        }

        for (ServerPlayer player : PlayerLookup.tracking(entity)) {
            player.connection.send(makeClientboundPacket(payload, payloads));
        }
    }

    @Override
    public void sendToPlayersTrackingChunk(ServerLevel level, ChunkPos chunkPos, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        for (ServerPlayer player : PlayerLookup.tracking(level, chunkPos)) {
            player.connection.send(makeClientboundPacket(payload, payloads));
        }
    }

    private static Packet<?> makeClientboundPacket(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        if (payloads.length > 0) {
            final List<Packet<? super ClientGamePacketListener>> packets = new ArrayList<>();
            packets.add(new ClientboundCustomPayloadPacket(payload));
            for (CustomPacketPayload otherPayload : payloads) {
                packets.add(new ClientboundCustomPayloadPacket(otherPayload));
            }
            return new ClientboundBundlePacket(packets);
        } else {
            return new ClientboundCustomPayloadPacket(payload);
        }
    }

    private static Context makeContext(Player player, BlockableEventLoop<?> queue, boolean client) {
        return new Context() {
            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public void queue(Runnable runnable) {
                queue.execute(runnable);
            }

            @Override
            public boolean isClient() {
                return client;
            }

            @Override
            public RegistryAccess getRegistryAccess() {
                return player.registryAccess();
            }
        };
    }

}