package whocraft.tardis_refined.client.screen.upgrades;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.ArrayList;
import java.util.List;

public class UpgradesScreen extends Screen {

    public static final ResourceLocation WINDOW = new ResourceLocation(TardisRefined.MODID, "textures/gui/upgrades/window.png");
    public static final ResourceLocation OVERLAY = new ResourceLocation(TardisRefined.MODID, "textures/gui/upgrades/upgrades_overlay.png");
    public static final ResourceLocation TABS = new ResourceLocation(TardisRefined.MODID, "textures/gui/upgrades/tabs.png");
    public static final ResourceLocation BACKDROP = new ResourceLocation(TardisRefined.MODID, "textures/gui/upgrades/upgrades.png");

    public static final ResourceLocation MAIN_UPGRADE_LOCKED = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/gallifreyan_frame_unobtained.png");
    public static final ResourceLocation SUB_UPGRADE_LOCKED = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/upgrade_frame_unobtained.png");
    public static final ResourceLocation SUB_UPGRADE = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/upgrade_frame_obtained.png");
    public static final ResourceLocation MAIN_UPGRADE = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/gallifreyan_frame_obtained.png");
    public static final ResourceLocation LOCKED = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/lock.png");
    public static final ResourceLocation UNOBTAINED_BOX = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/box_unobtained.png");
    public static final ResourceLocation OBTAINED_BOX = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/box_obtained.png");
    public static final ResourceLocation WIDGETS = new ResourceLocation(TardisRefined.MODID, "textures/gui/sprites/upgrades/widgets.png");


    public static final int WINDOW_WIDTH = 256;
    public static final int WINDOW_HEIGHT = 173;
    public static final int WINDOW_INSIDE_WIDTH = 234 - 10;
    public static final int WINDOW_INSIDE_HEIGHT = 169 - 46;
    private static final int WINDOW_INSIDE_X = 10;
    private static final int WINDOW_INSIDE_Y = 18;
    private static int tabPage;
    private static int maxPages;
    private final List<UpgradeTab> tabs = new ArrayList<>();
    private final UpgradeHandler upgradeHandler;
    @Nullable
    public UpgradeTab selectedTab;
    public Screen overlayScreen = null;
    private boolean isScrolling;

    public UpgradesScreen(UpgradeHandler upgradeHandler) {
        super(Component.empty());
        this.upgradeHandler = upgradeHandler;
    }

    public static boolean isPotentialParentUnlocked(Upgrade upgrade, UpgradeHandler upgradeHandler) {
        if (upgrade.getParent() == null) {
            return true;
        }
        return upgrade.getParent().isUnlocked(upgradeHandler);
    }

    public static ResourceLocation getFrame(Upgrade.UpgradeType upgradeType, boolean isUnlocked) {
        if (isUnlocked) {
            return upgradeType == Upgrade.UpgradeType.MAIN_UPGRADE ? MAIN_UPGRADE : SUB_UPGRADE;
        }
        return upgradeType == Upgrade.UpgradeType.MAIN_UPGRADE ? MAIN_UPGRADE_LOCKED : SUB_UPGRADE_LOCKED;
    }

    public static ResourceLocation getBox(boolean isUnlocked) {
        return isUnlocked ? OBTAINED_BOX : UNOBTAINED_BOX;
    }

    @Override
    protected void init() {
        this.tabs.clear();
        this.selectedTab = null;

        UpgradeTab upgradeTab = UpgradeTab.create(this.minecraft, this, 0, upgradeHandler);
        this.tabs.add(upgradeTab);

        if (this.tabs.size() > UpgradeTabType.MAX_TABS) {
            int guiLeft = (this.width - WINDOW_WIDTH) / 2;
            int guiTop = (this.height - WINDOW_HEIGHT) / 2;
            this.addRenderableWidget(Button.builder(Component.literal("<"), (b) -> {
                tabPage = Math.max(tabPage - 1, 0);
            }).bounds(guiLeft, guiTop - 50, 20, 20).build());
            this.addRenderableWidget(Button.builder(Component.literal(">"), (b) -> {
                tabPage = Math.min(tabPage + 1, maxPages);
            }).bounds(guiLeft + WINDOW_WIDTH - 20, guiTop - 50, 20, 20).build());
            maxPages = this.tabs.size() / UpgradeTabType.MAX_TABS;
        }

        if (!this.tabs.isEmpty()) {
            this.selectedTab = tabs.get(0);
        }

        if (this.overlayScreen != null) {
            this.overlayScreen.init(this.minecraft, this.width, this.height);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int i = (this.width - WINDOW_WIDTH) / 2;
            int j = (this.height - WINDOW_HEIGHT) / 2;

            if (this.isOverOverlayScreen(mouseX, mouseY)) {
                return this.overlayScreen.mouseClicked(mouseX, mouseY, button);
            } else {
                for (UpgradeTab powerTab : this.tabs) {
                    if (powerTab.isMouseOver(i, j, mouseX, mouseY)) {
                        this.selectedTab = powerTab;
                        break;
                    }
                }

                if (selectedTab != null) {
                    UpgradeWidget entry = this.selectedTab.getUpgradeHoveredOver((int) (mouseX - i - 9), (int) (mouseY - j - 18), i, j);

                    if (entry != null) {
                        Upgrade upgrade = entry.upgradeEntry;
                        boolean hasUnlockedParent = isPotentialParentUnlocked(upgrade, upgradeHandler);
                        if (upgrade.isUnlocked(upgradeHandler)) return false;
                        this.openOverlayScreen(new BuyUpgradeScreen(upgrade, hasUnlockedParent && !upgrade.isUnlocked(upgradeHandler) && upgradeHandler.getUpgradePoints() >= upgrade.getSkillPointsRequired(), this));
                    }
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return this.overlayScreen == null ? super.keyPressed(keyCode, scanCode, modifiers) : this.overlayScreen.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int i = (this.width - WINDOW_WIDTH) / 2;
        int j = (this.height - WINDOW_HEIGHT) / 2;
        guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
//        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        this.renderWindow(guiGraphics, i, j);
        this.renderInside(guiGraphics, mouseX, mouseY, i, j);

        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (this.selectedTab != null && this.overlayScreen != null) {
            guiGraphics.pose().translate(0, 0, 500);
            this.overlayScreen.render(guiGraphics, mouseX, mouseY, partialTick);
            this.selectedTab.fade = Mth.clamp(this.selectedTab.fade + 0.02F, 0, 0.5F);
            guiGraphics.pose().translate(0, 0, -500);
        }


        this.renderFinalOverlay(guiGraphics, i, j);
        this.renderTooltips(guiGraphics, mouseX, mouseY, i, j);

        if (upgradeHandler.getUpgradePoints() > 0) {
            guiGraphics.drawString(this.minecraft.font, "Points: " + upgradeHandler.getUpgradePoints(), width / 2 - font.width("Points: " + upgradeHandler.getUpgradePoints()) / 2, j + WINDOW_HEIGHT - 15, ChatFormatting.BLACK.getColor(), false);
        }

        guiGraphics.drawString(this.minecraft.font, "XP: " + upgradeHandler.getUpgradeXP() + " / 100", width / 2 - font.width("XP: " + upgradeHandler.getUpgradeXP() + " / 100") / 2, j + 6, ChatFormatting.BLACK.getColor(), false);


    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button != 0) {
            this.isScrolling = false;
            return false;
        } else {
            if (!this.isScrolling) {
                this.isScrolling = true;
            } else if (this.selectedTab != null) {
                this.selectedTab.scroll(dragX, dragY);
            }

            return true;
        }
    }

    private void renderInside(GuiGraphics guiGraphics, int mouseX, int mouseY, int offsetX, int offsetY) {
        UpgradeTab tab = this.selectedTab;
        if (tab == null) {
            guiGraphics.fill(offsetX + WINDOW_INSIDE_X, offsetY + WINDOW_INSIDE_Y, offsetX + WINDOW_INSIDE_X + WINDOW_INSIDE_WIDTH, offsetY + WINDOW_INSIDE_Y + WINDOW_INSIDE_HEIGHT, -16777216);
        } else {
            tab.drawContents(guiGraphics, offsetX + WINDOW_INSIDE_X, offsetY + WINDOW_INSIDE_Y);
        }
    }

    public void renderWindow(GuiGraphics guiGraphics, int offsetX, int offsetY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        guiGraphics.blit(BACKDROP, offsetX, offsetY, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        if (this.tabs.size() > 1) {
            RenderSystem.setShaderTexture(0, TABS);

            for (UpgradeTab tab : this.tabs) {
                tab.drawTab(guiGraphics, offsetX, offsetY, tab == this.selectedTab);
            }

            RenderSystem.defaultBlendFunc();

            for (UpgradeTab tab : this.tabs) {
                tab.drawIcon(guiGraphics, offsetX, offsetY);
            }

            RenderSystem.disableBlend();
        }
    }

    public void renderFinalOverlay(GuiGraphics guiGraphics, int offsetX, int offsetY) {
        guiGraphics.blit(OVERLAY, offsetX, offsetY, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

    }

    private void renderTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, int offsetX, int offsetY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.selectedTab != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate((float) (offsetX + WINDOW_INSIDE_X), (float) (offsetY + WINDOW_INSIDE_Y), 400.0F);
            RenderSystem.enableDepthTest();
            this.selectedTab.drawTooltips(guiGraphics, mouseX - offsetX - WINDOW_INSIDE_X, mouseY - offsetY - WINDOW_INSIDE_Y, offsetX, offsetY, this.overlayScreen != null);
            RenderSystem.disableDepthTest();
            guiGraphics.pose().popPose();
        }

        if (this.tabs.size() > 1) {
            for (UpgradeTab tab : this.tabs) {
                if (tab.isMouseOver(offsetX, offsetY, mouseX, mouseY)) {
                    guiGraphics.renderTooltip(this.font, tab.getTitle(), mouseX, mouseY);
                }
            }
        }
    }

    public void closeOverlayScreen() {
        this.overlayScreen = null;
    }

    public void openOverlayScreen(Screen screen) {
        this.closeOverlayScreen();
        this.overlayScreen = screen;
        this.overlayScreen.init(this.minecraft, this.width, this.height);
    }

    public boolean isOverOverlayScreen(double mouseX, double mouseY) {
        return overlayScreen != null;
    }


}
