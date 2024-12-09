package whocraft.tardis_refined.client.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;
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

    public static final ResourceLocation BAR_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/gui/bar/journey_bar.png");
    public static final ResourceLocation FUEL_BAR_TEXTURE = new ResourceLocation(TardisRefined.MODID, "textures/gui/bar/fuel_bar.png");
    public static boolean shouldRender = true;
    public static final RenderHelper.CustomProgressBar FUEL_BAR = new RenderHelper.CustomProgressBar(FUEL_BAR_TEXTURE, 256, 256, 11, 127, 60);
    private static final RenderHelper.CustomProgressBar PROGRESS_BAR = new RenderHelper.CustomProgressBar(BAR_TEXTURE, 256, 256, 5, 182, 60);

    public static void renderOverlay(GuiGraphics guiGraphics) {
        Minecraft mc = Minecraft.getInstance();
        if (!shouldRender)
            return;
        TardisPlayerInfo.get(mc.player).ifPresent(tardisPlayerInfo -> {
            // Exit if the player is not viewing the TARDIS or the debug screen is active
            if (!tardisPlayerInfo.isViewingTardis())
                return;
            TardisClientData tardisClientData = TardisClientData.getInstance(tardisPlayerInfo.getPlayerPreviousPos().getDimensionKey());

            PoseStack poseStack = guiGraphics.pose();
            int screenWidth = mc.getWindow().getGuiScaledWidth();
            int screenHeight = mc.getWindow().getGuiScaledHeight();

            int x = 10; // X position for text
            int y = 10; // Initial Y position for text

            // Background for text
            int textBackdropWidth = 150; // Width of the backdrop box
            int textBackdropHeight = 70; // Total height for the text backdrop box

            int remainingFuel = (int) tardisClientData.getFuel();
            int maxFuel = (int) tardisClientData.getMaximumFuel();
            int fuelPercentage = maxFuel != 0 ? (int) ((double) remainingFuel / maxFuel * 100) : 0;

            int throttleStage = tardisClientData.getThrottleStage();
            int maxThrottleStage = TardisPilotingManager.MAX_THROTTLE_STAGE;
            int throttlePercentage = maxThrottleStage != 0 ? (int) ((double) throttleStage / maxThrottleStage * 100) : 0;

            // Create a translatable component for the exit keybind
            Component exitKey = TRKeybinds.EXIT_EXTERIOR_VIEW.key.getDisplayName();
            MutableComponent message = Component.translatable(ModMessages.EXIT_EXTERNAL_VIEW).append(exitKey).withStyle(ChatFormatting.BOLD, ChatFormatting.WHITE);
            // Display throttle percentage
            MutableComponent throttleMessage = Component.literal("Throttle: " + throttlePercentage + "%").withStyle(ChatFormatting.WHITE);
            // Display fuel percentage
            MutableComponent fuelMessage = Component.translatable(ModMessages.FUEL, fuelPercentage).append("%").withStyle(ChatFormatting.WHITE);

            int messageWidth = mc.font.width(message);

            poseStack.pushPose();

            if (mc.screen == null) {
                poseStack.pushPose();
                poseStack.translate(5 + messageWidth / 2f, -3 + screenHeight - mc.font.lineHeight / 2f, 0);
                guiGraphics.fill(-messageWidth / 2, -3 - mc.font.lineHeight / 2, messageWidth / 2, 2 + mc.font.lineHeight / 2, 0x88000000);
                guiGraphics.drawString(mc.font, message.getString(), 8 - messageWidth / 2, -mc.font.lineHeight / 2, 0xFFFFFF, false); // White text
                poseStack.popPose();
            }

            {
                poseStack.pushPose();
                FUEL_BAR.animate = tardisClientData.isFlying();
                poseStack.translate(20, 20, 0);
                FUEL_BAR.blit(guiGraphics, 0, 0, (double) remainingFuel / maxFuel);
                guiGraphics.drawString(mc.font, fuelMessage.getString(), 3, 2, 0x572200, false); // White text
                poseStack.popPose();
            }

            guiGraphics.drawString(mc.font, throttleMessage.getString(), 20, 35, 0xFFFFFF, false); // White text

            float journeyProgress = tardisClientData.getJourneyProgress() / 100.0f;

            poseStack.popPose();

            if (tardisClientData.isFlying())
                renderJourneyProgressBar(guiGraphics, journeyProgress);

        });
    }


    public static void renderJourneyProgressBar(GuiGraphics guiGraphics, float journeyProgress) {
        Minecraft mc = Minecraft.getInstance();

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Clamp journey progress between 0.0 and 1.0
        float clampedProgress = Mth.clamp(journeyProgress, 0.0F, 1.0F);
        //clampedProgress = 0.75f;

        // Bar dimensions
        int barWidth = 182;
        int barHeight = 5;
        int barX = (screenWidth - barWidth) / 2;
        int barY = screenHeight - 25;

        // Bind the texture and render the bar
        PROGRESS_BAR.blit(guiGraphics, barX, barY, clampedProgress);

        // Render journey progress as a percentage above the bar
        String progressText = String.format("Journey: %.0f%%", clampedProgress * 100);
        Font fontRenderer = mc.font;
        int textX = (screenWidth - fontRenderer.width(progressText)) / 2;
        int textY = barY - 10;

        guiGraphics.drawString(mc.font, progressText, textX, textY, 0xFFFFFF, false); // White text
    }


}