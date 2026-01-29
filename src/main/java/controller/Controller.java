package controller;

import static controller.CommandValidator.validateInput;
import static view.Output.printError;

public class Controller {

    private final CommandManager commandManager;

    public Controller(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void work(String input) {
        try {
            // 입력값에 대한 최초 검증 수행 후 전달
            validateInput(input);
            commandManager.execute(CommandInfo.of(input));
        } catch (RuntimeException e) {
            printError(e);
        }
    }
}
