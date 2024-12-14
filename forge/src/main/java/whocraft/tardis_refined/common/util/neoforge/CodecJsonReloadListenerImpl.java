package whocraft.tardis_refined.common.util.neoforge;

import com.mojang.serialization.Codec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.util.CodecJsonReloadListener;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class CodecJsonReloadListenerImpl {

    public static <T> CodecJsonReloadListener<T> create(String folderName, Codec<T> codec) {
        return new Impl(folderName, codec);
    }

    public static class Impl<T> extends CodecJsonReloadListener<T> {
        public Impl(String folderName, Codec<T> codec) {
            super(folderName, codec);
        }

        @Override
        public CodecJsonReloadListener<T> setSyncPacket(NetworkManager networkManager, CustomPacketPayload packetFactory) {
            NeoForge.EVENT_BUS.addListener(this.getDatapackSyncListener(networkManager, resourceLocationTMap -> packetFactory));
            return this;
        }

        /**
         * Generate an event listener function for Forge's dedicated on-datapack-sync event which is timed at the correct point when datapack registries are synced.
         * The event is fired when a player logs in or if server resources were reloaded successfully, so there is no need to add it in the login event
         **/
        private Consumer<OnDatapackSyncEvent> getDatapackSyncListener(final NetworkManager networkManager, final Function<Map<ResourceLocation, T>, CustomPacketPayload> packetFactory) {
            return event -> {
                this.handleSyncPacket(event.getPlayer(), networkManager, packetFactory);
            };
        }
    }

}