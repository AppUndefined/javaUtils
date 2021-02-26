package 线程相关.当前线程副本;

import lombok.SneakyThrows;

public class ThreadA implements Runnable{
    private ThreadLocalNumber threadLocalNumber;
    public ThreadA(ThreadLocalNumber threadLocalNumber) {
        this.threadLocalNumber = threadLocalNumber;
    }

    @SneakyThrows
    @Override
    public void run() {
        for(int i = 0;i<5;i++) {
            System.out.println(Thread.currentThread().getName() + "==" + threadLocalNumber.getNextNum());
//            threadLocalNumber.remove();
            Thread.sleep(100);
        }
    }
}
