package whocraft.tardis_refined.client.screen.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.brigadier.StringReader;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.common.network.messages.C2SChangeDesktop;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRSoundRegistry;

import java.util.Collection;
import java.util.Comparator;

public class DesktopSelectionScreen extends MonitorOS {

    public static ResourceLocation previousImage = TardisDesktops.FACTORY_THEME.getPreviewTexture();
    private DesktopTheme currentDesktopTheme;

    public DesktopSelectionScreen() {
        super(Component.translatable(ModMessages.UI_DESKTOP_CONFIGURATION), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
    }

    public static void selectDesktop(DesktopTheme theme) {
        assert Minecraft.getInstance().player != null;
        new C2SChangeDesktop(Minecraft.getInstance().player.level().dimension(), theme).send();
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    protected void init() {
        super.init();
        this.setEvents(() -> DesktopSelectionScreen.selectDesktop(currentDesktopTheme), () -> {
            if (PREVIOUS != null)
                this.switchScreenToLeft(PREVIOUS);
        });
        this.currentDesktopTheme = grabDesktop();
        int vPos = (height - monitorHeight) / 2;
        addSubmitButton(width / 2 + 25, height - vPos - 25);
        addCancelButton(width / 2 + 5, height - vPos - 25);
    }

    private DesktopTheme grabDesktop() {
        for (DesktopTheme desktop : TardisDesktops.getRegistry().values()) {
            return desktop;
        }
        return null;
    }

    @Override
    public void renderBackdrop(@NotNull GuiGraphics guiGraphics) {
        super.renderBackdrop(guiGraphics);
    }

    @Override
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        PoseStack poseStack = guiGraphics.pose();
        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        /*Render Interior Image*/
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(backdrop, 5 + width / 2, -50 + height / 2, 110, monitorHeight, 110, 95);
        poseStack.pushPose();
        int trim = 15;
        poseStack.translate(hPos + trim - 5, vPos + trim + 5, 0);
        float scale = (monitorHeight - 2 * trim) / 400.0f;
        guiGraphics.blit(backdrop, -5, -5, 0, monitorHeight, 110, 110);
        poseStack.scale(scale, scale, scale);
        guiGraphics.blit(currentDesktopTheme.getPreviewTexture(), 0, 0, 0, 0, 400, 400, 400, 400);

        double alpha = (100.0D - this.age * 3.0D) / 100.0D;
        RenderSystem.enableBlend();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float) alpha);
        guiGraphics.blit(previousImage, 0, 0, 400, 400, 400, 400);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float) alpha);
        RenderSystem.setShaderTexture(0, NOISE);
        guiGraphics.blit(NOISE, 0, 0, (int) (Math.random() * 736), (int) (414 * (System.currentTimeMillis() % 1000) / 1000.0), 400, 400);
        RenderSystem.disableBlend();
        poseStack.popPose();

    }

    @Override
    public ObjectSelectionList<SelectionListEntry> createSelectionList() {
        int leftPos = 5 + width / 2;
        int topPos = (height - monitorHeight) / 2;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 100, 80, leftPos, topPos + 15, topPos + monitorHeight - 30, 12);
        selectionList.setRenderBackground(false);

        Collection<DesktopTheme> values = TardisDesktops.getRegistry().values();
        values = values.stream().sorted(Comparator.comparing(DesktopTheme::getName)).toList();

        for (DesktopTheme desktop : values) {

            Component name = Component.literal(MiscHelper.getCleanName(desktop.getIdentifier().getPath()));
            // Check for if the tellraw name is incomplete, or fails to pass.
            try {
                name = Component.Serializer.fromJson(new StringReader(desktop.getName()));
            } catch (Exception ex) {
                TardisRefined.LOGGER.error("Could not process Name for datapack desktop {}", desktop.getIdentifier().toString());
            }

            selectionList.children().add(new SelectionListEntry(name, (entry) -> {
                previousImage = currentDesktopTheme.getPreviewTexture();
                this.currentDesktopTheme = desktop;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }
                entry.setChecked(true);
                age = 0;
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(TRSoundRegistry.STATIC.get(), (float) Math.random()));
            }, leftPos));
        }

        return selectionList;
    }

}