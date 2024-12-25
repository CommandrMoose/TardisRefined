package whocraft.tardis_refined.client.screen.ponder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.common.crafting.astral_manipulator.ManipulatorCraftingRecipe;

import java.util.List;

public class PonderListScreen extends MonitorOS {

    public PonderListScreen() {
        super(Component.literal(""), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
    }

    @Override
    public ObjectSelectionList<SelectionListEntry> createSelectionList() {
        int vPos = (height - monitorHeight) / 2;
        int leftPos = this.width / 2 - 75;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 150, 80, leftPos, vPos + 15, vPos + monitorHeight - 30, 12);
        selectionList.setRenderBackground(false);

        if (Minecraft.getInstance().level == null) return null;
        if (ManipulatorCraftingRecipe.getAllRecipes(Minecraft.getInstance().level).isEmpty()) return null;

        List<ManipulatorCraftingRecipe> recipes = ManipulatorCraftingRecipe.getAllRecipes(Minecraft.getInstance().level);


        for (ManipulatorCraftingRecipe recipe : recipes) {
            Component name = PonderScreen.getResultName(recipe);

            selectionList.children().add(new SelectionListEntry(name, (entry) -> {
                Minecraft.getInstance().setScreen(new PonderScreen(recipe));
            }, leftPos));
        }

        return selectionList;
    }
}
