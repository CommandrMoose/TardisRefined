package whocraft.tardis_refined.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.items.PatternManipulatorItem;
import whocraft.tardis_refined.common.items.KeyItem;

public class ItemRegistry {

    public static CreativeModeTab MAIN_TAB = null;

    static {
        if (MAIN_TAB == null) {
            MAIN_TAB = getCreativeTab();
        }
    }

    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(TardisRefined.MODID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> KEY = ITEMS.register("tardis_key", () -> new KeyItem(new Item.Properties().stacksTo(1).tab(MAIN_TAB)));
    public static final RegistrySupplier<Item> PATTERN_MANIPULATOR = ITEMS.register("pattern_manipulator", () -> new PatternManipulatorItem(new Item.Properties().stacksTo(1).tab(MAIN_TAB)));



    @ExpectPlatform
    public static CreativeModeTab getCreativeTab() {
        throw new RuntimeException(TardisRefined.PLATFORM_ERROR);
    }

}
