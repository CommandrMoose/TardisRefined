package whocraft.tardis_refined.client.model.blockentity.door;// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;

public class GrowthDoorModel extends ShellModel {
	private final ModelPart root;
	private final ModelPart door_closed;
	private final ModelPart door_open;
	private final ModelPart bb_main;

	public GrowthDoorModel(ModelPart root) {
		super(root);
		this.root = root;
		this.door_closed = root.getChild("door_closed");
		this.door_open = root.getChild("door_open");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition door_closed = partdefinition.addOrReplaceChild("door_closed", CubeListBuilder.create().texOffs(33, 46).mirror().addBox(7.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 46).addBox(11.5F, -32.0F, -12.0F, 15.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(33, 46).addBox(26.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.0F, 24.0F, 19.55F));

		PartDefinition door_open = partdefinition.addOrReplaceChild("door_open", CubeListBuilder.create().texOffs(33, 80).mirror().addBox(7.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 80).addBox(11.5F, -32.0F, -12.0F, 15.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(33, 80).addBox(26.5F, -32.0F, -12.0F, 4.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.0F, 24.0F, 19.55F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(44, 46).addBox(-5.5F, -34.0F, 7.55F, 11.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-15.5F, -44.0F, 7.825F, 31.0F, 44.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		ShellModel.splice(partdefinition);

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	boolean isDoorOpen = false;
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

		door_closed.visible = !isDoorOpen;
		door_open.visible = isDoorOpen;

		door_closed.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		door_open.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setDoorPosition(boolean open) {
		this.isDoorOpen = open;
	}

	@Override
	public void renderShell(GlobalShellBlockEntity entity, boolean open, boolean isBaseModel, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

	}

	@Override
	public ResourceLocation texture() {
		return ShellTheme.GROWTH.getInternalDoorTexture();
	}

	@Override
	public ResourceLocation lightTexture() {
		return null;
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}
}