package com.github.appundefined.activiti.schedule;


import com.github.appundefined.activiti.dto.ScheduleTaskDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Activiti定时任务工具类
 */
public class ScheduleTaskUtils {

    /**
     * 存储所有定时任务 （key：唯一id）
     */
    public static Map<String, ScheduledFuture> timers = new HashMap();
    /**
     * 定时器 线程池
     */
    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    /**
     * 启动定时任务
     */
    public  static  void startTask(final ScheduleTaskDTO scheduleTaskDTO){
        ScheduledFuture scheduledFuture = service.scheduleAtFixedRate(new Runnable() {
            public void run() {
                scheduleTaskDTO.getScheduleTaskInterface().callBack(scheduleTaskDTO.getId());
                }
        },scheduleTaskDTO.getDelay(), scheduleTaskDTO.getPeriod(), TimeUnit.MILLISECONDS);
        timers.put(scheduleTaskDTO.getId(),scheduledFuture);
    }
    /**
     * 停止定时任务
     */
    public  static  void stopTask(ScheduleTaskDTO scheduleTaskDTO){
        timers.get(scheduleTaskDTO.getId()).cancel(true);
        timers.remove(scheduleTaskDTO.getId());
    }
}
