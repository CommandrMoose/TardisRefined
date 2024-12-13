package whocraft.tardis_refined.client.renderer.blockentity.device;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;
import org.joml.Random;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ModelRegistry;
import whocraft.tardis_refined.client.model.blockentity.device.ArtronPillarBlockModel;
import whocraft.tardis_refined.client.model.blockentity.door.interior.BulkHeadDoorModel;
import whocraft.tardis_refined.client.model.blockentity.life.ArsEggModel;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.block.device.ArtronPillarBlock;
import whocraft.tardis_refined.common.block.door.GlobalDoorBlock;
import whocraft.tardis_refined.common.blockentity.device.ArtronPillarBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;

public class ArtronPillarRenderer implements BlockEntityRenderer<ArtronPillarBlockEntity>, BlockEntityRendererProvider<ArtronPillarBlockEntity> {

    private static final float HALF_SQRT_3 = (float) (Math.sqrt(3.0D) / 2.0D);
    private final ArtronPillarBlockModel artronPillarBlockModel;
    private final ResourceLocation POWER_ON = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/blockentity/artron_pillar.png");
    private final ResourceLocation POWER_OFF = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/blockentity/artron_pillar_off.png");

    public ArtronPillarRenderer(Context context) {
        artronPillarBlockModel = new ArtronPillarBlockModel(context.bakeLayer((ModelRegistry.ARTRON_PILLAR)));
    }

    private static void vertex01(VertexConsumer iVertexBuilder, Matrix4f matrix4f, int p_229061_2_) {
        iVertexBuilder.addVertex(matrix4f, 0.0F, 0.0F, 0.0F).setColor(255, 255, 255, p_229061_2_);
        iVertexBuilder.addVertex(matrix4f, 0.0F, 0.0F, 0.0F).setColor(255, 255, 255, p_229061_2_);
    }

    private static void vertex2(VertexConsumer iVertexBuilder, Matrix4f matrix4f, float p_229060_2_, float p_229060_3_) {
        iVertexBuilder.addVertex(matrix4f, -HALF_SQRT_3 * p_229060_3_, p_229060_2_, -0.5F * p_229060_3_).setColor(16, 206, 0, 0);
    }

    private static void vertex3(VertexConsumer iVertexBuilder, Matrix4f matrix4f, float p_229062_2_, float p_229062_3_) {
        iVertexBuilder.addVertex(matrix4f, HALF_SQRT_3 * p_229062_3_, p_229062_2_, -0.5F * p_229062_3_).setColor(16, 206, 0, 0);
    }

    private static void vertex4(VertexConsumer iVertexBuilder, Matrix4f matrix4f, float p_229063_2_, float p_229063_3_) {
        iVertexBuilder.addVertex(matrix4f, 0.0F, p_229063_2_, 1.0F * p_229063_3_).setColor(16, 206, 0, 0);
    }

    @Override
    public void render(ArtronPillarBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.475F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));

        ResourceLocation lampTexture = blockEntity.getBlockState().getValue(ArtronPillarBlock.ACTIVE) ? POWER_ON : POWER_OFF;

        artronPillarBlockModel.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(lampTexture)), i, OverlayTexture.NO_OVERLAY, RenderHelper.rgbaToInt(1f, 1f, 1f, 1f));

        if (blockEntity.getBlockState().getValue(ArtronPillarBlock.ACTIVE)) {

            VertexConsumer vertexBuilder = multiBufferSource.getBuffer(RenderType.lightning());

            Random random = new Random(432L);

            float f5 = (150 + Minecraft.getInstance().getFrameTimeNs()) / 200.0F;

            float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);

            float sine = 0;
            sine = (float) ((Math.sin(0.1 * (blockEntity.getLevel().dayTime()) + blockEntity.getBlockPos().asLong()) * 0.25f)) + 1f;
            if (sine < 0) {
                sine = 0;
            }

            poseStack.translate(0, -2.5D, 0);

            boolean shouldRotateOtherWay = blockEntity.getBlockPos().asLong() % 3 == 0;

            poseStack.mulPose(Axis.YP.rotationDegrees(Minecraft.getInstance().player.tickCount * (shouldRotateOtherWay ? -0.5f : 0.5f)));

            poseStack.scale(0.075F * sine, 0.075F * sine, 0.075F * sine);

            for (int x = 0; (float) x < (f5 + f5 * f5) / 2.0F * 60.0F; ++x) {
                poseStack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
                poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F));
                poseStack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
                poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F + f5 * 90.0F));
                float randomFloat = random.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
                float randomFloat2 = random.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
                Matrix4f matrix4f = poseStack.last().pose();
                int k = (int) (255.0F * (1.0F - f7));
                vertex01(vertexBuilder, matrix4f, k);
                vertex2(vertexBuilder, matrix4f, randomFloat, randomFloat2);
                vertex3(vertexBuilder, matrix4f, randomFloat, randomFloat2);
                vertex01(vertexBuilder, matrix4f, k);
                vertex3(vertexBuilder, matrix4f, randomFloat, randomFloat2);
                vertex4(vertexBuilder, matrix4f, randomFloat, randomFloat2);
                vertex01(vertexBuilder, matrix4f, k);
                vertex4(vertexBuilder, matrix4f, randomFloat, randomFloat2);
                vertex2(vertexBuilder, matrix4f, randomFloat, randomFloat2);
            }
        }


        poseStack.popPose();
    }


    @Override
    public boolean shouldRenderOffScreen(ArtronPillarBlockEntity blockEntity) {
        return true;
    }

    @Override
    public BlockEntityRenderer<ArtronPillarBlockEntity> create(Context context) {
        return new ArtronPillarRenderer(context);
    }
}
