package whocraft.tardis_refined.client.screen.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;

public class GenericMonitorSelectionList<T extends ObjectSelectionList.Entry<T>> extends ObjectSelectionList<T> {
    /**
     * Creates a scrollable list with entries defined by a separate class
     *
     * @param minecraft
     * @param width
     * @param height
     * @param xLeftPos   - the x coordinate for the start position of the scrollable list area
     * @param yStart     - the y coordinate for the top of the scrollable list area
     * @param yEnd       - the y coordinate for the bottom of the scrollable list area
     * @param itemHeight - height of each item in the list
     */
    public GenericMonitorSelectionList(Minecraft minecraft, int width, int height, int xLeftPos, int yStart, int yEnd, int itemHeight) {
        super(minecraft, width, height, yStart, yEnd, itemHeight); //Don't add anything to the y1 variable otherwise the entry button will be slighter taller than expected
        this.setLeftPos(xLeftPos);
        this.setRenderHeader(false, 0);

        //Todo is this important?
        /*this.setRenderTopAndBottom(false);
        this.setRenderSelection(false);*/

        this.setRenderBackground(true);
    }

    @Override
    protected int getScrollbarPosition() {
        return this.x1 - 6;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }


}


