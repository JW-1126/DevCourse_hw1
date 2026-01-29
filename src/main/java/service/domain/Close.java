package service.domain;

import static view.Output.printEnd;

public class Close implements Process {
    @Override
    public void process(Integer index) {
        printEnd();
        System.exit(0);
    }
}
