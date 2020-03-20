package com.starseaing.example.usercenter.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starseaing.example.usercenter.model.Emp;
import com.starseaing.example.usercenter.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 雇员信息
 *
 * @author chentc
 * @since 2019/12/12
 */
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 新增
     */
    @PostMapping
    public int save(@RequestBody Emp emp) {
        int count = empService.insert(emp);
        return count;
    }

    /**
     * 修改
     */
    @PutMapping
    public int update(@RequestBody Emp emp) {
        int count = empService.update(emp);
        return count;
    }

    /**
     * 删除
     */
    @DeleteMapping
    public int delete(String id) {
        int count = empService.delete(id);
        return count;
    }

    /**
     * 查询
     */
    @GetMapping
    public Emp get(String id) {
        Emp emp = empService.get(id);
        if (emp != null) {
            return emp;
        } else {
            return null;
        }
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public IPage<Emp> list(@RequestParam(required = false) Emp emp,
                           @RequestParam(required = false, defaultValue = "0") int pageNumber,
                           @RequestParam(required = false, defaultValue = "10") int pageSize) {
        IPage<Emp> pageList = empService.pageList(pageNumber, pageSize, emp);
        return pageList;
    }
}

