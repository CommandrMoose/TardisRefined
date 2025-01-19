package whocraft.tardis_refined.common.util.neoforge;

import net.minecraft.server.MinecraftServer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.neoforged.neoforgespi.language.IModInfo;

import java.util.Collection;

public class PlatformImpl {

    public static boolean isProduction() {
        return FMLLoader.isProduction();
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static Collection<String> getModIds() {
        return ModList.get().getMods().stream().map(IModInfo::getModId).toList();
    }

    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    public static boolean isServer() {
        return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
    }

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static boolean isForge() {
        return true;
    }

    public static String getModName(String namespace) {
        return ModList.get().getModContainerById(namespace)
                .map(modContainer -> modContainer.getModInfo().getDisplayName())
                .orElse(namespace);
    }

}