package com.itheima.mapper;

import com.itheima.pojo.Dept;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept values(#{id},#{name},#{createTime},#{updateTime})")
    void save(Dept dept);

    @Select("select * from dept where name = #{name}")
    Dept findByName(String name);

    @Update("update dept set name = #{name} , update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    Dept findById(Integer id);

    List<Dept> list();
}
