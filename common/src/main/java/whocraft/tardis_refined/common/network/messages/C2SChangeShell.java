package whocraft.tardis_refined.common.network.messages;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.api.event.ShellChangeSources;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.util.Optional;

public record C2SChangeShell(ResourceKey<Level> resourceKey, ResourceLocation shellTheme, ShellPattern pattern) implements CustomPacketPayload, NetworkManager.Handler<C2SChangeShell> {

    public static final CustomPacketPayload.Type<C2SChangeShell> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "change_shell"));

    public static final StreamCodec<FriendlyByteBuf, C2SChangeShell> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeResourceKey(ref.resourceKey());
                buf.writeResourceLocation(ref.shellTheme());
                buf.writeResourceLocation(ref.pattern().id());
            },
            buf -> new C2SChangeShell(
                    buf.readResourceKey(Registries.DIMENSION),
                    buf.readResourceLocation(),
                    ShellPatterns.getPatternOrDefault(buf.readResourceLocation(), ShellPatterns.DEFAULT.id())
            )
    );


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SChangeShell value, NetworkManager.Context context) {
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
