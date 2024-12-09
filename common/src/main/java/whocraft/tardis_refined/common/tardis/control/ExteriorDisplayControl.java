package whocraft.tardis_refined.common.tardis.control;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenShellSelection;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;

public class ExteriorDisplayControl extends Control {

    public ExteriorDisplayControl(ResourceLocation id, String langId) {
        super(id, langId);
    }

    public ExteriorDisplayControl(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if(player instanceof ServerPlayer serverPlayer){
            sendPacket(serverPlayer, operator);
        }
        return true;
    }

    private void sendPacket(ServerPlayer player, TardisLevelOperator tardisLevelOperator) {
        new S2COpenShellSelection(tardisLevelOperator.getAestheticHandler().getShellTheme()).send(player);
    }


    @Override
    public boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            sendPacket(serverPlayer, operator);
        }
        return true;
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public Component getCustomControlName(TardisLevelOperator operator, ControlEntity entity, ControlSpecification controlSpecification) {
        return Component.translatable(ShellTheme.getShellTheme(operator.getAestheticHandler().getShellTheme()).getTranslationKey());
    }
}
