package whocraft.tardis_refined.client.sounds;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.NotNull;

/**
 * LoopingSound implementation that only plays when in a specific {@link net.minecraft.world.level.dimension.DimensionType}
 */
public abstract class LoopingDimensionTypeSpecificSound extends LoopingSoundGeneric {

    private final ResourceKey<DimensionType> dimensionTypeKey;

    public LoopingDimensionTypeSpecificSound(@NotNull SoundEvent soundEvent, SoundSource soundSource, ResourceKey<DimensionType> dimensionTypeKey) {
        super(soundEvent, soundSource);
        this.dimensionTypeKey = dimensionTypeKey;
    }

    @Override
    public boolean canPlaySound() {
        if (this.player != null) {
            return player.level().dimensionTypeId() == this.dimensionTypeKey;
        }
        return false;
    }

    public ResourceKey<DimensionType> getDimensionTypeKey() {
        return dimensionTypeKey;
    }
}
