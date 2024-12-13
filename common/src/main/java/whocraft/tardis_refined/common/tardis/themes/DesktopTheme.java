package whocraft.tardis_refined.common.tardis.themes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.util.MiscHelper;


public class DesktopTheme {

    private static final Codec<DesktopTheme> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                ResourceLocation.CODEC.fieldOf("id").forGetter(DesktopTheme::getIdentifier),
                ResourceLocation.CODEC.fieldOf("structure").forGetter(DesktopTheme::getStructureLocation),
                Codec.STRING.orElse("Placeholder").fieldOf("name_component").forGetter(DesktopTheme::getName)
        ).apply(instance, DesktopTheme::new);
    });
    private final ResourceLocation uiTexture;
    private ResourceLocation identifier;
    private String name = "";
    private ResourceLocation structureLocation;

    /**
     * Constructor for Tardis Refined entries only.
     * <br> The display name is set to the identifier using a standard String Text Component.
     *
     * @param id
     * @param structureLocation
     * @implNote NOTE: Users must also add a PNG display image under assets/tardis_refined/textures/gui/desktops/
     */
    public DesktopTheme(String id, String structureLocation) {
        this(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, structureLocation), TardisRefined.GSON.toJson(Component.literal(MiscHelper.getCleanName(id)).setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD))));
    }

    /**
     * Constructor for Tardis Refined entries only.
     *
     * @param id
     * @param structureLocation
     * @param name              - The display name that is shown on the Desktop Selection Screen. Supports Tellraw style JSON strings, but they must be validated beforehand.
     * @implNote NOTE: Users must also add a PNG display image under assets/tardis_refined/textures/gui/desktops/
     */
    public DesktopTheme(String id, String structureLocation, String name) {
        this(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, id), ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, structureLocation), name);
    }

    /**
     * Generic constructor for non-Tardis Refined entries.
     *
     * @param id                - the unique identifier for the DesktopTheme. Do not use the Tardis Refined ModId for the namespace if the DesktopTheme is not for the base mod.
     * @param structureLocation - the ResourceLocation for the structure's .nbt file. The nbt file should be located under data/[namespace]/structures.
     * @param name              - The display name that is shown on the Desktop Selection Screen. Supports Tellraw style JSON strings, but they must be validated beforehand.
     * @implNote NOTE: Users must also add a PNG display image under assets/[namespace]/textures/gui/desktops/
     */
    public DesktopTheme(ResourceLocation id, ResourceLocation structureLocation, String name) {
        this.identifier = id;
        this.structureLocation = structureLocation;
        this.uiTexture = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/gui/desktops/" + id.getPath().toString() + ".png");
        this.name = name;
    }

    public static Codec<DesktopTheme> getCodec() {
        return CODEC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceLocation getPreviewTexture() {
        return uiTexture;
    }

    public ResourceLocation getIdentifier() {
        return identifier;
    }

    public ResourceLocation getStructureLocation() {
        return structureLocation;
    }
}
