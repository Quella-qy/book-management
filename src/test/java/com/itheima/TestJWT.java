package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class TestJWT {

    //登录成功
    @Test
    public void testCreateToken(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",10087);
        map.put("username","tommy");
        String token = Jwts.builder()
                .setClaims(map)//存储到token的数据
                .signWith(SignatureAlgorithm.HS256, "itheima".getBytes())//秘钥
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60))//过期时间60s
                .compact();
        //eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwODcsImV4cCI6MTc3NjQ3NzU2OCwidXNlcm5hbWUiOiJ0b21teSJ9.6zfttp0I8Hy25MoGYZyfDMFs8_f15ZRrJWvxc6ksNUQ
        //eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwODcsImV4cCI6MTc3NjU2MDM1MCwidXNlcm5hbWUiOiJ0b21teSJ9.aHXK3w6Hb-XK-kvsfz1_Ok-t8jyW6apXg0m01zKXXi8
        System.out.println(token);
    }

    //校验请求
    @Test
    public void testPaserToken(){
        Claims map = Jwts.parser()
                .setSigningKey("itheima".getBytes())
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAwODcsImV4cCI6MTc3NjU3NDA2NiwidXNlcm5hbWUiOiJ0b21teSJ9.-E9wn2Eh7ICBh4qLSiZBtD-gcYLy_caee4mNT092zQY")
                .getBody();

        System.out.println(map);
    }
}
