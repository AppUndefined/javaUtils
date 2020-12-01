package com.github.appundefined.activiti.dto;

import com.github.appundefined.activiti.interfaces.ScheduleTaskInterface;

public class ScheduleTaskDTO {
    /**
     * 多长时间后执行（毫秒）
     */
    private Long delay;
    /**
     * 之后每隔多久执行一次（毫秒）
     */
    private Long period;
    /**
     * 定时任务唯一id
     */
    private String id;
    private ScheduleTaskInterface scheduleTaskInterface;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public ScheduleTaskInterface getScheduleTaskInterface() {
        return scheduleTaskInterface;
    }

    public void setScheduleTaskInterface(ScheduleTaskInterface scheduleTaskInterface) {
        this.scheduleTaskInterface = scheduleTaskInterface;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }
}
