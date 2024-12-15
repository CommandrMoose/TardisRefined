package whocraft.tardis_refined.client.screen.components;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public class CommonTRWidgets {

    public static Button imageButton(int width, MutableComponent component, Button.OnPress onPress, boolean iconOnly, ResourceLocation texture) {
        return new ImageButton(0, 0, 20, 20, 0, 0, 20, texture, width, width, onPress, component);
    }
}
