package whocraft.tardis_refined.common.blockentity.shell;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.common.blockentity.TardisDoorProperties;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;

public interface ExteriorShell extends TardisDoorProperties {

    ResourceKey<Level> getTardisId();

    void setTardisId(ResourceKey<Level> levelKey);

    boolean isInvalidTardis(TardisLevelOperator tardisLevelOperator);
}
