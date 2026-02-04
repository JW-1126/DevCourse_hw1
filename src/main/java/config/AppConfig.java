package config;

import controller.CommandController;
import controller.InputHandler;
import repository.WiseSayingRepository;
import view.Input;

public class AppConfig {

    public Input input() {
        return new Input(controller());
    }

    private InputHandler controller() {
        return new InputHandler(commandManager());
    }

    private CommandController commandManager() {
        return new CommandController(wiseSayingRepository());
    }

    private WiseSayingRepository wiseSayingRepository() {
        return new WiseSayingRepository();
    }
}
