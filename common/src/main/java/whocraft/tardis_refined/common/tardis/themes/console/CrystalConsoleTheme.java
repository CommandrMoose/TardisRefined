package whocraft.tardis_refined.common.tardis.themes.console;

import net.minecraft.world.entity.EntityDimensions;
import org.joml.Vector3f;
import whocraft.tardis_refined.registry.TRControlRegistry;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;

public class CrystalConsoleTheme extends ConsoleThemeDetails {

    @Override
    public ControlSpecification[] getControlSpecification() {
        return new ControlSpecification[]{
                new ControlSpecification(TRControlRegistry.THROTTLE, new Vector3f(-0.735f, 1.13f, -0.45f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.X, new Vector3f(-0.70f, 0.50f, 0.93f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.Y, new Vector3f(-0.52f, 0.50f, 0.89f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.Z, new Vector3f(-0.45f, 0.50f, 1.05f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.INCREMENT, new Vector3f(-0.92f, 0.46f, 0.87f), EntityDimensions.scalable(0.12f, 0.13f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(-0.62f, 0.57f, -1.07f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.RANDOM, new Vector3f(-0.595f, 0.55f, 1.04f), EntityDimensions.scalable(0.08f, 0.08f)),
                new ControlSpecification(TRControlRegistry.DOOR_TOGGLE, new Vector3f(1.18f, 0.52f, -0.35f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(-0.0149f, 0.94f, 1.38f), EntityDimensions.scalable(0.43f, 0.43f)),
                new ControlSpecification(TRControlRegistry.DIMENSION, new Vector3f(0.65f, 0.5f, -0.42f), EntityDimensions.scalable(0.33f, 0.3f)),
                new ControlSpecification(TRControlRegistry.FAST_RETURN, new Vector3f(-0.29f, 0.39f, -1.21f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(1.11f, 0.56f, 0.11f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(1.11f, 0.56f, -0.16f), EntityDimensions.scalable(0.13f, 0.12f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.61f, 0.56f, -0.96f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.68f, 0.56f, -1.08f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.29f, 0.56f, -1.28f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.52f, 0.61f, -0.74f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-1.03f, 0.64f, -0.52f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.81f, 0.64f, -0.01f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-1.14f, 0.51f, -0.38f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.EXTERIOR_DISPLAY, new Vector3f(-0.82f, 1.07f, 0.16f), EntityDimensions.scalable(0.21f, 0.21f)),
                new ControlSpecification(TRControlRegistry.FUEL, new Vector3f(0.29f, 0.52f, 1.23f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.58f, 0.52f, 1.29f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.69f, 0.52f, 1.22f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.83f, 0.52f, 1.14f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(1.12f, 0.40f, -0.99f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(1.12f, 0.40f, -0.99f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.HANDBRAKE, new Vector3f(0.685f, 1.13f, 0.37f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.READOUT, new Vector3f(0.83f, 1.06f, -0.56f), EntityDimensions.scalable(0.13f, 0.13f))
        };
    }


}
