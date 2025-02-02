package whocraft.tardis_refined.client.screen.upgrades;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.registry.TRUpgrades;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UpgradeWidget {


    private static final int[] TEST_SPLIT_OFFSETS = new int[]{0, 10, -10, 25, -25};
    private static final ResourceLocation TITLE_BOX_SPRITE = new ResourceLocation("advancements/title_box");
    public final Upgrade upgradeEntry;
    private final UpgradeTab tab;
    private final UpgradeHandler upgradeHandler;
    private final FormattedCharSequence title;
    private final int width;
    private final List<FormattedCharSequence> description;
    private final Minecraft minecraft;
    public double gridX, gridY;
    public boolean fixedPosition = false;
    List<UpgradeWidget> parents = new LinkedList<>();
    List<UpgradeWidget> children = new LinkedList<>();
    private int x;
    private int y;

    public UpgradeWidget(UpgradeTab tab, Minecraft mc, UpgradeHandler upgradeHandler, Upgrade upgradeEntry) {
        this.tab = tab;
        this.upgradeHandler = upgradeHandler;
        this.upgradeEntry = upgradeEntry;
        this.minecraft = mc;
        this.title = Language.getInstance().getVisualOrder(mc.font.substrByWidth(upgradeEntry.getDisplayName(), 163));
        int l = 29 + mc.font.width(this.title);
        var description = upgradeEntry.getDisplayDescription();
        this.description = Language.getInstance()
                .getVisualOrder(
                        this.findOptimalLines(ComponentUtils.mergeStyles(description != null ? description.copy() : Component.empty(), Style.EMPTY.withColor(ChatFormatting.WHITE)), l)
                );



        for (FormattedCharSequence formattedCharSequence : this.description) {
            l = Math.max(l, minecraft.font.width(formattedCharSequence));
        }

        this.width = l + 3 + 5;
    }

    private static float getMaxWidth(StringSplitter manager, List<FormattedText> text) {
        return (float) text.stream().mapToDouble(manager::stringWidth).max().orElse(0.0);
    }

    public UpgradeWidget updatePosition(double x, double y, UpgradeTab tab) {
        this.gridX = x;
        this.gridY = y;
        this.x = (int) (x * UpgradeTab.GRID_SIZE) - 16;
        this.y = (int) (tab.getFreeYPos(x, y) * UpgradeTab.GRID_SIZE) - 13;

        for (UpgradeWidget child : this.children) {
            child.updatePosition(this.gridX + 1, y, tab);
        }

        return this;
    }

    public UpgradeWidget setPosition(double x, double y) {
        this.gridX = x;
        this.gridY = y;
        this.x = (int) (x * UpgradeTab.GRID_SIZE) - 16;
        this.y = (int) (y * UpgradeTab.GRID_SIZE) - 13;
        return this;
    }

    public UpgradeWidget setPositionFixed(double x, double y) {
        this.fixedPosition = true;
        return this.setPosition(x, y);
    }

    public UpgradeWidget updateRelatives(Collection<UpgradeWidget> list) {
        this.parents.clear();
        this.children.clear();

        Upgrade parent = this.upgradeEntry.getParent();
        List<Upgrade> children = this.upgradeEntry.getDirectChildren();

        for (UpgradeWidget widget : list) {
            if (parent != null && widget.upgradeEntry == parent) {
                this.parents.add(widget);
            }

            if (!children.isEmpty()) {
                if (children.contains(widget.upgradeEntry)) {
                    this.children.add(widget);
                }
            }
        }

        return this;
    }

    private List<FormattedText> findOptimalLines(Component component, int maxWidth) {
        StringSplitter stringSplitter = this.minecraft.font.getSplitter();
        List<FormattedText> list = null;
        float f = Float.MAX_VALUE;

        for (int i : TEST_SPLIT_OFFSETS) {
            List<FormattedText> list2 = stringSplitter.splitLines(component, maxWidth - i, Style.EMPTY);
            float g = Math.abs(getMaxWidth(stringSplitter, list2) - (float) maxWidth);
            if (g <= 10.0F) {
                return list2;
            }

            if (g < f) {
                f = g;
                list = list2;
            }
        }

        return list;
    }

    public void drawDisplayIcon(Minecraft mc, GuiGraphics guiGraphics, int x, int y) {
        if (this.upgradeEntry.isUnlocked(upgradeHandler)) {
            guiGraphics.renderFakeItem(this.upgradeEntry.getIcon(), x, y);
        } else {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            //Display Lock6,
            guiGraphics.blitSprite(UpgradesScreen.LOCKED, x - 5, y - 5, 26, 26);
        }
    }

    public void drawIcon(Minecraft mc, GuiGraphics guiGraphics, int x, int y) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        boolean isUnlocked = this.upgradeEntry.isUnlocked(upgradeHandler);

        guiGraphics.blitSprite(UpgradesScreen.getFrame(upgradeEntry.getUpgradeType(), isUnlocked), x - 13, y - 13, 26, 26);
        this.drawDisplayIcon(mc, guiGraphics, x - 8, y - 8);
    }

    public int getWidth() {
        return this.width;
    }

    public void drawHover(GuiGraphics guiGraphics, int i, int j, float f, int k, int l) {
        boolean bl = k + i + this.x + this.width + 26 >= this.tab.getScreen().width;
        Component component = null;
        int m = component == null ? 0 : this.minecraft.font.width(component);
        int var10000 = 113 - j - this.y - 26;
        int var10002 = this.description.size();
        Objects.requireNonNull(this.minecraft.font);
        boolean bl2 = var10000 <= 6 + var10002 * 9;
        float g = this.upgradeHandler.isUpgradeUnlocked(upgradeEntry) ? 1 : 0;
        int n = Mth.floor(g * (float) this.width);

        int o = this.width - n;
        RenderSystem.enableBlend();
        int p = j + this.y;
        int q;
        if (bl) {
            q = i + this.x - this.width + 26 + 6;
        } else {
            q = i + this.x;
        }

        int var10001 = this.description.size();
        Objects.requireNonNull(this.minecraft.font);
        int r = 32 + var10001 * 9;

        if (!this.description.isEmpty()) {
            if (bl2) {
                guiGraphics.blitSprite(TITLE_BOX_SPRITE, q, p + 26 - r, this.width, r);
            } else {
                guiGraphics.blitSprite(TITLE_BOX_SPRITE, q, p, this.width, r);
            }
        }

        guiGraphics.blitSprite(UpgradesScreen.getBox(upgradeEntry.isUnlocked(upgradeHandler)), 200, 26, 0, 0, q, p, n, 26);
        guiGraphics.blitSprite(UpgradesScreen.getBox(upgradeEntry.isUnlocked(upgradeHandler)), 200, 26, 200 - o, 0, q + n, p, o, 26);
        guiGraphics.blitSprite(UpgradesScreen.getFrame(upgradeEntry.getUpgradeType(), upgradeEntry.isUnlocked(upgradeHandler)), i + this.x + 3, j + this.y, 26, 26);
        if (bl) {
            guiGraphics.drawString(this.minecraft.font, this.title, q + 5, j + this.y + 9, -1);
            if (component != null) {
                guiGraphics.drawString(this.minecraft.font, component, i + this.x - m, j + this.y + 9, -1);
            }
        } else {
            guiGraphics.drawString(this.minecraft.font, this.title, i + this.x + 32, j + this.y + 9, -1);
            if (component != null) {
                guiGraphics.drawString(this.minecraft.font, component, i + this.x + this.width - m - 5, j + this.y + 9, -1);
            }
        }

        int var10003;
        int s;
        int var10004;
        Font var21;
        FormattedCharSequence var22;
        if (bl2) {
            for (s = 0; s < this.description.size(); ++s) {
                var21 = this.minecraft.font;
                var22 = this.description.get(s);
                var10003 = q + 5;
                var10004 = p + 26 - r + 7;
                Objects.requireNonNull(this.minecraft.font);
                guiGraphics.drawString(var21, var22, var10003, var10004 + s * 9, -5592406, false);
            }
            ResourceLocation key = TRUpgrades.UPGRADE_DEFERRED_REGISTRY.getKey(upgradeEntry);
            String owner = Platform.getModName(key.getNamespace());
            guiGraphics.drawString(this.minecraft.font, ChatFormatting.BLUE + owner, m + 5, l + 26 + 9, -1);
        } else {
            for (s = 0; s < this.description.size(); ++s) {
                var21 = this.minecraft.font;
                var22 = this.description.get(s);
                var10003 = q + 5;
                var10004 = j + this.y + 9 + 17;
                Objects.requireNonNull(this.minecraft.font);
                guiGraphics.drawString(var21, var22, var10003, var10004 + s * 9, -5592406, false);
            }
            ResourceLocation key = TRUpgrades.UPGRADE_DEFERRED_REGISTRY.getKey(upgradeEntry);
            String owner = Platform.getModName(key.getNamespace());
            guiGraphics.drawString(this.minecraft.font, ChatFormatting.BLUE +owner, m + 5, y + this.y + 9 + 17 + this.description.size() * 9 + 5, -1);
        }

        this.drawDisplayIcon(this.minecraft, guiGraphics, i + this.x + 8, j + this.y + 5);
    }


    public boolean isMouseOver(int x, int y, int mouseX, int mouseY) {
        int i = x + this.x;
        int j = i + 26;
        int k = y + this.y;
        int l = k + 26;
        return mouseX >= i && mouseX <= j && mouseY >= k && mouseY <= l;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

}
