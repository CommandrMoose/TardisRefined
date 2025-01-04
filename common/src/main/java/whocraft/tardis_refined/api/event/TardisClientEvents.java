package whocraft.tardis_refined.api.event;

import net.minecraft.client.model.geom.EntityModelSet;
import whocraft.tardis_refined.client.model.blockentity.console.ConsoleModelCollection;

public class TardisClientEvents {

    public static final Event<TardisClientEvents.SetupModels> SHELLENTRY_MODELS_SETUP = new Event<>(TardisClientEvents.SetupModels.class, listeners -> (EntityModelSet context) -> {
        for (TardisClientEvents.SetupModels listener : listeners) {
            listener.setUpShellAndInteriorModels(context);
        }
    });

    public static final Event<TardisClientEvents.SetupConsoleModels> CONSOLE_MODELS_SETUP = new Event<>(TardisClientEvents.SetupConsoleModels.class, listeners -> (ConsoleModelCollection consoleModelCollection, EntityModelSet context) -> {
        for (TardisClientEvents.SetupConsoleModels listener : listeners) {
            listener.setupConsoleModels(consoleModelCollection, context);
        }
    });

    /*Provides a safe place for addon makers to register their shell and interior door models*/
    @FunctionalInterface
    public interface SetupModels {
        void setUpShellAndInteriorModels(EntityModelSet context);
    }


    @FunctionalInterface
    public interface SetupConsoleModels {
        void setupConsoleModels(ConsoleModelCollection consoleModelCollection, EntityModelSet context);
    }


}
