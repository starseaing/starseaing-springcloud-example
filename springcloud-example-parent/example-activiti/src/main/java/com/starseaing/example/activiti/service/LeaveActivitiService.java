package com.starseaing.example.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired(required = false)
    private RuntimeService runtimeService;

    @Autowired(required = false)
    private TaskService taskService;

}