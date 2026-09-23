package com.itheima.pojo;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Classes {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}