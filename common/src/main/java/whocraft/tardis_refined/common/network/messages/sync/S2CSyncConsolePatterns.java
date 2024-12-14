package whocraft.tardis_refined.common.network.messages.sync;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.UnboundedMapCodec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.patterns.ConsolePattern;
import whocraft.tardis_refined.patterns.ConsolePatterns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record S2CSyncConsolePatterns(Map<ResourceLocation, List<ConsolePattern>> patterns) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncConsolePatterns> {

    protected static final UnboundedMapCodec<ResourceLocation, List<ConsolePattern>> MAPPER = Codec.unboundedMap(ResourceLocation.CODEC, ConsolePattern.CODEC.listOf().xmap(List::copyOf, List::copyOf));

    public static final CustomPacketPayload.Type<S2CSyncConsolePatterns> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "sync_console_patterns"));

    public static final StreamCodec<FriendlyByteBuf, S2CSyncConsolePatterns> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeNbt(MAPPER.encodeStart(NbtOps.INSTANCE, ref.patterns()).result().orElse(new CompoundTag())),
            buf -> new S2CSyncConsolePatterns(MAPPER.parse(NbtOps.INSTANCE, buf.readNbt()).result().orElse(ConsolePatterns.registerDefaultPatterns()))
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncConsolePatterns value, NetworkManager.Context context) {
        ConsolePatterns.getReloadListener().setData(value.patterns());
    }
}
