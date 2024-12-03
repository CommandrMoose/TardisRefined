package whocraft.tardis_refined.patterns.sound;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.UnboundedMapCodec;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.patterns.PatternResourceConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * An object that allows for grouping of sounds to play in a specific Tardis interaction event
 * <br> In this case, the Exterior Shell and Internal Door will play a sound based on the pattern
 */
public class ShellSoundProfile {

    private static final UnboundedMapCodec<ResourceLocation, ConfiguredSound> UNBOUNDED_MAP_CODEC = Codec.unboundedMap(ResourceLocation.CODEC, ConfiguredSound.CODEC);
    public static final Codec<ShellSoundProfile> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                ShellSoundProfile.UNBOUNDED_MAP_CODEC.fieldOf("sounds").forGetter(ShellSoundProfile::getSoundEntries)
        ).apply(instance, ShellSoundProfile::new);
    });
    protected Map<ResourceLocation, ConfiguredSound> shellSoundEntries = new HashMap<>();


    public ShellSoundProfile(Map<ResourceLocation, ConfiguredSound> shellSoundEntries) {
        this.shellSoundEntries = shellSoundEntries;
    }

    public ShellSoundProfile() {
        this(new HashMap<>());
    }

    public Map<ResourceLocation, ConfiguredSound> getSoundEntries() {
        return shellSoundEntries;
    }

    public ConfiguredSound getDoorOpen() {
        return this.shellSoundEntries.get(PatternResourceConstants.DOOR_OPEN_KEY);
    }

    public ShellSoundProfile setDoorOpen(ConfiguredSound doorOpen) {
        this.shellSoundEntries.put(PatternResourceConstants.DOOR_OPEN_KEY, doorOpen);
        return this;
    }

    public ConfiguredSound getDoorClose() {
        return this.shellSoundEntries.get(PatternResourceConstants.DOOR_CLOSE_KEY);
    }

    public ShellSoundProfile setDoorClose(ConfiguredSound doorClose) {
        this.shellSoundEntries.put(PatternResourceConstants.DOOR_CLOSE_KEY, doorClose);
        return this;
    }

    public ConfiguredSound getDoorLocked() {
        return this.shellSoundEntries.get(PatternResourceConstants.DOOR_LOCK_KEY);
    }

    public ShellSoundProfile setDoorLocked(ConfiguredSound doorLocked) {
        this.shellSoundEntries.put(PatternResourceConstants.DOOR_LOCK_KEY, doorLocked);
        return this;
    }

    public ConfiguredSound getDoorUnlocked() {
        return this.shellSoundEntries.get(PatternResourceConstants.DOOR_UNLOCK_KEY);
    }

    public ShellSoundProfile setDoorUnlocked(ConfiguredSound doorUnlocked) {
        this.shellSoundEntries.put(PatternResourceConstants.DOOR_UNLOCK_KEY, doorUnlocked);
        return this;
    }
}
