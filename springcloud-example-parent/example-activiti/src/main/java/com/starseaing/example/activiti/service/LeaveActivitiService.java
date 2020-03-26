package com.starseaing.example.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假流程专用的业务逻辑service层
 *
 * @author chentc
 * @since 2020/3/12
 */
@Service
@Transactional
public class LeaveActivitiService {

    /**
     * 注入为我们自动配置好的服务
     */
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 开始流程，传入申请者的id和参数
     * @param applyUserId
     * @param leaveType
     * @param startTime
     * @param endTime
     * @param reason
     */
    public void startOaLeaveProcess(String applyUserId, String leaveType, Date startTime, Date endTime, String reason) {
        //此处写死流程定义的标识，这个id是在xml定义好的。
        String processDefinitionKey = "STARSEAING_OA_LEAVE";

        Map<String, Object> variables = new HashMap<>(4);

        variables.put("leaveType", leaveType);
        variables.put("startTime", startTime);
        variables.put("endTime", endTime);
        variables.put("reason", reason);

        //开启流程实例
        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);

    }

    /**
     * 获得某个人的任务别表
     * @param assignee
     * @return
     */
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    /**
     * 部门经理完成任务：是否同意请假
     * @param deptLeaderPass 是否同意请假
     * @param taskId 任务id，注意此处的id必须和所需的参数一致，属于同一个流程。此处专门用于请假流程的。
     */
    public void deptLeaderApprovedLeaveTasks(Boolean deptLeaderPass, String taskId) {
        Map<String, Object> params = new HashMap<String, Object>();

        //${deptLeaderPass}
        params.put("deptLeaderPass", deptLeaderPass);
        taskService.complete(taskId, params);
    }

    /**
     * 申请者完成任务
     * @param taskId 任务id。此处专门用于请假流程的。
     */
    public void applyUserCompleteTasks(String taskId) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        taskService.complete(taskId, params);
    }


    /**
     * 人事专员（张三）登记销假。
     * @param taskId 任务id。此处专门用于请假流程的。
     */
    public void doLeaveMark(String taskId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> taskVariables = task.getProcessVariables();

        Map<String, Object> params = new HashMap<String, Object>(4);
        taskService.complete(taskId, params);
    }
}