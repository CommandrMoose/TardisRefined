
package whocraft.tardis_refined.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Transformation;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.GravityUtil;
import whocraft.tardis_refined.constants.ModMessages;

public class GravityOverlay {

    private static boolean isInShaft = false;

    private static void checkOverlay(Player player){
        isInShaft = GravityUtil.isInGravityShaft(player);
    }

    public static void renderOverlay(PoseStack poseStack) {
        Minecraft mc = Minecraft.getInstance();
        Font fontRenderer = mc.font;
        LocalPlayer player = mc.player;

        if(player.tickCount % 100 == 0){
            checkOverlay(player);
        }

        if (isInShaft && !mc.options.renderDebug) {
            poseStack.pushPose();
            poseStack.scale(1.2f, 1.2f, 1.2f);


            int x = 5;
            int y = 5;

            MutableComponent ascendKey = Component.translatable(mc.options.keyJump.getDefaultKey().getName());
            MutableComponent descendKey = Component.translatable(mc.options.keyShift.getDefaultKey().getName());

            MultiBufferSource.BufferSource renderImpl = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            fontRenderer.drawInBatch(Component.translatable(ModMessages.ASCEND_KEY, ascendKey).getString(), x, y, ChatFormatting.WHITE.getColor(), true, Transformation.identity().getMatrix(), renderImpl, Font.DisplayMode.NORMAL, 0, 15728880);
            fontRenderer.drawInBatch(Component.translatable(Component.translatable(ModMessages.DESCEND_KEY, descendKey).getString()), x, y + fontRenderer.lineHeight, ChatFormatting.WHITE.getColor(), true, Transformation.identity().getMatrix(), renderImpl, Font.DisplayMode.NORMAL, 0, 15728880);
            renderImpl.endBatch();

            poseStack.popPose();
        }
    }

}