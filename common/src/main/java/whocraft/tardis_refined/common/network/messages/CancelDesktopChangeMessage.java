package whocraft.tardis_refined.common.network.messages;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;

import java.util.Optional;


public class CancelDesktopChangeMessage extends MessageC2S {

    private final ResourceKey<Level> resourceKey;

    public CancelDesktopChangeMessage(ResourceKey<Level> tardisLevel) {
        this.resourceKey = tardisLevel;
    }

    public CancelDesktopChangeMessage(FriendlyByteBuf friendlyByteBuf) {
        resourceKey = friendlyByteBuf.readResourceKey(Registries.DIMENSION);
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.CANCEL_CHANGE_DESKTOP;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.resourceKey);
    }


    @Override
    public void handle(MessageContext context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.ifPresent(x -> {
            TardisLevelOperator.get(x).ifPresent(y -> {
                y.getInteriorManager().cancelDesktopChange();
            });
        });


    }
}
