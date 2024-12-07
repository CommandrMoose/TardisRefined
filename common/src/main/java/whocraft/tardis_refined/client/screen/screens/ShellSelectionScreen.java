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
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ShellPatterns;

public class ShellSelectionScreen extends MonitorOS.MonitorOSExtension {

    private Button patternButton;

    public ShellSelectionScreen(ResourceLocation currentShellTheme) {
        super(Component.translatable(ModMessages.UI_SHELL_SELECTION), currentShellTheme);
    }

    @Override
    protected void init() {
        super.init();
        this.setEvents(
                () -> selectShell(this.currentShellTheme),
                () -> {
                    if (PREVIOUS != null)
                        this.switchScreenToRight(PREVIOUS);
                }
        );

        int vPos = (height - monitorHeight) / 2;

        addSubmitButton(width / 2 - 11, height - vPos - 25);
        addCancelButton(width / 2 + 90, height - vPos - 25);

        patternButton = addRenderableWidget(Button.builder(Component.literal(""), button -> {
            pattern = ShellPatterns.next(this.patternCollection, this.pattern);
            button.setMessage(Component.Serializer.fromJson(new StringReader(this.pattern.name())));
        }).pos(width / 2 + 14, height - vPos - 25).size(70, 20).build());

        patternButton.visible = false; //Hide when initialised. We will only show it when there are more than 1 pattern for a shell (via its {@link PatternCollection} )
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
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 100, 80, leftPos, topPos + 15, topPos + monitorHeight - 30, 12);

        selectionList.setRenderBackground(false);

        for (Holder.Reference<ShellTheme> shellTheme : ShellTheme.SHELL_THEME_REGISTRY.holders().toList()) {
            ShellTheme theme = shellTheme.value();
            ResourceLocation shellThemeId = shellTheme.key().location();

            if (theme == ShellTheme.HALF_BAKED.get()) {
                continue;
            }

            SelectionListEntry selectionListEntry = new SelectionListEntry(theme.getDisplayName(), (entry) -> {
                this.currentShellTheme = shellThemeId;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }
                this.patternCollection = ShellPatterns.getPatternCollectionForTheme(this.currentShellTheme);
                this.pattern = this.patternCollection.get(0);

                boolean themeHasPatterns = this.patternCollection.size() > 1;

                //Hide the pattern button if there is only one pattern available for the shell, else show it. (i.e. The default)
                patternButton.visible = themeHasPatterns;

                if (themeHasPatterns) //Update the button name now that we have confirmed that there is more than one pattern in the shell
                    this.patternButton.setMessage(Component.Serializer.fromJson(new StringReader(pattern.name())));

                age = 0;
                entry.setChecked(true);
            }, leftPos);

            if (currentShellTheme.toString().equals(shellThemeId.toString())) {
                selectionListEntry.setChecked(true);
            }

            selectionList.children().add(selectionListEntry);
        }

        return selectionList;
    }

    public void selectShell(ResourceLocation themeId) {
        assert Minecraft.getInstance().player != null;
        new C2SChangeShell(Minecraft.getInstance().player.level().dimension(), themeId, pattern).send();
        Minecraft.getInstance().setScreen(null);
    }

}
