package com.starseaing.example.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.starseaing.example.activiti.dto.ProcessDefinitionDto;
import com.starseaing.example.activiti.dto.ProcessInstanceDto;
import com.starseaing.example.activiti.dto.TaskDto;
import com.starseaing.example.activiti.service.LeaveActivitiService;
import com.starseaing.example.activiti.service.MyFormDataService;
import com.starseaing.example.activiti.service.MyFormService;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private MyFormService myFormService;

    @Autowired(required = false)
    private MyFormDataService myFormDataService;
    /**
     * 测试远程微服务
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
     * 演示完整的流程
     * @return
     */
    @GetMapping(value = "/demo")
    public JSONObject demo(){

        //此处写死流程定义的标识，这个id是在xml定义好的。
        //查询流程表单
        String processDefinitionKey = "test-p2";
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();
        boolean hasStartFormKey = processDefinition.hasStartFormKey();

        JSONObject result = new JSONObject();

        JSONObject processDefinitionData = new JSONObject();
        // 根据是否有formkey属性判断使用哪个展示层

        // 判断是否有formkey属性
        processDefinitionData.put("processDefinitionKey", processDefinitionKey);
        processDefinitionData.put("processDefinition", ProcessDefinitionDto.toProcessDefinitionDto(processDefinition));

        processDefinitionData.put("hasStartFormKey", hasStartFormKey);

        JSONObject form = null;
        if (hasStartFormKey) {
            //拿到具体的formKey
            Object startFormKey = formService.getRenderedStartForm(processDefinition.getId(), "myFormEngine");
            processDefinitionData.put("startFormKey", startFormKey);

            form = myFormService.getByFormId(String.valueOf(startFormKey));
            processDefinitionData.put("form表单对象", form);

        } else {
            // 动态表单字段。可能有其他表单类型，此处进行处理
            //StartFormData startFormData = formService.getStartFormData(processDefinitionKey);
        }
        result.put("步骤1_查看流程定义", processDefinitionData);


        //模拟发起流程申请

        //第1个审批人操作

        //第2个审批人操作

        //第3个审批人操作

        JSONObject applyData = new JSONObject();
        String applyParamsJson = "{\n" +
                "\t\"businessKey\": \"testp-busi-5\",\n" +
                " \t\"applyUserId\": \"kermit\", \n" +
                " \t\"leader1\": \"gonzo\",\n" +
                " \t\"leader2\": \"kermit\",\n" +
                " \t\"leader3\": \"fozzie\",\n" +
                " \t\"formData\": {\n" +
                " \t\t\"busiKey\": \"testp-busi-5\",\n" +
                " \t\t\"key1\": \"value5555\",\n" +
                " \t\t\"key2\": 5555\n" +
                " \t}\n" +
                "}";
        JSONObject applyParams = JSONObject.parseObject(applyParamsJson);

        //TODO: 此处定义业务ID
        //业务ID
        String businessKey = applyParams.getString("businessKey");

        //办理人ID
        String applyUserId = applyParams.getString("applyUserId");
        String leader1 = applyParams.getString("leader1");
        String leader2 = applyParams.getString("leader2");
        String leader3 = applyParams.getString("leader3");

        //流程发起人填写的表单数据：json格式字符串
        applyData.put("有表单id", form != null);
        if(form != null){
            JSONObject applyFormData = applyParams.getJSONObject("formData");
            //TODO: 此处保存表单数据
            /*
            "{\n" +
            "    \"id\": \"3\",\n" +
            "    \"businessId\": \"b3\",\n" +
            "    \"formId\": \"2\",\n" +
            "    \"dataId\": \"33333333333\",\n" +
            "    \"jsonData\": \"{\\\"key\\\": \\\"3333333333\\\"}\",\n" +
            "    \"creatorId\": \"1\",\n" +
            "    \"modifierId\": \"1\",\n" +
            "    \"createTime\": \"2020-03-26T07:30:52.000+0000\",\n" +
            "    \"modifiedTime\": \"2020-03-26T07:30:54.000+0000\"\n" +
            "}"
            */
            JSONObject formDataObj = new JSONObject();
            formDataObj.put("id", UUID.randomUUID());
            formDataObj.put("businessId", businessKey);
            formDataObj.put("formId", form.getString("formId"));
            formDataObj.put("dataId", UUID.randomUUID());
            formDataObj.put("jsonData", JSONObject.toJSONString(applyFormData, true));
            formDataObj.put("creatorId", applyUserId);
            formDataObj.put("modifierId", applyUserId);
            formDataObj.put("createTime", new Date());
            formDataObj.put("modifiedTime", new Date());

            JSONObject formDataResult = myFormDataService.save(formDataObj);

            applyData.put("保存的表单数据对象", formDataResult);
        }

        //此处传递流程需要的变量，主要是办理人的id
        Map<String, Object> variables = new HashMap<>(4);
        variables.put("applyUserId", applyUserId);
        variables.put("leader1", leader1);
        variables.put("leader2", leader2);
        variables.put("leader3", leader3);

        //指定业务标识，并开启流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);

        applyData.put("业务businessKey", businessKey);
        applyData.put("流程变量variables", variables);
        applyData.put("流程实例对象processInstance", ProcessInstanceDto.toProcessInstanceDto(pi));
        applyData.put("message", "申请提交成功");

        result.put("步骤2_发起流程申请", applyData);

        return result;
    }




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


    /**
     * 开启流程实例
     * @param data
     * @return
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public JSONObject startProcessInstance(@RequestBody JSONObject data) {

        String businessKey = data.getString("businessKey");
        String applyUserId = data.getString("applyUserId");
        String leader1 = data.getString("leader1");
        String leader2 = data.getString("leader2");
        String leader3 = data.getString("leader3");

        JSONObject formData = data.getJSONObject("formData");

        //TODO: 此处保存表单数据
        //TODO: 此处定义业务ID

        //此处写死流程定义的标识，这个id是在xml定义好的。
        String processDefinitionKey = "test-p2";

        Map<String, Object> variables = new HashMap<>(4);

        variables.put("applyUserId", applyUserId);
        variables.put("leader1", leader1);
        variables.put("leader2", leader2);
        variables.put("leader3", leader3);
        variables.put("formData", formData);

        //指定业务标识，并开启流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);


        JSONObject result = new JSONObject();
        result.put("message", "申请提交成功");
        result.put("processInstanceDto", ProcessInstanceDto.toProcessInstanceDto(pi));

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

