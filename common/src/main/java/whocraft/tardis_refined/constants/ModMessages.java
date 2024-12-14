package whocraft.tardis_refined.constants;

import whocraft.tardis_refined.TardisRefined;

public class ModMessages {


    /*UI Messages*/
    public static final String UI_DESKTOP_CANCEL_DESKTOP = ui("monitor.cancel_desktop");
    public static final String UI_DESKTOP_CANCEL_TITLE = ui("monitor.desktop_cancel.title");
    public static final String UI_DESKTOP_CANCEL = ui("monitor.desktop_cancel");
    public static final String UI_DESKTOP_CANCEL_DESCRIPTION = ui("monitor.desktop_cancel_description");
    public static final String UI_EXTERNAL_SHELL = ui("monitor.external_shell");
    public static final String UI_LIST_SELECTION = ui("monitor.list.selection");
    public static final String UI_DESKTOP_CONFIGURATION = ui("monitor.desktop");
    public static final String UI_SHELL_SELECTION = ui("shell_selection");
    public static final String UI_MONITOR_GPS = ui("monitor.main.gps");
    public static final String UI_MONITOR_VORTEX = ui("monitor.main.vortex");
    public static final String UI_MONITOR_DESTINATION = ui("monitor.main.destination");
    public static final String UI_MONITOR_MAIN_TITLE = ui("monitor.main_title");
    public static final String UI_MONITOR_WAYPOINTS = ui("monitor.waypoints");
    public static final String UI_MONITOR_SHELL_VIEW = ui("monitor.shell_view");
    public static final String UI_MONITOR_UPLOAD_WAYPOINTS = ui("monitor.upload.waypoints");
    public static final String UI_MONITOR_UPLOAD_COORDS = ui("monitor.upload.coords");
    public static final String UI_MONITOR_SELECT_HUM = ui("monitor.select.hum");
    public static final String UI_MONITOR_EJECT = ui("monitor.select.eject");
    public static final String UI_EJECT_CANNOT_IN_FLIGHT = ui("monitor.select.eject_fail");
    public static final String UI_MONITOR_NO_WAYPOINTS = ui("monitor.no.waypoints");
    public static final String UI_MONITOR_WAYPOINT_UPLOAD = ui("monitor.waypoints.upload");
    public static final String UI_MONITOR_WAYPOINT_CREATE = ui("monitor.waypoints.create");
    public static final String UI_MONITOR_WAYPOINT_LOAD = ui("monitor.waypoints.load");
    public static final String UI_MONITOR_WAYPOINT_EDIT = ui("monitor.waypoints.edit");
    public static final String UI_MONITOR_WAYPOINT_DELETE = ui("monitor.waypoints.delete");
    public static final String VANILLA_SELECT_WORLD = "selectWorld.search";
    public static final String UI_WAYPOINT_NAME_PLACEHOLDER = "monitor.waypoints.name.placeholder";
    public static final String UI_WAYPOINT_NEW_WAYPOINT = "monitor.waypoints.new_waypoint";
    public static final String UI_WAYPOINT_TAKEN = "monitor.waypoints.taken";
    public static final String UI_MONITOR_ISSUES = ui("monitor.waypoints.issues");
    public static final String UI_MONITOR_WAYPOINT_ISSUE_NAME = ui("monitor.waypoints.issues.name");
    public static final String UI_MONITOR_WAYPOINT_ISSUE_X = ui("monitor.waypoints.issues.x");
    public static final String UI_MONITOR_WAYPOINT_ISSUE_Y = ui("monitor.waypoints.issues.y");
    public static final String UI_MONITOR_WAYPOINT_ISSUE_Z = ui("monitor.waypoints.issues.z");
    public static final String UI_MONITOR_WAYPOINT_NAME = ui("monitor.waypoint_name");
    public static final String UI_UPGRADES = ui("upgrades");
    public static final String UI_UPGRADES_BUY = ui("upgrades.buy_ability");
    public static final String UI_NO_INSTALLED_SUBSYSTEMS = ui("no_installed_subsystems");
    public static final String CONFIG_IP_COMPAT = config("immersive_portals");
    public static final String CONFIG_CONTROL_NAMES = config("control_names");
    public static final String CONFIG_IDLE_CONSOLE_ANIMS = config("console_idle_animations");
    public static final String CONFIG_BANNED_DIMENSIONS = config("banned_dimensions");
    public static final String CMD_DIM_NOT_A_TARDIS = cmdOutput("dim_not_a_tardis");
    public static final String CMD_NO_INTERNAL_DOOR = cmdOutput("no_internal_door");
    public static final String CMD_EXPORT_DESKTOP_IN_PROGRESS = cmdOutput("export_desktop.in_progress");
    public static final String CMD_EXPORT_DESKTOP_SUCCESS = cmdOutput("export_desktop.success");
    public static final String CMD_EXPORT_DESKTOP_RESOURCE_PACK = cmdOutput("export_desktop.resource_pack");
    public static final String CMD_EXPORT_DESKTOP_FAIL = cmdOutput("export_desktop.fail");
    public static final String CMD_LEVEL_POINT_GET = cmdOutput("level.point.get");
    public static final String CMD_LEVEL_POINT_SET = cmdOutput("level.point.set");
    public static final String CMD_LEVEL_POINT_ADD = cmdOutput("level.point.add");
    public static final String CMD_LEVEL_XP_GET = cmdOutput("level.xp.get");
    public static final String CMD_LEVEL_XP_SET = cmdOutput("level.xp.set");
    public static final String CMD_LEVEL_XP_ADD = cmdOutput("level.xp.add");
    public static final String CMD_UPGRADE_LOCK = cmdOutput("upgrade.lock");
    public static final String CMD_UPGRADE_UNLOCK = cmdOutput("upgrade.unlock");
    public static final String CMD_CREATE_TARDIS_IN_PROGRESS = cmdOutput("create.in_progress");
    public static final String CMD_CREATE_TARDIS_SUCCESS = cmdOutput("create.success");
    public static final String CMD_ARG_UPGRADE_INVALID = cmdArgInvalid("upgrade");
    public static final String CMD_ARG_DESKTOP_INVALID = cmdArgInvalid("desktop");
    public static final String CMD_ARG_SHELL_INVALID = cmdArgInvalid("shell");
    public static final String KEYBIND_EXIT_VIEW = keybind("exit_exterior_view");
    public static final String KEYBIND_TOGGLE_INFO_EXTERIOR_VIEW = keybind("toggle_info_exterior_view");
    public static String GITHUB_RELEASE_PAGE = "https://github.com/WhoCraft/TardisRefined/releases";

    /*Item Special cases*/
    public static String ITEM_KEYCHAIN = "item." + TardisRefined.MODID + ".keychain";
    public static String ITEM_GROUP = "itemGroup." + TardisRefined.MODID;

    /*Messages*/
    public static String MSG_EXTERIOR_COOLDOWN = message("exterior_cooldown");
    public static String MSG_KEY_BOUND = message("key_bound");
    public static String MSG_KEY_CYCLED = message("key_cycled");
    public static String CONTROL_DIMENSION_SELECTED = message("selected");
    public static String HARDWARE_OFFLINE = message("hardware_offline");
    public static String ASCEND_KEY = message("ascend_key");
    public static String DESCEND_KEY = message("descend_key");
    public static String EXIT_EXTERNAL_VIEW = message("exit_external_view");
    public static String NO_FLIGHT_TRANSITIVE = message("no_flight_transitive");
    public static String HANDBRAKE_ENGAGED = message("handbrake_engaged");
    public static String HANDBRAKE_DISENGAGED = message("handbrake_disengaged");
    public static String HANDBRAKE = message("handbrake");
    public static String HANDBRAKE_WARNING = message("handbrake_warning");
    public static String CONSOLE_CONFIGURATION_NOT_IN_FLIGHT = message("console_config_not_in_flight");
    public static String CONSOLE_NOT_IN_FLIGHT = message("cannot_switch_console_units_whilst_in_flight");
    public static String NO_END_DRAGON_PREVENTS = message("no_end_dragon_prevents");
    public static String TARDIS_IS_ON_THE_WAY = message("tardis_on_the_way");
    public static String LANDING_PAD_NOT_UNLOCKED = message("landing_pad_unlocked");
    public static String LANDING_PAD_TRANSIENT = message("landing_pad_transient");
    public static String RECOVERY_PROGRESS = message("recovery_progress");
    public static String FUEL = message("fuel");
    public static String REFUEL = message("refuel_engaged");
    public static String STOP_REFUEL = message("refuel_stopped");
    public static String FUEL_OFFLINE = message("fuel_offline");
    public static String DOOR_LOCKED = message("door_locked");
    public static String DOOR_UNLOCKED = message("door_unlocked");
    public static String NO_DESKTOP_NO_FUEL = message("desktop_cancel_not_enough_fuel");
    public static String ASTRAL_MANIPULATOR_ENGAGED = message("astral_manipulator_engaged");
    public static String ROOT_PLANT_CUT_OPEN = message("root_plant_cut_open");
    public static String WAYPOINT_LOADED = message("waypoint_loaded");
    public static String CANNOT_START_NO_FUEL = message("cannot_start_no_fuel");
    public static String CURRENT = message("current");
    public static String DESTINATION = message("destination");
    public static String SUBMIT = message("submit");

    public static String DIM_NOT_ALLOWED = message("dim_not_allowed");
    public static String DIM_ALREADY_SAVED = message("dim_already_saved");
    public static String DIM_POTENTIAL = message("dim_potential");
    public static String DIM_ADDED_TO_TARDIS = message("dim_added_to_tardis");

    /*Tool Tips*/
    public static String TOOLTIP_TARDIS_LIST_TITLE = tooltip("tardis_list");
    public static String TOOLTIP_SCREWDRIVER_DESCRIPTION = tooltip("screwdriver_description");
    public static String TOOLTIP_IN_FLIGHT = tooltip("tooltip_in_flight");
    public static String TOOLTIP_DIM_PROGRESS = tooltip("tooltip_dim_progress");
    public static String TOOLTIP_DIM_SAVED = tooltip("tooltip_dim_saved");
    public static String TOOLTIP_NO_DIM_SAVED = tooltip("tooltip_no_dim_saved");

    public static String message(String translationKey) {
        return "message." + TardisRefined.MODID + "." + translationKey;
    }

    public static String ui(String translationKey) {
        return "ui." + TardisRefined.MODID + "." + translationKey;
    }


    public static String keybind(String translationKey) {
        return "keybind." + TardisRefined.MODID + "." + translationKey;
    }

    public static String desktop(String translationKey) {
        return "desktop." + TardisRefined.MODID + "." + translationKey;
    }

    public static String console(String translationKey) {
        return "console." + TardisRefined.MODID + "." + translationKey;
    }

    public static String shell(String translationKey) {
        return "shell." + TardisRefined.MODID + "." + translationKey;
    }

    public static String tooltip(String translationKey) {
        return "tooltip." + TardisRefined.MODID + "." + translationKey;
    }

    public static String cmdOutput(String translationKey) {
        return "command." + TardisRefined.MODID + "." + translationKey;
    }

    public static String config(String translationKey) {
        return "config." + TardisRefined.MODID + "." + translationKey;
    }

    public static String cmdArgInvalid(String translationKey) {
        return "argument" + "." + TardisRefined.MODID + "." + translationKey + "." + "invalid";
    }

}
