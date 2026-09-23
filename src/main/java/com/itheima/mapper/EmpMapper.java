package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> page(String name, Short gender, LocalDate begin,LocalDate end);

    /*
        同样要使用动态sql? 接收的ids集合，内部的id数量可变，动态sql动态遍历集合，拼接 in (?,?,?)
        delete from emp where id in (1,5,6,7)

     */
    void deleteByIds(List<Integer> ids);


    @Select("select * from emp where username=#{username}")
    Emp findByUsername(String username);

    @Insert("insert into emp values(#{id},#{username},#{password},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void save(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp findById(Integer id);

    void update(Emp emp);
}
