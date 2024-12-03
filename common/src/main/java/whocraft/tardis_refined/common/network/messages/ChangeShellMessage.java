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

public class ChangeShellMessage extends MessageC2S {

    private final ResourceKey<Level> resourceKey;
    private final ResourceLocation shellTheme;
    private final ShellPattern pattern;

    public ChangeShellMessage(ResourceKey<Level> tardisLevel, ResourceLocation theme, ShellPattern pattern) {
        this.resourceKey = tardisLevel;
        this.shellTheme = theme;
        this.pattern = pattern;
    }

    public ChangeShellMessage(FriendlyByteBuf buffer) {
        resourceKey = buffer.readResourceKey(Registries.DIMENSION);
        this.shellTheme = buffer.readResourceLocation();
        this.pattern = ShellPatterns.getPatternOrDefault(shellTheme, buffer.readResourceLocation());
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.CHANGE_SHELL;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.resourceKey);
        buf.writeResourceLocation(this.shellTheme);
        buf.writeResourceLocation(pattern.id());
    }

    @Override
    public void handle(MessageContext context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.flatMap(TardisLevelOperator::get).ifPresent(y -> {
            if (TRUpgrades.CHAMELEON_CIRCUIT_SYSTEM.get().isUnlocked(y.getUpgradeHandler()) && y.getExteriorManager().hasEnoughFuelForShellChange()) {
                y.setShellTheme(this.shellTheme, pattern.id(), ShellChangeSources.GENERIC_UPDATE);
                y.getPilotingManager().removeFuel(y.getExteriorManager().getFuelForShellChange());
            } else {
                PlayerUtil.sendMessage(context.getPlayer(), ModMessages.HARDWARE_OFFLINE, true);
            }
        });

    }


}
