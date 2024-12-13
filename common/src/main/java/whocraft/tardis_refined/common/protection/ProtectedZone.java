package whocraft.tardis_refined.common.protection;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

public class ProtectedZone {
    private final BlockPos topCorner;
    private final BlockPos bottomCorner;
    private boolean allowPlacement = false;
    private boolean allowBreaking = false;

    private String name = "";

    public ProtectedZone(BlockPos topCorner, BlockPos bottomCorner) {
        this.topCorner = topCorner;
        this.bottomCorner = bottomCorner;
    }

    public ProtectedZone(BlockPos topCorner, BlockPos bottomCorner, String name) {
        this.topCorner = topCorner;
        this.bottomCorner = bottomCorner;
        this.name = name;
    }

    public boolean isAllowPlacement() {
        return allowPlacement;
    }

    public ProtectedZone setAllowPlacement(boolean allowPlacement) {
        this.allowPlacement = allowPlacement;
        return this;
    }

    public boolean isAllowBreaking() {
        return allowBreaking;
    }

    public ProtectedZone setAllowBreaking(boolean allowBreaking) {
        this.allowBreaking = allowBreaking;
        return this;
    }

    public String getName() {
        return name;
    }

    public AABB getArea() {
        return new AABB(topCorner.getCenter(), bottomCorner.getCenter());
    }
}
