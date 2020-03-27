package com.starseaing.example.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.FormEngine;

/**
 * 自定义表单引擎：主要是去掉自带的表单操作。通过formKey去表单系统查询表单模板和表单数据进行替换。
 *
 * @author chentc
 * @since 2020/3/25
 */
@Slf4j
public class MyFormEngine implements FormEngine {

    public MyFormEngine(){
        log.error("自定义表单引擎[MyFormEngine] init...............");
    }
    @Override
    public String getName() {
        return "myFormEngine";
    }

    @Override
    public Object renderStartForm(StartFormData startForm) {
        log.error("自定义表单引擎[MyFormEngine] 获取流程的启动表单StartFormKey ...............");
        if(startForm.getFormKey() == null) {
            return null;
        } else {
            return startForm.getFormKey();
        }
    }

    @Override
    public Object renderTaskForm(TaskFormData taskForm) {
        log.error("自定义表单引擎[MyFormEngine] 获取流程的任务表单TaskFormKey ...............");
        if(taskForm.getFormKey() == null) {
            return null;
        } else {
            return taskForm.getFormKey();
        }
    }
}
