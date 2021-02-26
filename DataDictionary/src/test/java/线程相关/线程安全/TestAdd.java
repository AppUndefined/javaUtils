package 线程相关.线程安全;

import java.util.concurrent.locks.ReentrantLock;

public class TestAdd {
    private ReentrantLock reentrantLock = new ReentrantLock();
    int a =1;
    //此处处理不当会造成线程不安全
     void  add(){
         reentrantLock.lock();
         a+=1;
         reentrantLock.unlock();
    }

    public  int getA() {
        return a;
    }
}
