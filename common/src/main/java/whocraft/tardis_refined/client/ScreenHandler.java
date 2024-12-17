package whocraft.tardis_refined.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.client.screen.ponder.PonderListScreen;
import whocraft.tardis_refined.client.screen.screens.CancelDesktopScreen;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.client.screen.main.MonitorScreen;
import whocraft.tardis_refined.client.screen.screens.ShellSelectionScreen;
import whocraft.tardis_refined.client.screen.upgrades.UpgradesScreen;
import whocraft.tardis_refined.client.screen.waypoints.CoordInputType;
import whocraft.tardis_refined.client.screen.screens.WaypointListScreen;
import whocraft.tardis_refined.client.screen.screens.WaypointManageScreen;
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
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof MonitorOS) {
            ((MonitorOS) mc.screen).switchScreenToRight(new WaypointListScreen(waypoints));
            return;
        }
        Minecraft.getInstance().setScreen(new WaypointListScreen(waypoints));
    }

    @Environment(EnvType.CLIENT)
    public static void setCoordinatesScreen(List<ResourceKey<Level>> levels, CoordInputType coordInputType, TardisNavLocation tardisNavLocation) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof MonitorOS) {
            ((MonitorOS) mc.screen).switchScreenToRight(new WaypointManageScreen(levels, coordInputType, tardisNavLocation));
            return;
        }
        Minecraft.getInstance().setScreen(new WaypointManageScreen(levels, coordInputType, tardisNavLocation));
    }

    @Environment(EnvType.CLIENT)
    public static void setEditCoordinatesScreen(TardisWaypoint waypoint) {
        Minecraft.getInstance().setScreen(new WaypointManageScreen(waypoint));
    }


    @Environment(EnvType.CLIENT)
    public static void openMonitorScreen(boolean desktopGenerating, CompoundTag upgradeHandlerNbt, TardisNavLocation currentLocation, TardisNavLocation targetLocation, ResourceLocation currentShellTheme) {
        if (desktopGenerating) {
            Minecraft.getInstance().setScreen(new CancelDesktopScreen());
        } else {
            assert Minecraft.getInstance().level != null;
            UpgradeHandler upgradeHandlerClient = new UpgradeHandler(new TardisLevelOperator(Minecraft.getInstance().level));
            upgradeHandlerClient.loadData(upgradeHandlerNbt);
            Minecraft.getInstance().setScreen(new MonitorScreen(currentLocation, targetLocation, upgradeHandlerClient, currentShellTheme));
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
        assert Minecraft.getInstance().level != null;
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
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof MonitorOS) {
            ((MonitorOS) mc.screen).switchScreenToRight(new ShellSelectionScreen(currentShell));
            return;
        }
        Minecraft.getInstance().setScreen(new ShellSelectionScreen(currentShell));
    }

    @Environment(EnvType.CLIENT)
    public static void openCraftingScreen() {
        Minecraft.getInstance().setScreen(new PonderListScreen());
    }
}
