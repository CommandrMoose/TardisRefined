package whocraft.tardis_refined.common.blockentity.shell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.DimensionUtil;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;

import java.util.UUID;

import static whocraft.tardis_refined.common.block.shell.ShellBaseBlock.FACING;

public class RootedShellBlockEntity extends ShellBaseBlockEntity {
    public static boolean setUpOnNextTick = false; // used in fabric MinecraftServer:getAllLevels mixin
    private boolean runSetUpOnNextTick = false;
    public RootedShellBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TRBlockEntityRegistry.ROOT_SHELL.get(), blockPos, blockState);
    }

    @Override
    public void playDoorCloseSound(boolean closeDoor) {
        //Leave blank
    }

    @Override
    public void playDoorLockedSound(boolean lockDoor) {
        //Leave blank
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, ShellBaseBlockEntity blockEntity) {
        if (runSetUpOnNextTick) {
            this.runSetUpOnNextTick = false;
            setUpTardis(blockState, level, blockPos);
        }
    }

    public void setUpTardisOnNextTick() {
        this.runSetUpOnNextTick = true;
        setUpOnNextTick = true;
    }

    /**
     * Generate the dimension and open the Root Shell
     */
    private void setUpTardis(BlockState blockState, Level level, BlockPos blockPos) {
        if (level instanceof ServerLevel serverLevel) {
            if (this.shouldSetup()) {
                //Create a Level Key with a randomised UUID
                ResourceKey<Level> generatedLevelKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TardisRefined.MODID, UUID.randomUUID().toString()));

                //Create the Level on demand which will create our capability
                ServerLevel interior = DimensionHandler.getOrCreateInterior(serverLevel, generatedLevelKey.location());

                // Set the UUID on the block entity.
                this.setTardisId(generatedLevelKey);

                TardisLevelOperator.get(interior).ifPresent(tardisLevelOperator -> {
                    if (!tardisLevelOperator.hasInitiallyGenerated()) {
                        tardisLevelOperator.setupInitialCave(serverLevel, blockState, blockPos);
                        tardisLevelOperator.getProgressionManager().addDiscoveredLevel(level.dimension());
                    }
                    TardisPilotingManager pilot = tardisLevelOperator.getPilotingManager();

                    pilot.setTargetLocation(new TardisNavLocation(blockPos, blockState.getValue(FACING), level.dimension()));
                    pilot.setCurrentLocation(new TardisNavLocation(blockPos, blockState.getValue(FACING), level.dimension()));

                    //After we setup the data and desktop, open the doors.
                    tardisLevelOperator.setDoorClosed(false);
                    serverLevel.playSound(null, blockPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, 1);
                });
            } else {
                ServerLevel tardisLevel = DimensionUtil.getLevel(getTardisId());
                TardisLevelOperator.get(tardisLevel).ifPresent(tardisLevelOperator -> {
                    //Always open the root shell doors when this method is called to ensure that the player isn't softlocked by the early return of InteractionResult that occurs if the player isn't using shears.
                    tardisLevelOperator.setDoorClosed(false);
                });
            }
        }
    }
}
