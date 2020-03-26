package com.starseaing.example.activiti.config;

import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.FormEngine;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/25
 */
public class MyFormEngine implements FormEngine {

    public MyFormEngine(){
        System.out.print("MyFormEngine init...............");
    }
    @Override
    public String getName() {
        return "myFormEngine";
    }

    @Override
    public Object renderStartForm(StartFormData startForm) {
        System.out.print("renderStartForm...............");
        if(startForm.getFormKey() == null) {
            return null;
        } else {
            return startForm.getFormKey();
        }
    }

    @Override
    public Object renderTaskForm(TaskFormData taskForm) {
        System.out.print("renderTaskForm...............");
        if(taskForm.getFormKey() == null) {
            return null;
        } else {
            return taskForm.getFormKey();
        }
    }
}
