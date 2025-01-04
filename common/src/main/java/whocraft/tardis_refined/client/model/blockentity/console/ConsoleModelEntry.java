package whocraft.tardis_refined.client.model.blockentity.console;

import whocraft.tardis_refined.patterns.ConsolePattern;

import javax.annotation.Nullable;

public class ConsoleModelEntry {

    private ConsoleUnit consoleUnit;

    public ConsoleModelEntry(ConsoleUnit consoleUnit) {
        this.consoleUnit = consoleUnit;
    }

    public ConsoleUnit getConsoleModel(@Nullable ConsolePattern consolePattern) {
        return consoleUnit;
    }

}
