package whocraft.tardis_refined.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class PlayerUtil {


    /**
     * Sends the given message to all players on the given server.
     *
     * @param body   the message to send
     * @param server the server to send the message to
     */
    public static void globalMessage(Component body, MinecraftServer server) {
        // Return early if the server is null
        if (server == null) {
            return;
        }

        // Get the list of players on the server
        PlayerList playerList = server.getPlayerList();

        // Iterate over all players on the server
        for (ServerPlayer player : playerList.getPlayers()) {
            // Send the message to the player
            player.sendSystemMessage(body);
        }
    }

    public static void sendMessage(LivingEntity livingEntity, String message, boolean hotBar) {
        if (!(livingEntity instanceof Player player)) return;
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.translatable(message), hotBar);
        }
    }

    public static void sendMessage(LivingEntity livingEntity, Component translation, boolean hotBar) {
        if (!(livingEntity instanceof Player player)) return;
        if (!player.level().isClientSide) {
            player.displayClientMessage(translation, hotBar);
        }
    }

    /**
     * Checks if the given item is in the given hand of the given entity.
     *
     * @param hand   the hand to check
     * @param holder the entity to check the hand of
     * @param item   the item to check for
     * @return true if the given item is in the given hand of the given entity, false otherwise
     */
    public static boolean isInHand(InteractionHand hand, LivingEntity holder, Item item) {
        // Get the item that the entity is currently holding in the given hand
        ItemStack heldItem = holder.getItemInHand(hand);

        // Check if the held item is the same as the given item
        return heldItem.getItem() == item;
    }

    public static boolean isInMainHand(LivingEntity holder, Item item) {
        return isInHand(InteractionHand.MAIN_HAND, holder, item);
    }

    /**
     * Checks if player has item in offhand
     */
    public static boolean isInOffHand(LivingEntity holder, Item item) {
        return isInHand(InteractionHand.OFF_HAND, holder, item);
    }

    /**
     * Checks if player has item in either hand
     */
    public static boolean isInEitherHand(LivingEntity holder, Item item) {
        return isInMainHand(holder, item) || isInOffHand(holder, item);
    }

    // MAIN_HAND xor OFF_HAND
    public static boolean isInOneHand(LivingEntity holder, Item item) {
        boolean mainHand = (isInMainHand(holder, item) && !isInOffHand(holder, item));
        boolean offHand = (isInOffHand(holder, item) && !isInMainHand(holder, item));
        return mainHand || offHand;
    }

}