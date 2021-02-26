package 线程相关.线程异常处理;

public class ThreadA implements Runnable {
    @Override
    public void run() {
        int a = Integer.parseInt("abc");
    }
}
