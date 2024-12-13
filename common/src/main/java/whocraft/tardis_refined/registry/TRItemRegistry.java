package whocraft.tardis_refined.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TRItemRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(TardisRefined.MODID, Registries.CREATIVE_MODE_TAB);
    public static final RegistryHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register("main_tab", TRItemRegistry::getCreativeTab);


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(TardisRefined.MODID, Registries.ITEM);
    public static List<RegistryHolder<Item, ?>> TAB_ITEMS = new ArrayList<>();
    public static final RegistryHolder<Item, ?> KEY = register("tardis_key", () -> new KeyItem(new Item.Properties().stacksTo(1)), true);
    public static final RegistryHolder<Item, ?> SCREWDRIVER = register("amethyst_screwdriver", () -> new ScrewdriverItem(new Item.Properties().stacksTo(1)), true);
    public static final RegistryHolder<Item, ?> PATTERN_MANIPULATOR = register("pattern_manipulator", () -> new Item(new Item.Properties().stacksTo(1)), true);
    public static final RegistryHolder<Item, ?> DRILL = register("drill", () -> new DrillItem(new Item.Properties().stacksTo(1)), true);

    public static final RegistryHolder<Item, ?> RAW_ZEITON = register("raw_zeiton", () -> new Item(new Item.Properties()), true);
    public static final RegistryHolder<Item, ?> ZEITON_INGOT = register("zeiton_ingot", () -> new ZeitonIngotItem(new Item.Properties()), true);
    public static final RegistryHolder<Item, ?> ZEITON_NUGGET = register("zeiton_nugget", () -> new Item(new Item.Properties()), true);
    public static final RegistryHolder<Item, ?> GLASSES = register("glasses", () -> new GlassesItem(new Item.Properties()), true);
    public static final RegistryHolder<Item, ?> MALLET = register("mallet", () -> new MalletItem(new Item.Properties().stacksTo(1).durability(50)), true);
    public static final RegistryHolder<Item, ?> TEST_TUBE = register("test_tube", () -> new DimensionSamplerItem(new Item.Properties().stacksTo(1)), true);


    private static <T extends Item> RegistryHolder<Item, ?> register(String id, Supplier<T> itemSupplier, boolean addToTab) {
        RegistryHolder<Item, ?> item = ITEMS.register(id, itemSupplier);
        if (addToTab) {
            TAB_ITEMS.add(item);
        }
        return item;
    }

    @ExpectPlatform
    public static CreativeModeTab getCreativeTab() {
        throw new RuntimeException(TardisRefined.PLATFORM_ERROR);
    }


}
