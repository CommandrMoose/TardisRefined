package net.threetag.palladiumcore.network.neoforge;

import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.neoforge.TardisRefinedForge;

public class NetworkManagerImpl extends NetworkManager {

    public static NetworkManager make() {
        return new NetworkManagerImpl();
    }

    @Override
    public <T extends CustomPacketPayload> void registerS2C(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, Handler<T> receiver) {
        TardisRefinedForge.whenModBusAvailable(TardisRefined.MODID, bus -> {
            bus.<RegisterPayloadHandlersEvent>addListener(event -> {
                event.registrar(type.id().getNamespace()).optional().playToClient(type, codec, (arg, context) -> {
                    receiver.receive(arg, makeContext(context.player(), context, true));
                });
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void registerC2S(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, Handler<T> receiver) {
        TardisRefinedForge.whenModBusAvailable(TardisRefined.MODID, bus -> {
            bus.<RegisterPayloadHandlersEvent>addListener(event -> {
                event.registrar(type.id().getNamespace()).optional().playToServer(type, codec, (arg, context) -> {
                    receiver.receive(arg, makeContext(context.player(), context, false));
                });
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> Packet<?> toC2SPacket(T payload) {
        return new ServerboundCustomPayloadPacket(payload);
    }

    @Override
    public <T extends CustomPacketPayload> Packet<?> toS2CPacket(T payload) {
        return new ClientboundCustomPayloadPacket(payload);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void sendToServer(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToServer(payload, payloads);
    }

    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToPlayer(player, payload, payloads);
    }

    @Override
    public void sendToPlayersInDimension(ServerLevel level, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToPlayersInDimension(level, payload, payloads);
    }

    @Override
    public void sendToPlayersNear(ServerLevel level, @Nullable ServerPlayer excluded, double x, double y, double z, double radius, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToPlayersNear(level, excluded, x, y, z, radius, payload, payloads);
    }

    @Override
    public void sendToAllPlayers(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToAllPlayers(payload, payloads);
    }

    @Override
    public void sendToPlayersTrackingEntity(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, payload, payloads);
    }

    @Override
    public void sendToPlayersTrackingEntityAndSelf(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, payload, payloads);
    }

    @Override
    public void sendToPlayersTrackingChunk(ServerLevel level, ChunkPos chunkPos, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        PacketDistributor.sendToPlayersTrackingChunk(level, chunkPos, payload, payloads);
    }

    private static Context makeContext(Player player, IPayloadContext queue, boolean client) {
        return new Context() {
            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public void queue(Runnable runnable) {
                queue.enqueueWork(runnable);
            }

            @Override
            public boolean isClient() {
                return client;
            }

            @Override
            public RegistryAccess getRegistryAccess() {
                return client ? getClientRegistryAccess() : player.registryAccess();
            }
        };
    }

    @OnlyIn(Dist.CLIENT)
    public static RegistryAccess getClientRegistryAccess() {
        if (Minecraft.getInstance().level != null) {
            return Minecraft.getInstance().level.registryAccess();
        } else if (Minecraft.getInstance().getConnection() != null) {
            return Minecraft.getInstance().getConnection().registryAccess();
        } else if (Minecraft.getInstance().gameMode != null) {
            return Minecraft.getInstance().gameMode.connection.registryAccess();
        }

        // Fail-safe
        return RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
    }
}