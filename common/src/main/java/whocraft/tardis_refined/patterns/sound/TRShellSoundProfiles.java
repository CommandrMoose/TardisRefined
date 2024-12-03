package whocraft.tardis_refined.patterns.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;

import java.util.HashMap;
import java.util.Map;

public class TRShellSoundProfiles {

    public static ConfiguredSound DEFAULT_DOOR_OPEN = new ConfiguredSound(BlockSetType.IRON.doorOpen(), 1F, 1F);
    public static ConfiguredSound DEFAULT_DOOR_CLOSE = new ConfiguredSound(BlockSetType.IRON.doorClose(), 1F, 1.4F);
    public static ConfiguredSound DEFAULT_DOOR_LOCK = new ConfiguredSound(BlockSetType.IRON.trapdoorClose(), 1F, 1.4F);
    public static ConfiguredSound DEFAULT_DOOR_UNLOCK = new ConfiguredSound(BlockSetType.IRON.trapdoorOpen(), 1F, 1F);

    public static Map<ResourceLocation, ShellSoundProfile> DEFAULT_PATTERN_SOUND_PROFILES = new HashMap<>();
    public static ShellSoundProfile DEFAULT_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(DEFAULT_DOOR_OPEN)
            .setDoorClose(DEFAULT_DOOR_CLOSE)
            .setDoorLocked(DEFAULT_DOOR_LOCK)
            .setDoorUnlocked(DEFAULT_DOOR_UNLOCK);
    public static ShellSoundProfile HALF_BAKED_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(DEFAULT_DOOR_OPEN)
            .setDoorClose(DEFAULT_DOOR_CLOSE)
            .setDoorLocked(new ConfiguredSound(SoundEvents.WEEPING_VINES_PLACE, 1F, 1.4F))
            .setDoorUnlocked(new ConfiguredSound(SoundEvents.WEEPING_VINES_BREAK, 1F, 1.4F));
    public static ShellSoundProfile WOOL_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(new ConfiguredSound(SoundEvents.WOOL_PLACE, 1F, 1F))
            .setDoorClose(new ConfiguredSound(SoundEvents.WOOL_BREAK, 1F, 1.4F))
            .setDoorLocked(DEFAULT_DOOR_LOCK)
            .setDoorUnlocked(DEFAULT_DOOR_UNLOCK);
    public static ShellSoundProfile OAK_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(new ConfiguredSound(BlockSetType.OAK.doorOpen(), 1F, 1F))
            .setDoorClose(new ConfiguredSound(BlockSetType.OAK.doorClose(), 1F, 1.4F))
            .setDoorLocked(new ConfiguredSound(BlockSetType.OAK.trapdoorClose(), 1F, 1.4F))
            .setDoorUnlocked(new ConfiguredSound(BlockSetType.OAK.trapdoorOpen(), 1F, 1F));
    public static ShellSoundProfile DARK_OAK_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(new ConfiguredSound(BlockSetType.DARK_OAK.doorOpen(), 1F, 1F))
            .setDoorClose(new ConfiguredSound(BlockSetType.DARK_OAK.doorClose(), 1F, 1.4F))
            .setDoorLocked(new ConfiguredSound(BlockSetType.DARK_OAK.trapdoorClose(), 1F, 1.4F))
            .setDoorUnlocked(new ConfiguredSound(BlockSetType.DARK_OAK.trapdoorOpen(), 1F, 1F));
    public static ShellSoundProfile SPRUCE_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(new ConfiguredSound(BlockSetType.SPRUCE.doorOpen(), 1F, 1F))
            .setDoorClose(new ConfiguredSound(BlockSetType.SPRUCE.doorClose(), 1F, 1.4F))
            .setDoorLocked(new ConfiguredSound(BlockSetType.SPRUCE.trapdoorClose(), 1F, 1.4F))
            .setDoorUnlocked(new ConfiguredSound(BlockSetType.SPRUCE.trapdoorOpen(), 1F, 1F));
    public static ShellSoundProfile BAMBOO_SOUND_PROFILE = new ShellSoundProfile()
            .setDoorOpen(new ConfiguredSound(BlockSetType.BAMBOO.doorOpen(), 1F, 1F))
            .setDoorClose(new ConfiguredSound(BlockSetType.BAMBOO.doorClose(), 1F, 1.4F))
            .setDoorLocked(new ConfiguredSound(BlockSetType.BAMBOO.trapdoorClose(), 1F, 1.4F))
            .setDoorUnlocked(new ConfiguredSound(BlockSetType.BAMBOO.trapdoorOpen(), 1F, 1F));

    public static Map<ResourceLocation, ShellSoundProfile> defaultSoundProfilesByTheme() {
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.HALF_BAKED.getId(), HALF_BAKED_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.BRIEFCASE.getId(), DEFAULT_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.PHONE_BOOTH.getId(), SPRUCE_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.BIG_BEN.getId(), OAK_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.GROWTH.getId(), HALF_BAKED_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.DRIFTER.getId(), WOOL_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.PAGODA.getId(), BAMBOO_SOUND_PROFILE);
        DEFAULT_PATTERN_SOUND_PROFILES.put(ShellTheme.PATHFINDER.getId(), SPRUCE_SOUND_PROFILE);
        return DEFAULT_PATTERN_SOUND_PROFILES;
    }
}
