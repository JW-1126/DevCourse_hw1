package repository;

import java.util.InputMismatchException;

public class WiseSaying {
    private final int id;
    private String content;
    private String author;

    private static final String regex = "^[가-힣a-zA-Z .0-9]+$";

    private WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public static WiseSaying create(int id, String content, String author) {
        validateInput(content, author);
        return new WiseSaying(id, content, author);
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }

    public WiseSayingValues getValues() {
        return new WiseSayingValues(id, content, author);
    }

    public boolean isTarget(int id) {
        return this.id == id;
    }

    public void modify(String content, String author) {
        validateInput(content, author);
        this.content = content;
        this.author = author;
    }

    private static void validateInput(String content, String author) {
        if (!content.matches(regex) || !author.matches(regex)) {
            throw new InputMismatchException();
        }
    }
}
