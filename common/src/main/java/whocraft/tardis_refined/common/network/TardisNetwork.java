package whocraft.tardis_refined.common.network;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.messages.CancelDesktopChangeMessage;
import whocraft.tardis_refined.common.network.messages.ChangeDesktopMessage;
import whocraft.tardis_refined.common.network.messages.ChangeShellMessage;
import whocraft.tardis_refined.common.network.messages.EjectPlayerFromConsoleMessage;
import whocraft.tardis_refined.common.network.messages.hums.ChangeHumMessage;
import whocraft.tardis_refined.common.network.messages.player.EndPlayerVortexSessionMessage;
import whocraft.tardis_refined.common.network.messages.player.ExitTardisViewMessage;
import whocraft.tardis_refined.common.network.messages.player.StartVortexSessionMessage;
import whocraft.tardis_refined.common.network.messages.player.SyncTardisPlayerInfoMessage;
import whocraft.tardis_refined.common.network.messages.screens.C2SRequestShellSelection;
import whocraft.tardis_refined.common.network.messages.screens.OpenMonitorMessage;
import whocraft.tardis_refined.common.network.messages.screens.OpenShellSelectionScreen;
import whocraft.tardis_refined.common.network.messages.screens.*;
import whocraft.tardis_refined.common.network.messages.sync.*;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;
import whocraft.tardis_refined.common.network.messages.upgrades.UnlockUpgradeMessage;
import whocraft.tardis_refined.common.network.messages.waypoints.*;

public class TardisNetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(new ResourceLocation(TardisRefined.MODID, "channel"));

    public static MessageType START_VORTEX_SESSION, END_VORTEX_SESSION, TARDIS_EXIT, OPEN_SHELL_SELECT, SYNC_HUMS, OPEN_WAYPOINTS_DISPLAY, DEL_WAYPOINT, CLIENT_OPEN_COORDS_DISPLAY, SERVER_OPEN_COORDS_DISPLAY, UPGRADE_SCREEN_S2C,
            REQUEST_SHELL_C2S, CLIENT_OPEN_COORDS_SCREEN, SERVER_OPEN_COORDS_SCREEN, CLIENT_OPEN_EDIT_COORDS_SCREEN, SERVER_OPEN_EDIT_COORDS_SCREEN, UPLOAD_WAYPOINT,
            EDIT_WAYPOINT, SET_WAYPOINT, CHANGE_HUM, REQUEST_WAYPOINTS, SYNC_DESKTOPS, SYNC_CONSOLE_PATTERNS, SYNC_SHELL_PATTERNS, SYNC_LEVELS, INT_REACTION,
            OPEN_MONITOR, CHANGE_SHELL, CHANGE_DESKTOP, CANCEL_CHANGE_DESKTOP, UNLOCK_UPGRADE, EJECT_PLAYER, MONITOR_POSITION_DATA, MONITOR_CLOSED, TARDIS_PLAYER_INFO;

    public static void init() {
        // S2C Messages
        SYNC_LEVELS = NETWORK.registerS2C("sync_levels", SyncLevelListMessage::new);
        INT_REACTION = NETWORK.registerS2C("int_reaction", SyncTardisClientDataMessage::new);
        OPEN_MONITOR = NETWORK.registerS2C("open_monitor", OpenMonitorMessage::new);
        OPEN_SHELL_SELECT = NETWORK.registerS2C("open_shell_select", OpenShellSelectionScreen::new);
        SYNC_CONSOLE_PATTERNS = NETWORK.registerS2C("sync_console_patterns", SyncConsolePatternsMessage::new);
        SYNC_SHELL_PATTERNS = NETWORK.registerS2C("sync_shell_patterns", SyncShellPatternsMessage::new);
        SYNC_DESKTOPS = NETWORK.registerS2C("sync_desktop", SyncDesktopsMessage::new);
        SERVER_OPEN_COORDS_DISPLAY = NETWORK.registerS2C("server_open_coords_display", S2COpenCoordinatesDisplayMessage::new);
        SERVER_OPEN_EDIT_COORDS_SCREEN = NETWORK.registerS2C("server_open_edit_coords_display", S2COpenEditCoordinatesDisplayMessage::new);
        OPEN_WAYPOINTS_DISPLAY = NETWORK.registerS2C("open_waypoints_display", WaypointsListScreenMessage::new);
        SERVER_OPEN_COORDS_SCREEN = NETWORK.registerS2C("server_open_coords_screen", S2COpenCoordinatesDisplayMessage::new);
        SYNC_HUMS = NETWORK.registerS2C("sync_hums", SyncHumsMessage::new);
        UPGRADE_SCREEN_S2C = NETWORK.registerS2C("upgrade_screen_s2c", S2CDisplayUpgradeScreen::new);
        TARDIS_PLAYER_INFO = NETWORK.registerS2C("tardis_player_info", SyncTardisPlayerInfoMessage::new);
        END_VORTEX_SESSION = NETWORK.registerS2C("end_vortex_session", EndPlayerVortexSessionMessage::new);
        MONITOR_POSITION_DATA = NETWORK.registerS2C("monitor_position_data", MonitorPositionDataMessage::new);

        // C2S Messages
        CHANGE_SHELL = NETWORK.registerC2S("change_shell", ChangeShellMessage::new);
        CHANGE_DESKTOP = NETWORK.registerC2S("change_desktop", ChangeDesktopMessage::new);
        CANCEL_CHANGE_DESKTOP = NETWORK.registerC2S("cancel_change_desktop", CancelDesktopChangeMessage::new);
        REQUEST_WAYPOINTS = NETWORK.registerC2S("request_waypoints", RequestWaypointsMessage::new);
        SET_WAYPOINT = NETWORK.registerC2S("set_waypoint", TravelToWaypointMessage::new);
        UPLOAD_WAYPOINT = NETWORK.registerC2S("upload_waypoint", UploadWaypointMessage::new);
        EDIT_WAYPOINT = NETWORK.registerC2S("edit_waypoint", EditWaypointMessage::new);
        CLIENT_OPEN_COORDS_DISPLAY = NETWORK.registerC2S("client_open_coords_display", C2SOpenCoordinatesDisplayMessage::new);
        CLIENT_OPEN_EDIT_COORDS_SCREEN = NETWORK.registerC2S("client_open_edit_coords_display", C2SOpenEditCoordinatesDisplayMessage::new);
        DEL_WAYPOINT = NETWORK.registerC2S("del_waypoint", RemoveWaypointEntryMessage::new);
        CLIENT_OPEN_COORDS_SCREEN = NETWORK.registerC2S("client_open_coords_screen", C2SOpenCoordinatesDisplayMessage::new);
        UNLOCK_UPGRADE = NETWORK.registerC2S("unlock_upgrade", UnlockUpgradeMessage::new);
        REQUEST_SHELL_C2S = NETWORK.registerC2S("request_shell_c2s", C2SRequestShellSelection::new);
        CHANGE_HUM = NETWORK.registerC2S("change_hum", ChangeHumMessage::new);
        EJECT_PLAYER = NETWORK.registerC2S("eject_player", EjectPlayerFromConsoleMessage::new);
        TARDIS_EXIT = NETWORK.registerC2S("tardis_exit", ExitTardisViewMessage::new);
        START_VORTEX_SESSION = NETWORK.registerC2S("start_vortex_session", StartVortexSessionMessage::new);
        MONITOR_CLOSED = NETWORK.registerC2S("monitor_closed", C2SMonitorClosed::new);
    }


}
