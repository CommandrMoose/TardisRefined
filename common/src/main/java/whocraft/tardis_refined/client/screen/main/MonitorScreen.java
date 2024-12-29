package whocraft.tardis_refined.client.screen.main;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.BackgroundlessButton;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.screens.DesktopSelectionScreen;
import whocraft.tardis_refined.client.screen.screens.HumSelectionScreen;
import whocraft.tardis_refined.client.screen.screens.VortexSelectionScreen;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.network.messages.C2SEjectPlayer;
import whocraft.tardis_refined.common.network.messages.player.C2SBeginShellView;
import whocraft.tardis_refined.common.network.messages.screens.C2SRequestShellSelection;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SRequestWaypoints;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ShellPatterns;
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

    private Button ejectbtn;
    private int ejectbtntime;
    private boolean ejectbtnshow;

    @Override
    protected void init() {
        super.init();
        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        //shellSelectButton.active = TRUpgrades.CHAMELEON_CIRCUIT_SYSTEM.get().isUnlocked(upgradeHandler);
/*
        Button vortxSelectButton = addRenderableWidget(
                Button.builder(Component.translatable(ModMessages.UI_MONITOR_VORTEX),
                                button -> this.switchScreenToLeft(new VortexSelectionScreen(currentVortex)))
                        .pos(hPos + 5, -20 + height / 2)
                        .size(70, 20).build());
        vortxSelectButton.active = true;
*/
        BackgroundlessButton extView = addRenderableWidget(BackgroundlessButton.backgroundlessBuilder(Component.literal(""), button -> {
            new C2SBeginShellView().send();
            Minecraft.getInstance().setScreen(null);
        }).pos(hPos + 20, -30 + height / 2).size(40, 60).build());
        extView.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_SHELL_VIEW)));
        extView.active = true;

        ejectbtn = addRenderableWidget(Button.builder(Component.translatable(ModMessages.UI_MONITOR_EJECT), button -> {
            new C2SEjectPlayer().send();
            Minecraft.getInstance().setScreen(null);
        }).pos(-35 + hPos + monitorWidth / 2, vPos + monitorHeight - 20).size(70, 20).build());

    }

    @Override
    public void renderBackdrop(@NotNull GuiGraphics guiGraphics) {
        super.renderBackdrop(guiGraphics);

        PoseStack poseStack = guiGraphics.pose();

        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        poseStack.pushPose();
        int b = height - vPos, r = width - hPos;
        int l1 = hPos + monitorWidth / 5, l2 = (int) (hPos + monitorWidth / 2.5f);

        guiGraphics.fill(l1, vPos, r, b, -1072689136);
        poseStack.translate(l1, vPos, 0);
        poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        guiGraphics.fillGradient(0, 0, monitorHeight, l1 - hPos, -1072689136, 0x00000000);
        poseStack.popPose();
    }

    @Override
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        PoseStack poseStack = guiGraphics.pose();
        int upgradesLeftPos = width / 2 - 75;
        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;


        this.ejectbtnshow = (mouseY >= vPos + monitorHeight - 20 && mouseY <= vPos + monitorHeight) && (mouseX >= -35 + hPos + monitorWidth / 2 && mouseX <= 70 - 35 + hPos + monitorWidth / 2);

        ejectbtn.setPosition(-35 + hPos + monitorWidth / 2, vPos + monitorHeight - ejectbtntime);
        ejectbtn.active = ejectbtntime == 20;


        if (noUpgrades && ChatFormatting.GOLD.getColor() != null) {
            guiGraphics.drawString(Minecraft.getInstance().font, Component.translatable(ModMessages.UI_NO_INSTALLED_SUBSYSTEMS).getString(), upgradesLeftPos, vPos + 30, ChatFormatting.GOLD.getColor());
        }

        poseStack.pushPose();
        poseStack.translate(0, 0, 1000);
        renderShell(guiGraphics, hPos + 40, -1 + height / 2, 15F);
        poseStack.popPose();

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
    public ResourceLocation getPatternForRender() {
        TardisClientData tardisClientData = TardisClientData.getInstance(Minecraft.getInstance().level.dimension());
        if(tardisClientData == null) return ShellPatterns.DEFAULT.id();
        return tardisClientData.getShellPattern();
    }

    @Override
    public void tick() {
        super.tick();

        if (ejectbtnshow) ejectbtntime += 5 - ejectbtntime / 4;
        else ejectbtntime -= 5 - (20 - ejectbtntime) / 4;
        if (ejectbtntime > 20) ejectbtntime = 20;
        if (ejectbtntime < 5) ejectbtntime = 5;

    }

    @Override
    public GenericMonitorSelectionList<SelectionListEntry> createSelectionList() {
        int hPos = -20 + width / 2;
        int vPos = 20 + (height - monitorHeight) / 2;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 15 + monitorWidth / 2, 80, hPos, vPos, height - vPos, 12);
        selectionList.setRenderBackground(false);
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_EXTERNAL_SHELL), entry -> new C2SRequestShellSelection().send(), hPos, TRUpgrades.CHAMELEON_CIRCUIT_SYSTEM.get().isUnlocked(upgradeHandler)));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_DESKTOP_CONFIGURATION), entry -> switchScreenToRight(new DesktopSelectionScreen()), hPos, TRUpgrades.INSIDE_ARCHITECTURE.get().isUnlocked(upgradeHandler)));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_WAYPOINTS), entry -> new C2SRequestWaypoints().send(), hPos, TRUpgrades.WAYPOINTS.get().isUnlocked(upgradeHandler)));
        //selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_SHELL_VIEW), entry -> new C2SBeginShellView().send(), hPos, TRUpgrades.WAYPOINTS.get().isUnlocked(upgradeHandler)));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_VORTEX), entry -> switchScreenToRight(new VortexSelectionScreen(currentVortex)), hPos));
        selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_SELECT_HUM), entry -> switchScreenToRight(new HumSelectionScreen()), hPos));
        //selectionList.children().add(new SelectionListEntry(Component.translatable(ModMessages.UI_MONITOR_EJECT), entry -> {
        //    new C2SEjectPlayer().send();
        //    Minecraft.getInstance().setScreen(null);
        //}, hPos));

        if (selectionList.children().isEmpty()) {
            noUpgrades = true;
            return null;
        }

        return selectionList;
    }

}
