package whocraft.tardis_refined.fabric.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.client.TRItemColouring;
import whocraft.tardis_refined.client.TardisClientLogic;
import whocraft.tardis_refined.client.overlays.ExteriorViewOverlay;
import whocraft.tardis_refined.client.overlays.GravityOverlay;
import whocraft.tardis_refined.client.overlays.VortexOverlay;
import whocraft.tardis_refined.command.TardisRefinedCommand;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.dimension.TardisTeleportData;
import whocraft.tardis_refined.common.dimension.fabric.DimensionHandlerImpl;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortals;
import whocraft.tardis_refined.registry.TRDimensionTypes;
import whocraft.tardis_refined.registry.TRItemRegistry;
import whocraft.tardis_refined.registry.TRPointOfInterestTypes;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import static net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.START_WORLD_TICK;

public class ModEvents {

    public static void addCommonEvents() {

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> !MiscHelper.shouldCancelBreaking(world, player, pos, state));

        START_WORLD_TICK.register(world -> {
            if (world.dimensionTypeId().location() == TRDimensionTypes.TARDIS.location()) {
                TardisLevelOperator.get(world).get().tick(world);
            }
        });
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {

            // Load Levels
            ServerLevel world = server.getLevel(Level.OVERWORLD);
            DimensionHandler.loadLevels(world);

            // We call this here to make sure blocks are registered
            TRPointOfInterestTypes.registerBlockStates();

        });

        // Force End a Vortex Session
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> {
                tardisPlayerInfo.endPlayerForInspection(player);
            });
        });


        ServerTickEvents.END_SERVER_TICK.register(server -> TardisTeleportData.tick());

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            DimensionHandlerImpl.clear();

            if (ModCompatChecker.immersivePortals()) {
                ImmersivePortals.onServerStopping(server);
            }
        });


        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> TardisRefinedCommand.register(dispatcher));

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer serverPlayer = handler.getPlayer();
            TardisHelper.handlePlayerJoinWorldEvents(serverPlayer);
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if (newPlayer != null) {
                TardisHelper.handlePlayerJoinWorldEvents(newPlayer);
            }
        });

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            if (player != null) {
                TardisHelper.handlePlayerJoinWorldEvents(player);
            }
        });
    }

    public static void addClientEvents() {
        ClientTickEvents.START_CLIENT_TICK.register(TardisClientLogic::tickClientData);
        ColorProviderRegistry.ITEM.register(TRItemColouring.SCREWDRIVER_COLORS, TRItemRegistry.SCREWDRIVER.get());

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (world.isClientSide) return InteractionResult.PASS;
            AtomicBoolean stopBreak = new AtomicBoolean(false);
            TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> stopBreak.set(tardisPlayerInfo.isViewingTardis()));
            return stopBreak.get() ? InteractionResult.FAIL : InteractionResult.PASS;
        });

        Supplier<GuiGraphics> guiGraphics = () -> new GuiGraphics(Minecraft.getInstance(), Minecraft.getInstance().renderBuffers().bufferSource());
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> VortexOverlay.renderOverlay(guiGraphics.get()));
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> ExteriorViewOverlay.renderOverlay(guiGraphics.get()));
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> GravityOverlay.renderOverlay(guiGraphics.get()));
    }


}
