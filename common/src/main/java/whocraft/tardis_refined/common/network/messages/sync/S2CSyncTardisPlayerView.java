package whocraft.tardis_refined.common.network.messages.sync;


import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

public class S2CSyncTardisPlayerView extends MessageS2C {
    public int entityID;
    public CompoundTag nbt;

    public S2CSyncTardisPlayerView(int entityID, CompoundTag nbt) {
        this.entityID = entityID;
        this.nbt = nbt;
    }

    public S2CSyncTardisPlayerView(FriendlyByteBuf buf) {
        this.entityID = buf.readInt();
        this.nbt = buf.readNbt();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.TARDIS_PLAYER_INFO;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.entityID);
        buf.writeNbt(this.nbt);
    }

    @Override
    public void handle(MessageContext context) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        Entity entity = level.getEntity(this.entityID);
        if (entity != null)
            TardisPlayerInfo.get((Player) entity).ifPresent((c) -> c.loadData(this.nbt));
    }
}