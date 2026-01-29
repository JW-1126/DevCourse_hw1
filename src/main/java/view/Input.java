package view;

import controller.Controller;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;
    private final Controller controller;

    public InputView(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void mainConsole() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            controller.work(scanner.nextLine());
        }
    }
}
