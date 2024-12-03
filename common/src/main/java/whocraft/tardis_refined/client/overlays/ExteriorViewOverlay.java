package whocraft.tardis_refined.client.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TRKeybinds;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.client.renderer.RenderHelper;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.constants.ModMessages;

public class ExteriorViewOverlay {

    public static final ResourceLocation BAR_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/gui/bar_texture.png");
    public static boolean shouldRender = true;

    public static void renderOverlay(GuiGraphics guiGraphics) {
        Minecraft mc = Minecraft.getInstance();

        if (!shouldRender) {
            return;
        }

        TardisPlayerInfo.get(mc.player).ifPresent(tardisPlayerInfo -> {
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();

            // Exit if the player is not viewing the TARDIS or the debug screen is active
            if (!tardisPlayerInfo.isViewingTardis()) {
                poseStack.popPose();
                return;
            }

            TardisClientData tardisClientData = TardisClientData.getInstance(
                    tardisPlayerInfo.getPlayerPreviousPos().getDimensionKey()
            );

            int x = 10; // X position for text
            int y = 10; // Initial Y position for text

            int remainingFuel = (int) tardisClientData.getFuel();
            int maxFuel = (int) tardisClientData.getMaximumFuel();
            int fuelPercentage = maxFuel != 0 ? (int) ((double) remainingFuel / maxFuel * 100) : 0;


            // Background for text
            int textBackdropWidth = 150; // Width of the backdrop box
            int textBackdropHeight = 70; // Total height for the text backdrop box
            guiGraphics.fill(x - 5, y - 5, x + textBackdropWidth, y + textBackdropHeight, 0x88000000);

            // Create a translatable component for the exit keybind
            Component exitKey = TRKeybinds.EXIT_EXTERIOR_VIEW.key.getDisplayName();
            MutableComponent message = Component.translatable(ModMessages.EXIT_EXTERNAL_VIEW).append(exitKey)
                    .withStyle(ChatFormatting.BOLD, ChatFormatting.WHITE);

            int throttleStage = tardisClientData.getThrottleStage();
            int maxThrottleStage = TardisPilotingManager.MAX_THROTTLE_STAGE;
            int throttlePercentage = maxThrottleStage != 0
                    ? (int) ((double) throttleStage / maxThrottleStage * 100)
                    : 0;

            // Display throttle percentage
            MutableComponent throttleMessage = Component.literal("Throttle: " + throttlePercentage + "%")
                    .withStyle(ChatFormatting.WHITE);

            renderPlayerHeads(guiGraphics, mc, x, y + 45);

            // Display fuel percentage
            MutableComponent fuelMessage = Component.translatable(ModMessages.FUEL, fuelPercentage).append("%")
                    .withStyle(ChatFormatting.WHITE);

            guiGraphics.drawString(mc.font, message.getString(), x, y, 0xFFFFFF, false); // White text
            guiGraphics.drawString(mc.font, throttleMessage.getString(), x, y + 15, 0xFFFFFF, false); // White text
            guiGraphics.drawString(mc.font, fuelMessage.getString(), x, y + 30, 0xFFFFFF, false); // White text

            float journeyProgress = tardisClientData.getJourneyProgress() / 100;

            poseStack.popPose();

            if (tardisClientData.isFlying()) {
                renderJourneyProgressBar(guiGraphics, journeyProgress);
            }

        });
    }

    private static void renderPlayerHeads(GuiGraphics guiGraphics, Minecraft mc, int x, int y) {
        // Render player's face and name
        LocalPlayer player = mc.player;
        if (player != null) {
            // Render the player's face
            int faceX = x;
            int faceY = y + 1; // Position below fuel
            int faceSize = 10;

            RenderHelper.renderPlayerFace(guiGraphics, x, y, faceSize, player.getUUID());

            // Render the player's name
            String playerName = player.getName().getString();

            guiGraphics.drawString(mc.font, playerName, faceX + faceSize + 5, faceY, 0xFFFFFF, false); // White text
        }
    }

    public static void renderJourneyProgressBar(GuiGraphics guiGraphics, float journeyProgress) {
        Minecraft mc = Minecraft.getInstance();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Clamp journey progress between 0.0 and 1.0
        float clampedProgress = Mth.clamp(journeyProgress, 0.0F, 1.0F);

        // Bar dimensions
        int barWidth = 182;
        int barHeight = 5;
        int barX = (screenWidth - barWidth) / 2;
        int barY = screenHeight - 25;

        // Bind the texture and render the bar
        guiGraphics.blit(BAR_TEXTURE, barX, barY, 0, 0, barWidth, barHeight); // Background bar
        guiGraphics.blit(BAR_TEXTURE, barX, barY, 0, 5, (int) (barWidth * clampedProgress), barHeight); // Progress bar


        // Render journey progress as a percentage above the bar
        String progressText = String.format("Journey: %.0f%%", clampedProgress * 100);
        Font fontRenderer = mc.font;
        int textX = (screenWidth - fontRenderer.width(progressText)) / 2;
        int textY = barY - 10;

        guiGraphics.drawString(mc.font, progressText, textX, textY, 0xFFFFFF, false); // White text
    }


}