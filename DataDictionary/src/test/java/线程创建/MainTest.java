package 线程创建;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程名："+Thread.currentThread().getName());
        ThreadBTest();
        ThreadCTest();


    }

    private static void ThreadBTest() {
        ThreadB threadB = new ThreadB();
        new Thread(threadB).start();

    }

    private static void ThreadCTest() throws InterruptedException, ExecutionException {
        ThreadC threadC = new ThreadC();
        FutureTask<String> futureTask = new FutureTask<>(threadC);
        new Thread(futureTask).start();
        System.out.println("子线程返回结果："+futureTask.get());
    }
}
