package com.itheima.controller;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//员工管理控制器
@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    /*
        Short:整数（范围更小）
        Integer:整数（范围更大）
        Long:整数（范围比Integer还大）
     */
    @GetMapping
    public Result page(
            String name,
            Short gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            Integer page,
            Integer pageSize){
        log.info("员工分页：{},{},{},{},{},{}",name,gender,begin,end,page,pageSize);

        PageBean pageBean = empService.page(name,gender,begin,end,page,pageSize);
        return Result.success(pageBean);
    }
    /*
        删除（批量）员工
     */
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        log.info("删除员工:{}",ids);
        empService.deleteByIds(ids);
        return Result.success();
    }
    /*
    新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        try {
            empService.save(emp);
        } catch (Exception e) {
            //打印原始异常
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
    /*
    回显员工
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }
    /*
    修改员工
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }
//    @Override
//    public Emp login(Emp emp){
//        Emp e=
//    }
}