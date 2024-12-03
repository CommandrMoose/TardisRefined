package whocraft.tardis_refined.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.client.sounds.*;
import whocraft.tardis_refined.common.GravityUtil;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.hum.HumEntry;
import whocraft.tardis_refined.common.util.ClientHelper;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.List;

import static whocraft.tardis_refined.client.sounds.TRSoundInstances.VORTEX_WINDS;
import static whocraft.tardis_refined.client.TardisClientData.FOG_TICK_DELTA;
import static whocraft.tardis_refined.client.TardisClientData.MAX_FOG_TICK_DELTA;
import static whocraft.tardis_refined.common.util.TardisHelper.isInArsArea;

public class TardisClientLogic {

    @Environment(EnvType.CLIENT)
    public static void tickClientside(TardisClientData clientData) {

        if (clientData.isTakingOff()) {
            clientData.takeOffTime++;
            clientData.landingTime = 0;
            return;
        }

        if (clientData.isLanding()) {
            clientData.landingTime++;
            clientData.takeOffTime = 0;
        }

        Player player = Minecraft.getInstance().player;

        TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isViewingTardis()) {
                Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_FRONT);
            }
        });

        if (player.level().dimensionTypeId() == TRDimensionTypes.TARDIS) {
            ClientLevel tardisLevel = Minecraft.getInstance().level;
            createWorldAmbience(player);
            handleTardisLoopingSounds(clientData, player, tardisLevel);
            handleScreenShake(clientData, player);
            handleAestheticEffects(clientData, tardisLevel);
        }

        handleVortexSounds(clientData, player);

    }

    private static void handleVortexSounds(TardisClientData clientData, Player player) {
        SoundManager soundManager = Minecraft.getInstance().getSoundManager();

        TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> {
            if (!clientData.isLanding() && !clientData.isTakingOff() && tardisPlayerInfo.isRenderVortex() && !soundManager.isActive(TRSoundInstances.TARDIS_SINGLE_FLY_VORTEX)) {
                TRSoundInstances.TARDIS_SINGLE_FLY_VORTEX.restartSoundPlaying(); //Explicity tell the LoopingSound to set its volume to a non-zero value so that the SoundEngine will play it again.
                soundManager.play(TRSoundInstances.TARDIS_SINGLE_FLY_VORTEX.setPlayer(player).setLevel(Minecraft.getInstance().level));
            }
        });

        TardisPlayerInfo.get(player).ifPresent(tardisPlayerInfo -> {
            if (tardisPlayerInfo.isRenderVortex() && !soundManager.isActive(VORTEX_WINDS)) {
                TRSoundInstances.VORTEX_WINDS.restartSoundPlaying();
                soundManager.play(TRSoundInstances.VORTEX_WINDS.setPlayer(player));
            }
        });
    }

    /**
     * Updates the Tardis instance. This method is called manually from the SyncIntReactionsMessage message.
     */
    public static void update(TardisClientData tardisClientData) {
        // Check if the Tardis is not currently flying and the rotor animation is started
        if (!tardisClientData.isFlying() && tardisClientData.ROTOR_ANIMATION.isStarted()) {
            tardisClientData.ROTOR_ANIMATION.stop();
        }
        // Check if the Tardis is flying and the rotor animation is not started
        else if (tardisClientData.isFlying() && !tardisClientData.ROTOR_ANIMATION.isStarted()) {
            tardisClientData.ROTOR_ANIMATION.start(0);
        }

        // Handle landing animation
        if (tardisClientData.isLanding()) {
            if (!tardisClientData.LANDING_ANIMATION.isStarted()) {
                tardisClientData.TAKEOFF_ANIMATION.stop();
                tardisClientData.LANDING_ANIMATION.start(0);
            }
        } else if (tardisClientData.LANDING_ANIMATION.isStarted()) {
            tardisClientData.LANDING_ANIMATION.stop();
        }

        // Handle takeoff animation
        if (tardisClientData.isTakingOff()) {
            if (!tardisClientData.TAKEOFF_ANIMATION.isStarted()) {
                tardisClientData.LANDING_ANIMATION.stop();
                tardisClientData.TAKEOFF_ANIMATION.start(0);
            }
        } else if (tardisClientData.TAKEOFF_ANIMATION.isStarted()) {
            tardisClientData.TAKEOFF_ANIMATION.stop();
        }

        // Handle crashing animation
        if (tardisClientData.isCrashing()) {
            if (!tardisClientData.CRASHING_ANIMATION.isStarted()) {
                tardisClientData.ROTOR_ANIMATION.stop();
                tardisClientData.LANDING_ANIMATION.stop();
                tardisClientData.TAKEOFF_ANIMATION.stop();
                tardisClientData.CRASHING_ANIMATION.start(0);
            }
        } else if (tardisClientData.CRASHING_ANIMATION.isStarted()) {
            tardisClientData.CRASHING_ANIMATION.stop();
        }
    }


    /**
     * Called by platform-specific methods
     *
     * @param client Minecraft client
     */
    public static void tickClientData(Minecraft client) {
        // Inelegant solution, please revise
        if (client.level == null || client.isPaused()) {
            if (!TardisClientData.getAllEntries().isEmpty() && !client.isPaused()) {
                TardisClientData.clearAll();
            }
            return;
        }

        handleNonTardisLoopingSounds(client.player, client.level);

        for (TardisClientData tardisClientData : TardisClientData.getAllEntries()) {
            TardisClientLogic.tickClientside(tardisClientData);
        }
    }

    private static void createWorldAmbience(Player player) {
        if (player.tickCount % 120 == 0 && !isInArsArea(player.blockPosition())) return;
        RandomSource random = player.level().random;
        Level level = player.level();
        ClientLevel clientLevel = (ClientLevel) level;
        double originX = player.getX();
        double originY = player.getY();
        double originZ = player.getZ();
        for (int i = 0; i < 5; i++) {
            double particleX = originX + (random.nextInt(24) - random.nextInt(24));
            double particleY = originY + (random.nextInt(24) - random.nextInt(24));
            double particleZ = originZ + (random.nextInt(24) - random.nextInt(24));
            double velocityX = (random.nextDouble() - 0.5) * 0.02;
            double velocityY = (random.nextDouble() - 0.5) * 0.02;
            double velocityZ = (random.nextDouble() - 0.5) * 0.02;
            BlockPos position = new BlockPos((int) particleX, (int) particleY, (int) particleZ);
            if (isInArsArea(position)) {
                ClientHelper.playParticle(clientLevel, TRParticles.ARS_LEAVES.get(), position, velocityX, velocityY, velocityZ);
                ClientHelper.playParticle(clientLevel, ParticleTypes.END_ROD, position, velocityX, velocityY, velocityZ);
            }
        }
    }

    /**
     * Handle when to start playing looping sounds for anything that doesn't need to be played inside a Tardis dimension
     */
    private static void handleNonTardisLoopingSounds(Player player, Level targetLevel) {
        SoundManager soundManager = Minecraft.getInstance().getSoundManager();
        if (GravityUtil.isInGravityShaft(Minecraft.getInstance().player)) {
            if (!soundManager.isActive(TRSoundInstances.GRAVITY_LOOP)) {
                soundManager.play(TRSoundInstances.GRAVITY_LOOP.setPlayer(player).setLevel(targetLevel));
            }
        }
    }

    /**
     * Handle when to trigger the looping sounds to start playing. This is not a duplicate of the logic in the LoopingSound implementation. Here we are only defining when we should start or continue playing the sound
     */
    private static void handleTardisLoopingSounds(TardisClientData clientData, Player player, Level targetLevel) {

        boolean isThisTardis = clientData.getLevelKey() == targetLevel.dimension();

        SoundManager soundManager = Minecraft.getInstance().getSoundManager();


        if (isInArsArea(player.blockPosition())) {
            if (!soundManager.isActive(TRSoundInstances.ARS_HUMMING)) {
                soundManager.play(TRSoundInstances.ARS_HUMMING.setPlayer(player).setLevel(targetLevel));
            }
        }

        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {

            if (tardisPlayerInfo.isRenderVortex()) return;

            if (isThisTardis && !clientData.isTakingOff() && !clientData.isLanding() && !clientData.isCrashing() && clientData.isFlying()) {
                if (!soundManager.isActive(TRSoundInstances.TARDIS_SINGLE_FLY)) {
                    TRSoundInstances.TARDIS_SINGLE_FLY.restartSoundPlaying(); //Explicity tell the LoopingSound to set its volume to a non-zero value so that the SoundEngine will play it again.
                    soundManager.play(TRSoundInstances.TARDIS_SINGLE_FLY.setPlayer(player).setLevel(targetLevel));
                }
            }

            //Play hums, and use the dedicated HumSoundManager to stop and start sounds
            HumEntry humEntry = clientData.getHumEntry();
            if (isThisTardis && humEntry != null && !humEntry.getSoundEventId().toString().equals(HumSoundManager.getCurrentRawSound().getLocation().toString()) || !soundManager.isActive(HumSoundManager.getCurrentHumSound())) {
                HumSoundManager.playHum(SoundEvent.createVariableRangeEvent(humEntry.getSoundEventId()), player, targetLevel);
            }

            //Hum ambient sounds
            if (isThisTardis && targetLevel.getGameTime() % clientData.nextAmbientNoiseCall == 0) {
                clientData.nextAmbientNoiseCall = targetLevel.random.nextInt(400, 2400);
                List<ResourceLocation> ambientSounds = humEntry.getAmbientSounds();
                if (ambientSounds != null && !ambientSounds.isEmpty()) {
                    RandomSource randomSource = targetLevel.random;

                    ResourceLocation randomSoundLocation = ambientSounds.get(randomSource.nextInt(ambientSounds.size()));
                    SoundEvent randomSoundEvent = SoundEvent.createVariableRangeEvent(randomSoundLocation);

                    QuickSimpleSound simpleSoundInstance = new QuickSimpleSound(randomSoundEvent, SoundSource.AMBIENT);
                    simpleSoundInstance.setVolume(0.3F);

                    ClientHelper.playAmbientSound(simpleSoundInstance, randomSource, 0.3f);

                }
            }

            //Interior Voice
            if (isThisTardis && targetLevel.getGameTime() % clientData.nextVoiceAmbientCall == 0) {
                clientData.nextVoiceAmbientCall = targetLevel.random.nextInt(6000, 36000);

                RandomSource randomSource = targetLevel.random;
                ClientHelper.playAmbientSound(TRSoundInstances.INTERIOR_VOICE, randomSource, 0.3f);
            }

        });
        //Play the Tardis flight loop sound when the Tardis is in flight but not taking off or landing or crashing, to minimise sound overlap

        if (TRSoundInstances.shouldMinecraftMusicStop(soundManager)) {
            Minecraft.getInstance().getMusicManager().stopPlaying();
        }
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient() {
        Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
        TardisPlayerInfo.get(Minecraft.getInstance().player).ifPresent(tardisPlayerInfo -> {
            LocalPlayer player = Minecraft.getInstance().player;
            System.out.println(tardisPlayerInfo.getPlayerPreviousYaw());
            System.out.println(tardisPlayerInfo.getPlayerPreviousRot());
            player.setXRot(tardisPlayerInfo.getPlayerPreviousYaw());
            player.setYHeadRot(tardisPlayerInfo.getPlayerPreviousRot());
            player.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(0, 128, 0));
        });
    }


    private static void handleScreenShake(TardisClientData clientData, Player player) {
        // Responsible for screen-shake. Not sure of a better solution at this point in time.

        if (player.level().dimension() == clientData.getLevelKey()) {
            if (clientData.isCrashing()) {
                player.setXRot(player.getXRot() + (player.getRandom().nextFloat() - 0.5f) * 0.5f);
                player.setYHeadRot(player.getYHeadRot() + (player.getRandom().nextFloat() - 0.5f) * 0.5f);
            } else {
                if (clientData.isFlying()) {
                    player.setXRot(player.getXRot() + (player.getRandom().nextFloat() - 0.5f) * (clientData.getThrottleStage() * 0.1f));
                    player.setYHeadRot(player.getYHeadRot() + (player.getRandom().nextFloat() - 0.5f) * (clientData.getThrottleStage() * 0.1f));
                }
            }
        }
    }

    /**
     * Higher means more fog, lower means less fog
     *
     * @return 0 -> 1 float based off fog tick delta
     */
    public static float getFogTickDelta(BlockPos playerPosition) {
        return TardisHelper.isInArsArea(playerPosition) ? 1f : 1f - (float) FOG_TICK_DELTA / (float) MAX_FOG_TICK_DELTA;
    }

    public static void tickFog(boolean hasFuel) {
        if (!hasFuel && (FOG_TICK_DELTA <= MAX_FOG_TICK_DELTA) && (FOG_TICK_DELTA > 0)) {
            FOG_TICK_DELTA--; // Fading in the fog
            return;
        }

        if (hasFuel && (FOG_TICK_DELTA != MAX_FOG_TICK_DELTA)) {
            FOG_TICK_DELTA++; // Fading out the fog
            return;
        }
    }

    private static void handleAestheticEffects(TardisClientData clientData, Level targetLevel) {
        boolean isThisTardis = clientData.getLevelKey() == targetLevel.dimension();

        if (isThisTardis) {
            tickFog(clientData.getTardisState() < TardisLevelOperator.STATE_EYE_OF_HARMONY || clientData.getFuel() != 0);
        }

        if (isThisTardis && clientData.getTardisState() == TardisLevelOperator.STATE_EYE_OF_HARMONY) {
            double motionX = 0;
            double motionY = 0.1 + targetLevel.random.nextFloat() / 2;
            double motionZ = 0;
            Vec3 position = new Vec3((double) 1013 + 0.5 - 2 + targetLevel.random.nextInt(4), 71, (double) 55 + 0.5 - 2 + targetLevel.random.nextInt(4));
            ClientHelper.playParticle((ClientLevel) targetLevel, ParticleTypes.CLOUD, position, motionX, motionY, motionZ);
        }
    }

}
