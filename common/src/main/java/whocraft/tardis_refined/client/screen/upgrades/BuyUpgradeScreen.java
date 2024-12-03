package whocraft.tardis_refined.client.screen.upgrades;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import whocraft.tardis_refined.client.screen.components.BackgroundlessButton;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.network.messages.upgrades.UnlockUpgradeMessage;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.List;
import java.util.Objects;

public class BuyUpgradeScreen extends Screen {

    private static final int GUI_WIDTH = 202;
    private static final int GUI_HEIGHT = 60;
    public final Upgrade upgrade;
    public final boolean available;
    public final UpgradesScreen parentScreen;
    private final Component text;

    public BuyUpgradeScreen(Upgrade upgrade, boolean available, UpgradesScreen parentScreen) {
        super(Component.empty());
        this.upgrade = upgrade;
        this.available = available;
        this.parentScreen = parentScreen;
        this.text = Component.translatable(ModMessages.UI_UPGRADES_BUY);
    }

    @Override
    protected void init() {
        super.init();

        int guiLeft = (this.width - GUI_WIDTH) / 2;
        int guiTop = (this.height - GUI_HEIGHT) / 2;
        this.addRenderableWidget(BackgroundlessButton.backgroundlessBuilder(Component.literal("x"), s -> parentScreen.closeOverlayScreen()).bounds(guiLeft + 193, guiTop + 3, 5, 5).build());
        Button button = Button.builder(Component.literal(this.upgrade.getSkillPointsRequired() + " COST"), s -> { //TODO: Add translation key for Cost
            new UnlockUpgradeMessage(this.upgrade).send();
            this.parentScreen.closeOverlayScreen();
            Objects.requireNonNull(Objects.requireNonNull(this.minecraft).player).playSound(SoundEvents.PLAYER_LEVELUP, 1F, 1F);
        }).bounds(guiLeft + 23, guiTop + 33, 54, 20).build();
        button.active = this.available;
        this.addRenderableWidget(button);
        this.addRenderableWidget(Button.builder(Component.translatable("gui.no"), s -> parentScreen.overlayScreen = null).bounds(guiLeft + 125, guiTop + 33, 54, 20).build());
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int guiLeft = (this.width - GUI_WIDTH) / 2;
        int guiTop = (this.height - GUI_HEIGHT) / 2;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        guiGraphics.blit(UpgradesScreen.WINDOW, guiLeft, guiTop, 0, 196, GUI_WIDTH, GUI_HEIGHT);

        List<FormattedCharSequence> lines = this.font.split(this.text, GUI_WIDTH - 40);
        for (int k = 0; k < lines.size(); k++) {
            FormattedCharSequence text = lines.get(k);
            int width = this.font.width(text);
            guiGraphics.drawString(font, text, (int) (guiLeft + GUI_WIDTH / 2F - width / 2F), guiTop + 9 + k * 10, ChatFormatting.BLACK.getColor(), false);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
