package whocraft.tardis_refined;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.LowerCaseEnumTypeAdapterFactory;
import org.slf4j.Logger;
import whocraft.tardis_refined.client.TRParticles;
import whocraft.tardis_refined.common.VortexRegistry;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.common.hum.TardisHums;
import whocraft.tardis_refined.common.network.TardisNetwork;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncConsolePatterns;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncDesktops;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncHums;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncShellPatterns;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.ConsoleTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.common.world.ChunkGenerators;
import whocraft.tardis_refined.common.world.Features;
import whocraft.tardis_refined.registry.TRARSStructurePieceRegistry;
import whocraft.tardis_refined.patterns.ConsolePatterns;
import whocraft.tardis_refined.patterns.ShellPatterns;
import whocraft.tardis_refined.registry.*;

public class TardisRefined {

    public static final String MODID = "tardis_refined";
    public static final String NAME = "Tardis Refined";
    public static final String PLATFORM_ERROR = "Something has gone critically wrong with platform definitions. Please contact the mod author.";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .registerTypeHierarchyAdapter(Component.class, new Component.Serializer()).
            registerTypeHierarchyAdapter(Style.class, new Style.Serializer())
            .registerTypeAdapterFactory(new LowerCaseEnumTypeAdapterFactory()).create();

    public static void init() {

        VortexRegistry.VORTEX_DEFERRED_REGISTRY.registerToModBus();

        TRItemRegistry.TABS.registerToModBus();
        TRBlockRegistry.BLOCKS.registerToModBus();
        TRItemRegistry.ITEMS.registerToModBus();
        TREntityRegistry.ENTITY_TYPES.registerToModBus();
        TRSoundRegistry.SOUNDS.registerToModBus();
        TRBlockEntityRegistry.BLOCK_ENTITY_TYPES.registerToModBus();
        TRManipulatorRecipeResultTypes.MANIPULATOR_RECIPE_RESULT_DEFERRED_REGISTRY.registerToModBus();
        TRCraftingRecipeTypes.RECIPE_TYPE_DEFERRED_REGISTRY.registerToModBus();
        TRCraftingRecipeSerializers.RECIPE_SERIALIZERS.registerToModBus();
        TRDimensionTypes.register();
        ChunkGenerators.CHUNK_GENERATORS.registerToModBus();
        Features.FEATURES.registerToModBus();
        TRParticles.TYPES.registerToModBus();
        TRUpgrades.UPGRADE_DEFERRED_REGISTRY.registerToModBus();
        ConsoleTheme.CONSOLE_THEME_DEFERRED_REGISTRY.registerToModBus();
        ShellTheme.SHELL_THEME_DEFERRED_REGISTRY.registerToModBus();
        TRControlRegistry.CONTROL_DEFERRED_REGISTRY.registerToModBus();
        TRArgumentTypeRegistry.COMMAND_ARGUMENT_TYPES.registerToModBus();

        TRPointOfInterestTypes.POIS.registerToModBus();
        TRVillagerProfession.PROFESSIONS.registerToModBus();
        //     TRPointOfInterestTypes.registerBlockStates();

        TRTagKeys.init();
        TardisNetwork.init();
        TardisDesktops.getReloadListener().setSyncPacket(TardisNetwork.NETWORK, S2CSyncDesktops::new);
        ConsolePatterns.getReloadListener().setSyncPacket(TardisNetwork.NETWORK, S2CSyncConsolePatterns::new);
        ShellPatterns.getReloadListener().setSyncPacket(TardisNetwork.NETWORK, S2CSyncShellPatterns::new);
        TardisHums.getReloadListener().setSyncPacket(TardisNetwork.NETWORK, S2CSyncHums::new);

        registerFallbackEntries();
    }

    /**
     * Register default entries for data-driven registries. This is encapsulated in a method to call at different game load stages depending on the mod-loader
     * <br> E.g. On Forge, Console Patterns require Console Theme registry to be fully populated before the pattern can lookup a Console Theme object
     * <br> On Forge: This is called in ServerAboutToStartEvent, which is after registries are frozen, but before the server has started and before commands are registered, because commands still reference Console/Shell Themes
     * <br> On Fabric: The custom registries for Console/Shell Theme are created instantly, so there is no need to register the patterns at a specific stage.
     */
    public static void registerFallbackEntries() {
        /* Need to register a default list of entries because on Fabric Cardinal Components classloads the TardisClientData class early on, before datapack entries have been added.
        We will use these as fallback values when looking up patterns.
         */
        ConsolePatterns.registerDefaultPatterns();
        ShellPatterns.registerDefaultPatterns();
        TardisHums.registerDefaultHums();

        TRARSStructurePieceRegistry.register();
    }

}