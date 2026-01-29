import config.AppConfig;
import view.Input;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Input input = appConfig.input();
        input.mainConsole();
    }
}
