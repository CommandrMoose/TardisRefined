package whocraft.tardis_refined.common.network.messages.sync;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.TardisClientLogic;
import whocraft.tardis_refined.common.network.NetworkManager;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

public record S2CSyncTardisClientData(ResourceKey<Level> level, CompoundTag compoundTag) implements CustomPacketPayload, NetworkManager.Handler<S2CSyncTardisClientData> {

    public static final CustomPacketPayload.Type<S2CSyncTardisClientData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "int_reaction"));

    public static final StreamCodec<FriendlyByteBuf, S2CSyncTardisClientData> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeResourceKey(ref.level());
                buf.writeNbt(ref.compoundTag());
            },
            buf -> {
                ResourceKey<Level> level = buf.readResourceKey(Registries.DIMENSION);
                CompoundTag compoundTag = buf.readNbt();
                return new S2CSyncTardisClientData(level, compoundTag);
            }
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(S2CSyncTardisClientData value, NetworkManager.Context context) {
        // Retrieve the TardisIntReactions instance for the current level
        TardisClientData data = TardisClientData.getInstance(value.level());

        // Deserialize the Tardis instance from the given CompoundTag
        data.deserializeNBT(value.compoundTag());

        // Update the Tardis instance
        TardisClientLogic.update(data);
    }
}
