package 线程相关.线程安全;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<ThreadA> threadAS = new ArrayList<>();
        TestAdd testAdd = new TestAdd();
        for (int i=0;i<1000;i++) {
            threadAS.add(new ThreadA(testAdd));
        }
        int i =1;
        for (ThreadA t : threadAS) {
            Thread thread = new Thread(t, "线程" + i++);
            thread.start();
        }
        Thread.sleep(2000);
        System.out.println(testAdd.getA());


    }
}
