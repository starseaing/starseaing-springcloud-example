package com.starseaing.example.form.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starseaing.example.form.model.FormData;
import com.starseaing.example.form.service.FormDataService;
import com.starseaing.example.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 表单数据服务
 *
 * @author chentc
 * @since 2020/3/26
 */
@RestController
@RequestMapping(value = "/formData")
public class FormDataController {

    @Autowired
    private FormDataService formDataService;

    /**
     * 根据主键 id 查询表单数据
     * @date 2020/03/23
     **/
    @GetMapping
    public FormData getFormDataByBusinessIdAndFormId(@RequestParam String businessId, @RequestParam String formId){
        return formDataService.getFormDataByBusinessKeyAndFormKey(businessId, formId);
    }

    /**
     * 新增表单数据
     * @date 2020/03/23
     **/
    @PostMapping
    public FormData insert(@RequestBody FormData formData){
        return formDataService.insert(formData);
    }

    /**
     * 更新表单数据
     * @date 2020/03/23
     **/
    @PutMapping
    public FormData update(@RequestBody FormData formData){
        return formDataService.update(formData);
    }

    /**
     * 分页查询表单数据
     * @date 2020/03/23
     **/
    @GetMapping("/page")
    public IPage<FormData> pageFormData(@RequestParam(required = false) FormData formData,
                                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
        IPage<FormData> pageList = formDataService.pageList(pageNumber, pageSize, formData);
        return pageList;
    }
}
