package whocraft.tardis_refined.common.entity;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.common.tardis.control.ship.MonitorControl;
import whocraft.tardis_refined.common.tardis.manager.FlightDanceManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.util.ClientHelper;
import whocraft.tardis_refined.common.util.LevelHelper;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.constants.NbtConstants;
import whocraft.tardis_refined.patterns.sound.ConfiguredSound;
import whocraft.tardis_refined.registry.*;

public class ControlEntity extends Entity {

    /**
     * Flag to determine if this Control can continue to become more mis-aligned and thus lose "health".
     * <br> This name comes from a time when the terminology wasn't finalised, and a more traditional "health" system was being used.
     * <br> True - if able to keep being mis-aligned, False if cannot be further mis-aligned
     */
    private static final EntityDataAccessor<Boolean> TICKING_DOWN = SynchedEntityData.defineId(ControlEntity.class, EntityDataSerializers.BOOLEAN);
    /**
     * Flag to determine if this Control is far too mis-aligned and is considered "dead".
     * <br> This name comes from a time when the terminology wasn't finalised, and a more traditional "health" system was being used.
     */
    private static final EntityDataAccessor<Boolean> IS_DEAD = SynchedEntityData.defineId(ControlEntity.class, EntityDataSerializers.BOOLEAN);
    /**
     * Attribute to determine how far this Control is mis-aligned.
     * <br> This name comes from a time when the terminology wasn't finalised, and a more traditional "health" system was being used.
     */
    private static final EntityDataAccessor<Integer> CONTROL_HEALTH = SynchedEntityData.defineId(ControlEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SHOW_PARTICLE = SynchedEntityData.defineId(ControlEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SIZE_WIDTH = SynchedEntityData.defineId(ControlEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> SIZE_HEIGHT = SynchedEntityData.defineId(ControlEntity.class, EntityDataSerializers.FLOAT);
    /**
     * The total amount of control alignment health points before a control will start causing the Tardis to crash.
     * <br> This name comes from a time when the terminology wasn't finalised, and a more traditional "health" system was being used.
     */
    private int totalControlHealth = 10;
    private ControlSpecification controlSpecification;
    private ConsoleTheme consoleTheme;
    private BlockPos consoleBlockPos;
    private FlightDanceManager flightDanceManager;
    private Vector3f offset;
    public ControlEntity(EntityType<?> entityTypeIn, Level level) {
        super(entityTypeIn, level);
    }

    public ControlEntity(Level level) {
        super(TREntityRegistry.CONTROL_ENTITY.get(), level);
    }


    public GlobalConsoleBlockEntity getConsoleBlockEntity() {

        if (consoleBlockPos != null) {
            if (level().getBlockEntity(consoleBlockPos) instanceof GlobalConsoleBlockEntity consoleBlockEntity) {
                return consoleBlockEntity;
            }
        }

        return null;
    }

    public int getControlHealth() {
        return entityData.get(CONTROL_HEALTH);
    }

    public void assignControlData(ConsoleTheme theme, ControlSpecification specification, BlockPos consoleBlockPos) {
        this.consoleBlockPos = consoleBlockPos;
        this.controlSpecification = specification;
        this.consoleTheme = theme;
        if (this.controlSpecification != null) {
            float width = controlSpecification.size().width;
            float height = controlSpecification.size().height;
            this.setSizeData(width, height);
            this.setCustomName(Component.translatable(controlSpecification.control().getTranslationKey()));
        }
    }

    /**
     * Sets the Entity size to an EntityDataAccessor which gets synced to the client next time it updates
     */
    protected void setSizeData(float width, float height) {
        this.getEntityData().set(SIZE_WIDTH, width);
        this.getEntityData().set(SIZE_HEIGHT, height);
    }

    /**
     * Sets the Entity Size and makes an immediate size update
     */
    public void setSizeAndUpdate(float width, float height) {
        this.setSizeData(width, height);
        this.refreshDimensions();
    }

    public ControlSpecification controlSpecification() {
        return this.controlSpecification;
    }

    public ConsoleTheme consoleTheme() {
        return this.consoleTheme;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (this.getEntityData().hasItem(SIZE_WIDTH) && this.getEntityData().hasItem(SIZE_HEIGHT)) {
            return EntityDimensions.scalable(this.getEntityData().get(SIZE_WIDTH), this.getEntityData().get(SIZE_HEIGHT));
        }
        return super.getDimensions(pose);
    }

    @Override
    public Component getName() {

        TardisClientData tardisClientData = TardisClientData.getInstance(level().dimension());
        if (tardisClientData.isInRecovery()) {
            int cooldownTicks = tardisClientData.getRecoveryTicks();
            int maxCooldownTicks = 12000; // 10 minutes in ticks
            int percentage = (int) ((cooldownTicks / (float) maxCooldownTicks) * 100);
            return Component.translatable(ModMessages.RECOVERY_PROGRESS, percentage + "%");
        }

        if (this.controlSpecification == null) {
            return super.getName();
        }

        return Component.translatable(this.controlSpecification.control().getTranslationKey());
    }


    /**
     * Tell the Tardis that the control is currently continuing to be misaligned
     *
     * @param manager
     */
    public void setTickingDown(FlightDanceManager manager) {

        if (this.getEntityData().get(IS_DEAD)) {
            return;
        }

        this.entityData.set(TICKING_DOWN, true);
        this.flightDanceManager = manager;
        this.level().playSound(null, this.blockPosition(), SoundEvents.ARROW_HIT, SoundSource.BLOCKS, 0.5f, 2f);

        this.setCustomName(Component.translatable("!"));
    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(SHOW_PARTICLE, false);
        getEntityData().define(TICKING_DOWN, false);
        getEntityData().define(IS_DEAD, false);
        getEntityData().define(SIZE_WIDTH, 1F);
        getEntityData().define(SIZE_HEIGHT, 1F);
        getEntityData().define(CONTROL_HEALTH, 10);

    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        this.setSizeAndUpdate(this.getEntityData().get(SIZE_WIDTH), this.getEntityData().get(SIZE_HEIGHT));
    }


    @Override
    public boolean save(CompoundTag compound) {
        if (consoleBlockPos != null) {
            compound.put(NbtConstants.CONSOLE_POS, NbtUtils.writeBlockPos(this.consoleBlockPos));
        }
        compound.putFloat(NbtConstants.CONTROL_SIZE_WIDTH, this.getEntityData().get(SIZE_WIDTH));
        compound.putFloat(NbtConstants.CONTROL_SIZE_HEIGHT, this.getEntityData().get(SIZE_HEIGHT));
        return super.save(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        var consolePos = compound.getCompound(NbtConstants.CONSOLE_POS);
        this.consoleBlockPos = NbtUtils.readBlockPos(consolePos);

        float width = compound.getFloat(NbtConstants.CONTROL_SIZE_WIDTH);
        float height = compound.getFloat(NbtConstants.CONTROL_SIZE_HEIGHT);

        this.setSizeData(width, height);

        if (level() instanceof ServerLevel serverLevel) {
            TardisLevelOperator.get(serverLevel).ifPresent((operator) -> {
                this.flightDanceManager = operator.getFlightDanceManager();
            });
        }


    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        if (consoleBlockPos != null) {
            compound.put(NbtConstants.CONSOLE_POS, NbtUtils.writeBlockPos(this.consoleBlockPos));
        }

        compound.putFloat(NbtConstants.CONTROL_SIZE_WIDTH, this.getEntityData().get(SIZE_WIDTH));
        compound.putFloat(NbtConstants.CONTROL_SIZE_HEIGHT, this.getEntityData().get(SIZE_HEIGHT));

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return MiscHelper.spawnPacket(this);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (damageSource.getDirectEntity() instanceof Player player) { //Using getDirectEntity can allow for players to indirectly interact with controls, such as through primed TNT
            if (this.level() instanceof ServerLevel serverLevel) {
                if (!player.level().isClientSide()) {
                    if (entityData.get(IS_DEAD)) {
                        return false;
                    }
                    if (this.entityData.get(TICKING_DOWN)) {

                        ItemStack itemStack = player.getMainHandItem();

                        if (itemStack.is(TRItemRegistry.MALLET.get()) && !player.getCooldowns().isOnCooldown(TRItemRegistry.MALLET.get())) {
                            player.getCooldowns().addCooldown(TRItemRegistry.MALLET.get(), 600);
                            playSound(TRSoundRegistry.MALLET.get());
                            itemStack.hurtAndBreak(15, player, (livingEntityx) -> {
                                livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                            });

                            getConsoleBlockEntity().getControlEntityList().forEach(controlEntity -> {
                                controlEntity.realignControl();
                                BlockPos blockPos = controlEntity.blockPosition();
                                serverLevel.sendParticles(ParticleTypes.ENCHANT, blockPos.getX(), blockPos.getY() + 1.0, blockPos.getZ(), 120, 2.0, 1.0, 2.0, 0.005);
                            });
                        } else {
                            this.realignControl();
                        }
                        //Return early here because we want the player to re-align the control, but not actually activate the control's original function.
                        //e.g. If Randomiser control is re-aligned we shouldn't actually tell the Tardis to randomise its coordinates.
                        return true;
                    } else {
                        if (handleLeftClick(player, serverLevel)) {
                            return true;
                        }
                    }
                }
            }
        }
        return super.hurt(damageSource, f);
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand interactionHand) {
        if (this.level() instanceof ServerLevel serverLevel) {
            if (!player.level().isClientSide()) {
                if (player.getMainHandItem().getItem() == Items.COMMAND_BLOCK_MINECART) {
                    this.handleControlSizeAndPositionAdjustment(player);
                    return InteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
                }

                if (entityData.get(IS_DEAD)) {
                    return InteractionResult.FAIL;
                }

                if (this.entityData.get(TICKING_DOWN)) {
                    this.realignControl();
                    //Return an InteractionResult here because we want the player to re-align the control, but not actually activate the control's original function.
                    //e.g. If Randomiser control is re-aligned we shouldn't actually tell the Tardis to randomise its coordinates.
                    return InteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
                } else {
                    if (this.handleRightClick(player, serverLevel, interactionHand)) {
                        //Return an InteractionResult here because we want to tell the player that the control was correctly interacted with.
                        return InteractionResult.sidedSuccess(false); //Use InteractionResult.sidedSuccess(false) for non-client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
                    }
                }
            }
        }

        return InteractionResult.sidedSuccess(true); //Use InteractionResult.sidedSuccess(true) for client side. Stops hand swinging twice. We don't want to use InteractionResult.SUCCESS because the client calls SUCCESS, so the server side calling it too sends the hand swinging packet twice.
    }

    public boolean isTickingDown() {
        return getEntityData().get(TICKING_DOWN);
    }

    /**
     * Restores the control alignment points to a higher value so that it won't cause the Tardis to crash
     */
    public void realignControl() {
        int currentHealth = this.entityData.get(CONTROL_HEALTH);
        int nextHealth = currentHealth + 2;

        if (nextHealth >= this.totalControlHealth) {
            this.entityData.set(TICKING_DOWN, false);
            this.entityData.set(CONTROL_HEALTH, totalControlHealth);
            this.setCustomName(Component.translatable(controlSpecification.control().getTranslationKey()));

        } else {

            UpgradeHandler upgradeHandler = this.flightDanceManager.getOperator().getUpgradeHandler();
            upgradeHandler.addUpgradeXP(5);
            this.level().addParticle(ParticleTypes.HEART, consoleBlockPos.getX() + 0.5, consoleBlockPos.getY() + 2, consoleBlockPos.getZ() + 0.5, 0, 0.5, 0);

            this.entityData.set(CONTROL_HEALTH, nextHealth);
        }
    }

    @Override
    public void tick() {
        if (level() instanceof ServerLevel serverLevel) {

            if (this.flightDanceManager == null) {

                TardisLevelOperator.get(serverLevel).ifPresent((operator) -> {
                    this.flightDanceManager = operator.getFlightDanceManager();
                });
            }

            if (this.controlSpecification == null) {
                if (this.consoleBlockPos != null) {
                    if (serverLevel.getBlockEntity(this.consoleBlockPos) instanceof GlobalConsoleBlockEntity globalConsoleBlockEntity) {

                        globalConsoleBlockEntity.markDirty();
                    }
                    discard();
                }

            } else {
                onServerTick(serverLevel);
            }
        } else {
            onClientTick(this.level());
        }

        if (level().dimensionTypeId() != TRDimensionTypes.TARDIS) {
            discard();
        }

    }

    private void onServerTick(ServerLevel serverLevel) {

        boolean isTickingDown = getEntityData().get(TICKING_DOWN);
        boolean isDead = getEntityData().get(IS_DEAD);

        if (this.flightDanceManager != null) {
            TardisLevelOperator operator = this.flightDanceManager.getOperator();

            if (this.controlSpecification.control().hasCustomName()) {
                this.setCustomName(this.controlSpecification.control().getCustomControlName(operator, this, this.controlSpecification));
            }
        }


        if (!isDead && isTickingDown && serverLevel.getGameTime() % (5 * 20) == 0) {
            int controlHealth = getEntityData().get(CONTROL_HEALTH) - 1;

            getEntityData().set(CONTROL_HEALTH, controlHealth);

            if (controlHealth == 0) {
                this.onControlDead();
            }
        }

    }

    public void onControlDead() {

        this.entityData.set(TICKING_DOWN, false);
        this.entityData.set(IS_DEAD, true);

        if (this.flightDanceManager != null) {
            this.flightDanceManager.updateDamageList();
        }
    }

    private void onClientTick(Level level) {

        if (getEntityData().get(CONTROL_HEALTH) <= 5) {
            if (level.random.nextInt(25) == 0) {
                ClientHelper.playParticle((ClientLevel) level, ParticleTypes.LAVA, this.position(), -0.5 + level.random.nextFloat(), 0.05D, -0.5 + level.random.nextFloat());

                level.playLocalSound(BlockPos.containing(this.position()), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.25f, level.getRandom().nextFloat() + 1f, false);
            }
        }
    }


    private void handleControlSizeAndPositionAdjustment(Player player) {
        float width = this.getEntityData().get(SIZE_WIDTH);
        float height = this.getEntityData().get(SIZE_HEIGHT);
        float incrementAmount = 0.05F;
        float posIncrementAmount = 0.025F;
        Item offhandItem = player.getOffhandItem().getItem();

        if (offhandItem == Items.REDSTONE) { //Print position output to console
            if (this.controlSpecification != null)
                TardisRefined.LOGGER.info("Control Info for: " + this.controlSpecification.control().getId().getPath());
            if (this.consoleBlockPos != null) {
                Vec3 centre = LevelHelper.centerPos(this.consoleBlockPos, true);
                double x = this.position().x() - centre.x;
                double y = this.position().y() - centre.y;
                double z = this.position().z() - centre.z;
                TardisRefined.LOGGER.info("Offset: " + x + "F, " + y + "F, " + z + "F");
            }
            float finalWidth = this.getEntityData().get(SIZE_WIDTH);
            float finalHeight = this.getEntityData().get(SIZE_HEIGHT);
            TardisRefined.LOGGER.info("Size (Width, Height): " + finalWidth + "F, " + finalHeight + "F");
        } else {
            if (offhandItem == Items.EMERALD) { //Adjust X
                this.setPos(this.position().add(player.isShiftKeyDown() ? -posIncrementAmount : posIncrementAmount, 0, 0));
            }
            if (offhandItem == Items.DIAMOND) { //Adjust Y
                this.setPos(this.position().add(0, player.isShiftKeyDown() ? -posIncrementAmount : posIncrementAmount, 0));
            }
            if (offhandItem == Items.GOLD_INGOT) { //Adjust Z
                this.setPos(this.position().add(0, 0, player.isShiftKeyDown() ? posIncrementAmount : -posIncrementAmount));
            }
            if (offhandItem == Items.IRON_INGOT) { //Adjust Size Width
                float newWidth = player.isShiftKeyDown() ? width - incrementAmount : width + incrementAmount;
                this.setSizeAndUpdate(newWidth, height);
            }
            if (offhandItem == Items.COPPER_INGOT) { //Adjust Size Height
                float newHeight = player.isShiftKeyDown() ? height - incrementAmount : height + incrementAmount;
                this.setSizeAndUpdate(width, newHeight);
            }
        }
    }

    public boolean isDesktopWaitingToGenerate(TardisLevelOperator operator) {
        if (!(this.controlSpecification.control() instanceof MonitorControl)) {
            if (operator.getInteriorManager().isWaitingToGenerate()) {
                operator.getLevel().playSound(null, this.blockPosition(), SoundEvents.NOTE_BLOCK_BIT.value(), SoundSource.BLOCKS, 100F, (float) (0.1 + (level().getRandom().nextFloat() * 0.5)));
                return true;
            }
        }
        return false;
    }

    private boolean handleLeftClick(Player player, ServerLevel serverLevel) {
        if (!TardisLevelOperator.get(serverLevel).isPresent()) {
            return false;
        } else {
            TardisLevelOperator cap = TardisLevelOperator.get(serverLevel).get();

            if (cap.getPilotingManager().getCurrentConsole() == null || cap.getPilotingManager().getCurrentConsole() != getConsoleBlockEntity()) {
                cap.getPilotingManager().setCurrentConsole(this.getConsoleBlockEntity());
            }

            if (!controlSpecification.control().canUseControl(cap, controlSpecification.control(), this)) {
                return false;
            }

            whocraft.tardis_refined.common.tardis.control.Control control = this.controlSpecification.control();

            boolean successfulUse = control.onLeftClick(cap, this.consoleTheme, this, player);
            ConfiguredSound playedSound = successfulUse ? control.getSuccessSound(cap, this.consoleTheme, true) : control.getFailSound(cap, this.consoleTheme, true);
            control.playControlConfiguredSound(cap, this, playedSound);
            return successfulUse;
        }
    }

    private boolean handleRightClick(Player player, ServerLevel serverLevel, InteractionHand interactionHand) {
        if (!TardisLevelOperator.get(serverLevel).isPresent()) {
            return false;
        } else {
            TardisLevelOperator cap = TardisLevelOperator.get(serverLevel).get();
            TardisPilotingManager pilotingManager = cap.getPilotingManager();

            if (pilotingManager.getCurrentConsole() == null || pilotingManager.getCurrentConsole() != getConsoleBlockEntity()) {
                pilotingManager.setCurrentConsole(getConsoleBlockEntity());
            }

            if (!cap.getPilotingManager().canUseControls() && !controlSpecification.control().canBeUsedPostCrash()) {
                if (player.isCreative()) {
                    serverLevel.playSound(null, this.blockPosition(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 100, (float) (0.1 + (serverLevel.getRandom().nextFloat() * 0.5)));
                } else {
                    DamageSource source = MiscHelper.getDamageSource(serverLevel, DamageTypes.ON_FIRE);
                    player.hurt(source, 0.1F);
                }
                return false;
            }

            if (!controlSpecification.control().canUseControl(cap, controlSpecification.control(), this)) {
                return false;
            }

            whocraft.tardis_refined.common.tardis.control.Control control = this.controlSpecification.control();
            boolean successfulUse = control.onRightClick(cap, consoleTheme, this, player);
            ConfiguredSound playedSound = successfulUse ? control.getSuccessSound(cap, this.consoleTheme, false) : control.getFailSound(cap, this.consoleTheme, false);
            control.playControlConfiguredSound(cap, this, playedSound);
            return successfulUse;
        }
    }


    @Override
    public boolean mayInteract(Level level, BlockPos blockPos) {
        return true;
    }

    @Override
    public boolean shouldShowName() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isAttackable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    /**
     * Gets the total amount of control alignment health points before a control will start causing the Tardis to crash
     */
    public int getTotalControlHealth() {
        return this.totalControlHealth;
    }

    public void setTotalControlHealth(int totalControlHealth) {
        this.totalControlHealth = totalControlHealth;
    }

    public void setPosForDebug(Vector3f vector3f) {
        this.offset = vector3f;
    }
}
