package whocraft.tardis_refined.common.blockentity.life;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;
import whocraft.tardis_refined.registry.TRDamageSources;

import java.util.List;

public class EyeBlockEntity extends BlockEntity implements BlockEntityTicker<EyeBlockEntity> {
    public EyeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TRBlockEntityRegistry.THE_EYE.get(), blockPos, blockState);


    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, EyeBlockEntity blockEntity) {

        if (level instanceof ServerLevel serverLevel) {
            List<Entity> entities = level.getEntities(null, new AABB(blockPos).inflate(3));
            if (entities != null) {
                for (Entity entity : entities) {
                    entity.setRemainingFireTicks(30);
                    entity.hurt(TRDamageSources.getSource(serverLevel, TRDamageSources.EYE_OF_HARMONY), 10000f);
                }
            }
        }


    }

}
