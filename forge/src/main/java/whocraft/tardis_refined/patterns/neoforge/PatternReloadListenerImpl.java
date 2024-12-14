package whocraft.tardis_refined.patterns.neoforge;

import com.mojang.serialization.Codec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.patterns.BasePattern;
import whocraft.tardis_refined.patterns.PatternCollection;
import whocraft.tardis_refined.patterns.PatternReloadListener;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class PatternReloadListenerImpl {

    public static <P extends PatternCollection, B extends BasePattern> PatternReloadListener<P, B> createListener(String folderName, Codec<P> codec, final Function<List<P>, List<B>> merger) {
        return new Impl(folderName, codec, merger);
    }

    public static class Impl<T extends PatternCollection, B extends BasePattern> extends PatternReloadListener<T, B> {
        public Impl(String folderName, Codec<T> codec, final Function<List<T>, List<B>> merger) {
            super(folderName, codec, merger);
        }

        @Override
        public PatternReloadListener setSyncPacket(NetworkManager networkManager, Function packetFactory) {
            NeoForge.EVENT_BUS.addListener(this.getDatapackSyncListener(networkManager, packetFactory));
            return this;
        }

        /**
         * Generate an event listener function for Forge's dedicated on-datapack-sync event which is timed at the correct point when datapack registries are synced.
         * The event is fired when a player logs in or if server resources were reloaded successfully, so there is no need to add it in the login event
         **/
        private Consumer<OnDatapackSyncEvent> getDatapackSyncListener(final NetworkManager networkManager, final Function<Map<ResourceLocation, List<B>>, CustomPacketPayload> packetFactory) {
            return event -> {
                this.handleSyncPacket(event.getPlayer(), networkManager, packetFactory);
            };
        }
    }
}
