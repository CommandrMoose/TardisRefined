package whocraft.tardis_refined.client.screen.ponder;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ScreenHandler;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorBlockResult;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingIngredient;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipe;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorItemResult;

import java.util.HashSet;
import java.util.Set;

public class PonderScreen extends MonitorOS {

    private final ManipulatorCraftingRecipe recipe;
    private final int xSize, ySize, zSize;
    private int age = 0;

    public PonderScreen(ManipulatorCraftingRecipe recipe) {
        super(getResultName(recipe), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));

        this.setEvents(new MonitorOSRun() {
            @Override
            public void onPress() {

            }
        }, new MonitorOSRun() {
            @Override
            public void onPress() {
                ScreenHandler.openCraftingScreen();
            }
        });

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

    public static Component getResultName(ManipulatorCraftingRecipe recipe) {
        if (recipe.result() instanceof ManipulatorBlockResult blockResult) {
            BlockState blockState = blockResult.recipeOutput();
            return blockState.getBlock().getName().setStyle(Style.EMPTY);
        } else {
            if (recipe.result() instanceof ManipulatorItemResult itemResult) {
                ItemStack itemStack = itemResult.recipeOutput();
                return itemStack.getHoverName();
            }
        }
        return Component.literal("What on earth are you crafting?");
    }

    @Override
    protected void init() {
        super.init();
        int vPos = (height - monitorHeight) / 2;
        addCancelButton(width / 2 - 105, height - vPos - 25);
    }

    @Override
    public ObjectSelectionList<SelectionListEntry> createSelectionList() {
        int leftPos = width / 2;
        int topPos = (height - monitorHeight) / 2;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 105, 80, leftPos, topPos + 15, topPos + monitorHeight - 30, 12);
        selectionList.setRenderBackground(false);

        Set<String> uniqueNames = new HashSet<>();
        for (ManipulatorCraftingIngredient ingredient : recipe.ingredients()) {
            ItemStack stack = new ItemStack(ingredient.inputBlockState().getBlock());
            String itemName = stack.getHoverName().getString();

            if (uniqueNames.add(itemName)) { // Ensures duplicates are avoided
                SelectionListEntry selectionListEntry = new SelectionListEntry(ingredient.inputBlockState().getBlock().getName(), (entry) -> {

                }, leftPos);
                selectionList.children().add(selectionListEntry);
            }
        }

        return selectionList;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        assert minecraft != null;

        if (recipe.ingredients().isEmpty()) return;

        PoseStack pose = guiGraphics.pose();

        // Right-aligned 3D rendering of ingredients
        Lighting.setupFor3DItems();
        pose.pushPose();
        pose.translate((float) width / 2 - 50, height / 2f, 500);
        pose.mulPose(Axis.XP.rotationDegrees(-45F / 2));
        pose.mulPose(Axis.YP.rotationDegrees(45));
        pose.scale(-20, -20, -20);
        pose.translate(-xSize / 2f, -ySize / 2f, -zSize / 2f);

        int i = 0;
        int e = age / 10;
        for (ManipulatorCraftingIngredient ingredient : recipe.ingredients()) {
            if (i > e % recipe.ingredients().size()) break;
            BlockState s = ingredient.inputBlockState();
            BlockPos pos = ingredient.relativeBlockPos();
            pose.pushPose();
            pose.translate(pos.getX(), pos.getY(), pos.getZ());
            assert minecraft.level != null;
            RenderSystem.setShaderColor(1, 1, 1, 1);
            minecraft.getBlockRenderer().renderSingleBlock(s, guiGraphics.pose(), guiGraphics.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY);
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
