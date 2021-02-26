package 线程相关.线程异常处理;

public class ExceptionHandlerThreadTest implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        throwable.printStackTrace(System.out);
    }
}
