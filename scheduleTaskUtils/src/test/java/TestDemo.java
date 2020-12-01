import com.github.appundefined.activiti.dto.ScheduleTaskDTO;
import com.github.appundefined.activiti.interfaces.ScheduleTaskInterface;
import com.github.appundefined.activiti.schedule.ScheduleTaskUtils;

public class TestDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduleTaskDTO dto = new ScheduleTaskDTO();
        dto.setDelay(1000L);
        dto.setPeriod(1000L);
        dto.setId("1");
        dto.setScheduleTaskInterface(new ScheduleTaskInterface() {
            public void callBack(String taskId) {
                System.out.println("1");
            }
        });
        ScheduleTaskUtils.startTask(dto);
        Thread.sleep(10000L);
        System.out.println("开始停止");
        ScheduleTaskUtils.stopTask(dto);
        Thread.sleep(5000L);
        System.out.println("开始启动");
        ScheduleTaskUtils.startTask(dto);
        Thread.sleep(100000L);
    }
}
