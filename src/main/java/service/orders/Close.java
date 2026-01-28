package service.orders;

public class Close implements Order {
    @Override
    public void process() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }
}
