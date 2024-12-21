package whocraft.tardis_refined.common.network.messages.hums;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.soundscape.hum.HumEntry;
import whocraft.tardis_refined.common.soundscape.hum.TardisHums;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;

import java.util.Optional;

public class C2SChangeHum extends MessageC2S {

    private final ResourceKey<Level> resourceKey;
    private final HumEntry humEntry;

    public C2SChangeHum(ResourceKey<Level> tardisLevel, HumEntry humEntry) {
        this.resourceKey = tardisLevel;
        this.humEntry = humEntry;
    }

    public C2SChangeHum(FriendlyByteBuf buffer) {
        this.resourceKey = buffer.readResourceKey(Registries.DIMENSION);
        this.humEntry = TardisHums.getHumById(buffer.readResourceLocation());
    }

    @NotNull
    @Override
    public MessageType getType() {
        return TardisNetwork.CHANGE_HUM;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.resourceKey);
        buf.writeResourceLocation(this.humEntry.getIdentifier());
    }

    @Override
    public void handle(MessageContext context) {
        Optional<ServerLevel> level = Optional.ofNullable(context.getPlayer().getServer().levels.get(resourceKey));
        level.ifPresent(x -> {
            TardisLevelOperator.get(x).ifPresent(operator -> {
                TardisInteriorManager tardisInteriorManager = operator.getInteriorManager();
                TardisClientData tardisClientData = operator.tardisClientData();
                tardisInteriorManager.setHumEntry(humEntry);
                tardisClientData.setHumEntry(humEntry);
                tardisClientData.sync();
            });
        });

    }


}
