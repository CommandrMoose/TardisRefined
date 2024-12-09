package whocraft.tardis_refined.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.client.screen.CancelDesktopScreen;
import whocraft.tardis_refined.client.screen.MonitorScreen;
import whocraft.tardis_refined.client.screen.selections.ShellSelectionScreen;
import whocraft.tardis_refined.client.screen.upgrades.UpgradesScreen;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.client.screen.waypoints.WaypointListScreen;
import whocraft.tardis_refined.client.screen.waypoints.WaypointManageScreen;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.TardisWaypoint;

import java.util.Collection;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ScreenHandler {


    @Environment(EnvType.CLIENT)
    public static void setWaypointScreen(Collection<TardisWaypoint> waypoints) {
        Minecraft.getInstance().setScreen(new WaypointListScreen(waypoints));
    }

    @Environment(EnvType.CLIENT)
    public static void setCoordinatesScreen(List<ResourceKey<Level>> levels, CoordInputType coordInputType, TardisNavLocation tardisNavLocation) {
        Minecraft.getInstance().setScreen(new WaypointManageScreen(levels, coordInputType, tardisNavLocation));
    }

    @Environment(EnvType.CLIENT)
    public static void setEditCoordinatesScreen(TardisWaypoint waypoint) {
        Minecraft.getInstance().setScreen(new WaypointManageScreen(waypoint));
    }

    @Environment(EnvType.CLIENT)
    public static void openMonitorScreen(boolean desktopGenerating, CompoundTag upgradeHandlerNbt, TardisNavLocation currentLocation, TardisNavLocation targetLocation) {
        if (desktopGenerating) {
            Minecraft.getInstance().setScreen(new CancelDesktopScreen());
        } else {
            UpgradeHandler upgradeHandlerClient = new UpgradeHandler(new TardisLevelOperator(Minecraft.getInstance().level));
            upgradeHandlerClient.loadData(upgradeHandlerNbt);
            Minecraft.getInstance().setScreen(new MonitorScreen(currentLocation, targetLocation, upgradeHandlerClient));
        }
    }

    @Environment(EnvType.CLIENT)
    public static void openCoordinatesScreen(List<ResourceKey<Level>> levels, CoordInputType coordInputType, TardisNavLocation tardisNavLocation) {
        ScreenHandler.setCoordinatesScreen(levels, coordInputType, tardisNavLocation);
    }

    @Environment(EnvType.CLIENT)
    public static void openEditCoordinatesScreen(TardisWaypoint waypoint) {
        ScreenHandler.setEditCoordinatesScreen(waypoint);
    }

    @Environment(EnvType.CLIENT)
    public static void displayUpgradesScreen(CompoundTag upgradeTag) {
        UpgradeHandler upgradeHandlerClient = new UpgradeHandler(new TardisLevelOperator(Minecraft.getInstance().level));
        upgradeHandlerClient.loadData(upgradeTag);

        if (Minecraft.getInstance().screen instanceof UpgradesScreen screen && screen.selectedTab != null) {
            screen.selectedTab.populate(upgradeHandlerClient);
        } else {
            Minecraft.getInstance().setScreen(new UpgradesScreen(upgradeHandlerClient));
        }
    }

    @Environment(EnvType.CLIENT)
    public static void openShellSelection(ResourceLocation currentShell) {
        Minecraft.getInstance().setScreen(new ShellSelectionScreen(currentShell));
    }
}
