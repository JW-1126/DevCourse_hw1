package controller;

import static controller.CommandRegistry.DELETE;
import static controller.CommandRegistry.UPDATE;

import java.util.InputMismatchException;

public class CommandValidator {
    public static void validateInput(String input) {
        if (!patternValidate(input)) {
            throw new InputMismatchException("잘못된 형식의 입력입니다.");
        }

        String[] split = input.split("\\?");
        String command = split[0];

        CommandRegistry cr = commandValidate(command);

        if ((cr.equals(DELETE) || cr.equals(UPDATE)) && split.length == 1) {
            throw new InputMismatchException("삭제/수정 명령은 대상 id값 또한 입력해야합니다.");
        }
    }

    private static boolean patternValidate(String input) {
        return input.matches("^[가-힣]{2}(\\?id=\\d+)?$");
    }

    private static CommandRegistry commandValidate(String input) {
        return CommandRegistry.getCommandRegistry(input)
                .orElseThrow(() -> new InputMismatchException("지원하지 않는 명령어입니다."));
    }
}
