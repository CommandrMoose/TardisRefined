package whocraft.tardis_refined.common.util.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import whocraft.tardis_refined.common.util.Platform;

import java.util.Collection;

public class PlatformImpl {

    public static MinecraftServer MINECRAFT_SERVER;


    public static boolean isProduction() {
        return !FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static Collection<String> getModIds() {
        return FabricLoader.getInstance().getAllMods().stream().map(ModContainer::getMetadata).map(ModMetadata::getId).toList();
    }

    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    public static boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

    public static MinecraftServer getServer() {
        MinecraftServer server;
        if (Platform.isClient()) {
            server = getServerFromClient();
        } else {
            server = MINECRAFT_SERVER;
        }
        return server;
    }

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> MINECRAFT_SERVER = server);
    }

    @Environment(EnvType.CLIENT)
    private static MinecraftServer getServerFromClient() {
        return Minecraft.getInstance().getSingleplayerServer();
    }

    public static boolean isForge() {
        return false;
    }
}
