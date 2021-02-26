package 线程相关.守护线程测试;

import lombok.SneakyThrows;

public class ThreadA implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        int i =0;
        while (true){
            System.out.println("线程"+Thread.currentThread().getName()+"第"+i+++"次运行");
            Thread.sleep(1000);
        }
    }
}
