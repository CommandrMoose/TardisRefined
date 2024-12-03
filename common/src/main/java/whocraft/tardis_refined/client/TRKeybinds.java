package whocraft.tardis_refined.client;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.constants.ModMessages;

public class TRKeybinds {

    public static KeyMapping EXIT_EXTERIOR_VIEW = new KeyMapping(ModMessages.KEYBIND_EXIT_VIEW, GLFW.GLFW_KEY_TAB, TardisRefined.NAME);
    public static KeyMapping TOGGLE_INFO_EXTERIOR_VIEW = new KeyMapping(ModMessages.KEYBIND_TOGGLE_INFO_EXTERIOR_VIEW, GLFW.GLFW_KEY_N, TardisRefined.NAME);


}
