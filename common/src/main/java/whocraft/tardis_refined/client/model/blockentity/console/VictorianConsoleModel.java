package whocraft.tardis_refined.client.model.blockentity.console;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.anim.tardis.JsonToAnimationDefinition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.block.console.GlobalConsoleBlock;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class VictorianConsoleModel extends HierarchicalModel implements ConsoleUnit {

/*
    public static final AnimationDefinition IDLE = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/victorian/idle.json"));
    public static final AnimationDefinition FLIGHT = JsonToAnimationDefinition.loadAnimation(Minecraft.getInstance().getResourceManager(), new ResourceLocation(TardisRefined.MODID, "animated/console/victorian/flight.json"));
*/


    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(10f).looping()
            .addAnimation("bone186",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.68f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.72f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.8f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.84f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.92f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.96f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.28f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.32f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.68f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.72f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone188",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(1.36f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.4f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.08f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.44f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.48f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.88f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.92f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.96f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.32f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.36f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone189",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.52f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.52f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone190",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone191",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.6f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone192",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(6.08f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.48f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.52f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone193",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.84f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.88f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone194",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.08f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone195",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.16f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.68f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.84f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone196",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.52f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone197",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.24f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone200",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone198",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone199",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(5.24f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.28f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.6f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone201",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(3.92f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.28f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.32f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone203",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone204",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 360f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone205",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -720f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone206",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3f, KeyframeAnimations.degreeVec(0f, 172.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6f, KeyframeAnimations.degreeVec(0f, -62.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(8.72f, KeyframeAnimations.degreeVec(0f, 107.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone207",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.6f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.44f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.44f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.8f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone208",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.32f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.6f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone209",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.32f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.44f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.48f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone210",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone211",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone212",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone213",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone214",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone215",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone216",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone217",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone218",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone219",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone220",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone221",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone222",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.16f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone223",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.08f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.08f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone224",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(4.08f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.08f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone227",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone228",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2.28f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.32f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone229",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(4.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.68f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone230",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3f, KeyframeAnimations.scaleVec(2.6f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5.96f, KeyframeAnimations.scaleVec(1.5f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.64f, KeyframeAnimations.scaleVec(2.2f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.28f, KeyframeAnimations.scaleVec(1.4f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(0f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone231",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.84f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.88f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.28f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.32f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.24f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.28f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone232",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -720f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone234",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(2.6f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.64f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.08f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.12f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.24f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.68f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.72f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.48f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.52f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.96f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone235",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.24f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.28f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.88f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.92f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.44f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.48f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.48f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.52f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.16f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone236",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone237",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone238",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.28f, KeyframeAnimations.scaleVec(1f, 0.42f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone239",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone240",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone241",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.28f, KeyframeAnimations.scaleVec(1f, 0.42f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone242",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.28f, KeyframeAnimations.scaleVec(1f, 0.42f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
    public static final AnimationDefinition FLIGHT = AnimationDefinition.Builder.withLength(10f).looping()
            .addAnimation("bone186",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone188",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone189",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.48f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.52f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.44f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.48f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone190",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone191",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone192",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.44f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone193",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.84f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.88f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone194",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.08f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone195",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.12f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.16f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.12f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.68f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.84f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone196",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.52f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone197",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.24f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone200",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone198",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone199",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(5.24f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.28f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.6f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone201",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(3.92f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.28f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.32f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone203",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone204",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 1080f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone205",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -720f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone206",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -1080f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone207",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.6f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.44f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.44f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.8f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone208",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.32f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.16f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.48f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.52f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.08f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.52f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.24f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.28f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.8f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.84f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone209",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.32f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.44f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.48f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone210",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone211",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone212",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone213",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone214",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone215",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone216",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone217",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone218",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone219",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone220",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone221",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0.025f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone222",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone223",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone224",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.96f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone227",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.72f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone228",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(2.28f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.32f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.56f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone229",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(4.6f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.68f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone230",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(0f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3f, KeyframeAnimations.scaleVec(2.6f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5.96f, KeyframeAnimations.scaleVec(1.5f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.64f, KeyframeAnimations.scaleVec(2.2f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.28f, KeyframeAnimations.scaleVec(1.4f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(0f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone231",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.44f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.32f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.36f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.32f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.4f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.44f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.32f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.52f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.56f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.64f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.68f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.24f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9.28f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone232",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, -720f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone234",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.48f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.52f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.48f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.52f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.96f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.6f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.64f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.48f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.52f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.24f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.68f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.72f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.24f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.68f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.72f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.48f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.52f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.96f, KeyframeAnimations.scaleVec(1.04f, 1.06f, 1.06f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(9f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone235",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.04f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.8f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.84f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.84f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.88f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.64f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.68f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.88f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3.92f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.44f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.48f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.04f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.08f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.84f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.88f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.48f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(6.52f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.16f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone236",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.36f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5.4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.16f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.2f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.72f, KeyframeAnimations.posVec(0f, 0.05f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8.76f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone237",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone238",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.28f, KeyframeAnimations.scaleVec(1f, 0.42f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone239",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone240",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.4f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone241",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.28f, KeyframeAnimations.scaleVec(1f, 0.42f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone242",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.28f, KeyframeAnimations.scaleVec(1f, 0.42f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.2f, KeyframeAnimations.scaleVec(1f, 1.01f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.scaleVec(1f, 0.6f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("upper_rotor",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.56f, KeyframeAnimations.posVec(0f, 1.505f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.52f, KeyframeAnimations.posVec(0f, 4.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(4f, KeyframeAnimations.posVec(0f, 2.565f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, -0.7f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.52f, KeyframeAnimations.posVec(0f, 1.58f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.52f, KeyframeAnimations.posVec(0f, 4.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.04f, KeyframeAnimations.posVec(0f, 2.54f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("lower_rotor",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.6f, KeyframeAnimations.posVec(0f, -1.68f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.52f, KeyframeAnimations.posVec(0f, -4.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(3.92f, KeyframeAnimations.posVec(0f, -2.69f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.6f, KeyframeAnimations.posVec(0f, -1.68f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.52f, KeyframeAnimations.posVec(0f, -4.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(8.92f, KeyframeAnimations.posVec(0f, -2.69f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone243",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(1f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(5f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.04f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(7.96f, KeyframeAnimations.scaleVec(1.05f, 1.05f, 1.05f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(8f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("bone245",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.6f, KeyframeAnimations.degreeVec(0f, 110f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(5.32f, KeyframeAnimations.degreeVec(0f, 57.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(7.84f, KeyframeAnimations.degreeVec(0f, 107.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.4f, KeyframeAnimations.degreeVec(0f, 2.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("bone244",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -90f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.16f, KeyframeAnimations.degreeVec(0f, -90f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(2.6f, KeyframeAnimations.degreeVec(0f, -10f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(4.4f, KeyframeAnimations.degreeVec(0f, -147.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(6.48f, KeyframeAnimations.degreeVec(0f, 115f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(9.16f, KeyframeAnimations.degreeVec(0f, -37.5f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(10f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();

    private static final ResourceLocation VICTORIAN_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/victorian/victorian_console.png");

    private final ModelPart root;
    private final ModelPart upper_rotor;
    private final ModelPart lower_rotor;
    private final ModelPart controls;
    private final ModelPart base_console;

    private final ModelPart throttle_control;

    public VictorianConsoleModel(ModelPart root) {
        this.root = root;
        this.upper_rotor = root.getChild("upper_rotor");
        this.lower_rotor = root.getChild("lower_rotor");
        this.controls = root.getChild("controls");
        this.base_console = root.getChild("base_console");
        this.throttle_control = this.controls.getChild("south_left").getChild("bone185").getChild("bone187");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition upper_rotor = partdefinition.addOrReplaceChild("upper_rotor", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

        PartDefinition bone133 = upper_rotor.addOrReplaceChild("bone133", CubeListBuilder.create().texOffs(9, 60).addBox(-1.0F, -21.0F, -4.825F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -33.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone134 = bone133.addOrReplaceChild("bone134", CubeListBuilder.create().texOffs(9, 60).addBox(-1.0F, -23.0F, -4.825F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone135 = bone134.addOrReplaceChild("bone135", CubeListBuilder.create().texOffs(9, 60).addBox(-1.0F, -21.0F, -4.825F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone136 = bone135.addOrReplaceChild("bone136", CubeListBuilder.create().texOffs(9, 60).addBox(-1.0F, -23.0F, -4.825F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone137 = bone136.addOrReplaceChild("bone137", CubeListBuilder.create().texOffs(9, 60).addBox(-1.0F, -21.0F, -4.825F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone138 = bone137.addOrReplaceChild("bone138", CubeListBuilder.create().texOffs(9, 60).addBox(-1.0F, -23.0F, -4.825F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone163 = upper_rotor.addOrReplaceChild("bone163", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -40.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone163_r1 = bone163.addOrReplaceChild("bone163_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone164 = bone163.addOrReplaceChild("bone164", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone164_r1 = bone164.addOrReplaceChild("bone164_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone165 = bone164.addOrReplaceChild("bone165", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone165_r1 = bone165.addOrReplaceChild("bone165_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone166 = bone165.addOrReplaceChild("bone166", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone166_r1 = bone166.addOrReplaceChild("bone166_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone167 = bone166.addOrReplaceChild("bone167", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone167_r1 = bone167.addOrReplaceChild("bone167_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone168 = bone167.addOrReplaceChild("bone168", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone168_r1 = bone168.addOrReplaceChild("bone168_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition lower_rotor = partdefinition.addOrReplaceChild("lower_rotor", CubeListBuilder.create(), PartPose.offset(0.0F, 27.0F, 0.0F));

        PartDefinition bone127 = lower_rotor.addOrReplaceChild("bone127", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -18.0F, -4.325F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone128 = bone127.addOrReplaceChild("bone128", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -20.0F, -4.325F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone129 = bone128.addOrReplaceChild("bone129", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -18.0F, -4.325F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone130 = bone129.addOrReplaceChild("bone130", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -20.0F, -4.325F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone131 = bone130.addOrReplaceChild("bone131", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -18.0F, -4.325F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone132 = bone131.addOrReplaceChild("bone132", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -20.0F, -4.325F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone157 = lower_rotor.addOrReplaceChild("bone157", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone157_r1 = bone157.addOrReplaceChild("bone157_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone158 = bone157.addOrReplaceChild("bone158", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone158_r1 = bone158.addOrReplaceChild("bone158_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone159 = bone158.addOrReplaceChild("bone159", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone159_r1 = bone159.addOrReplaceChild("bone159_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone160 = bone159.addOrReplaceChild("bone160", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone160_r1 = bone160.addOrReplaceChild("bone160_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone161 = bone160.addOrReplaceChild("bone161", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone161_r1 = bone161.addOrReplaceChild("bone161_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone162 = bone161.addOrReplaceChild("bone162", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone162_r1 = bone162.addOrReplaceChild("bone162_r1", CubeListBuilder.create().texOffs(69, 21).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -4.2F, -0.0044F, 0.0F, 0.0F));

        PartDefinition controls = partdefinition.addOrReplaceChild("controls", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition north = controls.addOrReplaceChild("north", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone176 = north.addOrReplaceChild("bone176", CubeListBuilder.create().texOffs(31, 72).addBox(-2.5F, -0.7333F, -10.0271F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 76).addBox(-1.5F, -0.0833F, -5.0271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 34).addBox(-2.0F, -0.0833F, -7.2771F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 20).addBox(-1.5F, -0.0833F, -1.5271F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(81, 51).addBox(2.5F, -0.0833F, -9.5271F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(81, 51).mirror().addBox(-5.5F, -0.0833F, -9.5271F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(46, 83).addBox(-1.0F, -0.4833F, -4.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone177_r1 = bone176.addOrReplaceChild("bone177_r1", CubeListBuilder.create().texOffs(0, 86).addBox(-6.5F, -3.0F, 0.0F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 0.0167F, -2.0271F, -0.3491F, 0.5672F, -0.3054F));

        PartDefinition bone176_r1 = bone176.addOrReplaceChild("bone176_r1", CubeListBuilder.create().texOffs(75, 25).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.7333F, -9.0271F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone178 = bone176.addOrReplaceChild("bone178", CubeListBuilder.create().texOffs(31, 67).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 11).addBox(-1.0F, -0.5F, -0.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.9833F, -3.5271F, 0.0F, -0.3927F, 0.0F));

        PartDefinition bone175 = bone176.addOrReplaceChild("bone175", CubeListBuilder.create().texOffs(26, 83).addBox(-1.0F, -0.9F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(71, 83).addBox(-0.5F, -1.4F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.4167F, -5.5271F));

        PartDefinition bone175_r1 = bone175.addOrReplaceChild("bone175_r1", CubeListBuilder.create().texOffs(66, 7).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone177 = bone176.addOrReplaceChild("bone177", CubeListBuilder.create().texOffs(26, 83).mirror().addBox(-1.0F, -0.9F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(71, 83).mirror().addBox(-0.5F, -1.4F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.4167F, -5.5271F));

        PartDefinition bone177_r2 = bone177.addOrReplaceChild("bone177_r2", CubeListBuilder.create().texOffs(66, 7).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition north_right = controls.addOrReplaceChild("north_right", CubeListBuilder.create().texOffs(59, 57).addBox(2.5F, -9.5F, -19.7736F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(66, 43).addBox(-7.5F, -9.9F, -19.2736F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(79, 6).addBox(-7.0F, -11.4F, -17.7736F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone181 = north_right.addOrReplaceChild("bone181", CubeListBuilder.create().texOffs(82, 11).addBox(-1.0F, -0.4833F, -2.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 68).addBox(-3.0F, -0.4833F, -5.5271F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 68).mirror().addBox(-3.0F, -0.4833F, -8.0271F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(56, 29).addBox(-6.0F, -0.0833F, -10.2771F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 78).addBox(3.5F, -0.0833F, -10.2771F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(53, 81).mirror().addBox(1.5F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 63).mirror().addBox(2.0F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(53, 81).addBox(-3.5F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(47, 0).addBox(-4.5F, -0.0833F, -5.5271F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(77, 75).addBox(-6.25F, -0.0833F, -8.0271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 77).addBox(-6.5F, -0.1833F, -8.2771F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 72).addBox(-4.5F, -0.5833F, -4.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 39).addBox(3.5F, -0.0833F, -7.7771F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(62, 63).addBox(-3.0F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone179_r1 = bone181.addOrReplaceChild("bone179_r1", CubeListBuilder.create().texOffs(18, 64).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -0.0833F, -2.0271F, 0.0F, -2.5744F, 0.0F));

        PartDefinition bone179_r2 = bone181.addOrReplaceChild("bone179_r2", CubeListBuilder.create().texOffs(18, 64).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.0833F, -2.0271F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone178_r1 = bone181.addOrReplaceChild("bone178_r1", CubeListBuilder.create().texOffs(18, 64).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0833F, -1.5271F, 0.0F, -0.6545F, 0.0F));

        PartDefinition bone182 = bone181.addOrReplaceChild("bone182", CubeListBuilder.create().texOffs(56, 23).addBox(-0.625F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(35, 83).addBox(-0.375F, -2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.125F, -0.0833F, -4.7771F, -0.6981F, 0.0F, 0.0F));

        PartDefinition south_right = controls.addOrReplaceChild("south_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone183 = south_right.addOrReplaceChild("bone183", CubeListBuilder.create().texOffs(66, 72).addBox(-1.5F, -0.0833F, -5.0271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(38, 54).addBox(-3.5F, -0.4833F, -10.2771F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-2.75F, -0.5833F, -7.0271F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(0.75F, -0.5833F, -7.0271F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(81, 66).addBox(0.75F, -0.5833F, -9.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(39, 76).addBox(-0.5F, -0.5833F, -7.0271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 82).addBox(-1.0F, -0.0833F, -2.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 0).addBox(-1.0F, -0.7333F, -4.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(2.25F, -0.4833F, -3.5271F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(0, 46).mirror().addBox(-3.25F, -0.4833F, -3.5271F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(76, 30).mirror().addBox(-6.25F, -0.0833F, -9.5271F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(76, 30).addBox(4.25F, -0.0833F, -9.5271F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone181_r1 = bone183.addOrReplaceChild("bone181_r1", CubeListBuilder.create().texOffs(81, 70).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, -0.4833F, -8.5271F, 0.0F, -0.4363F, 0.0F));

        PartDefinition bone184_r1 = bone183.addOrReplaceChild("bone184_r1", CubeListBuilder.create().texOffs(38, 54).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(75, 18).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(75, 18).addBox(-2.5F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.5833F, -6.5271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition south = controls.addOrReplaceChild("south", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone184 = south.addOrReplaceChild("bone184", CubeListBuilder.create().texOffs(50, 14).addBox(-3.5F, -0.4833F, -10.2771F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(47, 4).addBox(-2.5F, -0.8833F, -6.7771F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 49).addBox(-2.0F, -0.4833F, -2.5271F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(74, 48).addBox(1.75F, 0.1667F, -4.2771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(74, 48).mirror().addBox(-2.75F, 0.1667F, -4.2771F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(75, 79).addBox(-1.0F, -1.2333F, -4.5271F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(62, 81).mirror().addBox(-6.25F, -0.0833F, -9.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 81).addBox(4.25F, -0.0833F, -9.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone182_r1 = bone184.addOrReplaceChild("bone182_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-0.25F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -0.0833F, -3.7771F, 0.0F, -0.5672F, 0.0F));

        PartDefinition bone181_r2 = bone184.addOrReplaceChild("bone181_r2", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-1.75F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.25F, -0.0833F, -3.7771F, 0.0F, 0.5672F, 0.0F));

        PartDefinition bone183_r1 = bone184.addOrReplaceChild("bone183_r1", CubeListBuilder.create().texOffs(58, 54).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -0.3833F, -9.5271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone182_r2 = bone184.addOrReplaceChild("bone182_r2", CubeListBuilder.create().texOffs(71, 51).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -0.8833F, -8.2771F, 0.0F, -0.0873F, 0.0F));

        PartDefinition south_left = controls.addOrReplaceChild("south_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone185 = south_left.addOrReplaceChild("bone185", CubeListBuilder.create().texOffs(62, 63).addBox(-3.0F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(53, 81).addBox(-3.5F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(53, 81).mirror().addBox(1.5F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 63).mirror().addBox(2.0F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(53, 81).mirror().addBox(-1.0F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(56, 21).addBox(-1.5F, -0.0833F, -10.0271F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(70, 13).addBox(-6.0F, -0.0833F, -10.0271F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 49).addBox(2.5F, -0.4833F, -9.5271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(55, 77).mirror().addBox(2.0F, -0.0833F, -6.0271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(55, 77).addBox(-5.0F, -0.0833F, -6.0271F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 46).addBox(-3.5F, -0.5833F, -5.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 46).mirror().addBox(2.5F, -0.5833F, -5.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 63).mirror().addBox(-0.5F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone181_r3 = bone185.addOrReplaceChild("bone181_r3", CubeListBuilder.create().texOffs(18, 64).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0833F, -2.0271F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone184_r2 = bone185.addOrReplaceChild("bone184_r2", CubeListBuilder.create().texOffs(0, 24).addBox(-0.75F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0833F, -8.5271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone185_r1 = bone185.addOrReplaceChild("bone185_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-0.75F, -1.5F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -0.0833F, -8.0271F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone181_r4 = bone185.addOrReplaceChild("bone181_r4", CubeListBuilder.create().texOffs(18, 64).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -0.0833F, -2.0271F, 0.0F, 2.3562F, 0.0F));

        PartDefinition bone180_r1 = bone185.addOrReplaceChild("bone180_r1", CubeListBuilder.create().texOffs(18, 64).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.0833F, -2.0271F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone187 = bone185.addOrReplaceChild("bone187", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 14).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -0.4833F, -8.0271F, -0.829F, 0.0F, 0.0F));

        PartDefinition north_left = controls.addOrReplaceChild("north_left", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone179 = north_left.addOrReplaceChild("bone179", CubeListBuilder.create().texOffs(66, 63).addBox(-1.5F, -0.0833F, -10.0271F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(39, 79).addBox(2.5F, -1.0833F, -9.7771F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(39, 79).mirror().addBox(-1.5F, -1.0833F, -9.7771F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(8, 79).addBox(4.0F, -0.0833F, -10.0271F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(8, 79).addBox(-3.0F, -0.0833F, -10.0271F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(82, 0).addBox(-6.0F, -0.7333F, -9.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(74, 56).addBox(-6.5F, -0.0833F, -10.0271F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(82, 11).addBox(-1.0F, -0.4833F, -2.5271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 61).addBox(-4.0F, -0.4833F, -5.2771F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 33).addBox(-4.0F, -0.4833F, -6.7771F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 63).addBox(-3.0F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(53, 81).addBox(-3.5F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(53, 81).mirror().addBox(1.5F, -0.0833F, -3.0271F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(62, 63).mirror().addBox(2.0F, -0.4833F, -2.5271F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone181_r5 = bone179.addOrReplaceChild("bone181_r5", CubeListBuilder.create().texOffs(18, 60).addBox(0.75F, -1.75F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.2333F, -8.5271F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone180_r2 = bone179.addOrReplaceChild("bone180_r2", CubeListBuilder.create().texOffs(18, 64).mirror().addBox(-0.75F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -0.0833F, -2.0271F, 0.0F, 2.3562F, 0.0F));

        PartDefinition bone179_r3 = bone179.addOrReplaceChild("bone179_r3", CubeListBuilder.create().texOffs(18, 64).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.0833F, -2.0271F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone178_r2 = bone179.addOrReplaceChild("bone178_r2", CubeListBuilder.create().texOffs(18, 64).addBox(-0.25F, -0.5F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0833F, -1.5271F, 0.0F, -0.6545F, 0.0F));

        PartDefinition bone180 = bone179.addOrReplaceChild("bone180", CubeListBuilder.create().texOffs(83, 16).addBox(0.0F, -23.5309F, -16.2736F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 82).addBox(-0.5F, -23.5309F, -16.7736F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 23.1975F, 6.9965F));

        PartDefinition base_console = partdefinition.addOrReplaceChild("base_console", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone61 = base_console.addOrReplaceChild("bone61", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone61_r1 = bone61.addOrReplaceChild("bone61_r1", CubeListBuilder.create().texOffs(23, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone62 = bone61.addOrReplaceChild("bone62", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone62_r1 = bone62.addOrReplaceChild("bone62_r1", CubeListBuilder.create().texOffs(23, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone63 = bone62.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone63_r1 = bone63.addOrReplaceChild("bone63_r1", CubeListBuilder.create().texOffs(23, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone64_r1 = bone64.addOrReplaceChild("bone64_r1", CubeListBuilder.create().texOffs(23, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone65_r1 = bone65.addOrReplaceChild("bone65_r1", CubeListBuilder.create().texOffs(23, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone66 = bone65.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66_r1 = bone66.addOrReplaceChild("bone66_r1", CubeListBuilder.create().texOffs(23, 49).addBox(0.0F, -11.0F, 0.0F, 1.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -6.85F, 0.9599F, 0.0F, 0.0F));

        PartDefinition bone145 = base_console.addOrReplaceChild("bone145", CubeListBuilder.create().texOffs(46, 72).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone146 = bone145.addOrReplaceChild("bone146", CubeListBuilder.create().texOffs(46, 72).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone147 = bone146.addOrReplaceChild("bone147", CubeListBuilder.create().texOffs(46, 72).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone148 = bone147.addOrReplaceChild("bone148", CubeListBuilder.create().texOffs(46, 72).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone149 = bone148.addOrReplaceChild("bone149", CubeListBuilder.create().texOffs(46, 72).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone150 = bone149.addOrReplaceChild("bone150", CubeListBuilder.create().texOffs(46, 72).addBox(-1.0F, -7.0F, -7.6F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone31 = base_console.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(28, 76).addBox(-1.5F, -4.0F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone32 = bone31.addOrReplaceChild("bone32", CubeListBuilder.create().texOffs(28, 76).addBox(-1.5F, -4.0F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(28, 76).addBox(-1.5F, -4.0F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(28, 76).addBox(-1.5F, -4.0F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(28, 76).addBox(-1.5F, -4.0F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone36 = bone35.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(28, 76).addBox(-1.5F, -4.0F, -8.6F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone151 = base_console.addOrReplaceChild("bone151", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone151_r1 = bone151.addOrReplaceChild("bone151_r1", CubeListBuilder.create().texOffs(55, 63).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone152 = bone151.addOrReplaceChild("bone152", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone152_r1 = bone152.addOrReplaceChild("bone152_r1", CubeListBuilder.create().texOffs(55, 63).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone153 = bone152.addOrReplaceChild("bone153", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone153_r1 = bone153.addOrReplaceChild("bone153_r1", CubeListBuilder.create().texOffs(55, 63).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone154 = bone153.addOrReplaceChild("bone154", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone154_r1 = bone154.addOrReplaceChild("bone154_r1", CubeListBuilder.create().texOffs(55, 63).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone155 = bone154.addOrReplaceChild("bone155", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone155_r1 = bone155.addOrReplaceChild("bone155_r1", CubeListBuilder.create().texOffs(55, 63).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone156 = bone155.addOrReplaceChild("bone156", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone156_r1 = bone156.addOrReplaceChild("bone156_r1", CubeListBuilder.create().texOffs(55, 63).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -8.6F, -0.8727F, 0.0F, 0.0F));

        PartDefinition bone43 = base_console.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(81, 46).addBox(-1.0F, -2.0F, -22.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone44 = bone43.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(81, 46).addBox(-1.0F, -2.0F, -22.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(81, 46).addBox(-1.0F, -2.0F, -22.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(81, 46).addBox(-1.0F, -2.0F, -22.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(81, 46).addBox(-1.0F, -2.0F, -22.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(81, 46).addBox(-1.0F, -2.0F, -22.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone7 = base_console.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(81, 41).addBox(-1.0F, -2.0F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(81, 41).addBox(-1.0F, -2.0F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(81, 41).addBox(-1.0F, -2.0F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(81, 41).addBox(-1.0F, -2.0F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(81, 41).addBox(-1.0F, -2.0F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(81, 41).addBox(-1.0F, -2.0F, -9.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49 = base_console.addOrReplaceChild("bone49", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone49_r1 = bone49.addOrReplaceChild("bone49_r1", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone50 = bone49.addOrReplaceChild("bone50", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50_r1 = bone50.addOrReplaceChild("bone50_r1", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone51_r1 = bone51.addOrReplaceChild("bone51_r1", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone52_r1 = bone52.addOrReplaceChild("bone52_r1", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53_r1 = bone53.addOrReplaceChild("bone53_r1", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone54 = bone53.addOrReplaceChild("bone54", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone54_r1 = bone54.addOrReplaceChild("bone54_r1", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -17.075F, 0.6283F, 0.0F, 0.0F));

        PartDefinition bone19 = base_console.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone55 = bone19.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone55_r1 = bone55.addOrReplaceChild("bone55_r1", CubeListBuilder.create().texOffs(40, 24).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone55_r2 = bone55.addOrReplaceChild("bone55_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone20 = bone19.addOrReplaceChild("bone20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone21_r1 = bone21.addOrReplaceChild("bone21_r1", CubeListBuilder.create().texOffs(40, 24).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone21_r2 = bone21.addOrReplaceChild("bone21_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone22 = bone20.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone23_r1 = bone23.addOrReplaceChild("bone23_r1", CubeListBuilder.create().texOffs(40, 24).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone23_r2 = bone23.addOrReplaceChild("bone23_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone24 = bone22.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone56 = bone24.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone56_r1 = bone56.addOrReplaceChild("bone56_r1", CubeListBuilder.create().texOffs(40, 24).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone56_r2 = bone56.addOrReplaceChild("bone56_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone57 = bone24.addOrReplaceChild("bone57", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone58 = bone57.addOrReplaceChild("bone58", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone58_r1 = bone58.addOrReplaceChild("bone58_r1", CubeListBuilder.create().texOffs(40, 24).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone58_r2 = bone58.addOrReplaceChild("bone58_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone59 = bone57.addOrReplaceChild("bone59", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = bone59.addOrReplaceChild("bone60", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, 0.0167F, -11.0271F, 17.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.6975F, -7.2465F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone60_r1 = bone60.addOrReplaceChild("bone60_r1", CubeListBuilder.create().texOffs(40, 24).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.7553F, -0.0083F, -10.4723F, 0.0F, 0.4189F, 0.0F));

        PartDefinition bone60_r2 = bone60.addOrReplaceChild("bone60_r2", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.7553F, -0.0083F, -10.4723F, 0.0F, -0.4189F, 0.0F));

        PartDefinition bone73 = base_console.addOrReplaceChild("bone73", CubeListBuilder.create().texOffs(17, 81).addBox(-1.0F, -10.0F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone74 = bone73.addOrReplaceChild("bone74", CubeListBuilder.create().texOffs(17, 81).addBox(-1.0F, -10.0F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(17, 81).addBox(-1.0F, -10.0F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone76 = bone75.addOrReplaceChild("bone76", CubeListBuilder.create().texOffs(17, 81).addBox(-1.0F, -10.0F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create().texOffs(17, 81).addBox(-1.0F, -10.0F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create().texOffs(17, 81).addBox(-1.0F, -10.0F, -7.725F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone109 = base_console.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -45.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone110 = bone109.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone113 = bone112.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone114 = bone113.addOrReplaceChild("bone114", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -5.725F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone85 = base_console.addOrReplaceChild("bone85", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.0F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone86 = bone85.addOrReplaceChild("bone86", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.0F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.0F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.0F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.0F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create().texOffs(41, 24).addBox(-1.0F, -11.0F, -5.725F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone121 = base_console.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(26, 34).addBox(-1.0F, -11.5F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -51.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone122 = bone121.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(26, 34).addBox(-1.0F, -11.5F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone123 = bone122.addOrReplaceChild("bone123", CubeListBuilder.create().texOffs(26, 34).addBox(-1.0F, -11.5F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone124 = bone123.addOrReplaceChild("bone124", CubeListBuilder.create().texOffs(26, 34).addBox(-1.0F, -11.5F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone125 = bone124.addOrReplaceChild("bone125", CubeListBuilder.create().texOffs(26, 34).addBox(-1.0F, -11.5F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone126 = bone125.addOrReplaceChild("bone126", CubeListBuilder.create().texOffs(26, 34).addBox(-1.0F, -11.5F, -7.725F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone103 = base_console.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -9.5F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -47.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone104 = bone103.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -9.5F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -9.5F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -9.5F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone107 = bone106.addOrReplaceChild("bone107", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -9.5F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -9.5F, -6.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone115 = base_console.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(45, 41).addBox(-3.0F, -11.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -51.5F, 0.0F));

        PartDefinition bone116 = bone115.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(45, 41).addBox(-3.0F, -11.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone117 = bone116.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(45, 41).addBox(-3.0F, -11.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone118 = bone117.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(45, 41).addBox(-3.0F, -11.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone119 = bone118.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(45, 41).addBox(-3.0F, -11.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone120 = bone119.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(45, 41).addBox(-3.0F, -11.5F, -7.2F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = base_console.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(66, 37).addBox(-2.5F, -9.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -47.5F, 0.0F));

        PartDefinition bone98 = bone97.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(66, 37).addBox(-2.5F, -9.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone99 = bone98.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(66, 37).addBox(-2.5F, -9.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone100 = bone99.addOrReplaceChild("bone100", CubeListBuilder.create().texOffs(66, 37).addBox(-2.5F, -9.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(66, 37).addBox(-2.5F, -9.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone102 = bone101.addOrReplaceChild("bone102", CubeListBuilder.create().texOffs(66, 37).addBox(-2.5F, -9.5F, -6.3F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone139 = base_console.addOrReplaceChild("bone139", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -6.5F, -6.225F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -52.0F, 0.0F));

        PartDefinition bone140 = bone139.addOrReplaceChild("bone140", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -6.5F, -6.225F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone141 = bone140.addOrReplaceChild("bone141", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -6.5F, -6.225F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone142 = bone141.addOrReplaceChild("bone142", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -6.5F, -6.225F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone143 = bone142.addOrReplaceChild("bone143", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -6.5F, -6.225F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone144 = bone143.addOrReplaceChild("bone144", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -6.5F, -6.225F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91 = base_console.addOrReplaceChild("bone91", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -6.5F, -5.475F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -52.5F, 0.0F));

        PartDefinition bone92 = bone91.addOrReplaceChild("bone92", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -6.5F, -5.475F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -6.5F, -5.475F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -6.5F, -5.475F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -6.5F, -5.475F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -6.5F, -5.475F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone79 = base_console.addOrReplaceChild("bone79", CubeListBuilder.create().texOffs(80, 35).addBox(-1.0F, -11.5F, -5.975F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.5F, 0.0F));

        PartDefinition bone79_r1 = bone79.addOrReplaceChild("bone79_r1", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone80 = bone79.addOrReplaceChild("bone80", CubeListBuilder.create().texOffs(80, 35).addBox(-1.0F, -11.5F, -5.975F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80_r1 = bone80.addOrReplaceChild("bone80_r1", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create().texOffs(80, 35).addBox(-1.0F, -11.5F, -5.975F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone81_r1 = bone81.addOrReplaceChild("bone81_r1", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create().texOffs(80, 35).addBox(-1.0F, -11.5F, -5.975F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82_r1 = bone82.addOrReplaceChild("bone82_r1", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone83 = bone82.addOrReplaceChild("bone83", CubeListBuilder.create().texOffs(80, 35).addBox(-1.0F, -11.5F, -5.975F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone83_r1 = bone83.addOrReplaceChild("bone83_r1", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create().texOffs(80, 35).addBox(-1.0F, -11.5F, -5.975F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84_r1 = bone84.addOrReplaceChild("bone84_r1", CubeListBuilder.create().texOffs(47, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -5.475F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone67 = base_console.addOrReplaceChild("bone67", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

        PartDefinition bone68 = bone67.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone70 = bone69.addOrReplaceChild("bone70", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone72 = bone71.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(63, 0).addBox(-3.0F, -10.0F, -7.2F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = base_console.addOrReplaceChild("bone37", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone37_r1 = bone37.addOrReplaceChild("bone37_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone38 = bone37.addOrReplaceChild("bone38", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone38_r1 = bone38.addOrReplaceChild("bone38_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone39_r1 = bone39.addOrReplaceChild("bone39_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40_r1 = bone40.addOrReplaceChild("bone40_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41_r1 = bone41.addOrReplaceChild("bone41_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42_r1 = bone42.addOrReplaceChild("bone42_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-10.5F, 0.0F, 1.775F, 21.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -21.95F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone169 = base_console.addOrReplaceChild("bone169", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition bone170 = bone169.addOrReplaceChild("bone170", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone171 = bone170.addOrReplaceChild("bone171", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone172 = bone171.addOrReplaceChild("bone172", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone173 = bone172.addOrReplaceChild("bone173", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone174 = bone173.addOrReplaceChild("bone174", CubeListBuilder.create().texOffs(0, 24).addBox(-8.0F, -7.0F, -13.85F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = base_console.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(18, 67).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition bone26 = bone25.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(18, 67).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(18, 67).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(18, 67).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(18, 67).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(18, 67).addBox(-2.5F, -7.0F, -6.425F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone13 = base_console.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(36, 65).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(36, 65).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(36, 65).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(36, 65).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(36, 65).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(36, 65).addBox(-3.0F, -4.0F, -8.2F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone = base_console.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -2.0F, -8.925F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        upper_rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        lower_rotor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        controls.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        base_console.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void renderConsole(GlobalConsoleBlockEntity globalConsoleBlock, Level level, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root().getAllParts().forEach(ModelPart::resetPose);
        TardisClientData reactions = TardisClientData.getInstance(level.dimension());

        if (globalConsoleBlock != null && globalConsoleBlock.getBlockState().getValue(GlobalConsoleBlock.POWERED)) {
            if (reactions.isFlying()) {
                this.animate(reactions.ROTOR_ANIMATION, FLIGHT, Minecraft.getInstance().player.tickCount);
            } else {
                if (TRConfig.CLIENT.PLAY_CONSOLE_IDLE_ANIMATIONS.get() && globalConsoleBlock != null) {
                    this.animate(globalConsoleBlock.liveliness, IDLE, Minecraft.getInstance().player.tickCount);
                }
            }
        }

        float rot = -1f + (2 * ((float) reactions.getThrottleStage() / TardisPilotingManager.MAX_THROTTLE_STAGE));
        throttle_control.xRot = rot;

        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return VICTORIAN_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.VICTORIAN.getId();
    }
}