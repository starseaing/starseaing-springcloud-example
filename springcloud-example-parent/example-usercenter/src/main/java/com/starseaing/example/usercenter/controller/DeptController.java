package com.starseaing.example.usercenter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starseaing.example.usercenter.model.Dept;
import com.starseaing.example.usercenter.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门信息
 *
 * @author chentc
 * @since 2019/12/12
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 新增
     */
    @PostMapping
    public int save(@RequestBody Dept dept) {
        int count = deptService.insert(dept);
        return count;
    }

    /**
     * 修改
     */
    @PutMapping
    public int update(@RequestBody Dept dept) {
        int count = deptService.update(dept);
        return count;
    }

    /**
     * 删除
     */
    @DeleteMapping
    public int delete(String id) {
        int count = deptService.delete(id);
        return count;
    }

    /**
     * 查询
     */
    @GetMapping
    public Dept get(String id) {
        Dept dept = deptService.get(id);
        if (dept != null) {
            return dept;
        } else {
            return null;
        }
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public IPage<Dept> list(@RequestParam(required = false) Dept dept,
                            @RequestParam(required = false, defaultValue = "0") int pageNumber,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        IPage<Dept> pageList = deptService.pageList(pageNumber, pageSize, dept);
        return pageList;
    }
}

