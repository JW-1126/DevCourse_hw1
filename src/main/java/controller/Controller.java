package controller;

import static controller.CommandValidator.validateInput;
import static view.Output.printError;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import service.CommandManager;
import service.CommandMapping;

public class Controller {

    private final CommandManager commandManager;
    private final Map<CommandRegistry, Method> commandMap;

    public Controller(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.commandMap = new HashMap<>();
        init();
    }

    public void work(String input) {
        try {
            validateInput(input);
            // Annotation 기반 전환
            execute(input);
        } catch (InvocationTargetException e) {
            printError((Exception) e.getTargetException());
        } catch (IllegalAccessException e) {
            printError((Exception) e.getCause());
        }
    }

    private void init() {
        Method[] methods = commandManager.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CommandMapping.class)) {
                CommandRegistry inputCommand = method.getAnnotation(CommandMapping.class).value();
                commandMap.put(inputCommand, method);
            }
        }
    }

    private void execute(String input) throws InvocationTargetException, IllegalAccessException {
        CommandRegistry inputCommand = CommandRegistry
                .getCommandRegistry(input.split("\\?")[0]).get();
        Method method = commandMap.get(inputCommand);

        Class<?>[] parameterType = method.getParameterTypes();
        if (parameterType.length == 1) {
            int args = Integer.parseInt(
                    input.split("\\?")[1].split("=")[1]
            );
            method.invoke(commandManager, args);
            return;
        }

        method.invoke(commandManager);
    }
}
