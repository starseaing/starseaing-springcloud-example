package com.starseaing.example.activiti.mcubeuser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.starseaing.example.activiti.mcubeuser.entity.McubeRole;
import com.starseaing.example.activiti.mcubeuser.entity.McubeUser;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/30
 */
public class McubeUserUtil {

    public static String getCookie(){
        try {
            java.net.URL uri = new McubeUserUtil().getClass().getResource("/");

            Path path = Paths.get(uri.toString().substring(6), "cookie.txt");
            List<String> lines = Files.readAllLines(path);
            String cookie = String.join("", lines);
            return cookie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param jsonUser
     * @return
     */
    public static UserEntity toActivitiUser(JSONObject jsonUser){
        McubeUser mcubeUser = JSONObject.toJavaObject(jsonUser, McubeUser.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(mcubeUser.getId());
        userEntity.setFirstName(mcubeUser.getName());
        userEntity.setLastName("");
        userEntity.setPassword("123456");
        userEntity.setEmail(String.valueOf(mcubeUser.getEmail()));
        userEntity.setRevision(1);
        return userEntity;
    }


    public static GroupEntity toActivitiGroup(JSONObject jsonRole){

        McubeRole mcubeRole = JSONObject.toJavaObject(jsonRole, McubeRole.class);

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setRevision(1);
        groupEntity.setType(mcubeRole.getType());
        groupEntity.setId(mcubeRole.getId()+"_" + mcubeRole.getCode());
        return groupEntity;
    }

    public static List<Group> toActivitiGroups(JSONArray roleList){
        List<Group> groups = new ArrayList<>();
        for (Object jsonObject : roleList) {
            GroupEntity groupEntity = toActivitiGroup((JSONObject) jsonObject);
            groups.add(groupEntity);
        }
        return groups;
    }
}
