package whocraft.tardis_refined.common.tardis.themes.console;

import net.minecraft.world.entity.EntityDimensions;
import org.joml.Vector3f;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;
import whocraft.tardis_refined.registry.TRControlRegistry;

public class NukaConsoleTheme extends ConsoleThemeDetails {

    @Override
    public ControlSpecification[] getControlSpecification() {
        return new ControlSpecification[]{
                new ControlSpecification(TRControlRegistry.THROTTLE, new Vector3f(0.22f, 0.56f, -1.01f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.X, new Vector3f(-0.95f, 0.63f, -0.42f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.Y, new Vector3f(-0.99f, 0.63f, -0.33f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.Z, new Vector3f(-1.05f, 0.63f, -0.25f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.INCREMENT, new Vector3f(-1.11f, 0.56f, 0.27f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(-0.96f, 0.54f, 0.48f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.RANDOM, new Vector3f(-0.88f, 0.56f, 0.56f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.DOOR_TOGGLE, new Vector3f(-0.24f, 0.62f, 0.99f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(0.395f, 1.06f, 0.24f), EntityDimensions.scalable(0.4f, 0.25f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(-0.375f, 1.06f, 0.265f), EntityDimensions.scalable(0.4f, 0.25f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(-0.015f, 1.06f, -0.45f), EntityDimensions.scalable(0.5f, 0.25f)),
                new ControlSpecification(TRControlRegistry.DIMENSION, new Vector3f(0.205f, 0.58f, 0.99f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.FAST_RETURN, new Vector3f(0.15f, 0.83f, 0.73f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.11f, 0.67f, 0.91f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.01f, 0.67f, 0.91f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.01f, 0.61f, 0.99f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.11f, 0.61f, 0.99f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.11f, 0.57f, 1.07f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.01f, 0.57f, 1.07f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.37f, 0.57f, 1.07f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.66f, 0.67f, 0.60f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.81f, 0.72f, 0.28f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.94f, 0.61f, 0.28f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.79f, 0.54f, 0.74f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.79f, 0.77f, -0.33f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.92f, 0.67f, -0.57f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.67f, 0.67f, -0.64f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.77f, 0.56f, -0.84f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.82f, 0.56f, -0.70f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.17f, 0.79f, -0.79f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.06f, 0.79f, -0.79f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(1.06f, 0.62f, -0.24f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(1.00f, 0.59f, -0.44f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.69f, 0.70f, -0.64f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.69f, 0.70f, 0.61f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.97f, 0.32f, 0.55f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.04f, 0.56f, -1.04f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.21f, 0.59f, -0.96f), EntityDimensions.scalable(0.12f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.11f, 0.87f, 0.74f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.HANDBRAKE, new Vector3f(0.77f, 0.63f, 0.25f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.READOUT, new Vector3f(0.74f, 0.75f, -0.34f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.FUEL, new Vector3f(-0.55f, 1.06f, -0.36f), EntityDimensions.scalable(0.25f, 0.25f))
        };
    }


}
