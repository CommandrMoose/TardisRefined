package whocraft.tardis_refined.compat.portals;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import qouteall.imm_ptl.core.portal.Portal;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.common.util.MiscHelper;
import whocraft.tardis_refined.constants.NbtConstants;

import java.util.Optional;
import java.util.UUID;

public class BotiPortalEntity extends Portal {

    private static final EntityDataAccessor<Optional<UUID>> TARDIS_ID = SynchedEntityData.defineId(BotiPortalEntity.class, EntityDataSerializers.OPTIONAL_UUID);


    // We don't save this as the portals die on server stop, we just need it in RAM
    ShellTheme shellTheme = ShellTheme.FACTORY.get();
    private boolean isValid = false;

    public BotiPortalEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
    }

    public ShellTheme getShellTheme() {
        return shellTheme;
    }

    public void setShellTheme(ShellTheme shellTheme) {
        this.shellTheme = shellTheme;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        UUID tardisId = getTardisId();
        if (tardisId != null) {
            compoundTag.putUUID(NbtConstants.TARDIS_ID, tardisId);
        }
        compoundTag.putBoolean("valid", isValid);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains(NbtConstants.TARDIS_ID)) {
            setTardisId(compoundTag.getUUID(NbtConstants.TARDIS_ID));
        }
        if (compoundTag.contains("valid")) {
            setValid(compoundTag.getBoolean("valid"));
        } else {
            isValid = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean isPortalValid() {
        UUID tardisId = getTardisId();

        if (level() instanceof ServerLevel serverLevel && tickCount > (20 * 40)) {
            // Fetch portal entry for the TARDIS
            PortalEntry portalEntry = ImmersivePortals.getPortalsForTardis(tardisId);

            // Get the TARDIS dimension level
            ResourceKey<Level> tardisDimIdLocation = MiscHelper.idToKey(new ResourceLocation(TardisRefined.MODID, tardisId.toString()));
            ServerLevel tardisLevel = serverLevel.getServer().getLevel(tardisDimIdLocation);

            if (tardisLevel != null) {
                Optional<TardisLevelOperator> tardisLevelOperatorOpt = TardisLevelOperator.get(tardisLevel);

                if (tardisLevelOperatorOpt.isPresent()) {
                    TardisPilotingManager pilotManager = tardisLevelOperatorOpt.get().getPilotingManager();
                    if (pilotManager.isInFlight()) {
                        return false;
                    }
                }
            }

            // Check for portal entry and client-side conditions
            if (portalEntry == null && tickCount > (2 * 20) && !level().isClientSide()) {
                return false;
            }

            // Additional validity check
            if (!isValid) {
                return false;
            }
        }

        return super.isPortalValid();
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(TARDIS_ID, Optional.of(UUID.randomUUID()));
    }

    public UUID getTardisId() {
        return this.getEntityData().get(TARDIS_ID).get();
    }

    public void setTardisId(UUID tardisId) {
        this.getEntityData().set(TARDIS_ID, Optional.of(tardisId));
    }


    /*Marks whether or not a portal can live! If set to false the portal will immediately poof out of existence*/
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
