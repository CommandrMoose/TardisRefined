package whocraft.tardis_refined.common.network.messages.sync;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageS2C;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.network.handler.HandleSyncDimensions;

public class S2CSyncLevelList extends MessageS2C {

    public ResourceKey<Level> level;
    public boolean add;

    public S2CSyncLevelList(ResourceKey<Level> level, boolean add) {
        this.level = level;
        this.add = add;
    }

    public S2CSyncLevelList(FriendlyByteBuf buf) {
        this.level = buf.readResourceKey(Registries.DIMENSION);
        this.add = buf.readBoolean();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.SYNC_LEVELS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.level);
        buf.writeBoolean(this.add);
    }

    @Override
    public void handle(MessageContext context) {
        HandleSyncDimensions.handleDimSyncPacket(this.level, this.add);
    }
}
