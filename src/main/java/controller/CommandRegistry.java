package controller;

import java.util.Arrays;
import java.util.Optional;

public enum CommandRegistry {
    CREATE("등록"),
    DELETE("삭제"),
    UPDATE("수정"),
    READ("목록"),
    BUILD("빌드"),
    CLOSE("종료");

    private final String inputMessage;

    CommandRegistry(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public boolean inputMatches(String input) {
        return inputMessage.equals(input);
    }

    public static Optional<CommandRegistry> getCommandRegistry(String input) {
        return Arrays.stream(CommandRegistry.values())
                .filter(cr -> cr.inputMatches(input))
                .findFirst();
    }
}
