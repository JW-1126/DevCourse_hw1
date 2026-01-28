package service.orders;

public class Close implements Command {
    @Override
    public void process() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }
}
