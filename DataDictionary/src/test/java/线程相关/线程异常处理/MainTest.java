package 线程相关.线程异常处理;

public class MainTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadA());
        thread.setUncaughtExceptionHandler(new ExceptionHandlerThreadTest());
        thread.start();
    }
}
