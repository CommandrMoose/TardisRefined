package whocraft.tardis_refined.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MalletItem extends Item {
    public MalletItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }


}
