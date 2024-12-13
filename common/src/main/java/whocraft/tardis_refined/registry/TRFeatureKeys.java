package whocraft.tardis_refined.registry;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;

/**
 * Instances of ResourceLocations that are shared between Configured and Placed Features.
 * DO NOT REGISTER THEM, FOR DATAGEN ONLY
 * In 1.19.3+ most world gen objects will not work if registered via code
 */
public class TRFeatureKeys {

    public static ResourceLocation TARDIS_ROOT_CLUSTER_RL = ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "tardis_root_cluster");

}