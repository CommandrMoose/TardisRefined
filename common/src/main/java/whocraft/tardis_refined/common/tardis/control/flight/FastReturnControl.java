package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.patterns.sound.ConfiguredSound;

public class FastReturnControl extends whocraft.tardis_refined.common.tardis.control.Control {
    public FastReturnControl(ResourceLocation id) {
        super(id);
    }

    public FastReturnControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return this.onRightClick(operator, theme, controlEntity, player);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if (!operator.getLevel().isClientSide()) {
            TardisPilotingManager pilotManager = operator.getPilotingManager();
            if (pilotManager.preloadFastReturn()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public ConfiguredSound getFailSound(TardisLevelOperator operator, ConsoleTheme theme, boolean leftClick) {
        return new ConfiguredSound(SoundEvents.NOTE_BLOCK_BIT.value(), 1F);
    }
}
