package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.WiseSaying;
import dto.PageDto;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WiseSayingRepository {
    public static final int PAGE_SIZE = 5;
    private final List<WiseSaying> wiseSayingList;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Path FILEPATH = Path.of("file/data.json");

    public WiseSayingRepository() {
        wiseSayingList = loadRepo();
    }

    public PageDto readAll(int page) {
        List<WiseSaying> list = wiseSayingList.reversed().stream()
                .toList();
        return loadPage(page, list);
    }

    public PageDto readByKeyword(String type, String keyword, int page) {
        List<WiseSaying> list = wiseSayingList.reversed().stream()
                .filter(i -> i.isContains(type, keyword))
                .toList();
        return loadPage(page, list);
    }

    public void save(int index, String content, String author) {
        wiseSayingList.add(WiseSaying.create(index, content, author));
    }

    public void delete(int index) {
        // removeIf method 존재 refactoring 가능
        wiseSayingList.remove(checkAndGetWise(index));
    }

    public void modify(int index, String content, String author) {
        for (int i = 0; i < wiseSayingList.size(); i++) {
            WiseSaying wiseSaying = wiseSayingList.get(i);
            if (wiseSaying.id() == index) {
                wiseSayingList.set(i, WiseSaying.create(index, content, author));
            }
        }
    }

    public void build() {
        try (OutputStream out = Files.newOutputStream(FILEPATH)) {
            objectMapper.writeValue(out, wiseSayingList);
        } catch (IOException e) {
            throw new RuntimeException("저장 오류 발생. 재시도");
        }
    }

    public WiseSaying checkAndGetWise(int index) {
        return wiseSayingList.stream()
                .filter(i -> i.isTarget(index))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(index + "번 명언은 존재하지 않습니다."));
    }

    private List<WiseSaying> loadRepo() {
        try (InputStream in = Files.newInputStream(FILEPATH)) {
            if (!Files.exists(FILEPATH) || Files.size(FILEPATH) == 0) {
                Files.writeString(FILEPATH, "[]");
                return List.of();
            }
            return objectMapper.readValue(in, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PageDto loadPage(int page, List<WiseSaying> filteredList) {
        List<String> pagedList = filteredList.stream()
                .skip(PAGE_SIZE * (page - 1))
                .limit(PAGE_SIZE)
                .map(WiseSaying::toString)
                .toList();
        int pageCount = (int) Math.ceil((double) filteredList.size() / PAGE_SIZE);
        return new PageDto(page, pageCount, pagedList);
    }
}
