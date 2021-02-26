package 线程相关.线程创建;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程名："+Thread.currentThread().getName());
        System.out.println("当前线程组中活动线程数目："+Thread.activeCount());
//        ThreadBTest();
//        ThreadCTest();
    }


    private static void ThreadBTest() {
        ThreadB threadB = new ThreadB();
        Thread thread = new Thread(threadB, "自定义名称");
        thread.start();

    }

    private static void ThreadCTest() throws InterruptedException, ExecutionException {
        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask,"自定义名称2").start();
        System.out.println("子线程返回结果："+futureTask.get());
    }
}
