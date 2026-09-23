package com.itheima.mapper;
import com.itheima.pojo.Classes;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ClassesMapper {
    @Select("select * from classes")
    List<Classes> findAll();

    @Delete("delete from classes where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into classes values(null,#{name},#{createTime},#{updateTime})")
    void save(Classes classes);

    @Select("select * from classes where name=#{name}")
    Classes findByName(String name);

    @Update("update classes set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Classes classes);

    @Select("select * from classes where id=#{id}")
    Classes findById(Integer id);
}