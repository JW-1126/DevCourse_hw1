import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import service.orders.Order;

public class Controller {

    private final Map<String, Order> orders;
    private final Scanner scanner = new Scanner(System.in);

    public Controller(Map<String, Order> commandManager) {
        this.orders = commandManager;
    }

    public void work() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String input = scanner.nextLine();

            // optional | null object
            Optional.ofNullable(orders.get())
                    .ifPresentOrElse(Order::process, () -> System.out.println("잘못된 명령입니다."));
            System.out.println();
        }
    }
}
