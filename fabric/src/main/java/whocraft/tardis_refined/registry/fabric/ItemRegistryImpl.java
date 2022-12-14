package whocraft.tardis_refined.registry.fabric;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.ItemRegistry;

public class ItemRegistryImpl {

    public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(new ResourceLocation(TardisRefined.MODID, TardisRefined.MODID), () -> new ItemStack(ItemRegistry.KEY.get()));

    public static CreativeModeTab getCreativeTab() {
        return TAB;
    }

}
