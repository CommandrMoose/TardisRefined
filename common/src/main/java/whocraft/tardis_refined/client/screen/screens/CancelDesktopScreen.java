package whocraft.tardis_refined.client.screen.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.client.screen.main.MonitorScreen;
import whocraft.tardis_refined.common.network.messages.C2SCancelDesktopChange;
import whocraft.tardis_refined.constants.ModMessages;

import java.awt.*;

public class CancelDesktopScreen extends MonitorOS {

    public CancelDesktopScreen() {
        super(Component.translatable(ModMessages.UI_DESKTOP_CANCEL_DESKTOP), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(Button.builder(Component.translatable(ModMessages.UI_DESKTOP_CANCEL), (button) -> {
            new C2SCancelDesktopChange(Minecraft.getInstance().player.level().dimension()).send();
            Minecraft.getInstance().setScreen(null);
        }).bounds(this.width / 2 - (175 / 2), this.height / 2 + 10, 175, 20).build());

        this.addRenderableWidget(new Button.Builder(CommonComponents.GUI_BACK, (button) -> {
            Minecraft.getInstance().setScreen(null);
        }).bounds(this.width / 2 - (175 / 2), this.height / 2 + 30, 175, 20).build());

    }

    @Override
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int i, int j, float f) {
        ScreenHelper.renderWidthScaledText(Component.translatable(ModMessages.UI_DESKTOP_CANCEL_TITLE).getString(), guiGraphics, Minecraft.getInstance().font, width / 2f, height / 2f - 30, Color.LIGHT_GRAY.getRGB(), 300, true);
        ScreenHelper.renderWidthScaledText(Component.translatable(ModMessages.UI_DESKTOP_CANCEL_DESCRIPTION).getString(), guiGraphics, Minecraft.getInstance().font, width / 2f, this.height / 2f - 20, Color.WHITE.getRGB(), 210, true);
        ScreenHelper.renderWidthScaledText(Component.translatable(ModMessages.UI_DESKTOP_CANCEL_DESKTOP).getString(), guiGraphics, Minecraft.getInstance().font, width / 2f, this.height / 2f - 10, Color.WHITE.getRGB(), 210, true);
    }

}
