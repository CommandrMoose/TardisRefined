package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.TardisClientData;
import whocraft.tardis_refined.common.VortexRegistry;
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
    private ResourceLocation vortex = VortexRegistry.FLOW.getId();
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

    public ResourceLocation getVortex() {
        return vortex;
    }

    public void setVortex(ResourceLocation vortex) {
        this.vortex = vortex;
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
        shellInfo.putString("vortex", vortex.toString());
        baseTag.put("aesthetic", aestheticTag);

        return baseTag;
    }

    @Override
    public void loadData(CompoundTag tag) {
        boolean needsDataFixed = false;

        if (tag.contains("aesthetic")) {
            CompoundTag aestheticTag = tag.getCompound("aesthetic");

            if (aestheticTag.contains("vortex")) {
                this.vortex = new ResourceLocation(aestheticTag.getString("vortex"));
            }

            // Shell
            if (aestheticTag.contains("shell")) {
                CompoundTag shellInfo = aestheticTag.getCompound("shell");

                if (shellInfo.contains(NbtConstants.TARDIS_EXT_CURRENT_THEME)) {
                    ResourceLocation themeId = new ResourceLocation(shellInfo.getString(NbtConstants.TARDIS_EXT_CURRENT_THEME));
                    ShellTheme theme = ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.get(themeId);

                    if (theme == null) {
                        TardisRefined.LOGGER.info("The shell theme: {} does not exist! Resetting Shell Theme & Pattern for {}", themeId, tardisOperator.getLevel().dimension());
                        needsDataFixed = true;
                        this.shellTheme = ShellTheme.FACTORY.getId();
                        TardisClientData clientData = tardisOperator.tardisClientData();
                        clientData.setShellTheme(shellTheme);
                        clientData.sync();
                    } else {
                        this.shellTheme = themeId;
                    }
                }

                if (shellInfo.contains(NbtConstants.TARDIS_EXT_CURRENT_PATTERN) && !needsDataFixed) {
                    ResourceLocation currentPattern = new ResourceLocation(shellInfo.getString(NbtConstants.TARDIS_EXT_CURRENT_PATTERN));

                    if (ShellPatterns.doesPatternExist(shellTheme, currentPattern)) {
                        this.shellPattern = ShellPatterns.getPatternOrDefault(shellTheme, currentPattern);
                        TardisClientData clientData = tardisOperator.tardisClientData();
                        clientData.setShellPattern(shellPattern.id());
                        clientData.sync();
                    }
                }
            }
        }

        if (this.shellTheme == null) {
            this.shellTheme = ShellTheme.FACTORY.getId();
        }

        if (this.shellPattern == null || needsDataFixed) {
            this.shellPattern = ShellPatterns.DEFAULT;
        }
    }


}
