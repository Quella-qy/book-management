package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        try {
            Emp empFroMysql = empService.login(emp);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", empFroMysql.getId());
            map.put("username", empFroMysql.getUsername());

            String token = Jwts.builder()
                    .setClaims(map)//存储到token的数据
                    .signWith(SignatureAlgorithm.HS256, "itheima".getBytes())//秘钥
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))//过期时间
                    .compact();
            return Result.success(token);
        } catch (Exception e) {
            //e.printStackTrace();//打印当前异常，但不会终止程序

            return Result.error(e.getMessage());
        }
    }
}
