package controller;

import static repository.Id.getLastId;
import static repository.Id.writeLastId;
import static view.Input.getAuthor;
import static view.Input.getContent;
import static view.Output.printContent;
import static view.Output.printEnd;
import static view.Output.printReadHeader;
import static view.Output.printResult;

import domain.WiseSaying;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import repository.WiseSayingRepository;
import view.Output;

public class CommandController {
    private final WiseSayingRepository wiseSayingRepository;
    private static int id;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CommandMapping {
        CommandRegistry value();
    }

    public CommandController(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
        id = getLastId();
    }

    @CommandMapping(CommandRegistry.CREATE)
    public void create() {
        String content = getContent();
        String author = getAuthor();

        wiseSayingRepository.add(id, content, author);
        printResult(id++, "등록");
    }

    @CommandMapping(CommandRegistry.READ)
    public void read() {
        printReadHeader();
        wiseSayingRepository.readAll()
                .forEach(Output::printContent);
    }

    @CommandMapping(CommandRegistry.UPDATE)
    public void update(int index) {
        WiseSaying values = wiseSayingRepository.checkAndGetWise(index);

        printContent("명언(기존) : " + values.content());
        String newContent = getContent();

        printContent("작가(기존) : " + values.author());
        String newAuthor = getAuthor();

        wiseSayingRepository.modify(index, newContent, newAuthor);
    }

    @CommandMapping(CommandRegistry.DELETE)
    public void delete(int index) {
        wiseSayingRepository.delete(index);
        printResult(index, "삭제");
    }

    @CommandMapping(CommandRegistry.BUILD)
    public void build() {
        wiseSayingRepository.build();
        writeLastId(id);
    }

    @CommandMapping(CommandRegistry.CLOSE)
    public void close() {
        build();
        printEnd();
        System.exit(0);
    }
}
