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
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //chrome规定本地不可以设置cookie!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Cookie []cookies=request.getCookies();
        boolean b=false;
        if(cookies!=null) {
            for (Cookie cookie:cookies) {
                String name=cookie.getName();
                if ("lasttime".equals(name)) {
                    out.println("您上次登录的时间是:" + cookie.getValue());
                    //更新时间
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String nowtime=simpleDateFormat.format(new java.util.Date());
                    Cookie mycookie=new Cookie("lasttime",nowtime);
                    mycookie.setMaxAge(3600*24*7);
                    response.addCookie(mycookie);
                    b = true;
                    break;
                }
            }
        }
        if(b==false){
            //没有找到 简写都是默认和true做比较。也就是说当b=false时，if (!b)就等价于!b == true
            out.println("您是第一次登录..");
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowtime=simpleDateFormat.format(new java.util.Date());
            Cookie cookie=new Cookie("lasttime",nowtime);
            cookie.setMaxAge(3600*24*7);
            response.addCookie(cookie);
        }
    }
}
