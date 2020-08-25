package com.github.appundefined.activiti.schedule;

import com.github.appundefined.activiti.dto.TimerDTO;
import com.github.appundefined.activiti.ActivitiUtils;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Activiti定时任务工具类
 */
public class ScheduleUtils {

    /**
     * 存储所有定时任务 （key：唯一id）
     */
    private static Map<String, ScheduledFuture> timers = new HashMap();
    /**
     * 定时器 线程池
     */
    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    /**
     * 启动定时任务
     */
    public  static  void startTimer(TimerDTO timerDTO){
        ScheduledFuture scheduledFuture = service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ProcessInstance instance = null;
                Map<String, String> result = null;
                try {
                    String deploymentId = ActivitiUtils.repositoryService.createProcessDefinitionQuery().processDefinitionId(timerDTO.getProcessDefinitionId()).singleResult().getDeploymentId();
                    String key = ActivitiUtils.repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult().getKey();
                    instance = ActivitiUtils.runtimeService
                            .startProcessInstanceById(timerDTO.getProcessDefinitionId(), key, timerDTO.getVariables());
                } catch (Exception e) {
                }
                if (instance != null) {
                    result = new HashMap<>(2);
                    // 流程实例ID
                    result.put("processID", instance.getId());
                    // 流程定义ID
                    result.put("processDefinitionKey", instance.getProcessDefinitionId());
                    //启动成功通知第一个执行人
                    timerDTO.getSendInterface().callBack(instance.getId());
                }
            }
        },timerDTO.getDelay(), timerDTO.getPeriod(), TimeUnit.MILLISECONDS);
        timers.put(timerDTO.getUuid(),scheduledFuture);
    }
    /**
     * 停止定时任务
     */
    public  static  void stopTimer(TimerDTO timerDTO){
        timers.get(timerDTO.getUuid()).cancel(true);
    }
}
