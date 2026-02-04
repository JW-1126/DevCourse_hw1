package domain;

import java.util.HashMap;
import java.util.Map;

public record InputCommand(String command, Map<String, String> queryParam) {

    public static InputCommand create(String input) {
        Map<String, String> queryParam = new HashMap<>();

        String[] command = input.split("\\?");
        if (command.length == 2) {
            String[] params = command[1].split("&");
            for (String param : params) {
                queryParam.put(param.split("=")[0], param.split("=")[1]);
            }
        }

        return new InputCommand(command[0], queryParam);
    }

    public int getIdParam() {
        return Integer.parseInt(queryParam.get("id"));
    }
}
