package whocraft.tardis_refined.common.util;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.Set;

public class DimensionUtil {

    public static boolean isAllowedDimension(ResourceKey<Level> level) {
        var bannedDimensions = TRConfig.SERVER.BANNED_DIMENSIONS.get();
        return !level.location().getNamespace().toString().contains("tardis") && !bannedDimensions.contains(level.location().toString());
    }

    public static Set<ResourceKey<Level>> getTardisLevels(MinecraftServer server) {
        Set<ResourceKey<Level>> set = Sets.newHashSet();
        for (ServerLevel level : server.getAllLevels()) {
            if (level.dimensionTypeRegistration() == TRDimensionTypes.TARDIS) {
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
}
