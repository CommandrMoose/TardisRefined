
package whocraft.tardis_refined.registry;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public abstract class RegistryHolder<R, T extends R> implements Supplier<T>, Holder<R> {

    public abstract ResourceLocation getId();

}