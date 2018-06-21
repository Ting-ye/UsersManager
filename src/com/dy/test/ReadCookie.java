package com.dy.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReadCookie")
public class ReadCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Cookie cookies[]=request.getCookies();
        System.out.println(cookies.length);

        for(int i=0;i<cookies.length;i++){
            Cookie cookie=cookies[i];
            out.println("cookie 的信息"+cookie.getName()+"   "+cookie.getValue());
        }
    }
}
