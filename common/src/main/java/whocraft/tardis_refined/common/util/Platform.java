package whocraft.tardis_refined.common.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.PlatformOnly;
import dev.architectury.injectables.targets.ArchitecturyTarget;
import net.minecraft.server.MinecraftServer;

import java.util.Collection;

public class Platform {

    private static final boolean FORGE = ArchitecturyTarget.getCurrentTarget().equals(PlatformOnly.FORGE);

    @ExpectPlatform
    public static boolean isProduction() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<String> getModIds() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isClient() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isServer() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isForge() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static MinecraftServer getServer() {
        throw new AssertionError();
    }

}