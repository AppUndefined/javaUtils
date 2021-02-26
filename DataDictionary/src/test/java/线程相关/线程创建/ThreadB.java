package 线程相关.线程创建;

public class ThreadB implements Runnable {
    @Override
    public void run() {
        System.out.println("子线程名："+Thread.currentThread().getName());
        System.out.println("所属线程组："+Thread.currentThread().getThreadGroup());
        System.out.println("线程标识符："+Thread.currentThread().getId());
        System.out.println("线程优先级："+Thread.currentThread().getPriority());
    }
}
