package com.dy.view;

import com.dy.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateUserVie")
public class UpdateUserVie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //接收UserClServlet页面获取到的对象 对象传送用userInfo的名字
        User user = (User) request.getAttribute("userInfo");


        out.println("<h1>修改用户</h1><hr />");
        out.println("<form action='/UsersManager/UserClServlet?type=update' method='post'>");
        out.println("<table border=1px bordercolor=blue cellspacing=0 width=300px>");
        out.println("<tr><td>id</td><td><input type='text' name='id' readonly value='" + user.getId() + "'/></td></tr>");
        out.println("<tr><td>用户名</td><td><input type='text' name='username' value='" + user.getUsername() + "'/></td></tr>");
        out.println("<tr><td>邮箱地址</td><td><input type='text' name='email' value='" + user.getEmail() + "'/></td></tr>");
        out.println("<tr><td>权限等级</td><td><input type='text' name='grade' value='" + user.getGrade() + "'/></td></tr>");
        out.println("<tr><td>用户密码</td><td><input type='text' name='password' value='" + user.getPassword() + "'/></td></tr>");
        out.println("<tr><td><input type='submit' value='修改用户'/></td><td><input type='reset' value='重新输入'/></td></tr>");
        out.println("</table>");
    }
}
