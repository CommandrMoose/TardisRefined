package whocraft.tardis_refined.common.tardis.control.flight;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;

public class GenericControl extends whocraft.tardis_refined.common.tardis.control.Control {
    public GenericControl(ResourceLocation id) {
        super(id);
    }

    public GenericControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return this.onRightClick(operator, theme, controlEntity, player);
    }

    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        return true;
    }
}
