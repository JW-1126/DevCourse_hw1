package controller;

import java.util.InputMismatchException;

public class CommandValidator {
    public static void validateInput(String input) {
        if (!patternValidate(input)) {
            throw new InputMismatchException("잘못된 형식의 입력입니다.");
        }

        String[] split = input.split("\\?");
        if (!supports(split[0])) {
            throw new InputMismatchException("지원하지 않는 명령어입니다.");
        }
    }

    private static boolean patternValidate(String input) {
        return input.matches("^[가-힣]{2}(\\?id=\\d+)?$");
    }

    private static boolean supports(String input) {
        return CommandRegistry.getCommandRegistry(input).isPresent();
    }
}
