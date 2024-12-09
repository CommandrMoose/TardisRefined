package whocraft.tardis_refined.client.screen.selections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.CommonTRWidgets;
import whocraft.tardis_refined.constants.ModMessages;

import java.awt.*;

public class SelectionScreen extends Screen {

    public static final ResourceLocation BUTTON_LOCATION = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/save.png");
    public static final ResourceLocation BCK_LOCATION = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/back.png");
    private final Component title;
    public int noiseX, noiseY, age;
    public double noiseAlpha;
    private SelectionScreenRun onSubmit;
    private SelectionScreenRun onCancel;
    private ObjectSelectionList list;


    public SelectionScreen(Component title) {
        super(Component.translatable(ModMessages.UI_SHELL_SELECTION));
        this.title = title;
    }

    public void setEvents(SelectionScreenRun onSubmit, SelectionScreenRun onCancel) {
        this.onSubmit = onSubmit;
        this.onCancel = onCancel;
    }

    @Override
    protected void init() {
        super.init();
        list = createSelectionList();
        this.addRenderableWidget(list);

    }


    public void addSubmitButton(int x, int y) {
        if (onSubmit != null) {
            Button spriteiconbutton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> {
                this.onSubmit.onPress();
            }, true, BUTTON_LOCATION));
            spriteiconbutton.setPosition(x, y);
        }
    }

    public void addCancelButton(int x, int y) {
        if (onCancel != null) {
            Button spriteiconbutton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Cancel"), (arg) -> {
                this.onCancel.onPress();
            }, true, BCK_LOCATION));
            spriteiconbutton.setPosition(x, y);
        }

    }

    public ObjectSelectionList createSelectionList() {
        return null;
    }

    public Component getSelectedDisplayName() {
        return null;
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        ScreenHelper.renderWidthScaledText(title.getString(), guiGraphics, Minecraft.getInstance().font, width / 2, height / 2 - 100, Color.LIGHT_GRAY.getRGB(), 300, true);
    }

    @Override
    public void tick() {
        RandomSource random = Minecraft.getInstance().level.random;
        super.tick();
        this.age++;
        this.noiseX = random.nextInt(736);
        this.noiseY = random.nextInt(414);
        if (this.age % 3 == 0) this.noiseAlpha = random.nextDouble();
    }

    public interface SelectionScreenRun {
        void onPress();
    }
}
