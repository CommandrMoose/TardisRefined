package whocraft.tardis_refined.common.tardis.manager;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import whocraft.tardis_refined.NbtConstants;
import whocraft.tardis_refined.common.block.door.BulkHeadDoorBlock;
import whocraft.tardis_refined.common.block.door.GlobalDoorBlock;
import whocraft.tardis_refined.common.block.door.RootShellDoorBlock;
import whocraft.tardis_refined.common.block.shell.GlobalShellBlock;
import whocraft.tardis_refined.common.block.shell.RootedShellBlock;
import whocraft.tardis_refined.common.blockentity.door.BulkHeadDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.GlobalDoorBlockEntity;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.TardisArchitectureHandler;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.registry.BlockRegistry;

import java.util.List;

public class TardisInteriorManager {

    private TardisLevelOperator operator;
    private boolean isWaitingToGenerate = false;
    private boolean isGeneratingDesktop = false;
    private boolean hasGeneratedCorridors = false;
    private int interiorGenerationCooldown = 0;
    private BlockPos corridorAirlockCenter;

    // Airlock systems.
    private boolean processingWarping = false;
    private int airlockCountdownSeconds = 6;
    private int airlockTimerSeconds = 10;

    private DesktopTheme preparedTheme;

    public TardisInteriorManager(TardisLevelOperator operator) {
        this.operator = operator;
    }

    public boolean isGeneratingDesktop() {
        return this.isGeneratingDesktop;
    }

    public boolean isWaitingToGenerate() {
        return this.isWaitingToGenerate;
    }

    public int getInteriorGenerationCooldown() {
        return this.interiorGenerationCooldown / 20;
    }

    public CompoundTag saveData(CompoundTag tag) {
        tag.putBoolean(NbtConstants.TARDIS_IM_IS_WAITING_TO_GENERATE, this.isWaitingToGenerate);
        tag.putBoolean(NbtConstants.TARDIS_IM_GENERATING_DESKTOP, this.isGeneratingDesktop);
        tag.putInt(NbtConstants.TARDIS_IM_GENERATION_COOLDOWN, this.interiorGenerationCooldown);
        tag.putBoolean(NbtConstants.TARDIS_IM_GENERATED_CORRIDORS, this.hasGeneratedCorridors);

        if (this.corridorAirlockCenter != null) {
            tag.put(NbtConstants.TARDIS_IM_AIRLOCK_CENTER, NbtUtils.writeBlockPos(this.corridorAirlockCenter));
        }

        if (this.preparedTheme != null) {
            tag.putString(NbtConstants.TARDIS_IM_PREPARED_THEME, this.preparedTheme.id);
        } else {
            tag.putString(NbtConstants.TARDIS_IM_PREPARED_THEME, "");
        }

        return tag;
    }

    public void loadData(CompoundTag tag) {
        this.isWaitingToGenerate = tag.getBoolean(NbtConstants.TARDIS_IM_IS_WAITING_TO_GENERATE);
        this.isGeneratingDesktop = tag.getBoolean(NbtConstants.TARDIS_IM_GENERATING_DESKTOP);
        this.interiorGenerationCooldown = tag.getInt(NbtConstants.TARDIS_IM_GENERATION_COOLDOWN);
        this.hasGeneratedCorridors = tag.getBoolean(NbtConstants.TARDIS_IM_GENERATED_CORRIDORS);
        this.preparedTheme = TardisDesktops.getDesktopThemeById(tag.getString(NbtConstants.TARDIS_IM_PREPARED_THEME));
        this.corridorAirlockCenter = NbtUtils.readBlockPos(tag.getCompound(NbtConstants.TARDIS_IM_AIRLOCK_CENTER));
    }

    public void tick(Level level) {
        if (this.isWaitingToGenerate) {
            if (level.random.nextInt(30) == 0) {
                level.playSound(null, TardisArchitectureHandler.DESKTOP_CENTER_POS, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 5.0F + level.random.nextFloat(), level.random.nextFloat() * 0.7F + 0.3F);
            }

            if (level.random.nextInt(100) == 0) {
                level.playSound(null, TardisArchitectureHandler.DESKTOP_CENTER_POS, SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 15.0F + level.random.nextFloat(), 0.1f);
            }

            if (level.players().size() == 0) {

                this.operator.getExteriorManager().triggerShellRegenState();
                operator.setDoorClosed(true);
                generateDesktop(this.preparedTheme);

                this.isWaitingToGenerate = false;
                this.isGeneratingDesktop = true;
            }
        }

        if (this.isGeneratingDesktop) {

            if (!level.isClientSide()) {
                interiorGenerationCooldown--;
            }

            if (interiorGenerationCooldown == 0) {
                this.operator.setShellTheme( (this.operator.getExteriorManager().getCurrentTheme() != null) ? operator.getExteriorManager().getCurrentTheme() : ShellTheme.FACTORY);
                this.isGeneratingDesktop = false;
            }

            if (level.getGameTime() % 60 == 0) {
                operator.getExteriorManager().playSoundAtShell(SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 1.0F + operator.getExteriorManager().getLastKnownLocation().level.getRandom().nextFloat(), 0.1f);
            }
        }


        /// Airlock Logic

        BlockPos staticCorridorCenter = new BlockPos(1000,100,0);

        // Check if a player is in the radius of either airlock points
        if (!processingWarping) {
            if (level.getGameTime() % 20 == 0) {
                // Dynamic desktop position.
                List<LivingEntity> desktopEntities = level.getEntitiesOfClass(LivingEntity.class, new AABB(corridorAirlockCenter.north(2).west(2), corridorAirlockCenter.south(2).east(2).above(4)));
                List<LivingEntity> corridorEntities = level.getEntitiesOfClass(LivingEntity.class, new AABB(staticCorridorCenter.north(2).west(2), staticCorridorCenter.south(2).east(2).above(4)));

                if (desktopEntities.size() > 0 || corridorEntities.size() > 0) {
                    airlockCountdownSeconds--;
                    if (airlockCountdownSeconds <=0 ) {

                        this.processingWarping = true;
                        airlockCountdownSeconds = 20;
                        this.airlockTimerSeconds = 0;

                        // Lock the doors.
                        BlockPos desktopDoorPos = corridorAirlockCenter.north(2);
                        if (level.getBlockEntity(desktopDoorPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                            bulkHeadDoorBlockEntity.closeDoor(level, desktopDoorPos, level.getBlockState(desktopDoorPos));
                            level.setBlock(desktopDoorPos, level.getBlockState(desktopDoorPos).setValue(BulkHeadDoorBlock.LOCKED, true), 2);
                        }

                        BlockPos corridorDoorBlockPos = new BlockPos(1000,100,2);
                        if (level.getBlockEntity(corridorDoorBlockPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                            bulkHeadDoorBlockEntity.closeDoor(level, corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos));
                            level.setBlock(corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos).setValue(BulkHeadDoorBlock.LOCKED, true), 2);
                        }
                    }
                } else {
                    this.processingWarping = false;
                    this.airlockCountdownSeconds = 6;
                    this.airlockTimerSeconds = 0;
                }

            }
        }

        if (processingWarping) {
            if (level.getGameTime() % 20 == 0) {
                if (airlockTimerSeconds == 2) {
                    level.playSound(null, corridorAirlockCenter, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 5, 0.25f);
                    level.playSound(null, staticCorridorCenter, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 5, 0.25f);
                }

                if (airlockTimerSeconds == 10) {
                    List<LivingEntity> desktopEntities = level.getEntitiesOfClass(LivingEntity.class, new AABB(corridorAirlockCenter.north(2).west(2), corridorAirlockCenter.south(2).east(2).above(4)));
                    List<LivingEntity> corridorEntities = level.getEntitiesOfClass(LivingEntity.class, new AABB(staticCorridorCenter.north(2).west(2), staticCorridorCenter.south(2).east(2).above(4)));

                    desktopEntities.forEach(x -> {
                        Vec3 offsetPos = x.position().subtract(Vec3.atCenterOf(corridorAirlockCenter.north(2))) ;
                        x.teleportTo(1000.5f + offsetPos.x(), 100.5f + offsetPos.y(), -1.5f + offsetPos.z());
                    });

                    corridorEntities.forEach(x -> {
                        Vec3 offsetPos = x.position().subtract(Vec3.atCenterOf(new BlockPos(1000, 100, -2))) ;
                        x.teleportTo(corridorAirlockCenter.north(2).getX() + offsetPos.x() + 0.5f, corridorAirlockCenter.north(2).getY() + offsetPos.y() + 0.5f, corridorAirlockCenter.north(2).getZ() + offsetPos.z() + 0.5f);
                    });
                }

                if (airlockTimerSeconds == 14) {
                    airlockTimerSeconds = 0;
                    this.processingWarping = false;
                    this.airlockTimerSeconds = 20;
                    BlockPos desktopDoorPos = corridorAirlockCenter.north(2);
                    if (level.getBlockEntity(desktopDoorPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                        bulkHeadDoorBlockEntity.openDoor(level, desktopDoorPos, level.getBlockState(desktopDoorPos));
                        level.setBlock(desktopDoorPos, level.getBlockState(desktopDoorPos).setValue(BulkHeadDoorBlock.LOCKED, false), 2);
                    }

                    BlockPos corridorDoorBlockPos = new BlockPos(1000,100,2);
                    if (level.getBlockEntity(corridorDoorBlockPos) instanceof BulkHeadDoorBlockEntity bulkHeadDoorBlockEntity) {
                        bulkHeadDoorBlockEntity.openDoor(level, corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos));
                        level.setBlock(corridorDoorBlockPos, level.getBlockState(corridorDoorBlockPos).setValue(BulkHeadDoorBlock.LOCKED, false), 2);
                    }
                }


                airlockTimerSeconds++;
            }
        }
    }

    public void generateDesktop(DesktopTheme theme) {

        // Has generated before.
        if (this.operator.getInternalDoor() != null) {
            this.operator.getLevel().setBlockAndUpdate(this.operator.getInternalDoor().getDoorPosition(), Blocks.AIR.defaultBlockState()); // Remove the already existing door.

            if (!this.hasGeneratedCorridors) {
                if(operator.getLevel() instanceof ServerLevel serverLevel){
                    TardisArchitectureHandler.generateEssentialCorridors(serverLevel);
                    this.hasGeneratedCorridors = true;
                }
            }
        }

        if(operator.getLevel() instanceof ServerLevel serverLevel){
            TardisArchitectureHandler.generateDesktop(serverLevel, theme);
        }
    }

    public void setCorridorAirlockCenter(BlockPos center) {
        this.corridorAirlockCenter = center;
    }

    public BlockPos getCorridorAirlockCenter() {
        return this.corridorAirlockCenter;
    }

    public void prepareDesktop(DesktopTheme theme) {
        this.preparedTheme = theme;
        this.isWaitingToGenerate = true;
        this.interiorGenerationCooldown = 1200; // Make this more independent.
    }

    public void cancelDesktopChange() {
        this.preparedTheme = null;
        this.isWaitingToGenerate = false;
    }

    public void setShellTheme(ShellTheme theme) {
        BlockState state = operator.getLevel().getBlockState(operator.getInternalDoor().getDoorPosition());
        // Check if its our default global shell.

        if (state.getBlock() instanceof GlobalDoorBlock) {
            operator.getLevel().setBlock(operator.getInternalDoor().getDoorPosition(),
                    state.setValue(GlobalDoorBlock.SHELL, theme), 2);
        } else {
            if (state.getBlock() instanceof RootShellDoorBlock) {
                operator.getLevel().setBlock(operator.getInternalDoor().getDoorPosition(),
                        BlockRegistry.GLOBAL_SHELL_BLOCK.get().defaultBlockState().setValue(GlobalShellBlock.OPEN, state.getValue(RootedShellBlock.OPEN))
                                .setValue(GlobalShellBlock.FACING, state.getValue(RootedShellBlock.FACING)).setValue(GlobalShellBlock.SHELL, theme), 2);

                var shellBlockEntity = operator.getLevel().getBlockEntity(operator.getInternalDoor().getDoorPosition());
                if (shellBlockEntity instanceof GlobalDoorBlockEntity entity) {
                    operator.setInternalDoor(entity);
                }
            }
        }
    }
}
