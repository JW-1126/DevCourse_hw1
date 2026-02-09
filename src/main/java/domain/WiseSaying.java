package domain;

import java.util.InputMismatchException;

public record WiseSaying(int id, String content, String author) {

    private static final String REGEX = "^[가-힣a-zA-Z .1-9]+$";

    public static WiseSaying create(int id, String content, String author) {
        validateInput(content, author);
        return new WiseSaying(id, content, author);
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }

    public boolean isTarget(int id) {
        return this.id == id;
    }

    public boolean isContains(String type, String keyword) {
        if (type.equals("author")) {
            return author.contains(keyword);
        }
        return content.contains(keyword);
    }

    private static void validateInput(String content, String author) {
        if (!content.matches(REGEX) || !author.matches(REGEX)) {
            throw new InputMismatchException("입력 형식이 잘못되었습니다.");
        }
    }
}
