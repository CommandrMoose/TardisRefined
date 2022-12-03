package whocraft.tardis_refined.common.tardis;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.blockentity.door.ITardisInternalDoor;

import java.util.Iterator;
import java.util.Optional;

// Responsible for all the tedious generation of the desktop;
public class TardisArchitectureHandler {

    public static final BlockPos DESKTOP_CENTER_POS = new BlockPos(0, 100, 0);
    public static final BlockPos CORRIDOR_ENTRY_POS = new BlockPos(1000, 100, 0);

    public static void generateDesktop(ServerLevel operator, DesktopTheme theme) {
        TardisRefined.LOGGER.debug(String.format("Attempting to generate desktop theme: %s for TARDIS.", theme.name));

        // Fill the area out.
        BlockPos corner = new BlockPos(DESKTOP_CENTER_POS.getX() - 100, operator.getMinBuildHeight() + 75, DESKTOP_CENTER_POS.getZ() - 100);
        BlockPos farCorner = new BlockPos(DESKTOP_CENTER_POS.getX() + 100, operator.getMaxBuildHeight() -75, DESKTOP_CENTER_POS.getZ() + 100);

        for (Iterator<BlockPos> iterator = BlockPos.betweenClosed(corner, farCorner).iterator(); iterator.hasNext();) {
            BlockPos pos = iterator.next();

            operator.setBlock(pos, Blocks.STONE.defaultBlockState(),1);
        }


        Optional<StructureTemplate> structureNBT = operator.getLevel().getStructureManager().get(theme.resourceLocation);
        structureNBT.ifPresent(structure -> {
            BlockPos offsetPosition = calculateArcOffset(structure, DESKTOP_CENTER_POS);
            structure.placeInWorld(operator.getLevel(), offsetPosition, offsetPosition, new StructurePlaceSettings(), operator.getLevel().random, 3);

            // Assign the door from the created structure.
            setInteriorDoorFromStructure(structure, operator);
        });
    }

    public static void generateEssentialCorridors(ServerLevel operator) {
        // Generate corridor hub
        Optional<StructureTemplate> structureNBT = operator.getLevel().getStructureManager().get(new ResourceLocation(TardisRefined.MODID, "corridors/corridor_hub_roomless"));
        structureNBT.ifPresent(structure -> {
            BlockPos offsetPosition = new BlockPos(13, 28, 5);
            structure.placeInWorld(operator.getLevel(), CORRIDOR_ENTRY_POS.subtract(offsetPosition), CORRIDOR_ENTRY_POS.subtract(offsetPosition), new StructurePlaceSettings(), operator.getLevel().random, 3);
        });

        generateArsTree(operator);

        // Generate workshop.
        structureNBT = operator.getLevel().getStructureManager().get(new ResourceLocation(TardisRefined.MODID, "rooms/workshop"));
        structureNBT.ifPresent(structure -> {
            BlockPos position = new BlockPos(977,99,9);
            structure.placeInWorld(operator.getLevel(), position, position, new StructurePlaceSettings(), operator.getLevel().random, 3);
        });

    }

    public static void generateArsTree(ServerLevel level) {
        Optional<StructureTemplate> structureNBT = level.getLevel().getStructureManager().get(new ResourceLocation(TardisRefined.MODID, "rooms/room_ars_stage_one"));
        structureNBT.ifPresent(structure -> {
            BlockPos position = new BlockPos(1011,97,3);
            structure.placeInWorld(level.getLevel(), position, position, new StructurePlaceSettings(), level.getLevel().random, 3);
        });
    }

    public static void setInteriorDoorFromStructure(StructureTemplate template, ServerLevel level) {

        BlockPos minPos = calculateArcOffset(template, DESKTOP_CENTER_POS);
        BlockPos maxPos = new BlockPos(minPos.getX() + template.getSize().getX(),
                minPos.getY() + template.getSize().getY(),
                minPos.getZ() + template.getSize().getZ()
                );

        for (BlockPos pos : BlockPos.betweenClosed(minPos, maxPos)) {
            if (level.getBlockEntity(pos) instanceof ITardisInternalDoor internalDoor) {
                TardisLevelOperator.get(level).ifPresent(cap -> cap.setInternalDoor(internalDoor));
                return;
            }
        }
    }

    public static void generateDesktop(TardisLevelOperator operator, DesktopTheme theme) {
        if(operator.getLevel() instanceof ServerLevel serverLevel){
            generateDesktop(serverLevel, theme);
        }
    }

    public static BlockPos calculateArcOffset(StructureTemplate structureTemplate, BlockPos centerPos) {
        return new BlockPos(centerPos.getX() - structureTemplate.getSize().getX() / 2, centerPos.getY() - structureTemplate.getSize().getY()  / 2,centerPos.getZ() - structureTemplate.getSize().getZ()  / 2);
    }
    
}
