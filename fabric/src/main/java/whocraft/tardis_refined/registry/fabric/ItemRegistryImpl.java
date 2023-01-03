package whocraft.tardis_refined.registry.fabric;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.BlockRegistry;
import whocraft.tardis_refined.registry.ItemRegistry;

public class ItemRegistryImpl {

    public static final CreativeModeTab TAB = new FabricItemGroupBuilderImpl(new ResourceLocation(TardisRefined.MODID, TardisRefined.MODID))
            .icon(() -> new ItemStack(BlockRegistry.GLOBAL_CONSOLE_BLOCK.get())).displayItems(((featureFlagSet, output, bl) -> {
                for (Item stack : ItemRegistry.CREATIVE_ITEMS) {
                    output.accept(stack);
                }
            })).title(Component.literal("TARDIS Refined")).build();

    public static CreativeModeTab getCreativeTab() {
        return TAB;
    }

}
