package whocraft.tardis_refined.client.screen.selections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import whocraft.tardis_refined.ModMessages;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.common.network.messages.ChangeDesktopMessage;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;

import java.util.List;

public class DesktopSelectionScreen extends SelectionScreen {

    private List<DesktopTheme> themeList;
    private DesktopTheme currentDesktopTheme;

    public DesktopSelectionScreen() {
        super(Component.translatable(ModMessages.UI_DESKTOP_SELECTION));
        this.themeList = TardisDesktops.DESKTOPS;
    }

    @Override
    protected void init() {
        this.setEvents(() -> {
            DesktopSelectionScreen.selectDesktop(currentDesktopTheme);
        }, ()-> {
            Minecraft.getInstance().setScreen(null);
        });
        this.currentDesktopTheme = this.themeList.get(0);

        super.init();
    }


    public static void selectDesktop(DesktopTheme theme) {
        new ChangeDesktopMessage(Minecraft.getInstance().player.getLevel().dimension(), theme).send();
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    public Component getSelectedDisplayName() {
        return currentDesktopTheme.getDisplayName();
    }

    @Override
    public ObjectSelectionList createSelectionList() {
        var selectionList = new GenericMonitorSelectionList(this.minecraft, this.listWidth, this.listHeight, 0, 100, 15);
        selectionList.setRenderBackground(false);
        selectionList.setRenderTopAndBottom(false);

        var desktops = TardisDesktops.DESKTOPS.stream().filter(x -> x.availableByDefault);
        desktops.forEach(x -> selectionList.children().add(new GenericMonitorSelectionList.Entry(x.getDisplayName(), () -> {
            this.currentDesktopTheme = x;
        })));
        return selectionList;
    }

}
