package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  响应给页面的数据
 *  ·列表数据
 *  ·总记录数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
private Long total; //总记录数
private List rows; //当前页数据列表
}