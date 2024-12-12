package whocraft.tardis_refined.client.screen.ponder;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingIngredient;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipe;

import java.awt.*;

public class PonderScreen extends Screen {

    private final ManipulatorCraftingRecipe recipe;
    private final int xSize, ySize, zSize;
    private int age = 0;

    public PonderScreen(ManipulatorCraftingRecipe recipe) {
        super(Component.literal(recipe.getId().toString()));
        this.recipe = recipe;
        int minX = 100, maxX = -100, minY = 100, maxY = -100, minZ = 100, maxZ = -100;
        for (ManipulatorCraftingIngredient ingredient : recipe.ingredients()) {
            BlockPos pos = ingredient.relativeBlockPos();
            minX = Math.min(minX, pos.getX());
            maxX = Math.max(maxX, pos.getX());
            minY = Math.min(minY, pos.getY());
            maxY = Math.max(maxY, pos.getY());
            minZ = Math.min(minZ, pos.getZ());
            maxZ = Math.max(maxZ, pos.getZ());
        }
        xSize = maxX - minX;
        ySize = maxY - minY;
        zSize = maxZ - minZ;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        assert minecraft != null;
        ScreenHelper.renderWidthScaledText(title.getString(), guiGraphics, Minecraft.getInstance().font, width / 2f, 5, Color.LIGHT_GRAY.getRGB(), 300, true);
        if (recipe.ingredients().isEmpty()) return;

        PoseStack pose = guiGraphics.pose();
        Lighting.setupFor3DItems();
        pose.pushPose();
        pose.translate(width / 2f, height / 2f, 500);
        pose.mulPose(Axis.XP.rotationDegrees(-45F / 2));
        pose.mulPose(Axis.YP.rotationDegrees(45));
        pose.scale(-50, -50, -50);
        pose.translate(-xSize / 2f, -ySize / 2f, -zSize / 2f);
        //pose.translate(-0.5, -0.5, 0);
        int i = 0;
        int e = age / 10;
        for (ManipulatorCraftingIngredient ingredient : recipe.ingredients()) {
            if (i > e % recipe.ingredients().size()) break;
            ItemStack s = new ItemStack(ingredient.inputBlockState().getBlock());
            BlockPos pos = ingredient.relativeBlockPos();
            pose.pushPose();
            pose.translate(pos.getX(), pos.getY(), pos.getZ());
            assert minecraft.level != null;
            RenderSystem.setShaderColor(1, 1, 1, 1);

            //minecraft.getBlockRenderer().renderBatched(ingredient.inputBlockState(), ingredient.relativeBlockPos(), minecraft.level, guiGraphics.pose(), guiGraphics.bufferSource().getBuffer(RenderType.text(TextureAtlas.LOCATION_BLOCKS)), false, minecraft.level.random);
            minecraft.getItemRenderer().renderStatic(s, ItemDisplayContext.HEAD, 15728880, OverlayTexture.NO_OVERLAY, guiGraphics.pose(), guiGraphics.bufferSource(), null, 0);
            pose.popPose();
            i++;
        }
        guiGraphics.flush();
        Lighting.setupFor3DItems();
        pose.popPose();
    }

    @Override
    public void tick() {
        super.tick();
        age++;
    }

}
