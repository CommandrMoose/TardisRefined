package whocraft.tardis_refined.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.upgrades.IncrementUpgrade;
import whocraft.tardis_refined.common.capability.tardis.upgrades.SpeedUpgrade;
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.common.util.RegistryHelper;

public class TRUpgrades {

    public static final ResourceKey<Registry<Upgrade>> UPGRADE_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "upgrade"));

    /**
     * Tardis Refined instance of the Upgrade registry. Addon Mods: DO NOT USE THIS, it is only for Tardis Refined use only
     */
    public static final DeferredRegister<Upgrade> UPGRADE_DEFERRED_REGISTRY = DeferredRegister.createCustom(TardisRefined.MODID, UPGRADE_REGISTRY_KEY, true);

    /**
     * Instance of registry containing all Upgrade entries. Addon mod entries will be included in this registry as long as they are use the same ResourceKey<Registry<ObjectType>>.
     */
    public static final Registry<Upgrade> UPGRADE_REGISTRY = UPGRADE_DEFERRED_REGISTRY.getRegistry().get();

    // Base Upgrades
    public static final RegistrySupplier<Upgrade> TARDIS_XP = UPGRADE_DEFERRED_REGISTRY.register("tardis_xp", () -> new Upgrade(Items.GLASS_BOTTLE::getDefaultInstance, RegistryHelper.makeKey("tardis_xp"), Upgrade.UpgradeType.MAIN_UPGRADE)
            .setSkillPointsRequired(0).setPosition(5, 0));

    // Chameleon Circuit Upgrades
    public static final RegistrySupplier<Upgrade> ARCHITECTURE_SYSTEM = UPGRADE_DEFERRED_REGISTRY.register("architecture_system", () -> new Upgrade(TRBlockRegistry.ARS_EGG.get().asItem()::getDefaultInstance, TARDIS_XP, RegistryHelper.makeKey("architecture_system"), Upgrade.UpgradeType.MAIN_UPGRADE)
            .setSkillPointsRequired(1).setPosition(3, 1));

    public static final RegistrySupplier<Upgrade> CHAMELEON_CIRCUIT_SYSTEM = UPGRADE_DEFERRED_REGISTRY.register("chameleon_circuit_system", () -> new Upgrade(TRBlockRegistry.ROOT_SHELL_BLOCK.get().asItem()::getDefaultInstance, ARCHITECTURE_SYSTEM, RegistryHelper.makeKey("chameleon_circuit_system"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(10).setPosition(2, 2));

    public static final RegistrySupplier<Upgrade> INSIDE_ARCHITECTURE = UPGRADE_DEFERRED_REGISTRY.register("inside_architecture", () -> new Upgrade(TRBlockRegistry.TERRAFORMER_BLOCK.get().asItem()::getDefaultInstance, ARCHITECTURE_SYSTEM, RegistryHelper.makeKey("inside_architecture"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(20).setPosition(4, 2));

    public static final RegistrySupplier<Upgrade> IMPROVED_GENERATION_TIME_I = UPGRADE_DEFERRED_REGISTRY.register("improved_generation_time_i", () -> new Upgrade(Items.TURTLE_EGG::getDefaultInstance, INSIDE_ARCHITECTURE, RegistryHelper.makeKey("improved_generation_time_i"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(10).setPosition(4, 3));
    public static final RegistrySupplier<Upgrade> IMPROVED_GENERATION_TIME_II = UPGRADE_DEFERRED_REGISTRY.register("improved_generation_time_ii", () -> new Upgrade(Items.RABBIT_FOOT::getDefaultInstance, IMPROVED_GENERATION_TIME_I, RegistryHelper.makeKey("improved_generation_time_ii"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(10).setPosition(4, 4));
    public static final RegistrySupplier<Upgrade> IMPROVED_GENERATION_TIME_III = UPGRADE_DEFERRED_REGISTRY.register("improved_generation_time_iii", () -> new Upgrade(Items.MINECART::getDefaultInstance, IMPROVED_GENERATION_TIME_II, RegistryHelper.makeKey("improved_generation_time_iii"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(10).setPosition(4, 5));

    // Defense Upgrades
    public static final RegistrySupplier<Upgrade> DEFENSE_SYSTEM = UPGRADE_DEFERRED_REGISTRY.register("defense_system", () -> new Upgrade(Items.DIAMOND_SWORD::getDefaultInstance, TARDIS_XP, RegistryHelper.makeKey("defense_system"), Upgrade.UpgradeType.MAIN_UPGRADE)
            .setSkillPointsRequired(1).setPosition(1, 1));

    public static final RegistrySupplier<Upgrade> MATERIALIZE_AROUND = UPGRADE_DEFERRED_REGISTRY.register("materialize_around", () -> new Upgrade(Items.GLASS_PANE::getDefaultInstance, DEFENSE_SYSTEM, RegistryHelper.makeKey("materialize_around"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(2).setPosition(1, 2));

    // Navigation Upgrades
    public static final RegistrySupplier<Upgrade> NAVIGATION_SYSTEM = UPGRADE_DEFERRED_REGISTRY.register("navigation_system", () -> new Upgrade(Items.COMPASS::getDefaultInstance, TARDIS_XP, RegistryHelper.makeKey("navigation_system"), Upgrade.UpgradeType.MAIN_UPGRADE)
            .setSkillPointsRequired(1).setPosition(7, 1));

    public static final RegistrySupplier<Upgrade> EXPLORER = UPGRADE_DEFERRED_REGISTRY.register("explorer", () -> new IncrementUpgrade(Items.COMPASS::getDefaultInstance, NAVIGATION_SYSTEM, RegistryHelper.makeKey("explorer"), Upgrade.UpgradeType.SUB_UPGRADE).setIncrementAmount(1000)
            .setSkillPointsRequired(10).setPosition(7, 2));

    public static final RegistrySupplier<Upgrade> EXPLORER_II = UPGRADE_DEFERRED_REGISTRY.register("explorer_ii", () -> new IncrementUpgrade(Items.COMPASS::getDefaultInstance, EXPLORER, RegistryHelper.makeKey("explorer_ii"), Upgrade.UpgradeType.SUB_UPGRADE).setIncrementAmount(2500)
            .setSkillPointsRequired(15).setPosition(7, 3));

    public static final RegistrySupplier<Upgrade> EXPLORER_III = UPGRADE_DEFERRED_REGISTRY.register("explorer_iii", () -> new IncrementUpgrade(Items.COMPASS::getDefaultInstance, EXPLORER_II, RegistryHelper.makeKey("explorer_iii"), Upgrade.UpgradeType.SUB_UPGRADE).setIncrementAmount(5000)
            .setSkillPointsRequired(20).setPosition(7, 4));

    public static final RegistrySupplier<Upgrade> DIMENSION_TRAVEL = UPGRADE_DEFERRED_REGISTRY.register("dimension_travel", () -> new Upgrade(Blocks.NETHER_BRICKS.asItem()::getDefaultInstance, EXPLORER_III, RegistryHelper.makeKey("dimension_travel"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(20).setPosition(7, 5));

    public static final RegistrySupplier<Upgrade> WAYPOINTS = UPGRADE_DEFERRED_REGISTRY.register("waypoints", () -> new Upgrade(Items.MAP::getDefaultInstance, NAVIGATION_SYSTEM, RegistryHelper.makeKey("waypoints"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(10).setPosition(6, 2));

    public static final RegistrySupplier<Upgrade> LANDING_PAD = UPGRADE_DEFERRED_REGISTRY.register("landing_pad", () -> new Upgrade(TRBlockRegistry.LANDING_PAD.get().asItem()::getDefaultInstance, NAVIGATION_SYSTEM, RegistryHelper.makeKey("landing_pad"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSkillPointsRequired(10).setPosition(8, 2));

    // Flight Upgrades
    public static final RegistrySupplier<Upgrade> FLIGHT_SYSTEM = UPGRADE_DEFERRED_REGISTRY.register("flight_system", () -> new Upgrade(Items.ELYTRA::getDefaultInstance, TARDIS_XP, RegistryHelper.makeKey("flight_system"), Upgrade.UpgradeType.MAIN_UPGRADE)
            .setSkillPointsRequired(1).setPosition(9, 1));

    public static final RegistrySupplier<Upgrade> SPEED_I = UPGRADE_DEFERRED_REGISTRY.register("speed_i", () -> new SpeedUpgrade(Items.FIREWORK_ROCKET::getDefaultInstance, FLIGHT_SYSTEM, RegistryHelper.makeKey("speed_i"), Upgrade.UpgradeType.SUB_UPGRADE).setSpeedModifier(5)
            .setSkillPointsRequired(10).setPosition(9, 2));

    public static final RegistrySupplier<Upgrade> SPEED_II = UPGRADE_DEFERRED_REGISTRY.register("speed_ii", () -> new SpeedUpgrade(Items.FIREWORK_ROCKET::getDefaultInstance, SPEED_I, RegistryHelper.makeKey("speed_ii"), Upgrade.UpgradeType.SUB_UPGRADE).setSpeedModifier(10)
            .setSkillPointsRequired(20).setPosition(9, 3));

    public static final RegistrySupplier<Upgrade> SPEED_III = UPGRADE_DEFERRED_REGISTRY.register("speed_iii", () -> new SpeedUpgrade(Items.FIREWORK_ROCKET::getDefaultInstance, SPEED_II, RegistryHelper.makeKey("speed_iii"), Upgrade.UpgradeType.SUB_UPGRADE).setSpeedModifier(25)
            .setSkillPointsRequired(30).setPosition(9, 4));

    public static final RegistrySupplier<Upgrade> SPEED_IV = UPGRADE_DEFERRED_REGISTRY.register("speed_iv", () -> new SpeedUpgrade(Items.FIREWORK_ROCKET::getDefaultInstance, SPEED_II, RegistryHelper.makeKey("speed_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
            .setSpeedModifier(50)
            .setSkillPointsRequired(50)
            .setPosition(9, 5));

}
