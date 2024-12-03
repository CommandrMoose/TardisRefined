package whocraft.tardis_refined.client.screen.selections;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.brigadier.StringReader;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModel;
import whocraft.tardis_refined.client.model.blockentity.shell.ShellModelCollection;
import whocraft.tardis_refined.client.screen.components.GenericMonitorSelectionList;
import whocraft.tardis_refined.client.screen.components.SelectionListEntry;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.network.messages.ChangeShellMessage;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import java.util.*;
import java.util.stream.Stream;

public class ShellSelectionScreen extends SelectionScreen {

    public static ResourceLocation MONITOR_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/gui/shell.png");
    public static ResourceLocation NOISE = new ResourceLocation(TardisRefined.MODID, "textures/gui/noise.png");
    public static GlobalShellBlockEntity globalShellBlockEntity;
    private final List<ResourceLocation> themeList;
    protected int imageWidth = 256;
    protected int imageHeight = 173;
    private ResourceLocation currentShellTheme;
    private int leftPos, topPos;
    private ShellPattern pattern;
    private List<ShellPattern> patternCollection;
    private Button patternButton;

    public ShellSelectionScreen(ResourceLocation currentShellTheme) {
        super(Component.translatable(ModMessages.UI_SHELL_SELECTION));
        this.themeList = ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.keySet().stream().toList();
        generateDummyGlobalShell();
        this.currentShellTheme = currentShellTheme;

    }

    public static void generateDummyGlobalShell() {
        globalShellBlockEntity = new GlobalShellBlockEntity(BlockPos.ZERO, TRBlockRegistry.GLOBAL_SHELL_BLOCK.get().defaultBlockState());
        assert Minecraft.getInstance().level != null;
        globalShellBlockEntity.setLevel(Minecraft.getInstance().level);
        ResourceKey<Level> generatedLevelKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TardisRefined.MODID, UUID.randomUUID().toString()));
        globalShellBlockEntity.setTardisId(generatedLevelKey);
        globalShellBlockEntity.setShellTheme(ShellTheme.POLICE_BOX.getId());
        globalShellBlockEntity.setPattern(ShellPatterns.DEFAULT);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void init() {
        this.setEvents(() -> {
            selectShell(this.currentShellTheme);
        }, () -> {
            Minecraft.getInstance().setScreen(null);
        });
        if (currentShellTheme == null) {
            this.currentShellTheme = this.themeList.get(0);
        }
        this.patternCollection = ShellPatterns.getPatternCollectionForTheme(this.currentShellTheme);
        this.pattern = this.patternCollection.get(0);

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        addSubmitButton(width / 2 + 90, (height) / 2 + 35);
        addCancelButton(width / 2 - 11, (height) / 2 + 35);

        patternButton = addRenderableWidget(Button.builder(Component.literal(""), button -> {
            pattern = ShellPatterns.next(this.patternCollection, this.pattern);
            button.setMessage(Component.Serializer.fromJson(new StringReader(this.pattern.name())));
        }).pos(width / 2 + 14, (height) / 2 + 34).size(70, 20).build());

        patternButton.visible = false; //Hide when initialised. We will only show it when there are more than 1 pattern for a shell (via its {@link PatternCollection} )

        super.init();
    }

    public void selectShell(ResourceLocation themeId) {
        new ChangeShellMessage(Minecraft.getInstance().player.level().dimension(), themeId, pattern).send();
        Minecraft.getInstance().setScreen(null);
    }


    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        renderBackground(guiGraphics);
        PoseStack poseStack = guiGraphics.pose();
        ClientLevel lvl = Minecraft.getInstance().level;
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


        /*Render Back drop*/
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(MONITOR_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        /*Model*/
        renderShell(guiGraphics, width / 2 - 75, height / 2 - 20, 25F);
        //renderShell(guiGraphics, width / 2, height / 2, 25F);


        double alpha = (100.0D - this.age * 3.0D) / 100.0D;
        if (isCrashed) {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float) alpha);
            guiGraphics.blit(NOISE, leftPos, topPos, this.noiseX, this.noiseY, imageWidth, imageHeight);
            RenderSystem.disableBlend();
        }
        super.render(guiGraphics, i, j, f);
    }

    private void renderShell(GuiGraphics guiGraphics, int x, int y, float scale) {
        ShellModel model = ShellModelCollection.getInstance().getShellEntry(this.currentShellTheme).getShellModel(pattern);
        model.setDoorPosition(false);
        Lighting.setupForEntityInInventory();
        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        pose.translate((float) x, y, 100.0F);
        pose.scale(-scale, scale, scale);
        pose.mulPose(Axis.XP.rotationDegrees(-15F));
        pose.mulPose(Axis.YP.rotationDegrees((float) (System.currentTimeMillis() % 5400L) / 15L));

        VertexConsumer vertexConsumer = guiGraphics.bufferSource().getBuffer(model.renderType(model.getShellTexture(pattern, false)));
        model.renderShell(globalShellBlockEntity, false, false, pose, vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.flush();
        pose.popPose();
        Lighting.setupFor3DItems();
    }


    @Override
    public Component getSelectedDisplayName() {
        ShellTheme theme = ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.get(this.currentShellTheme);
        return theme.getDisplayName();
    }

    @Override
    public GenericMonitorSelectionList createSelectionList() {
        int leftPos = width / 2 - 5;
        GenericMonitorSelectionList<SelectionListEntry> selectionList = new GenericMonitorSelectionList<>(this.minecraft, 100, 80, leftPos, this.topPos + 30, this.topPos + this.imageHeight - 60, 12);

        selectionList.setRenderBackground(false);


        Set<Map.Entry<ResourceKey<ShellTheme>, ShellTheme>> allShells = ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.entrySet();

        Stream<Map.Entry<ResourceKey<ShellTheme>, ShellTheme>> sortedShells = allShells.stream().sorted(Comparator.comparing(o -> o.getValue().getDisplayName().toString()));

        for (Map.Entry<ResourceKey<ShellTheme>, ShellTheme> shellTheme : sortedShells.toList()) {
            ShellTheme theme = shellTheme.getValue();
            ResourceLocation shellThemeId = shellTheme.getKey().location();

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
}
