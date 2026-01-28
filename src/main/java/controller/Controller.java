import java.util.Scanner;

public class Controller {

    private final Scanner scanner = new Scanner(System.in);

    public Controller() {

    }

    public void work() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String input = scanner.nextLine();

            System.out.println();
        }
    }
}
