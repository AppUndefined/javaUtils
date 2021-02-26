package 线程相关.线程组;

public class ThreadA implements Runnable {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("当前线程名:"+thread.getName()+"___当前线程组:"+thread.getThreadGroup().getName()+"_____当前线程所属组:"+thread.getThreadGroup().getParent().getName());
    }
}
