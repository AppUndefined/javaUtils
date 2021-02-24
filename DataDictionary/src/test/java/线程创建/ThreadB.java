package 线程创建;

public class ThreadB implements Runnable {
    @Override
    public void run() {
        System.out.println("子线程名："+Thread.currentThread().getName());
    }
}
