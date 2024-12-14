package whocraft.tardis_refined.common.util.fabric;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.util.CodecJsonReloadListener;

import java.util.Map;
import java.util.function.Function;

public class CodecJsonReloadListenerImpl {

    public static <T> CodecJsonReloadListener<T> create(String folderName, Codec<T> codec) {
        return new Impl<T>(folderName, codec);
    }

    public static class Impl<T> extends CodecJsonReloadListener<T> {
        public Impl(String folderName, Codec<T> codec) {
            super(folderName, codec);
        }


        @Override
        public CodecJsonReloadListener<T> setSyncPacket(NetworkManager networkManager, CustomPacketPayload packetFactory) {
            ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, joined) -> {
                this.handleSyncPacket(player, networkManager, resourceLocationTMap -> packetFactory);
            });
            return this;
        }
    }

}