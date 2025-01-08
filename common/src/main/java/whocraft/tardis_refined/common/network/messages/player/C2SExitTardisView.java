package whocraft.tardis_refined.common.network.messages.player;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.MessageC2S;
import whocraft.tardis_refined.common.network.MessageContext;
import whocraft.tardis_refined.common.network.MessageType;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.common.util.Platform;

public class C2SExitTardisView extends MessageC2S {

    public C2SExitTardisView() {
    }

    public C2SExitTardisView(FriendlyByteBuf buf) {
    }

    @Override
    public @NotNull MessageType getType() {
        return TardisNetwork.TARDIS_EXIT;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        ServerPlayer player = context.getPlayer();

        TardisPlayerInfo.get(player).ifPresent(tardisInfo -> {
            if (tardisInfo.isViewingTardis()) {
                ResourceKey<Level> key = ResourceKey.create(Registries.DIMENSION,
                        new ResourceLocation(TardisRefined.MODID, tardisInfo.getViewedTardis().toString()));
                ServerLevel tardisLevel = DimensionUtil.getLevel(key);
                if (tardisLevel != null) {
                    TardisLevelOperator.get(tardisLevel).ifPresent(tardisLevelOperator -> {
                        tardisInfo.endShellView(player);
                    });
                }
            }
        });

    }
}
