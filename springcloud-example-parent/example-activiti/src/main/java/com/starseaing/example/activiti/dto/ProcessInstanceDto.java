package com.starseaing.example.activiti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessInstanceDto {

    /**
     * The unique identifier of the execution.
     */
    private  String id;

    /**
     * Indicates if the execution is ended.
     */
    boolean isEnded;

    /**
     * Returns the id of the activity where the execution currently is at.
     * Returns null if the execution is not a 'leaf' execution (eg concurrent parent).
     */
    private  String activityId;

    /** Id of the root of the execution tree representing the process instance.
     * It is the same as {@link #getId()} if this execution is the process instance. */
    private  String processInstanceId;

    /**
     * Gets the id of the parent of this execution. If null, the execution represents a process-instance.
     */
    private  String parentId;

    /**
     * Gets the id of the super execution of this execution.
     */
    private  String superExecutionId;


    /**
     * The id of the process definition of the process instance.
     */
    private  String processDefinitionId;

    /**
     * The name of the process definition of the process instance.
     */
    private  String processDefinitionName;

    /**
     * The key of the process definition of the process instance.
     */
    private  String processDefinitionKey;

    /**
     * The version of the process definition of the process instance.
     */
    private Integer processDefinitionVersion;

    /**
     * The deployment id of the process definition of the process instance.
     */
    private  String deploymentId;

    /**
     * The business key of this process instance.
     */
    private  String businessKey;

    /**
     * returns true if the process instance is suspended
     */
    private boolean isSuspended;

    /**
     * Returns the process variables if requested in the process instance query
     */
    Map<String, Object> processVariables;

    /**
     * The tenant identifier of this process instance
     */
    private  String tenantId;

    /**
     * Returns the name of this process instance.
     */
    private  String name;

    /**
     * Returns the description of this process instance.
     */
    private  String description;

    /**
     * Returns the localized name of this process instance.
     */
    private  String localizedName;

    /**
     * Returns the localized description of this process instance.
     */
    private  String localizedDescription;


    public static ProcessInstanceDto toProcessInstanceDto(ProcessInstance pi){
        ProcessInstanceDto dto = ProcessInstanceDto.builder()
                .activityId(pi.getActivityId())
                .businessKey(pi.getBusinessKey())
                .deploymentId(pi.getDeploymentId())
                .description(pi.getDescription())
                .id(pi.getId())
                .isEnded(pi.isEnded())
                .isSuspended(pi.isSuspended())
                .localizedDescription(pi.getLocalizedDescription())
                .localizedName(pi.getLocalizedName())
                .name(pi.getName())
                .parentId(pi.getParentId())
                .processDefinitionId(pi.getProcessDefinitionId())
                .processDefinitionKey(pi.getProcessDefinitionKey())
                .processDefinitionName(pi.getProcessDefinitionName())
                .processDefinitionVersion(pi.getProcessDefinitionVersion())
                .processInstanceId(pi.getProcessInstanceId())
                .processVariables(pi.getProcessVariables())
                .superExecutionId(pi.getSuperExecutionId())
                .tenantId(pi.getTenantId())
                .build();
        return dto;
    }

}
