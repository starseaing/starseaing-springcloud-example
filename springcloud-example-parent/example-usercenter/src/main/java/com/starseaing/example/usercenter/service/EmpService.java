package com.starseaing.example.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starseaing.example.usercenter.mapper.EmpMapper;
import com.starseaing.example.usercenter.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author chentc
 * @since 2019/12/12
 */
@Service
@Transactional
public class EmpService {

    @Autowired
    private EmpMapper empMapper;

    public int insert(Emp emp) {
        return empMapper.insert(emp);
    }

    public int delete(String id) {
        int ret = empMapper.deleteById(id);
        return ret;
    }

    public int update(Emp user) {
        int ret = empMapper.updateById(user);
        return ret;
    }

    public Emp get(String id) {
        return empMapper.selectById(id);
    }


    public IPage<Emp> pageList(int pageNumber, int pageSize, Emp emp) {
        IPage<Emp> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>(emp);
        queryWrapper.eq("x.name", "1");

        IPage<Emp> pageList = empMapper.selectPage(page, queryWrapper);
        return pageList;
    }

    public IPage<Emp> test(int pageNumber, int pageSize, Emp emp) {
        IPage<Emp> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>(emp);

        IPage<Emp> pageList = empMapper.selectPage(page, queryWrapper);

        return pageList;
    }


}