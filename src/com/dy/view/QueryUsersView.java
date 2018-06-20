package com.dy.view;

import com.dy.domain.User;
import com.dy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "QueryUsersView")
public class QueryUsersView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        int pageNow=1;//当前页面
        int pageSize=3;//每页数据量
        User u=(User) request.getAttribute("queryUserInfo");

        try {
            out.println("<h1>查询用户</h1><hr />");
            out.println("<form action='/UsersManager/UserClServlet?type=query' method='post'>");
            out.println("<input type='text' name='findName'/>&nbsp;&nbsp;<input type='submit' value='查询用户' style='width:100px; height:50px;'/>");
            out.println("<input type='radio' value='exactQuery' name='queryWay'/><label>精确查询</label><input type='radio' value='fuzzyQuery' name='queryWay'/><label>模糊查询</label>");
            out.println("</form>");
            out.println("<table border=1 bordercolor=blue cellspacing=0 width=500px>");
            out.println("<tr><th>id</th><th>用户名</th><th>e-mail</th><th>级别</th></tr>");
                out.println("<tr><td>" + u.getId()
                        + "</td><td>" + u.getUsername()
                        + "</td> <td>" + u.getEmail()
                        + "</td> <td>" + u.getGrade()
                        + "</td>");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
