package whocraft.tardis_refined.client.screen.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.CommonTRWidgets;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SEditWaypoint;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SRequestWaypoints;
import whocraft.tardis_refined.common.network.messages.waypoints.C2SUploadWaypoint;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.constants.ModMessages;

import java.awt.*;
import java.util.List;

public class WaypointManageScreen extends MonitorOS {

    private final CoordInputType coordInputType;
    protected EditBox waypointName;
    private TardisWaypoint preExistingWaypoint = null;
    private final TardisNavLocation tardisNavLocation;
    private SpriteIconButton onSaveWaypoint;

    public WaypointManageScreen(List<ResourceKey<Level>> ignoredWorlds, CoordInputType coordInputType, TardisNavLocation tardisNavLocation) {
        super(Component.translatable(coordInputType == CoordInputType.WAYPOINT ? ModMessages.UI_MONITOR_UPLOAD_WAYPOINTS : ModMessages.UI_MONITOR_UPLOAD_COORDS), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
        this.coordInputType = coordInputType;
        this.tardisNavLocation = tardisNavLocation;
        tardisNavLocation.setName("Waypoint");
    }

    public WaypointManageScreen(TardisWaypoint waypoint) {
        super(Component.translatable("Edit waypoint"), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
        this.preExistingWaypoint = waypoint;
        this.tardisNavLocation = waypoint.getLocation();
        this.coordInputType = CoordInputType.WAYPOINT;

        this.setEvents(() -> {

        }, () -> {
            new C2SRequestWaypoints().send();
        });

    }

    @Override
    protected void init() {
        super.init();

        int widgetHeight = 20;
        int waypointNameWidth = monitorWidth / 2;
        int waypointNameHeight = this.height / 2;
        int yPosition = height / 2;
        int xPosition = this.width / 2 - (waypointNameWidth / 2);

        yPosition += 30;

        onSaveWaypoint = this.addRenderableWidget(CommonTRWidgets.imageButton(waypointNameWidth, Component.translatable(ModMessages.SUBMIT), (arg) -> {

            if (preExistingWaypoint != null) {
                tardisNavLocation.setName(this.waypointName.getValue());
                preExistingWaypoint.setLocation(tardisNavLocation);
                new C2SEditWaypoint(preExistingWaypoint).send();
                new C2SRequestWaypoints().send();
            } else {
                new C2SUploadWaypoint(tardisNavLocation, coordInputType).send();
                new C2SRequestWaypoints().send();
            }


        }, false, BUTTON_LOCATION));
        onSaveWaypoint.setPosition(xPosition, yPosition);
        addWidget(onSaveWaypoint);

        if (coordInputType == CoordInputType.WAYPOINT) {
            this.waypointName = new EditBox(this.font, xPosition, waypointNameHeight, waypointNameWidth, widgetHeight, this.waypointName, Component.translatable(ModMessages.VANILLA_SELECT_WORLD));

            if (this.preExistingWaypoint != null) {
                this.waypointName.setValue(this.preExistingWaypoint.getLocation().getName());
            } else {
                this.waypointName.setSuggestion(Component.translatable(ModMessages.UI_WAYPOINT_NAME_PLACEHOLDER).getString());
            }

            this.waypointName.setResponder((string) -> {
                if (!string.isEmpty()) {
                    tardisNavLocation.setName(string);
                    this.waypointName.setSuggestion("");
                }
            });
            // Waypoint Stuff
            this.addWidget(waypointName);

            int vPos = (height - monitorHeight) / 2;
            addCancelButton(width / 2 - 105, height - vPos - 25);

        }
    }

    @Override
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int i, int j, float f) {

        if (coordInputType == CoordInputType.WAYPOINT)
            this.waypointName.render(guiGraphics, i, j, f);

        onSaveWaypoint.active = !waypointName.getValue().isEmpty();

        int headerHeight = height / 2 - monitorHeight / 3;
        int starterCordHeight = height / 2 - monitorHeight / 3 + 7;
        int centerX = width / 2;

        String baseDirection = tardisNavLocation.getDirection().getName();
        String direction = baseDirection.substring(0, 1).toUpperCase() + baseDirection.substring(1);

        String dimensionName = MiscHelper.getCleanDimensionName(tardisNavLocation.getDimensionKey());

        ScreenHelper.renderWidthScaledText(Component.translatable(ModMessages.UI_WAYPOINT_NEW_WAYPOINT).getString(), guiGraphics, Minecraft.getInstance().font, centerX, headerHeight, Color.LIGHT_GRAY.getRGB(), 80, 1F, true);

        if (preExistingWaypoint == null)
            ScreenHelper.renderWidthScaledText(Component.translatable(ModMessages.UI_WAYPOINT_TAKEN).getString(), guiGraphics, Minecraft.getInstance().font, centerX, headerHeight + 10, Color.LIGHT_GRAY.getRGB(), 80, 1F, true);

        ScreenHelper.renderWidthScaledText(tardisNavLocation.getPosition().toShortString(), guiGraphics, Minecraft.getInstance().font, centerX, starterCordHeight + 15, Color.white.getRGB(), 80, 1F, true);
        ScreenHelper.renderWidthScaledText(direction + ", " + dimensionName, guiGraphics, Minecraft.getInstance().font, centerX, starterCordHeight + 25, Color.white.getRGB(), 100, 1F, true);

    }

    @Override
    public boolean keyPressed(int i, int j, int k) {
        return super.keyPressed(i, j, k);
    }

    @Override
    public boolean charTyped(char c, int i) {
        return super.charTyped(c, i);
    }

}
