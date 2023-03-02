package whocraft.tardis_refined.client.renderer.blockentity.shell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.block.shell.RootedShellBlock;
import whocraft.tardis_refined.common.block.shell.ShellBaseBlock;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.patterns.ShellPattern;

public class GlobalShellRenderer implements BlockEntityRenderer<GlobalShellBlockEntity>, BlockEntityRendererProvider<GlobalShellBlockEntity> {


    public GlobalShellRenderer(Context context) {
    }

    @Override
    public void render(GlobalShellBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if(blockEntity.TARDIS_ID == null) return;

        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F));

        BlockState blockstate = blockEntity.getBlockState();
        float rotation = blockstate.getValue(RootedShellBlock.FACING).toYRot();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        ShellTheme theme = blockstate.getValue(GlobalShellBlock.SHELL);
        boolean isOpen = blockstate.getValue(GlobalShellBlock.OPEN);

        float sine = 0;
        if (blockstate.getValue(ShellBaseBlock.REGEN)) {
            sine = (float) ((Math.sin(0.1 * (blockEntity.getLevel().dayTime())) * 1));
            if (sine < 0) {
                sine = 0;
            }
        }

        var currentModel = ShellModelCollection.getInstance().getShellModel(theme);
        TardisClientData reactions = TardisClientData.getInstance(ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(TardisRefined.MODID, blockEntity.TARDIS_ID.toString())));
        ShellPattern pattern = reactions.shellPattern();

        currentModel.renderShell(blockEntity, isOpen, true, poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(currentModel.texture(pattern, false))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);


        /*Emmissive*/
        Boolean isRegenerating = blockstate.getValue(ShellBaseBlock.REGEN);
        if (pattern.emissive()) {
            currentModel.renderShell(blockEntity, isOpen, false, poseStack, bufferSource.getBuffer(RenderType.entityTranslucentEmissive(currentModel.texture(pattern, true))), 15728640, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, (isRegenerating) ? sine : 1f);
        } else {
            if (isRegenerating) {
                currentModel.renderShell(blockEntity, isOpen, false, poseStack, bufferSource.getBuffer(RenderType.entityTranslucentEmissive(currentModel.texture(pattern, false))), 15728640, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, sine);
            }
        }

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(GlobalShellBlockEntity blockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(GlobalShellBlockEntity blockEntity, Vec3 vec3) {
        return true;
    }
    @Override
    public BlockEntityRenderer<GlobalShellBlockEntity> create(Context context) {
        return new GlobalShellRenderer(context);
    }


}
