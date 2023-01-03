package whocraft.tardis_refined.client.renderer.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.console.ConsoleModelCollection;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

import java.util.Objects;

public class GlobalConsoleRenderer implements BlockEntityRenderer<GlobalConsoleBlockEntity>, BlockEntityRendererProvider<GlobalConsoleBlockEntity> {


    public GlobalConsoleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(GlobalConsoleBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.475F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
        BlockState blockstate = blockEntity.getBlockState();
        ConsoleTheme theme = blockstate.getValue(GlobalConsoleBlock.CONSOLE);

        ConsoleModelCollection.getInstance().getConsoleModel(theme).renderConsole(Objects.requireNonNull(blockEntity.getLevel()), poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(ConsoleModelCollection.getInstance().getConsoleModel(theme).getTexture(blockEntity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);


        poseStack.popPose();

        if (theme == ConsoleTheme.CRYSTAL) {
            if (blockEntity.getLevel().random.nextInt(20) != 0) {
                poseStack.pushPose();
                TardisClientData reactions = TardisClientData.getInstance(blockEntity.getLevel().dimension());
                var model = ShellModelCollection.getInstance().getShellModel(reactions.getShellTheme());
                model.setDoorPosition(false);

                poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
                poseStack.scale(0.1f,0.1f,0.1f);
                poseStack.translate(3.075f, -16.75f, 6.57F);
                poseStack.translate(0,  blockEntity.getLevel().random.nextFloat() * 0.01, 0);
                if (reactions.isFlying()) {
                    poseStack.mulPose(Axis.YP.rotationDegrees(((blockEntity.getLevel().getGameTime() % 360)) * 25f));
                } else {
                    poseStack.mulPose(Axis.YP.rotationDegrees(270 % 360));
                }

                model.renderToBuffer(poseStack,bufferSource.getBuffer(RenderType.entityTranslucent(reactions.getShellTheme().getExternalShellTexture())), packedLight, OverlayTexture.NO_OVERLAY, 1f, 0.64f, 0f, 0.25f);
                poseStack.popPose();
            }
        }
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
