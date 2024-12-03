package whocraft.tardis_refined.common.capability.tardis.upgrades;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class SpeedUpgrade extends Upgrade {
    private int speedModifier = 1;

    public SpeedUpgrade(Supplier<ItemStack> icon, ResourceLocation translationKey, UpgradeType upgradeType) {
        super(icon, translationKey, upgradeType);
    }

    public SpeedUpgrade(Supplier<ItemStack> icon, Supplier<Upgrade> parent, ResourceLocation translationKey, UpgradeType upgradeType) {
        super(icon, parent, translationKey, upgradeType);
    }

    public int getSpeedModifier() {
        return speedModifier;
    }

    public SpeedUpgrade setSpeedModifier(int speedModifier) {
        this.speedModifier = speedModifier;
        return this;
    }
}
