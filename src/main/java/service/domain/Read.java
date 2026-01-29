package service.domain;

import static view.Output.printReadHeader;

import repository.WiseSayingRepository;
import view.Output;

public class Read implements Process {
    private final WiseSayingRepository wiseSayingRepository;

    public Read(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    @Override
    public void process(Integer index) {
        printReadHeader();
        wiseSayingRepository.readAll()
                .forEach(Output::printContent);
    }
}
