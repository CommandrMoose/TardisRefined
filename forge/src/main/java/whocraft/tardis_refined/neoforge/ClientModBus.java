package whocraft.tardis_refined.neoforge;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.*;
import whocraft.tardis_refined.client.model.pallidium.ModelLayerManager;
import whocraft.tardis_refined.client.neoforge.ModelRegistryImpl;
import whocraft.tardis_refined.client.particle.ParticleGallifrey;
import whocraft.tardis_refined.client.renderer.blockentity.RootPlantRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.console.GlobalConsoleRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.device.ArtronPillarRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.device.AstralManipulatorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.device.ConsoleConfigurationRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.door.BulkHeadDoorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.door.GlobalDoorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.door.RootShellDoorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.life.ArsEggRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.life.EyeRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.shell.GlobalShellRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.shell.RootShellRenderer;
import whocraft.tardis_refined.client.renderer.entity.ControlEntityRenderer;
import whocraft.tardis_refined.common.items.DimensionSamplerItem;
import whocraft.tardis_refined.mixin.forge.ReloadableResourceManagerMixin;
import whocraft.tardis_refined.registry.RegistrySupplier;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;
import whocraft.tardis_refined.registry.TREntityRegistry;
import whocraft.tardis_refined.registry.TRItemRegistry;

import java.io.IOException;
import java.util.List;

@Mod.EventBusSubscriber(modid = TardisRefined.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBus {

    @SubscribeEvent
    public static void onItemColors(RegisterColorHandlersEvent.Item item) {
        item.register(TRItemColouring.SCREWDRIVER_COLORS, TRItemRegistry.SCREWDRIVER.get());
        item.register(TRItemColouring.SAMPLE_COLORS, TRItemRegistry.TEST_TUBE.get());
    }

    @SubscribeEvent
    public static void onItemColors(RegisterShadersEvent registerShadersEvent) throws IOException {
        registerShadersEvent.registerShader(new ShaderInstance(registerShadersEvent.getResourceProvider(), new ResourceLocation(TardisRefined.MODID, "nivis"), DefaultVertexFormat.NEW_ENTITY), (e) -> TRShaders.SNOW_SHADER = e);
        registerShadersEvent.registerShader(new ShaderInstance(registerShadersEvent.getResourceProvider(), new ResourceLocation(TardisRefined.MODID, "glow_shader"), DefaultVertexFormat.NEW_ENTITY), (e) -> TRShaders.GLOW_SHADER = e);
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        Minecraft mc = Minecraft.getInstance();
        List<PreparableReloadListener> listeners = ((ReloadableResourceManagerMixin) mc.getResourceManager()).getListeners();
        int idx = listeners.indexOf(mc.getEntityModels());
        listeners.add(idx + 1, new ModelLayerManager());
    }

    @SubscribeEvent
    public static void keyMapping(RegisterKeyMappingsEvent event) {
        event.register(TRKeybinds.EXIT_EXTERIOR_VIEW);
    }

    @SubscribeEvent
    public static void onBuildTabsContent(BuildCreativeModeTabContentsEvent event) {

        if (event.getTab() == TRItemRegistry.MAIN_TAB.get()) {
            for (RegistrySupplier<Item> item : TRItemRegistry.TAB_ITEMS.stream().toList()) {
                event.accept(item.get());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelRegistry.init();
        ModelRegistryImpl.register(event);
    }

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ParticleEngine particleEngine = mc.particleEngine;
        particleEngine.register(TRParticles.GALLIFREY.get(), (ParticleEngine.SpriteParticleRegistration) (ParticleGallifrey.Provider::new));
        particleEngine.register(TRParticles.ARS_LEAVES.get(), (ParticleEngine.SpriteParticleRegistration) (ParticleGallifrey.ARSVinesParticle::new));
    }


    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(TRBlockEntityRegistry.ROOT_PLANT.get(), RootPlantRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.ROOT_SHELL.get(), RootShellRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.ROOT_SHELL_DOOR.get(), RootShellDoorRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.GLOBAL_SHELL_BLOCK.get(), GlobalShellRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.GLOBAL_DOOR_BLOCK.get(), GlobalDoorRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.GLOBAL_CONSOLE_BLOCK.get(), GlobalConsoleRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.ARS_EGG.get(), ArsEggRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.THE_EYE.get(), EyeRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.BULK_HEAD_DOOR.get(), BulkHeadDoorRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.CONSOLE_CONFIGURATION.get(), ConsoleConfigurationRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.ASTRAL_MANIPULATOR.get(), AstralManipulatorRenderer::new);
        BlockEntityRenderers.register(TRBlockEntityRegistry.ARTRON_PILLAR.get(), ArtronPillarRenderer::new);

        EntityRenderers.register(TREntityRegistry.CONTROL_ENTITY.get(), ControlEntityRenderer::new);

        ItemProperties.register(TRItemRegistry.TEST_TUBE.get(), new ResourceLocation(TardisRefined.MODID, "is_sampled"), (itemStack, clientLevel, livingEntity, i) -> DimensionSamplerItem.hasDimAtAll(itemStack) ? 1 : 0);

    }


}
