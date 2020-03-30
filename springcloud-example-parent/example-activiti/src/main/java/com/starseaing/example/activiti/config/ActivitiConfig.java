package com.starseaing.example.activiti.config;

import com.starseaing.example.activiti.mcubeuser.McubeGroupEntityManagerFactory;
import com.starseaing.example.activiti.mcubeuser.McubeUserEntityManagerFactory;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 工作流配置
 *
 * @author chentc
 * @since 2020/3/25
 */
@Component
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private McubeUserEntityManagerFactory mcubeUserEntityManagerFactory;

    @Autowired
    private McubeGroupEntityManagerFactory mcubeGroupEntityManagerFactory;

    //rest service
    @Bean
    public RestResponseFactory restResponseFactory(){
        return new RestResponseFactory();
    }
    @Bean
    public ContentTypeResolver contentTypeResolver(){
        return new DefaultContentTypeResolver();
    }

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");

        processEngineConfiguration.setDbIdentityUsed(false);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        //自定义表单引擎
        processEngineConfiguration.setCustomFormEngines(Arrays.asList(new MyFormEngine()));

        //自定义用户和组
        List<SessionFactory> mcubeSessionFactories = new ArrayList<>();
        mcubeSessionFactories.add(mcubeUserEntityManagerFactory);
        mcubeSessionFactories.add(mcubeGroupEntityManagerFactory);

        processEngineConfiguration.setCustomSessionFactories(mcubeSessionFactories);

    }

}
