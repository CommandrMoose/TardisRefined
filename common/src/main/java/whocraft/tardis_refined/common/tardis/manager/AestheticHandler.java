package whocraft.tardis_refined.common.tardis.manager;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.NbtConstants;
import whocraft.tardis_refined.patterns.ShellPattern;
import whocraft.tardis_refined.patterns.ShellPatterns;

// #PimpMyTimeship
public class AestheticHandler extends BaseHandler {

    private final TardisLevelOperator tardisOperator;

    // Shell
    private ResourceLocation shellTheme = ShellTheme.HALF_BAKED.getId();
    private ShellPattern shellPattern = ShellPatterns.DEFAULT;


    public AestheticHandler(TardisLevelOperator tardisLevelOperator) {
        super();
        this.tardisOperator = tardisLevelOperator;
    }


    public ShellPattern shellPattern() {
        return shellPattern;
    }

    public void setShellPattern(ShellPattern shellPattern) {
        this.shellPattern = shellPattern;
    }

    public ResourceLocation getShellTheme() {
        if (shellTheme.getNamespace().contains("minecraft")) {
            return ShellTheme.HALF_BAKED.getId();
        }
        return shellTheme;
    }


    /**
     * Sets the shell theme ID for the Exterior Shell Block
     *
     * @param theme        - the Shell Theme ID
     * @param shellPattern - the Shell Theme Pattern
     */
    public boolean setShellTheme(ResourceLocation theme, ResourceLocation shellPattern, TardisNavLocation tardisNavLocation) {
        this.setShellPattern(ShellPatterns.getPatternOrDefault(theme, shellPattern));
        this.shellTheme = theme;

        if (tardisNavLocation == null)
            return false;

        BlockPos lastKnownLocationPosition = tardisNavLocation.getPosition();
        ServerLevel lastKnownLocationLevel = tardisNavLocation.getLevel();
        //Copy over important data such as Tardis ID to the internal door and exterior shell
        updateShellBlock(theme, shellPattern, lastKnownLocationLevel, lastKnownLocationPosition);
        updateInteriorDoors(theme, shellPattern);

        return true;
    }

    private void updateInteriorDoors(ResourceLocation theme, ResourceLocation shellPattern) {
        if (tardisOperator.getInternalDoor() != null) {
            BlockPos internalDoorPos = tardisOperator.getInternalDoor().getDoorPosition();
            BlockEntity blockEntity = tardisOperator.getLevel().getBlockEntity(internalDoorPos);

            if (blockEntity instanceof GlobalDoorBlockEntity doorBlockEntity) {
                doorBlockEntity.setShellTheme(theme);
                doorBlockEntity.setPattern(ShellPatterns.getPatternOrDefault(shellTheme, shellPattern));
                tardisOperator.setInternalDoor(doorBlockEntity);
                doorBlockEntity.sendUpdates();
            }
        }
    }

    private void updateShellBlock(ResourceLocation theme, ResourceLocation shellPattern, ServerLevel lastKnownLocationLevel, BlockPos lastKnownLocationPosition) {
        var shellBlockEntity = lastKnownLocationLevel.getBlockEntity(lastKnownLocationPosition);
        if (shellBlockEntity instanceof GlobalShellBlockEntity entity) {
            entity.setTardisId(tardisOperator.getLevel().dimension());
            entity.setShellTheme(theme);
            entity.setPattern(ShellPatterns.getPatternOrDefault(theme, shellPattern));
            entity.sendUpdates();
            entity.setChanged();
        }
    }

    @Override
    public CompoundTag saveData(CompoundTag baseTag) {
        CompoundTag aestheticTag = new CompoundTag();

        // Shell
        CompoundTag shellInfo = new CompoundTag();
        if (this.shellTheme != null) {
            shellInfo.putString(NbtConstants.TARDIS_EXT_CURRENT_THEME, shellTheme.toString());
        }
        if (this.shellPattern != null) {
            shellInfo.putString(NbtConstants.TARDIS_EXT_CURRENT_PATTERN, shellPattern.id().toString());
        }


        aestheticTag.put("shell", shellInfo);
        baseTag.put("aesthetic", aestheticTag);

        return baseTag;
    }

    @Override
    public void loadData(CompoundTag tag) {
        if (tag.contains("aesthetic", NbtType.COMPOUND)) {
            CompoundTag aestheticTag = tag.getCompound("aesthetic");

            // Shell
            if (aestheticTag.contains("shell", NbtType.COMPOUND)) {
                CompoundTag shellInfo = aestheticTag.getCompound("shell");

                if (shellInfo.contains(NbtConstants.TARDIS_EXT_CURRENT_THEME, NbtType.STRING) && shellInfo.contains(NbtConstants.TARDIS_EXT_CURRENT_PATTERN, NbtType.STRING)) {
                    ResourceLocation themeID = new ResourceLocation(shellInfo.getString(NbtConstants.TARDIS_EXT_CURRENT_THEME));
                    this.shellTheme = themeID;
                    String patternId = shellInfo.getString(NbtConstants.TARDIS_EXT_CURRENT_PATTERN);
                    this.shellPattern = ShellPatterns.getPatternOrDefault(themeID, new ResourceLocation(patternId));
                }
            }
        }
    }

}
