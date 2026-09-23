package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//部门管理控制器
@RestController
@RequestMapping("/depts")
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     */
    @GetMapping
    public Result findAll() {
        List<Dept> list = deptService.findAll();
        return Result.success(list);
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result save(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);
        try {
            deptService.save(dept);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }
    /**
     * 修改部门
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        try {
            deptService.update(dept);
            return Result.success();
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
}