package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//开启servlet相关注解
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class,args);
    }
}
