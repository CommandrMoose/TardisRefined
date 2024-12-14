package whocraft.tardis_refined.common.network.messages.sync;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.hum.HumEntry;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.network.NetworkManager;

import java.util.Map;

public record S2CSyncHums(
        Map<ResourceLocation, HumEntry> tardisHums) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncHums> {

    public static final CustomPacketPayload.Type<S2CSyncHums> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "sync_hums"));

    private static final Codec<Map<ResourceLocation, HumEntry>> MAPPER = Codec.unboundedMap(ResourceLocation.CODEC, HumEntry.codec());

    // StreamCodec handles both serialization and deserialization
    public static final StreamCodec<FriendlyByteBuf, S2CSyncHums> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeNbt(MAPPER.encodeStart(NbtOps.INSTANCE, ref.tardisHums()).result().orElse(new CompoundTag()));
            },
            buf -> {
                Map<ResourceLocation, HumEntry> tardisHums = MAPPER.parse(NbtOps.INSTANCE, buf.readNbt()).result().orElse(TardisHums.registerDefaultHums());
                return new S2CSyncHums(tardisHums);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncHums value, NetworkManager.Context context) {
        TardisHums.getRegistry().clear();
        for (Map.Entry<ResourceLocation, HumEntry> entry : value.tardisHums().entrySet()) {
            TardisHums.getRegistry().put(entry.getKey(), entry.getValue());
        }
    }
}
