package com.github.appundefined.activiti.dto;

import com.github.appundefined.activiti.interfaces.SendInterface;

import java.util.Map;

public class TimerDTO {
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
    private String uuid;
    /**
     * 流程定义id
     */
    private String processDefinitionId;
    /**
     * 流程启动所需表单数据
     */
    private Map variables;
    /**
     * 定时任务启动成功后回调该接口
     */
    private SendInterface sendInterface;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Map getVariables() {
        return variables;
    }

    public void setVariables(Map variables) {
        this.variables = variables;
    }

    public SendInterface getSendInterface() {
        return sendInterface;
    }

    public void setSendInterface(SendInterface sendInterface) {
        this.sendInterface = sendInterface;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
