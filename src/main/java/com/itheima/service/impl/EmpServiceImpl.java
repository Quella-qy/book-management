package com.itheima.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//员工业务实现类
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     *
     * @param name 员工姓名
     * @param gender 性别：1男,2女
     * @param begin 开始时间
     * @param end 结束时间
     * @param page 页码
     * @param pageSize 每页展示条数
     *
     *  1.不带条件的普通分页查询
     *
     *  select * from emp limit 起始索引,查询条数
     * @return
     */
    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //1.設置分頁條件
        Page<Emp> pageInfo = PageHelper.startPage(page, pageSize);
        //2.执行基础查询
        empMapper.page(name,gender,begin,end);
        //3.封装数据并响应
        return new PageBean(pageInfo.getTotal(),pageInfo.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }

    /*
        教研是否存在该员工
        完善数据
        新增
     */
    @Override
    public void save(Emp emp) {
        //1.校验是否存在该员工
        Emp empForMysql = empMapper.findByUsername(emp.getUsername());
        if(empForMysql!=null){
            throw new RuntimeException(emp.getUsername()+"已存在");
        }
        //2.完善数据
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setPassword("123456");
        //3.新增
        empMapper.save(emp);
    }

    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);
    }

    /*
        完善数据
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    /*
        登录
     */
    @Override
    public Emp login(Emp emp) {
        //select * from emp where username =?
        Emp empForMysql = empMapper.findByUsername(emp.getUsername());
        if(empForMysql==null){
            throw new RuntimeException("用户名错误");
        }
        if(!empForMysql.getPassword().equals(emp.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return empForMysql;
    }

}