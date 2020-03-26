package com.starseaing.example.activiti.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 表单服务
 *
 * @author chentc
 * @since 2020/3/26
 */
@FeignClient(name = "example-form", path = "/form" + "/form")
public interface MyFormService {

    /**
     * 根据formId 查询
     *
     * @date 2020/03/23
     **/
    @GetMapping
    public JSONObject getByFormId(@RequestParam String formId);

    /**
     * 新增表单
     *
     * @date 2020/03/23
     **/
    @PostMapping
    public JSONObject insert(@RequestBody JSONObject form);

    /**
     * 更新
     *
     * @date 2020/03/23
     **/
    @PutMapping
    public JSONObject update(@RequestBody JSONObject form);

    /**
     * 分页查询
     *
     * @date 2020/03/23
     **/
    @GetMapping("/page")
    public JSONObject page(@RequestParam(required = false) JSONObject form,
                           @RequestParam(required = false, defaultValue = "0") int pageNumber,
                           @RequestParam(required = false, defaultValue = "10") int pageSize);

}
