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
 * <br> In this case, the controls on the console will look at the console's pattern
 */
public class ConsoleSoundProfile {

    private static final UnboundedMapCodec<ResourceLocation, ConsoleSound> UNBOUNDED_MAP_CODEC = Codec.unboundedMap(ResourceLocation.CODEC, ConsoleSound.CODEC);
    public static final Codec<ConsoleSoundProfile> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                ConsoleSoundProfile.UNBOUNDED_MAP_CODEC.fieldOf("sounds").forGetter(ConsoleSoundProfile::getSoundEntries)
        ).apply(instance, ConsoleSoundProfile::new);
    });
    protected Map<ResourceLocation, ConsoleSound> consoleSoundEntries = new HashMap<>();


    public ConsoleSoundProfile(Map<ResourceLocation, ConsoleSound> consoleSoundEntries) {
        this.consoleSoundEntries = consoleSoundEntries;
    }

    public ConsoleSoundProfile() {
        this(new HashMap<>());
    }

    public Map<ResourceLocation, ConsoleSound> getSoundEntries() {
        return consoleSoundEntries;
    }

    public ConsoleSound getThrottleEnable() {
        return this.consoleSoundEntries.get(PatternResourceConstants.THROTTLE_ENABLE_KEY);
    }

    public ConsoleSoundProfile setThrottleEnable(ConsoleSound throttleEnable) {
        this.consoleSoundEntries.put(PatternResourceConstants.THROTTLE_ENABLE_KEY, throttleEnable);
        return this;
    }

    public ConsoleSound getThrottleDisable() {
        return this.consoleSoundEntries.get(PatternResourceConstants.THROTTLE_DISABLE_KEY);
    }

    public ConsoleSoundProfile setThrottleDisable(ConsoleSound throttleDisable) {
        this.consoleSoundEntries.put(PatternResourceConstants.THROTTLE_DISABLE_KEY, throttleDisable);
        return this;
    }

    public ConsoleSound getHandbrakeEnable() {
        return this.consoleSoundEntries.get(PatternResourceConstants.HANDBRAKE_ENABLE_KEY);
    }

    public ConsoleSoundProfile setHandbrakeEnable(ConsoleSound handbrakeEnable) {
        this.consoleSoundEntries.put(PatternResourceConstants.HANDBRAKE_ENABLE_KEY, handbrakeEnable);
        return this;
    }

    public ConsoleSound getHandbrakeDisable() {
        return this.consoleSoundEntries.get(PatternResourceConstants.HANDBRAKE_DISABLE_KEY);
    }

    public ConsoleSoundProfile setHandbrakeDisable(ConsoleSound handbrakeDisable) {
        this.consoleSoundEntries.put(PatternResourceConstants.HANDBRAKE_DISABLE_KEY, handbrakeDisable);
        return this;
    }

    public ConsoleSound getGeneric() {
        return this.consoleSoundEntries.get(PatternResourceConstants.GENERIC_CONSOLE_KEY);
    }

    public ConsoleSoundProfile setGeneric(ConsoleSound generic) {
        this.consoleSoundEntries.put(PatternResourceConstants.GENERIC_CONSOLE_KEY, generic);
        return this;
    }


}
