package com.dy.view;

import com.dy.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddUsersView")
public class AddUsersView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>添加用户</h1><hr />");
        out.println("<form action='/UsersManager/UserClServlet?type=add' method='post'>");
        out.println("<table border=1px bordercolor=blue cellspacing=0 width=300px>");
        out.println("<tr><td>用户名</td><td><input type='text' name='username' /></td></tr>");
        out.println("<tr><td>邮箱地址</td><td><input type='text' name='email' /></td></tr>");
        out.println("<tr><td>权限等级</td><td><input type='text' name='grade' /></td></tr>");
        out.println("<tr><td>用户密码</td><td><input type='text' name='password' /></td></tr>");
        out.println("<tr><td><input type='submit' value='添加用户'/></td><td><input type='reset' value='重新输入'/></td></tr>");
        out.println("</table>");
        out.println("</form>");
    }
}
