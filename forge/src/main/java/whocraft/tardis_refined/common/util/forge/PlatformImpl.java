package whocraft.tardis_refined.common.util.forge;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Collection;

public class PlatformImpl {

    public static boolean isProduction() {
        return FMLLoader.isProduction();
    }

    public static boolean isModLoaded(String id) {
        return LoadingModList.get().getModFileById(id) != null;
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