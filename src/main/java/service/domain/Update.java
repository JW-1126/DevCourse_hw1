package service.domain;

import static view.Input.getAuthor;
import static view.Input.getContent;
import static view.Output.printContent;

import repository.WiseSayingRepository;

public class Update implements Process {
    private final WiseSayingRepository wiseSayingRepository;

    public Update(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    @Override
    public void process(Integer index) {
        WiseSaying values = wiseSayingRepository.checkAndGetWise(index);

        printContent("명언(기존) : " + values.content());
        String newContent = getContent();

        printContent("작가(기존) : " + values.author());
        String newAuthor = getAuthor();

        wiseSayingRepository.modify(index, newContent, newAuthor);
    }
}
