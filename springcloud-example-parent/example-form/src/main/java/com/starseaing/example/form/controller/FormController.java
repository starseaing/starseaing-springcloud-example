package com.starseaing.example.form.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starseaing.example.form.model.Form;
import com.starseaing.example.form.model.FormData;
import com.starseaing.example.form.service.FormDataService;
import com.starseaing.example.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 表单服务
 *
 * @author chentc
 * @since 2020/3/23
 */
@RestController
@RequestMapping(value = "/form")
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private FormDataService formDataService;

    /**
     * 根据formId 查询
     * @date 2020/03/23
     **/
    @GetMapping
    public Form getByFormId(@RequestParam String formId){
        return formService.getByFormId(formId);
    }

    /**
     * 新增表单
     * @date 2020/03/23
     **/
    @PostMapping
    public Form insert(@RequestBody Form form){
        return formService.insert(form);
    }

    /**
     * 更新
     * @date 2020/03/23
     **/
    @PutMapping
    public Form update(@RequestBody Form form){
        return formService.update(form);
    }

    /**
     * 分页查询
     * @date 2020/03/23
     **/
    @GetMapping("/page")
    public IPage<Form> list(@RequestParam(required = false) Form form,
                           @RequestParam(required = false, defaultValue = "0") int pageNumber,
                           @RequestParam(required = false, defaultValue = "10") int pageSize) {
        IPage<Form> pageList = formService.pageList(pageNumber, pageSize, form);
        return pageList;
    }

}
