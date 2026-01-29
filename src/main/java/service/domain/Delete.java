package service.domain;

import static view.Output.printResult;

import repository.WiseSayingRepository;

public class Delete implements Process {
    private final WiseSayingRepository wiseSayingRepository;

    public Delete(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    @Override
    public void process(Integer index) {
        wiseSayingRepository.delete(index);
        printResult(index, "삭제");
    }
}
