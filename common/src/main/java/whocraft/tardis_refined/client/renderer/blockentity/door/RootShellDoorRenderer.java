package whocraft.tardis_refined.client.renderer.blockentity.door;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ModelRegistry;
import whocraft.tardis_refined.client.model.blockentity.shell.internal.door.RootShellDoorModel;
import whocraft.tardis_refined.common.block.shell.RootedShellBlock;
import whocraft.tardis_refined.common.blockentity.desktop.door.RootShellDoorBlockEntity;

public class RootShellDoorRenderer implements BlockEntityRenderer<RootShellDoorBlockEntity>, BlockEntityRendererProvider<RootShellDoorBlockEntity> {

    private static RootShellDoorModel rootShellDoorModel;
    private static ResourceLocation rootShellDoorTexture = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/root/root_plant/internal_door.png");

    public RootShellDoorRenderer(BlockEntityRendererProvider.Context context) {
        rootShellDoorModel = new RootShellDoorModel(context.bakeLayer((ModelRegistry.ROOT_SHELL_DOOR)));
    }

    @Override
    public void render(RootShellDoorBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.475F, 0.5F);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F));

        BlockState state = blockEntity.getBlockState();
        float rotation = ((Direction)state.getValue(RootedShellBlock.FACING)).toYRot();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        rootShellDoorModel.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(rootShellDoorTexture)),
                i, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(RootShellDoorBlockEntity blockEntity) {
        return true;
    }

    @Override
    public BlockEntityRenderer<RootShellDoorBlockEntity> create(Context context) {
        return new RootShellDoorRenderer(context);
    }
}
