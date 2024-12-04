package whocraft.tardis_refined.common.network.messages;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.api.event.ShellChangeSources;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.util.Optional;

public class ChangeVortexMessage extends MessageC2S {

    private final ResourceKey<Level> resourceKey;
    private final ResourceLocation vortex;

    public ChangeVortexMessage(ResourceKey<Level> tardisLevel, ResourceLocation theme) {
        this.resourceKey = tardisLevel;
        this.vortex = theme;
    }

    public ChangeVortexMessage(FriendlyByteBuf buffer) {
        resourceKey = buffer.readResourceKey(Registries.DIMENSION);
        this.vortex = buffer.readResourceLocation();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.CHANGE_VORTEX;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.resourceKey);
        buf.writeResourceLocation(this.vortex);
    }

    @Override
    public void handle(MessageContext context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.flatMap(TardisLevelOperator::get).ifPresent(y -> y.getAestheticHandler().setVortex(this.vortex));

    }


}
