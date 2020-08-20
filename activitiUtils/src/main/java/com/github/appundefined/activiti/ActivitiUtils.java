package com.github.appundefined.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

public class ActivitiUtils {
    private static ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
    public static String getProcdefName(String procdefId){
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procdefId).singleResult();
        return processDefinition.getResourceName().replace(".bpmn", "");
    }
}
