package whocraft.tardis_refined.client.model.blockentity.life;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.frame.tardis.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.block.life.ArsEggBlock;
import whocraft.tardis_refined.common.blockentity.life.ArsEggBlockEntity;

public class ArsEggModel extends HierarchicalModel {

    public static final AnimationDefinition CRASHING = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/living/ars_egg/crashing.json"));
    public static final AnimationDefinition SWINGING = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/living/ars_egg/swinging.json"));

    private final ModelPart Lamp;
    private final ModelPart root;
    private final ModelPart clamp;


    public ArsEggModel(ModelPart root) {
        this.Lamp = root.getChild("Lamp");
        this.clamp = Lamp.getChild("clamp");
        this.root = root;
    }


    public void renderToBuffer(ArsEggBlockEntity arsEggBlockEntity, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        BlockState blockState = arsEggBlockEntity.getBlockState();

        if (blockState.hasProperty(ArsEggBlock.ALIVE)) {
            clamp.visible = blockState.getValue(ArsEggBlock.ALIVE);
        }

        Lamp.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Lamp.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    public void doAnimation(AnimationState liveliness, AnimationDefinition animationDefinition, int animationCounter) {
        this.animate(liveliness, animationDefinition, animationCounter);
    }
}