package whocraft.tardis_refined.forge;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientLogic;
import whocraft.tardis_refined.client.overlays.ExteriorViewOverlay;
import whocraft.tardis_refined.client.overlays.GravityOverlay;
import whocraft.tardis_refined.client.overlays.VortexOverlay;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

@Mod.EventBusSubscriber(modid = TardisRefined.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeBus {

    @SubscribeEvent
    public static void tickTARDIS(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

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
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post guiOverlayEvent) {
        GravityOverlay.renderOverlay(guiOverlayEvent.getGuiGraphics());
        ExteriorViewOverlay.renderOverlay(guiOverlayEvent.getGuiGraphics());
        VortexOverlay.renderOverlay(guiOverlayEvent.getGuiGraphics());
    }
}
