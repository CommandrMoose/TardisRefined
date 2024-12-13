package whocraft.tardis_refined.registry;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.world.chunk.ARSStructurePiece;

import java.util.ArrayList;
import java.util.List;

public class TRARSStructurePieceRegistry {

    public static List<ARSStructurePiece> CORRIDORS = new ArrayList<>();
    public static List<ARSStructurePiece> ROOMS = new ArrayList<>();

    public static void register() {
        registerCorridors();
        registerRooms();
    }

    public static void registerCorridors() {
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r135")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r206")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r296")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r356")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r415")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r418")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r464")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r606")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r836")));
        registerCorridorPiece(new ARSStructurePiece(createCorridorResourceLocation("gs_r9")));
    }

    public static void registerRooms() {
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r144")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r145")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r157")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r177")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r186")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r194")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r2")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r233")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r240")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r244")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r250")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r260")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r284")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r289")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r312")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r328")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r356")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r39")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r408")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r412")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r416")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r479")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r5")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r508")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r527")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r536")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r611")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r628")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r642")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r649")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r658")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r677")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r684")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r687")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r719")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r73")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r794")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r814")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r851")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r904")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r917")));
        registerRoomPiece(new ARSStructurePiece(createRoomResourceLocation("gs_r994")));
    }


    /**
     * Register a new corridor to be featured in corridor generation.
     *
     * @param corridorPiece ARSStructurePiece to be placed.
     */

    public static void registerCorridorPiece(ARSStructurePiece corridorPiece) {
        CORRIDORS.add(corridorPiece);
    }

    /**
     * Register a new room to be featured in corridor generation.
     *
     * @param roomPiece ARSStructurePiece to be placed.
     */

    public static void registerRoomPiece(ARSStructurePiece roomPiece) {
        ROOMS.add(roomPiece);
    }

    private static ResourceLocation createCorridorResourceLocation(String id) {
        return ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "corridors/" + id);
    }

    private static ResourceLocation createRoomResourceLocation(String id) {
        return ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "rooms/" + id);
    }


}
