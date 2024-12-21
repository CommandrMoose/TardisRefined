package whocraft.tardis_refined.common.network.messages.sync;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.soundscape.hum.HumEntry;
import whocraft.tardis_refined.common.soundscape.hum.TardisHums;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

import java.util.HashMap;
import java.util.Map;

public class S2CSyncHums extends MessageS2C {

    //We use an unboundedMapCodec. However it is limited in that it can only parse objects whose keys can be serialised to a string, such as ResourceLocation
    //E.g. If you used an int as a key, the unboundedMapCodec will not parse it and will error.
    private static final Codec<Map<ResourceLocation, HumEntry>> MAPPER = Codec.unboundedMap(ResourceLocation.CODEC, HumEntry.codec());
    private Map<ResourceLocation, HumEntry> tardisHums = new HashMap<>();


    public S2CSyncHums(Map<ResourceLocation, HumEntry> tardisHums) {
        this.tardisHums = tardisHums;
    }

    public S2CSyncHums(FriendlyByteBuf buf) {
        //Parse our Map Codec and send the nbt data over. If there's any errors, populate with default Tardis Refined console rooms
        this.tardisHums = MAPPER.parse(NbtOps.INSTANCE, buf.readNbt()).result().orElse(TardisHums.registerDefaultHums());
    }

    @Override
    public MessageType getType() {
        return TardisNetwork.SYNC_HUMS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(MAPPER.encodeStart(NbtOps.INSTANCE, this.tardisHums).result().orElse(new CompoundTag()));
    }

    @Override
    public void handle(MessageContext context) {
        TardisHums.getRegistry().clear();
        for (Map.Entry<ResourceLocation, HumEntry> entry : this.tardisHums.entrySet()) {
            TardisHums.getRegistry().put(entry.getKey(), entry.getValue());
        }
    }
}
