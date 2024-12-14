package whocraft.tardis_refined.common.network.messages;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.*;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRSoundRegistry;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record C2SChangeDesktop(ResourceKey<Level> resourceKey, DesktopTheme desktopTheme) implements CustomPacketPayload, NetworkManager.Handler<C2SChangeDesktop> {

    public static final CustomPacketPayload.Type<C2SChangeDesktop> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "change_vortex"));

    public static final StreamCodec<FriendlyByteBuf, C2SChangeDesktop> STREAM_CODEC = StreamCodec.of(
            (buf, ref) -> {
                buf.writeResourceKey(ref.resourceKey());
                buf.writeResourceLocation(ref.desktopTheme.getIdentifier());
            },
            buf -> new C2SChangeDesktop(
                    buf.readResourceKey(Registries.DIMENSION),
                    TardisDesktops.getDesktopById(buf.readResourceLocation())
            )
    );



    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void receive(C2SChangeDesktop value, NetworkManager.Context context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.ifPresent(x -> {
            TardisLevelOperator.get(x).ifPresent(operator -> {
                TardisPilotingManager pilotManager = operator.getPilotingManager();
                TardisInteriorManager interiorManager = operator.getInteriorManager();

                boolean inFlight = pilotManager.isInFlight();
                boolean hasFuel = interiorManager.hasEnoughFuel();

                if (!inFlight && hasFuel) {
                    interiorManager.prepareDesktop(desktopTheme);
                    pilotManager.removeFuel(interiorManager.getRequiredFuel());
                } else {
                    if (inFlight)
                        x.playSound(null, context.getPlayer(), TRSoundRegistry.TARDIS_SINGLE_FLY.get(), SoundSource.BLOCKS, 10f, 0.25f);

                    if (!hasFuel) {
                        x.playSound(null, context.getPlayer(), TRSoundRegistry.SCREWDRIVER_CONNECT.get(), SoundSource.BLOCKS, 10f, 0.25f);
                        PlayerUtil.sendMessage(context.getPlayer(), ModMessages.NO_DESKTOP_NO_FUEL, true);
                    }
                }
            });
        });
    }
}
