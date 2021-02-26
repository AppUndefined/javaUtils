package 线程相关.当前线程副本;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        ThreadLocalNumber threadLocalNumber = new ThreadLocalNumber();
        ArrayList<ThreadA> threadAS = new ArrayList<>();
        for (int i=0;i<3;i++) {
            threadAS.add(new ThreadA(threadLocalNumber));
        }
        int i =1;
        for (ThreadA t : threadAS) {
            Thread thread = new Thread(t, "线程" + i++);
            thread.start();
        }


    }
}
