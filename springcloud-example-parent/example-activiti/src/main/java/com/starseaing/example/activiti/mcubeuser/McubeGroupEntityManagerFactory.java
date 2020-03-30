package com.starseaing.example.activiti.mcubeuser;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/30
 */
@Service
public class McubeGroupEntityManagerFactory implements SessionFactory {


    @Autowired(required = false)
    private McubeGroupEntityManager mcubeGroupEntityManager;

    @Override
    public Class<?> getSessionType() {
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return mcubeGroupEntityManager;
    }
}
