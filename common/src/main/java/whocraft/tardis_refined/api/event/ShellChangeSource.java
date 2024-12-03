package whocraft.tardis_refined.api.event;

import net.minecraft.resources.ResourceLocation;

/**
 * Object to identify the source of Shell updates.
 */
public class ShellChangeSource {

    private ResourceLocation id;

    public ShellChangeSource(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public void setId(ResourceLocation id) {
        this.id = id;
    }
}
