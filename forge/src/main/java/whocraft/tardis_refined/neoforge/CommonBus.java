package whocraft.tardis_refined.neoforge;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDestroyBlockEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.command.TardisRefinedCommand;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.dimension.TardisTeleportData;
import whocraft.tardis_refined.common.soundscape.hum.TardisHums;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.patterns.ConsolePatterns;
import whocraft.tardis_refined.patterns.ShellPatterns;

@Mod.EventBusSubscriber(modid = TardisRefined.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonBus {


    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            TardisTeleportData.tick();
        }

    }

    @SubscribeEvent
    public static void onServerStart(ServerStartedEvent event) {
        // Load Levels
        ServerLevel world = event.getServer().getLevel(Level.OVERWORLD);
        DimensionHandler.loadLevels(world);

    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent playerLoggedOutEvent) {
        // Force End a Vortex Session
        Player player = playerLoggedOutEvent.getEntity();
        if (player instanceof ServerPlayer serverPlayer) {
            TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> {
                tardisPlayerInfo.endPlayerForInspection(serverPlayer);
            });
        }
    }

    @SubscribeEvent
    public static void onDatapack(AddReloadListenerEvent addReloadListenerEvent) {
        addReloadListenerEvent.addListener(ConsolePatterns.getReloadListener());

        addReloadListenerEvent.addListener(TardisDesktops.getReloadListener());
        addReloadListenerEvent.addListener(ShellPatterns.getReloadListener());
        addReloadListenerEvent.addListener(TardisHums.getReloadListener());

    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        TardisRefinedCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (MiscHelper.shouldStopItem(event.getEntity().level(), player, event.getPos(), player.getMainHandItem())) {
                event.getLevel().destroyBlock(event.getPos(), true);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        event.setCanceled(MiscHelper.shouldCancelBreaking(event.getPlayer().level(), event.getPlayer(), event.getPos(), event.getState()));
    }

    @SubscribeEvent
    public static void onEntityBlockBreak(LivingDestroyBlockEvent event) {
        event.setCanceled(MiscHelper.shouldCancelBreaking(event.getEntity().level(), event.getEntity(), event.getPos(), event.getState()));
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer != null)
                TardisHelper.handlePlayerJoinWorldEvents(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer != null)
                TardisHelper.handlePlayerJoinWorldEvents(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (serverPlayer != null)
                TardisHelper.handlePlayerJoinWorldEvents(serverPlayer);
        }
    }

}