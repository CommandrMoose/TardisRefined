package whocraft.tardis_refined.forge;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.client.ModelRegistry;
import whocraft.tardis_refined.client.forge.ModelRegistryImpl;
import whocraft.tardis_refined.client.renderer.blockentity.RootPlantRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.console.GlobalConsoleRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.device.ConsoleConfigurationRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.door.BulkHeadDoorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.door.GlobalDoorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.door.RootShellDoorRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.life.ArsEggRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.shell.GlobalShellRenderer;
import whocraft.tardis_refined.client.renderer.blockentity.shell.RootShellRenderer;
import whocraft.tardis_refined.client.renderer.entity.ControlEntityRenderer;
import whocraft.tardis_refined.registry.BlockEntityRegistry;
import whocraft.tardis_refined.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = TardisRefined.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBus {

    @SubscribeEvent
    public static void event(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelRegistry.init();
        ModelRegistryImpl.register(event);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(BlockEntityRegistry.ROOT_PLANT.get(), RootPlantRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.ROOT_SHELL.get(), RootShellRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.ROOT_SHELL_DOOR.get(), RootShellDoorRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.GLOBAL_SHELL_BLOCK.get(), GlobalShellRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.GLOBAL_DOOR_BLOCK.get(), GlobalDoorRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.GLOBAL_CONSOLE_BLOCK.get(), GlobalConsoleRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.ARS_EGG.get(), ArsEggRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.BULK_HEAD_DOOR.get(), BulkHeadDoorRenderer::new);
        BlockEntityRenderers.register(BlockEntityRegistry.CONSOLE_CONFIGURATION.get(), ConsoleConfigurationRenderer::new);

        EntityRenderers.register(EntityRegistry.CONTROL_ENTITY.get(), ControlEntityRenderer::new);
    }

}
