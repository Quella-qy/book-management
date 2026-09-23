package com.itheima.service.impl;

import com.itheima.mapper.ClassesMapper;
import com.itheima.pojo.Classes;
import com.itheima.service.ClassesService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Resource
    private ClassesMapper classesMapper;

    @Override
    public List<Classes> findAll() {
        return classesMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        classesMapper.deleteById(id);
    }

    @Override
    public void save(Classes classes) {
        Classes c = classesMapper.findByName(classes.getName());
        if (c != null) throw new RuntimeException("班级已存在");
        classes.setCreateTime(LocalDateTime.now());
        classes.setUpdateTime(LocalDateTime.now());
        classesMapper.save(classes);
    }

    @Override
    public void update(Classes classes) {
        classes.setUpdateTime(LocalDateTime.now());
        classesMapper.update(classes);
    }

    @Override
    public Classes findById(Integer id) {
        return classesMapper.findById(id);
    }
}