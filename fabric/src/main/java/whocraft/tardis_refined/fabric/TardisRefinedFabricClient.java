package whocraft.tardis_refined.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ModelRegistry;
import whocraft.tardis_refined.client.TRKeybinds;
import whocraft.tardis_refined.client.TRParticles;
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
import whocraft.tardis_refined.compat.ModCompatChecker;
import whocraft.tardis_refined.compat.portals.ImmersivePortalsClient;
import whocraft.tardis_refined.fabric.events.ModEvents;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;
import whocraft.tardis_refined.registry.TRBlockRegistry;
import whocraft.tardis_refined.registry.TREntityRegistry;
import whocraft.tardis_refined.registry.TRItemRegistry;

public class TardisRefinedFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        establishBlockEntityRenderers();
        ModelRegistry.init();
        ModEvents.addClientEvents();
        particles();
        registerEntityRenderers();

        KeyBindingHelper.registerKeyBinding(TRKeybinds.EXIT_EXTERIOR_VIEW);
        KeyBindingHelper.registerKeyBinding(TRKeybinds.TOGGLE_INFO_EXTERIOR_VIEW);
        ItemProperties.register(TRItemRegistry.TEST_TUBE.get(), new ResourceLocation(TardisRefined.MODID, "is_sampled"), (itemStack, clientLevel, livingEntity, i) -> DimensionSamplerItem.hasDimAtAll(itemStack) ? 1 : 0);

    }

    private void particles() {
        ParticleFactoryRegistry.getInstance().register(TRParticles.GALLIFREY.get(), (ParticleGallifrey.Provider::new));
        ParticleFactoryRegistry.getInstance().register(TRParticles.ARS_LEAVES.get(), (ParticleGallifrey.ARSVinesParticle::new));
    }

    private void establishBlockEntityRenderers() {
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.ROOT_PLANT.get(), RootPlantRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.ROOT_SHELL.get(), RootShellRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.ROOT_SHELL_DOOR.get(), RootShellDoorRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.GLOBAL_SHELL_BLOCK.get(), GlobalShellRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.GLOBAL_DOOR_BLOCK.get(), GlobalDoorRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.GLOBAL_CONSOLE_BLOCK.get(), GlobalConsoleRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.ARS_EGG.get(), ArsEggRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.THE_EYE.get(), EyeRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.BULK_HEAD_DOOR.get(), BulkHeadDoorRenderer::new);

        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.CONSOLE_CONFIGURATION.get(), ConsoleConfigurationRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.ASTRAL_MANIPULATOR.get(), AstralManipulatorRenderer::new);
        BlockEntityRendererRegistry.register(TRBlockEntityRegistry.ARTRON_PILLAR.get(), ArtronPillarRenderer::new);

        /*Required to Render Transparency*/
        for (Block block : TRBlockRegistry.BLOCKS.getRegistry().get()) {
            if (TRBlockRegistry.BLOCKS.getRegistry().get().getKey(block).getNamespace().contains(TardisRefined.MODID)) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
            }
        }

    }

    private void registerEntityRenderers() {
        EntityRendererRegistry.register(TREntityRegistry.CONTROL_ENTITY.get(), ControlEntityRenderer::new);

        if (ModCompatChecker.immersivePortals()) {
            ImmersivePortalsClient.doClientRenderers();
        }

    }
}
