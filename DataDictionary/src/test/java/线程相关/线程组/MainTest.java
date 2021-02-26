package 线程相关.线程组;

public class MainTest {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("第一层组1");
        Thread thread1 = new Thread(threadGroup,new ThreadA(), "第二层线程1");
        Thread thread2 = new Thread(threadGroup,new ThreadA(), "第二层线程2");
        thread1.start();
        thread2.start();
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup, "第二层组3");
        Thread thread3 = new Thread(threadGroup2,new ThreadA(), "第三层线程1");
        thread3.start();
    }
}
