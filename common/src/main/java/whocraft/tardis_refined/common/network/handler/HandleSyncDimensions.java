package whocraft.tardis_refined.common.network.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Set;

public class HandleSyncDimensions {

    public static void handleDimSyncPacket(ResourceKey<Level> level, boolean add) {
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().player.connection.levels() == null) {
            return;
        }

        Set<ResourceKey<Level>> levels = Minecraft.getInstance().player.connection.levels();

        if (add && !levels.contains(level)) {
            levels.add(level);
        } else if (!add) {
            levels.remove(level);
        }
    }


}
