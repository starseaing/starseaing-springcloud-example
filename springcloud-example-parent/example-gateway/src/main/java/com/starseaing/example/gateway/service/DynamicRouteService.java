package com.starseaing.example.gateway.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starseaing.example.gateway.mapper.DynamicRouteMapper;
import com.starseaing.example.gateway.model.DynamicRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 动态路由服务
 *
 * @author chentc
 * @since 2019/12/10
 */
@Service
@Transactional
public class DynamicRouteService {

    @Autowired
    private DynamicRouteMapper dynamicRouteMapper;

    public int insert(DynamicRoute dynamicRoute) {
        int count = dynamicRouteMapper.insert(dynamicRoute);
        return count;
    }

    public int delete(String id) {
        int ret = dynamicRouteMapper.deleteById(id);
        return ret;
    }

    public int update(DynamicRoute dynamicRoute) {
        int ret = dynamicRouteMapper.updateById(dynamicRoute);
        return ret;
    }

    public DynamicRoute get(String id) {
        return dynamicRouteMapper.selectById(id);
    }

    public IPage<DynamicRoute> pageList(int pageNumber, int pageSize, DynamicRoute dynamicRoute) {
        IPage<DynamicRoute> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<DynamicRoute> queryWrapper = new QueryWrapper<>(dynamicRoute);
        IPage<DynamicRoute> pageList = dynamicRouteMapper.selectPage(page, queryWrapper);
        return pageList;
    }

    /**
     * 查询所有
     * @return
     */
    public List<DynamicRoute> listAll() {
        QueryWrapper<DynamicRoute> queryWrapper = new QueryWrapper<>();
        List<DynamicRoute> list = dynamicRouteMapper.selectList(queryWrapper);
        return list;
    }

}
