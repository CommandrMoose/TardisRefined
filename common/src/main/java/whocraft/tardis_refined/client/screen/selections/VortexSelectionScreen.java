package whocraft.tardis_refined.client.screen.selections;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexSorting;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4f;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.renderer.vortex.VortexRenderer;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.network.messages.C2SChangeVortex;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.List;

public class VortexSelectionScreen extends SelectionScreen {

    public static ResourceLocation MONITOR_TEXTURE = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/gui/shell.png");
    public static ResourceLocation NOISE = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "textures/gui/noise.png");
    private final List<ResourceLocation> vortexList;
    protected int imageWidth = 256;
    protected int imageHeight = 173;
    private ResourceLocation currentVortex;
    private int leftPos, topPos;

    public static final VortexRenderer VORTEX = new VortexRenderer(VortexRegistry.CLOUDS.get());


    public VortexSelectionScreen(ResourceLocation currentShellTheme) {
        super(Component.translatable(ModMessages.UI_MONITOR_VORTEX));
        this.vortexList = VortexRegistry.VORTEX_REGISTRY.keySet().stream().toList();
        this.currentVortex = currentShellTheme;
    }


    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void init() {
        this.setEvents(() -> {
            selectVortex(this.currentVortex);
        }, () -> {
            Minecraft.getInstance().setScreen(null);
        });
        if (currentVortex == null) {
            this.currentVortex = this.vortexList.get(0);
        }

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        addSubmitButton(width / 2 + 90, (height) / 2 + 34);
        addCancelButton(width / 2 - 11, (height) / 2 + 34);

        super.init();
    }

    public void selectVortex(ResourceLocation themeId) {
        new C2SChangeVortex(Minecraft.getInstance().player.level().dimension(), themeId).send();
        Minecraft.getInstance().setScreen(null);
    }


    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        PoseStack poseStack = guiGraphics.pose();
        Minecraft mc = Minecraft.getInstance();
        ClientLevel lvl = mc.level;
        assert lvl != null;
        RandomSource rand = lvl.random;

        boolean isCrashed = TardisClientData.getInstance(lvl.dimension()).isCrashing();

        if (isCrashed) {
            if (rand.nextInt(10) == 1) {
                for (int i1 = 0; i1 < 3; i1++) {
                    poseStack.translate(rand.nextInt(3) / 100F, rand.nextInt(3) / 100.0f, rand.nextInt(3) / 100.0f);
                }
            }
            if (rand.nextInt(20) == 1) {
                poseStack.scale(1, 1 + rand.nextInt(5) / 100F, 1);
            }
        }


        guiGraphics.enableScissor(leftPos + 3, topPos + 3, width - leftPos - 3, height - topPos - 3);
        RenderSystem.backupProjectionMatrix();
        Matrix4f perspective = new Matrix4f();
        perspective.perspective((float) Math.toRadians(mc.options.fov().get()), (float) width / (float) height, 0.01f, 9999, false, perspective);
        perspective.translate(0, 0, 11000f);
        RenderSystem.setProjectionMatrix(perspective, VertexSorting.DISTANCE_TO_ORIGIN);
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(20));
        VORTEX.time.speed = 0.3;
        VORTEX.vortexType = VortexRegistry.VORTEX_DEFERRED_REGISTRY.get(currentVortex);
        VORTEX.renderVortex(guiGraphics, 1, false);
        RenderSystem.restoreProjectionMatrix();
        poseStack.popPose();
        guiGraphics.disableScissor();

        guiGraphics.enableScissor(0, 0, width + 3, topPos + 3);
        this.renderTransparentBackground(guiGraphics);
        guiGraphics.disableScissor();
        guiGraphics.enableScissor(0, topPos + 3, leftPos + 3, height - topPos - 3);
        this.renderTransparentBackground(guiGraphics);
        guiGraphics.disableScissor();
        guiGraphics.enableScissor(width - leftPos - 3, topPos + 3, width, height - topPos - 3);
        this.renderTransparentBackground(guiGraphics);
        guiGraphics.disableScissor();
        guiGraphics.enableScissor(0, height - topPos - 3, width, height);
        this.renderTransparentBackground(guiGraphics);
        guiGraphics.disableScissor();

        poseStack.pushPose();
        int c = -1072689136;
        int l = leftPos + 3, t = topPos + 3, b = height - t, r = width - l;
        int l1 = leftPos + imageWidth / 4, l2 = leftPos + imageWidth / 2;

        guiGraphics.fill(l, t, r, b, 0x40000000);
        guiGraphics.fill(l2, t, r, b, -1072689136);

        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
        poseStack.translate(-height, 0, 0);
        guiGraphics.fillGradient(t, l1, b, l2, 0x00000000, -1072689136);
        poseStack.popPose();

        /*Render Back drop*/
        //RenderSystem.setShader(GameRenderer::getPositionTexShader); //REDUNDANT
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(MONITOR_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);


        double alpha = (100.0D - this.age * 3.0D) / 100.0D;
        if (isCrashed) {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float) alpha);
            guiGraphics.blit(NOISE, leftPos, topPos, this.noiseX, this.noiseY, imageWidth, imageHeight);
            RenderSystem.disableBlend();
        }
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        // super.renderBackground(guiGraphics, i, j, f);
    }


    @Override
    public Component getSelectedDisplayName() {
        VortexRegistry theme = VortexRegistry.VORTEX_DEFERRED_REGISTRY.get(this.currentVortex);
        return theme.getDisplayName();
    }

    @Override
    public GenericMonitorSelectionList createSelectionList() {
        int leftPos = width / 2 - 5;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 100, 80, leftPos, this.topPos + 30, this.topPos + this.imageHeight - 60, 12);

        selectionList.setRenderBackground(false);

        for (Holder.Reference<VortexRegistry> shellTheme : VortexRegistry.VORTEX_REGISTRY.holders().toList()) {
            VortexRegistry theme = shellTheme.value();
            ResourceLocation shellThemeId = shellTheme.key().location();


            SelectionListEntry selectionListEntry = new SelectionListEntry(theme.getDisplayName(), (entry) -> {
                this.currentVortex = shellThemeId;

                for (Object child : selectionList.children()) {
                    if (child instanceof SelectionListEntry current) {
                        current.setChecked(false);
                    }
                }

                age = 0;
                entry.setChecked(true);
            }, leftPos);

            if (currentVortex.toString().equals(shellThemeId.toString())) {
                selectionListEntry.setChecked(true);
            }

            selectionList.children().add(selectionListEntry);
        }

        return selectionList;
    }
}
