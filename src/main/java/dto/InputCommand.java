package dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record InputCommand(String command, Map<String, String> queryParam) {

    public static InputCommand create(String input) {
        Map<String, String> queryParam = new HashMap<>();

        String[] command = input.split("\\?");
        if (command.length == 2) {
            queryParam = Arrays.stream(command[1].split("&"))
                    .map(p -> p.split("="))
                    .collect(Collectors.toMap(
                            p -> p[0],
                            p -> p[1]
                    ));
            /*
             *          String[] params = command[1].split("&");
             *          for (String param : params) {
             *              queryParam.put(param.split("=")[0], param.split("=")[1]);
             *          }
             */
        }

        return new InputCommand(command[0], queryParam);
    }

    public int getIdParam() {
        return Integer.parseInt(queryParam.get("id"));
    }
}
