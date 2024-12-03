package whocraft.tardis_refined.compat.create;

import com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours;
import com.simibubi.create.content.redstone.displayLink.DisplayBehaviour;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.valkyrienskies.VSHelper;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.ArrayList;

import static whocraft.tardis_refined.registry.TRBlockEntityRegistry.FLIGHT_DETECTOR;


public class CreateIntergrations {

    private static ArrayList<QuickOneLineDisplaySource.TardisInfo> tardisInfos = new ArrayList<>();
    private static ArrayList<TardisNavLocationDisplaySource.TardisNavInfo> tardisNavInfos = new ArrayList<>();

    public static void init() {

        // Register big data
        DisplayBehaviour tardisOverallData = AllDisplayBehaviours.register(new ResourceLocation(TardisRefined.MODID, "tardis_bigdata"), new TardisDisplaySource());
        AllDisplayBehaviours.assignBlockEntity(tardisOverallData, FLIGHT_DETECTOR.get());


        // Fuel
        registerBehaviour(new QuickOneLineDisplaySource.TardisInfo() {
            @Override
            public MutableComponent provideInfo(TardisLevelOperator tardisLevelOperator) {
                return Component.translatable(ModMessages.FUEL).append(String.valueOf((Math.round((tardisLevelOperator.getPilotingManager().getFuelPercentage() * 100))))).append("%");
            }

            @Override
            public ResourceLocation getId() {
                return new ResourceLocation(TardisRefined.MODID, "fuel");
            }
        });

        // Door Status
        registerBehaviour(new QuickOneLineDisplaySource.TardisInfo() {
            @Override
            public MutableComponent provideInfo(TardisLevelOperator tardisLevelOperator) {
                return Component.translatable(ModMessages.DOOR_STATUS, String.valueOf(tardisLevelOperator.getInternalDoor().isOpen()));
            }

            @Override
            public ResourceLocation getId() {
                return new ResourceLocation(TardisRefined.MODID, "door");
            }
        });

        // Door Status
        registerBehaviour(new QuickOneLineDisplaySource.TardisInfo() {
            @Override
            public MutableComponent provideInfo(TardisLevelOperator tardisLevelOperator) {
                return Component.translatable(ModMessages.LOCK_STATUS, String.valueOf(tardisLevelOperator.getExteriorManager().locked()));
            }

            @Override
            public ResourceLocation getId() {
                return new ResourceLocation(TardisRefined.MODID, "locked");
            }
        });

        // Register GPS
        registerBehaviour(new TardisNavLocationDisplaySource.TardisNavInfo() {
            @Override
            public TardisNavLocation provideInfo(TardisLevelOperator tardisLevelOperator) {
                TardisNavLocation currentLoc = tardisLevelOperator.getPilotingManager().getCurrentLocation();
                if (ModCompatChecker.valkyrienSkies()) {
                    currentLoc = VSHelper.toWorldLocation(currentLoc);
                }
                return currentLoc;
            }

            @Override
            public ResourceLocation getId() {
                return new ResourceLocation(TardisRefined.MODID, "gps");
            }
        });

        // Register GPS
        registerBehaviour(new TardisNavLocationDisplaySource.TardisNavInfo() {
            @Override
            public TardisNavLocation provideInfo(TardisLevelOperator tardisLevelOperator) {
                return tardisLevelOperator.getPilotingManager().getTargetLocation();
            }

            @Override
            public ResourceLocation getId() {
                return new ResourceLocation(TardisRefined.MODID, "destination");
            }
        });

        //Register all
        for (QuickOneLineDisplaySource.TardisInfo tardisInfo : tardisInfos) {
            AllDisplayBehaviours.assignBlockEntity(AllDisplayBehaviours.register(tardisInfo.getId(), new QuickOneLineDisplaySource(tardisInfo)), FLIGHT_DETECTOR.get());
        }

        for (TardisNavLocationDisplaySource.TardisNavInfo tardisInfo : tardisNavInfos) {
            AllDisplayBehaviours.assignBlockEntity(AllDisplayBehaviours.register(tardisInfo.getId(), new TardisNavLocationDisplaySource(tardisInfo)), FLIGHT_DETECTOR.get());
        }

    }

    public static void registerBehaviour(QuickOneLineDisplaySource.TardisInfo displayBehaviour) {
        tardisInfos.add(displayBehaviour);
    }

    public static void registerBehaviour(TardisNavLocationDisplaySource.TardisNavInfo displayBehaviour) {
        tardisNavInfos.add(displayBehaviour);
    }


}
