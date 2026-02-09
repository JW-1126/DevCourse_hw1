package controller;

import java.util.Arrays;
import java.util.InputMismatchException;

public class CommandValidator {
    public static final String[] COMMANDS
            = {"등록", "목록", "수정", "삭제", "빌드", "종료"};

    public static void validateInput(String input) {
        if (!patternValidate(input)) {
            throw new InputMismatchException("잘못된 형식의 입력입니다.");
        }

        String[] split = input.split("\\?");
        String command = split[0];

        commandValidate(command);

        if ((command.equals("삭제") || command.equals("수정")) && split.length == 1) {
            throw new InputMismatchException("삭제/수정 명령은 대상 id값 또한 입력해야합니다.");
        }
    }

    private static boolean patternValidate(String input) {
        return input.matches(
                "^[가-힣]{2}(?:\\?(?:id=\\d+|page=\\d+|keywordType=(?:author|keyword)&keyword=[a-zA-Z가-힣0-9]+))?$");
    }

    private static void commandValidate(String input) {
        Arrays.stream(COMMANDS)
                .filter(command -> command.equals(input))
                .findFirst()
                .orElseThrow(() -> new InputMismatchException("지원하지 않는 명령어입니다."));
    }
}
