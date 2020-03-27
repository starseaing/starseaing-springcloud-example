package com.starseaing.example.activiti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.Map;

/**
 * activiti的Task的传输对象转换
 *
 * @author chentc
 * @since 2020/3/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    /**
     * Indicates whether this task is suspended or not.
     */
    private boolean isSuspended;


    /**
     * DB id of the task.
     */
    private String id;

    /**
     * Name or title of the task.
     */
    private String name;

    /**
     * Free text description of the task.
     */
    private String description;

    /**
     * Indication of how important/urgent this task is
     */
    private int priority;

    /**
     * The {@link User.getId() userId} of the person that is responsible for this
     * task.
     */
    private String owner;

    /**
     * The {@link User.getId() userId} of the person to which this task is
     * delegated.
     */
    private String assignee;

    /**
     * Reference to the process instance or null if it is not related to a process
     * instance.
     */
    private String processInstanceId;

    /**
     * Reference to the path of execution or null if it is not related to a
     * process instance.
     */
    private String executionId;

    /**
     * Reference to the process definition or null if it is not related to a
     * process.
     */
    private String processDefinitionId;

    /**
     * The date/time when this task was created
     */
    private Date createTime;

    /**
     * The id of the activity in the process defining this task or null if this is
     * not related to a process
     */
    private String taskDefinitionKey;

    /**
     * Due date of the task.
     */
    private Date dueDate;

    /**
     * The category of the task. This is an optional field and allows to 'tag'
     * tasks as belonging to a certain category.
     */
    private String category;

    /**
     * The parent task for which this task is a subtask
     */
    private String parentTaskId;

    /**
     * The tenant identifier of this task
     */
    private String tenantId;

    /**
     * The form key for the user task
     */
    private String formKey;

    /**
     * Returns the local task variables if requested in the task query
     */
    Map<String, Object> taskLocalVariables;

    /**
     * Returns the process variables if requested in the task query
     */
    Map<String, Object> processVariables;


    public static TaskDto toTaskDto(Task task){
        TaskDto taskDto = TaskDto.builder()
                .assignee(task.getAssignee())
                .category(task.getCategory())
                .createTime(task.getCreateTime())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .executionId(task.getExecutionId())
                .formKey(task.getFormKey())
                .id(task.getId())
                .isSuspended(task.isSuspended())
                .name(task.getName())
                .owner(task.getOwner())
                .parentTaskId(task.getParentTaskId())
                .priority(task.getPriority())
                .processDefinitionId(task.getProcessDefinitionId())
                .processInstanceId(task.getProcessInstanceId())
                .processVariables(task.getProcessVariables())
                .taskDefinitionKey(task.getTaskDefinitionKey())
                .taskLocalVariables(task.getTaskLocalVariables())
                .tenantId(task.getTenantId())
                .build();
        return taskDto;
    }

}