package controller;

import static controller.CommandValidator.validateInput;
import static view.Output.printError;

import controller.CommandController.CommandMapping;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private final CommandController commandController;
    private final Map<CommandRegistry, Method> commandMap;

    public InputHandler(CommandController commandController) {
        this.commandController = commandController;
        this.commandMap = new HashMap<>();
        init();
    }

    public void work(String input) {
        try {
            // 입력값 파싱 & 각 변수에 저장해서 활용
            validateInput(input);
            execute(input);
        } catch (InvocationTargetException e) {
            printError((Exception) e.getTargetException());
        } catch (IllegalAccessException e) {
            printError((Exception) e.getCause());
        }
    }

    private void init() {
        Method[] methods = commandController.getClass().getDeclaredMethods();
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
            method.invoke(commandController, args);
            return;
        }

        method.invoke(commandController);
    }
}
