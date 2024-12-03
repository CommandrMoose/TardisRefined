package whocraft.tardis_refined.common.tardis.themes.console;

import net.minecraft.world.entity.EntityDimensions;
import org.joml.Vector3f;
import whocraft.tardis_refined.registry.TRControlRegistry;
import whocraft.tardis_refined.common.tardis.control.ControlSpecification;

public class VictorianConsoleTheme extends ConsoleThemeDetails {

    @Override
    public ControlSpecification[] getControlSpecification() {
        return new ControlSpecification[]{
                new ControlSpecification(TRControlRegistry.THROTTLE, new Vector3f(-0.55f, 0.50f, 0.61f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.X, new Vector3f(-0.58f, 0.78f, 0.11f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.Y, new Vector3f(-0.48f, 0.78f, 0.25f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.Z, new Vector3f(-0.40f, 0.77f, 0.39f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.INCREMENT, new Vector3f(-0.72f, 0.66f, 0.16f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.ROTATE, new Vector3f(-0.02f, 0.75f, -0.68f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.RANDOM, new Vector3f(-0.26f, 0.66f, -0.74f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.DOOR_TOGGLE, new Vector3f(0.83f, 0.56f, -0.16f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.MONITOR, new Vector3f(-0.6375f, 0.5625f, -0.3875f), EntityDimensions.scalable(0.25f, 0.25f)),
                new ControlSpecification(TRControlRegistry.DIMENSION, new Vector3f(0.80f, 0.50f, -0.83f), EntityDimensions.scalable(0.23f, 0.13f)),
                new ControlSpecification(TRControlRegistry.FAST_RETURN, new Vector3f(0.72f, 0.75f, -0.09f), EntityDimensions.scalable(0.06f, 0.06f)),
                new ControlSpecification(TRControlRegistry.HANDBRAKE, new Vector3f(0.32f, 0.50f, 0.85f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.FUEL, new Vector3f(-0.34f, 0.50f, 0.85f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.READOUT, new Vector3f(-0.01f, 0.75f, 0.65f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.12f, 0.54f, 0.86f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.82f, 0.54f, 0.45f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.90f, 0.51f, 0.24f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.64f, 0.51f, -0.73f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.44f, 0.81f, -0.45f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.59f, 0.81f, -0.17f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.49f, 0.81f, -0.29f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.24f, 0.69f, -0.78f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.08f, 0.59f, -0.94f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(-0.07f, 0.59f, -0.94f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.50f, 0.59f, -0.62f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.88f, 0.52f, 0.13f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.58f, 0.52f, 0.70f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.70f, 0.52f, 0.53f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.55f, 0.75f, 0.30f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.82f, 0.54f, 0.35f), EntityDimensions.scalable(0.13f, 0.13f)),
                new ControlSpecification(TRControlRegistry.GENERIC_NO_SHOW, new Vector3f(0.65f, 0.62f, 0.36f), EntityDimensions.scalable(0.13f, 0.13f))
        };
    }
}
