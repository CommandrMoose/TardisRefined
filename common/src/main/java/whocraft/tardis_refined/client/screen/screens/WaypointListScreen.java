package whocraft.tardis_refined.client.screen.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.CommonTRWidgets;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SOpenCoordinatesDisplayMessage;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SOpenEditCoordinatesDisplayMessage;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SRemoveWaypointEntry;
import whocraft.tardis_refined.common.network.messages.waypoints.C2STravelToWaypoint;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.constants.ModMessages;

import java.awt.*;
import java.util.Collection;
import java.util.Comparator;


public class WaypointListScreen extends MonitorOS {

    public static final ResourceLocation TRASH_LOCATION = new ResourceLocation(TardisRefined.MODID, "trash");
    public static final ResourceLocation OKAY_TEXTURE = new ResourceLocation(TardisRefined.MODID, "okay");
    public static final ResourceLocation EDIT_TEXTURE = new ResourceLocation(TardisRefined.MODID, "edit");
    private final Component noWaypointsLabel = Component.translatable(ModMessages.UI_MONITOR_NO_WAYPOINTS);
    private SpriteIconButton loadButton;
    private SpriteIconButton editButton;
    private SpriteIconButton trashButton;
    private final Collection<TardisWaypoint> WAYPOINTS;
    private TardisWaypoint waypoint = null;

    public WaypointListScreen(Collection<TardisWaypoint> waypoints) {
        super(Component.translatable(ModMessages.UI_MONITOR_MAIN_TITLE), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
        this.WAYPOINTS = waypoints;
    }

    @Override
    protected void init() {
        super.init();

        setEvents(() -> {
            if (waypoint != null)
                new C2STravelToWaypoint(waypoint.getId()).send();
        }, () -> {
            if (waypoint != null)
                new C2SRemoveWaypointEntry(waypoint.getId()).send();
            this.switchScreenToLeft(PREVIOUS);

        });


        SpriteIconButton newWaypointButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> new C2SOpenCoordinatesDisplayMessage(CoordInputType.WAYPOINT).send(), true, BUTTON_LOCATION));

        newWaypointButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_CREATE)));
        newWaypointButton.setPosition(width / 2 + 85, (height) / 2 - 60);

        int vPos = (height - monitorHeight) / 2;
        addCancelButton(width / 2 - 105, height - vPos - 25);

        this.loadButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> {
            if (waypoint != null) {

                new C2STravelToWaypoint(waypoint.getId()).send();
                Minecraft.getInstance().setScreen(null);
            }
        }, true, OKAY_TEXTURE));
        this.loadButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_LOAD)));
        this.loadButton.setPosition(width / 2 + 85, (height) / 2);

        this.loadButton.active = false;


        this.editButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Edit"), (arg) -> {
            if (waypoint != null) {
                new C2SOpenEditCoordinatesDisplayMessage(waypoint.getId()).send();
                Minecraft.getInstance().setScreen(null);
            }
        }, true, EDIT_TEXTURE));
        this.editButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_EDIT)));
        this.editButton.setPosition(width / 2 + 85, (height) / 2 - 40);

        this.editButton.active = false;

        this.trashButton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> new C2SRemoveWaypointEntry(waypoint.getId()).send(), true, TRASH_LOCATION));

        this.trashButton.setPosition(width / 2 + 85, (height) / 2 - 20);
        this.trashButton.setTooltip(Tooltip.create(Component.translatable(ModMessages.UI_MONITOR_WAYPOINT_DELETE)));
        this.trashButton.active = false;
    }

    @Override
    public GenericMonitorSelectionList<SelectionListEntry> createSelectionList() {
        int vPos = (height - monitorHeight) / 2;
        int leftPos = this.width / 2 - 75;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 150, 80, leftPos, vPos + 15, vPos + monitorHeight - 30, 12);
        selectionList.setRenderBackground(false);

        Collection<TardisWaypoint> values = WAYPOINTS;
        values = values.stream().sorted(Comparator.comparing(TardisWaypoint::getName)).toList();

        for (TardisWaypoint waypointEntry : values) {
            selectionList.children().add(new SelectionListEntry(Component.literal(waypointEntry.getLocation().getName()), entry -> {
                entry.setChecked(true);
                this.waypoint = waypointEntry;
                this.loadButton.active = true;
                this.editButton.active = true;
                this.trashButton.active = true;

                for (SelectionListEntry child : selectionList.children()) {
                    if (child != entry) {
                        child.setChecked(false);
                    }
                }

            }, leftPos));
        }

        return selectionList;
    }

    @Override
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int textOffset = height / 2 - 60;
        int textScale = 40;

        if (WAYPOINTS.isEmpty())
            ScreenHelper.renderWidthScaledText(noWaypointsLabel.getString(), guiGraphics, Minecraft.getInstance().font, width / 2f - 96, textOffset + 15, Color.LIGHT_GRAY.getRGB(), textScale * 2, 1F, false);

    }
}
