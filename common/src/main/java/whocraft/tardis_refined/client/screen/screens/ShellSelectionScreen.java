package whocraft.tardis_refined.client.screen.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.brigadier.StringReader;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.main.MonitorOS;
import whocraft.tardis_refined.common.network.messages.C2SChangeShell;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ShellPatterns;

import java.util.Collection;
import java.util.Comparator;

public class ShellSelectionScreen extends MonitorOS.MonitorOSExtension {

    private Button patternButton;

    public ShellSelectionScreen(ResourceLocation currentShellTheme) {
        super(Component.translatable(ModMessages.UI_SHELL_SELECTION), currentShellTheme);
    }

    @Override
    protected void init() {
        super.init();
        this.setEvents(
                () -> selectShell(CURRENTSHELLTHEME),
                () -> {
                    //selectShell(CURRENTSHELLTHEME);
                    if (PREVIOUS != null)
                        this.switchScreenToLeft(PREVIOUS);
                }
        );

        int vPos = (height - monitorHeight) / 2;

        addSubmitButton(width / 2 + 90, height - vPos - 25);
        addCancelButton(width / 2 - 11, height - vPos - 25);

        patternButton = addRenderableWidget(Button.builder(Component.literal(""), button -> {
            PATTERN = ShellPatterns.next(PATTERNCOLLECTION, PATTERN);
            button.setMessage(Component.Serializer.fromJson(new StringReader(PATTERN.name())));
        }).pos(width / 2 + 14, height - vPos - 25).size(70, 20).build());
        boolean themeHasPatterns = PATTERNCOLLECTION.size() > 1;
        patternButton.visible = themeHasPatterns;
        if (themeHasPatterns) //Update the button name now that we have confirmed that there is more than one pattern in the shell
            this.patternButton.setMessage(Component.Serializer.fromJson(new StringReader(PATTERN.name())));
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
    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderShell(guiGraphics, width / 2 - 70, height / 2 - 5, 25F);
    }

    @Override
    public GenericMonitorSelectionList<SelectionListEntry> createSelectionList() {
        int leftPos = width / 2;
        int topPos = (height - monitorHeight) / 2;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 105, 80, leftPos, topPos + 15, topPos + monitorHeight - 30, 12);
        selectionList.setRenderBackground(false);

        Collection<ShellTheme> values = ShellTheme.SHELL_THEME_REGISTRY.stream().toList();
        values = values.stream()
                .sorted(Comparator.comparing(theme -> theme.getDisplayName().toString()))
                .toList();

        for (ShellTheme shellTheme : values) {
            ResourceLocation shellThemeId = ShellTheme.getKey(shellTheme);

            SelectionListEntry selectionListEntry = new SelectionListEntry(shellTheme.getDisplayName(), (entry) -> {
                CURRENTSHELLTHEME = shellThemeId;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }
                PATTERNCOLLECTION = ShellPatterns.getPatternCollectionForTheme(CURRENTSHELLTHEME);
                PATTERN = PATTERNCOLLECTION.get(0);

                boolean themeHasPatterns = PATTERNCOLLECTION.size() > 1;

                //Hide the pattern button if there is only one pattern available for the shell, else show it. (i.e. The default)
                patternButton.visible = themeHasPatterns;

                if (themeHasPatterns) //Update the button name now that we have confirmed that there is more than one pattern in the shell
                    this.patternButton.setMessage(Component.Serializer.fromJson(new StringReader(PATTERN.name())));

                entry.setChecked(true);
            }, leftPos);

            if (CURRENTSHELLTHEME.toString().equals(shellThemeId.toString())) {
                selectionListEntry.setChecked(true);
            }

            selectionList.children().add(selectionListEntry);
        }

        return selectionList;
    }

    public void selectShell(ResourceLocation themeId) {
        assert Minecraft.getInstance().player != null;
        new C2SChangeShell(Minecraft.getInstance().player.level().dimension(), themeId, PATTERN).send();
        //Minecraft.getInstance().setScreen(null);
    }

}
