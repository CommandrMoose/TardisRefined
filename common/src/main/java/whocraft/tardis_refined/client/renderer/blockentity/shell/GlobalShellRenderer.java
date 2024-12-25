package whocraft.tardis_refined.client.renderer.blockentity.shell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.client.TRShaders;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.block.shell.RootedShellBlock;
import whocraft.tardis_refined.common.block.shell.ShellBaseBlock;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.patterns.ShellPattern;

public class GlobalShellRenderer implements BlockEntityRenderer<GlobalShellBlockEntity>, BlockEntityRendererProvider<GlobalShellBlockEntity> {


    public GlobalShellRenderer(Context context) {
    }

    @Override
    public void render(GlobalShellBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (blockEntity.getTardisId() == null) return;

        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));

        BlockState blockstate = blockEntity.getBlockState();
        float rotation = blockstate.getValue(RootedShellBlock.FACING).toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        ResourceLocation theme = blockEntity.theme();
        boolean isOpen = blockstate.getValue(GlobalShellBlock.OPEN);

        float sine = 0;
        if (blockstate.getValue(ShellBaseBlock.REGEN)) {
            sine = (float) ((Math.sin(0.1 * (blockEntity.getLevel().dayTime())) * 1));
            if (sine < 0) {
                sine = 0;
            }
        }

        ShellPattern pattern = blockEntity.pattern();

        var currentModel = ShellModelCollection.getInstance().getShellEntry(theme).getShellModel(pattern);

        ClientLevel level = Minecraft.getInstance().level;
        BlockPos blockPos = blockEntity.getBlockPos();
        Biome biome = level.getBiome(blockPos).value();
        Biome.Precipitation precipitation = biome.getPrecipitationAt(blockPos);
        boolean renderSnow = biome.hasPrecipitation() && precipitation == Biome.Precipitation.SNOW;

        currentModel.renderShell(blockEntity, isOpen, true, poseStack, bufferSource.getBuffer(TRShaders.translucentWithSnow(currentModel.getShellTexture(pattern, false), renderSnow)), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        /*Emmissive*/
        Boolean isRegenerating = blockstate.getValue(ShellBaseBlock.REGEN);
        if (pattern.shellTexture().emissive()) {
            RenderType glowingRenderType = TRShaders.glow(currentModel.getShellTexture(pattern, true), 1F);
            VertexConsumer vertexConsumer = bufferSource.getBuffer(glowingRenderType);
            currentModel.renderShell(blockEntity, isOpen, false, poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, (isRegenerating) ? sine : 1f);
        } else {
            if (isRegenerating) {
                RenderType glowingRenderTypeRegen = TRShaders.glow(currentModel.getShellTexture(pattern, false), 1F);
                VertexConsumer vertexConsumerRegen = bufferSource.getBuffer(glowingRenderTypeRegen);
                currentModel.renderShell(blockEntity, isOpen, false, poseStack, vertexConsumerRegen, 15728640, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, sine);
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
