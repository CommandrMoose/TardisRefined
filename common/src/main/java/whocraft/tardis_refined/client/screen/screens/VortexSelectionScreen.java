package whocraft.tardis_refined.client.screen.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.renderer.vortex.VortexRenderer;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.network.messages.C2SChangeVortex;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class VortexSelectionScreen extends MonitorOS {

    private final List<ResourceLocation> vortexList;

    public VortexSelectionScreen(ResourceLocation currentVortex) {
        super(Component.translatable(ModMessages.UI_MONITOR_VORTEX), null);
        this.vortexList = VortexRegistry.VORTEX_REGISTRY.keySet().stream().toList();
        VortexSelectionScreen.currentVortex = currentVortex;
    }

    @Override
    protected void init() {
        super.init();
        this.setEvents(() -> selectVortex(currentVortex), () -> {
            //selectVortex(currentVortex);
            if (PREVIOUS != null)
                this.switchScreenToLeft(PREVIOUS);
        });
        if (currentVortex == null)
            currentVortex = this.vortexList.get(0);
        int vPos = (height - monitorHeight) / 2;
        addSubmitButton(width / 2 + 90, height - vPos - 25);
        addCancelButton(width / 2 - 11, height - vPos - 25);
    }

    public void selectVortex(ResourceLocation themeId) {
        assert Minecraft.getInstance().player != null;
        new C2SChangeVortex(Minecraft.getInstance().player.level().dimension(), themeId).send();
        //Minecraft.getInstance().setScreen(null);
    }

    @Override
    public void renderBackdrop(@NotNull GuiGraphics guiGraphics) {
        super.renderBackdrop(guiGraphics);

        PoseStack poseStack = guiGraphics.pose();

        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        poseStack.pushPose();

        int b = height - vPos, r = width - hPos;
        int l1 = hPos + monitorWidth / 4, l2 = hPos + monitorWidth / 2;

        guiGraphics.fill(l2, vPos, r, b, -1072689136);

        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
        poseStack.translate(-height, 0, 0);
        guiGraphics.fillGradient(vPos, l1, b, l2, 0x00000000, -1072689136);
        poseStack.popPose();
    }

    @Override
    public GenericMonitorSelectionList<SelectionListEntry> createSelectionList() {
        int leftPos = width / 2;
        int topPos = (height - monitorHeight) / 2;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 105, 80, leftPos, topPos + 15, topPos + monitorHeight - 30, 12);

        selectionList.setRenderBackground(false);

        Collection<VortexRegistry> values = VortexRegistry.VORTEX_REGISTRY.stream().toList();
        values = values.stream()
                .sorted(Comparator.comparing(theme -> theme.getDisplayName().toString()))
                .toList();

        for (VortexRegistry vort : values) {
            ResourceLocation vortId = VortexRegistry.VORTEX_REGISTRY.getKey(vort);

            SelectionListEntry selectionListEntry = new SelectionListEntry(vort.getDisplayName(), (entry) -> {
                currentVortex = vortId;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }

                entry.setChecked(true);
            }, leftPos);


            if (currentVortex.toString().equals(vortId.toString())) {
                selectionListEntry.setChecked(true);
            }

            selectionList.children().add(selectionListEntry);
        }

        return selectionList;
    }
}
