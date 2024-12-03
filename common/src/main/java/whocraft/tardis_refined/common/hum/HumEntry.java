package whocraft.tardis_refined.common.hum;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.RegistryHelper;

import java.util.ArrayList;
import java.util.List;

public class HumEntry {

    private static final Codec<HumEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(HumEntry::getIdentifier),
            ResourceLocation.CODEC.fieldOf("hum_sound").forGetter(HumEntry::getSoundEventId),
            Codec.list(ResourceLocation.CODEC).fieldOf("ambient_sounds").forGetter(HumEntry::getAmbientSounds),
            Codec.STRING.orElse("Placeholder").fieldOf("name_component").forGetter(HumEntry::getNameComponent)
    ).apply(instance, HumEntry::new));

    private final ResourceLocation identifier;
    private ResourceLocation soundEventId;
    private List<ResourceLocation> ambientSounds;
    private String nameComponent;

    /**
     * Generic constructor
     *
     * @param identifier    - Enter the registry name.
     * @param soundEventId  - the underlying SoundEvent id
     * @param ambientSounds - List of ambient sounds that can play in addition to the Hum sound event
     * @param nameComponent - the string that will be displayed. Can be either a language file translation key or a component
     */
    public HumEntry(ResourceLocation identifier, ResourceLocation soundEventId, List<ResourceLocation> ambientSounds, String nameComponent) {
        this.identifier = identifier;
        this.soundEventId = soundEventId;
        this.ambientSounds = ambientSounds;
        this.nameComponent = nameComponent;
    }

    /**
     * Convenience constructor that creates a default value for the nameComponent parameter by creating a gold coloured text component
     *
     * @param identifier
     * @param soundEventId
     * @param ambientSounds
     */
    public HumEntry(ResourceLocation identifier, ResourceLocation soundEventId, List<ResourceLocation> ambientSounds) {
        this(identifier, soundEventId, ambientSounds, TardisRefined.GSON.toJson(Component.literal(MiscHelper.getCleanName(identifier.getPath())).setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD))));
    }

    /**
     * Tardis Refined specific constructor
     *
     * @param identifier    - Enter the registry name. No need to add namespace because the TardisRefined namespace is already added
     * @param soundEventId  - the underlying SoundEvent id
     * @param ambientSounds - List of ambient sounds that can play in addition to the Hum sound event
     */
    public HumEntry(String identifier, ResourceLocation soundEventId, List<ResourceLocation> ambientSounds) {
        this(RegistryHelper.makeKey(identifier), soundEventId, ambientSounds);
    }

    /**
     * Tardis Refined specific constructor with an empty list of ambient sounds
     *
     * @param identifier   - Enter the registry name. No need to add namespace because the TardisRefined namespace is already added
     * @param soundEventId - the underlying SoundEvent id
     */
    public HumEntry(String identifier, ResourceLocation soundEventId) {
        this(identifier, soundEventId, new ArrayList<>());
    }


    public static Codec<HumEntry> codec() {
        return CODEC;
    }

    /**
     * Gets the underlying SoundEvent's ID
     */
    public ResourceLocation getSoundEventId() {
        return soundEventId;
    }

    public HumEntry setSoundEventId(ResourceLocation soundEventId) {
        this.soundEventId = soundEventId;
        return this;
    }

    public ResourceLocation getIdentifier() {
        return identifier;
    }

    public List<ResourceLocation> getAmbientSounds() {
        return ambientSounds;
    }

    public HumEntry setAmbientSounds(List<ResourceLocation> ambientSounds) {
        this.ambientSounds = ambientSounds;
        return this;
    }

    public String getNameComponent() {
        return nameComponent;
    }

    public void setNameComponent(String nameComponent) {
        this.nameComponent = nameComponent;
    }
}
