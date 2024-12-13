package whocraft.tardis_refined.common.tardis.themes.console;


import net.minecraft.world.entity.EntityDimensions;
import org.joml.Vector3f;
import whocraft.tardis_refined.registry.TRControlRegistry;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;

public class CopperConsoleTheme extends ConsoleThemeDetails {

    @Override
    public ControlSpecification[] getControlSpecification() {
        return new ControlSpecification[]{
                new ControlSpecification(TRControlRegistry.THROTTLE.get(), new Vector3f(0.11f, 0.69f, -1.26f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.X.get(), new Vector3f(0.68f, 0.97f, 0.14f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.Y.get(), new Vector3f(0.68f, 0.97f, -0.01f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.Z.get(), new Vector3f(0.68f, 0.97f, -0.17f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.INCREMENT.get(), new Vector3f(1.19f, 0.51f, -0.26f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.ROTATE.get(), new Vector3f(0.74f, 0.50f, 0.93f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.RANDOM.get(), new Vector3f(0.68f, 0.25f, -1.26f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.DOOR_TOGGLE.get(), new Vector3f(-0.20f, 0.63f, -1.26f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.MONITOR.get(), new Vector3f(0.89f, 1.39f, -0.515f), EntityDimensions.scalable(0.45f, 0.4f)),
                new ControlSpecification(TRControlRegistry.DIMENSION.get(), new Vector3f(0.35f, 0.63f, 1.11f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.FAST_RETURN.get(), new Vector3f(-0.79f, 0.53f, -0.95f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(0.71f, 0.47f, -0.91f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(1.02f, 0.63f, -0.25f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(1.23f, 0.57f, -0.00f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(1.23f, 0.57f, 0.27f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(1.23f, 0.57f, 0.54f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(1.10f, 0.69f, 0.47f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(1.04f, 0.69f, 0.79f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(0.13f, 0.82f, 0.97f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(0.09f, 0.60f, 1.19f), EntityDimensions.scalable(0.19f, 0.19f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-0.19f, 0.76f, 1.03f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-1.06f, 0.57f, 0.75f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-0.96f, 0.69f, 0.69f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-0.74f, 0.76f, 0.56f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-0.36f, 1.06f, 0.65f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-1.25f, 0.57f, 0.51f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-1.00f, 0.76f, 0.39f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-1.21f, 0.57f, 0.05f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-1.21f, 0.64f, -0.28f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW.get(), new Vector3f(-0.49f, 0.54f, -1.09f), EntityDimensions.scalable(0.12f, 0.13f)),
                new ControlSpecification(TRControlRegistry.HANDBRAKE.get(), new Vector3f(-1.20f, 0.63f, -0.57f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.READOUT.get(), new Vector3f(0.24f, 0.97f, -0.70f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.FUEL.get(), new Vector3f(0.55f, 1.00f, -0.51f), EntityDimensions.scalable(0.13f, 0.13f))
        };

    }


}
