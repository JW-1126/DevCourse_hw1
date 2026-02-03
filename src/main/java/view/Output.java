package view;

public class Output {

    public static void printEnd() {
        System.out.println("프로그램을 종료합니다.");
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printResult(int index, String process) {
        System.out.printf("%d번 명언이 %s되었습니다.\n", index, process);
    }

    public static void printReadHeader() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");
    }

    public static void printContent(String content) {
        System.out.println(content);
    }
}
