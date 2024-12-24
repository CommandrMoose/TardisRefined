package whocraft.tardis_refined.patterns;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.util.RegistryHelper;
import whocraft.tardis_refined.patterns.sound.ShellSoundProfile;
import whocraft.tardis_refined.patterns.sound.TRShellSoundProfiles;

import java.util.Optional;

public class ShellPattern extends BasePattern {

    public static final Codec<ShellPattern> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                ResourceLocation.CODEC.fieldOf("id").forGetter(ShellPattern::id),
                Codec.STRING.orElse("Placeholder").fieldOf("name_component").forGetter(ShellPattern::name),
                PatternTexture.getCodec().fieldOf("exterior").forGetter(ShellPattern::shellTexture),
                PatternTexture.getCodec().fieldOf("interior").forGetter(ShellPattern::doorTexture),
                ShellSoundProfile.CODEC.optionalFieldOf("sound_profile").orElse(Optional.of(TRShellSoundProfiles.DEFAULT_SOUND_PROFILE)).forGetter(ShellPattern::soundProfile)
        ).apply(instance, ShellPattern::new);
    });

    private final PatternTexture interiorDoorTexture;
    private final PatternTexture exteriorDoorTexture;

    private final Optional<ShellSoundProfile> shellSoundProfile;

    public ShellPattern(String identifier, PatternTexture exteriorDoorTexture, PatternTexture interiorDoorTexture, Optional<ShellSoundProfile> shellSoundProfile) {
        this(RegistryHelper.makeKey(identifier), exteriorDoorTexture, interiorDoorTexture, shellSoundProfile);
    }

    public ShellPattern(ResourceLocation identifier, PatternTexture exteriorDoorTexture, PatternTexture interiorDoorTexture, Optional<ShellSoundProfile> shellSoundProfile) {
        super(identifier);
        this.exteriorDoorTexture = exteriorDoorTexture;
        this.interiorDoorTexture = interiorDoorTexture;
        this.shellSoundProfile = shellSoundProfile;
    }

    public ShellPattern(ResourceLocation identifier, String name, PatternTexture exteriorDoorTexture, PatternTexture interiorDoorTexture, Optional<ShellSoundProfile> shellSoundProfile) {
        super(identifier, name);
        this.exteriorDoorTexture = exteriorDoorTexture;
        this.interiorDoorTexture = interiorDoorTexture;
        this.shellSoundProfile = shellSoundProfile;
    }

    public PatternTexture shellTexture() {
        return this.exteriorDoorTexture;
    }

    public PatternTexture doorTexture() {
        return this.interiorDoorTexture;
    }

    @Override
    public Codec<ShellPattern> getCodec() {
        return CODEC;
    }

    public Optional<ShellSoundProfile> soundProfile() {
        return this.shellSoundProfile;
    }
}
