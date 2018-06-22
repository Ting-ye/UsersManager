package com.dy.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet(name = "cookieTest")
public class cookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Cookie cookies[]=request.getCookies();
        boolean b=false;
//        如果要在cookie中添加中文或者空格用下面这一行转码 取出的时候要解码取出否则为乱码 第二行为解码
//        String val=java.net.URLEncoder.encode("  空格和中文转码","utf-8");
//        String val=java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if ("lasttime".equals(cookie.getName())) {
                    out.println("您上次的登录时间为:" + cookie.getValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    String nowTime = simpleDateFormat.format(new java.util.Date());
                    Cookie mycookie = new Cookie("lasttime", nowTime);
                    mycookie.setMaxAge(7 * 3600 * 24);
                    response.addCookie(mycookie);
                    b=true;
                    break;
                }
            }
        }
        if(!b) {
            out.println("您是第一次访问..");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String nowTime = simpleDateFormat.format(new java.util.Date());
            Cookie cookie = new Cookie("lasttime", nowTime);
            cookie.setMaxAge(7 * 3600 * 24);
            response.addCookie(cookie);
        }

    }
}
