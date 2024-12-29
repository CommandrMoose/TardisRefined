package whocraft.tardis_refined.common.capability.player;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.network.messages.player.C2SExitTardisView;
import whocraft.tardis_refined.common.network.messages.player.S2CResetPostShellView;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncTardisPlayerView;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.common.util.TRTeleporter;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TardisPlayerInfo implements TardisPilot {

    private Player player;
    private UUID viewedTardis;
    private TardisNavLocation playerPreviousPos = TardisNavLocation.ORIGIN;
    private float playerPreviousRot = 0;
    private float playerPreviousYaw = 0;
    private boolean renderVortex = false;

    public TardisPlayerInfo(Player player) {
        this.player = player;
    }

    @ExpectPlatform
    public static Optional<TardisPlayerInfo> get(LivingEntity player) {
        throw new AssertionError();
    }

    public static void updateTardisForAllPlayers(TardisLevelOperator tardisLevelOperator, TardisNavLocation tardisNavLocation, boolean timeVortex) {
        if (Platform.getServer() == null) return;
        Platform.getServer().getPlayerList().getPlayers().forEach(serverPlayer -> {
            TardisPlayerInfo.get(serverPlayer).ifPresent(tardisPlayerInfo -> {
                if (tardisPlayerInfo.isViewingTardis()) {
                    if (Objects.equals(tardisPlayerInfo.getViewedTardis().toString(), UUID.fromString(tardisLevelOperator.getLevelKey().location().getPath()).toString())) {
                        tardisPlayerInfo.startShellView(serverPlayer, tardisLevelOperator, tardisNavLocation, timeVortex);
                    }
                }
            });
        });
    }

    public float getPlayerPreviousRot() {
        return playerPreviousRot;
    }

    public void setPlayerPreviousRot(float playerPreviousRot) {
        this.playerPreviousRot = playerPreviousRot;
    }

    public float getPlayerPreviousYaw() {
        return playerPreviousYaw;
    }

    public void setPlayerPreviousYaw(float playerPreviousYaw) {
        this.playerPreviousYaw = playerPreviousYaw;
    }

    @Override
    public void updatePlayerAbilities(ServerPlayer player, Abilities abilities, boolean isWatcher) {

        if (isWatcher) {
            abilities.mayfly = false;
            abilities.instabuild = false;
            abilities.mayBuild = false;
            abilities.invulnerable = true;
            abilities.flying = true;
            player.setNoGravity(true);
        } else {
            player.gameMode.getGameModeForPlayer().updatePlayerAbilities(abilities);
            player.setNoGravity(false);
        }
    }

    @Override
    public void startShellView(ServerPlayer serverPlayer, TardisLevelOperator tardisLevelOperator, TardisNavLocation spectateTarget, boolean timeVortex) {

        // Set the player's viewed TARDIS UUID
        UUID uuid = UUID.fromString(tardisLevelOperator.getLevelKey().location().getPath());

        if (!isViewingTardis()) {
            setPlayerPreviousPos(new TardisNavLocation(player.blockPosition(), Direction.NORTH, tardisLevelOperator.getLevelKey()));
            setPlayerPreviousRot(player.getYHeadRot());
            setPlayerPreviousYaw(player.getXRot());
        }

        setViewedTardis(uuid);

        if (spectateTarget != null) {

            BlockPos spectatePos = spectateTarget.getPosition();

            if (spectateTarget.getPosition().distManhattan(new Vec3i((int) player.position().x, (int) player.position().y, (int) player.position().z)) > 3 || !player.level().dimension().location().toString().equals(spectateTarget.getDimensionKey().location().toString())) {
                TRTeleporter.simpleTeleport(player, spectateTarget.getLevel(), spectatePos.getX() + 0.5, spectatePos.getY(), spectatePos.getZ() + 0.5, playerPreviousRot, playerPreviousYaw);
            }
            updatePlayerAbilities(serverPlayer, serverPlayer.getAbilities(), true);
            setRenderVortex(timeVortex);
            serverPlayer.onUpdateAbilities();
            syncToClients(null);
        }


    }

    public TardisNavLocation getPlayerPreviousPos() {
        return playerPreviousPos;
    }

    public void setPlayerPreviousPos(TardisNavLocation playerPreviousPos) {
        this.playerPreviousPos = playerPreviousPos;
    }

    public static void onExitKeybindPressed() {
        new C2SExitTardisView().send();
    }

    @Override
    @Nullable
    public UUID getViewedTardis() {
        return viewedTardis;
    }

    @Override
    public void setViewedTardis(@Nullable UUID uuid) {
        this.viewedTardis = uuid;
    }

    @Override
    public boolean isViewingTardis() {
        return viewedTardis != null;
    }

    @Override
    public CompoundTag saveData() {
        CompoundTag tag = new CompoundTag();
        if (viewedTardis != null) {
            tag.putUUID("ViewedTardis", viewedTardis);
        }

        CompoundTag playerPos = playerPreviousPos.serialise();
        tag.put("TardisPlayerPos", playerPos);

        tag.putBoolean("RenderVortex", renderVortex);
        tag.putFloat("PlayerPreviousRot", playerPreviousRot);
        tag.putFloat("PlayerPreviousYaw", playerPreviousYaw);

        return tag;
    }

    public boolean isRenderVortex() {
        return renderVortex;
    }

    public void setRenderVortex(boolean renderVortex) {
        this.renderVortex = renderVortex;
        syncToClients(null);
    }

    @Override
    public void loadData(CompoundTag tag) {

        playerPreviousRot = tag.getFloat("PlayerPreviousRot");
        playerPreviousYaw = tag.getFloat("PlayerPreviousYaw");

        if (tag.contains("TardisPlayerPos")) {
            playerPreviousPos = TardisNavLocation.deserialize(tag.getCompound("TardisPlayerPos"));
        }

        if (tag.hasUUID("ViewedTardis")) {
            this.viewedTardis = tag.getUUID("ViewedTardis");
        } else {
            this.viewedTardis = null;
        }

        renderVortex = tag.getBoolean("RenderVortex");

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void endShellView(ServerPlayer serverPlayer) {
        if (!isViewingTardis()) return;
        BlockPos targetPosition = getPlayerPreviousPos().getPosition();

        TRTeleporter.simpleTeleport(serverPlayer, Platform.getServer().getLevel(getPlayerPreviousPos().getDimensionKey()), targetPosition.getX() + 0.5, targetPosition.getY(), targetPosition.getZ() + 0.5, playerPreviousYaw, playerPreviousRot);
        updatePlayerAbilities(serverPlayer, serverPlayer.getAbilities(), false);
        serverPlayer.onUpdateAbilities();
        new S2CResetPostShellView().send(serverPlayer);

        setPlayerPreviousPos(TardisNavLocation.ORIGIN);
        setRenderVortex(false);
        // Clear the viewed TARDIS UUID
        setViewedTardis(null);

        syncToClients(null);

    }

    @Override
    public void syncToClients(@Nullable ServerPlayer serverPlayerEntity) {
        if (player != null && player.level().isClientSide)
            throw new IllegalStateException("Don't sync client -> server");

        CompoundTag nbt = saveData();

        S2CSyncTardisPlayerView message = new S2CSyncTardisPlayerView(this.player.getId(), nbt);
        if (serverPlayerEntity == null) {
            message.sendToAll();
        } else {
            message.send(serverPlayerEntity);
        }
    }

    @Override
    public void tick(TardisLevelOperator tardisLevelOperator, ServerPlayer serverPlayerEntity) {
        TardisPilotingManager pilotManger = tardisLevelOperator.getPilotingManager();
        if (tardisLevelOperator.getLevelKey() == getPlayerPreviousPos().getDimensionKey()) {
            boolean showVortex = pilotManger.isLanding() || pilotManger.isTakingOff() || pilotManger.isInFlight();

            TardisNavLocation movePlayerToLocation = pilotManger.getCurrentLocation();

            if (pilotManger.isInFlight()) {
                if (pilotManger.isLanding()) {
                    movePlayerToLocation = pilotManger.getTargetLocation();
                } else if (pilotManger.isTakingOff()) {
                    movePlayerToLocation = pilotManger.getCurrentLocation();
                }
            }
            updateTardisForAllPlayers(tardisLevelOperator, movePlayerToLocation, showVortex);
            setRenderVortex(showVortex);
        }
    }
}
