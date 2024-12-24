package whocraft.tardis_refined.compat.portals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import qouteall.imm_ptl.core.api.PortalAPI;
import qouteall.imm_ptl.core.portal.PortalManipulation;
import qouteall.q_misc_util.MiscHelper;
import qouteall.q_misc_util.api.DimensionAPI;
import qouteall.q_misc_util.my_util.DQuaternion;
import whocraft.tardis_refined.api.event.EventResult;
import whocraft.tardis_refined.api.event.TardisCommonEvents;
import whocraft.tardis_refined.common.blockentity.door.TardisInternalDoor;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.AestheticHandler;
import whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.registry.RegistrySupplier;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

import static whocraft.tardis_refined.registry.TREntityRegistry.ENTITY_TYPES;
import static whocraft.tardis_refined.registry.TREntityRegistry.registerStatic;


public class ImmersivePortals {

    private static final Map<UUID, PortalEntry> EXISTING_PORTALS = new HashMap<>();
    // First 4 is exterior, last 4 is door offsets, in order of East, South, West, North
    private static final Map<ResourceLocation, PortalOffets> THEME_OFFSETS = new HashMap<>();
    public static RegistrySupplier<EntityType<BotiPortalEntity>> BOTI_PORTAL = null;
    public static Logger LOGGER = LogManager.getLogger("TardisRefined/ImmersivePortals");

    public static void clearPortalCache() {
        EXISTING_PORTALS.clear();
    }

    public static boolean doPortalsExistForTardis(UUID uuid) {
        return EXISTING_PORTALS.containsKey(uuid);
    }

    public static PortalEntry getPortalsForTardis(UUID uuid) {
        return EXISTING_PORTALS.get(uuid);
    }


    public static ServerLevel createDimension(Level level, ResourceKey<Level> id) {
        MinecraftServer server = MiscHelper.getServer();
        if (server == null) return null;
        ServerLevel world = server.levelKeys().contains(id) ? server.getLevel(id) : null;
        if (world != null) return world;
        BiFunction<MinecraftServer, ResourceKey<LevelStem>, LevelStem> dimensionFactory = DimensionHandler::formLevelStem;
        final ResourceKey<LevelStem> dimensionKey = ResourceKey.create(Registries.LEVEL_STEM, id.location());
        DimensionAPI.addDimensionDynamically(server, id.location(), dimensionFactory.apply(server, dimensionKey));
        // TODO, Is this important? DimensionAPI.saveDimensionConfiguration(id);

        world = server.getLevel(id);
        DimensionHandler.addDimension(world.dimension());
        return world;
    }

    public static void init() {
        if (!ModCompatChecker.immersivePortals()) return; // If the mod isn't detected, we shouldn't do anything

        LOGGER.info("Immersive Portals Detected - Setting up Compatibility");

        // Register BOTI Portal here, as doing it in main code would make it a hard dependency
        BOTI_PORTAL = ENTITY_TYPES.register("boti_portal", () -> registerStatic(BotiPortalEntity::new, MobCategory.MISC, 1, 1, 96, 20, "boti_portal"));

        setupEvents();

        // Set up for Portals!
        setupPortalsForShellThemes();
    }

    private static void setupEvents() {


        // Create Portals when Doors are opened
        TardisCommonEvents.DOOR_OPENED_EVENT.register(ImmersivePortals::createPortals);

        // Destroy Portals when Doors are closed
        TardisCommonEvents.DOOR_CLOSED_EVENT.register(ImmersivePortals::destroyPortals);

        // Destroy & Create Portals when a shell is changed
        TardisCommonEvents.SHELL_CHANGE_EVENT.register((operator, theme, isSetupTardis) -> {
            TardisInternalDoor internalDoor = operator.getInternalDoor();
            ImmersivePortals.destroyPortals(operator);
            if (internalDoor != null) {
                if (internalDoor.isOpen()) {
                    ImmersivePortals.createPortals(operator);
                }
            }
        });

        TardisCommonEvents.DESKTOP_CHANGE_EVENT.register(ImmersivePortals::destroyPortals);

        TardisCommonEvents.TAKE_OFF.register((tardisLevelOperator, level, pos) -> {
            ImmersivePortals.destroyPortals(tardisLevelOperator);
            return EventResult.pass();
        });
    }

    private static void setupPortalsForShellThemes() {

        THEME_OFFSETS.clear();

        PortalOffets ttCapsule = new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.499, 0.3125, 0),
                new Vec3(0, 0.3125, 0.499), new Vec3(-0.499, 0.3125, 0), new Vec3(0, 0.3125, -0.499)), new PortalOffets.OffsetData(
                new Vec3(-1.375, 0.125, 0), new Vec3(0, 0.125, -1.375),
                new Vec3(1.375, 0.125, 0), new Vec3(0, 0.125, 1.375)), new Vec2(1, 2));

        registerThemePortal(ShellTheme.FACTORY.get(), ttCapsule);

        registerThemePortal(ShellTheme.HALF_BAKED.get(), ttCapsule);


        registerThemePortal(ShellTheme.POLICE_BOX.get(),
                new PortalOffets(new PortalOffets.OffsetData(
                        new Vec3(0.6, 0.125, 0),
                        new Vec3(0, 0.125, 0.6),
                        new Vec3(-0.6, 0.125, 0),
                        new Vec3(0, 0.125, -0.6)),

                        new PortalOffets.OffsetData(
                                new Vec3(-1.425, 0.0625, 0),
                                new Vec3(0, 0.0625, -1.425),
                                new Vec3(1.425, 0.0625, 0),
                                new Vec3(0, 0.0625, 1.700)), new Vec2(1, 2)));

        registerThemePortal(ShellTheme.PHONE_BOOTH.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.5, 0.125, 0),
                new Vec3(0, 0.125, 0.5), new Vec3(-0.5, 0.125, 0), new Vec3(0, 0.125, -0.5)),
                new PortalOffets.OffsetData(new Vec3(-1.435, 0, 0), new Vec3(0, 0, -1.435),
                        new Vec3(1.435, 0, 0), new Vec3(0, 0, 1.435)), new Vec2(1, 2)));

        registerThemePortal(ShellTheme.MYSTIC.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.5, 0.1875, 0),
                new Vec3(0, 0.1875, 0.5), new Vec3(-0.5, 0.1875, 0), new Vec3(0, 0.1875, -0.5)),
                new PortalOffets.OffsetData(new Vec3(-1.425, 0.0625, 0), new Vec3(0, 0.0625, -1.425),
                        new Vec3(1.425, 0.0625, 0), new Vec3(0, 0.0625, 1.425)), new Vec2(1, 2)));

        registerThemePortal(ShellTheme.VENDING.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.57, 0, 0),
                new Vec3(0, 0, 0.57), new Vec3(-0.57, 0, 0), new Vec3(0, 0, -0.57)), new PortalOffets.OffsetData(
                new Vec3(-1.455, 0, 0), new Vec3(0, 0, -1.455),
                new Vec3(1.455, 0, 0), new Vec3(0, 0, 1.455)), new Vec2(1, 2.175f)));

        registerThemePortal(ShellTheme.PRESENT.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.57, 0, 0),
                new Vec3(0, 0, 0.57), new Vec3(-0.57, 0, 0), new Vec3(0, 0, -0.57)), new PortalOffets.OffsetData(
                new Vec3(-1.455, 0, 0), new Vec3(0, 0, -1.455),
                new Vec3(1.455, 0, 0), new Vec3(0, 0, 1.455)), new Vec2(1, 2.175f)));


        registerThemePortal(ShellTheme.DRIFTER.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.61, 0.125, 0),
                new Vec3(0, 0.125, 0.61), new Vec3(-0.61, 0.125, 0), new Vec3(0, 0.125, -0.61)), new PortalOffets.OffsetData(
                new Vec3(-1.425, 0.0625, 0), new Vec3(0, 0.0625, -1.425),
                new Vec3(1.425, 0.0625, 0), new Vec3(0, 0.0625, 1.425)), new Vec2(1, 2)));

        registerThemePortal(ShellTheme.HIEROGLYPH.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.5, 0, 0),
                new Vec3(0, 0, 0.5), new Vec3(-0.5, 0, 0), new Vec3(0, 0, -0.5)), new PortalOffets.OffsetData(
                new Vec3(-1.33, 0, 0), new Vec3(0, 0, -1.33),
                new Vec3(1.33, 0, 0), new Vec3(0, 0, 1.33)), new Vec2(1, 2.25f)));


        registerThemePortal(ShellTheme.GROENING.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.5, 0, 0),
                new Vec3(0, 0, 0.5), new Vec3(-0.5, 0, 0), new Vec3(0, 0, -0.5)), new PortalOffets.OffsetData(
                new Vec3(-1.33, 0, 0), new Vec3(0, 0, -1.33),
                new Vec3(1.33, 0, 0), new Vec3(0, 0, 1.33)), new Vec2(1, 2.25f)));

        registerThemePortal(ShellTheme.BIG_BEN.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.46, 0, 0),
                new Vec3(0, 0, 0.46), new Vec3(-0.46, 0, 0), new Vec3(0, 0, -0.46)), new PortalOffets.OffsetData(
                new Vec3(-1.3, 0, 0), new Vec3(0, 0, -1.3),
                new Vec3(1.3, 0, 0), new Vec3(0, 0, 1.3)), new Vec2(1, 2)));

        registerThemePortal(ShellTheme.NUKA.get(),
                new PortalOffets(
                        new PortalOffets.OffsetData(new Vec3(0.65, 0.375, 0), new Vec3(0, 0.375, 0.65), new Vec3(-0.65, 0.375, 0), new Vec3(0, 0.375, -0.65)),
                        new PortalOffets.OffsetData(new Vec3(-1.33, 0, 0), new Vec3(0, 0, -1.33), new Vec3(1.33, 0, 0), new Vec3(0, 0, 1.33)), new Vec2(1, 2)
                )
        );

        /* TODO: Fix these
        registerShellPortal(ShellTheme.PAGODA.get(),
                new PortalOffets(
                        new PortalOffets.OffsetData(new Vec3(0.65, 0.09375, 0), new Vec3(0, 0.09375, 0.51), new Vec3(-0.65, 0.09375, 0), new Vec3(0, 0.09375, -0.51)),
                        new PortalOffets.OffsetData( new Vec3(-1.33, 0.09375, 0), new Vec3(0, 0.09375, -1.33), new Vec3(1.33, 0.09375, 0), new Vec3(0, 0.09375, 1.33))
                )
        );
        registerShellPortal(ShellTheme.GROENING.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.5, 0.09375, 0),
                new Vec3(0, 0.09375, 0.5), new Vec3(-0.5, 0.09375, 0), new Vec3(0, 0.09375, -0.5)), new PortalOffets.OffsetData(
                new Vec3(-1.33, 0.09375, 0), new Vec3(0, 0.09375, -1.33),
                new Vec3(1.33, 0.09375, 0), new Vec3(0, 0.09375, 1.33))));
         */


        // First 4 is exterior, last 4 is door offsets, in order of East, South, West, North
        registerThemePortal(ShellTheme.PORTALOO.get(), new PortalOffets(new PortalOffets.OffsetData(new Vec3(0.51, 0.125, 0),
                new Vec3(0, 0.125, 0.51), new Vec3(-0.51, 0.125, 0), new Vec3(0, 0.125, -0.51)),
                new PortalOffets.OffsetData(
                        new Vec3(-1.425, 0, 0), new Vec3(0, 0, -1.425),
                        new Vec3(1.425, 0, 0), new Vec3(0, 0, 1.425)), new Vec2(1, 2)));


        detectMissingSetup();
    }


    private static void detectMissingSetup() {
        for (ResourceLocation value : ShellTheme.SHELL_THEME_REGISTRY.keySet()) {
            if (!isShellThemeSupported(value) && !value.equals(ShellTheme.getKey(ShellTheme.BRIEFCASE.get()))) {
                LOGGER.info("{} shell has not been setup for ImmersivePortals", value);
            }
        }
    }


    public static void registerThemePortal(ShellTheme shellTheme, PortalOffets portalOffets) {
        THEME_OFFSETS.put(ShellTheme.getKey(shellTheme), portalOffets);
    }

    public static boolean isShellThemeSupported(ResourceLocation shellTheme) {
        return THEME_OFFSETS.containsKey(shellTheme);
    }

    public static boolean onDoorRemoved(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        if (blockEntity instanceof TardisInternalDoor door) {
            if (level instanceof ServerLevel serverLevel) {
                if (!door.isMainDoor()) {
                    return true;
                }
                if (serverLevel.dimensionTypeId().equals(TRDimensionTypes.TARDIS)) {
                    TardisLevelOperator.get(serverLevel).ifPresent(ImmersivePortals::destroyPortals);
                }
            }
        }
        return true;
    }

    public static void createPortals(TardisLevelOperator operator) {

        // Just for debugging editing values
        if (!Platform.isProduction()) {
            setupPortalsForShellThemes();
        }
        if(operator.getInternalDoor() == null){
            return;
        }

        if(operator.getPilotingManager().isInFlight()){
            destroyPortals(operator);
            return;
        }

        destroyPortals(operator);
        UUID dimId = UUID.fromString(operator.getLevel().dimension().location().getPath());

        AestheticHandler aestheticsHandler = operator.getAestheticHandler();
        TardisInteriorManager interiorManager = operator.getInteriorManager();

        ResourceLocation theme = aestheticsHandler.getShellTheme();
        TardisInternalDoor door = operator.getInternalDoor();
        TardisPilotingManager pilotingManager = operator.getPilotingManager();

        if (!isShellThemeSupported(theme)) {
            destroyPortals(operator); //we're going to make sure.
            return;
        }

        if (interiorManager.isCave() || door != null && !door.isOpen() || !operator.isTardisReady() || EXISTING_PORTALS.get(dimId) != null || door == null) {
            return;
        }


        TardisNavLocation location = pilotingManager.getCurrentLocation();
        BlockPos entryPositionBPos = door.getTeleportPosition();
        Vec3 entryPosition = new Vec3(entryPositionBPos.getX() + 0.5, entryPositionBPos.getY() + 1, entryPositionBPos.getZ() + 0.5);
        BlockPos exteriorEntryBPos = location.getPosition();
        Vec3 exteriorEntryPosition = new Vec3(exteriorEntryBPos.getX() + 0.5, exteriorEntryBPos.getY() + 1, exteriorEntryBPos.getZ() + 0.5);

        theme = operator.getAestheticHandler().getShellTheme();
        PortalOffets themeData = THEME_OFFSETS.get(theme);
        PortalOffets.OffsetData interiorDoor = themeData.intDoor();
        PortalOffets.OffsetData exteriorDoor = themeData.shell();

        Level operatorLevel = operator.getLevel();


        switch (location.getDirection()) {
            case EAST -> exteriorEntryPosition = exteriorEntryPosition.add(exteriorDoor.east());
            case SOUTH -> exteriorEntryPosition = exteriorEntryPosition.add(exteriorDoor.south());
            case WEST -> exteriorEntryPosition = exteriorEntryPosition.add(exteriorDoor.west());
            case NORTH -> exteriorEntryPosition = exteriorEntryPosition.add(exteriorDoor.north());
        }

        switch (door.getTeleportRotation()) {
            case EAST -> entryPosition = entryPosition.add(interiorDoor.east());
            case SOUTH -> entryPosition = entryPosition.add(interiorDoor.south());
            case WEST -> entryPosition = entryPosition.add(interiorDoor.west());
            case NORTH -> entryPosition = entryPosition.add(interiorDoor.north());
        }
        DQuaternion extQuat = DQuaternion.rotationByDegrees(new Vec3(0, -1, 0), location.getDirection().toYRot());
        DQuaternion interiorQuat = DQuaternion.rotationByDegrees(new Vec3(0, -1, 0), door.getTeleportRotation().toYRot());

        BotiPortalEntity exteriorPortal = createPortal(location.getLevel(), exteriorEntryPosition, entryPosition, operatorLevel.dimension(), extQuat);
        BotiPortalEntity interiorPortal = createDestPortal(exteriorPortal, entryPosition, ImmersivePortals.BOTI_PORTAL.get(), interiorQuat);

        exteriorPortal.setShellTheme(ShellTheme.getShellTheme(theme));
        interiorPortal.setShellTheme(ShellTheme.getShellTheme(theme));

        updatePortalEntry(operator, dimId, interiorPortal, exteriorPortal, theme);

        PortalManipulation.adjustRotationToConnect(exteriorPortal, interiorPortal);
        exteriorPortal.setInteractable(false);
        interiorPortal.setInteractable(false);
        interiorPortal.setValid(true);
        exteriorPortal.setValid(true);

        CompoundTag tag = new CompoundTag();
        tag.putBoolean("adjustPositionAfterTeleport", false);

        exteriorPortal.updatePortalFromNbt(tag);

        interiorPortal.updatePortalFromNbt(tag);

        exteriorPortal.level().addFreshEntity(exteriorPortal);
        interiorPortal.level().addFreshEntity(interiorPortal);

        exteriorPortal.reloadPortal();
        interiorPortal.reloadPortal();
    }

    private static void updatePortalEntry(TardisLevelOperator operator, UUID dimId, BotiPortalEntity interiorPortal, BotiPortalEntity exteriorPortal, ResourceLocation theme) {
        destroyPortals(operator);
        EXISTING_PORTALS.put(dimId, new PortalEntry(interiorPortal, exteriorPortal, ShellTheme.getShellTheme(theme), dimId));
    }

    public static void destroyPortals(TardisLevelOperator operator) {
        UUID tardisID = UUID.fromString(operator.getLevel().dimension().location().getPath());
        PortalEntry portalEntry = EXISTING_PORTALS.get(tardisID);
        if (portalEntry == null) {
            return;
        }

        PortalManipulation.removeConnectedPortals(portalEntry.getInternalPortal(), portal -> {

        });

        PortalManipulation.removeConnectedPortals(portalEntry.getShellPortal(), portal -> {

        });

        portalEntry.getInternalPortal().setValid(false);
        portalEntry.getInternalPortal().kill();

        portalEntry.getShellPortal().setValid(false);
        portalEntry.getShellPortal().kill();

        EXISTING_PORTALS.remove(tardisID);
    }

    public static BotiPortalEntity createDestPortal(BotiPortalEntity portal, Vec3 doorPos, EntityType<BotiPortalEntity> entityType, DQuaternion quat) {
        Level world = portal.getDestinationWorld();

        BotiPortalEntity newPortal = entityType.create(world);
        newPortal.setTardisId(UUID.fromString(world.dimension().location().getPath()));
        newPortal.dimensionTo = portal.level().dimension();
        newPortal.setPos(doorPos);
        newPortal.setDestination(portal.getOriginPos());
        newPortal.specificPlayerId = portal.specificPlayerId;

        //TODO Is this important? newPortal.initCullableRange(portal.cullableXStart * portal.scaling, portal.cullableXEnd * portal.scaling, -portal.cullableYStart * portal.scaling, -portal.cullableYEnd * portal.scaling);

        newPortal.width = portal.width;
        newPortal.height = portal.height;
        newPortal.axisW = new Vec3(1, 0, 0);
        newPortal.axisH = new Vec3(0, 1, 0);

        PortalManipulation.rotatePortalBody(newPortal, DQuaternion.fromMcQuaternion(quat.toMcQuaternion()));

        return newPortal;
    }

    public static BotiPortalEntity createPortal(Level level, Vec3 origin, Vec3 destination, ResourceKey<Level> destinationLvl, DQuaternion quat) {
        BotiPortalEntity portal = ImmersivePortals.BOTI_PORTAL.get().create(level);
        portal.setTardisId(UUID.fromString(destinationLvl.location().getPath()));
        portal.setOriginPos(origin);
        portal.setDestinationDimension(destinationLvl);
        portal.setDestination(destination);
        portal.setOrientationAndSize(
                new Vec3(1, 0, 0), // axisW
                new Vec3(0, 1, 0), // axisH
                1, // width
                2.175 // height
        );
        PortalManipulation.rotatePortalBody(portal, DQuaternion.fromMcQuaternion(quat.toMcQuaternion()));

        return portal;
    }

    public static void teleportViaIp(Entity pEntity, ServerLevel destination, double pX, double pY, double pZ) {
        PortalAPI.teleportEntity(pEntity, destination, new Vec3(pX, pY, pZ));
    }

    public static void onServerStopping(MinecraftServer server) {
        EXISTING_PORTALS.forEach((uuid, portalEntry) -> {
            portalEntry.getShellPortal().setValid(false);
            portalEntry.getInternalPortal().setValid(false);
            portalEntry.getShellPortal().kill();
            portalEntry.getInternalPortal().kill();
        });

        EXISTING_PORTALS.clear();
    }
}
