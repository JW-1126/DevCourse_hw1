package controller;

import static controller.CommandValidator.validateInput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {

    private final CommandManager commandManager;
    private final Scanner scanner = new Scanner(System.in);

    public Controller(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void work() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String input = scanner.nextLine();
            try {
                // 입력값에 대한 최초 검증 수행 후 전달
                validateInput(input);
                commandManager.execute(CommandInfo.of(input));
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }
}
