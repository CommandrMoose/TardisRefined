package whocraft.tardis_refined.mixin.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.jellysquid.mods.sodium.client.render.immediate.model.EntityRenderer;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import whocraft.tardis_refined.client.model.pallidium.ExtendedCubeListBuilder;



@Mixin(value = EntityRenderer.class, remap = false)
public class SodiumFixMixin {

    @SuppressWarnings("ConstantValue")
    @Inject(method = "render", at = @At("HEAD"), remap = false, cancellable = true)
    private static void render(PoseStack poseStack, VertexBufferWriter writer, ModelPart part, int packedLight, int packedOverlay, int color, CallbackInfo ci) {
        boolean found = false;
        for (ModelPart.Cube cube : part.cubes) {
            if (cube instanceof ExtendedCubeListBuilder.PerFaceUVCube) {
                found = true;
                break;
            }
        }

        if (found && writer instanceof VertexConsumer vertexConsumer) {
            ci.cancel();

            if (part.visible) {
                float red = ColorABGR.unpackRed(color) / 255F;
                float green = ColorABGR.unpackGreen(color) / 255F;
                float blue = ColorABGR.unpackBlue(color) / 255F;
                float alpha = ColorABGR.unpackAlpha(color) / 255F;

                if (!part.cubes.isEmpty() || !part.children.isEmpty()) {
                    poseStack.pushPose();
                    part.translateAndRotate(poseStack);
                    if (!part.skipDraw && ((Object) part) instanceof ModelPartInvoker invoker) {
                        invoker.invokeCompile(poseStack.last(), vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                    }

                    for (ModelPart modelPart : part.children.values()) {
                        modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                    }

                    poseStack.popPose();
                }
            }
        }
    }

}