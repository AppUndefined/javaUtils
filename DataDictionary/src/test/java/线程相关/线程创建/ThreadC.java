package 线程相关.线程创建;

import java.util.concurrent.Callable;

public class ThreadC implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("子线程名："+Thread.currentThread().getName());
        return "1";
    }
}
