package whocraft.tardis_refined.constants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;

public class NbtConstants {

    // TARDIS Level Operator

    public static final String TARDIS_ID = "tardis_id";

    public static final String THEME = "theme";

    public static final String PATTERN = "pattern";

    public static final String POWERED = "powered";

    public static final String TARDIS_IS_SETUP = "has_setup";
    public static final String TARDIS_INTERNAL_DOOR_ID = "internal_door_id";
    public static final String TARDIS_INTERNAL_DOOR_POSITION = "internal_door_pos";


    // Interior Manager
    public static final String TARDIS_IM_IS_WAITING_TO_GENERATE = "im_waiting_to_generate";
    public static final String TARDIS_IM_PREPARED_THEME = "im_prepared_desktop_theme";
    public static final String TARDIS_IM_CURRENT_THEME = "im_current_desktop_theme";
    public static final String TARDIS_IM_GENERATING_DESKTOP = "im_generating_desktop";
    public static final String TARDIS_IM_GENERATION_COOLDOWN = "im_generation_cooldown";
    public static final String TARDIS_IM_GENERATED_CORRIDORS = "im_generated_corridors";
    public static final String TARDIS_IM_AIRLOCK_CENTER = "im_generated_airlock_center";
    public static final String TARDIS_CURRENT_HUM = "tardis_current_hum";
    public static final String TARDIS_IM_FUEL_FOR_INT_CHANGE = "tardis_fuel_for_int_change";

    // External Readings Data
    public static final String TARDIS_EXT_CURRENT_THEME = "terd_current_theme";
    public static final String TARDIS_EXT_CURRENT_PATTERN = "terd_current_pattern";
    public static final String LOCKED = "terd_locked";

    // Internal Door
    public static final String DOOR_IS_MAIN_DOOR = "is_main_door";
    public static final String DOOR_ID = "door_id";
    public static final String DOOR_IS_OPEN = "is_open";
    public static final String DOOR_IS_LOCKED = "is_locked";

    // External Shell
    public static final String SHELL_THEME_ID = "shell_id";


    // Controls
    public static final String CONTROL_ID = "control_id";
    public static final String CONSOLE_POS = "console_pos";
    public static final String CONTROL_SIZE_WIDTH = "console_size_width";
    public static final String CONTROL_SIZE_HEIGHT = "console_size_height";

    public static final String CONTROL_IS_IN_FLIGHT = "ctrl_is_in_flight";
    public static final String IS_HANDBRAKE_ON = "is_handbrake_on";
    public static final String THROTTLE_STAGE = "throttle_stage";
    public static final String HANDBRAKE_ENGAGED = "handbrake_engaged";
    public static final String CONTROL_INCREMENT_INDEX = "ctrl_increment_index";
    public static final String CONTROL_AUTOLAND = "ctrl_autoland";

    // Location Constant
    public static final String KEYCHAIN = "keychain";
    public static final String TARGET_LOCATION = "target_location";
    public static final String CURRENT_LOCATION = "current_location";
    public static final String RETURN_LOCATION = "return_location";

    // Flight
    public static final String FLIGHT_DISTANCE = "flight_distance";
    public static final String DISTANCE_COVERED = "distance_covered";
    public static final String SPEED_MODIFIER = "speed_modifier";
    public static final String TICKS_CRASHING = "ticksCrashing";
    public static final String IS_IN_RECOVERY = "isInCrashRecovery";
    public static final String RECOVERY_TICKS = "ticksInCrashRecovery";

    public static final String CURRENT_CONSOLE_POS = "currentConsoleBlockPos";
    public static final String CAN_USE_CONTROLS = "canUseControls";


    // Piloting Manager
    public static final String FUEL = "fuel";
    public static final String MAXIMUM_FUEL = "MaximumFuel";
    public static final String IS_PASSIVELY_REFUELING = "is_passively_refueling";
    public static final CharSequence MINECRAFT = "minecraft";

    public static TardisNavLocation getTardisNavLocation(CompoundTag targetTag, String entry) {
        if(targetTag.contains(entry)) {
            CompoundTag savedLocationTag = targetTag.getCompound(entry);
            return TardisNavLocation.deserialize(savedLocationTag);
        }
        return TardisNavLocation.ORIGIN;
    }

    public static void writeTardisNavLocation(CompoundTag targetTag, String entry, TardisNavLocation tardisNavLocation) {
        if(tardisNavLocation == null){
            TardisRefined.LOGGER.error("Tried to save a null TardisNavLocation to {}", entry);
            return;
        }
        CompoundTag navigationTag = tardisNavLocation.serialise();
        targetTag.put(entry, navigationTag);
    }
}
