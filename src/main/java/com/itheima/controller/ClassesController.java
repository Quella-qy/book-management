package com.itheima.controller;

import com.itheima.pojo.Classes;
import com.itheima.pojo.Result;
import com.itheima.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    // 查询全部班级
    @GetMapping
    public Result list() {
        List<Classes> list = classesService.findAll();
        return Result.success(list); // 必须返回这个格式！
    }

    // 新增
    @PostMapping
    public Result add(@RequestBody Classes classes) {
        classesService.save(classes);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        classesService.deleteById(id);
        return Result.success();
    }

    // 修改
    @PutMapping
    public Result update(@RequestBody Classes classes) {
        classesService.update(classes);
        return Result.success();
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Classes classes = classesService.findById(id);
        return Result.success(classes);
    }
}