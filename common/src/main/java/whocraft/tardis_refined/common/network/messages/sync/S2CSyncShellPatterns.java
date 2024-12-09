package whocraft.tardis_refined.common.network.messages.sync;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.UnboundedMapCodec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S2CSyncShellPatterns extends MessageS2C {

    protected final UnboundedMapCodec<ResourceLocation, List<ShellPattern>> MAPPER = Codec.unboundedMap(ResourceLocation.CODEC, ShellPattern.CODEC.listOf().xmap(List::copyOf, List::copyOf));
    protected Map<ResourceLocation, List<ShellPattern>> patterns = new HashMap<>();

    public S2CSyncShellPatterns(Map<ResourceLocation, List<ShellPattern>> patterns) {
        this.patterns = patterns;
    }

    public S2CSyncShellPatterns(FriendlyByteBuf buf) {
        this.patterns = MAPPER.parse(NbtOps.INSTANCE, buf.readNbt()).result().orElse(ShellPatterns.registerDefaultPatterns());
    }

    @Override
    public MessageType getType() {
        return TardisNetwork.SYNC_SHELL_PATTERNS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt((CompoundTag) MAPPER.encodeStart(NbtOps.INSTANCE, this.patterns).result().orElse(new CompoundTag()));
    }

    @Override
    public void handle(MessageContext context) {
        ShellPatterns.getReloadListener().setData(this.patterns);
    }
}