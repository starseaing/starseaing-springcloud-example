package com.starseaing.example.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

/**
 * 此处需要排除SecurityAutoConfiguration这个类，不然activiti会报错
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                                  org.activiti.spring.boot.SecurityAutoConfiguration.class})
@MapperScan("com.starseaing.example.activiti.mapper")
@EnableFeignClients
public class ExampleActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleActivitiApplication.class, args);
        deploymentProcessDefinition();
    }

    public static void deploymentProcessDefinition() {

        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();

        List<Deployment> list = new ArrayList<>();

        // 流程部署
        // 流程定义和部署相关的service
        // 创建一个部署对象
        Deployment dy1 = pe.getRepositoryService()
                .createDeployment()
                .addClasspathResource("processes/test-p1.bpmn")
                //.addClasspathResource("processes/helloworld.png")
                .name("测试流程p1")
                .deploy();
        list.add(dy1);

        Deployment dy2 = pe.getRepositoryService()
                .createDeployment()
                .addClasspathResource("processes/test-p2.bpmn")
                .addClasspathResource("processes/test-p2.png")
                .name("测试流程p2")
                .deploy();
        list.add(dy2);

        list.forEach(deployment -> {
            System.out.println("部署流程ID: " + deployment.getId());
            System.out.println("类别: " + deployment.getCategory());
            System.out.println("名称: " + deployment.getName());
            System.out.println("租户: " + deployment.getTenantId());
            System.out.println("部署流程entity: " + deployment.getClass());
            System.out.println("部署流程时间: " + deployment.getDeploymentTime());
            System.out.println("______________________________________________________________");
        });

    }
}
