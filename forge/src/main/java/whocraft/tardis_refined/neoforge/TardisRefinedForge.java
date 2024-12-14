package whocraft.tardis_refined.neoforge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import whocraft.tardis_refined.TRConfig;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.data.*;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.compat.trinkets.CuriosUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Mod(TardisRefined.MODID)
public class TardisRefinedForge {
    public TardisRefinedForge(ModContainer container) {
        TardisRefined.init();
        IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        modEventBus.addListener(this::onGatherData);

        container.registerConfig(ModConfig.Type.COMMON, TRConfig.COMMON_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, TRConfig.CLIENT_SPEC);
        container.registerConfig(ModConfig.Type.SERVER, TRConfig.SERVER_SPEC);

        if (Platform.isModLoaded("curios")) {
            CuriosUtil.init();
        }

   /*     if (ModCompatChecker.immersivePortals()) {
            if(TRConfig.COMMON.COMPATIBILITY_IP.get()) {
                ImmersivePortals.init();
                PortalsCompatForge.init();
            }
        } else {
            TardisRefined.LOGGER.info("ImmersivePortals was not detected.");
        }*/
    }

    public static Optional<IEventBus> getModEventBus(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(ModContainer::getEventBus);
    }

    public static void whenModBusAvailable(String modId, Consumer<IEventBus> busConsumer) {
        IEventBus bus = getModEventBus(modId).orElseThrow(() -> new IllegalStateException("Mod '" + modId + "' is not available!"));
        busConsumer.accept(bus);
    }

    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();
       ///TODO!! ManipulatorRecipes.registerRecipes();

        /*Resource Pack*/
        generator.addProvider(e.includeClient(), new LangProviderEnglish(generator));
        generator.addProvider(e.includeClient(), new ItemModelProvider(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new TRBlockModelProvider(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new SoundProvider(generator, existingFileHelper));
        generator.addProvider(e.includeClient(), new ParticleProvider(generator));

        /*Data Pack*/
        ProviderBlockTags blocks = generator.addProvider(e.includeServer(), new ProviderBlockTags(generator.getPackOutput(), e.getLookupProvider(), e.getExistingFileHelper()));
        generator.addProvider(e.includeServer(), new ItemTagProvider(generator.getPackOutput(), e.getLookupProvider(), blocks.contentsGetter(), existingFileHelper));
        generator.addProvider(e.includeServer(), new WorldGenProvider(generator.getPackOutput(), e.getLookupProvider()));

        generator.addProvider(e.includeServer(), new ProviderLootTable(generator.getPackOutput(), BuiltInLootTables.all(), List.of(new LootTableProvider.SubProviderEntry(ProviderLootTable.ModBlockLoot::new, LootContextParamSets.BLOCK)), e.getLookupProvider()));
        generator.addProvider(e.includeServer(), new RecipeProvider(generator, e.getLookupProvider()));
        generator.addProvider(e.includeServer(), new ConsolePatternProvider(generator));
        generator.addProvider(e.includeServer(), new DesktopProvider(generator));
        generator.addProvider(e.includeServer(), new HumProvider(generator));
        generator.addProvider(e.includeServer(), new ShellPatternProvider(generator, TardisRefined.MODID));
        generator.addProvider(e.includeServer(), new ManipulatorRecipeProvider(generator, TardisRefined.MODID));


        //Tags
        generator.addProvider(e.includeServer(), new TRBiomeTagsProvider(generator.getPackOutput(), e.getLookupProvider(), e.getExistingFileHelper()));

        generator.addProvider(e.includeServer(), new ProviderEntityTags(generator.getPackOutput(), e.getLookupProvider(), e.getExistingFileHelper()));
        generator.addProvider(e.includeServer(), new TRPoiTypeTagsProvider(generator.getPackOutput(), e.getLookupProvider(), e.getExistingFileHelper()));

    }
}