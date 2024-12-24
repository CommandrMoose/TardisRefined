package whocraft.tardis_refined.common.tardis;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.block.door.BulkHeadDoorBlock;
import whocraft.tardis_refined.common.blockentity.door.TardisInternalDoor;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.entity.ControlEntity;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.constants.TardisDimensionConstants;
import whocraft.tardis_refined.registry.TRBlockRegistry;

import java.util.*;

import static whocraft.tardis_refined.common.tardis.manager.TardisInteriorManager.STATIC_CORRIDOR_POSITION;

// Responsible for all the tedious generation of the desktop;
public class TardisArchitectureHandler {

    public static Logger LOGGER = LogManager.getLogger("TardisRefined/TardisArchitectureHandler");

    public static final BlockPos DESKTOP_CENTER_POS = new BlockPos(0, 100, 0);
    public static final BlockPos EYE_OF_HARMONY_PLACEMENT = new BlockPos(991, 41, 31);

    public static String currentArsStage = "one";

    public static void generateDesktop(ServerLevel operator, DesktopTheme theme) {
        LOGGER.debug("Attempting to generate desktop theme: {} for TARDIS.", theme.getIdentifier());

        // Fill the area out.
        BlockPos corner = new BlockPos(TardisDimensionConstants.TARDIS_CENTER_POS.getX() - TardisDimensionConstants.DESKTOP_RADIUS, TardisDimensionConstants.TARDIS_ROOT_GENERATION_MIN_HEIGHT, TardisDimensionConstants.TARDIS_CENTER_POS.getZ() - TardisDimensionConstants.DESKTOP_RADIUS);
        BlockPos farCorner = new BlockPos(TardisDimensionConstants.TARDIS_CENTER_POS.getX() + TardisDimensionConstants.DESKTOP_RADIUS, TardisDimensionConstants.TARDIS_ROOT_GENERATION_MAX_HEIGHT, TardisDimensionConstants.TARDIS_CENTER_POS.getZ() + TardisDimensionConstants.DESKTOP_RADIUS);


        if (theme != TardisDesktops.DEFAULT_OVERGROWN_THEME) {

            for (BlockPos pos : BlockPos.betweenClosed(corner, farCorner)) {
                if (operator.getBlockState(pos) != TRBlockRegistry.FOOLS_STONE.get().defaultBlockState()) {
                    operator.setBlock(pos, TRBlockRegistry.FOOLS_STONE.get().defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }

        List<ItemEntity> itemEntities = operator.getLevel().getEntitiesOfClass(ItemEntity.class, new AABB(corner, farCorner));
        List<ControlEntity> controlEntities = operator.getLevel().getEntitiesOfClass(ControlEntity.class, new AABB(corner, farCorner));
        List<Entity> entitiesForDeath = new ArrayList<>(itemEntities);
        entitiesForDeath.addAll(controlEntities);
        entitiesForDeath.forEach(Entity::discard); //Don't teleport entities to a hard coded coordinate, that causes hanging entity out of world issues. In other cases, if another mod defines that coordinate as a safe area (possible) that will mean the entities never get killed.

        Optional<StructureTemplate> structureNBT = operator.getLevel().getStructureManager().get(theme.getStructureLocation());

        structureNBT.ifPresent(structure -> {
            BlockPos offsetPosition = calculateArcOffset(structure, DESKTOP_CENTER_POS);
            structure.placeInWorld(operator.getLevel(), offsetPosition, offsetPosition, new StructurePlaceSettings(), operator.getLevel().random, Block.UPDATE_IMMEDIATE);

            // Assign the door from the created structure.
            setInteriorDoorFromStructure(structure, operator);
            buildAirlockEntranceFromStructure(structure, operator);
        });
    }

    public static void buildAirlockEntranceFromStructure(StructureTemplate template, ServerLevel level) {
        BlockPos minPos = calculateArcOffset(template, DESKTOP_CENTER_POS);
        BlockPos maxPos = new BlockPos(minPos.getX() + template.getSize().getX(), minPos.getY() + template.getSize().getY(), minPos.getZ() + template.getSize().getZ());

        for (BlockPos pos : BlockPos.betweenClosed(minPos, maxPos)) {
            if (level.getBlockState(pos).getBlock() == TRBlockRegistry.AIR_LOCK_GENERATION_BLOCK.get()) {

                TardisLevelOperator.get(level).ifPresent(cap -> {
                    Optional<StructureTemplate> structureNBT = level.getLevel().getStructureManager().get(new ResourceLocation(TardisRefined.MODID, "corridors/airlock_entrance"));
                    structureNBT.ifPresent(structure -> {
                        BlockPos offsetPosition = new BlockPos(3, 2, 0);
                        structure.placeInWorld(level.getLevel(), pos.subtract(offsetPosition), pos.subtract(offsetPosition), new StructurePlaceSettings(), level.getLevel().random, 2);

                        cap.getInteriorManager().setCorridorAirlockCenter(pos.south(2));
                        level.setBlock(pos, level.getBlockState(pos).setValue(BulkHeadDoorBlock.LOCKED, false), 2);

                    });
                });
                return;
            }
        }
    }

    public static void generateArsTree(TardisLevelOperator tardisLevelOperator, ServerLevel level) {
        if (!currentArsStage.equals("one") && Objects.equals(tardisLevelOperator.getUpgradeHandler().getProgressLevel(), currentArsStage))
            return;

        currentArsStage = tardisLevelOperator.getUpgradeHandler().getProgressLevel();

        Optional<StructureTemplate> structureNBT = level.getLevel().getStructureManager().get(new ResourceLocation(TardisRefined.MODID, "rooms/ars/room_ars_stage_" + currentArsStage));
        structureNBT.ifPresent(structure -> {
            structure.placeInWorld(level.getLevel(), TardisDimensionConstants.ARS_TREE_PLACEMENT_POS, TardisDimensionConstants.ARS_TREE_PLACEMENT_POS, new StructurePlaceSettings(), level.getLevel().random, Block.UPDATE_ALL);
        });


    }

    public static boolean setInteriorDoorFromStructure(StructureTemplate template, ServerLevel level) {

        BlockPos minPos = calculateArcOffset(template, DESKTOP_CENTER_POS);
        BlockPos maxPos = new BlockPos(minPos.getX() + template.getSize().getX(), minPos.getY() + template.getSize().getY(), minPos.getZ() + template.getSize().getZ());

        //First set the internal door to null so that we aren't caching the previous desktop's internal door
        TardisLevelOperator.get(level).ifPresent(cap -> cap.setInternalDoor(null));

        for (BlockPos pos : BlockPos.betweenClosed(minPos, maxPos)) {
            if (level.getBlockEntity(pos) instanceof TardisInternalDoor internalDoor) {
                TardisLevelOperator.get(level).ifPresent(cap -> cap.setInternalDoor(internalDoor));
                return true;
            }
        }
        return false;
    }

    public static BlockPos calculateArcOffset(StructureTemplate structureTemplate, BlockPos centerPos) {
        return new BlockPos(centerPos.getX() - structureTemplate.getSize().getX() / 2, centerPos.getY() - structureTemplate.getSize().getY() / 2, centerPos.getZ() - structureTemplate.getSize().getZ() / 2);
    }

}
