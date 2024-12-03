package whocraft.tardis_refined.client.screen.upgrades;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.registry.TRUpgrades;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class UpgradeTab {

    public static final int GRID_SIZE = 30;
    public final UpgradeHandler upgradeHandler;
    private final Minecraft minecraft;
    private final UpgradesScreen screen;
    private final UpgradeTabType type;
    private final int index;
    private final Component title;
    private final List<UpgradeWidget> entries = new ArrayList<>();
    private final List<Connection> connections = new ArrayList<>();
    public float fade;
    private double scrollX;
    private double scrollY;
    private int minX = 2147483647;
    private int minY = 2147483647;
    private int maxX = -2147483648;
    private int maxY = -2147483648;
    private boolean centered;

    public UpgradeTab(Minecraft minecraft, UpgradesScreen UpgradesScreen, UpgradeTabType tabType, int i, UpgradeHandler powerHolder) {
        this.minecraft = minecraft;
        this.screen = UpgradesScreen;
        this.type = tabType;
        this.index = i;
        this.upgradeHandler = powerHolder;
        this.title = Component.literal("");
        this.populate(powerHolder);
    }

    @Nullable
    public static UpgradeTab create(Minecraft minecraft, UpgradesScreen screen, int tabIndex, UpgradeHandler upgradeHandler) {
        UpgradeTabType[] var4 = UpgradeTabType.values();

        for (UpgradeTabType tabType : var4) {
            if (tabIndex < tabType.getMax()) {
                return new UpgradeTab(minecraft, screen, tabType, tabIndex, upgradeHandler);
            }

            tabIndex -= tabType.getMax();
        }

        return null;
    }

    public void populate(UpgradeHandler upgradeHandlerClient) {
        this.entries.clear();
        this.connections.clear();
        CompoundTag newData = upgradeHandlerClient.saveData(new CompoundTag());
        this.upgradeHandler.loadData(newData);
        List<UpgradeWidget> root = new LinkedList<>();

        // Create entry for each ability
        for (Map.Entry<ResourceKey<Upgrade>, Upgrade> entry : TRUpgrades.UPGRADE_DEFERRED_REGISTRY.entrySet()) {
            Upgrade upgrade = entry.getValue();
            var widget = new UpgradeWidget(this, this.minecraft, upgradeHandlerClient, upgrade).setPosition(0, 0);
            this.entries.add(widget);
            var pos = upgrade.getScreenPosition();

            if (pos != null) {
                widget.setPositionFixed(pos.x, pos.y);
            }
        }

        // Find parents and children for each
        for (UpgradeWidget entry : this.entries) {
            entry.updateRelatives(this.entries);
        }


        // Locate and set first row
        int y = 0;
        for (UpgradeWidget entry : this.entries) {
            if (entry.parents.isEmpty()) {
                if (!entry.fixedPosition) {
                    entry.updatePosition(0, y, this);
                    y++;
                }
                root.add(entry);
            }
        }

        int longest = longestRow();

        // Set position for children
        for (int j = 0; j < root.size(); j++) {
            for (UpgradeWidget parent : root) {
                for (UpgradeWidget child : parent.children) {
                    if (!child.fixedPosition && parent.gridX == child.gridX) {
                        child.setPosition(child.gridX + 1, getFreeYPos(child.gridX + 1, parent.gridY));
                    }
                }
            }
        }

        // Last Adjustments
        for (int x = 0; x < 100; x++) {
            List<UpgradeWidget> entries = getEntriesAtX(x);
            for (int n = 0; n < entries.size(); n++) {
                UpgradeWidget entry = entries.get(n);
                if (!entry.fixedPosition) {
                    entry.setPosition(entry.gridX, (longest / 2D) - (entries.size() / 2D) + n);
                }
            }
        }

        // Fixing min & max size; make lines
        for (UpgradeWidget entry : this.entries) {
            this.minX = (int) Math.min((entry.gridX - 1) * GRID_SIZE, this.minX);
            this.minY = (int) Math.min((entry.gridY - 1) * GRID_SIZE, this.minY);
            this.maxX = (int) Math.max((entry.gridX + 1) * GRID_SIZE, this.maxX);
            this.maxY = (int) Math.max((entry.gridY + 1) * GRID_SIZE, this.maxY);

            for (UpgradeWidget child : entry.children) {
                Connection connection = new Connection();
                int startX = toCoord(entry.gridX);
                int startY = toCoord(entry.gridY, 1D / (entry.children.size() + 1) * (entry.children.indexOf(child) + 1));
                int endX = toCoord(child.gridX);
                int endY = toCoord(child.gridY, 1D / (child.parents.size() + 1) * (child.parents.indexOf(entry) + 1));

                if (this.getEntry(child.gridX, entry.gridY) == null) {
                    connection.addLine(new ConnectionLine(startX, startY, endX, startY));
                    connection.addLine(new ConnectionLine(endX, startY, endX, endY));
                } else {
                    connection.addLine(new ConnectionLine(startX, startY, startX, endY));
                    connection.addLine(new ConnectionLine(startX, endY, endX, endY));
                }

                connection.color = entry.upgradeEntry.isUnlocked(upgradeHandler) ? new Color(ChatFormatting.WHITE.getColor()) : Color.GRAY;
                this.connections.add(connection);
            }
        }
    }

    private int toCoord(double d) {
        return toCoord(d, 0.5D);
    }

    private int toCoord(double d, double height) {
        return (int) ((d - 0.5D) * GRID_SIZE + (GRID_SIZE - 16) / 2D + (16 * height));
    }

    private int longestRow() {
        int l = 0;
        for (int i = 0; i < 100; i++) {
            int k = getEntriesAtX(i).size();
            l = Math.max(l, k);
        }
        return l;
    }

    public List<UpgradeWidget> getEntriesAtX(double x) {
        List<UpgradeWidget> list = new LinkedList<>();
        for (UpgradeWidget entry : this.entries) {
            if (entry.gridX == x) {
                list.add(entry);
            }
        }

        return list;
    }

    public UpgradeWidget getEntry(double x, double y) {
        for (UpgradeWidget entry : this.entries) {
            if (entry.gridX == x && entry.gridY == y) {
                return entry;
            }
        }

        return null;
    }

    public double getFreeYPos(double x, double y) {
        for (int i = (int) y; i < 100; i++) {
            if (getEntry(x, i) == null) {
                return i;
            }
        }

        return 0;
    }

    public UpgradeTabType getType() {
        return this.type;
    }

    public int getIndex() {
        return this.index;
    }

    public Component getTitle() {
        return this.title;
    }

    public void drawTab(GuiGraphics guiGraphics, int offsetX, int offsetY, boolean isSelected) {
        this.type.draw(guiGraphics, offsetX, offsetY, isSelected, this.index);
    }

    public void drawIcon(GuiGraphics guiGraphics, int offsetX, int offsetY) {
        //TODO Render iTem this.type.drawIcon(guiGraphics, DataContext.forPower(this.minecraft.player, this.powerHolder), offsetX, offsetY, this.index, this.icon);
    }

    public void drawContents(GuiGraphics guiGraphics, int x, int y) {
        if (!this.centered) {
            this.scrollX = 117 - (this.maxX + this.minX) / 2D;
            this.scrollY = 56 - (this.maxY + this.minY) / 2D;
            this.centered = true;
        }

        guiGraphics.enableScissor(x, y, x + UpgradesScreen.WINDOW_INSIDE_WIDTH, y + UpgradesScreen.WINDOW_INSIDE_HEIGHT);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate((float) x, (float) y, 0.0F);

        int i = Mth.floor(this.scrollX);
        int j = Mth.floor(this.scrollY);
        int k = i % 16;
        int l = j % 16;


        for (Connection connection : this.connections) {
            connection.drawOutlines(this, guiGraphics, i, j);
        }

        for (Connection connection : this.connections) {
            connection.draw(this, guiGraphics, i, j);
        }

        for (UpgradeWidget widget : this.entries) {

            widget.drawIcon(this.minecraft, guiGraphics, i + widget.getX() + 16, j + widget.getY() + 13);
        }

        guiGraphics.pose().popPose();
        guiGraphics.disableScissor();
    }

    public void drawTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, int width, int height, boolean overlayActive) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, -200.0F);
        guiGraphics.fill(0, 0, UpgradesScreen.WINDOW_INSIDE_WIDTH, UpgradesScreen.WINDOW_INSIDE_HEIGHT, Mth.floor(this.fade * 255.0F) << 24);
        boolean flag = false;

        if (!overlayActive) {
            int i = Mth.floor(this.scrollX);
            int j = Mth.floor(this.scrollY);
            if (mouseX > 0 && mouseX < UpgradesScreen.WINDOW_INSIDE_WIDTH && mouseY > 0 && mouseY < UpgradesScreen.WINDOW_INSIDE_HEIGHT) {

                for (UpgradeWidget widget : this.entries) {
                    if (widget.isMouseOver(i, j, mouseX, mouseY)) {
                        flag = true;
                        widget.drawHover(guiGraphics, i, j, this.fade, width, height);
                        break;
                    }
                }
            }
        }

        guiGraphics.pose().popPose();

        if (!overlayActive) {
            if (flag) {
                this.fade = Mth.clamp(this.fade + 0.02F, 0.0F, 0.3F);
            } else {
                this.fade = Mth.clamp(this.fade - 0.04F, 0.0F, 1.0F);
            }
        }
    }

    public UpgradeWidget getUpgradeHoveredOver(int mouseX, int mouseY, int x, int y) {
        int i = Mth.floor(this.scrollX);
        int j = Mth.floor(this.scrollY);
        if (mouseX > 0 && mouseX < UpgradesScreen.WINDOW_INSIDE_WIDTH && mouseY > 0 && mouseY < UpgradesScreen.WINDOW_INSIDE_HEIGHT) {
            for (UpgradeWidget entry : this.entries) {
                if (entry.isMouseOver(i, j, mouseX, mouseY)) {
                    return entry;
                }
            }
        }
        return null;
    }

    public boolean isMouseOver(int offsetX, int offsetY, double mouseX, double mouseY) {
        return this.type.isMouseOver(offsetX, offsetY, this.index, mouseX, mouseY);
    }

    public void scroll(double dragX, double dragY) {
        if (this.maxX - this.minX > UpgradesScreen.WINDOW_INSIDE_WIDTH) {
            this.scrollX = Mth.clamp(this.scrollX + dragX, -(this.maxX - UpgradesScreen.WINDOW_INSIDE_WIDTH), -this.minX);
        }

        if (this.maxY - this.minY > UpgradesScreen.WINDOW_INSIDE_HEIGHT) {
            this.scrollY = Mth.clamp(this.scrollY + dragY, -(this.maxY - UpgradesScreen.WINDOW_INSIDE_HEIGHT), -this.minY);
        }

    }

    public UpgradesScreen getScreen() {
        return this.screen;
    }

    public static class Connection {

        public Color color = Color.YELLOW;
        public List<ConnectionLine> lines = new LinkedList<>();

        public Connection(List<ConnectionLine> lines) {
            this.lines = lines;
        }

        public Connection() {

        }

        public Connection addLine(ConnectionLine line) {
            this.lines.add(line);
            return this;
        }

        public void drawOutlines(UpgradeTab gui, GuiGraphics guiGraphics, int x, int y) {
            for (ConnectionLine lines : this.lines) {
                lines.draw(gui, guiGraphics, x, y, true, Color.BLACK);
            }
        }

        public void draw(UpgradeTab gui, GuiGraphics guiGraphics, int x, int y) {
            for (ConnectionLine lines : this.lines) {
                lines.draw(gui, guiGraphics, x, y, false, this.color);
            }
        }

    }

    public static class ConnectionLine {

        public int startX, startY, endX, endY;

        public ConnectionLine(int startX, int startY, int endX, int endY) {
            this.startX = Math.min(startX, endX);
            this.startY = Math.min(startY, endY);
            this.endX = Math.max(startX, endX);
            this.endY = Math.max(startY, endY);
        }

        public void draw(UpgradeTab upgradeTab, GuiGraphics guiGraphics, int x, int y, boolean outline, Color color) {
            // AARRGGBB
            int colorCode = color.getRGB();
            if (outline) {
                if (this.startY == endY) {
                    //hLine
                    guiGraphics.hLine(x + startX - 2, x + endX + 1, y + startY - 2, colorCode);
                    guiGraphics.hLine(x + startX - 2, x + endX + 1, y + startY + 1, colorCode);
                } else if (this.startX == endX) {
                    //vLine
                    guiGraphics.vLine(x + startX - 2, y + startY - 2, y + endY + 1, colorCode);
                    guiGraphics.vLine(x + startX + 1, y + startY - 2, y + endY + 1, colorCode);
                }
            } else {
                if (this.startY == endY) {
                    guiGraphics.hLine(x + startX - 1, x + endX, y + startY - 1, colorCode);
                    guiGraphics.hLine(x + startX - 1, x + endX, y + startY, colorCode);
                } else if (this.startX == endX) {
                    guiGraphics.vLine(x + startX - 1, y + startY - 1, y + endY, colorCode);
                    guiGraphics.vLine(x + startX, y + startY - 1, y + endY, colorCode);
                }
            }
        }

    }
}
