package whocraft.tardis_refined.neoforge;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientLogic;
import whocraft.tardis_refined.client.overlays.ExteriorViewOverlay;
import whocraft.tardis_refined.client.overlays.GravityOverlay;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

@EventBusSubscriber(modid = TardisRefined.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientForgeBus {

    @SubscribeEvent
    public static void tickTARDIS(ClientTickEvent event) {
        TardisClientLogic.tickClientData(Minecraft.getInstance());
    }

    @SubscribeEvent
    public static void onPunchBlock(PlayerInteractEvent.LeftClickBlock e) {
        if (e.getEntity().level().isClientSide) return;

        TardisPlayerInfo.get(e.getEntity()).ifPresent(tardisPlayerInfo -> {
            e.setCanceled(tardisPlayerInfo.isViewingTardis());
        });

    }

    @SubscribeEvent
    public static void onRenderOverlay(CustomizeGuiOverlayEvent guiOverlayEvent) {
        GravityOverlay.renderOverlay(guiOverlayEvent.getGuiGraphics());
        ExteriorViewOverlay.renderOverlay(guiOverlayEvent.getGuiGraphics());
    }
}
