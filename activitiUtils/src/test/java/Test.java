import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前时间为：" + new Date());
        Calendar c = Calendar.getInstance();
        Date runDate1 = c.getTime();
        System.out.println("计划时间为：" + runDate1);
        MyTaskA task1 = new MyTaskA();
        MyTaskB task2 = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(task1, runDate1, 4000);
        timer.schedule(task2, runDate1, 4000);
//        timer.cancel();
    }
}

class MyTaskA extends TimerTask {

    @Override
    public void run()  {
        System.out.println("A计划 run timer=" + new Date());
//        this.cancel();// 调用的是TimerTask类中的cancel()方法
        try {
            throw new Exception("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("A任务自己移除自己,B任务不受影响，继续运行");
    }

}

class MyTaskB extends TimerTask {

    @Override
    public void run() {
        System.out.println("B计划 run timer=" + new Date());
    }

}