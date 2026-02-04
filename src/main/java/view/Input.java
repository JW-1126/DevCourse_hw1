package view;

import controller.InputHandler;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);
    private final InputHandler inputHandler;

    public Input(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void mainConsole() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            inputHandler.work(scanner.nextLine().trim());
        }
    }

    public static String getContent() {
        System.out.print("명언 : ");
        return scanner.nextLine().trim();
    }

    public static String getAuthor() {
        System.out.print("작가 : ");
        return scanner.nextLine().trim();
    }


}
