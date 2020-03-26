package com.starseaing.example.form.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starseaing.example.form.mapper.FormMapper;
import com.starseaing.example.form.model.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 表单服务
 *
 * @author chentc
 * @since 2020/3/23
 */
@Service
@Transactional
public class FormService {

    @Autowired
    private FormMapper formMapper;

    public Form insert(Form form) {
        int ret = formMapper.insert(form);
        return form;
    }

    public Form delete(String id) {
        Form form = this.get(id);
        int ret = formMapper.deleteById(id);
        return form;
    }

    public Form update(Form form) {
        int ret = formMapper.updateById(form);
        return form;
    }

    public Form get(String id) {
        return formMapper.selectById(id);
    }

    public Form getByFormId(String formId) {
        QueryWrapper<Form> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FORM_ID", formId);
        return formMapper.selectOne(queryWrapper);
    }

    public IPage<Form> pageList(int pageNumber, int pageSize, Form form) {
        IPage<Form> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Form> queryWrapper = new QueryWrapper<>(form);
        //queryWrapper.eq("x.name", "1");

        IPage<Form> pageList = formMapper.selectPage(page, queryWrapper);
        return pageList;
    }

}