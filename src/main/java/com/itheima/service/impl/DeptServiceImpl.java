package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {

        return deptMapper.findAll();//alt + enter
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /*
        0.检查该部门是否已经存在
        1.完善数据
        2.调用mapper完成新增
     */
    @Override
    public void save(Dept dept) {
        //0.查询部门是否存在
        Dept deptForMysql = deptMapper.findByName(dept.getName());
        if(deptForMysql != null){
            throw new RuntimeException(dept.getName()+"已存在");//抛异常后面代码不会执行
        }
        //1.完善数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper完成新增
        deptMapper.save(dept);
    }

    /*
        1.完善数据
        2.修改
     */
// 【带校验的更新逻辑，可直接替换上面的update方法】
    @Override
    public void update(Dept dept) {
        // 1. 校验：先查数据库里有没有和要修改的名称重复的部门
        // 注意：要排除自己本身，不然修改成和原来一样的名字也会报错
        List<Dept> allDepts = deptMapper.list();
        for (Dept d : allDepts) {
            if (d.getName().equals(dept.getName()) && !d.getId().equals(dept.getId())) {
                throw new RuntimeException("部门名称已存在！");
            }
        }
        // 2. 设置更新时间
        dept.setUpdateTime(LocalDateTime.now());
        // 3. 执行更新
        deptMapper.update(dept);
    }
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }
}