package whocraft.tardis_refined.client;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableLeatherItem;
import whocraft.tardis_refined.common.items.DimensionSamplerItem;

public class TRItemColouring {

    // Sonic Colouring - changes the colour of any face that's blank and white and has a tintindex of 0
    public static ItemColor SCREWDRIVER_COLORS = (itemStack, tintIndex) -> {
        if (tintIndex == 0) {
            if (itemStack.getItem() instanceof DyeableLeatherItem dyeableLeatherItem) {
                return dyeableLeatherItem.getColor(itemStack);
            }
        }
        return 0; // We do not want to tint, so we let the usual colour take over
    };

    public static ItemColor SAMPLE_COLORS = (itemStack, tintIndex) -> {
        if (tintIndex == 2 && DimensionSamplerItem.hasDimAtAll(itemStack)) {
            if (itemStack.getItem() instanceof DimensionSamplerItem dimensionSamplerItem) {
                return dimensionSamplerItem.getColor(itemStack);
            }
        }
        return 0; // We do not want to tint, so we let the usual colour take over
    };



}
