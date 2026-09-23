package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/*
    演示如何实现文件基础上传
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private OssUtils ossUtils;

    /*
        文件上传到服务器后，MultipartFile接收

        文件 ->  file
     */
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException, ClientException {
        //防止文件名重复发生覆盖文件现象
        //1.截取后缀 1.jpg
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));// .jpg    .png   .gif
        //2.生成随机字符串作为文件名
        String randomStr = UUID.randomUUID().toString().replace("-", "");
        //3.拼接文件名
        String fileName = randomStr + suffix;
        //4.按日期划分文件目录
        SimpleDateFormat patten = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = patten.format(new Date());//    2026/04/11
        //5.上传后的完整文件名
        fileName = dateStr +"/"+fileName;
        //接收到文件对象，直接通过流形式上传到阿里云oss中。
        String url = ossUtils.upload(fileName, file.getInputStream());

        System.out.println("fabhui:"+url);

        return Result.success(url);
    }
}
