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
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record S2CSyncShellPatterns(Map<ResourceLocation, List<ShellPattern>> patterns) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncShellPatterns> {

    public static final CustomPacketPayload.Type<S2CSyncShellPatterns> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "sync_shell_patterns"));

    private static final UnboundedMapCodec<ResourceLocation, List<ShellPattern>> MAPPER = Codec.unboundedMap(ResourceLocation.CODEC, ShellPattern.CODEC.listOf().xmap(List::copyOf, List::copyOf));

    // StreamCodec handles both serialization and deserialization
    public static final StreamCodec<FriendlyByteBuf, S2CSyncShellPatterns> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeNbt(MAPPER.encodeStart(NbtOps.INSTANCE, ref.patterns()).result().orElse(new CompoundTag()));
            },
            buf -> {
                Map<ResourceLocation, List<ShellPattern>> patterns = MAPPER.parse(NbtOps.INSTANCE, buf.readNbt()).result().orElse(ShellPatterns.registerDefaultPatterns());
                return new S2CSyncShellPatterns(patterns);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncShellPatterns value, NetworkManager.Context context) {
        ShellPatterns.getReloadListener().setData(value.patterns());
    }
}
