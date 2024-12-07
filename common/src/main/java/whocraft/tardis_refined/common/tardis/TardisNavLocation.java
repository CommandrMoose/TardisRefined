package whocraft.tardis_refined.common.tardis;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.common.util.Platform;

/**
 * TardisNavLocation
 * Co-ordinates that represent position, rotation, level and name.
 **/
public class TardisNavLocation {


    public static final TardisNavLocation ORIGIN = new TardisNavLocation(BlockPos.ZERO, Direction.NORTH, Level.OVERWORLD);

    private BlockPos position;
    private Direction direction;
    private ServerLevel level;

    private ResourceKey<Level> dimensionKey;

    private String name = "";

    /**
     * @param position  World co-ordinate
     * @param direction Rotation/Facing direction.
     * @param level     ResourceKey of the desired level.
     **/
    public TardisNavLocation(BlockPos position, Direction direction, ServerLevel level) {
        this.position = position;
        this.direction = direction;
        this.level = level;
        if (level != null) {
            this.dimensionKey = level.dimension();
        }

    }

    /**
     * <br> Alternate Constructor ONLY for static references.
     * <br> DO NOT use for logic E.g. Using methods from the Level instance
     * <br> This is because this version doesn't have a {@link Level}  reference
     *
     * @param position
     * @param direction
     * @param level
     */
    public TardisNavLocation(BlockPos position, Direction direction, ResourceKey<Level> level) {
        this.position = position;
        this.direction = direction;
        this.dimensionKey = level;
    }

    public static TardisNavLocation deserialize(CompoundTag tag) {
        TardisNavLocation loc = new TardisNavLocation(
                BlockPos.of(tag.getLong("position")),
                Direction.values()[tag.getInt("direction")],
                ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString("dimension"))));

        if (tag.contains("name"))
            loc.setName(tag.getString("name"));
        return loc;
    }

    public CompoundTag serialise() {
        CompoundTag tag = new CompoundTag();
        tag.putLong("position", this.position.asLong());
        tag.putString("dimension", this.dimensionKey.location().toString());
        tag.putInt("direction", this.direction.ordinal());
        tag.putString("name", this.name);
        return tag;
    }

    public ServerLevel getLevel() {
        if (this.level != null) {
            this.dimensionKey = this.level.dimension();
            return Platform.getServer().getLevel(dimensionKey);
        }

        return Platform.getServer().getLevel(Level.OVERWORLD);
    }

    public void setLevel(ServerLevel level) {
        this.dimensionKey = level.dimension();
        this.level = level;
    }

    public ResourceKey<Level> getDimensionKey() {
        return dimensionKey;
    }

    public void setDimensionKey(ResourceKey<Level> dimensionKey) {
        this.dimensionKey = dimensionKey;
    }

    public BlockPos getPosition() {
        return position;
    }

    public TardisNavLocation setPosition(BlockPos pos) {
        this.position = pos;
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public TardisNavLocation setDirection(Direction dir) {
        this.direction = dir;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public BlockPos setX(int x) {
        BlockPos blockPos = new BlockPos(x, position.getY(), position.getZ());
        position = blockPos;
        return position;
    }

    public BlockPos setY(int y) {
        BlockPos blockPos = new BlockPos(position.getX(), y, position.getZ());
        position = blockPos;
        return position;
    }

    public BlockPos setZ(int z) {
        BlockPos blockPos = new BlockPos(position.getX(), position.getY(), z);
        position = blockPos;
        return position;
    }

    public TardisNavLocation copy() {
        TardisNavLocation copy = new TardisNavLocation(this.position, this.direction, this.dimensionKey);
        copy.setName(this.name);
        return copy;
    }


}
