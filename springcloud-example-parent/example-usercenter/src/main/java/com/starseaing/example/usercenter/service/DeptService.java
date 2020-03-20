package com.starseaing.example.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starseaing.example.usercenter.mapper.DeptMapper;
import com.starseaing.example.usercenter.model.Dept;
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
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public int insert(Dept dept) {
        return deptMapper.insert(dept);
    }

    public int delete(String id) {
        int ret = deptMapper.deleteById(id);
        return ret;
    }

    public int update(Dept user) {
        int ret = deptMapper.updateById(user);
        return ret;
    }

    public Dept get(String id) {
        return deptMapper.selectById(id);
    }


    public IPage<Dept> pageList(int pageNumber, int pageSize, Dept dept) {
        IPage<Dept> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>(dept);
        IPage<Dept> pageList = deptMapper.selectPage(page, queryWrapper);
        return pageList;
    }
}

