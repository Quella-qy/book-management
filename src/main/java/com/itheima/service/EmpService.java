package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工业务规则
public interface EmpService {
    PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    void deleteByIds(List<Integer> ids);

    void save(Emp emp);

    Emp findById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}