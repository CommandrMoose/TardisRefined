package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

/**
 * Tickable version of the BaseHandler
 */
public abstract class TickableHandler extends BaseHandler {
    /**
     * Method that is run every tick. This can happen on both server and client side
     * <br> Make sure to check your logic for if you need
     */
    public void tick(Level level) {
    }

    public void tick() {
    }

    /**
     * Method that is run every tick.
     *
     * @param operatorLevel - The ServerLevel used by the TardisLevelOperator
     * @implNote Use this if we need to run logic on the server side and use the TardisLevelOperator's ServerLevel.
     */
    public abstract void tick(ServerLevel operatorLevel);
}
