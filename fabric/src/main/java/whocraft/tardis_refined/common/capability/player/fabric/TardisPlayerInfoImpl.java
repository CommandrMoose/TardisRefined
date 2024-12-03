package whocraft.tardis_refined.common.capability.player.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import whocraft.tardis_refined.common.capability.player.TardisPlayerInfo;

import java.util.Objects;
import java.util.Optional;

public class TardisPlayerInfoImpl extends TardisPlayerInfo implements ComponentV3 {

    public TardisPlayerInfoImpl(Player player) {
        super(player);
    }

    public static Optional<TardisPlayerInfo> get(LivingEntity livingEntity) {
        try {
            return Optional.of(TardisPlayerComponents.TARDIS_PLAYER_INFO.get(livingEntity));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.loadData(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        CompoundTag nbt = this.saveData();
        for (String key : nbt.getAllKeys()) {
            tag.put(key, Objects.requireNonNull(nbt.get(key)));
        }
    }


}