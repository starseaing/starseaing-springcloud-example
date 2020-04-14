package com.starseaing.example.activiti.mcubeuser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.starseaing.example.activiti.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 分页获取角色
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


    /**
     * 根据用户ID获取角色分组
     * @param id
     * @param cookie
     * @return
     */
    @GetMapping("/api/accounts/management/details")
    public JSONArray findGroupsByUser(@RequestParam String id,
                             @RequestHeader(name = "cookie") String cookie);


}
