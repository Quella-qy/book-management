package com.itheima.filter;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")//过滤器过滤全局请求
@Slf4j
public class LoginFilter implements Filter {

    //任何请求都会经过该方法，默认拦截
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //0.为了请求响应对象的功能更丰富，我们需要对这两个对象进行转换
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.登录请求放行
        String requestURI = req.getRequestURI();
        if(requestURI.contains("/login")){
            log.info("【无需校验】：登录请求直接放行");
            filterChain.doFilter(req,resp);//放行，同一对象不同称呼
            return;//结束
        }
        //2.获取token
        String token = req.getHeader("Token");
        //3.解析token(token为null,token过期,token伪造)
        try {
            Claims map = Jwts.parser()
                    .setSigningKey("itheima".getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            log.info("【校验token通过】用户名：{}",map.get("username"));

        } catch (Exception e) {
            log.info("【校验token失败】请重新登录");
            //4.响应失败内容给前端
            resp.getWriter().write("{\n" +
                    "\t\"code\": 0,\n" +
                    "\t\"msg\": \"NOT_LOGIN\",\n" +
                    "\t\"data\": null\n" +
                    "}");
            return;//结束
        }
        filterChain.doFilter(req,resp);

    }
}
