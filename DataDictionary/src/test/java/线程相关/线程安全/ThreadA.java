package 线程相关.线程安全;

import lombok.SneakyThrows;

public class ThreadA implements Runnable{
    private TestAdd testAdd;
    public ThreadA(TestAdd testAdd) {
        this.testAdd = testAdd;
    }
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(100);
        testAdd.add();
    }
}
