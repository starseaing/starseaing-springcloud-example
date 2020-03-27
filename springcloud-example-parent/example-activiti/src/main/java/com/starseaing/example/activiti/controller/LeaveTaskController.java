package com.starseaing.example.activiti.controller;

import com.starseaing.example.activiti.service.LeaveActivitiService;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请假流程接口：针对通用流程，进行定制化开发
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


}
