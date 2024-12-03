package whocraft.tardis_refined.client.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Transformation;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.GravityUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class GravityOverlay {

    private static boolean isInShaft = false;

    private static void checkOverlay(Player player) {
        isInShaft = GravityUtil.isInGravityShaft(player);
    }

    public static void renderOverlay(GuiGraphics guiGraphics) {
        Minecraft mc = Minecraft.getInstance();
        Font fontRenderer = mc.font;
        LocalPlayer player = mc.player;
        PoseStack poseStack = guiGraphics.pose();

        // Perform overlay checks periodically
        if (player.tickCount % 100 == 0) {
            checkOverlay(player);
        }

        if (isInShaft && !mc.options.renderDebug) {
            poseStack.pushPose();
            poseStack.scale(1.2f, 1.2f, 1.2f);

            // Padding for better positioning
            int padding = 15; // Amount of padding from the screen edges
            int x = padding;
            int y = padding;

            Component ascendKey = mc.options.keyJump.key.getDisplayName();
            Component descendKey = mc.options.keyShift.key.getDisplayName();

            // Get the translated strings for both keys
            String ascendKeyText = Component.translatable(ModMessages.ASCEND_KEY, ascendKey).getString();
            String descendKeyText = Component.translatable(ModMessages.DESCEND_KEY, descendKey).getString();

            // Calculate the longest key text width
            int maxWidth = Math.max(fontRenderer.width(ascendKeyText), fontRenderer.width(descendKeyText));

            MultiBufferSource.BufferSource renderImpl = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

            // Render both key texts
            fontRenderer.drawInBatch(
                    ascendKeyText,
                    x,
                    y,
                    ChatFormatting.WHITE.getColor(),
                    true,
                    Transformation.identity().getMatrix(),
                    renderImpl,
                    Font.DisplayMode.NORMAL,
                    0,
                    15728880
            );

            fontRenderer.drawInBatch(
                    descendKeyText,
                    x,
                    y + fontRenderer.lineHeight,
                    ChatFormatting.WHITE.getColor(),
                    true,
                    Transformation.identity().getMatrix(),
                    renderImpl,
                    Font.DisplayMode.NORMAL,
                    0,
                    15728880
            );

            renderImpl.endBatch();

            guiGraphics.fill(
                    x - 5,
                    y - 5,
                    x + maxWidth,
                    y + fontRenderer.lineHeight * 2,
                    0x88000000
            );

            poseStack.popPose();
        }
    }


}
