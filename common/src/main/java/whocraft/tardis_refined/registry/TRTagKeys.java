package whocraft.tardis_refined.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.util.RegistryHelper;

public class TRTagKeys {
    /**
     * Entity tag used to blacklist entities from being teleported into the Tardis via the doors or being landed on by the exterior shell
     */
    public static final TagKey<EntityType<?>> TARDIS_TELEPORT_BLACKLIST = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(TardisRefined.MODID, "tardis_teleport_blacklist"));
    public static TagKey<Biome> IS_MOUNTAIN_OR_OCEAN = RegistryHelper.makeGenericBiomeTagCollection("is_mountain_or_ocean");
    public static TagKey<Biome> TARDIS_ROOT_CLUSTER = RegistryHelper.makeBiomeTagForFeature("tardis_root_cluster");
    public static TagKey<Block> DIAGONAL_COMPAT_WALLS = RegistryHelper.makeBlockTag("diagonalwalls", "non_diagonal_walls");
    public static TagKey<Block> DIAGONAL_COMPAT_GLASS = RegistryHelper.makeBlockTag("diagonalwalls", "non_diagonal_windows");
    public static TagKey<Item> CURIOS_HEAD = RegistryHelper.makeItemTag("curios", "timelord_sight");
    public static TagKey<Item> TRINKETS_HEAD = RegistryHelper.makeItemTag("trinkets", "head/hat");
    public static TagKey<Item> TRINKETS_FACE = RegistryHelper.makeItemTag("trinkets", "head/face");

    public static void init() {
    }
}