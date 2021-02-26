package 线程相关.atomic原子操作;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAdd {
    AtomicInteger a = new AtomicInteger(1);
    //此处处理不当会造成线程不安全
     void  add(){
         a.incrementAndGet();
    }

    public  int getA() {
        return a.intValue();
    }
}
