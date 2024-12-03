package whocraft.tardis_refined.common.network.messages;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.Optional;

public class ChangeDesktopMessage extends MessageC2S {

    private final ResourceKey<Level> resourceKey;
    private final DesktopTheme desktopTheme;

    public ChangeDesktopMessage(ResourceKey<Level> tardisLevel, DesktopTheme theme) {
        this.resourceKey = tardisLevel;
        this.desktopTheme = theme;
    }

    public ChangeDesktopMessage(FriendlyByteBuf buffer) {
        this.resourceKey = buffer.readResourceKey(Registries.DIMENSION);
        this.desktopTheme = TardisDesktops.getDesktopById(buffer.readResourceLocation());
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.CHANGE_DESKTOP;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.resourceKey);
        buf.writeResourceLocation(this.desktopTheme.getIdentifier());
    }

    @Override
    public void handle(MessageContext context) {
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
                        x.playSound(null, context.getPlayer(), TRSoundRegistry.SCREWDRIVER_CONNECT.get(), SoundSource.BLOCKS, 10f, 0.25f); // Sound should be changed
                        PlayerUtil.sendMessage(context.getPlayer(), ModMessages.NO_DESKTOP_NO_FUEL, true);
                    }

                }
            });
        });

    }


}
