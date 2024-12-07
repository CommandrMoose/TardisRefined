package whocraft.tardis_refined.compat.portals;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import qouteall.imm_ptl.core.CHelper;
import qouteall.imm_ptl.core.ClientWorldLoader;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.chunk_loading.ChunkLoader;
import qouteall.imm_ptl.core.chunk_loading.DimensionalChunkPos;
import qouteall.imm_ptl.core.render.GuiPortalRendering;
import qouteall.imm_ptl.core.render.MyRenderHelper;
import qouteall.imm_ptl.core.render.context_management.WorldRenderInfo;
import qouteall.q_misc_util.my_util.DQuaternion;
import whocraft.tardis_refined.client.renderer.vortex.RenderTargetHelper;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class IPStencil {

    @Environment(EnvType.CLIENT)
    public static final RenderTargetHelper RENDER_TARGET_HELPER = new RenderTargetHelper();

    private static final WeakHashMap<ServerPlayer, ChunkLoader> LOADED_CHUNKS = new WeakHashMap<>();

    private static Vec3 viewingPosition = new Vec3(3,3,3);

    // SERVER ONLY
    public static void loadServerChunks(ServerPlayer serverPlayer, TardisNavLocation tardisNavLocation){
        ChunkLoader chunkLoader = new ChunkLoader(
                new DimensionalChunkPos(
                        tardisNavLocation.getDimensionKey(), new ChunkPos(tardisNavLocation.getPosition())
                ),
                8
        );

        LOADED_CHUNKS.put(serverPlayer, chunkLoader);
    }

    // SERVER ONLY
    public static void unloadServerChunks(ServerPlayer serverPlayer){
        ChunkLoader chunkLoader = LOADED_CHUNKS.remove(serverPlayer);
        if (chunkLoader != null) {
            PortalAPI.removeChunkLoaderForPlayer(serverPlayer, chunkLoader);
        }
    }


    public static ArrayList<TardisNavLocation> arrayList = new ArrayList<>();

    public static void render(TardisNavLocation tardisNavLocation){

        if(arrayList.contains(tardisNavLocation)) {
            return;
        } else {
            arrayList.add(tardisNavLocation);
        }

       RENDER_TARGET_HELPER.start();
        double t1 = CHelper.getSmoothCycles(503);
        double t2 = CHelper.getSmoothCycles(197);

        Minecraft minecraft = Minecraft.getInstance();


        // Determine the camera transformation
        Matrix4f cameraTransformation = new Matrix4f();
        cameraTransformation.identity();
        cameraTransformation.mul(
                DQuaternion.rotationByDegrees(
                        new Vec3(1, 1, 1).normalize(),
                        t1 * 360
                ).toMatrix()
        );

        // Determine the camera position
        Vec3 cameraPosition = viewingPosition.add(
                new Vec3(Math.cos(t2 * 2 * Math.PI), 0, Math.sin(t2 * 2 * Math.PI)).scale(30)
        );

        // Create the world render info
        WorldRenderInfo worldRenderInfo = new WorldRenderInfo.Builder()
                .setWorld(ClientWorldLoader.getWorld(tardisNavLocation.getDimensionKey()))
                .setCameraPos(cameraPosition)
                .setCameraTransformation(cameraTransformation)
                .setOverwriteCameraTransformation(true) // do not apply camera transformation to existing player camera transformation
                .setDescription(null)
                .setRenderDistance(minecraft.options.getEffectiveRenderDistance())
                .setDoRenderHand(false)
                .setEnableViewBobbing(false)
                .setDoRenderSky(false)
                .setHasFog(false)
                .build();

        GuiPortalRendering.submitNextFrameRendering(worldRenderInfo, RENDER_TARGET_HELPER.renderTarget);

    }

}
