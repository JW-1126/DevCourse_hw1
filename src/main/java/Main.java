import controller.InputController;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        InputController inputController = appConfig.inputHandler();
        inputController.mainConsole();
    }
}
