package whocraft.tardis_refined.client.renderer.blockentity.life;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Random;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.blockentity.life.EyeBlockEntity;

public class EyeRenderer implements BlockEntityRenderer<EyeBlockEntity>, BlockEntityRendererProvider<EyeBlockEntity> {


    //Taken from vanilla
    private static final float HALF_SQRT_3 = (float) (Math.sqrt(3.0D) / 2.0D);
    private final Context context;

    public EyeRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    private static void vertex01(VertexConsumer iVertexBuilder, Matrix4f matrix4f, int p_229061_2_) {
        iVertexBuilder.addVertex(matrix4f, 0.0F, 0.0F, 0.0F).setColor(255, 255, 255, p_229061_2_).endVertex();
        iVertexBuilder.addVertex(matrix4f, 0.0F, 0.0F, 0.0F).setColor(255, 255, 255, p_229061_2_).endVertex();
    }

    private static void vertex2(VertexConsumer iVertexBuilder, Matrix4f matrix4f, float p_229060_2_, float p_229060_3_) {
        iVertexBuilder.addVertex(matrix4f, -HALF_SQRT_3 * p_229060_3_, p_229060_2_, -0.5F * p_229060_3_).setColor(255, 93, 0, 0).endVertex();
    }

    private static void vertex3(VertexConsumer iVertexBuilder, Matrix4f matrix4f, float p_229062_2_, float p_229062_3_) {
        iVertexBuilder.addVertex(matrix4f, HALF_SQRT_3 * p_229062_3_, p_229062_2_, -0.5F * p_229062_3_).setColor(255, 93, 0, 0).endVertex();
    }

    private static void vertex4(VertexConsumer iVertexBuilder, Matrix4f matrix4f, float p_229063_2_, float p_229063_3_) {
        iVertexBuilder.addVertex(matrix4f, 0.0F, p_229063_2_, 1.0F * p_229063_3_).setColor(255, 93, 0, 0);
    }

    @Override
    public boolean shouldRender(EyeBlockEntity blockEntity, Vec3 vec3) {
        return BlockEntityRenderer.super.shouldRender(blockEntity, vec3);
    }

    @Override
    public void render(EyeBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int packed) {
        Random random = new Random(432L);

        float f5 = (150 + Minecraft.getInstance().getFrameTimeNs()) / 200.0F;


        float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
        VertexConsumer vertexBuilder = multiBufferSource.getBuffer(RenderType.lightning());
        poseStack.pushPose();

        float sine = 0;
        sine = (float) ((Math.sin(0.1 * (blockEntity.getLevel().dayTime())) * 0.25f)) + 1f;
        if (sine < 0) {
            sine = 0;
        }


        poseStack.translate(0.5D, 0.5D, 0.5D);

        poseStack.mulPose(Axis.YP.rotationDegrees(Minecraft.getInstance().player.tickCount * 0.5f));


        poseStack.scale(0.15F * sine, 0.15F * sine, 0.15F * sine);

        for (int i = 0; (float) i < (f5 + f5 * f5) / 2.0F * 60.0F; ++i) {
            poseStack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(random.nextFloat() * 360.0F + f5 * 90.0F));
            float randomFloat = random.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
            float randomFloat2 = random.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
            Matrix4f matrix4f = poseStack.last().pose();
            int j = (int) (255.0F * (1.0F - f7));
            vertex01(vertexBuilder, matrix4f, j);
            vertex2(vertexBuilder, matrix4f, randomFloat, randomFloat2);
            vertex3(vertexBuilder, matrix4f, randomFloat, randomFloat2);
            vertex01(vertexBuilder, matrix4f, j);
            vertex3(vertexBuilder, matrix4f, randomFloat, randomFloat2);
            vertex4(vertexBuilder, matrix4f, randomFloat, randomFloat2);
            vertex01(vertexBuilder, matrix4f, j);
            vertex4(vertexBuilder, matrix4f, randomFloat, randomFloat2);
            vertex2(vertexBuilder, matrix4f, randomFloat, randomFloat2);
        }


        int sideWidth = 7;
        RenderHelper.drawGlowingBox(poseStack, vertexBuilder, sideWidth, sideWidth, sideWidth, 1, 0.11f, 0, 1, 0);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(EyeBlockEntity blockEntity) {
        return true;
    }

    @Override
    public BlockEntityRenderer<EyeBlockEntity> create(Context context) {
        return new EyeRenderer(context);
    }
}
