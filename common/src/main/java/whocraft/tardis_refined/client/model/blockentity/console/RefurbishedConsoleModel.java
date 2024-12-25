package whocraft.tardis_refined.client.model.blockentity.console;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.jeryn.frame.tardis.Frame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationDefinition;
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

public class RefurbishedConsoleModel extends HierarchicalModel implements ConsoleUnit {

    private static final ResourceLocation REFURBISHED_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/blockentity/console/refurbished/refurbished_console.png");
    private final ModelPart root;
    private final ModelPart throttle;
    private final ModelPart handbrake;

    public static final AnimationDefinition IDLE = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/refurbished/idle.json"));
    public static final AnimationDefinition FLIGHT = Frame.loadAnimation( new ResourceLocation(TardisRefined.MODID, "frame/console/refurbished/flight.json"));


    public RefurbishedConsoleModel(ModelPart root) {
        this.root = root;
        this.throttle = (ModelPart) getAnyDescendantWithName("throttle").get();
        this.handbrake = (ModelPart) getAnyDescendantWithName("bone309").get();

    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.5F, 0.0F));

        PartDefinition console = root.addOrReplaceChild("console", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.0F));

        PartDefinition console_r1 = console.addOrReplaceChild("console_r1", CubeListBuilder.create().texOffs(80, 61).addBox(-8.0F, -0.5F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(80, 61).addBox(-8.0F, 40.5F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -64.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition Rotor = console.addOrReplaceChild("Rotor", CubeListBuilder.create(), PartPose.offset(0.0F, -45.0F, 0.0F));

        PartDefinition bone152 = Rotor.addOrReplaceChild("bone152", CubeListBuilder.create().texOffs(77, 9).addBox(-3.5F, 5.25F, 15.225F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, -21.285F));

        PartDefinition bone153 = bone152.addOrReplaceChild("bone153", CubeListBuilder.create().texOffs(77, 9).addBox(-3.5F, 5.25F, -6.06F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone154 = bone153.addOrReplaceChild("bone154", CubeListBuilder.create().texOffs(77, 9).addBox(-3.5F, 5.25F, -6.06F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone155 = bone154.addOrReplaceChild("bone155", CubeListBuilder.create().texOffs(77, 9).addBox(-3.5F, 5.25F, -6.06F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone156 = bone155.addOrReplaceChild("bone156", CubeListBuilder.create().texOffs(77, 9).addBox(-3.5F, 5.25F, -6.06F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone157 = bone156.addOrReplaceChild("bone157", CubeListBuilder.create().texOffs(77, 9).addBox(-3.5F, 5.25F, -6.06F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone212 = Rotor.addOrReplaceChild("bone212", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 0.0F));

        PartDefinition bone213 = bone212.addOrReplaceChild("bone213", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone214 = bone213.addOrReplaceChild("bone214", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone215 = bone214.addOrReplaceChild("bone215", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone216 = bone215.addOrReplaceChild("bone216", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone217 = bone216.addOrReplaceChild("bone217", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone206 = Rotor.addOrReplaceChild("bone206", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition bone207 = bone206.addOrReplaceChild("bone207", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone208 = bone207.addOrReplaceChild("bone208", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone209 = bone208.addOrReplaceChild("bone209", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone210 = bone209.addOrReplaceChild("bone210", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone211 = bone210.addOrReplaceChild("bone211", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone158 = Rotor.addOrReplaceChild("bone158", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.5F, 0.0F));

        PartDefinition bone159 = bone158.addOrReplaceChild("bone159", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone160 = bone159.addOrReplaceChild("bone160", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone161 = bone160.addOrReplaceChild("bone161", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone162 = bone161.addOrReplaceChild("bone162", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone163 = bone162.addOrReplaceChild("bone163", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone176 = Rotor.addOrReplaceChild("bone176", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition bone177 = bone176.addOrReplaceChild("bone177", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone178 = bone177.addOrReplaceChild("bone178", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone179 = bone178.addOrReplaceChild("bone179", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone180 = bone179.addOrReplaceChild("bone180", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone181 = bone180.addOrReplaceChild("bone181", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone182 = Rotor.addOrReplaceChild("bone182", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.5F, 0.0F));

        PartDefinition bone183 = bone182.addOrReplaceChild("bone183", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone184 = bone183.addOrReplaceChild("bone184", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone185 = bone184.addOrReplaceChild("bone185", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone186 = bone185.addOrReplaceChild("bone186", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone187 = bone186.addOrReplaceChild("bone187", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone188 = Rotor.addOrReplaceChild("bone188", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.5F, 0.0F));

        PartDefinition bone189 = bone188.addOrReplaceChild("bone189", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone190 = bone189.addOrReplaceChild("bone190", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone191 = bone190.addOrReplaceChild("bone191", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone192 = bone191.addOrReplaceChild("bone192", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone193 = bone192.addOrReplaceChild("bone193", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone164 = Rotor.addOrReplaceChild("bone164", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition bone165 = bone164.addOrReplaceChild("bone165", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone166 = bone165.addOrReplaceChild("bone166", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone167 = bone166.addOrReplaceChild("bone167", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone168 = bone167.addOrReplaceChild("bone168", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone169 = bone168.addOrReplaceChild("bone169", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone194 = Rotor.addOrReplaceChild("bone194", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition bone195 = bone194.addOrReplaceChild("bone195", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone196 = bone195.addOrReplaceChild("bone196", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone197 = bone196.addOrReplaceChild("bone197", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone198 = bone197.addOrReplaceChild("bone198", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone199 = bone198.addOrReplaceChild("bone199", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone170 = Rotor.addOrReplaceChild("bone170", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));

        PartDefinition bone171 = bone170.addOrReplaceChild("bone171", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone172 = bone171.addOrReplaceChild("bone172", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone173 = bone172.addOrReplaceChild("bone173", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone174 = bone173.addOrReplaceChild("bone174", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone175 = bone174.addOrReplaceChild("bone175", CubeListBuilder.create().texOffs(49, 34).addBox(-3.0F, 6.0F, -5.21F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone200 = Rotor.addOrReplaceChild("bone200", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition bone201 = bone200.addOrReplaceChild("bone201", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone202 = bone201.addOrReplaceChild("bone202", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone203 = bone202.addOrReplaceChild("bone203", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone204 = bone203.addOrReplaceChild("bone204", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone205 = bone204.addOrReplaceChild("bone205", CubeListBuilder.create().texOffs(49, 37).addBox(-2.5F, 6.0F, -4.31F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone60 = console.addOrReplaceChild("bone60", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone32 = bone60.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offset(0.0F, -21.5F, -21.285F));

        PartDefinition bone98 = bone32.addOrReplaceChild("bone98", CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, -5.0F, -3.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, -7.5F, -3.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 3).addBox(-1.0F, -8.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 14.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone349 = bone98.addOrReplaceChild("bone349", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, -1.75F));

        PartDefinition bone33 = bone32.addOrReplaceChild("bone33", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone99 = bone33.addOrReplaceChild("bone99", CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, -5.0F, -3.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, -7.5F, -3.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 3).addBox(-1.0F, -8.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -7.285F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone350 = bone99.addOrReplaceChild("bone350", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, -1.75F));

        PartDefinition bone34 = bone33.addOrReplaceChild("bone34", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone35 = bone34.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, -5.0F, -3.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, -7.5F, -3.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 3).addBox(-1.0F, -8.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -7.285F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone351 = bone35.addOrReplaceChild("bone351", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, -1.75F));

        PartDefinition bone36 = bone34.addOrReplaceChild("bone36", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone37 = bone36.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, -5.0F, -3.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, -7.5F, -3.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 3).addBox(-1.0F, -8.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -7.285F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone346 = bone37.addOrReplaceChild("bone346", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, -1.75F));

        PartDefinition bone100 = bone36.addOrReplaceChild("bone100", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone101 = bone100.addOrReplaceChild("bone101", CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, -5.0F, -3.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, -7.5F, -3.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 3).addBox(-1.0F, -8.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -7.285F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone347 = bone101.addOrReplaceChild("bone347", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, -1.75F));

        PartDefinition bone102 = bone100.addOrReplaceChild("bone102", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone103 = bone102.addOrReplaceChild("bone103", CubeListBuilder.create().texOffs(0, 76).addBox(-2.0F, -5.0F, -3.75F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-1.5F, -7.5F, -3.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(97, 3).addBox(-1.0F, -8.0F, -2.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -7.285F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone348 = bone103.addOrReplaceChild("bone348", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, -1.75F));

        PartDefinition bone44 = bone60.addOrReplaceChild("bone44", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -25.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone45 = bone44.addOrReplaceChild("bone45", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone46 = bone45.addOrReplaceChild("bone46", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone47 = bone46.addOrReplaceChild("bone47", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone48 = bone47.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone49 = bone48.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone243 = bone60.addOrReplaceChild("bone243", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -67.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone244 = bone243.addOrReplaceChild("bone244", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone245 = bone244.addOrReplaceChild("bone245", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone246 = bone245.addOrReplaceChild("bone246", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone247 = bone246.addOrReplaceChild("bone247", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone248 = bone247.addOrReplaceChild("bone248", CubeListBuilder.create().texOffs(49, 40).addBox(-0.5F, 0.5F, -8.87F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone249 = bone60.addOrReplaceChild("bone249", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -69.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone249_r1 = bone249.addOrReplaceChild("bone249_r1", CubeListBuilder.create().texOffs(91, 37).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -8.62F, 0.5454F, 0.0F, 0.0F));

        PartDefinition bone250 = bone249.addOrReplaceChild("bone250", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone250_r1 = bone250.addOrReplaceChild("bone250_r1", CubeListBuilder.create().texOffs(91, 37).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -8.62F, 0.5454F, 0.0F, 0.0F));

        PartDefinition bone251 = bone250.addOrReplaceChild("bone251", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone251_r1 = bone251.addOrReplaceChild("bone251_r1", CubeListBuilder.create().texOffs(91, 37).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -8.62F, 0.5454F, 0.0F, 0.0F));

        PartDefinition bone252 = bone251.addOrReplaceChild("bone252", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone252_r1 = bone252.addOrReplaceChild("bone252_r1", CubeListBuilder.create().texOffs(91, 37).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -8.62F, 0.5454F, 0.0F, 0.0F));

        PartDefinition bone253 = bone252.addOrReplaceChild("bone253", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone253_r1 = bone253.addOrReplaceChild("bone253_r1", CubeListBuilder.create().texOffs(91, 37).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -8.62F, 0.5454F, 0.0F, 0.0F));

        PartDefinition bone254 = bone253.addOrReplaceChild("bone254", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone254_r1 = bone254.addOrReplaceChild("bone254_r1", CubeListBuilder.create().texOffs(91, 37).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -8.62F, 0.5454F, 0.0F, 0.0F));

        PartDefinition bone123 = bone60.addOrReplaceChild("bone123", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -26.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone345 = bone123.addOrReplaceChild("bone345", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.6828F, 0.4833F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 1.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone345_r1 = bone345.addOrReplaceChild("bone345_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone345_r2 = bone345.addOrReplaceChild("bone345_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone124 = bone123.addOrReplaceChild("bone124", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone125 = bone124.addOrReplaceChild("bone125", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.6828F, 0.4833F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 1.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone125_r1 = bone125.addOrReplaceChild("bone125_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone125_r2 = bone125.addOrReplaceChild("bone125_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone131 = bone124.addOrReplaceChild("bone131", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone132 = bone131.addOrReplaceChild("bone132", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.6828F, 0.4833F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 1.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone132_r1 = bone132.addOrReplaceChild("bone132_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone132_r2 = bone132.addOrReplaceChild("bone132_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone133 = bone131.addOrReplaceChild("bone133", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone140 = bone133.addOrReplaceChild("bone140", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.6828F, 0.4833F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 1.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone140_r1 = bone140.addOrReplaceChild("bone140_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone140_r2 = bone140.addOrReplaceChild("bone140_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone141 = bone133.addOrReplaceChild("bone141", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone142 = bone141.addOrReplaceChild("bone142", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.6828F, 0.4833F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 1.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone142_r1 = bone142.addOrReplaceChild("bone142_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone142_r2 = bone142.addOrReplaceChild("bone142_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone143 = bone141.addOrReplaceChild("bone143", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone144 = bone143.addOrReplaceChild("bone144", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.6828F, 0.4833F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 1.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone144_r1 = bone144.addOrReplaceChild("bone144_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone144_r2 = bone144.addOrReplaceChild("bone144_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -1.9817F, 0.4583F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone134 = bone60.addOrReplaceChild("bone134", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -61.5F, 0.0F, 0.0F, -0.5236F, -3.1416F));

        PartDefinition bone135 = bone134.addOrReplaceChild("bone135", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.6354F, 0.0505F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 3.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone135_r1 = bone135.addOrReplaceChild("bone135_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone135_r2 = bone135.addOrReplaceChild("bone135_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone136 = bone134.addOrReplaceChild("bone136", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone137 = bone136.addOrReplaceChild("bone137", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.6354F, 0.0505F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 3.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone137_r1 = bone137.addOrReplaceChild("bone137_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone137_r2 = bone137.addOrReplaceChild("bone137_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone138 = bone136.addOrReplaceChild("bone138", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone139 = bone138.addOrReplaceChild("bone139", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.6354F, 0.0505F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 3.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone139_r1 = bone139.addOrReplaceChild("bone139_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone139_r2 = bone139.addOrReplaceChild("bone139_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone145 = bone138.addOrReplaceChild("bone145", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone146 = bone145.addOrReplaceChild("bone146", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.6354F, 0.0505F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 3.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone146_r1 = bone146.addOrReplaceChild("bone146_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone146_r2 = bone146.addOrReplaceChild("bone146_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone147 = bone145.addOrReplaceChild("bone147", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone148 = bone147.addOrReplaceChild("bone148", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.6354F, 0.0505F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 3.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone148_r1 = bone148.addOrReplaceChild("bone148_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone148_r2 = bone148.addOrReplaceChild("bone148_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone149 = bone147.addOrReplaceChild("bone149", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone150 = bone149.addOrReplaceChild("bone150", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -6.6354F, 0.0505F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 3.6743F, -6.9448F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone150_r1 = bone150.addOrReplaceChild("bone150_r1", CubeListBuilder.create().texOffs(21, 53).mirror().addBox(0.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(-2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, 0.3665F));

        PartDefinition bone150_r2 = bone150.addOrReplaceChild("bone150_r2", CubeListBuilder.create().texOffs(21, 53).addBox(-1.0F, -3.0F, 0.05F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(2.3591F, -3.9343F, 0.0255F, 0.0F, 0.0F, -0.3665F));

        PartDefinition bone38 = bone60.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 1.5F, 13.35F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.0F, -21.285F));

        PartDefinition bone39 = bone38.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 1.5F, -7.935F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone40 = bone39.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 1.5F, -7.935F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone41 = bone40.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 1.5F, -7.935F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone42 = bone41.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 1.5F, -7.935F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone43 = bone42.addOrReplaceChild("bone43", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 1.5F, -7.935F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone321 = bone60.addOrReplaceChild("bone321", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 0.5F, 13.35F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -67.0F, -21.285F));

        PartDefinition bone322 = bone321.addOrReplaceChild("bone322", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 0.5F, -7.935F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone323 = bone322.addOrReplaceChild("bone323", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 0.5F, -7.935F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone324 = bone323.addOrReplaceChild("bone324", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 0.5F, -7.935F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone325 = bone324.addOrReplaceChild("bone325", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 0.5F, -7.935F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone326 = bone325.addOrReplaceChild("bone326", CubeListBuilder.create().texOffs(68, 50).addBox(-4.0F, 0.5F, -7.935F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone104 = bone60.addOrReplaceChild("bone104", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.5F, 16.35F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -27.0F, -21.285F));

        PartDefinition bone105 = bone104.addOrReplaceChild("bone105", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.5F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone106 = bone105.addOrReplaceChild("bone106", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.5F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone107 = bone106.addOrReplaceChild("bone107", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.5F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone108 = bone107.addOrReplaceChild("bone108", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.5F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone109 = bone108.addOrReplaceChild("bone109", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.5F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone110 = bone60.addOrReplaceChild("bone110", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, 16.35F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -62.0F, -21.285F));

        PartDefinition bone111 = bone110.addOrReplaceChild("bone111", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone112 = bone111.addOrReplaceChild("bone112", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone113 = bone112.addOrReplaceChild("bone113", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone114 = bone113.addOrReplaceChild("bone114", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone115 = bone114.addOrReplaceChild("bone115", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, -4.935F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone116 = bone60.addOrReplaceChild("bone116", CubeListBuilder.create().texOffs(29, 53).addBox(-0.75F, -12.0F, 17.35F, 2.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -47.0F, -21.285F));

        PartDefinition bone117 = bone116.addOrReplaceChild("bone117", CubeListBuilder.create().texOffs(29, 53).addBox(-0.75F, -12.0F, -3.935F, 2.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone118 = bone117.addOrReplaceChild("bone118", CubeListBuilder.create().texOffs(29, 53).addBox(-0.75F, -12.0F, -3.935F, 2.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone120 = bone118.addOrReplaceChild("bone120", CubeListBuilder.create().texOffs(29, 53).addBox(-0.75F, -12.0F, -3.935F, 2.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone121 = bone120.addOrReplaceChild("bone121", CubeListBuilder.create().texOffs(29, 53).addBox(-0.75F, -12.0F, -3.935F, 2.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone122 = bone121.addOrReplaceChild("bone122", CubeListBuilder.create().texOffs(29, 53).addBox(-0.75F, -12.0F, -3.935F, 2.0F, 28.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone352 = bone60.addOrReplaceChild("bone352", CubeListBuilder.create().texOffs(37, 53).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, -42.0F, -3.46F));

        PartDefinition bone353 = bone352.addOrReplaceChild("bone353", CubeListBuilder.create().texOffs(37, 53).addBox(-0.75F, -2.0F, -3.96F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 1.5F, 3.46F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone354 = bone353.addOrReplaceChild("bone354", CubeListBuilder.create().texOffs(37, 53).addBox(-0.75F, -2.0F, -3.96F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone355 = bone354.addOrReplaceChild("bone355", CubeListBuilder.create().texOffs(37, 53).addBox(-0.75F, -2.0F, -3.96F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone356 = bone355.addOrReplaceChild("bone356", CubeListBuilder.create().texOffs(37, 53).addBox(-0.75F, -2.0F, -3.96F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone357 = bone356.addOrReplaceChild("bone357", CubeListBuilder.create().texOffs(37, 53).addBox(-0.75F, -2.0F, -3.96F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone50 = bone60.addOrReplaceChild("bone50", CubeListBuilder.create(), PartPose.offset(0.0F, -19.5F, -21.285F));

        PartDefinition bone = bone50.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 10.725F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone_r1 = bone.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone_r2 = bone.addOrReplaceChild("bone_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone51 = bone50.addOrReplaceChild("bone51", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone268 = bone51.addOrReplaceChild("bone268", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone268_r1 = bone268.addOrReplaceChild("bone268_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone268_r2 = bone268.addOrReplaceChild("bone268_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone52 = bone51.addOrReplaceChild("bone52", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone53 = bone52.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone53_r1 = bone53.addOrReplaceChild("bone53_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone53_r2 = bone53.addOrReplaceChild("bone53_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone54 = bone52.addOrReplaceChild("bone54", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone55 = bone54.addOrReplaceChild("bone55", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone55_r1 = bone55.addOrReplaceChild("bone55_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone55_r2 = bone55.addOrReplaceChild("bone55_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone56 = bone54.addOrReplaceChild("bone56", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone57 = bone56.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone57_r1 = bone57.addOrReplaceChild("bone57_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone57_r2 = bone57.addOrReplaceChild("bone57_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone58 = bone56.addOrReplaceChild("bone58", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone59 = bone58.addOrReplaceChild("bone59", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone59_r1 = bone59.addOrReplaceChild("bone59_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone59_r2 = bone59.addOrReplaceChild("bone59_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone26 = bone60.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(67, 55).addBox(-5.0F, 0.5F, 10.625F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.5F, -21.285F));

        PartDefinition bone27 = bone26.addOrReplaceChild("bone27", CubeListBuilder.create().texOffs(67, 55).addBox(-5.0F, 0.5F, -10.66F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone28 = bone27.addOrReplaceChild("bone28", CubeListBuilder.create().texOffs(67, 55).addBox(-5.0F, 0.5F, -10.66F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone29 = bone28.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(67, 55).addBox(-5.0F, 0.5F, -10.66F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone30 = bone29.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(67, 55).addBox(-5.0F, 0.5F, -10.66F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone31 = bone30.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(67, 55).addBox(-5.0F, 0.5F, -10.66F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone256 = bone60.addOrReplaceChild("bone256", CubeListBuilder.create().texOffs(92, 45).mirror().addBox(-7.75F, 1.45F, 6.835F, 16.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -19.2F, -21.285F));

        PartDefinition bone257 = bone256.addOrReplaceChild("bone257", CubeListBuilder.create().texOffs(92, 45).addBox(-8.5F, 1.6F, -15.725F, 17.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone258 = bone257.addOrReplaceChild("bone258", CubeListBuilder.create().texOffs(92, 45).addBox(-8.5F, 1.6F, -15.725F, 17.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone259 = bone258.addOrReplaceChild("bone259", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone260 = bone259.addOrReplaceChild("bone260", CubeListBuilder.create().texOffs(92, 45).addBox(-8.5F, 1.6F, -15.725F, 17.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone261 = bone260.addOrReplaceChild("bone261", CubeListBuilder.create().texOffs(92, 45).addBox(-8.25F, 1.45F, -14.45F, 16.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone262 = bone60.addOrReplaceChild("bone262", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -19.2F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone262_r1 = bone262.addOrReplaceChild("bone262_r1", CubeListBuilder.create().texOffs(122, 101).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.6105F, -17.6117F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone263 = bone262.addOrReplaceChild("bone263", CubeListBuilder.create().texOffs(0, 40).addBox(-0.5F, 1.6F, -17.875F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone264 = bone263.addOrReplaceChild("bone264", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone264_r1 = bone264.addOrReplaceChild("bone264_r1", CubeListBuilder.create().texOffs(122, 101).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.6105F, -17.6117F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone265 = bone264.addOrReplaceChild("bone265", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone265_r1 = bone265.addOrReplaceChild("bone265_r1", CubeListBuilder.create().texOffs(122, 101).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.6105F, -17.6117F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone266 = bone265.addOrReplaceChild("bone266", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone266_r1 = bone266.addOrReplaceChild("bone266_r1", CubeListBuilder.create().texOffs(122, 101).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.6105F, -17.6117F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone267 = bone266.addOrReplaceChild("bone267", CubeListBuilder.create().texOffs(0, 40).addBox(-0.5F, 1.45F, -16.375F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone20 = bone60.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(5, 53).addBox(-1.0F, 0.5F, -11.728F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone21 = bone20.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(5, 53).addBox(-1.0F, 0.5F, -11.728F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone22 = bone21.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(5, 53).addBox(-1.0F, 0.5F, -11.728F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone23 = bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(5, 53).addBox(-1.0F, 0.5F, -11.728F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone24 = bone23.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(5, 53).addBox(-1.0F, 0.5F, -11.728F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone25 = bone24.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(5, 53).addBox(-1.0F, 0.5F, -11.728F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone275 = bone60.addOrReplaceChild("bone275", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone275_r1 = bone275.addOrReplaceChild("bone275_r1", CubeListBuilder.create().texOffs(19, 76).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -11.478F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone282 = bone275.addOrReplaceChild("bone282", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone282_r1 = bone282.addOrReplaceChild("bone282_r1", CubeListBuilder.create().texOffs(19, 76).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -11.478F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone296 = bone282.addOrReplaceChild("bone296", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone296_r1 = bone296.addOrReplaceChild("bone296_r1", CubeListBuilder.create().texOffs(19, 76).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -11.478F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone297 = bone296.addOrReplaceChild("bone297", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone297_r1 = bone297.addOrReplaceChild("bone297_r1", CubeListBuilder.create().texOffs(19, 76).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -11.478F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone298 = bone297.addOrReplaceChild("bone298", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone298_r1 = bone298.addOrReplaceChild("bone298_r1", CubeListBuilder.create().texOffs(19, 76).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -11.478F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone299 = bone298.addOrReplaceChild("bone299", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone299_r1 = bone299.addOrReplaceChild("bone299_r1", CubeListBuilder.create().texOffs(19, 76).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 3.5F, -11.478F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone255 = bone60.addOrReplaceChild("bone255", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition bone225 = bone255.addOrReplaceChild("bone225", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -4.5F, -11.728F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -78.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone226 = bone225.addOrReplaceChild("bone226", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -4.5F, -11.728F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone227 = bone226.addOrReplaceChild("bone227", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -4.5F, -11.728F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone228 = bone227.addOrReplaceChild("bone228", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -4.5F, -11.728F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone229 = bone228.addOrReplaceChild("bone229", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -4.5F, -11.728F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone230 = bone229.addOrReplaceChild("bone230", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -4.5F, -11.728F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone219 = bone255.addOrReplaceChild("bone219", CubeListBuilder.create().texOffs(36, 60).addBox(-5.0F, -4.5F, 10.625F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -78.5F, -21.285F));

        PartDefinition bone220 = bone219.addOrReplaceChild("bone220", CubeListBuilder.create().texOffs(36, 60).addBox(-5.0F, -4.5F, -10.66F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone221 = bone220.addOrReplaceChild("bone221", CubeListBuilder.create().texOffs(36, 60).addBox(-5.0F, -4.5F, -10.66F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone222 = bone221.addOrReplaceChild("bone222", CubeListBuilder.create().texOffs(36, 60).addBox(-5.0F, -4.5F, -10.66F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone223 = bone222.addOrReplaceChild("bone223", CubeListBuilder.create().texOffs(36, 60).addBox(-5.0F, -4.5F, -10.66F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone224 = bone223.addOrReplaceChild("bone224", CubeListBuilder.create().texOffs(36, 60).addBox(-5.0F, -4.5F, -10.66F, 10.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone327 = bone255.addOrReplaceChild("bone327", CubeListBuilder.create().texOffs(90, 19).addBox(-5.5F, -4.5F, 11.625F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -78.5F, -21.285F));

        PartDefinition bone328 = bone327.addOrReplaceChild("bone328", CubeListBuilder.create().texOffs(90, 19).addBox(-5.5F, -4.5F, -9.66F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone329 = bone328.addOrReplaceChild("bone329", CubeListBuilder.create().texOffs(90, 19).addBox(-5.5F, -4.5F, -9.66F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone330 = bone329.addOrReplaceChild("bone330", CubeListBuilder.create().texOffs(90, 19).addBox(-5.5F, -4.5F, -9.66F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone331 = bone330.addOrReplaceChild("bone331", CubeListBuilder.create().texOffs(90, 19).addBox(-5.5F, -4.5F, -9.66F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone332 = bone331.addOrReplaceChild("bone332", CubeListBuilder.create().texOffs(90, 19).addBox(-5.5F, -4.5F, -9.66F, 11.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone231 = bone255.addOrReplaceChild("bone231", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -75.5F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition bone232 = bone231.addOrReplaceChild("bone232", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(-2.5F, -4.0125F, -0.9783F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone232_r1 = bone232.addOrReplaceChild("bone232_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone232_r2 = bone232.addOrReplaceChild("bone232_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone233 = bone231.addOrReplaceChild("bone233", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone234 = bone233.addOrReplaceChild("bone234", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(-2.5F, -4.0125F, -0.9783F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone234_r1 = bone234.addOrReplaceChild("bone234_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone234_r2 = bone234.addOrReplaceChild("bone234_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone235 = bone233.addOrReplaceChild("bone235", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone236 = bone235.addOrReplaceChild("bone236", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(-2.5F, -4.0125F, -0.9783F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone236_r1 = bone236.addOrReplaceChild("bone236_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone236_r2 = bone236.addOrReplaceChild("bone236_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone237 = bone235.addOrReplaceChild("bone237", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone238 = bone237.addOrReplaceChild("bone238", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(-2.5F, -4.0125F, -0.9783F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone238_r1 = bone238.addOrReplaceChild("bone238_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone238_r2 = bone238.addOrReplaceChild("bone238_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone239 = bone237.addOrReplaceChild("bone239", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone240 = bone239.addOrReplaceChild("bone240", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(-2.5F, -4.0125F, -0.9783F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone240_r1 = bone240.addOrReplaceChild("bone240_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone240_r2 = bone240.addOrReplaceChild("bone240_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone241 = bone239.addOrReplaceChild("bone241", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone242 = bone241.addOrReplaceChild("bone242", CubeListBuilder.create().texOffs(36, 75).addBox(-5.0F, -6.0125F, 0.0217F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(142, 204).addBox(-2.5F, -4.0125F, -0.9783F, 5.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -10.56F, -0.5236F, 0.0F, 0.0F));

        PartDefinition bone242_r1 = bone242.addOrReplaceChild("bone242_r1", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1833F));

        PartDefinition bone242_r2 = bone242.addOrReplaceChild("bone242_r2", CubeListBuilder.create().texOffs(7, 31).mirror().addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1833F));

        PartDefinition bone68 = console.addOrReplaceChild("bone68", CubeListBuilder.create().texOffs(176, 85).addBox(-7.0F, 1.5F, 8.175F, 14.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.5F, -21.285F));

        PartDefinition bone69 = bone68.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(176, 85).addBox(-7.0F, 1.5F, -13.11F, 14.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone70 = bone69.addOrReplaceChild("bone70", CubeListBuilder.create().texOffs(176, 85).addBox(-7.0F, 1.5F, -13.11F, 14.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone71 = bone70.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(176, 85).addBox(-7.0F, 1.5F, -13.11F, 14.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone72 = bone71.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(176, 85).addBox(-7.0F, 1.5F, -13.11F, 14.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone73 = bone72.addOrReplaceChild("bone73", CubeListBuilder.create().texOffs(176, 85).addBox(-7.0F, 1.5F, -13.11F, 14.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone92 = console.addOrReplaceChild("bone92", CubeListBuilder.create().texOffs(16, 53).addBox(-0.5F, 0.5F, -14.85F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone93 = bone92.addOrReplaceChild("bone93", CubeListBuilder.create().texOffs(16, 53).addBox(-0.5F, 0.5F, -14.85F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone94 = bone93.addOrReplaceChild("bone94", CubeListBuilder.create().texOffs(16, 53).addBox(-0.5F, 0.5F, -14.85F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone95 = bone94.addOrReplaceChild("bone95", CubeListBuilder.create().texOffs(16, 53).addBox(-0.5F, 0.5F, -14.85F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone96 = bone95.addOrReplaceChild("bone96", CubeListBuilder.create().texOffs(16, 53).addBox(-0.5F, 0.5F, -14.85F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone97 = bone96.addOrReplaceChild("bone97", CubeListBuilder.create().texOffs(16, 53).addBox(-0.5F, 0.5F, -14.85F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone333 = console.addOrReplaceChild("bone333", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone333_r1 = bone333.addOrReplaceChild("bone333_r1", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -11.1F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bone334 = bone333.addOrReplaceChild("bone334", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone334_r1 = bone334.addOrReplaceChild("bone334_r1", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -11.1F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bone335 = bone334.addOrReplaceChild("bone335", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone335_r1 = bone335.addOrReplaceChild("bone335_r1", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -11.1F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bone336 = bone335.addOrReplaceChild("bone336", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone336_r1 = bone336.addOrReplaceChild("bone336_r1", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -11.1F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bone337 = bone336.addOrReplaceChild("bone337", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone337_r1 = bone337.addOrReplaceChild("bone337_r1", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -11.1F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bone338 = bone337.addOrReplaceChild("bone338", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone338_r1 = bone338.addOrReplaceChild("bone338_r1", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -11.1F, -0.0873F, 0.0F, 0.0F));

        PartDefinition bone74 = console.addOrReplaceChild("bone74", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -21.285F));

        PartDefinition bone74_r1 = bone74.addOrReplaceChild("bone74_r1", CubeListBuilder.create().texOffs(139, 39).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 11.625F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone75 = bone74.addOrReplaceChild("bone75", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone75_r1 = bone75.addOrReplaceChild("bone75_r1", CubeListBuilder.create().texOffs(139, 39).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -9.66F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone76 = bone75.addOrReplaceChild("bone76", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone76_r1 = bone76.addOrReplaceChild("bone76_r1", CubeListBuilder.create().texOffs(139, 39).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -9.66F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone77 = bone76.addOrReplaceChild("bone77", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone77_r1 = bone77.addOrReplaceChild("bone77_r1", CubeListBuilder.create().texOffs(139, 39).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -9.66F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone78 = bone77.addOrReplaceChild("bone78", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone78_r1 = bone78.addOrReplaceChild("bone78_r1", CubeListBuilder.create().texOffs(139, 39).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -9.66F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone79 = bone78.addOrReplaceChild("bone79", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone79_r1 = bone79.addOrReplaceChild("bone79_r1", CubeListBuilder.create().texOffs(139, 39).addBox(-5.0F, -9.0F, -1.0F, 10.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -9.66F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone339 = console.addOrReplaceChild("bone339", CubeListBuilder.create().texOffs(160, 42).addBox(-5.0F, 2.5F, 11.625F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -21.285F));

        PartDefinition bone340 = bone339.addOrReplaceChild("bone340", CubeListBuilder.create().texOffs(160, 42).addBox(-5.0F, 2.5F, -9.66F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone341 = bone340.addOrReplaceChild("bone341", CubeListBuilder.create().texOffs(160, 42).addBox(-5.0F, 2.5F, -9.66F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone342 = bone341.addOrReplaceChild("bone342", CubeListBuilder.create().texOffs(160, 42).addBox(-5.0F, 2.5F, -9.66F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone343 = bone342.addOrReplaceChild("bone343", CubeListBuilder.create().texOffs(160, 42).addBox(-5.0F, 2.5F, -9.66F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone344 = bone343.addOrReplaceChild("bone344", CubeListBuilder.create().texOffs(160, 42).addBox(-5.0F, 2.5F, -9.66F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone218 = console.addOrReplaceChild("bone218", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 0.0F));

        PartDefinition bone62 = bone218.addOrReplaceChild("bone62", CubeListBuilder.create(), PartPose.offset(0.0F, -13.5F, -21.285F));

        PartDefinition bone62_r1 = bone62.addOrReplaceChild("bone62_r1", CubeListBuilder.create().texOffs(23, 202).addBox(-7.25F, 0.0F, 0.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -5.0F, 7.835F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone63 = bone62.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone63_r1 = bone63.addOrReplaceChild("bone63_r1", CubeListBuilder.create().texOffs(0, 31).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.6042F, -15.4837F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone64 = bone63.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone64_r1 = bone64.addOrReplaceChild("bone64_r1", CubeListBuilder.create().texOffs(0, 31).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.6042F, -15.4837F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone65 = bone64.addOrReplaceChild("bone65", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66 = bone65.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone66_r1 = bone66.addOrReplaceChild("bone66_r1", CubeListBuilder.create().texOffs(0, 31).addBox(-9.0F, 0.0F, 0.0F, 18.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.6042F, -15.4837F, -0.2618F, 0.0F, 0.0F));

        PartDefinition bone67 = bone66.addOrReplaceChild("bone67", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone67_r1 = bone67.addOrReplaceChild("bone67_r1", CubeListBuilder.create().texOffs(23, 202).mirror().addBox(-6.75F, 0.0F, 0.0F, 14.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, -5.0F, -13.45F, -0.0044F, 0.0F, 0.0F));

        PartDefinition bone8 = bone218.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(0.0F, -13.5F, -21.285F));

        PartDefinition bone295 = bone8.addOrReplaceChild("bone295", CubeListBuilder.create().texOffs(134, 24).addBox(-14.5F, 0.0F, 0.0F, 29.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -4.455F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone295_r1 = bone295.addOrReplaceChild("bone295_r1", CubeListBuilder.create().texOffs(73, 82).mirror().addBox(-1.5F, 0.025F, 11.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 82).mirror().addBox(-1.5F, 0.025F, 4.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 0.0F, -0.48F, 0.0F));

        PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 21.285F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone9_r1 = bone9.addOrReplaceChild("bone9_r1", CubeListBuilder.create().texOffs(161, 62).addBox(-14.5F, 0.0F, 0.0F, 29.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone10_r1 = bone10.addOrReplaceChild("bone10_r1", CubeListBuilder.create().texOffs(161, 62).mirror().addBox(-14.5F, 0.0F, 0.0F, 29.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone11 = bone10.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone274 = bone11.addOrReplaceChild("bone274", CubeListBuilder.create().texOffs(134, 24).addBox(-14.5F, 0.0F, 0.0F, 29.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone274_r1 = bone274.addOrReplaceChild("bone274_r1", CubeListBuilder.create().texOffs(73, 82).mirror().addBox(-1.5F, 0.025F, 4.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(73, 82).mirror().addBox(-1.5F, 0.025F, 11.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(14.5F, 0.0F, 0.0F, 0.0F, -0.48F, 0.0F));

        PartDefinition bone274_r2 = bone274.addOrReplaceChild("bone274_r2", CubeListBuilder.create().texOffs(73, 82).addBox(-0.5F, 0.025F, 11.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(73, 82).addBox(-0.5F, 0.025F, 4.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

        PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone12_r1 = bone12.addOrReplaceChild("bone12_r1", CubeListBuilder.create().texOffs(0, 18).addBox(-14.5F, 0.0F, 0.0F, 29.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone13 = bone12.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone288 = bone13.addOrReplaceChild("bone288", CubeListBuilder.create().texOffs(134, 24).addBox(-14.5F, 0.0F, 0.0F, 29.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone288_r1 = bone288.addOrReplaceChild("bone288_r1", CubeListBuilder.create().texOffs(73, 82).addBox(-0.5F, 0.025F, 11.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(73, 82).addBox(-0.5F, 0.025F, 4.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

        PartDefinition bone14 = bone218.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone14_r1 = bone14.addOrReplaceChild("bone14_r1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -29.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone313 = bone15.addOrReplaceChild("bone313", CubeListBuilder.create().texOffs(40, 231).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(38, 245).addBox(-2.5F, 0.75F, 6.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(17, 240).addBox(-3.0F, 0.0F, 5.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -29.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone313_r1 = bone313.addOrReplaceChild("bone313_r1", CubeListBuilder.create().texOffs(13, 223).mirror().addBox(0.0F, 0.125F, 0.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 0.0F, 6.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition bone313_r2 = bone313.addOrReplaceChild("bone313_r2", CubeListBuilder.create().texOffs(13, 223).addBox(-1.0F, 0.125F, 0.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 6.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition bone16 = bone15.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone16_r1 = bone16.addOrReplaceChild("bone16_r1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -29.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone17_r1 = bone17.addOrReplaceChild("bone17_r1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -29.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone18 = bone17.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone18_r1 = bone18.addOrReplaceChild("bone18_r1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -29.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone19 = bone18.addOrReplaceChild("bone19", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone19_r1 = bone19.addOrReplaceChild("bone19_r1", CubeListBuilder.create().texOffs(38, 128).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -25.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone19_r2 = bone19.addOrReplaceChild("bone19_r2", CubeListBuilder.create().texOffs(40, 231).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -29.866F, 0.3403F, 0.0F, 0.0F));

        PartDefinition bone119 = bone218.addOrReplaceChild("bone119", CubeListBuilder.create().texOffs(0, 45).addBox(-15.0F, 1.0F, -26.98F, 30.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-14.5F, -1.0F, -26.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone126 = bone119.addOrReplaceChild("bone126", CubeListBuilder.create().texOffs(0, 45).addBox(-15.0F, 1.0F, -26.98F, 30.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-14.5F, -1.0F, -26.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone127 = bone126.addOrReplaceChild("bone127", CubeListBuilder.create().texOffs(0, 45).addBox(-15.0F, 1.0F, -26.98F, 30.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-14.5F, -1.0F, -26.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone128 = bone127.addOrReplaceChild("bone128", CubeListBuilder.create().texOffs(0, 45).addBox(-15.0F, 1.0F, -26.98F, 30.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-14.5F, -1.0F, -26.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone129 = bone128.addOrReplaceChild("bone129", CubeListBuilder.create().texOffs(0, 45).addBox(-15.0F, 1.0F, -26.98F, 30.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-14.5F, -1.0F, -26.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone130 = bone129.addOrReplaceChild("bone130", CubeListBuilder.create().texOffs(0, 45).addBox(-15.0F, 1.0F, -26.98F, 30.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 49).addBox(-14.5F, -1.0F, -26.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone61 = bone218.addOrReplaceChild("bone61", CubeListBuilder.create().texOffs(0, 88).addBox(-14.5F, 0.0F, -25.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone269 = bone61.addOrReplaceChild("bone269", CubeListBuilder.create().texOffs(0, 88).addBox(-14.5F, 0.0F, -25.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone270 = bone269.addOrReplaceChild("bone270", CubeListBuilder.create().texOffs(0, 88).addBox(-14.5F, 0.0F, -25.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone271 = bone270.addOrReplaceChild("bone271", CubeListBuilder.create().texOffs(0, 88).addBox(-14.5F, 0.0F, -25.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone272 = bone271.addOrReplaceChild("bone272", CubeListBuilder.create().texOffs(0, 88).addBox(-14.5F, 0.0F, -25.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone273 = bone272.addOrReplaceChild("bone273", CubeListBuilder.create().texOffs(0, 88).addBox(-14.5F, 0.0F, -25.115F, 29.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone80 = bone218.addOrReplaceChild("bone80", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition bone80_r1 = bone80.addOrReplaceChild("bone80_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -1.0F, 0.0F, 30.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -26.73F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone81 = bone80.addOrReplaceChild("bone81", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone81_r1 = bone81.addOrReplaceChild("bone81_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -1.0F, 0.0F, 30.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -26.73F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone82 = bone81.addOrReplaceChild("bone82", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone82_r1 = bone82.addOrReplaceChild("bone82_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -1.0F, 0.0F, 30.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -26.73F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone83 = bone82.addOrReplaceChild("bone83", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone83_r1 = bone83.addOrReplaceChild("bone83_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -1.0F, 0.0F, 30.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -26.73F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone84 = bone83.addOrReplaceChild("bone84", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone84_r1 = bone84.addOrReplaceChild("bone84_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -1.0F, 0.0F, 30.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -26.73F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone85 = bone84.addOrReplaceChild("bone85", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone85_r1 = bone85.addOrReplaceChild("bone85_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -1.0F, 0.0F, 30.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, -26.73F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone2 = bone218.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(10, 8).addBox(-0.5F, -1.0F, -29.866F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 39).addBox(-0.5F, 1.0F, -30.865F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(10, 8).addBox(-0.5F, -1.0F, -29.866F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 39).addBox(-0.5F, 1.0F, -30.865F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(10, 8).addBox(-0.5F, -1.0F, -29.866F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 39).addBox(-0.5F, 1.0F, -30.865F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone5 = bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(10, 8).addBox(-0.5F, -1.0F, -29.866F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 39).addBox(-0.5F, 1.0F, -30.865F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(10, 8).addBox(-0.5F, -1.0F, -29.866F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 39).addBox(-0.5F, 1.0F, -30.865F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(10, 8).addBox(-0.5F, -1.0F, -29.866F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 39).addBox(-0.5F, 1.0F, -30.865F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone86 = bone218.addOrReplaceChild("bone86", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition bone86_r1 = bone86.addOrReplaceChild("bone86_r1", CubeListBuilder.create().texOffs(47, 35).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -30.865F, -0.2094F, 0.0F, 0.0F));

        PartDefinition bone87 = bone86.addOrReplaceChild("bone87", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone87_r1 = bone87.addOrReplaceChild("bone87_r1", CubeListBuilder.create().texOffs(47, 35).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -30.865F, -0.2094F, 0.0F, 0.0F));

        PartDefinition bone88 = bone87.addOrReplaceChild("bone88", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone88_r1 = bone88.addOrReplaceChild("bone88_r1", CubeListBuilder.create().texOffs(47, 35).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -30.865F, -0.2094F, 0.0F, 0.0F));

        PartDefinition bone89 = bone88.addOrReplaceChild("bone89", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone89_r1 = bone89.addOrReplaceChild("bone89_r1", CubeListBuilder.create().texOffs(47, 35).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -30.865F, -0.2094F, 0.0F, 0.0F));

        PartDefinition bone90 = bone89.addOrReplaceChild("bone90", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone90_r1 = bone90.addOrReplaceChild("bone90_r1", CubeListBuilder.create().texOffs(47, 35).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -30.865F, -0.2094F, 0.0F, 0.0F));

        PartDefinition bone91 = bone90.addOrReplaceChild("bone91", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone91_r1 = bone91.addOrReplaceChild("bone91_r1", CubeListBuilder.create().texOffs(47, 35).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -30.865F, -0.2094F, 0.0F, 0.0F));

        PartDefinition bone276 = root.addOrReplaceChild("bone276", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone277 = bone276.addOrReplaceChild("bone277", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.25F, 0.0F, 0.0F, -1.0472F, 0.0F));

        PartDefinition bone278 = bone277.addOrReplaceChild("bone278", CubeListBuilder.create().texOffs(91, 156).addBox(-12.5F, -0.1F, 0.5F, 25.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(98, 166).addBox(-2.5F, -0.2F, 5.75F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(-8.0F, -0.6F, 5.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(-5.5F, -1.6F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 122).addBox(-10.0F, -0.6F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F))
                .texOffs(107, 134).addBox(-11.25F, -2.35F, 1.75F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 122).mirror().addBox(9.0F, -0.6F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(101, 122).mirror().addBox(8.5F, -2.1F, 3.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(107, 134).mirror().addBox(8.25F, -2.35F, 1.75F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(50, 121).addBox(-9.5F, -0.6F, 3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(61, 133).addBox(-4.5F, -0.6F, 2.5F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(-8.25F, -1.85F, 6.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(-9.75F, -1.85F, 3.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(-4.75F, -1.85F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).mirror().addBox(2.75F, -1.85F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 9).mirror().addBox(0.75F, -1.85F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 9).addBox(-2.75F, -1.85F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(-0.75F, -1.85F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).mirror().addBox(6.25F, -1.85F, 6.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(101, 122).mirror().addBox(7.0F, -0.6F, 5.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(99, 149).addBox(-6.5F, -1.1F, 6.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(88, 152).addBox(-6.5F, -2.1F, 8.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.025F))
                .texOffs(88, 152).mirror().addBox(3.5F, -2.1F, 8.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.025F)).mirror(false)
                .texOffs(101, 122).mirror().addBox(4.5F, -1.6F, 6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(99, 149).mirror().addBox(3.5F, -1.1F, 6.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(99, 149).mirror().addBox(3.5F, -1.1F, 6.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(110, 147).mirror().addBox(-1.0F, -1.6F, 7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone316 = bone278.addOrReplaceChild("bone316", CubeListBuilder.create().texOffs(20, 151).mirror().addBox(-1.0F, -3.5F, -3.25F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(15, 153).mirror().addBox(-0.5F, -3.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, -2.1F, 3.75F, -0.3491F, 0.3491F, 0.0F));

        PartDefinition bone279 = bone276.addOrReplaceChild("bone279", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.25F, 0.0F, 0.0F, -2.0944F, 0.0F));

        PartDefinition bone280 = bone279.addOrReplaceChild("bone280", CubeListBuilder.create().texOffs(91, 169).addBox(-12.5F, -0.1F, 0.5F, 25.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(72, 165).addBox(3.5F, -0.2F, 1.25F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 198).addBox(-9.75F, -0.6F, 1.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 121).addBox(-5.25F, -0.6F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 121).addBox(-7.25F, -0.6F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(57, 118).addBox(6.25F, -1.6F, 5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(57, 118).addBox(4.25F, -1.6F, 5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(-5.5F, -2.1F, 5.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(-7.5F, -2.1F, 5.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(6.0F, -3.1F, 6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(4.0F, -3.1F, 6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 128).addBox(-1.0F, -1.1F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(40, 128).addBox(-3.0F, -1.1F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(40, 128).addBox(1.0F, -1.1F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(72, 191).addBox(-3.0F, -2.1F, 3.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone310 = bone280.addOrReplaceChild("bone310", CubeListBuilder.create().texOffs(76, 187).addBox(5.0F, -16.1F, -19.24F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 187).addBox(2.0F, -16.1F, -19.24F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 187).addBox(-1.0F, -16.1F, -19.24F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 187).addBox(-4.0F, -16.1F, -19.24F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(76, 187).addBox(-7.0F, -16.1F, -19.24F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.25F, 26.74F));

        PartDefinition throttle = bone280.addOrReplaceChild("throttle", CubeListBuilder.create().texOffs(75, 198).addBox(-2.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(75, 198).mirror().addBox(1.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.1F, 5.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition monitor = bone279.addOrReplaceChild("monitor", CubeListBuilder.create().texOffs(86, 135).addBox(-3.5F, -5.0F, -3.25F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(87, 142).addBox(-1.0F, -4.0F, -1.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(84, 126).addBox(-5.0F, -6.0F, -3.225F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -6.0F, -8.75F, -0.1745F, 0.2618F, 0.0F));

        PartDefinition bone358 = monitor.addOrReplaceChild("bone358", CubeListBuilder.create().texOffs(83, 134).addBox(0.0F, -0.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -4.25F, -3.275F));

        PartDefinition bone359 = monitor.addOrReplaceChild("bone359", CubeListBuilder.create().texOffs(83, 134).addBox(0.0F, -0.75F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -3.25F, -3.275F));

        PartDefinition bone311 = monitor.addOrReplaceChild("bone311", CubeListBuilder.create().texOffs(141, 187).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(104, 123).addBox(1.5F, -0.5F, -2.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 123).addBox(-3.5F, -0.5F, -2.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -3.225F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone281 = bone276.addOrReplaceChild("bone281", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.25F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition bone304 = bone281.addOrReplaceChild("bone304", CubeListBuilder.create().texOffs(2, 101).addBox(-2.5F, -19.5F, -21.24F, 5.0F, 4.0F, 5.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 13.75F, 2.0F));

        PartDefinition bone151 = bone304.addOrReplaceChild("bone151", CubeListBuilder.create(), PartPose.offset(0.0F, -19.25F, -18.74F));

        PartDefinition bone151_r1 = bone151.addOrReplaceChild("bone151_r1", CubeListBuilder.create().texOffs(0, 136).addBox(-2.0F, -0.75F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0436F, 0.0F));

        PartDefinition bone283 = bone281.addOrReplaceChild("bone283", CubeListBuilder.create().texOffs(11, 172).addBox(-14.5F, 2.0F, 3.0F, 29.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(0, 161).addBox(-8.0F, 0.25F, 8.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 161).mirror().addBox(4.0F, 0.25F, 8.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(2, 117).addBox(-6.0F, -0.1F, -0.25F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(2, 123).addBox(-5.5F, -0.85F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 130).addBox(-4.0F, -0.2F, 1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(2, 123).addBox(2.5F, -0.85F, 1.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone283_r1 = bone283.addOrReplaceChild("bone283_r1", CubeListBuilder.create().texOffs(1, 133).addBox(-2.5F, -0.5F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.2F, 2.5F, -0.48F, 0.0F, 0.0F));

        PartDefinition bone302 = bone283.addOrReplaceChild("bone302", CubeListBuilder.create().texOffs(13, 135).addBox(-6.0F, -15.35F, -24.99F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, 15.25F, 25.74F));

        PartDefinition bone302_r1 = bone302.addOrReplaceChild("bone302_r1", CubeListBuilder.create().texOffs(25, 138).addBox(-1.0F, -0.75F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-4.5F, -16.1F, -23.49F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone303 = bone283.addOrReplaceChild("bone303", CubeListBuilder.create().texOffs(13, 135).mirror().addBox(3.0F, -15.35F, -24.99F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.5F, 15.25F, 25.74F));

        PartDefinition bone303_r1 = bone303.addOrReplaceChild("bone303_r1", CubeListBuilder.create().texOffs(25, 138).mirror().addBox(-1.0F, -0.75F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(4.5F, -16.1F, -23.49F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bone284 = bone283.addOrReplaceChild("bone284", CubeListBuilder.create().texOffs(21, 129).mirror().addBox(-8.5F, -1.101F, 5.9137F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(5, 123).addBox(-9.5F, -0.1F, 2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 0.0F, -0.5F));

        PartDefinition bone284_r1 = bone284.addOrReplaceChild("bone284_r1", CubeListBuilder.create().texOffs(9, 127).addBox(-1.0F, -2.25F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 122).addBox(-1.5F, -2.75F, 0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -0.1F, 2.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone300 = bone284.addOrReplaceChild("bone300", CubeListBuilder.create(), PartPose.offset(-8.0F, -0.2901F, 3.1366F));

        PartDefinition bone285 = bone283.addOrReplaceChild("bone285", CubeListBuilder.create().texOffs(21, 129).addBox(-8.5F, -1.101F, 5.9137F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.25F))
                .texOffs(5, 123).addBox(-9.5F, -0.1F, 2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(13.75F, 0.0F, -0.5F));

        PartDefinition bone285_r1 = bone285.addOrReplaceChild("bone285_r1", CubeListBuilder.create().texOffs(9, 127).addBox(-1.0F, -2.25F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 122).addBox(-1.5F, -2.75F, 0.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -0.1F, 2.5F, -0.5672F, 0.0F, 0.0F));

        PartDefinition bone301 = bone285.addOrReplaceChild("bone301", CubeListBuilder.create(), PartPose.offset(-8.0F, -0.2901F, 3.1366F));

        PartDefinition bone286 = bone276.addOrReplaceChild("bone286", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.25F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition bone287 = bone286.addOrReplaceChild("bone287", CubeListBuilder.create().texOffs(32, 110).addBox(-12.5F, -0.1F, 0.5F, 25.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(33, 133).addBox(-8.75F, -0.125F, 0.25F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(41, 148).addBox(0.0F, -1.125F, 0.75F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(30, 148).addBox(-2.5F, -0.625F, 0.75F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(33, 120).addBox(5.0F, -1.1F, 4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 118).addBox(10.5F, -1.1F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 128).addBox(-12.5F, -1.1F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(49, 118).addBox(-9.5F, -0.6F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 121).addBox(-7.0F, -0.6F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 128).addBox(-11.5F, -1.1F, 3.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 124).addBox(7.5F, -0.6F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 123).addBox(8.5F, -0.6F, 2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 123).addBox(8.0F, -0.6F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 123).addBox(7.0F, -0.6F, 2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 123).addBox(6.5F, -0.6F, 1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 126).addBox(2.0F, -0.125F, 0.75F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(33, 120).addBox(2.5F, -1.1F, 4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(39, 123).addBox(-8.5F, -1.0F, 7.0F, 17.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(23, 145).addBox(5.5F, -1.75F, 7.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(30, 144).addBox(4.0F, -1.5F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 144).addBox(0.0F, -1.5F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 145).addBox(-2.5F, -1.75F, 7.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 144).addBox(-2.0F, -2.75F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 144).addBox(6.0F, -2.75F, 8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 149).addBox(-7.5F, -1.1F, 7.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone305 = bone287.addOrReplaceChild("bone305", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.5F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -0.6F, 2.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition bone306 = bone287.addOrReplaceChild("bone306", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.5F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, -1.6F, 4.5F, 0.0F, -1.5272F, 0.0F));

        PartDefinition bone307 = bone287.addOrReplaceChild("bone307", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.5F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, -1.1F, 2.0F, 0.0F, 0.9599F, 0.0F));

        PartDefinition bone308 = bone287.addOrReplaceChild("bone308", CubeListBuilder.create().texOffs(20, 151).addBox(0.0F, -3.0F, -3.5F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(15, 153).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -1.1F, 8.5F, -0.5236F, -0.5672F, 0.0F));

        PartDefinition bone292 = bone276.addOrReplaceChild("bone292", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.25F, 0.0F, 0.0F, 1.0472F, 0.0F));

        PartDefinition bone293 = bone292.addOrReplaceChild("bone293", CubeListBuilder.create().texOffs(117, 129).addBox(-12.5F, -0.1F, 0.5F, 25.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(15, 186).addBox(-10.5F, 1.75F, 4.0F, 21.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(154, 103).addBox(-12.0F, -1.5F, 0.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(154, 103).addBox(9.0F, -1.5F, 0.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(5.5F, -0.6F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(3.75F, -0.6F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(2.0F, -0.6F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(5.25F, -1.85F, 2.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(3.5F, -1.85F, 2.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(98, 9).addBox(1.75F, -1.85F, 2.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 153).addBox(-6.5F, -0.85F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 153).addBox(-4.75F, -0.85F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(15, 153).addBox(-3.0F, -0.85F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 147).addBox(0.25F, -0.85F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(5, 147).addBox(-1.25F, -0.85F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(11, 172).addBox(-14.5F, 2.0F, 3.0F, 29.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone317 = bone293.addOrReplaceChild("bone317", CubeListBuilder.create().texOffs(7, 200).addBox(-2.0F, 0.1F, -1.5F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(120, 123).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(137, 122).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5F, -1.35F, 5.5F, 0.0F, -0.1745F, 0.0F));

        PartDefinition bone317_r1 = bone317.addOrReplaceChild("bone317_r1", CubeListBuilder.create().texOffs(146, 119).addBox(0.0F, -1.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.5F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone317_r2 = bone317.addOrReplaceChild("bone317_r2", CubeListBuilder.create().texOffs(6, 203).addBox(-1.0F, -0.555F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.088F)), PartPose.offsetAndRotation(0.0042F, 0.518F, 0.0429F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone318 = bone293.addOrReplaceChild("bone318", CubeListBuilder.create().texOffs(7, 200).addBox(-2.0F, 0.1F, -1.5F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(112, 123).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(137, 122).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.85F, 8.25F, 2.9664F, -0.0859F, -3.1264F));

        PartDefinition bone318_r1 = bone318.addOrReplaceChild("bone318_r1", CubeListBuilder.create().texOffs(146, 119).addBox(0.0F, -1.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.5F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone318_r2 = bone318.addOrReplaceChild("bone318_r2", CubeListBuilder.create().texOffs(6, 203).addBox(-1.0F, -0.555F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.088F)), PartPose.offsetAndRotation(0.0042F, 0.518F, 0.0429F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone319 = bone293.addOrReplaceChild("bone319", CubeListBuilder.create().texOffs(7, 200).addBox(-2.0F, 0.1F, -1.75F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(128, 123).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(137, 122).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.35F, 7.25F, 0.3491F, -0.2618F, 0.0F));

        PartDefinition bone319_r1 = bone319.addOrReplaceChild("bone319_r1", CubeListBuilder.create().texOffs(146, 119).addBox(0.0F, -1.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.5F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone319_r2 = bone319.addOrReplaceChild("bone319_r2", CubeListBuilder.create().texOffs(6, 203).addBox(-1.0F, -0.555F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.088F)), PartPose.offsetAndRotation(0.0042F, 0.518F, -0.2071F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone320 = bone293.addOrReplaceChild("bone320", CubeListBuilder.create().texOffs(7, 200).addBox(-2.0F, 0.1F, -1.5F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(112, 123).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.25F))
                .texOffs(137, 122).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -1.35F, 5.5F, 2.9671F, 0.0F, 3.1416F));

        PartDefinition bone320_r1 = bone320.addOrReplaceChild("bone320_r1", CubeListBuilder.create().texOffs(146, 119).addBox(0.0F, -1.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.25F, 0.1309F, 0.0F, 0.0F));

        PartDefinition bone320_r2 = bone320.addOrReplaceChild("bone320_r2", CubeListBuilder.create().texOffs(6, 203).addBox(-1.0F, -0.555F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.088F)), PartPose.offsetAndRotation(0.0042F, 0.518F, 0.0429F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone294 = bone292.addOrReplaceChild("bone294", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.5F, -19.24F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone289 = bone276.addOrReplaceChild("bone289", CubeListBuilder.create(), PartPose.offset(0.0F, -15.25F, 0.0F));

        PartDefinition bone290 = bone289.addOrReplaceChild("bone290", CubeListBuilder.create().texOffs(99, 117).addBox(-12.5F, -0.1F, 0.5F, 25.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(-8.0F, -0.6F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(-6.5F, -0.6F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(101, 122).addBox(-5.0F, -0.6F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(104, 123).addBox(-0.5F, -0.6F, 1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 123).addBox(4.0F, -0.6F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(104, 123).addBox(6.5F, -0.6F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(107, 127).addBox(6.5F, -0.6F, 4.75F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(107, 138).addBox(-3.5F, 0.65F, 4.75F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(107, 134).addBox(5.75F, -2.1F, 5.75F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 172).addBox(-14.5F, 2.0F, 3.0F, 29.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(15, 186).addBox(-10.5F, 1.75F, 4.0F, 21.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -25.74F, 0.3927F, 0.0F, 0.0F));

        PartDefinition bone290_r1 = bone290.addOrReplaceChild("bone290_r1", CubeListBuilder.create().texOffs(98, 9).addBox(-0.75F, -1.25F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -0.35F, 1.75F, 0.0F, 0.5236F, 0.0F));

        PartDefinition bone290_r2 = bone290.addOrReplaceChild("bone290_r2", CubeListBuilder.create().texOffs(110, 108).addBox(-1.0F, -6.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(111, 103).addBox(-0.5F, -7.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(99, 109).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 3.9F, 11.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition bone291 = bone289.addOrReplaceChild("bone291", CubeListBuilder.create().texOffs(35, 96).addBox(-3.5F, -2.0F, -2.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(65, 81).mirror().addBox(5.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(65, 81).addBox(-4.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(69, 98).addBox(-7.5F, -1.75F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(69, 98).mirror().addBox(6.5F, -1.75F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 94).addBox(0.0F, -1.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(97, 99).addBox(2.75F, -2.25F, -2.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(97, 99).addBox(-2.75F, -2.25F, -2.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.5F, -18.24F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone360 = bone291.addOrReplaceChild("bone360", CubeListBuilder.create().texOffs(27, 98).addBox(1.5F, -1.0F, 0.02F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 98).mirror().addBox(-3.5F, -1.0F, 0.02F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 1.0F, -2.025F));

        PartDefinition bone361 = bone291.addOrReplaceChild("bone361", CubeListBuilder.create().texOffs(27, 98).addBox(1.5F, -1.0F, 0.02F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 98).mirror().addBox(-3.5F, -1.0F, 0.02F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -0.5F, -2.025F));

        PartDefinition bone362 = bone291.addOrReplaceChild("bone362", CubeListBuilder.create().texOffs(27, 98).addBox(1.5F, -1.0F, -0.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 98).mirror().addBox(-3.5F, -1.0F, -0.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -2.0F, -2.025F, -1.5708F, 0.0F, 0.0F));

        PartDefinition bone363 = bone291.addOrReplaceChild("bone363", CubeListBuilder.create().texOffs(27, 98).addBox(1.5F, -1.0F, -0.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 98).mirror().addBox(-3.5F, -1.0F, -0.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -2.0F, -0.525F, -1.5708F, 0.0F, 0.0F));

        PartDefinition bone364 = bone291.addOrReplaceChild("bone364", CubeListBuilder.create().texOffs(27, 100).addBox(1.5F, -1.0F, -0.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 100).mirror().addBox(-3.5F, -1.0F, -0.025F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -2.0F, 0.975F, -1.5708F, 0.0F, 0.0F));

        PartDefinition bone309 = bone291.addOrReplaceChild("bone309", CubeListBuilder.create().texOffs(67, 88).mirror().addBox(-0.5F, -5.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.475F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition bone314 = bone276.addOrReplaceChild("bone314", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.25F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone314_r1 = bone314.addOrReplaceChild("bone314_r1", CubeListBuilder.create().texOffs(73, 216).addBox(-1.5F, -0.75F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -3.1F, -22.24F, 0.0F, -0.7854F, 0.0F));

        PartDefinition bone315 = bone314.addOrReplaceChild("bone315", CubeListBuilder.create().texOffs(128, 217).addBox(0.25F, -4.25F, -1.75F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -4.1F, -22.24F));

        PartDefinition bone315_r1 = bone315.addOrReplaceChild("bone315_r1", CubeListBuilder.create().texOffs(128, 217).addBox(0.25F, -2.0F, -1.75F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -2.25F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
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
        throttle.xRot = rot;

        handbrake.xRot = reactions.isHandbrakeEngaged() ? 1f : 0f;

        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ResourceLocation getDefaultTexture() {
        return REFURBISHED_TEXTURE;
    }

    @Override
    public ResourceLocation getConsoleTheme() {
        return ConsoleTheme.REFURBISHED.getId();
    }

    @Override
    public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

    }
}