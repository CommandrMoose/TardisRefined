package whocraft.tardis_refined.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRVillagerProfession;
import whocraft.tardis_refined.villager.FlyTardisAtPOI;
import whocraft.tardis_refined.villager.VillagerDuck;

import static whocraft.tardis_refined.common.util.MiscHelper.convertTicksToRealTime;

@Mixin(Villager.class)
public class VillagerMixin implements VillagerDuck {

    @Unique
    private static final EntityDataAccessor<Integer> PILOT_TICKS = SynchedEntityData.defineId(Villager.class, EntityDataSerializers.INT);

    @Inject(method = "defineSynchedData()V", at = @At("HEAD"), cancellable = true)
    protected void defineSynchedData(CallbackInfo ci) {
        Villager villager = (Villager) (Object) this;
        villager.getEntityData().define(PILOT_TICKS, 0);
    }

    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), cancellable = true)
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putInt("tardis_refined_pilot_ticks", tardisRefined$getPilotingTicks());
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), cancellable = true)
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        tardisRefined$setPilotingTicks(compoundTag.getInt("tardis_refined_pilot_ticks"));
    }


    @Inject(method = "mobInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        Villager villager = (Villager) (Object) this;
        if (villager.getVillagerData().getProfession() == TRVillagerProfession.PILOT.get()) {

            // Handle Emerald
            if(player.getItemInHand(interactionHand).is(Items.EMERALD)){
                tardisRefined$setPilotingTicks(tardisRefined$getPilotingTicks() + FlyTardisAtPOI.EMERALD_FLIGHT_TIME);
                player.getItemInHand(interactionHand).shrink(1);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }

            if(tardisRefined$getPilotingTicks() > 0){
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.PILOT_TIME, convertTicksToRealTime(tardisRefined$getPilotingTicks())), true);
            } else {
                villager.setUnhappy();
                PlayerUtil.sendMessage(player, Component.translatable(ModMessages.DEMANDS_PAYMENT), true);
            }
        }
    }

    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        Villager villager = (Villager) (Object) this;

        if (villager.level() instanceof ServerLevel serverLevel) {
            TardisLevelOperator.get(serverLevel).ifPresent(tardisLevelOperator -> {

                TardisPilotingManager pilotingManager = tardisLevelOperator.getPilotingManager();

                if (pilotingManager.isCrashing()) {
                    if (!villager.getBrain().isActive(Activity.PANIC)) {
                        villager.getBrain().setActiveActivityIfPossible(Activity.PANIC);
                        return;
                    }
                }

                if (pilotingManager.isLanding()) {
                    if (!villager.getBrain().isActive(Activity.CELEBRATE)) {
                        villager.getBrain().setActiveActivityIfPossible(Activity.CELEBRATE);
                        return;
                    }
                }


                if (pilotingManager.isInFlight()) {
                    if (!villager.getBrain().isActive(Activity.WORK)) {
                        villager.getBrain().setDefaultActivity(Activity.WORK);
                        villager.getBrain().setActiveActivityIfPossible(Activity.WORK);
                        return;
                    }
                }

            });
        }
    }

    @Override
    public void tardisRefined$setPilotingTicks(int ticks) {
        Villager villager = (Villager) (Object) this;
        villager.getEntityData().set(PILOT_TICKS, ticks);
    }

    @Override
    public int tardisRefined$getPilotingTicks() {
        Villager villager = (Villager) (Object) this;
        return villager.getEntityData().get(PILOT_TICKS);
    }
}
