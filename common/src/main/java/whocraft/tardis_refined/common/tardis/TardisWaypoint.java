package whocraft.tardis_refined.common.tardis;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class TardisWaypoint {

    private final UUID id;
    private TardisNavLocation location;

    public TardisWaypoint(TardisNavLocation location) {
        this.id = UUID.randomUUID();
        this.location = location;
    }

    public TardisWaypoint(UUID id, TardisNavLocation location) {
        this.id = id;
        this.location = location;
    }

    public static TardisWaypoint deserialise(CompoundTag tag) {
        TardisNavLocation loc = TardisNavLocation.deserialize(tag.getCompound("location"));
        UUID id = tag.getUUID("id");
        return new TardisWaypoint(id, loc);
    }

    public UUID getId() {
        return id;
    }

    public TardisNavLocation getLocation() {
        return location;
    }

    public String getName() {
        return location.getName();
    }


    public void setLocation(TardisNavLocation location) {
        this.location = location;
    }

    public CompoundTag serialise() {
        CompoundTag tag = new CompoundTag();
        tag.put("location", this.location.serialise());
        tag.putUUID("id", this.id);

        return tag;
    }
}
