package whocraft.tardis_refined.registry;


import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.registries.Registries;

import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.command.arguments.DesktopArgumentType;
import whocraft.tardis_refined.command.arguments.ShellArgumentType;
import whocraft.tardis_refined.command.arguments.UpgradeArgumentType;

public class TRArgumentTypeRegistry {

    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(TardisRefined.MODID, Registries.COMMAND_ARGUMENT_TYPE);

    public static final RegistryHolder<ArgumentTypeInfo<?, ?>, ?> UPGRADE_ARGUMENT = COMMAND_ARGUMENT_TYPES.register("upgrade", () ->
            registerByClass(UpgradeArgumentType.class, SingletonArgumentInfo.contextFree(UpgradeArgumentType::upgradeArgumentType)));

    public static final RegistryHolder<ArgumentTypeInfo<?, ?>, ?> DESKTOP_ARGUMENT = COMMAND_ARGUMENT_TYPES.register("desktop", () ->
            registerByClass(DesktopArgumentType.class, SingletonArgumentInfo.contextFree(DesktopArgumentType::desktopArgumentType)));

    public static final RegistryHolder<ArgumentTypeInfo<?, ?>, ?> SHELL_ARGUMENT = COMMAND_ARGUMENT_TYPES.register("shell", () ->
            registerByClass(ShellArgumentType.class, SingletonArgumentInfo.contextFree(ShellArgumentType::shellArgumentType)));

    private static synchronized <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>> I registerByClass(Class<A> infoClass, I argumentTypeInfo) {
        ArgumentTypeInfos.BY_CLASS.put(infoClass, argumentTypeInfo);
        return argumentTypeInfo;
    }
}