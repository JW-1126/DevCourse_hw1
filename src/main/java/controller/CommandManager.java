package service;

import java.util.Map;
import service.orders.Command;

public class CommandManager {
    private final Map<String, Command> orders;

    public CommandManager(Map<String, Command> orders) {
        this.orders = orders;
    }

    public void execute(String command) {
        String[] commands = command.split("?");
    }
}
