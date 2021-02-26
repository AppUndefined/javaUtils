package 线程相关.守护线程测试;

public class MainTest {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        Thread a = new Thread(threadA,"线程A");
//        a.setDaemon(true);
        a.start();
    }
}
