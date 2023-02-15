package whocraft.tardis_refined.client.screen.selections;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.screen.RenderPanorama;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.common.network.messages.ChangeDesktopMessage;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.constants.ModMessages;

import static whocraft.tardis_refined.client.screen.selections.ShellSelectionScreen.NOISE;

public class DesktopSelectionScreen extends SelectionScreen {

    private DesktopTheme currentDesktopTheme;


    protected int imageWidth = 256;
    protected int imageHeight = 173;
    private int leftPos, topPos;

    public static ResourceLocation MONITOR_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/ui/desktop.png");
    public static ResourceLocation MONITOR_TEXTURE_OVERLAY = new ResourceLocation(TardisRefined.MODID, "textures/ui/desktop_overlay.png");

    public static final CubeMap CUBE_MAP = new CubeMap(new ResourceLocation("textures/gui/title/background/panorama"));
    private PanoramaRenderer panorama;

    public DesktopSelectionScreen() {
        super(Component.translatable(ModMessages.UI_DESKTOP_SELECTION));
        this.panorama = new PanoramaRenderer(CUBE_MAP);
    }

    @Override
    protected void init() {
        this.setEvents(() -> {
            DesktopSelectionScreen.selectDesktop(currentDesktopTheme);
        }, () -> {
            Minecraft.getInstance().setScreen(null);
        });
        this.currentDesktopTheme = grabDesktop();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        addSubmitButton(width / 2 + 90, (height) / 2 + 35);
        addCancelButton(width / 2 + 40, (height) / 2 + 35);

        super.init();
    }

    private DesktopTheme grabDesktop() {
        for (DesktopTheme desktop : TardisDesktops.DESKTOPS) {
            if (desktop.availableByDefault) {
                return desktop;
            }
        }
        return null;
    }

    @Override
    public void render(PoseStack poseStack, int i, int j, float f) {
        renderBackground(poseStack);

        /*Render Back drop*/
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(255, 255, 255, 255);
        RenderSystem.setShaderTexture(0, MONITOR_TEXTURE);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);


        /*Render Interior Image*/
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(255, 255, 255, 255);
        RenderSystem.setShaderTexture(0, currentDesktopTheme.getPreviewTexture());
        poseStack.pushPose();
        poseStack.translate(width / 2 - 110, height / 2 - 70, 0);
        poseStack.scale(0.31333333F, 0.31333333F, 0.313333330F);

        double alpha = (100.0D - this.age * 3.0D) / 100.0D;
        blit(poseStack, 0, 0, 0, 0, 400, 400, 400, 400);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float) alpha);
        RenderSystem.setShaderTexture(0, NOISE);
        blit(poseStack, 0, 0, this.noiseX, this.noiseY, 400, 400);
        RenderSystem.disableBlend();


        poseStack.popPose();


        /*Render Back drop*/
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(255, 255, 255, 255);
        RenderSystem.setShaderTexture(0, MONITOR_TEXTURE_OVERLAY);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        super.render(poseStack, i, j, f);

        RenderPanorama.drawCube(poseStack, 0, 0, 400, 400, 360);


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
        GenericMonitorSelectionList<GenericMonitorSelectionList.Entry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, width / 2 + 45, height / 2 - 60, 60, 80, 12);
        selectionList.setRenderBackground(false);
        selectionList.setRenderTopAndBottom(false);

        for (DesktopTheme desktop : TardisDesktops.DESKTOPS) {
            if (desktop.availableByDefault) {
                selectionList.children().add(new GenericMonitorSelectionList.Entry(desktop.getDisplayName(), (entry) -> {
                    this.currentDesktopTheme = desktop;

                    for (Object child : selectionList.children()) {
                        if (child instanceof GenericMonitorSelectionList.Entry current) {
                            current.setChecked(false);
                        }
                    }
                    entry.setChecked(true);
                    age = 0;
                }));
            }
        }

        return selectionList;
    }

}
