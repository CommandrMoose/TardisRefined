package whocraft.tardis_refined.common.network.messages.screens;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.Platform;

public class C2SMonitorClosed extends MessageC2S {

    private ResourceKey<Level> level;

    public C2SMonitorClosed(ResourceKey<Level> level) {
        this.level = level;
    }

    public C2SMonitorClosed(FriendlyByteBuf friendlyByteBuf) {
        this.level = friendlyByteBuf.readResourceKey(Registries.DIMENSION);
    }

    @Override
    public @NotNull MessageType getType() {
        return TardisNetwork.MONITOR_CLOSED;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(level);
    }

    @Override
    public void handle(MessageContext context) {
        TardisLevelOperator.get(DimensionUtil.getLevel(level)).ifPresent(operator -> {
            operator.updatingMonitors.remove(context.getPlayer());
        });
    }
}
