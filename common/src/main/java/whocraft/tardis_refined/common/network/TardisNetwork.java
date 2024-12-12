package whocraft.tardis_refined.common.network;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.messages.*;
import whocraft.tardis_refined.common.network.messages.hums.C2SChangeHum;
import whocraft.tardis_refined.common.network.messages.player.S2CResetPostShellView;
import whocraft.tardis_refined.common.network.messages.player.C2SExitTardisView;
import whocraft.tardis_refined.common.network.messages.player.C2SBeginShellView;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenCraftingScreen;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncTardisPlayerView;
import whocraft.tardis_refined.common.network.messages.screens.C2SRequestShellSelection;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenMonitor;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenShellSelection;
import whocraft.tardis_refined.common.network.messages.sync.*;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;
import whocraft.tardis_refined.common.network.messages.upgrades.C2SUnlockUpgrade;
import whocraft.tardis_refined.common.network.messages.waypoints.*;

public class TardisNetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(new ResourceLocation(TardisRefined.MODID, "channel"));

    public static MessageType OPEN_CRAFTING_SCREEN, START_VORTEX_SESSION, END_VORTEX_SESSION, TARDIS_EXIT, OPEN_SHELL_SELECT, SYNC_HUMS, OPEN_WAYPOINTS_DISPLAY, DEL_WAYPOINT, CLIENT_OPEN_COORDS_DISPLAY, SERVER_OPEN_COORDS_DISPLAY, UPGRADE_SCREEN_S2C,
            REQUEST_SHELL_C2S, CLIENT_OPEN_COORDS_SCREEN, SERVER_OPEN_COORDS_SCREEN, CLIENT_OPEN_EDIT_COORDS_SCREEN, SERVER_OPEN_EDIT_COORDS_SCREEN, UPLOAD_WAYPOINT,
            EDIT_WAYPOINT, SET_WAYPOINT, CHANGE_HUM, REQUEST_WAYPOINTS, SYNC_DESKTOPS, SYNC_CONSOLE_PATTERNS, SYNC_SHELL_PATTERNS, SYNC_LEVELS, INT_REACTION,
            OPEN_MONITOR, CHANGE_SHELL, CHANGE_DESKTOP, CANCEL_CHANGE_DESKTOP, UNLOCK_UPGRADE, EJECT_PLAYER, TARDIS_PLAYER_INFO, CHANGE_VORTEX;

    public static void init() {
        // S2C Messages
        SYNC_LEVELS = NETWORK.registerS2C("sync_levels", S2CSyncLevelList::new);
        INT_REACTION = NETWORK.registerS2C("int_reaction", S2CSyncTardisClientData::new);
        OPEN_MONITOR = NETWORK.registerS2C("open_monitor", S2COpenMonitor::new);
        OPEN_SHELL_SELECT = NETWORK.registerS2C("open_shell_select", S2COpenShellSelection::new);
        SYNC_CONSOLE_PATTERNS = NETWORK.registerS2C("sync_console_patterns", S2CSyncConsolePatterns::new);
        SYNC_SHELL_PATTERNS = NETWORK.registerS2C("sync_shell_patterns", S2CSyncShellPatterns::new);
        SYNC_DESKTOPS = NETWORK.registerS2C("sync_desktop", S2CSyncDesktops::new);
        SERVER_OPEN_COORDS_DISPLAY = NETWORK.registerS2C("server_open_coords_display", S2COpenCoordinatesDisplayMessage::new);
        SERVER_OPEN_EDIT_COORDS_SCREEN = NETWORK.registerS2C("server_open_edit_coords_display", S2COpenEditCoordinatesDisplayMessage::new);
        OPEN_WAYPOINTS_DISPLAY = NETWORK.registerS2C("open_waypoints_display", S2CWaypointsListScreen::new);
        SERVER_OPEN_COORDS_SCREEN = NETWORK.registerS2C("server_open_coords_screen", S2COpenCoordinatesDisplayMessage::new);
        SYNC_HUMS = NETWORK.registerS2C("sync_hums", S2CSyncHums::new);
        UPGRADE_SCREEN_S2C = NETWORK.registerS2C("upgrade_screen_s2c", S2CDisplayUpgradeScreen::new);
        TARDIS_PLAYER_INFO = NETWORK.registerS2C("tardis_player_info", S2CSyncTardisPlayerView::new);
        END_VORTEX_SESSION = NETWORK.registerS2C("end_vortex_session", S2CResetPostShellView::new);
        OPEN_CRAFTING_SCREEN = NETWORK.registerS2C("open_crafting_screen", S2COpenCraftingScreen::new);

        // C2S Messages
        CHANGE_SHELL = NETWORK.registerC2S("change_shell", C2SChangeShell::new);
        CHANGE_VORTEX = NETWORK.registerC2S("change_vortex", C2SChangeVortex::new);
        CHANGE_DESKTOP = NETWORK.registerC2S("change_desktop", C2SChangeDesktop::new);
        CANCEL_CHANGE_DESKTOP = NETWORK.registerC2S("cancel_change_desktop", C2SCancelDesktopChange::new);
        REQUEST_WAYPOINTS = NETWORK.registerC2S("request_waypoints", C2SRequestWaypoints::new);
        SET_WAYPOINT = NETWORK.registerC2S("set_waypoint", C2STravelToWaypoint::new);
        UPLOAD_WAYPOINT = NETWORK.registerC2S("upload_waypoint", C2SUploadWaypoint::new);
        EDIT_WAYPOINT = NETWORK.registerC2S("edit_waypoint", C2SEditWaypoint::new);
        CLIENT_OPEN_COORDS_DISPLAY = NETWORK.registerC2S("client_open_coords_display", C2SOpenCoordinatesDisplayMessage::new);
        CLIENT_OPEN_EDIT_COORDS_SCREEN = NETWORK.registerC2S("client_open_edit_coords_display", C2SOpenEditCoordinatesDisplayMessage::new);
        DEL_WAYPOINT = NETWORK.registerC2S("del_waypoint", C2SRemoveWaypointEntry::new);
        CLIENT_OPEN_COORDS_SCREEN = NETWORK.registerC2S("client_open_coords_screen", C2SOpenCoordinatesDisplayMessage::new);
        UNLOCK_UPGRADE = NETWORK.registerC2S("unlock_upgrade", C2SUnlockUpgrade::new);
        REQUEST_SHELL_C2S = NETWORK.registerC2S("request_shell_c2s", C2SRequestShellSelection::new);
        CHANGE_HUM = NETWORK.registerC2S("change_hum", C2SChangeHum::new);
        EJECT_PLAYER = NETWORK.registerC2S("eject_player", C2SEjectPlayer::new);
        TARDIS_EXIT = NETWORK.registerC2S("tardis_exit", C2SExitTardisView::new);
        START_VORTEX_SESSION = NETWORK.registerC2S("start_vortex_session", C2SBeginShellView::new);
    }


}
