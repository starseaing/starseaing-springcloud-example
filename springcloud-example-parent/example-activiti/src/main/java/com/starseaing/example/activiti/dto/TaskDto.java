package com.starseaing.example.activiti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * activiti的Task的传输对象转换
 *
 * @author chentc
 * @since 2020/3/12
 */
@Data
@AllArgsConstructor
@Builder
public class TaskDto {
    /**
     * id
     */
    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 表单key
     */
    private String formKey;

    /**
     * 执行者
     */
    private String assignee;
}