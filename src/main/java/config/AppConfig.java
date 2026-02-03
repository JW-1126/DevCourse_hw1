package config;

import controller.Controller;
import repository.WiseSayingRepository;
import service.CommandManager;
import view.Input;

public class AppConfig {

    public Input input() {
        return new Input(controller());
    }

    private Controller controller() {
        return new Controller(commandManager());
    }

    private CommandManager commandManager() {
        return new CommandManager(wiseSayingRepository());
    }

    private WiseSayingRepository wiseSayingRepository() {
        return new WiseSayingRepository();
    }
}
