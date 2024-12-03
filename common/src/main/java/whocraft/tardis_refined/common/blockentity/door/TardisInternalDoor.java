package whocraft.tardis_refined.common.blockentity.door;

import whocraft.tardis_refined.common.blockentity.TardisDoorProperties;

public interface TardisInternalDoor extends TardisDoorProperties {

    boolean isMainDoor();

    void onSetMainDoor(boolean isMainDoor);

    String getID();

    void setID(String id);

}
