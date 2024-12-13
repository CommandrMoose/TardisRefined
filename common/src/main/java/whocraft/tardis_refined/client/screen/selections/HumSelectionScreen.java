package whocraft.tardis_refined.client.screen.selections;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.brigadier.StringReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.common.hum.HumEntry;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.network.messages.hums.C2SChangeHum;
import whocraft.tardis_refined.common.util.MiscHelper;

import java.util.Collection;
import java.util.Comparator;

public class HumSelectionScreen extends SelectionScreen {
    public static ResourceLocation MONITOR_TEXTURE = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/gui/monitor.png");
    protected int imageWidth = 256;
    protected int imageHeight = 173;
    private HumEntry currentHumEntry;
    private int leftPos, topPos;


    public HumSelectionScreen() {
        super(Component.translatable(""));
    }

    public static void selectHum(HumEntry theme) {
        new C2SChangeHum(Minecraft.getInstance().player.level().dimension(), theme).send();
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    public void addSubmitButton(int x, int y) {
        super.addSubmitButton(x, y);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        this.setEvents(() -> {
            HumSelectionScreen.selectHum(currentHumEntry);
        }, () -> {
            Minecraft.getInstance().setScreen(null);
        });
        this.currentHumEntry = grabHum();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        //Super method already creates the list, we don't need to create it a second time.
        super.init();

        addSubmitButton(width / 2 + 85, (height) / 2 + 35);
        addCancelButton(width / 2 - 105, (height) / 2 + 35);
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        return super.mouseClicked(d, e, i);
    }

    private HumEntry grabHum() {
        for (HumEntry humEntry : TardisHums.getRegistry().values()) {
            return humEntry;
        }
        return null;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderTransparentBackground(guiGraphics);


        /*Render Back drop*/
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        guiGraphics.blit(MONITOR_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);


        super.render(guiGraphics, i, j, f);

    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {

    }

    @Override
    public Component getSelectedDisplayName() {
        return Component.Serializer.fromJson(currentHumEntry.getNameComponent());
    }

    @Override
    public ObjectSelectionList createSelectionList() {
        int leftPos = this.width / 2 - 75;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 150, 80, leftPos, this.topPos + 30, this.topPos + this.imageHeight - 60, 12);
        selectionList.setRenderBackground(false);

        Collection<HumEntry> knownHums = TardisHums.getRegistry().values();
        knownHums = knownHums.stream().sorted(Comparator.comparing(HumEntry::getNameComponent)).toList();

        for (HumEntry humEntry : knownHums) {
            Component name = Component.literal(MiscHelper.getCleanName(humEntry.getIdentifier().getPath()));

            // Check for if the tellraw name is incomplete, or fails to pass.
            try {
                var json = Component.Serializer.fromJson(new StringReader(humEntry.getNameComponent()));
                name = json;
            } catch (Exception ex) {
                TardisRefined.LOGGER.error("Could not process Name for hum " + humEntry.getIdentifier().toString());
            }

            selectionList.children().add(new SelectionListEntry(name, (entry) -> {
                // previousImage = humEntry.getPreviewTexture();
                this.currentHumEntry = humEntry;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }
                entry.setChecked(true);
            }, leftPos));
        }

        return selectionList;
    }

}