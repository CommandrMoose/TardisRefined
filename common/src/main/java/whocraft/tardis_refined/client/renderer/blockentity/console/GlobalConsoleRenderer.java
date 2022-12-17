package whocraft.tardis_refined.client.renderer.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.client.model.blockentity.console.ConsoleModelCollection;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

import java.util.Objects;

public class  GlobalConsoleRenderer implements BlockEntityRenderer<GlobalConsoleBlockEntity>, BlockEntityRendererProvider<GlobalConsoleBlockEntity> {

    private ConsoleModelCollection modelCollection;

    public GlobalConsoleRenderer(BlockEntityRendererProvider.Context context) {
        modelCollection = new ConsoleModelCollection(context);
    }

    @Override
    public void render(GlobalConsoleBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.475F, 0.5F);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        BlockState blockstate = blockEntity.getBlockState();
        ConsoleTheme theme = blockstate.getValue(GlobalConsoleBlock.CONSOLE);

        modelCollection.getConsoleModel(theme).renderConsole(Objects.requireNonNull(blockEntity.getLevel()), poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(modelCollection.getConsoleModel(theme).getTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(GlobalConsoleBlockEntity blockEntity) {
        return true;
    }

    @Override
    public BlockEntityRenderer<GlobalConsoleBlockEntity> create(BlockEntityRendererProvider.Context context) {
        return new GlobalConsoleRenderer(context);
    }
}
