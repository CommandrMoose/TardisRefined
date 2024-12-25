package whocraft.tardis_refined.client.renderer.blockentity.device;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import org.joml.Vector3f;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.blockentity.device.AstralManipulatorBlockEntity;
import whocraft.tardis_refined.common.items.ScrewdriverItem;

import java.awt.*;

public class AstralManipulatorRenderer implements BlockEntityRenderer<AstralManipulatorBlockEntity>, BlockEntityRendererProvider<AstralManipulatorBlockEntity> {

    public AstralManipulatorRenderer(Context context) {
    }

    @Override
    public BlockEntityRenderer<AstralManipulatorBlockEntity> create(Context context) {
        return new AstralManipulatorRenderer(context);
    }

    @Override
    public void render(AstralManipulatorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        var pointA = blockEntity.getPointABlockPos();
        if (pointA != null && blockEntity.shouldDisplay()) {

            var pointB = blockEntity.getPointBBlockPos();

            float width = 1;
            float height = 1;
            float length = 1;

            var centerOfBoth = new Vector3f(0, 0, 0);
            if (pointB != null) {

                float xDiff = Math.abs(pointA.getX() - pointB.getX());
                float yDiff = Math.abs(pointA.getY() - pointB.getY());
                float zDiff = Math.abs(pointA.getZ() - pointB.getZ());

                var smallestPointX = Math.min(pointA.getX(), pointB.getX());
                var smallestPointY = Math.min(pointA.getY(), pointB.getY());
                var smallestPointZ = Math.min(pointA.getZ(), pointB.getZ());

                var xCenter = smallestPointX + (xDiff * 0.5f);
                var yCenter = smallestPointY + (yDiff * 0.5f);
                var zCenter = smallestPointZ + (zDiff * 0.5f);

                centerOfBoth = new Vector3f(xCenter, yCenter, zCenter);
                length = xDiff;
                height = yDiff;
                width = zDiff;

            }


            var centerPos = pointB != null ? centerOfBoth : new Vector3f(pointA.getX(), pointA.getY(), pointA.getZ());

            var posAOffsetX = blockEntity.getBlockPos().getX() - centerPos.x - .5f;

            var posAOffsetY = blockEntity.getBlockPos().getY() - centerPos.y - .5f;
            var posAOffsetZ = blockEntity.getBlockPos().getZ() - centerPos.z - .5f;

            float sine = (float) (Math.sin(blockEntity.getLevel().getGameTime() * 10f * Math.PI / 8f) * (0.25f / 2f) + (0.25f / 2f)) * 0.25f;
            if (sine < 0.001) {
                sine = 0.001f;
            }

            poseStack.pushPose();
            poseStack.translate(-posAOffsetX, -posAOffsetY, -posAOffsetZ);
            VertexConsumer vertexBuilder = bufferSource.getBuffer(RenderType.lightning());

            if (Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ScrewdriverItem screwdriverItem) {
                Color color = new Color(screwdriverItem.getColor(Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.MAINHAND)));
                RenderHelper.drawGlowingBox(poseStack, vertexBuilder, length + 1.25f, height + 1.25f, width + 1.25f, (float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, 0.2F, 0);
            } else {
                RenderHelper.drawGlowingBox(poseStack, vertexBuilder, length + 1.25f, height + 1.25f, width + 1.25f, 0.635f, 0.392f, 0.878f, 0.2F, 0);
            }

            poseStack.popPose();

        }


    }


}
