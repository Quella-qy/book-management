package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

//部门业务规则
public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    void save(Dept dept);

    void update(Dept dept);

    Dept findById(Integer id);
}