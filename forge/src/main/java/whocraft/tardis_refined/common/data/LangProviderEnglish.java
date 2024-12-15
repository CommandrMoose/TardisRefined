package whocraft.tardis_refined.common.data;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.LanguageProvider;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.hum.HumEntry;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.tardis.control.Control;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.*;

import java.util.Map;

public class LangProviderEnglish extends LanguageProvider {

    public LangProviderEnglish(DataGenerator gen) {
        super(gen.getPackOutput(), TardisRefined.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        /*Vortex*/
        add(VortexRegistry.CLOUDS.get().getTranslationKey(), "Clouds");
        add(VortexRegistry.FLOW.get().getTranslationKey(), "Flow");
        add(VortexRegistry.SPACE.get().getTranslationKey(), "Space");
        add(VortexRegistry.WAVES.get().getTranslationKey(), "Waves");
        add(VortexRegistry.STARS.get().getTranslationKey(), "Stars");
        add(VortexRegistry.TWILIGHT_GLOW.get().getTranslationKey(), "Twilight Glow");
        add(VortexRegistry.AURORA_DREAMS.get().getTranslationKey(), "Aurora Dreams");
        add(VortexRegistry.DESERT_MIRAGE.get().getTranslationKey(), "Desert Mirage");
        add(VortexRegistry.NEON_PULSE.get().getTranslationKey(), "Neon Pulse");
        add(VortexRegistry.OCEAN_BREEZE.get().getTranslationKey(), "Ocean Breeze");
        add(VortexRegistry.SOLAR_FLARE.get().getTranslationKey(), "Solar Flare");
        add(VortexRegistry.CRYSTAL_LAGOON.get().getTranslationKey(), "Crystal Lagoon");
        add(VortexRegistry.VELVET_NIGHT.get().getTranslationKey(), "Velvet Night");
        add(VortexRegistry.CANDY_POP.get().getTranslationKey(), "Candy Pop");
        add(VortexRegistry.EMERALD_FOREST.get().getTranslationKey(), "Emerald Forest");
        add(VortexRegistry.LGBT_RAINBOW.get().getTranslationKey(), "LGBT Rainbow");
        add(VortexRegistry.TRANSGENDER_FLAG.get().getTranslationKey(), "Transgender Flag");
        add(VortexRegistry.BISEXUAL_FLAG.get().getTranslationKey(), "Bisexual Flag");
        add(VortexRegistry.LESBIAN_FLAG.get().getTranslationKey(), "Lesbian Flag");
        add(VortexRegistry.NON_BINARY_FLAG.get().getTranslationKey(), "Non-Binary Flag");
        add(VortexRegistry.AGENDER_FLAG.get().getTranslationKey(), "Agender Flag");
        add(VortexRegistry.GAY_FLAG.get().getTranslationKey(), "Gay Flag");


        /*Sounds*/
        addSound(TRSoundRegistry.TARDIS_LAND.get(), "TARDIS lands");
        addSound(TRSoundRegistry.TARDIS_SINGLE_FLY.get(), "TARDIS flies");
        addSound(TRSoundRegistry.TARDIS_TAKEOFF.get(), "TARDIS takes off");
        addSound(TRSoundRegistry.TARDIS_CRASH_LAND.get(), "TARDIS crash lands");
        addSound(TRSoundRegistry.STATIC.get(), "Screen display static");
        addSound(TRSoundRegistry.PATTERN_MANIPULATOR.get(), "Pattern Manipulator activates");
        addSound(TRSoundRegistry.TARDIS_MISC_SPARKLE.get(), "TARDIS arriving");
        addSound(TRSoundRegistry.TIME_BLAST.get(), "Time Vortex blast");
        addSound(TRSoundRegistry.DESTINATION_DING.get(), "TARDIS reaches destination");
        addSound(TRSoundRegistry.ARS_HUM.get(), "ARS Tree Hum");
        addSound(TRSoundRegistry.FLIGHT_FAIL_START.get(), "Failing TARDIS groans");
        addSound(TRSoundRegistry.CONSOLE_POWER_ON.get(), "Console power on");
        addSound(TRSoundRegistry.INTERIOR_VOICE.get(), "...?");
        addSound(TRSoundRegistry.ARTRON_PILLAR_ACTIVE.get(), "Artron pillar activated");
        addSound(TRSoundRegistry.CORRIDOR_TELEPORTER.get(), "Teleporter building up");
        addSound(TRSoundRegistry.CORRIDOR_TELEPORTER_SUCCESS.get(), "Teleporter used");
        addSound(TRSoundRegistry.SCREWDRIVER_CONNECT.get(), "Screwdriver connected position");
        addSound(TRSoundRegistry.SCREWDRIVER_SHORT.get(), "Screwdriver used");
        addSound(TRSoundRegistry.INTERIOR_CREAKS.get(), "Creaks");
        addSound(TRSoundRegistry.SCREWDRIVER_DISCARD.get(), "Screwdriver discard data");
        addSound(TRSoundRegistry.GRAVITY_TUNNEL.get(), "Gravity tunnel winds");
        addSound(TRSoundRegistry.LOW_FUEL.get(), "Low fuel warning");
        addSound(TRSoundRegistry.CLOISTER_BELL.get(), "Cloister Bell");
        addSound(TRSoundRegistry.MALLET.get(), "Mallet Hit");
        addSound(TRSoundRegistry.ALARM.get(), "Alarm");

        //Hum Sounds
        TardisHums.registerDefaultHums();
        for (Map.Entry<ResourceLocation, HumEntry> entry : TardisHums.getDefaultHums().entrySet()) {
            addSound(entry.getValue().getSoundEventId(), "TARDIS hums");
        }

        /*Block*/
        add(TRBlockRegistry.ARS_EGG.get(), "ARS Egg");
        add(TRBlockRegistry.ARS_LEAVES.get(), "ARS Leaves");
        add(TRBlockRegistry.ARS_LEAVES_FENCE.get(), "ARS Fence");
        add(TRBlockRegistry.ARS_LEAVES_SLAB.get(), "ARS Slab");
        add(TRBlockRegistry.BULK_HEAD_DOOR.get(), "Bulk Head Door");
        add(TRBlockRegistry.ROOT_PLANT_BLOCK.get(), "Root Plant");
        add(TRBlockRegistry.ROOT_SHELL_BLOCK.get(), "Root Shell");
        add(TRBlockRegistry.TERRAFORMER_BLOCK.get(), "Terraformer");
        add(TRBlockRegistry.GLOBAL_CONSOLE_BLOCK.get(), "Console");
        add(TRBlockRegistry.GLOBAL_DOOR_BLOCK.get(), "Tardis Door");
        add(TRBlockRegistry.ROOT_SHELL_DOOR.get(), "Root Door");
        add(TRBlockRegistry.AIR_LOCK_GENERATION_BLOCK.get(), "Air Lock Generator");
        add(TRBlockRegistry.CONSOLE_CONFIGURATION_BLOCK.get(), "Console Configurator");
        add(TRBlockRegistry.LANDING_PAD.get(), "Landing Pad");
        add(TRBlockRegistry.FOOLS_STONE.get(), "Fool's Stone");
        add(TRBlockRegistry.FLIGHT_DETECTOR.get(), "Flight Detector");
        add(TRBlockRegistry.GLOBAL_SHELL_BLOCK.get(), "TARDIS");
        add(TRBlockRegistry.ASTRAL_MANIPULATOR_BLOCK.get(), "Astral Manipulator");
        add(TRBlockRegistry.ZEITON_FUSED_IRON_BLOCK.get(), "Zeiton Fused Iron Block");
        add(TRBlockRegistry.ZEITON_FUSED_COPPER_BLOCK.get(), "Zeiton Fused Copper Block");
        add(TRBlockRegistry.ZEITON_ORE.get(), "Zeiton Ore");
        add(TRBlockRegistry.ZEITON_ORE_DEEPSLATE.get(), "Deepslate Zeiton Ore");
        add(TRBlockRegistry.ZEITON_BLOCK.get(), "Block of Zeiton");
        add(TRBlockRegistry.GRAVITY_WELL.get(), "Gravity Well");
        add(TRBlockRegistry.ZEITON_LANTERN.get(), "Zeiton Lantern");
        add(TRBlockRegistry.ARTRON_PILLAR.get(), "Artron Pillar");
        add(TRBlockRegistry.ARTRON_PILLAR_PORT.get(), "Artron Pillar Port");
        add(TRBlockRegistry.CORRIDOR_TELEPORTER.get(), "Corridor Teleporter");

        /*Items*/
        add(TRItemRegistry.PATTERN_MANIPULATOR.get(), "Pattern Manipulator");
        add(TRItemRegistry.KEY.get(), "Tardis Key");
        add(TRItemRegistry.DRILL.get(), "Growth Drill");
        add(ModMessages.ITEM_KEYCHAIN, "Tardis Keyset");
        add(ModMessages.ITEM_GROUP, "Tardis Refined");
        add(TRItemRegistry.SCREWDRIVER.get(), "Amethyst Screwdriver");
        add(ModMessages.TOOLTIP_SCREWDRIVER_DESCRIPTION, "An amethyst frequency manipulator");
        add(TRItemRegistry.ZEITON_INGOT.get(), "Zeiton Ingot");
        add(TRItemRegistry.RAW_ZEITON.get(), "Raw Zeiton");
        add(TRItemRegistry.GLASSES.get(), "AR Glasses");
        add(TRItemRegistry.ZEITON_NUGGET.get(), "Zeiton Nugget");
        add(TRItemRegistry.MALLET.get(), "Mallet");
        add(TRItemRegistry.TEST_TUBE.get(), "Test Tube");

        /*Damage Sources*/
        add(TRDamageSources.EYE_OF_HARMONY, "%s was fried by time winds.");
        add(TRDamageSources.CHOKE, "%s was overwhelmed by toxic fumes");

        /*Entity*/
        add(TREntityRegistry.CONTROL_ENTITY.get(), "Generic Control");

        /*Controls*/
        addControl(TRControlRegistry.DOOR_TOGGLE.get(), "Door Toggle");
        addControl(TRControlRegistry.X.get(), "X");
        addControl(TRControlRegistry.Y.get(), "Y");
        addControl(TRControlRegistry.Z.get(), "Z");
        addControl(TRControlRegistry.INCREMENT.get(), "Increment");
        addControl(TRControlRegistry.ROTATE.get(), "Direction");
        addControl(TRControlRegistry.RANDOM.get(), "Randomizer");
        addControl(TRControlRegistry.THROTTLE.get(), "Throttle");
        addControl(TRControlRegistry.MONITOR.get(), "Computer Bank");
        addControl(TRControlRegistry.FAST_RETURN.get(), "Fast Return");
        addControl(TRControlRegistry.DIMENSION.get(), "Dimension");
        addControl(TRControlRegistry.HANDBRAKE.get(), "Handbrake");
        addControl(TRControlRegistry.GENERIC_NO_SHOW.get(), "Switch");
        addControl(TRControlRegistry.FUEL.get(), "Fuel");
        addControl(TRControlRegistry.READOUT.get(), "GPS");
        addControl(TRControlRegistry.EXTERIOR_DISPLAY.get(), "Exterior Display");

        /*Messages*/
        add(ModMessages.MSG_EXTERIOR_COOLDOWN, "You must wait %s seconds");
        add(ModMessages.MSG_KEY_BOUND, "Key Bound to %s");
        add(ModMessages.MSG_KEY_CYCLED, "Main: %s");
        add(ModMessages.CONSOLE_CONFIGURATION_NOT_IN_FLIGHT, "Cannot update console block whilst in flight");
        add(ModMessages.HARDWARE_OFFLINE, "Hardware offline");
        add(ModMessages.NO_FLIGHT_TRANSITIVE, "Cannot change handbrake state whilst in transitive flight");
        add(ModMessages.HANDBRAKE_WARNING, "Ship is in flight. Left click the handbrake to engage");
        add(ModMessages.CONSOLE_NOT_IN_FLIGHT, "Cannot change consoles whilst in flight");
        add(ModMessages.NO_END_DRAGON_PREVENTS, "A dragon prevents you from progressing to The End");
        add(ModMessages.TARDIS_IS_ON_THE_WAY, "TARDIS has been summoned and is on the way");
        add(ModMessages.LANDING_PAD_NOT_UNLOCKED, "Specified TARDIS rejected landing pad signal");
        add(ModMessages.LANDING_PAD_TRANSIENT, "Cannot summon TARDIS at this time.");
        add(ModMessages.REFUEL, "Enabled refuelling");
        add(ModMessages.STOP_REFUEL, "Stopped refuelling");
        add(ModMessages.NO_DESKTOP_NO_FUEL, "Not enough fuel to start the reconfiguration process");
        add(ModMessages.ASTRAL_MANIPULATOR_ENGAGED, "Please make your selection. Right click again to confirm");
        add(ModMessages.ROOT_PLANT_CUT_OPEN, "Roots cover the entrance");
        add(ModMessages.FUEL, "Fuel: %s");
        add(ModMessages.FUEL_OFFLINE, "Fuel offline");
        add(ModMessages.WAYPOINT_LOADED, "Preloaded waypoint: %s");
        add(ModMessages.HANDBRAKE_ENGAGED, "Handbrake engaged");
        add(ModMessages.HANDBRAKE_DISENGAGED, "Handbrake disengaged");
        add(ModMessages.CURRENT, "CURRENT");
        add(ModMessages.DESTINATION, "DESTINATION");
        add(ModMessages.DOOR_LOCKED, "Door locked");
        add(ModMessages.DOOR_UNLOCKED, "Door unlocked");
        add(ModMessages.RECOVERY_PROGRESS, "Recovery Progress: %s");
        add(ModMessages.SUBMIT, "Submit");


        /*Command*/
        add(ModMessages.CMD_DIM_NOT_A_TARDIS, ChatFormatting.RED + "%s is not a TARDIS Dimension!");
        add(ModMessages.CMD_NO_INTERNAL_DOOR, ChatFormatting.RED + "No Internal Door found in dimension %s! Consider using the default teleport command %s");
        add(ModMessages.CMD_EXPORT_DESKTOP_IN_PROGRESS, "Generating datapack for desktop %s, this may take some time depending on the structure's size...");
        add(ModMessages.CMD_EXPORT_DESKTOP_SUCCESS, ChatFormatting.GREEN + "Successfully exported desktop %s to datapack %s! Use the %s command to see changes.");
        add(ModMessages.CMD_EXPORT_DESKTOP_RESOURCE_PACK, ChatFormatting.BLUE + "To define the Desktop's preview image, please create a Resource Pack. See some example Resource Packs at: %s");
        add(ModMessages.CMD_EXPORT_DESKTOP_FAIL, ChatFormatting.RED + "Failed to export desktop %s!");

        add(ModMessages.CMD_LEVEL_POINT_GET, "%s has %s upgrade points");
        add(ModMessages.CMD_LEVEL_POINT_SET, "Set upgrade points for %s to %s");
        add(ModMessages.CMD_LEVEL_POINT_ADD, "Added %s points for %s, total points are now %s");

        add(ModMessages.CMD_LEVEL_XP_GET, "%s has %s XP");
        add(ModMessages.CMD_LEVEL_XP_SET, "Set XP for %s to %s");
        add(ModMessages.CMD_LEVEL_XP_ADD, "Added %s XP for %s, total XP is now %s");

        add(ModMessages.CMD_UPGRADE_LOCK, "Locked upgrade %s for %s");
        add(ModMessages.CMD_UPGRADE_UNLOCK, "Unlocked upgrade %s for %s");

        add(ModMessages.CMD_CREATE_TARDIS_IN_PROGRESS, "Attempting to create Tardis %s, generation in progress");
        add(ModMessages.CMD_CREATE_TARDIS_SUCCESS, "Successfully created Tardis with id %s");

        add(ModMessages.CMD_ARG_UPGRADE_INVALID, "Invalid Upgrade of ID %s");
        add(ModMessages.CMD_ARG_DESKTOP_INVALID, "Invalid Desktop of ID %s");
        add(ModMessages.CMD_ARG_SHELL_INVALID, "Invalid Shell of ID %s");

        /*GUI*/
        add(ModMessages.UI_MONITOR_MAIN_TITLE, "COMPUTER BANK");
        add(ModMessages.UI_MONITOR_WAYPOINTS, "WAYPOINTS");
        add(ModMessages.UI_MONITOR_UPLOAD_WAYPOINTS, "WAYPOINT NAVIGATION");
        add(ModMessages.UI_MONITOR_UPLOAD_COORDS, "COORD NAVIGATION");
        add(ModMessages.UI_MONITOR_SELECT_HUM, "SOUNDSCAPE");
        add(ModMessages.UI_MONITOR_GPS, "GPS");
        add(ModMessages.UI_MONITOR_VORTEX, "VORTEX");
        add(ModMessages.UI_MONITOR_DESTINATION, "Destination");
        add(ModMessages.UI_LIST_SELECTION, "Currently selected: &s");
        add(ModMessages.UI_EXTERNAL_SHELL, "SHELL CONFIGURATION");
        add(ModMessages.UI_MONITOR_SHELL_VIEW, "SHELL VIEW");
        add(ModMessages.UI_DESKTOP_CONFIGURATION, "DESKTOP");
        add(ModMessages.UI_DESKTOP_CANCEL_TITLE, "OPERATION IN PROGRESS");
        add(ModMessages.UI_DESKTOP_CANCEL_DESCRIPTION, "Systems disabled as a Desktop reconfiguration has been scheduled.");
        add(ModMessages.UI_DESKTOP_CANCEL_DESKTOP, "Would you like to cancel the upcoming reconfiguration?");
        add(ModMessages.UI_DESKTOP_CANCEL, "Cancel Desktop Reconfiguration");
        add(ModMessages.UI_MONITOR_NO_WAYPOINTS, "No Waypoints Saved!");
        add(ModMessages.UI_MONITOR_WAYPOINT_UPLOAD, "Upload");
        add(ModMessages.UI_MONITOR_ISSUES, "Issues:");
        add(ModMessages.UI_MONITOR_WAYPOINT_ISSUE_NAME, "Invalid waypoint name");
        add(ModMessages.UI_MONITOR_WAYPOINT_ISSUE_X, "Invalid X value");
        add(ModMessages.UI_MONITOR_WAYPOINT_ISSUE_Y, "Invalid Y value");
        add(ModMessages.UI_MONITOR_WAYPOINT_ISSUE_Z, "Invalid Z value");
        add(ModMessages.UI_MONITOR_WAYPOINT_NAME, "Waypoint Name:");
        add(ModMessages.UI_UPGRADES, "Tardis Upgrades");
        add(ModMessages.UI_UPGRADES_BUY, "Purchase upgrade?");
        add(ModMessages.UI_NO_INSTALLED_SUBSYSTEMS, "No available sub-systems");
        add(ModMessages.UI_WAYPOINT_NAME_PLACEHOLDER, "Waypoint name");
        add(ModMessages.UI_WAYPOINT_NEW_WAYPOINT, "New Waypoint");
        add(ModMessages.UI_WAYPOINT_TAKEN, "Data retrieved from destination values");
        add(ModMessages.UI_MONITOR_WAYPOINT_LOAD, "Send to console");
        add(ModMessages.UI_MONITOR_WAYPOINT_CREATE, "New waypoint");
        add(ModMessages.UI_MONITOR_WAYPOINT_EDIT, "Edit waypoint");
        add(ModMessages.UI_MONITOR_WAYPOINT_DELETE, "Delete waypoint");
        add(ModMessages.CANNOT_START_NO_FUEL, "Not enough fuel to start");
        add(ModMessages.UI_MONITOR_EJECT, "EMERGENCY EJECT");
        add(ModMessages.UI_EJECT_CANNOT_IN_FLIGHT, "Cannot eject whilst in flight");
        add(ModMessages.DIM_NOT_ALLOWED, "This dimension cannot be sampled");
        add(ModMessages.DIM_ALREADY_SAVED, "Already contains a sample");
        add(ModMessages.DIM_POTENTIAL, "Sampled %s");
        add(ModMessages.DIM_ADDED_TO_TARDIS, "Added %s to Data Banks");


        add("curios.identifier.timelord_sight", "AR Glasses");


        /*Create GUI Compatibility*/

       /* add(ModMessages.DOOR_STATUS, "Door: %s");
        add(ModMessages.LOCK_STATUS, "Locked: %s");
        add(ModMessages.POSITION, "Position: %s");
        add(ModMessages.DIRECTION, "Direction: %s");
        add(ModMessages.DIMENSION, "Dimension: %s");
        add(ModCompatMessages.createDisplaySource("fuel"), "Fuel");
        add(ModCompatMessages.createDisplaySource("gps"), "GPS");
        add(ModCompatMessages.createDisplaySource("destination"), "GPS Destination");
        add(ModCompatMessages.createDisplaySource("tardis_bigdata"), "Tardis Summary");
        add(ModCompatMessages.createDisplaySource("door"), "Door Status");
        add(ModCompatMessages.createDisplaySource("locked"), "Lock Status");*/

        /*Shell Themes*/
        addShell(ShellTheme.FACTORY.getId(), "Factory");
        addShell(ShellTheme.POLICE_BOX.getId(), "Police Box");
        addShell(ShellTheme.PHONE_BOOTH.getId(), "Phone Booth");
        addShell(ShellTheme.MYSTIC.getId(), "Mystic");
        addShell(ShellTheme.DRIFTER.getId(), "Drifter");
        addShell(ShellTheme.PRESENT.getId(), "Present");
        addShell(ShellTheme.BRIEFCASE.getId(), "Briefcase");
        addShell(ShellTheme.GROENING.getId(), "Groening");
        addShell(ShellTheme.VENDING.getId(), "Vending Machine");
        addShell(ShellTheme.BIG_BEN.getId(), "Big Ben");
        addShell(ShellTheme.NUKA.getId(), "Nuka");
        addShell(ShellTheme.GROWTH.getId(), "Growth");
        addShell(ShellTheme.PORTALOO.getId(), "Portaloo");
        addShell(ShellTheme.PAGODA.getId(), "Pagoda");
        addShell(ShellTheme.LIFT.getId(), "Lift");
        addShell(ShellTheme.HIEROGLYPH.getId(), "Hieroglyph");
        addShell(ShellTheme.CASTLE.getId(), "Castle");
        addShell(ShellTheme.PATHFINDER.getId(), "Pathfinder");
        addShell(ShellTheme.HALF_BAKED.getId(), "Half Baked");

        /*Tool Tips*/
        add(ModMessages.TOOLTIP_TARDIS_LIST_TITLE, ChatFormatting.YELLOW + "< "
                + ChatFormatting.GOLD + "Key Set:"
                + ChatFormatting.YELLOW + " >");
        add(ModMessages.TOOLTIP_DIM_PROGRESS, ChatFormatting.GREEN + "Decoding Sample: %s");
        add(ModMessages.TOOLTIP_DIM_SAVED, ChatFormatting.GOLD + "Saved Sample: %s");
        add(ModMessages.TOOLTIP_NO_DIM_SAVED, ChatFormatting.GRAY + "No Dimension Sample");


        add(ModMessages.CONTROL_DIMENSION_SELECTED, "Selected: %s");

        /*Config*/
        add(ModMessages.CONFIG_IP_COMPAT, "Immersive Portals Compatibility?");
        add(ModMessages.CONFIG_CONTROL_NAMES, "Render control names?");
        add(ModMessages.CONFIG_BANNED_DIMENSIONS, "Banned Dimensions");
        add(ModMessages.CONFIG_IDLE_CONSOLE_ANIMS, "Play idle console animations");

        /*Overlay Messages*/
        add(ModMessages.ASCEND_KEY, "Ascend: %s");
        add(ModMessages.DESCEND_KEY, "Descend: %s");
        add(ModMessages.EXIT_EXTERNAL_VIEW, "Exit Shell View: ");

        /*Upgrades*/
        addUpgrade(TRUpgrades.CHAMELEON_CIRCUIT_SYSTEM.get(), "Chameleon Circuit", "Allows the TARDIS to change it's shape");
        addUpgrade(TRUpgrades.DEFENSE_SYSTEM.get(), "Defense System", "Enables Defense Protocols");
        addUpgrade(TRUpgrades.WAYPOINTS.get(), "Waypoints", "Allows the Pilot to create saved locations");
        addUpgrade(TRUpgrades.NAVIGATION_SYSTEM.get(), "Navigation System", "Allows upgrades to the TARDIS Navigation System");
        addUpgrade(TRUpgrades.TARDIS_XP.get(), "System Upgrades", "Allows upgrades to the TARDIS");
        addUpgrade(TRUpgrades.MATERIALIZE_AROUND.get(), "Materialize Around", "Allows the TARDIS to have entities enter while materalizing");
        addUpgrade(TRUpgrades.ARCHITECTURE_SYSTEM.get(), "Architecture", "Enables TARDIS Architecture Upgrades");
        addUpgrade(TRUpgrades.INSIDE_ARCHITECTURE.get(), "Desktop Reconfiguration", "Allows the Pilot to change the appearance of the TARDIS Desktop");
        addUpgrade(TRUpgrades.EXPLORER.get(), "Explorer I", "x1000 Increment");
        addUpgrade(TRUpgrades.EXPLORER_II.get(), "Explorer II", "x2500 Increment");
        addUpgrade(TRUpgrades.EXPLORER_III.get(), "Explorer III", "x5000 Increment");
        addUpgrade(TRUpgrades.DIMENSION_TRAVEL.get(), "Inter-Dimensional Travel", "Allows the TARDIS to move between dimensions");
        addUpgrade(TRUpgrades.LANDING_PAD.get(), "Landing Pad", "Allows the TARDIS to be summoned to a landing pad");
        addUpgrade(TRUpgrades.IMPROVED_GENERATION_TIME_I.get(), "Improved Generation I", "Lowers desktop wait times to 120 seconds");
        addUpgrade(TRUpgrades.IMPROVED_GENERATION_TIME_II.get(), "Improved Generation II", "Lowers desktop wait times to 60 seconds");
        addUpgrade(TRUpgrades.IMPROVED_GENERATION_TIME_III.get(), "Improved Generation III", "Lowers desktop wait times to 10 seconds");
        addUpgrade(TRUpgrades.FLIGHT_SYSTEM.get(), "Flight System", "Allows upgrades to the TARDIS Flight System");
        addUpgrade(TRUpgrades.SPEED_I.get(), "Speed I", "Flight speed is 5x faster");
        addUpgrade(TRUpgrades.SPEED_II.get(), "Speed II", "Flight speed is 10x faster");
        addUpgrade(TRUpgrades.SPEED_III.get(), "Speed III", "Flight speed is 25x faster");
        addUpgrade(TRUpgrades.SPEED_IV.get(), "Speed IV", "Flight speed is 50x faster");

        /*Keybinds*/
        add(ModMessages.KEYBIND_EXIT_VIEW, "Exit Shell View");
        add(ModMessages.KEYBIND_TOGGLE_INFO_EXTERIOR_VIEW, "(Shell View) Toggle Info");
    }


    public void addControl(Control control, String name) {
        add(control.getTranslationKey(), name);
    }

    public void addShell(ResourceLocation theme, String name) {
        add(ModMessages.shell(theme.getPath()), name);
    }

    public void addUpgrade(Upgrade upgrade, String title, String description) {
        add(Util.makeDescriptionId("upgrade", TRUpgrades.UPGRADE_REGISTRY.getKey(upgrade)), title);
        add(Util.makeDescriptionId("upgrade", TRUpgrades.UPGRADE_REGISTRY.getKey(upgrade)) + ".description", description);
    }

    public void addSound(SoundEvent soundEvent, String lang) {
        String subtitleKey = SoundProvider.createSubtitle(soundEvent.getLocation().getPath());
        add(subtitleKey, lang);
    }

    public void addSound(ResourceLocation soundId, String lang) {
        String subtitleKey = SoundProvider.createSubtitle(soundId.getPath());
        add(subtitleKey, lang);
    }

    public void add(ResourceKey<DamageType> damageSource, String message) {
        add("death.attack." + damageSource.location().getPath(), message);
        add("death.attack." + damageSource.location().getPath() + ".player", message);
    }

}
