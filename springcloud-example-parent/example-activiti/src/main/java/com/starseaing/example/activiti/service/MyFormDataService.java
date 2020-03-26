package com.starseaing.example.activiti.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 表单数据服务
 *
 * @author chentc
 * @since 2020/3/26
 */
@FeignClient(name = "example-form", path = "/form" + "/formData")
public interface MyFormDataService {
    /**
     * 根据主键 id 查询表单数据
     *
     * @date 2020/03/23
     **/
    @GetMapping
    public JSONObject getFormData(@RequestParam String businessKey, @RequestParam String formKey);

    /**
     * 新增表单数据
     *
     * @date 2020/03/23
     **/
    @PostMapping
    public JSONObject save(@RequestBody JSONObject formData);

    /**
     * 更新表单数据
     *
     * @date 2020/03/23
     **/
    @PutMapping
    public JSONObject update(@RequestBody JSONObject formData);

    /**
     * 分页查询表单数据
     *
     * @date 2020/03/23
     **/
    @GetMapping("/page")
    public JSONObject pageFormData(@RequestParam(required = false) JSONObject formData,
                                   @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize);
}
