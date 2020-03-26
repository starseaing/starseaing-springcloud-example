package com.starseaing.example.form.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starseaing.example.form.mapper.FormDataMapper;
import com.starseaing.example.form.model.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 表单数据服务
 *
 * @author chentc
 * @since 2020/3/23
 */
@Service
@Transactional
public class FormDataService {

    @Autowired
    private FormDataMapper formDataMapper;

    public FormData insert(FormData formData) {
        int ret = formDataMapper.insert(formData);
        return formData;
    }

    public FormData delete(String id) {
        FormData formData = this.get(id);
        int ret = formDataMapper.deleteById(id);
        return formData;
    }

    public FormData update(FormData formData) {
        int ret = formDataMapper.updateById(formData);
        return formData;
    }

    public FormData get(String id) {
        return formDataMapper.selectById(id);
    }

    public IPage<FormData> pageList(int pageNumber, int pageSize, FormData formData) {
        IPage<FormData> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<FormData> queryWrapper = new QueryWrapper<>(formData);
        //queryWrapper.eq("x.name", "1");

        IPage<FormData> pageList = formDataMapper.selectPage(page, queryWrapper);
        return pageList;
    }

    public FormData getFormDataByBusinessKeyAndFormKey(String businessKey, String formKey) {
        QueryWrapper<FormData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("BUSINESS_KEY", businessKey);
        queryWrapper.eq("FORM_KEY", formKey);

        return formDataMapper.selectOne(queryWrapper);
    }
}