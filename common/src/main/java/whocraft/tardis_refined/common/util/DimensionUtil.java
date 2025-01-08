package whocraft.tardis_refined.common.util;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.Set;

public class DimensionUtil {

    public static boolean isAllowedDimension(ResourceKey<Level> level) {
        String namespace = level.location().getNamespace();
        String location = level.location().toString();
        var bannedDimensions = TRConfig.SERVER.BANNED_DIMENSIONS.get();
        return !namespace.contains("immersive_portals") && !namespace.contains(TardisRefined.MODID) && !bannedDimensions.contains(location);
    }
    public static Set<ResourceKey<Level>> getTardisLevels(MinecraftServer server) {
        Set<ResourceKey<Level>> set = Sets.newHashSet();
        for (ServerLevel level : server.getAllLevels()) {
            if (level.dimensionTypeId() == TRDimensionTypes.TARDIS) {
                set.add(level.dimension());
            }
        }
        return set;
    }

    public static Set<ResourceKey<Level>> getAllowedDimensions(MinecraftServer server) {
        Set<ResourceKey<Level>> set = Sets.newHashSet();
        for (ServerLevel level : server.getAllLevels()) {
            if (isAllowedDimension(level.dimension())) {
                set.add(level.dimension());
            }
        }
        return set;
    }

    public static ServerLevel getLevel(ResourceKey<Level> levelResourceKey) {
        if(Platform.getServer() == null) {
            TardisRefined.LOGGER.error("Null Server when looking for {} - Can be ignored if logging out", levelResourceKey);
            return null;
        }
        return Platform.getServer().getLevel(levelResourceKey);
    }
}
