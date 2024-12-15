package whocraft.tardis_refined.client.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
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
    private static final RenderHelper.CustomProgressBar PROGRESS_BAR = new RenderHelper.CustomProgressBar(BAR_TEXTURE, 256, 256, 5, 182, 60);
    public static final RenderHelper.CustomProgressBar FUEL_BAR = new RenderHelper.CustomProgressBar(FUEL_BAR_TEXTURE, 256, 256, 11, 127, 60);

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

            // Background for keybind text
            int x = 10; // X position for text
            int y = 10; // Initial Y position for text

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

            // Get player coordinates
            BlockPos pos = mc.player.blockPosition();
            BlockPos targetPos = pos.east(120);

            Vec3 currentPos = new Vec3(pos.getX(), pos.getY(), pos.getZ());

            double progress = tardisClientData.getJourneyProgress();
            int xV = (int) (currentPos.x + ((targetPos.getX() - currentPos.x) * progress));
            int yV = (int) (currentPos.y + ((targetPos.getY() - currentPos.y) * progress));
            int zV = (int) (currentPos.z + ((targetPos.getZ() - currentPos.z) * progress));

            BlockPos landingLocation = new BlockPos(xV, yV, zV);

            MutableComponent coordsMessage = Component.literal(
                    String.format("Coordinates: X: %d Y: %d Z: %d", landingLocation.getX(), landingLocation.getY(), landingLocation.getZ())
            ).withStyle(ChatFormatting.WHITE);

            int coordsWidth = mc.font.width(coordsMessage);

            poseStack.pushPose();

            // Render keybind message
            if (mc.screen == null) {
                poseStack.pushPose();
                int messageWidth = mc.font.width(message);
                poseStack.translate(5 + messageWidth / 2f, -3 + screenHeight - mc.font.lineHeight / 2f, 0);
                guiGraphics.fill(-messageWidth / 2, -3 - mc.font.lineHeight / 2, messageWidth / 2, 2 + mc.font.lineHeight / 2, 0x88000000);
                guiGraphics.drawString(mc.font, message.getString(), 8 - messageWidth / 2, -mc.font.lineHeight / 2, 0xFFFFFF, false);
                poseStack.popPose();
            }

            // Render fuel bar
            {
                poseStack.pushPose();
                FUEL_BAR.animate = tardisClientData.isFlying();
                poseStack.translate(20, 20, 0);
                FUEL_BAR.blit(guiGraphics, 0, 0, (double) remainingFuel / maxFuel);
                guiGraphics.drawString(mc.font, fuelMessage.getString(), 3, 2, 0x572200, false);
                poseStack.popPose();
            }

            guiGraphics.drawString(mc.font, throttleMessage.getString(), 20, 35, 0xFFFFFF, false);

            float journeyProgress = tardisClientData.getJourneyProgress() / 100.0f;

           if(!tardisClientData.isFlying()) {
               // Render player coordinates at the top-right corner
               poseStack.pushPose();
               poseStack.translate(screenWidth - coordsWidth - 10, 10, 0); // Adjust position
               guiGraphics.fill(-2, -3, coordsWidth + 2, mc.font.lineHeight + 2, 0x88000000); // Black background
               guiGraphics.drawString(mc.font, coordsMessage.getString(), 0, 0, 0xFFFFFF, false); // White text
               poseStack.popPose();
           }

            // Render journey progress bar
            if (tardisClientData.isFlying())
                renderJourneyProgressBar(guiGraphics, journeyProgress);

            poseStack.popPose();
        });
    }


    private static void renderPlayerHeads(PlayerInfo player, GuiGraphics guiGraphics, Minecraft mc, int x, int y) {
        // Render player's face and name
        if (player == null) return;

        // Render the player's face
        int faceSize = 10;
        RenderHelper.renderPlayerFace(guiGraphics, x, y, faceSize, player.getProfile().getId());
        // Render the player's name
        guiGraphics.drawString(mc.font, player.getProfile().getName(), x + faceSize + 5, y + 1, 0xFFFFFF, false); // White text
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