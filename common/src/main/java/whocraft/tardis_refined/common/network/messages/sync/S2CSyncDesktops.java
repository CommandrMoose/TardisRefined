package whocraft.tardis_refined.common.network.messages.sync;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;

import java.util.Map;

public record S2CSyncDesktops(Map<ResourceLocation, DesktopTheme> desktops) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncDesktops> {

    //We use an unboundedMapCodec. However it is limited in that it can only parse objects whose keys can be serialised to a string, such as ResourceLocation
    private static final Codec<Map<ResourceLocation, DesktopTheme>> MAPPER = Codec.unboundedMap(ResourceLocation.CODEC, DesktopTheme.getCodec());

    public static final CustomPacketPayload.Type<S2CSyncDesktops> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "sync_desktops"));

    public static final StreamCodec<FriendlyByteBuf, S2CSyncDesktops> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> buf.writeNbt(MAPPER.encodeStart(NbtOps.INSTANCE, ref.desktops()).result().orElse(new CompoundTag())),
            buf -> new S2CSyncDesktops(MAPPER.parse(NbtOps.INSTANCE, buf.readNbt()).result().orElse(TardisDesktops.registerDefaultDesktops()))
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncDesktops value, NetworkManager.Context context) {
        TardisDesktops.getRegistry().clear();
        for (Map.Entry<ResourceLocation, DesktopTheme> entry : value.desktops().entrySet()) {
            TardisDesktops.getRegistry().put(entry.getKey(), entry.getValue());
        }
    }
}
