package service.domain;

import static repository.Id.writeLastId;
import static service.domain.Create.id;

import repository.WiseSayingRepository;

public class Build implements Process {
    private final WiseSayingRepository wiseSayingRepository;

    public Build(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    @Override
    public void process(Integer index) {
        wiseSayingRepository.build();
        writeLastId(id);
    }
}
