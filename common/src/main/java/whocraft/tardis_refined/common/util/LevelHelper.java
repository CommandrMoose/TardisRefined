package whocraft.tardis_refined.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

/**
 * Helpers related to levels
 **/
public class LevelHelper {

    public static Vec3 centerPos(BlockPos pos, boolean centerY) {
        return new Vec3(pos.getX() + 0.5, pos.getY() + (centerY ? 0.5 : 0), pos.getZ() + 0.5);
    }

    public static float getAdjustedRotation(float rot) {
        float newR = rot % 360.0F;
        if (newR < 0)
            return 360.0F + newR;

        return newR;
    }

    public static float getAngleFromDirection(Direction dir) {
        switch (dir) {
            case EAST:
                return 90.0F;
            case SOUTH:
                return 180.0F;
            case WEST:
                return 270.0F;
            default:
                return 0.0F;
        }
    }

    /**
     * Gets a list of block positions for every direction
     *
     * @param referencePoint        - Position to use for searching
     * @param radius                - Radius around the reference point to search for.
     * @param interCardinal         - if we need to include intercardinal directions such as (North-East, South-East etc.)
     * @param includeReferencePoint - if we should include the reference point as part of the list too.
     * @return
     */
    public static List<BlockPos> getBlockPosInRadius(BlockPos referencePoint, int radius, boolean interCardinal, boolean includeReferencePoint) {
        List<BlockPos> posList = new ArrayList<>();

        //Add all horizontal directions, with the option of adding any intercardinal directions (North-East, South-East etc.)
        List<Direction> horizontalDirections = new ArrayList<>();
        horizontalDirections.addAll(Direction.Plane.HORIZONTAL.stream().toList());

        for (Direction dir : horizontalDirections) {
            BlockPos offsettedPos = referencePoint.relative(dir, radius);
            posList.add(offsettedPos);
            if (interCardinal) {
                BlockPos interCardinalPos = offsettedPos.offset(dir.getClockWise().getNormal());
                posList.add(interCardinalPos);
            }
        }

        //If we want to include the original reference point, add it as well.
        if (includeReferencePoint)
            posList.add(referencePoint);

        return posList;
    }


}
