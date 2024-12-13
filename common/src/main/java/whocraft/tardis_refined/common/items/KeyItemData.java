package whocraft.tardis_refined.common.items;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyItemData {

    public static List<ResourceKey<Level>> getKeychain(ItemStack stack) {
        return stack.get(TRItemData.KEYCHAIN.get());
    }

    public static void setKeychain(ItemStack stack, List<ResourceKey<Level>> dataType) {
        stack.set(TRItemData.KEYCHAIN.get(), dataType);
    }

    public static void addTardis(ItemStack itemStack, ResourceKey<Level> tardisKey) {
        List<ResourceKey<Level>> keychain = new ArrayList<>(getKeychain(itemStack));
        if (!keychain.contains(tardisKey)) {
            keychain.add(tardisKey);
            setKeychain(itemStack, keychain);
        }
    }

    public static boolean containsTardis(ItemStack itemStack, ResourceKey<Level> tardisKey) {
        return getKeychain(itemStack).contains(tardisKey);
    }
}
