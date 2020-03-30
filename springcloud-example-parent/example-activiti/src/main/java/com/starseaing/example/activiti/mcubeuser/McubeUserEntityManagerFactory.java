package com.starseaing.example.activiti.mcubeuser;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/30
 */
@Service
public class McubeUserEntityManagerFactory implements SessionFactory {

    @Autowired
    private McubeUserEntityManager mcubeUserEntityManager;

    @Override
    public Class<?> getSessionType() {
        return UserIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return mcubeUserEntityManager;
    }
}
