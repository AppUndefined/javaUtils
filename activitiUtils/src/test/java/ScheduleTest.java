import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    public static void main(String[] args) {
        testSchedule();
    }
    public static  void testSchedule(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "任务一 : 延迟1秒,间隔3秒");
            }
        }, 1, 3, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture2 = service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "任务二 : 延迟1秒,间隔3秒");
            }
        }, 1, 3, TimeUnit.SECONDS);

//        scheduledFuture.cancel(true);
    }
}
