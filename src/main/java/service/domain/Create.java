package service.domain;

import static repository.Id.getLastId;
import static view.Input.getAuthor;
import static view.Input.getContent;
import static view.Output.printResult;

import repository.WiseSayingRepository;

public class Create implements Process {
    private final WiseSayingRepository wiseSayingRepository;
    public static int id;

    public Create(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
        id = getLastId();
    }

    @Override
    public void process(Integer commandIndex) {
        String content = getContent();
        String author = getAuthor();

        wiseSayingRepository.add(id, content, author);
        printResult(id++, "등록");
    }
}
