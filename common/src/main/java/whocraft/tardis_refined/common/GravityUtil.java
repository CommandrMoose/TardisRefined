package whocraft.tardis_refined.common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import whocraft.tardis_refined.common.block.device.AntiGravityBlock;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import static net.minecraft.core.BlockPos.betweenClosed;

public class GravityUtil {

    private static final int MAX_Y = 30; // The most a shaft can carry a player up
    private static final double ACCELERATION = 0.2;

    public static boolean isInAntiGrav(Player player, AABB box, Level level) {
        if (level.dimensionTypeRegistration() != TRDimensionTypes.TARDIS) return false;

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (BlockPos pos : betweenClosed(new BlockPos((int) box.maxX, (int) box.maxY, (int) box.maxZ), new BlockPos((int) box.minX, (int) box.minY, (int) box.minZ))) {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.getBlock() instanceof AntiGravityBlock) {
                int space = blockState.getValue(AntiGravityBlock.SPACE);
                if (space > 0 && level.getBlockState(mutablePos.set(pos).move(0, 1, 0)).isAir()) {
                    AABB myGravBox = createGravityBoxFromLevel(pos, space);
                    if (myGravBox.intersects(player.getBoundingBox()) && player.blockPosition().getY() >= pos.getY()) {
                        spawnParticlesWithinAABB(level, myGravBox);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void spawnParticlesWithinAABB(Level level, AABB box) {
        for (int i = 0; i < 2; i++) {
            double x = box.minX + (box.maxX - box.minX) * level.random.nextDouble();
            double y = box.minY + (box.maxY - box.minY) * level.random.nextDouble();
            double z = box.minZ + (box.maxZ - box.minZ) * level.random.nextDouble();
            level.addParticle(ParticleTypes.ASH, x, y, z, 0, 0.2, 0);
        }
    }

    public static AABB createGravityBoxFromLevel(BlockPos blockPos, int range) {
        return new AABB(blockPos).inflate(range, MAX_Y, range);
    }

    public static boolean isInGravityShaft(Player player) {
        return isInAntiGrav(player, player.getBoundingBox().inflate(8, MAX_Y, 8), player.level());
    }

    static double easeMovement() {
        double smoothedMovement = Math.abs(ACCELERATION);
        return smoothedMovement * smoothedMovement * smoothedMovement;
    }
}
