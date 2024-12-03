package whocraft.tardis_refined.client.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.registry.TRDimensionTypes;

/**
 * Generic implementation of LoopingSound that only occurs in a Tardis dimension type
 */
public abstract class LoopingTardisInteriorSound extends LoopingDimensionTypeSpecificSound {

    protected final boolean requiresClientData;
    private TardisClientData tardisClientData;

    public LoopingTardisInteriorSound(@NotNull SoundEvent soundEvent, SoundSource soundSource, boolean requiresClientData) {
        super(soundEvent, soundSource, TRDimensionTypes.TARDIS);
        this.requiresClientData = requiresClientData;
    }

    public LoopingTardisInteriorSound(@NotNull SoundEvent soundEvent, SoundSource soundSource) {
        this(soundEvent, soundSource, true);
    }

    public TardisClientData getTardisClientData() {
        return this.tardisClientData;
    }

    public LoopingTardisInteriorSound setTardisClientData(Level level) {
        if (level != null) {
            this.tardisClientData = TardisClientData.getInstance(level.dimension());
        }
        return this;
    }

    @Override
    public LoopingSound setLevel(Level targetLevel) {
        this.level = targetLevel;
        return this.setTardisClientData(targetLevel);
    }

    /**
     * Flag to determine if we need to consider there is a valid TardisClientData instance before playing the sound
     */
    public boolean requiresClientData() {
        return this.requiresClientData;
    }

    @Override
    public boolean canPlaySound() {
        return this.requiresClientData() ? super.canPlaySound() && this.getTardisClientData() != null : super.canPlaySound();
    }
}
