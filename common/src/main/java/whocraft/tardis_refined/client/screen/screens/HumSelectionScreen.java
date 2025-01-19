package whocraft.tardis_refined.client.screen.screens;

import com.mojang.brigadier.StringReader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.common.network.messages.hums.C2SChangeHum;
import whocraft.tardis_refined.common.soundscape.hum.HumEntry;
import whocraft.tardis_refined.common.soundscape.hum.TardisHums;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.Collection;
import java.util.Comparator;

public class HumSelectionScreen extends MonitorOS {

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/HumSelectionScreen");


    private HumEntry currentHumEntry;

    public HumSelectionScreen() {
        super(Component.translatable(ModMessages.UI_MONITOR_SELECT_HUM), new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/backdrop.png"));
    }

    public static void selectHum(HumEntry theme) {
        assert Minecraft.getInstance().player != null;
        new C2SChangeHum(Minecraft.getInstance().player.level().dimension(), theme).send();
        //Minecraft.getInstance().setScreen(null);
    }

    @Override
    protected void init() {
        super.init();
        this.setEvents(() -> HumSelectionScreen.selectHum(currentHumEntry), () -> {
            if (PREVIOUS != null)
                this.switchScreenToLeft(PREVIOUS);
        });
        this.currentHumEntry = grabHum();
        int vPos = (height - monitorHeight) / 2;
        addSubmitButton(width / 2 + 85, height - vPos - 25);
        addCancelButton(width / 2 - 105, height - vPos - 25);
    }

    private HumEntry grabHum() {
        for (HumEntry humEntry : TardisHums.getRegistry().values())
            return humEntry;

        return null;
    }

    @Override
    public ObjectSelectionList<SelectionListEntry> createSelectionList() {
        int vPos = (height - monitorHeight) / 2;
        int leftPos = this.width / 2 - 75;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 150, 80, leftPos, vPos + 15, vPos + monitorHeight - 30, 12);
        selectionList.setRenderBackground(false);

        Collection<HumEntry> knownHums = TardisHums.getRegistry().values();
        knownHums = knownHums.stream().sorted(Comparator.comparing(HumEntry::getNameComponent)).toList();

        for (HumEntry humEntry : knownHums) {
            Component name = Component.literal(MiscHelper.getCleanName(humEntry.getIdentifier().getPath()));
            Component tooltip;

            try {
                name = Component.Serializer.fromJson(new StringReader(humEntry.getNameComponent()));
                tooltip = Component.literal(ChatFormatting.BLUE + Platform.getModName(humEntry.getIdentifier().getNamespace()));
            } catch (Exception ex) {
                LOGGER.error("Could not process Name for hum {}", humEntry.getIdentifier());
                tooltip = Component.literal("Unknown");
            }

            SelectionListEntry entry = new SelectionListEntry(name, selectedEntry -> {
                this.currentHumEntry = humEntry;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }

                selectedEntry.setChecked(true);
            }, leftPos);

            entry.setTooltip(tooltip);
            selectionList.children().add(entry);
        }

        return selectionList;
    }


}