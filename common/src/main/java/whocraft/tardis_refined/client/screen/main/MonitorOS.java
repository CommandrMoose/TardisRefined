package whocraft.tardis_refined.client.screen.main;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexSorting;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.client.renderer.vortex.VortexRenderer;
import whocraft.tardis_refined.client.screen.ScreenHelper;
import whocraft.tardis_refined.client.screen.components.CommonTRWidgets;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.client.screen.screens.VortexSelectionScreen;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class MonitorOS extends Screen {

    public static ResourceLocation FRAME = new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/frame_brass.png");
    protected static final int frameWidth = 256, frameHeight = 180;
    protected static final int monitorWidth = 230, monitorHeight = 130;
    public final ResourceLocation backdrop;
    public static final VortexRenderer VORTEX = new VortexRenderer(VortexRegistry.CLOUDS.get());
    public static ResourceLocation currentVortex = VortexRegistry.VORTEX_REGISTRY.getKey(VortexRegistry.CLOUDS.get());
    public static ResourceLocation NOISE = new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/noise.png");
    public static ResourceLocation SYMBLS = new ResourceLocation(TardisRefined.MODID, "textures/gui/monitor/gallifreyan_symbols.png");

    public MonitorOS LEFT;
    public MonitorOS RIGHT;
    public MonitorOS PREVIOUS;

    public static final ResourceLocation BUTTON_LOCATION = new ResourceLocation(TardisRefined.MODID, "save");
    public static final ResourceLocation BCK_LOCATION = new ResourceLocation(TardisRefined.MODID, "back");
    public int shakeX, shakeY, age, transitionStartTime = -1;
    public float shakeAlpha;
    private MonitorOSRun onSubmit;
    private MonitorOSRun onCancel;

    public MonitorOS(Component title, ResourceLocation backdrop) {
        super(title);
        this.backdrop = backdrop;
    }

    @Override
    protected void init() {
        super.init();
        ObjectSelectionList<SelectionListEntry> list = createSelectionList();
        if (list != null) this.addRenderableWidget(list);
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    }

    public void render2Background(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        guiGraphics.enableScissor(0, 0, width, vPos + shakeY);
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.disableScissor();
        guiGraphics.enableScissor(0, vPos + shakeY, hPos + shakeX, height - vPos + shakeY);
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.disableScissor();
        guiGraphics.enableScissor(width - hPos + shakeX, vPos + shakeY, width, height - vPos + shakeY);
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.disableScissor();
        guiGraphics.enableScissor(0, height - vPos + shakeY, width, height);
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.disableScissor();
    }


    public void renderVortex(@NotNull GuiGraphics guiGraphics) {
        PoseStack poseStack = guiGraphics.pose();

        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        guiGraphics.enableScissor(hPos + shakeX, vPos + shakeY, width - hPos + shakeX, height - vPos + shakeY);
        RenderSystem.backupProjectionMatrix();
        assert minecraft != null;
        Matrix4f perspective = new Matrix4f();
        perspective.perspective((float) Math.toRadians(minecraft.options.fov().get()), (float) width / (float) height, 0.01f, 9999, false, perspective);
        perspective.translate(0, 0, 11000f);
        RenderSystem.setProjectionMatrix(perspective, VertexSorting.DISTANCE_TO_ORIGIN);
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(20));

        // Blindly assume that the player is not doing weird stuff to open the menu outside a TARDIS
        assert Minecraft.getInstance().level != null;
        TardisClientData tardisClientData = TardisClientData.getInstance(Minecraft.getInstance().level.dimension());

        VORTEX.vortexType = VortexRegistry.VORTEX_REGISTRY.get(this instanceof VortexSelectionScreen ? VortexSelectionScreen.currentVortex : tardisClientData.getVortex());
        VORTEX.time.speed = 0.3;
        VORTEX.renderVortex(guiGraphics, 1, false);
        RenderSystem.restoreProjectionMatrix();
        poseStack.popPose();
        guiGraphics.disableScissor();
    }

    public void renderBackdrop(@NotNull GuiGraphics guiGraphics) {
        if (backdrop == null) return;
        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;
        guiGraphics.blit(backdrop, hPos, vPos, 0, 0, monitorWidth, monitorHeight);
        int b = height - vPos, r = width - hPos;
        guiGraphics.fill(hPos, vPos, r, b, 0x40000000);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        render2Background(guiGraphics, mouseX, mouseY, partialTick);
        RenderSystem.enableBlend();
        renderVortex(guiGraphics);

        int hPos = (width - monitorWidth) / 2;
        int vPos = (height - monitorHeight) / 2;

        guiGraphics.enableScissor(hPos, vPos, width - hPos, height - vPos);

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();
        poseStack.translate(shakeX, shakeY, 0);

        int symb = 0 % 64;
        poseStack.pushPose();
        poseStack.translate(hPos + 10, vPos + 10, 0);
        poseStack.scale(2, 2, 1);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float) (System.currentTimeMillis() % 5400L) / 15L));
        poseStack.translate(-31 / 2f, -31 / 2f, 0);
        guiGraphics.blit(SYMBLS, 0, 0, 32 * (symb % 8), 32 * (symb / 8), 32, 32);
        poseStack.popPose();

        symb = 3 % 64;
        poseStack.pushPose();
        poseStack.translate(hPos + monitorWidth - 10, vPos + monitorHeight - 10, 0);
        poseStack.scale(2, 2, 1);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float) (System.currentTimeMillis() % 5400L) / 15L));
        poseStack.translate(-31 / 2f, -31 / 2f, 0);
        guiGraphics.blit(SYMBLS, 0, 0, 32 * (symb % 8), 32 * (symb / 8), 32, 32);
        poseStack.popPose();

        symb = 9 % 64;
        poseStack.pushPose();
        poseStack.translate(hPos + 10, vPos + monitorHeight - 10, 0);
        poseStack.scale(2, 2, 1);
        poseStack.mulPose(Axis.ZP.rotationDegrees(-(float) (System.currentTimeMillis() % 5400L) / 15L));
        poseStack.translate(-31 / 2f, -31 / 2f, 0);
        guiGraphics.blit(SYMBLS, 0, 0, 32 * (symb % 8), 32 * (symb / 8), 32, 32);
        poseStack.popPose();

        symb = 8 % 64;
        poseStack.pushPose();
        poseStack.translate(hPos + monitorWidth - 10, vPos + 10, 0);
        poseStack.scale(2, 2, 1);
        poseStack.mulPose(Axis.ZP.rotationDegrees(-(float) (System.currentTimeMillis() % 5400L) / 15L));
        poseStack.translate(-31 / 2f, -31 / 2f, 0);
        guiGraphics.blit(SYMBLS, 0, 0, 32 * (symb % 8), 32 * (symb / 8), 32, 32);
        poseStack.popPose();

        boolean right = RIGHT != null && PREVIOUS != null && RIGHT == PREVIOUS && transitionStartTime >= 0;
        boolean left = LEFT != null && PREVIOUS != null && LEFT == PREVIOUS && transitionStartTime >= 0;
        float t = (age - transitionStartTime + partialTick) / 10f;
        float o = -0.5f * Mth.cos(Mth.PI * t) + 0.5f;

        if (right || left) RenderSystem.setShaderColor(1, 1, 1, o);
        RenderSystem.enableBlend();
        renderBackdrop(guiGraphics);
        RenderSystem.enableBlend();
        if (right || left) RenderSystem.setShaderColor(1, 1, 1, 1 - o);
        if (right) {
            RIGHT.renderBackdrop(guiGraphics);
            poseStack.translate(monitorWidth * o, 0, 0);
            RIGHT.doRender(guiGraphics, mouseX, mouseY, partialTick);
        }

        if (left) {
            LEFT.renderBackdrop(guiGraphics);
            poseStack.translate(-monitorWidth * o, 0, 0);
            LEFT.doRender(guiGraphics, mouseX, mouseY, partialTick);
        }

        if (right || left) poseStack.translate(right ? -monitorWidth : monitorWidth, 0, 0);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        doRender(guiGraphics, mouseX, mouseY, partialTick);

        poseStack.popPose();

        guiGraphics.disableScissor();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (shakeAlpha + 1 - partialTick) / 100.0f);
        guiGraphics.blit(NOISE, hPos + shakeX, vPos + shakeY, (int) (Math.random() * 736), (int) (414 * (System.currentTimeMillis() % 1000) / 1000.0), monitorWidth, monitorHeight);
        RenderSystem.setShaderColor(1, 1, 1, 1);

        renderFrame(guiGraphics, mouseX, mouseY, partialTick);
        RenderSystem.disableBlend();
    }

    public void doRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        inMonitorRender(guiGraphics, mouseX, mouseY, partialTick);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        ScreenHelper.renderWidthScaledText(title.getString(), guiGraphics, Minecraft.getInstance().font, width / 2f, 5 + (height - monitorHeight) / 2f, Color.LIGHT_GRAY.getRGB(), 300, true);
    }

    public void inMonitorRender(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    }

    public void renderFrame(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int hPos = (width - frameWidth) / 2;
        int vPos = -13 + (height - monitorHeight) / 2;

        guiGraphics.blit(FRAME, hPos + shakeX, vPos + shakeY, 0, 0, frameWidth, frameHeight);
    }

    @Override
    public void tick() {
        super.tick();
        this.age++;
        if (transitionStartTime >= 0 && age - transitionStartTime >= 10) transitionStartTime = -1;

        if (minecraft == null || minecraft.level == null) return;
        boolean isCrashed = TardisClientData.getInstance(minecraft.level.dimension()).isCrashing();

        this.shakeAlpha--;

        if (isCrashed) this.shakeAlpha = 50;
        if (shakeAlpha < 0) shakeAlpha = 0;

        if (shakeAlpha > 0) {
            this.shakeX = (int) (this.shakeAlpha * (Math.random() - 0.5) * 0.5);
            this.shakeY = (int) (this.shakeAlpha * (Math.random() - 0.5) * 0.5);
        }
    }

    public void switchScreenToLeft(MonitorOS next) {
        this.LEFT = next;
        next.PREVIOUS = this;
        next.RIGHT = this;
        next.transition();
        Minecraft.getInstance().setScreen(next);
    }

    public void switchScreenToRight(MonitorOS next) {
        this.RIGHT = next;
        next.PREVIOUS = this;
        next.LEFT = this;
        next.transition();
        Minecraft.getInstance().setScreen(next);
    }

    public void transition() {
        transitionStartTime = age;
    }

    public void setEvents(MonitorOSRun onSubmit, MonitorOSRun onCancel) {
        this.onSubmit = onSubmit;
        this.onCancel = onCancel;
    }

    public void addSubmitButton(int x, int y) {
        if (onSubmit != null) {
            SpriteIconButton spriteiconbutton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Submit"), (arg) -> this.onSubmit.onPress(), true, BUTTON_LOCATION));
            spriteiconbutton.setPosition(x, y);
        }
    }

    public void addCancelButton(int x, int y) {
        if (onCancel != null) {
            SpriteIconButton spriteiconbutton = this.addRenderableWidget(CommonTRWidgets.imageButton(20, Component.translatable("Cancel"), (arg) -> this.onCancel.onPress(), true, BCK_LOCATION));
            spriteiconbutton.setPosition(x, y);
        }
    }

    public ObjectSelectionList<SelectionListEntry> createSelectionList() {
        return null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public interface MonitorOSRun {
        void onPress();
    }

    public static class MonitorOSExtension extends MonitorOS {

        public MonitorOSExtension(Component title, ResourceLocation currentShellThem) {
            super(title, null);
            CURRENTSHELLTHEME = currentShellThem;
            PATTERNCOLLECTION = ShellPatterns.getPatternCollectionForTheme(CURRENTSHELLTHEME);
            THEMELIST = ShellTheme.SHELL_THEME_REGISTRY.keySet().stream().toList();
            generateDummyGlobalShell();
        }

        @Override
        protected void init() {
            super.init();
            if (CURRENTSHELLTHEME == null) CURRENTSHELLTHEME = THEMELIST.get(0);
            if (PATTERN == null) PATTERN = PATTERNCOLLECTION.get(0);
        }

        public static GlobalShellBlockEntity GLOBALSHELL_BLOCKENTITY;
        public static ResourceLocation CURRENTSHELLTHEME;
        public static ShellPattern PATTERN;
        public static List<ResourceLocation> THEMELIST;
        public static List<ShellPattern> PATTERNCOLLECTION;

        public void renderShell(GuiGraphics guiGraphics, int x, int y, float scale) {
            ShellModel model = ShellModelCollection.getInstance().getShellEntry(CURRENTSHELLTHEME).getShellModel(PATTERN);
            model.setDoorPosition(false);
            Lighting.setupForEntityInInventory();
            PoseStack pose = guiGraphics.pose();
            pose.pushPose();
            pose.translate((float) x, y, 100.0F);
            pose.scale(-scale, scale, scale);
            pose.mulPose(Axis.XP.rotationDegrees(-15F));
            pose.mulPose(Axis.YP.rotationDegrees((float) (System.currentTimeMillis() % 5400L) / 15L));

            VertexConsumer vertexConsumer = guiGraphics.bufferSource().getBuffer(model.renderType(model.getShellTexture(PATTERN, false)));
            model.renderShell(GLOBALSHELL_BLOCKENTITY, false, false, pose, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            guiGraphics.flush();
            pose.popPose();
            Lighting.setupFor3DItems();
        }

        public static void generateDummyGlobalShell() {
            GLOBALSHELL_BLOCKENTITY = new GlobalShellBlockEntity(BlockPos.ZERO, TRBlockRegistry.GLOBAL_SHELL_BLOCK.get().defaultBlockState());
            assert Minecraft.getInstance().level != null;
            GLOBALSHELL_BLOCKENTITY.setLevel(Minecraft.getInstance().level);
            ResourceKey<Level> generatedLevelKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TardisRefined.MODID, UUID.randomUUID().toString()));
            GLOBALSHELL_BLOCKENTITY.setTardisId(generatedLevelKey);
            GLOBALSHELL_BLOCKENTITY.setShellTheme(ShellTheme.POLICE_BOX.getId());
            GLOBALSHELL_BLOCKENTITY.setPattern(ShellPatterns.DEFAULT);
        }
    }
}
