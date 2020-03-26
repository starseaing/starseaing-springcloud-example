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
@RequestMapping(value = "/formBak")
public class FormBakController {

    @Autowired
    private FormService formService;

    @Autowired
    private FormDataService formDataService;

    /**
     * 根据主键 id 查询
     * @date 2020/03/23
     **/
    @GetMapping
    public Form get(String id){
        return formService.get(id);
    }

    /**
     * 新增表单
     * @date 2020/03/23
     **/
    @PostMapping
    public Form insert(Form form){
        return formService.insert(form);
    }

    /**
     * 更新
     * @date 2020/03/23
     **/
    @PutMapping
    public Form update(Form form){
        return formService.update(form);
    }

    /**
     * 刪除表单
     * @date 2020/03/23
     **/
    @DeleteMapping
    public Form delete(String id){
        return formService.delete(id);
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


    //以下是表单数据操作

    /**
     * 根据主键 id 查询表单数据
     * @date 2020/03/23
     **/
    @GetMapping("/formData")
    public FormData getFormData(String id){
        return formDataService.get(id);
    }

    /**
     * 新增表单数据
     * @date 2020/03/23
     **/
    @PostMapping("/formData")
    public FormData insert(FormData formData){
        return formDataService.insert(formData);
    }

    /**
     * 更新表单数据
     * @date 2020/03/23
     **/
    @PutMapping("/formData")
    public FormData update(FormData formData){
        return formDataService.update(formData);
    }

}
