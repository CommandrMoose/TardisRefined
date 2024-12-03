package whocraft.tardis_refined.api.event;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.util.RegistryHelper;

public class ShellChangeSources {

    public static final ResourceLocation ROOT_TO_TARDIS_ID = RegistryHelper.makeKey("root_to_tardis");
    public static final ResourceLocation GENERIC_UPDATE_ID = RegistryHelper.makeKey("generic_update");
    public static final ResourceLocation REGEN_EXISTING_TARDIS_ID = RegistryHelper.makeKey("regen_existing_tardis");

    public static final ShellChangeSource ROOT_TO_TARDIS = new ShellChangeSource(ROOT_TO_TARDIS_ID);
    public static final ShellChangeSource GENERIC_UPDATE = new ShellChangeSource(GENERIC_UPDATE_ID);
    public static final ShellChangeSource REGEN_EXISTING_TARDIS = new ShellChangeSource(REGEN_EXISTING_TARDIS_ID);
}
