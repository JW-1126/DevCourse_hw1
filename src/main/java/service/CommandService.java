package service;

import static repository.Id.getLastId;
import static repository.Id.writeLastId;

import domain.WiseSaying;
import java.util.List;
import repository.WiseSayingRepository;

public class CommandService {
    private final WiseSayingRepository wiseSayingRepository;
    private static int id;

    public CommandService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
        id = getLastId();
    }

    public int create(String content, String author) {
        wiseSayingRepository.save(id, content, author);
        return id++;
    }

    public List<String> read() {
        return wiseSayingRepository.readAll();
    }

    public WiseSaying getWiseInfo(int index) {
        return wiseSayingRepository.checkAndGetWise(index);
    }

    public void update(int index, String newContent, String newAuthor) {
        wiseSayingRepository.modify(index, newContent, newAuthor);
    }

    public void delete(int index) {
        wiseSayingRepository.delete(index);
    }

    public void build() {
        wiseSayingRepository.build();
        writeLastId(id);
    }

    public void close() {
        build();
        System.exit(0);
    }
}
