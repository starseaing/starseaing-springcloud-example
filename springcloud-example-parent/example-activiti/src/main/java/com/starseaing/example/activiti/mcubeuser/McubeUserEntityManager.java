package com.starseaing.example.activiti.mcubeuser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/30
 */
@Component
public class McubeUserEntityManager extends UserEntityManager {

    @Autowired
    private McubeUserService mcubeUserService;

    @Override
    public User findUserById(String userId) {
        JSONObject jsonUser = mcubeUserService.findUserById(userId, McubeUserUtil.getCookie());
        return McubeUserUtil.toActivitiUser(jsonUser);
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {

        //如果是id查询，调用单个记录查询接口
        if(StringUtils.isNotBlank(query.getId())){
            User user = findUserById(query.getId());
            return Collections.singletonList(user);
        }

        JSONObject result = mcubeUserService.findUserByQueryCriteria(1L, 100L, null, null, null, null, McubeUserUtil.getCookie());

        JSONArray jsonUserList = result.getJSONArray("data");
        List<User> userEntitys = new ArrayList<>();
        for (Object jsonObject : jsonUserList) {
            UserEntity userEntity = McubeUserUtil.toActivitiUser((JSONObject) jsonObject);
            userEntitys.add(userEntity);
        }
        return userEntitys;
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        JSONArray list = mcubeUserService.findGroupsByUser(userId, McubeUserUtil.getCookie());
        return McubeUserUtil.toActivitiGroups(list);
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<org.activiti.engine.identity.User> findPotentialStarterUsers(String proceDefId) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<org.activiti.engine.identity.User> findUsersByNativeQuery(Map<String, Object> parameterMap,
                                                                          int firstResult, int maxResults) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new RuntimeException("not implement method.");
    }
}
