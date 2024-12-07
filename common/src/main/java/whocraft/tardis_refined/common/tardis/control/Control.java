package whocraft.tardis_refined.common.tardis.control;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.api.event.EventResult;
import whocraft.tardis_refined.api.event.TardisCommonEvents;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.patterns.sound.ConfiguredSound;
import whocraft.tardis_refined.patterns.ConsolePattern;
import whocraft.tardis_refined.patterns.ConsolePatterns;

public abstract class Control {
    protected final ResourceLocation id;
    protected final String langId;
    /**
     * Determines if this Control should be used for the FlightDance. This can be expanded to be used for other purposes in the future.
     */
    private boolean isCriticalForTardisOperation = false;
    private boolean canBeUsedPostCrash = false;
    private ConfiguredSound successSound = new ConfiguredSound(SoundEvents.ARROW_HIT_PLAYER);
    private ConfiguredSound failSound = new ConfiguredSound(SoundEvents.ITEM_BREAK);

    protected Control(ResourceLocation id, String langId, boolean isCriticalForTardisOperation) {
        this.id = id;
        this.langId = langId;
        this.isCriticalForTardisOperation = isCriticalForTardisOperation;
    }

    protected Control(ResourceLocation id, String langId) {
        this(id, langId, false);
    }

    protected Control(ResourceLocation id, boolean isCriticalForTardisOperation) {
        this(id, Util.makeDescriptionId("control", id), isCriticalForTardisOperation);
    }
    protected Control(ResourceLocation id) {
        this(id, false);
    }

    public abstract boolean onLeftClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player);

    public abstract boolean onRightClick(TardisLevelOperator operator, ConsoleTheme theme, ControlEntity controlEntity, Player player);

    /**
     * The sound event to be played when the control fails to activate
     */
    public ConfiguredSound getFailSound(TardisLevelOperator operator, ConsoleTheme theme, boolean leftClick) {
        return this.failSound;
    }

    /**
     * Directly set the fail sound event to be used in special scenarios
     *
     * @param failSound
     */
    public void setFailSound(ConfiguredSound failSound) {
        this.failSound = failSound;
    }

    public ConfiguredSound getSuccessSound(TardisLevelOperator operator, ConsoleTheme theme, boolean leftClick) {
        ConsolePattern pattern = ConsolePatterns.DEFAULT;

        GlobalConsoleBlockEntity consoleBlockEntity = operator.getPilotingManager().getCurrentConsole();
        if (consoleBlockEntity != null) {
            pattern = consoleBlockEntity.pattern();
        }

        var pitchedSound = (leftClick) ? pattern.soundProfile().get().getGeneric().leftClick() : pattern.soundProfile().get().getGeneric().rightClick();
        if (pitchedSound != null) {
            this.successSound = pitchedSound;
        }
        return this.successSound;
    }

    /**
     * Directly set the success sound event to be used in special scenario
     *
     * @param successSound
     */
    public void setSuccessSound(ConfiguredSound successSound) {
        this.successSound = successSound;
    }

    public void playControlConfiguredSound(TardisLevelOperator operator, ControlEntity controlEntity, ConfiguredSound pitchedSound, SoundSource source, float volume, float pitch, boolean ignorePitch) {
        controlEntity.level().playSound(null, controlEntity.blockPosition(), pitchedSound.getSoundEvent(), source, volume, ignorePitch ? pitch : pitchedSound.getPitch());
    }

    public void playControlConfiguredSound(TardisLevelOperator operator, ControlEntity controlEntity, ConfiguredSound pitchedSound, float pitch) {
        this.playControlConfiguredSound(operator, controlEntity, pitchedSound, SoundSource.BLOCKS, pitchedSound.getVolume(), pitch, true);
    }

    public void playControlConfiguredSound(TardisLevelOperator operator, ControlEntity controlEntity, ConfiguredSound pitchedSound) {
        this.playControlConfiguredSound(operator, controlEntity, pitchedSound, SoundSource.BLOCKS, 1F, 1F, false);
    }

    public boolean canUseControl(TardisLevelOperator tardisLevelOperator, Control control, ControlEntity controlEntity) {
        boolean isDeskopWaiting = controlEntity.isDesktopWaitingToGenerate(tardisLevelOperator);
        return !isDeskopWaiting && TardisCommonEvents.PLAYER_CONTROL_INTERACT.invoker().canControlBeUsed(tardisLevelOperator, control, controlEntity) == EventResult.pass();
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public String getTranslationKey() {
        return this.langId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Control control = (Control) o;

        return this.getId().equals(control.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    /**
     * Determines if the Control will have a custom name that will display other information. E.g. Monitor control shows flight progress
     */
    public boolean hasCustomName() {
        return false;
    }

    public Component getCustomControlName(TardisLevelOperator operator, ControlEntity entity, ControlSpecification controlSpecification) {
        return Component.translatable(controlSpecification.control().getTranslationKey());
    }

    public Control setCanBeUsedPostCrash(boolean canBeUsedPostCrash) {
        this.canBeUsedPostCrash = canBeUsedPostCrash;
        return this;
    }

    public boolean canBeUsedPostCrash() {
        return canBeUsedPostCrash;
    }

    /**
     * Determines if this Control should be used for the FlightDance.
     * <br> This can be expanded to be used for other purposes in the future.
     *
     * @return true if shouldn't be included in FlightDance, false if allowed to be included in FlightDance.
     */
    public boolean isCriticalForTardisOperation() {
        return this.isCriticalForTardisOperation;
    }

    public Control setCriticalForTardisOperation(boolean criticalForTardisOperation) {
        this.isCriticalForTardisOperation = criticalForTardisOperation;
        return this;
    }
}
