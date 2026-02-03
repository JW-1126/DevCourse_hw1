package service;

import static repository.Id.getLastId;
import static repository.Id.writeLastId;
import static view.Input.getAuthor;
import static view.Input.getContent;
import static view.Output.printContent;
import static view.Output.printEnd;
import static view.Output.printReadHeader;
import static view.Output.printResult;

import controller.CommandRegistry;
import repository.WiseSayingRepository;
import service.domain.WiseSaying;
import view.Output;

public class OrderManager {
    private final WiseSayingRepository wiseSayingRepository;
    private static int id;

    public OrderManager(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
        id = getLastId();
    }

    @CommandType(CommandRegistry.CREATE)
    public void create() {
        String content = getContent();
        String author = getAuthor();

        wiseSayingRepository.add(id, content, author);
        printResult(id++, "등록");
    }

    @CommandType(CommandRegistry.READ)
    public void read() {
        printReadHeader();
        wiseSayingRepository.readAll()
                .forEach(Output::printContent);
    }

    @CommandType(CommandRegistry.UPDATE)
    public void update(int index) {
        WiseSaying values = wiseSayingRepository.checkAndGetWise(index);

        printContent("명언(기존) : " + values.content());
        String newContent = getContent();

        printContent("작가(기존) : " + values.author());
        String newAuthor = getAuthor();

        wiseSayingRepository.modify(index, newContent, newAuthor);
    }

    @CommandType(CommandRegistry.DELETE)
    public void delete(int index) {
        wiseSayingRepository.delete(index);
        printResult(index, "삭제");
    }

    @CommandType(CommandRegistry.CLOSE)
    public void close() {
        printEnd();
        System.exit(0);
    }

    @CommandType(CommandRegistry.BUILD)
    public void build() {
        wiseSayingRepository.build();
        writeLastId(id);
    }
}
