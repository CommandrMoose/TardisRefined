package whocraft.tardis_refined.common.capability.tardis.upgrades;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import whocraft.tardis_refined.api.event.TardisCommonEvents;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.registry.TRUpgrades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static whocraft.tardis_refined.common.tardis.TardisArchitectureHandler.generateArsTree;

public class UpgradeHandler {

    // TODO max xp per point might change, set to 50 for testing
    public static final int XP_PER_UPGRADE_POINT = 50;

    @NotNull
    private final TardisLevelOperator tardisLevelOperator;
    private final List<Upgrade> unlockedUpgrades = new ArrayList<>();
    private int upgradeXP = 0;
    private int upgradePoints = 0;
    private int overallTardisPoints = 0;

    public UpgradeHandler(@NotNull TardisLevelOperator tardisLevelOperator) {
        this.tardisLevelOperator = tardisLevelOperator;
    }

    public double calculateProgress() {
        int totalUpgrades = TRUpgrades.UPGRADE_DEFERRED_REGISTRY.getRegistry().get().size();
        int unlockedCount = unlockedUpgrades.size();

        if (totalUpgrades == 0) {
            return 0.0;
        }

        return ((double) unlockedCount / totalUpgrades) * 100.0;
    }

    public String getProgressLevel() {
        double progress = calculateProgress();

        if (progress >= 80.0) {
            return "five";
        } else if (progress >= 60.0) {
            return "four";
        } else if (progress >= 40.0) {
            return "three";
        } else if (progress >= 20.0) {
            return "two";
        } else {
            return "one";
        }
    }

    @Override
    public String toString() {
        return "UpgradeHandler{" +
                "tardisLevelOperator=" + tardisLevelOperator +
                ", upgradeXP=" + upgradeXP +
                ", upgradePoints=" + upgradePoints +
                ", unlockedUpgrades=" + unlockedUpgrades +
                '}';
    }

    public List<Upgrade> getUnlockedUpgrades() {
        return this.unlockedUpgrades;
    }

    public int getUpgradeXP() {
        return this.upgradeXP;
    }

    public void setUpgradeXP(int upgradeXP) {
        this.upgradeXP = Mth.clamp(upgradeXP, 0, 49);
    }

    public void addUpgradeXP(int upgradeXP) {
        this.upgradeXP += upgradeXP;

        while (this.upgradeXP >= XP_PER_UPGRADE_POINT) {
            this.upgradePoints++;
            this.overallTardisPoints++;
            this.upgradeXP -= XP_PER_UPGRADE_POINT;
        }

        while (this.upgradeXP <= -XP_PER_UPGRADE_POINT) {
            this.upgradePoints--;
            this.upgradeXP += XP_PER_UPGRADE_POINT;
        }

        if (this.upgradeXP < 0) {
            this.upgradePoints--;
            this.upgradeXP = XP_PER_UPGRADE_POINT - Mth.abs(this.upgradeXP);
        }

        this.upgradePoints = Mth.clamp(this.upgradePoints, 0, Integer.MAX_VALUE);

    }

    public int getNeededXPForNextPoint() {
        return XP_PER_UPGRADE_POINT - this.upgradeXP;
    }

    public int getUpgradePoints() {
        return this.upgradePoints;
    }

    public void setUpgradePoints(int upgradePoints) {
        this.upgradePoints = Mth.clamp(upgradePoints, 0, Integer.MAX_VALUE);
    }

    public int getOverallTardisPoints() {
        return overallTardisPoints;
    }

    public void addUpgradePoints(int upgradePoints) {
        this.setUpgradePoints(this.getUpgradePoints() + upgradePoints);
    }

    public boolean isUpgradeUnlocked(Upgrade upgrade) {
        Upgrade s = upgrade;

        while (s != null) {
            if (!this.unlockedUpgrades.contains(upgrade)) {
                return false;
            }

            s = s.getParent();
        }

        return true;
    }

    public void unlockUpgrade(Upgrade upgrade) {
        if (!this.isUpgradeUnlocked(upgrade)) {
            var parent = upgrade.getParent();

            while (parent != null) {
                if (!this.isUpgradeUnlocked(parent)) {
                    this.unlockedUpgrades.add(parent);
                    parent.onUnlocked(this.tardisLevelOperator, this);
                    parent = parent.getParent();
                } else {
                    break;
                }
            }

            this.unlockedUpgrades.add(upgrade);
            upgrade.onUnlocked(this.tardisLevelOperator, this);

            if (this.tardisLevelOperator.getLevel() instanceof ServerLevel serverLevel) {
                generateArsTree(tardisLevelOperator, serverLevel);
            }

            TardisCommonEvents.UPGRADE_UNLOCKED.invoker().onUpgradeUnlock(this.tardisLevelOperator, upgrade);

        }
    }

    public void lockUpgrade(Upgrade upgrade) {
        if (this.unlockedUpgrades.contains(upgrade)) {
            this.unlockedUpgrades.remove(upgrade);
            upgrade.onLocked(this.tardisLevelOperator, this);
            for (Upgrade child : upgrade.getDirectChildren()) {
                this.lockUpgrade(child);
            }
        }
    }

    public CompoundTag saveData(CompoundTag compoundTag) {
        CompoundTag updateTag = new CompoundTag();
        updateTag.putInt("UpgradeXP", this.upgradeXP);
        updateTag.putInt("UpgradePoints", this.upgradePoints);
        updateTag.putInt("OverallPoints", this.overallTardisPoints);

        ListTag unlockedUpgradesTag = new ListTag();
        for (Upgrade upgrade : this.unlockedUpgrades) {

            if (TRUpgrades.UPGRADE_DEFERRED_REGISTRY.getKey(upgrade) == null) {
                continue;
            }

            unlockedUpgradesTag.add(StringTag.valueOf(Objects.requireNonNull(TRUpgrades.UPGRADE_REGISTRY.getKey(upgrade)).toString()));
        }
        updateTag.put("UnlockedUpgrades", unlockedUpgradesTag);
        compoundTag.put("upgrades", updateTag);
        return compoundTag;
    }

    public void loadData(CompoundTag compoundTag) {
        CompoundTag nbt = (CompoundTag) compoundTag.get("upgrades");
        this.upgradeXP = nbt.getInt("UpgradeXP");
        this.upgradePoints = nbt.getInt("UpgradePoints");
        this.overallTardisPoints = nbt.getInt("OverallPoints");
        this.unlockedUpgrades.clear();
        for (Tag upgrade : nbt.getList("UnlockedUpgrades", StringTag.TAG_STRING)) {
            this.unlockedUpgrades.add(TRUpgrades.UPGRADE_REGISTRY.get(ResourceLocation.parse(upgrade.getAsString())));
        }
    }
}