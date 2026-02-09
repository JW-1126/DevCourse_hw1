package controller;

import static controller.CommandValidator.validateInput;

import domain.WiseSaying;
import dto.InputCommand;
import dto.PageDto;
import java.util.Map;
import java.util.Scanner;
import service.CommandService;

public class InputController {
    CommandService commandService;

    public InputController(CommandService commandService) {
        this.commandService = commandService;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public void mainConsole() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String input = scanner.nextLine().trim();
            try {
                validateInput(input);
                execute(InputCommand.create(input));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void execute(InputCommand command) {
        if (command.command().equals("등록")) {
            System.out.print("명언 : ");
            String content = scanner.nextLine().trim();
            System.out.print("작가 : ");
            String author = scanner.nextLine().trim();
            int id = commandService.create(content, author);
            System.out.printf("%d번 명언이 등록되었습니다.\n", id);
        }
        if (command.command().equals("목록")) {
            Map<String, String> queryParam = command.queryParam();

            if (queryParam.containsKey("keywordType")) {
                String type = queryParam.get("keywordType");
                String keyword = queryParam.get("keyword");
                System.out.println("-------------------------");
                System.out.printf("검색타입 : %s\n검색어 : %s\n", type, keyword);
                System.out.println("-------------------------");
            }

            System.out.println("번호 / 작가 / 명언");
            System.out.println("-------------------------");
            PageDto pageDto = commandService.read(queryParam);
            pageDto.list().forEach(System.out::println);
            System.out.println("-------------------------");
            // 페이지 정보 출력
            System.out.print("페이지 : ");
            for (int i = 1; i <= pageDto.pageCount(); i++) {
                if (i == pageDto.page()) {
                    System.out.printf("[%d] | ", i);
                    continue;
                }
                System.out.printf("%d | ", i);
            }
            System.out.println();
        }
        if (command.command().equals("수정")) {
            WiseSaying values = commandService.getWiseInfo(command.getIdParam());

            System.out.println("명언(기존) : " + values.content());
            System.out.print("명언 : ");
            String newContent = scanner.nextLine().trim();

            System.out.println("작가(기존) : " + values.author());
            System.out.print("작가 : ");
            String newAuthor = scanner.nextLine().trim();

            commandService.update(command.getIdParam(), newContent, newAuthor);
        }
        if (command.command().equals("삭제")) {
            commandService.delete(command.getIdParam());
            System.out.printf("%d번 명언이 삭제되었습니다.\n", command.getIdParam());
        }
        if (command.command().equals("빌드")) {
            commandService.build();
            System.out.println("파일의 내용이 갱신되었습니다.");
        }
        if (command.command().equals("종료")) {
            System.out.println("프로그램을 종료합니다.");
            commandService.close();
        }
    }
}
