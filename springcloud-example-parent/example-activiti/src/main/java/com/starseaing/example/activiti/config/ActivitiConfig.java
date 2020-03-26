package com.starseaing.example.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 工作流配置
 *
 * @author chentc
 * @since 2020/3/25
 */
@Component
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");

        processEngineConfiguration.setDbIdentityUsed(false);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        //自定义表单引擎
        processEngineConfiguration.setCustomFormEngines(Arrays.asList(new MyFormEngine()));
    }

}
