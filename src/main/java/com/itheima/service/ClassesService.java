package com.itheima.service;
import com.itheima.pojo.Classes;
import java.util.List;

public interface ClassesService {
    List<Classes> findAll();
    void deleteById(Integer id);
    void save(Classes classes);
    void update(Classes classes);
    Classes findById(Integer id);
}