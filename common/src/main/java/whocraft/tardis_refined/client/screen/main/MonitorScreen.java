package whocraft.tardis_refined.client.screen.main;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.screens.DesktopSelectionScreen;
import whocraft.tardis_refined.client.screen.screens.HumSelectionScreen;
import whocraft.tardis_refined.client.screen.screens.VortexSelectionScreen;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.network.messages.C2SEjectPlayer;
import whocraft.tardis_refined.common.network.messages.player.C2SBeginShellView;
import whocraft.tardis_refined.common.network.messages.screens.C2SRequestShellSelection;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SRequestWaypoints;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRUpgrades;

import java.awt.*;


public class MonitorScreen extends MonitorOS.MonitorOSExtension {

    private final TardisNavLocation currentLocation;
    private final TardisNavLocation targetLocation;
    private final UpgradeHandler upgradeHandler;

    private boolean noUpgrades = false;

    public MonitorScreen(TardisNavLocation currentLocation, TardisNavLocation targetLocation, UpgradeHandler upgradeHandler, ResourceLocation currentShellTheme) {
        super(Component.translatable(ModMessages.UI_MONITOR_MAIN_TITLE), currentShellTheme);
        this.currentLocation = currentLocation;
        this.targetLocation = targetLocation;
        this.upgradeHandler = upgradeHandler;
    }

    @Override
    protected void init() {
        super.init();
        int hPos = (width - monitorWidth) / 2;

        Button shellSelectButton = addRenderableWidget(Button.builder(Component.translatable(ModMessages.UI_SHELL_SELECTION), button -> new C2SRequestShellSelection().send()).pos(hPos + 5, height / 2).size(70, 20).build());
        shellSelectButton.active = TRUpgrades.CHAMELEON_CIRCUIT_SYSTEM.get().isUnlocked(upgradeHandler);
        Button vortxSelectButton = addRenderableWidget(Button.builder(Component.translatable(ModMessages.UI_MONITOR_VORTEX), button -> this.switchScreenToLeft(new VortexSelectionScreen(VortexRegistry.VORTEX_REGISTRY.getKey(VortexRegistry.FLOW.get())))).pos(hPos + 5, -20 + height / 2).size(70, 20).build());
        vortxSelectButton.active = true;
    }

    @Override
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        PoseStack poseStack = guiGraphics.pose();
        int upgradesLeftPos = width / 2 - 75;
        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        if (noUpgrades && ChatFormatting.GOLD.getColor() != null) {
            guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable(ModMessages.UI_NO_INSTALLED_SUBSYSTEMS).getString(), upgradesLeftPos, vPos + 30, ChatFormatting.GOLD.getColor());
        }

        renderShell(guiGraphics, width / 2, height / 2, 15F);

        int textScale = 40;

        poseStack.pushPose();
        poseStack.translate(hPos + 10, vPos + 10, 0);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable(ModMessages.UI_MONITOR_GPS).getString() + ":", 0, 0, Color.WHITE.getRGB());
        ScreenHelper.renderWidthScaledText(currentLocation.getDirection().getName().toUpperCase() + " @ " + currentLocation.getPosition().toShortString(), guiGraphics, Minecraft.getInstance().font, 0, 10, Color.LIGHT_GRAY.getRGB(), textScale * 2, 0.75F, false);
        ScreenHelper.renderWidthScaledText(MiscHelper.getCleanDimensionName(currentLocation.getDimensionKey()), guiGraphics, Minecraft.getInstance().font, 0, 20, Color.LIGHT_GRAY.getRGB(), textScale - 3, 1.5F, false);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(hPos + 10, vPos + monitorHeight - 35, 0);
        guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable(ModMessages.UI_MONITOR_DESTINATION).getString() + ":", 0, 0, Color.WHITE.getRGB());
        ScreenHelper.renderWidthScaledText(targetLocation.getDirection().getName().toUpperCase() + " @ " + targetLocation.getPosition().toShortString(), guiGraphics, Minecraft.getInstance().font, 0, 10, Color.LIGHT_GRAY.getRGB(), textScale * 2, 0.75F, false);
        ScreenHelper.renderWidthScaledText(MiscHelper.getCleanDimensionName(targetLocation.getDimensionKey()), guiGraphics, Minecraft.getInstance().font, 0, 20, Color.LIGHT_GRAY.getRGB(), textScale - 3, 1.5F, false);
        poseStack.popPose();
    }

    @Override
    public GenericMonitorSelectionList<SelectionListEntry> createSelectionList() {
        int hPos = 20 + width / 2;
        int vPos = 20 + (height - monitorHeight) / 2;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 100, 80, hPos, vPos, height - vPos, 10);
        selectionList.setRenderBackground(false);
        //selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_EXTERNAL_SHELL), entry -> new C2SRequestShellSelection().send(), hPos, TRUpgrades.CHAMELEON_CIRCUIT_SYSTEM.get().isUnlocked(upgradeHandler)));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_DESKTOP_CONFIGURATION), entry -> switchScreenToRight(new DesktopSelectionScreen()), hPos, TRUpgrades.INSIDE_ARCHITECTURE.get().isUnlocked(upgradeHandler)));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_WAYPOINTS), entry -> new C2SRequestWaypoints().send(), hPos, TRUpgrades.WAYPOINTS.get().isUnlocked(upgradeHandler)));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_SHELL_VIEW), entry -> new C2SBeginShellView().send(), hPos, TRUpgrades.WAYPOINTS.get().isUnlocked(upgradeHandler)));
        //selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_VORTEX), entry -> Minecraft.getInstance().setScreen(new VortexSelectionScreen(VortexRegistry.VORTEX_REGISTRY.getKey(VortexRegistry.FLOW.get()))), hPos));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_SELECT_HUM), entry -> switchScreenToRight(new HumSelectionScreen()), hPos));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_EJECT), entry -> {
            new C2SEjectPlayer().send();
            Minecraft.getInstance().setScreen(null);
        }, hPos));

        if (selectionList.children().isEmpty()) {
            noUpgrades = true;
            return null;
        }

        return selectionList;
    }

}
