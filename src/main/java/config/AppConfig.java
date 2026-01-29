package config;

import static controller.CommandRegistry.BUILD;
import static controller.CommandRegistry.CLOSE;
import static controller.CommandRegistry.CREATE;
import static controller.CommandRegistry.DELETE;
import static controller.CommandRegistry.READ;
import static controller.CommandRegistry.UPDATE;

import controller.CommandManager;
import controller.CommandRegistry;
import controller.Controller;
import java.util.HashMap;
import java.util.Map;
import repository.WiseSayingRepository;
import service.domain.Build;
import service.domain.Close;
import service.domain.Create;
import service.domain.Delete;
import service.domain.Process;
import service.domain.Read;
import service.domain.Update;
import view.Input;

public class AppConfig {

    public Input input() {
        return new Input(controller());
    }

    private Controller controller() {
        return new Controller(commandManager());
    }

    private CommandManager commandManager() {
        return new CommandManager(orders());
    }

    private Map<CommandRegistry, Process> orders() {
        WiseSayingRepository ws = wiseSayingRepository();
        Map<CommandRegistry, Process> order = new HashMap<>();
        order.put(CREATE, new Create(ws));
        order.put(DELETE, new Delete(ws));
        order.put(UPDATE, new Update(ws));
        order.put(READ, new Read(ws));
        order.put(BUILD, new Build(ws));
        order.put(CLOSE, new Close());
        return order;
    }

    private WiseSayingRepository wiseSayingRepository() {
        return new WiseSayingRepository();
    }
}
