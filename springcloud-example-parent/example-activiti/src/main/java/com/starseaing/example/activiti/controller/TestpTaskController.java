package com.starseaing.example.activiti.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.starseaing.example.activiti.dto.ProcessInstanceDto;
import com.starseaing.example.activiti.dto.TaskDto;
import com.starseaing.example.activiti.service.LeaveActivitiService;
import com.starseaing.example.activiti.service.MyFormDataService;
import com.starseaing.example.activiti.service.MyFormService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/26
 */
@RestController
@RequestMapping(("/testp"))
public class TestpTaskController {

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


    @Autowired(required = false)
    private HistoryService historyService;


    @Autowired(required = false)
    private MyFormService myFormService;

    @Autowired(required = false)
    private MyFormDataService myFormDataService;

    /**
     * 测试远程微服务
     *
     * @return
     */
    @GetMapping(value = "/test")
    public JSONObject test() {
        JSONObject data = new JSONObject();
        JSONObject form = myFormService.getByFormId("1");

        data.put("form", form);
        data.put("form-list", myFormService.page(new JSONObject(), 0, 10));

        return data;
    }


    /**
     * 开启流程实例
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public JSONObject startProcessInstance(@RequestBody JSONObject data) {

        //流程定义的标识，这个id是在xml定义好的。
        String processDefKey = data.getString("processDefKey");

        //业务ID
        String businessId = data.getString("businessId");

        //流程的变量：主要是办理人等
        Map<String, Object> variables = new HashMap<>(8);
        variables.putAll(data.getJSONObject("variables"));

        //当前用户id
        String userId = data.getString("userId");

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefKey)
                .latestVersion()
                .singleResult();
        boolean hasStartFormKey = processDefinition.hasStartFormKey();
        //表单数据：如果有的话
        JSONObject formData = data.getJSONObject("formData");
        if (hasStartFormKey && !formData.isEmpty()) {
            Object startFormKey = formService.getRenderedStartForm(processDefinition.getId(), "myFormEngine");
            JSONObject formDataObj = new JSONObject();

            /**
             {
             "formId": "test-p2-formkey-start",
             "modifiedTime": "2020-03-27T03:10:41.000+0000",
             "jsonData": "{\n\t\"key1\":\"value5555\",\n\t\"key2\":5555,\n\t\"busiKey\":\"testp-busi-5\"\n}",
             "dataId": "4ea86729-16c1-46e6-af7d-2136b7a48464",
             "createTime": "2020-03-27T03:10:41.000+0000",
             "businessId": "testp-busi-5",
             "creatorId": "kermit",
             "modifierId": "kermit",
             "id": "3989d7f0-895e-42f8-a69e-ff47e1c3cb69"
             }
             */
            formDataObj.put("businessId", businessId);
            formDataObj.put("formId", startFormKey);
            formDataObj.put("jsonData", JSONObject.toJSONString(formData, true));

            String id = UUID.randomUUID().toString();
            formDataObj.put("dataId", id);
            formDataObj.put("id", id);
            formDataObj.put("creatorId", userId);
            formDataObj.put("modifierId", userId);
            formDataObj.put("createTime", new Date());
            formDataObj.put("modifiedTime", new Date());

            JSONObject formObjRt = myFormDataService.save(formDataObj);
        }

        //指定业务标识，并开启流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefKey, businessId, variables);

        JSONObject result = new JSONObject();
        result.put("message", "申请提交成功");
        result.put("processInstanceDto", ProcessInstanceDto.toProcessInstanceDto(pi));

        //返回流程实例的详情
        result.put("business", this.getTaskByBusinessId(businessId));
        return result;
    }

    /**
     * 获取当前人的任务
     *
     * @param assignee 用户id
     * @return
     */
    @GetMapping(value = "/task/list")
    public JSONObject getTaskList(@RequestParam String assignee) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().desc().list();
        List<TaskDto> taskDtoList = taskList.stream().map(task -> TaskDto.toTaskDto(task)).collect(Collectors.toList());
        JSONObject result = new JSONObject();
        result.put("message", "获取任务成功");
        result.put("taskList", taskDtoList);
        return result;
    }

    /**
     * 根据任务id获取任务详情
     *
     * @param data 任务数据
     * @return
     */
    @PostMapping(value = "/task/complete")
    public JSONObject completeTaskByTaskIdAndAssignee(@RequestBody JSONObject data) {

        String taskId = data.getString("taskId");
        String assignee = data.getString("assignee");

        JSONObject formData = data.getJSONObject("formData");

        JSONObject result = new JSONObject();

        Task task = taskService.createTaskQuery().taskId(taskId).taskAssignee(assignee).singleResult();

        if (task != null) {
            result.put("task", TaskDto.toTaskDto(task));
            result.put("message", "获取任务成功");

            //保存表单数据
            String formKey = task.getFormKey();
            if (StringUtils.isNotBlank(formKey)) {
                JSONObject formDataObj = new JSONObject();

                //1  获取任务对象
                //2  通过任务对象获取流程实例
                ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                //3 通过流程实例获取 业务键
                String businessKey = pi.getBusinessKey();

                /**
                 {
                 "formId": "test-p2-formkey-start",
                 "modifiedTime": "2020-03-27T03:10:41.000+0000",
                 "jsonData": "{\n\t\"key1\":\"value5555\",\n\t\"key2\":5555,\n\t\"busiKey\":\"testp-busi-5\"\n}",
                 "dataId": "4ea86729-16c1-46e6-af7d-2136b7a48464",
                 "createTime": "2020-03-27T03:10:41.000+0000",
                 "businessId": "testp-busi-5",
                 "creatorId": "kermit",
                 "modifierId": "kermit",
                 "id": "3989d7f0-895e-42f8-a69e-ff47e1c3cb69"
                 }
                 */
                formDataObj.put("businessId", businessKey);
                formDataObj.put("formId", formKey);
                formDataObj.put("jsonData", JSONObject.toJSONString(formData, true));

                String id = UUID.randomUUID().toString();
                formDataObj.put("dataId", id);
                formDataObj.put("id", id);
                formDataObj.put("creatorId", assignee);
                formDataObj.put("modifierId", assignee);
                formDataObj.put("createTime", new Date());
                formDataObj.put("modifiedTime", new Date());

                JSONObject formObjRt = myFormDataService.save(formDataObj);
            }

            Map<String, Object> variables = new HashMap<>(8);
            variables.putAll(data.getJSONObject("variables"));
            taskService.complete(taskId, variables);

            result.put("complete", "任务已完成");
        } else {
            result.put("message", "获取任务失败");
        }

        return result;
    }

    /**
     * 根据任务id获取任务详情
     *
     * @param taskId 任务id
     * @return
     */
    @GetMapping(value = "/task")
    public JSONObject getTaskByTaskId(@RequestParam String taskId, @RequestParam String assignee) {
        Task task = taskService.createTaskQuery().taskId(taskId).taskAssignee(assignee).singleResult();
        JSONObject result = new JSONObject();
        if (task != null) {
            result.put("message", "获取任务成功");
            result.put("task", TaskDto.toTaskDto(task));

            //1  获取任务对象
            //2  通过任务对象获取流程实例
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            //3 通过流程实例获取“业务键”
            String businessKey = pi.getBusinessKey();
            result.put("businessKey", businessKey);

            //走过的审批节点历程
            List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .orderByHistoricActivityInstanceStartTime().asc()
                    .list();
            JSONArray hpiList = new JSONArray();
            for (HistoricActivityInstance hpi : list) {
                JSONObject hpiObj = new JSONObject();
                hpiObj.put("id", hpi.getId());
                hpiObj.put("activityName", hpi.getActivityName());
                hpiObj.put("activityType", hpi.getActivityType());
                hpiObj.put("assignee", hpi.getAssignee());
                hpiObj.put("taskId", hpi.getTaskId());


                if ("userTask".equals(hpi.getActivityType())) {
                    //活动任务，需要通过 HistoricTaskInstance 去查询
                    HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery()
                            .taskId(hpi.getTaskId()).singleResult();

                    Object taskFormKey = hti.getFormKey();
                    hpiObj.put("taskFormKey", taskFormKey);

                    JSONObject formContent = null;

                    if (taskFormKey != null) {
                        formContent = myFormService.getByFormId(String.valueOf(taskFormKey));
                    }
                    hpiObj.put("formContent", formContent);
                    hpiObj.put("startFormKeyMsg", formContent != null ? "获取表单模板成功" : "没有找到对应的表单模板");

                    if (formContent != null) {
                        JSONObject formData = myFormDataService.getFormData(businessKey, String.valueOf(taskFormKey));
                        hpiObj.put("formData", formData);
                        hpiObj.put("formDataMsg", formData != null ? "获取表单数据成功" : "没有找到对应的表单数据");
                    } else {
                        hpiObj.put("formDataMsg", "没有表单模板，不存在表单数据");
                    }
                } else {
                    Object startFormKey = formService.getRenderedStartForm(hpi.getProcessDefinitionId(), "myFormEngine");
                    hpiObj.put("startFormKey", startFormKey);

                    JSONObject formContent = null;
                    if (startFormKey != null) {
                        formContent = myFormService.getByFormId(String.valueOf(startFormKey));
                    }
                    hpiObj.put("formContent", formContent);
                    hpiObj.put("startFormKeyMsg", formContent != null ? "获取表单模板成功" : "没有找到对应的表单模板");


                    if (formContent != null) {
                        JSONObject formData = myFormDataService.getFormData(businessKey, String.valueOf(startFormKey));
                        hpiObj.put("formData", formData);
                        hpiObj.put("formDataMsg", formData != null ? "获取表单数据成功" : "没有找到对应的表单数据");
                    } else {
                        hpiObj.put("formDataMsg", "没有表单模板，不存在表单数据");
                    }
                }

                hpiList.add(hpiObj);
            }

            result.put("hpiList", hpiList);
        } else {
            result.put("message", "获取任务失败");
        }

        return result;
    }

    /**
     * 根据业务id获取实例的所有详情
     *
     * @param businessId 业务id
     * @return
     */
    @GetMapping(value = "/business")
    public JSONObject getTaskByBusinessId(@RequestParam String businessId) {
        JSONObject result = new JSONObject();
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessId)
                .singleResult();
        if (hpi != null) {
            result.put("message", "获取实例成功");
            //result.put("hpi", HistoricProcessInstanceDto.toHistoricProcessInstanceDto(hpi));

            //走过的审批节点历程
            List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(hpi.getId())
                    .orderByHistoricActivityInstanceStartTime().asc()
                    .list();
            JSONArray hpiList = new JSONArray();
            for (HistoricActivityInstance hacti : list) {
                JSONObject hpiObj = new JSONObject();
                hpiObj.put("id", hacti.getId());
                hpiObj.put("activityName", hacti.getActivityName());
                hpiObj.put("activityType", hacti.getActivityType());
                hpiObj.put("assignee", hacti.getAssignee());
                hpiObj.put("taskId", hacti.getTaskId());

                if ("userTask".equals(hacti.getActivityType())) {
                    //活动任务，需要通过 HistoricTaskInstance 去查询
                    HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery()
                            .taskId(hacti.getTaskId()).singleResult();

                    Object taskFormKey = hti.getFormKey();
                    hpiObj.put("taskFormKey", taskFormKey);

                    JSONObject formContent = null;

                    if (taskFormKey != null) {
                        formContent = myFormService.getByFormId(String.valueOf(taskFormKey));
                    }
                    hpiObj.put("formContent", formContent);
                    hpiObj.put("startFormKeyMsg", formContent != null ? "获取表单模板成功" : "没有找到对应的表单模板");

                    if (formContent != null) {
                        JSONObject formData = myFormDataService.getFormData(businessId, String.valueOf(taskFormKey));
                        hpiObj.put("formData", formData);
                        hpiObj.put("formDataMsg", formData != null ? "获取表单数据成功" : "没有找到对应的表单数据");
                    } else {
                        hpiObj.put("formDataMsg", "没有表单模板，不存在表单数据");
                    }
                } else {
                    Object startFormKey = formService.getRenderedStartForm(hacti.getProcessDefinitionId(), "myFormEngine");
                    hpiObj.put("startFormKey", startFormKey);

                    JSONObject formContent = null;
                    if (startFormKey != null) {
                        formContent = myFormService.getByFormId(String.valueOf(startFormKey));
                    }
                    hpiObj.put("formContent", formContent);
                    hpiObj.put("startFormKeyMsg", formContent != null ? "获取表单模板成功" : "没有找到对应的表单模板");

                    if (formContent != null) {

                        JSONObject formData = myFormDataService.getFormData(businessId, String.valueOf(startFormKey));
                        hpiObj.put("formData", formData);
                        hpiObj.put("formDataMsg", formData != null ? "获取表单数据成功" : "没有找到对应的表单数据");
                    } else {
                        hpiObj.put("formDataMsg", "没有表单模板，不存在表单数据");
                    }
                }

                hpiList.add(hpiObj);
            }

            result.put("hpiList", hpiList);
        } else {
            result.put("message", "获取任务失败");
        }

        return result;
    }
}

