import controller.InputController;
import repository.WiseSayingRepository;
import service.CommandService;

public class AppConfig {

    public InputController inputHandler() {
        return new InputController(commandService());
    }

    private CommandService commandService() {
        return new CommandService(wiseSayingRepository());
    }

    private WiseSayingRepository wiseSayingRepository() {
        return new WiseSayingRepository();
    }
}
