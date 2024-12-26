package whocraft.tardis_refined.compat.portals;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import qouteall.imm_ptl.core.compat.IPPortingLibCompat;
import qouteall.imm_ptl.core.render.context_management.PortalRendering;

public class ImmersivePortalsClient {

    @Environment(EnvType.CLIENT)
    public static void doClientRenderers() {
        if (ImmersivePortals.BOTI_PORTAL == null) return;
        EntityRendererRegistry.register(ImmersivePortals.BOTI_PORTAL.get(), BotiPortalRenderer::new);
    }

    @Environment(EnvType.CLIENT)
    public static boolean isStencilEnabled(RenderTarget renderTarget){
        return IPPortingLibCompat.getIsStencilEnabled(renderTarget);
    }

    @Environment(EnvType.CLIENT)
    public static void setStencilEnabled(RenderTarget renderTarget, boolean cond){
        IPPortingLibCompat.setIsStencilEnabled(renderTarget, cond);
    }

    @Environment(EnvType.CLIENT)
    public static boolean shouldStopRenderingInPortal() {
        if (PortalRendering.isRendering()) return true;
        return false;
    }
}
