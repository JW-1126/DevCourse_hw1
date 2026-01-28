package controller;

import java.util.Map;
import service.domain.Process;

public class CommandManager {
    private final Map<CommandRegistry, Process> orders;

    public CommandManager(Map<CommandRegistry, Process> orders) {
        this.orders = orders;
    }

    public void execute(CommandInfo command) {
        Process process = orders.get(command.commandRegistry());
        process.process(command.index());
    }
}
