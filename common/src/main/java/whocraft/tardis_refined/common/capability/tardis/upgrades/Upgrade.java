package whocraft.tardis_refined.common.capability.tardis.upgrades;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.registry.TRUpgrades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Upgrade {


    private final Supplier<Upgrade> parent;
    private final UpgradeType upgradeType;
    private final Supplier<ItemStack> icon;
    private int cost = 1;
    private boolean posSet = false;
    private double posX = 0, posY = 0;
    private ResourceLocation translationKey;

    /**
     * @param icon
     * @param translationKey - Requires the namespace of your mod (e.g. my_mod_id) and the registry object (E.g. explorer) to be used for display names and translation keys
     */
    public Upgrade(Supplier<ItemStack> icon, ResourceLocation translationKey, UpgradeType upgradeType) {
        this.icon = icon;
        this.parent = null;
        this.translationKey = translationKey;
        this.upgradeType = upgradeType;
    }

    /**
     * @param icon
     * @param parent         - the parent Upgrade object before we can unlock this current upgrade
     * @param translationKey - Requires the namespace of your mod (e.g. my_mod_id) and the registry object (E.g. explorer) to be used for display names and translation keys
     */
    public Upgrade(Supplier<ItemStack> icon, Supplier<Upgrade> parent, ResourceLocation translationKey, UpgradeType upgradeType) {
        this.icon = icon;
        this.parent = parent;
        this.translationKey = translationKey;
        this.upgradeType = upgradeType;
    }

    public ItemStack getIcon() {
        return this.icon.get();
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }

    /**
     * Currently, the Tardis has a XP system, every successful flight event supplies a certain amount of Tardis XP
     * 50 Tardis XP becomes 1 skill point
     *
     * @return Skill points required before the upgrade unlocks
     */
    public int getSkillPointsRequired() {
        return this.cost;
    }

    public Upgrade setSkillPointsRequired(int cost) {
        this.cost = cost;
        return this;
    }

    public Upgrade setPosition(double x, double y) {
        this.posSet = true;
        this.posX = x;
        this.posY = y;
        return this;
    }

    @Nullable
    public Vec3 getScreenPosition() {
        if (!this.posSet) {
            return null;
        }
        return new Vec3(this.posX, this.posY, 0);
    }

    @Nullable
    public Upgrade getParent() {
        return this.parent != null ? this.parent.get() : null;
    }

    public List<Upgrade> getDirectChildren() {
        List<Upgrade> upgrades = new ArrayList<>();

        for (Map.Entry<ResourceKey<Upgrade>, Upgrade> entry : TRUpgrades.UPGRADE_DEFERRED_REGISTRY.entrySet()) {
            Upgrade upgrade = entry.getValue();
            if (upgrade.getParent() == this) {
                upgrades.add(upgrade);
            }
        }

        return upgrades;
    }

    public Component getDisplayName() {
        return Component.translatable(Util.makeDescriptionId("upgrade", this.translationKey));
    }

    public Component getDisplayDescription() {
        return Component.translatable(Util.makeDescriptionId("upgrade", this.translationKey) + ".description");
    }

    public void onUnlocked(TardisLevelOperator tardisLevelOperator, UpgradeHandler upgradeHandler) {
    }

    public void onLocked(TardisLevelOperator tardisLevelOperator, UpgradeHandler upgradeHandler) {
    }

    public boolean isUnlocked(UpgradeHandler upgradeHandler) {
        if (this.cost <= 0) {
            return true;
        }
        return upgradeHandler.isUpgradeUnlocked(this);
    }

    public enum UpgradeType {
        MAIN_UPGRADE, SUB_UPGRADE;
    }

}
