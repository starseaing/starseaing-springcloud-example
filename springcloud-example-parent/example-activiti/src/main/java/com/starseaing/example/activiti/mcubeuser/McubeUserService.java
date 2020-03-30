package com.starseaing.example.activiti.mcubeuser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.starseaing.example.activiti.config.FeignConfiguration;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/30
 */
@FeignClient(name = "mcube-dashboard", url = "http://192.168.16.191:6100/dashboard", configuration = FeignConfiguration.class)
public interface McubeUserService{


    /**
     * 根据id获取用户信息
     * @param id
     * @param cookie
     * @return
     */
    @GetMapping("/api/accounts/management/details")
    public JSONObject findUserById(@RequestParam String id,
                             @RequestHeader(name = "cookie") String cookie);


    /**
     * http://192.168.16.191:6100/dashboard/doc#/api/role-page
         参数名 	类型 	必填 	默认值 	说明
         pageIndex 	Long 	否 	1 	分页索引
         pageSize 	Long 	否 	20 	分页大小
         name 	String 	否 	无 	角色名称
         code 	String 	否 	无 	角色标识
         enabled 	Integer 	否 	无 	状态：0-禁用，1-启用
         appId 	String 	是 	无 	系统编码
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param username
     * @param enable
     * @param appId
     * @param cookie
     * @return
     */
    @GetMapping("/api/accounts/management/page")
    public JSONObject findUserByQueryCriteria(@RequestParam Long pageIndex,
                                              @RequestParam Long pageSize,
                                              @RequestParam String name,
                                              @RequestParam String username,
                                              @RequestParam Integer enable,
                                              @RequestParam String appId,
                                              @RequestHeader(name = "cookie") String cookie);


    @GetMapping("/api/accounts/management/details")
    public JSONArray findGroupsByUser(@RequestParam String id,
                             @RequestHeader(name = "cookie") String cookie);


}
