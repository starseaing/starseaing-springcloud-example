package com.starseaing.example.activiti.controller;

import com.alibaba.fastjson.JSONObject;
import com.starseaing.example.activiti.dto.TaskDto;
import com.starseaing.example.activiti.service.LeaveActivitiService;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请假流程接口
 *
 * @author chentc
 * @since 2020/3/12
 */
@RestController
public class LeaveTaskController {

    @Autowired
    private LeaveActivitiService leaveActivitiService;

    @Autowired(required = false)
    private RepositoryService repositoryService;

    @Autowired(required = false)
    private FormService formService;

    @Autowired(required = false)
    private TaskService taskService;

    @Autowired(required = false)
    private RuntimeService runtimeService;

    /**
     * 用taskId获取业务对象id
     * @param taskId
     * @return
     */
    public String getBusinessObjId(String taskId) {
        //1  获取任务对象
        Task task  =  taskService.createTaskQuery().taskId(taskId).singleResult();

        //2  通过任务对象获取流程实例
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //3 通过流程实例获取“业务键”
        String businessKey = pi.getBusinessKey();

        //4 拆分业务键（如有必要）

        return businessKey;
    }

    /**
     * 根据业务键获取流程实例和任务
     * @param businessKey
     * @return
     */
    public List<Task> getTaskList(String businessKey){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
        List<Task> taskList = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
        return taskList;
    }


    /**
     * 读取启动流程的表单字段
     */
    @GetMapping(value = "getform/start/{processDefinitionKey}")
    public JSONObject readStartForm(@PathVariable("processDefinitionKey") String processDefinitionKey) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .singleResult();
        boolean hasStartFormKey = processDefinition.hasStartFormKey();

        JSONObject data = new JSONObject();
        // 根据是否有formkey属性判断使用哪个展示层

        // 判断是否有formkey属性
        data.put("processDefinitionKey", processDefinitionKey);
        data.put("hasStartFormKey", hasStartFormKey);
        if (hasStartFormKey) {
            Object renderedStartForm = formService.getRenderedStartForm(processDefinition.getId(), "myFormEngine");
            //Object renderedStartForm = formService.getRenderedStartForm(processDefinition.getId(), "juel");
            data.put("startFormData", renderedStartForm);
            data.put("processDefinitionId", processDefinition.getId());
        } else {
            // 动态表单字段
            //StartFormData startFormData = formService.getStartFormData(processDefinitionKey);
        }

        return data;
    }



    // 开启流程实例
    @RequestMapping(value = "/leave/start", method = RequestMethod.GET)
    public JSONObject startProcessInstance(
            @RequestParam String applyUserId ,
            @RequestParam String leaveType,
            @RequestParam Date startTime,
            @RequestParam Date endTime,
            @RequestParam String reason) {
        leaveActivitiService.startOaLeaveProcess(applyUserId, leaveType, startTime, endTime, reason);

        JSONObject result = new JSONObject();
        result.put("message", "申请提交成功");
        return result;
    }

    /**
     * 获取当前人的任务
     * @param assignee 用户id
     * @return
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public JSONObject getTasks(@RequestParam String assignee) {
        List<Task> tasks = leaveActivitiService.getTasks(assignee);
        List<TaskDto> taskDtos = new ArrayList<TaskDto>();
        for (Task task : tasks) {
            taskDtos.add(TaskDto.builder()
                    .id(task.getId())
                    .name(task.getName())
                    .formKey(task.getFormKey())
                    .assignee(task.getAssignee())
                    .build());
        }

        JSONObject result = new JSONObject();
        result.put("message", "获取任务成功");
        result.put("taskList", taskDtos);
        return result;
    }

    /**
     * 部门经理审批
     * @param taskId
     * @param deptLeaderPass
     * @return
     */
    @RequestMapping(value = "/leave/approved", method = RequestMethod.GET)
    public JSONObject deptLeaderApprovedLeaveTasks(@RequestParam String taskId, @RequestParam Boolean deptLeaderPass) {
        leaveActivitiService.deptLeaderApprovedLeaveTasks(deptLeaderPass, taskId);

        JSONObject result = new JSONObject();
        result.put("message", "部门经理审批完成");
        return result;
    }
    /**
     * 人事专员登记
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/leave/mark", method = RequestMethod.GET)
    public JSONObject doLeaveMark(@RequestParam String taskId) {
        leaveActivitiService.doLeaveMark(taskId);

        JSONObject result = new JSONObject();
        result.put("message", "人事专员登记销假完成");
        return result;
    }

    /**
     * 申请者完成任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/leave/complete", method = RequestMethod.GET)
    public JSONObject complete(@RequestParam String taskId) {
        leaveActivitiService.applyUserCompleteTasks(taskId);

        JSONObject result = new JSONObject();
        result.put("message", "申请者完成任务：确认放弃请假");
        return result;
    }
}
